
package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Employee;
import com.huak.auth.model.User;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.auth<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/31<BR>
 * Description:  系统菜单   <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EmployeeService employeeService;
    @Value("${work.order.monitor}")
    private String monitor;
    /**
     * 转至系统员工列表页
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统员工列表页");
        return "/auth/employees/list";
    }

    /**
     * 员工列表查询
     * @param paramsMap
     * @param page
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("员工列表页分页查询");
        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, employeeService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("员工列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


    /**
     * 跳转到员工添加页面
     * @param orgId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/add/{orgId}/{comId}", method = RequestMethod.GET)
    public String addPage(@PathVariable("orgId") String orgId,ModelMap modelMap
                           ,@PathVariable("comId") String comId) {
        try {
            Employee obj = new Employee();
            Map<String,Object> map = new HashMap<String,Object>();

            map.put("comId",comId);
            map.put("monitor",monitor);
            List<Map<String,Object>> emp = employeeService.getEmployee(map);

            obj.setOrgId(Long.parseLong(orgId));
            modelMap.put(Constants.OBJECT,obj);
            modelMap.put("emp",emp);
        } catch (Exception e) {
            logger.error("跳转到员工编辑页出错！"+e.getMessage());
        }
        return "/auth/employees/add";
    }

    /**
     * 跳转到员工修改页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}/{comId}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") String id ,ModelMap modelMap,@PathVariable("comId") String comId) {
        logger.info("跳转修改员工页");
        try {
            Employee employee = employeeService.getEmployeeById(id);
            Map<String,Object> map = new HashMap<String,Object>();

            map.put("comId",comId);
            map.put("monitor",monitor);
            List<Map<String,Object>> emp = employeeService.getEmployee(map);
            model.addAttribute(Constants.OBJECT, employee);
            modelMap.put("emp",emp);
        } catch (Exception e) {
            logger.error("跳转修改员工页异常" + e.getMessage());
        }
        return "/auth/employees/edit";
    }

    /**
     * 员工数据保存
     * @param employee
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Employee employee, HttpServletRequest request) {
        logger.info("添加员工");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // TODO 添加session，创建者
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(Constants.SESSION_KEY);
            employee.setId(UUIDGenerator.getUUID());
            employee.setCreator(user.getId());
            employeeService.addEmployee(employee);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加员工成功");
        } catch (Exception e) {
            logger.error("添加员工异常" + e.getMessage());
            jo.put(Constants.MSG, "添加员工失败");
        }
        return jo.toJSONString();
    }

    /**
     * 员工数据修改
     * @param employee
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editValue(Employee employee) {
        logger.info("修改员工");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int i = employeeService.editEmployee(employee);
            if(i>0){
                jo.put(Constants.FLAG, true);
                jo.put(Constants.MSG, "修改员工成功");
            }
        } catch (Exception e) {
            logger.error("修改员工异常" + e.getMessage());
            jo.put(Constants.MSG, "修改员工失败");
        }
        return jo.toJSONString();
    }

    /**
     * 员工修改
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFeed(@PathVariable("id") String id) {
        logger.info("删除员工");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            employeeService.removeEmployee(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除员工成功");
        } catch (Exception e) {
            logger.error("删除员工异常" + e.getMessage());
            jo.put(Constants.MSG, "删除员工失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出员工列表EXCEL");
        String workBookName = "员工列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)

        cellName.put("EMP_NAME", "员工名称");
        cellName.put("JOB_NUM", "员工工号");
        cellName.put("SEX", "性别");
        cellName.put("PHONE", "手机号");
        cellName.put("EMAIL", "邮箱");
        cellName.put("AGE", "年龄");
        cellName.put("BIRTHDAY", "生日");
        cellName.put("TEL", "联系电话");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = employeeService.export(paramsMap);
            HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            //response输出流导出excel
            String mimetype = "application/vnd.ms-excel";
            response.setContentType(mimetype);
            response.setCharacterEncoding("UTF-8");
            String fileName = workBookName + ".xls";
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            logger.error("导出员工列表EXCEL异常" + e.getMessage());
        }
    }

    /**
     *名称唯一性校验
     * sunbinbin
     * @return string
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public String checkUnique(@RequestParam Map<String, Object> paramsMap) {
        logger.info("员工唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            List<Employee> data= employeeService.selectByMap(paramsMap);
            if (data.size() == 0) {
                jo.put(Constants.FLAG, true);
            }else {
                jo.put(Constants.FLAG, false);
            }
        } catch (Exception e) {
            jo.put(Constants.FLAG, false);
            logger.error("员工唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }


}