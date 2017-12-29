package com.huak.auth;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huak.auth.model.User;
import com.huak.auth.model.vo.OrgEmpVo;
import com.huak.common.CommonExcelExport;
import com.huak.common.Constants;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Page;
import com.huak.log.EMCLog;

/**
 * 用户controller
 * @author Administrator
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private UserService userService;

    /**
     * 跳转到列表页面
     * @param modelMap
     * @return
     */
    @EMCLog(key="用户管理",name="跳转到用户列表页面",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listPage(ModelMap modelMap) {
        logger.info("转至系统用户列表页");
        return "/auth/user/list";
    }

    /**
     * 根据条件查询列表信息
     * @param paramsMap
     * @param page
     * @return
     */
    @EMCLog(key="用户管理",name="查询用户列表",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/list", method = RequestMethod.PATCH)
    @ResponseBody
    public String list(@RequestParam Map<String, String> paramsMap, Page page) {
        logger.info("用户列表页分页查询");

        JSONObject jo = new JSONObject();
        try {
            jo.put(Constants.LIST, userService.queryByPage(paramsMap, page));
        } catch (Exception e) {
            logger.error("用户列表页分页查询异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    
    /**
     * 根据orgid查询此机构下的员工
     * @param orgId
     * @return
     */
    @EMCLog(key="用户管理",name="查询某组织结构下的员工",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/org/emp", method = RequestMethod.POST)
    @ResponseBody
    public String orgEmp(String orgId) {
        logger.info("查询某组织节点下的所有员工");
        String result = "";
        ObjectMapper om = new ObjectMapper();
        try {
            List<OrgEmpVo> oeList = userService.queryOrgEmpByOrgId(orgId);
            result = om.writeValueAsString(oeList);
        } catch (Exception e) {
            logger.error("查询某组织节点下的所有员工" + e.getMessage());
        }
        return result;
    }
    
    /**
     * 跳转到添加用户页面
     * @return
     */
    @EMCLog(key="用户管理",name="跳转到新增用户页面",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        return "/auth/user/add";
    }

    /**
     * 添加用户信息
     * @param request
     * @param user
     * @return
     */
    @EMCLog(key="新增用户",name="新增用户信息",type=Constants.OPT_TYPE_INSERT)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(HttpServletRequest request,User user) {
        logger.info("添加用户");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
        	User u = (User) request.getSession().getAttribute(Constants.SESSION_KEY);
        	user.setCreator(u.getId());
        	user.setId(UUIDGenerator.getUUID());
            int ret = userService.addUser(user);
            if(ret<=0){
            	jo.put(Constants.MSG, "添加用户失败");
            }else{
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "添加用户成功");
            }
        } catch (Exception e) {
            logger.error("添加用户异常" + e.getMessage());
            
        }
        return jo.toJSONString();
    }
    
    /**
     * 校验登录账号唯一性
     * @param login
     * @return
     */
    @EMCLog(key="新增用户",name="校验登录账号唯一性",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/check/login", method = RequestMethod.POST)
    @ResponseBody
    public String checkLogin(String login) {
        logger.info("登录账号唯一性校验");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            Long num = userService.checkLogin(login);
            if (num == 0) {
                jo.put(Constants.FLAG, true);
            }
        } catch (Exception e) {
            logger.error("登录账号唯一性校验异常" + e.getMessage());
        }
        return jo.toJSONString();
    }
    
    /**
     * 跳转到编辑页面
     * @param model
     * @param id
     * @return
     */
    @EMCLog(key="用户管理",name="跳转到修改用户列表页面",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPage(Model model, @PathVariable("id") String id) {
	    logger.info("跳转编辑用户页");
	    try {
	    	User user = userService.getUser(id);
	    	if(user!=null){
	    		model.addAttribute("user", user);
	    		List<OrgEmpVo> eList = userService.queryOrgEmpByOrgId(user.getOrgId());
	    		StringBuilder sb = new StringBuilder();
	    		if(null!=eList&&eList.size()>0){
	    			for(OrgEmpVo oe:eList){
	    				sb.append("<option ");
	    				sb.append("value=\"").append(oe.getEmpId()).append("\"");
	    				if(oe.getEmpId().equals(user.getEmpId())){
	    					sb.append(" selected=\"selected\"");
	    				}
	    				sb.append(" >").append(oe.getEmpName());
	    				sb.append("</option>");
	    			}
	    		}
	    		model.addAttribute("emp", sb.toString());
	    	}
	    } catch (Exception e) {
	        logger.error("跳转编辑页异常" + e.getMessage());
	    }
	    return "/auth/user/edit";
    }
    
    /**
     * 编辑用户信息
     * @param user
     * @return
     */
    @EMCLog(key="编辑用户",name="编辑用户信息",type=Constants.OPT_TYPE_UPDATE)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(User user) {
        logger.info("编辑用户");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int ret = userService.editUser(user);
            if(ret<=0){
            	jo.put(Constants.MSG, "编辑用户失败");
            }else{
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "编辑用户成功");
            }
        } catch (Exception e) {
            logger.error("编辑用户异常" + e.getMessage());
            jo.put(Constants.MSG, "编辑用户失败");
        }
        return jo.toJSONString();
    }

    /**
     * 批量删除用户信息
     * @param ids
     * @return
     */
    @EMCLog(key="删除用户",name="批量删除用户信息",type=Constants.OPT_TYPE_DELETE)
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUsers(String[] ids) {
        logger.info("批量删除用户");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int ret = userService.removeUsers(ids);
            if(ret<=0 || ret!=ids.length){
            	jo.put(Constants.MSG, "删除用户失败");
            }else{
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "删除用户成功");
            }
        } catch (Exception e) {
            logger.error("删除用户异常" + e.getMessage());
            jo.put(Constants.MSG, "删除用户失败");
        }
        return jo.toJSONString();
    }
    
    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @EMCLog(key="删除用户",name="删除用户信息",type=Constants.OPT_TYPE_DELETE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable("id") String id) {
        logger.info("删除用户");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
        	String[] ids = new String[1];
        	ids[0] = id;
            int ret = userService.removeUsers(ids);
            if(ret<=0){
            	jo.put(Constants.MSG, "删除用户失败");
            }else {
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "删除用户成功");
			}
        } catch (Exception e) {
            logger.error("删除用户异常" + e.getMessage());
            jo.put(Constants.MSG, "删除用户失败");
        }
        return jo.toJSONString();
    }
    
    /**
     * 重置用户密码
     * @param ids
     * @return
     */
    @EMCLog(key="重置密码",name="重置用户密码",type=Constants.OPT_TYPE_UPDATE)
    @RequestMapping(value = "/reset/pwd", method = RequestMethod.POST)
    @ResponseBody
    public String resetPwd(String[] ids) {
        logger.info("重置密码");
        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            String pwd = userService.resetPwd(ids);
            if(pwd==null){
            	jo.put(Constants.MSG, "重置密码失败");
            }else{
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "重置密码成功,密码为【" + pwd + "】");
            }
        } catch (Exception e) {
            jo.put(Constants.MSG, "重置密码失败");
            logger.error("重置密码异常" + e.getMessage());
        }
        return jo.toJSONString();
    }

    /**
     * 禁用/启用用户
     * @param status
     * @param ids
     * @return
     */
    @EMCLog(key="用户管理",name="禁用/启用用户管理",type=Constants.OPT_TYPE_UPDATE)
    @RequestMapping(value = "/status/{status}", method = RequestMethod.POST)
    @ResponseBody
    public String status(@PathVariable("status") String status,String[] ids) {
        logger.info("修改用户可用状态");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            int ret = userService.editUseStatus(status,ids);
            if(ret==-1){
            	jo.put(Constants.MSG, "修改失败");
            }else{
            	jo.put(Constants.FLAG, true);
            	jo.put(Constants.MSG, "修改成功");
            }
        } catch (Exception e) {
            logger.error("修改用户可用状态异常" + e.getMessage());
            jo.put(Constants.MSG, "修改失败");
        }
        return jo.toJSONString();
    }

    /**
     * 导出用户信息
     * @param paramsMap
     * @param response
     */
    @EMCLog(key="用户管理",name="导出用户信息",type=Constants.OPT_TYPE_SELECT)
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(@RequestParam Map<String, String> param, HttpServletResponse response) {
        logger.info("导出用户列表EXCEL");
        try {
        	
        	JSONObject jo = new JSONObject();
	        jo.put(Constants.FLAG, false);
	        Map<String, String> cellName = new LinkedHashMap<>();//列标题(有序)
	        cellName.put("user_name", "用户名称");
	        cellName.put("login", "登录账号");
	        cellName.put("mobile", "联系电话");
	        cellName.put("mail", "电子邮箱");
	        cellName.put("use_status", "使用状态");
	        cellName.put("memo", "备注");
//	        cellName.put("login_time", "登录时间");
//	        cellName.put("last_login_time", "上次登录时间");
//	        cellName.put("login_num", "登录次数");
//	        cellName.put("creator", "创建者");
//	        cellName.put("create_time", "创建时间");
	        List<Map<String, Object>> cellValues = userService.exportUser(param);
            if(cellValues==null){
            	jo.put(Constants.MSG, "导出失败");
            }else if(cellValues.size()==0){
            	jo.put(Constants.MSG, "没有数据要导出");
            }else{
            	jo.put(Constants.MSG, "导出失败");
            	HSSFWorkbook wb = CommonExcelExport.excelExport(cellName, cellValues);
            	OutputStream out = response.getOutputStream();
            	//输出Excel文件  
            	String mimetype = "application/vnd.ms-excel";
                response.setContentType(mimetype);
                response.setCharacterEncoding("UTF-8");
                String fileName = "用户列表.xls";
                response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                wb.write(out);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("导出用户列表EXCEL异常" + e.getMessage());
        }
    }

    @RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
    public String grantPage(Model model,@PathVariable("id") String id) {
        logger.info("跳转分配角色页");
        try {
            model.addAttribute("user", userService.getUser(id));
            model.addAttribute("role", userService.getRole(id));
        } catch (Exception e) {
            logger.error("跳转分配角色页异常" + e.getMessage());
        }
        return "/auth/user/grant";
    }

    @RequestMapping(value = "/grant", method = RequestMethod.POST)
    @ResponseBody
    public String grant(@RequestParam Map<String, Object> paramsMap) {
        logger.info("用户分配角色");

        JSONObject jo = new JSONObject();
        jo.put(Constants.FLAG, false);
        try {
            userService.deleteRoleByUser(paramsMap);
            userService.grantRole(paramsMap);
            jo.put(Constants.FLAG, true);
            jo.put(Constants.MSG, "分配角色成功");
        } catch (Exception e) {
            logger.error("用户分配角色异常" + e.getMessage());
            jo.put(Constants.MSG, "分配角色失败");
        }
        return jo.toJSONString();
    }
}

