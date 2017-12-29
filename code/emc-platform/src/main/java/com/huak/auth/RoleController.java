package com.huak.auth;

import com.alibaba.fastjson.JSONObject;
import com.huak.auth.model.Role;
import com.huak.auth.model.RoleFuncRel;
import com.huak.auth.model.User;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
 * Date: 2016/8/30<BR>
 * Description:   角色  <BR>
 * Function List:  <BR>
 */

@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage() {
        logger.info("转至系统角色列表页");
        return "/auth/role/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, Object> paramsMap, Page page) {
        logger.info("角色列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, roleService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("角色列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/auth/role/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Role role, HttpServletRequest request) {
        logger.info("添加角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            // 添加session，创建者
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute(Constants.SESSION_KEY);
            role.setCreator(user.getId());

            role.setId(UUIDGenerator.getUUID());
            roleService.insertSelective(role);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "添加角色成功");
        } catch (Exception e) {
            logger.error("添加角色异常" + e.getMessage());
            jo.put(Constants.MSG, "添加角色失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
        logger.info("跳转修改角色页");
        try {
            model.addAttribute("role", roleService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("跳转修改角色页异常" + e.getMessage());
        }
        return "/auth/role/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @ResponseBody
    public String edit(Role role) {
        logger.info("修改角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            roleService.updateByPrimaryKeySelective(role);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "修改角色成功");
        } catch (Exception e) {
            logger.error("修改角色异常" + e.getMessage());
            jo.put(Constants.MSG, "修改角色失败");
        }
        return jo.toJSONString();
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRole(@PathVariable("id") String id) {
        logger.info("删除角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            roleService.deleteByPrimaryKey(id);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "删除角色成功");
        } catch (Exception e) {
            logger.error("删除角色异常" + e.getMessage());
            jo.put(Constants.MSG, "删除角色失败");
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/check/name", method = RequestMethod.POST)
    @ResponseBody
    public String checkRoleName(@RequestParam Map<String, Object> paramsMap) {
        logger.info("角色名称唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = roleService.checkRoleName(paramsMap);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("角色名称唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grantPage(Model model,@PathVariable("id") String id) {
        logger.info("跳转角色授权页");
        try {
            model.addAttribute("role", roleService.selectByPrimaryKey(id));
            model.addAttribute("auth", roleService.selectGrantByRoleKey(id));
        } catch (Exception e) {
            logger.error("跳转角色授权页异常" + e.getMessage());
        }
        return "/auth/role/grant";
    }

    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ResponseBody
    public String grant(String roleId,String[] funcId) {
        logger.info("角色授权");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);

        try {
            List<RoleFuncRel> rels = new ArrayList<>();
            for(String fid:funcId){
                RoleFuncRel rel = new RoleFuncRel(roleId,fid);
                rels.add(rel);
            }
            roleService.grantRoleFunc(roleId,rels);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "角色授权成功");
        } catch (Exception e) {
            logger.error("角色授权异常" + e.getMessage());
            jo.put(Constants.MSG, "角色授权异常");
        }

        return jo.toJSONString();
    }


/*     @RequestMapping(value = "/select/list", method = RequestMethod.PATCH)
     @ResponseBody
     public String userSelect(@RequestParam Map<String, Object> paramsMap, Page page) {
         logger.info("选择角色列表页分页查询");

         JSONObject jo = new JSONObject();
         try {
             jo.put(Constants.LIST, roleService.queryByPage(paramsMap, page));
         } catch (Exception e) {
             logger.error("选择角色列表页分页查询异常" + e.getMessage());
         }
         return jo.toJSONString();
     }*/
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, Object> paramsMap, HttpServletResponse response) {
        logger.info("导出角色列表EXCEL");
        String workBookName = "角色列表";//文件名
        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
        cellName.put("ROLE_NAME", "角色名称");
        cellName.put("ROLE_DES", "角色说明");
        cellName.put("memo", "备注");
        List<Map<String, Object>> cellValues = null;//列值
        OutputStream out = null;
        try {
            cellValues = roleService.exportRoles(paramsMap);
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
            logger.error("导出角色列表EXCEL异常" + e.getMessage());
        }
    }

}
