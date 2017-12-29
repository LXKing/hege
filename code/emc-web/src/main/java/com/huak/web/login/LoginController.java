package com.huak.web.login;

import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.Producer;
import com.huak.auth.EmployeeService;
import com.huak.auth.UserService;
import com.huak.auth.model.Employee;
import com.huak.auth.model.User;
import com.huak.auth.type.UserStatusType;
import com.huak.base.ReversibleEncryption;
import com.huak.common.Constants;
import com.huak.org.CompanyService;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.web.login<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/23<BR>
 * Description:  登录   <BR>
 * Function List:  <BR>
 */
@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;
    @Resource
    private CompanyService companyService;
    @Resource
    private OrgService orgService;
    @Resource
    private Producer captchaProducer;
    @Resource
    private ReversibleEncryption reversibleEncryption;
    @Resource
    private EmployeeService employeeService;

    private String IS_LOGIN = "isLogin";
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login-out")
    public String loginOut(HttpServletRequest request) {
        logger.info("退出后台");
        //删除session会话
        HttpSession session = request.getSession(false);
        session.removeAttribute(com.huak.common.Constants.SESSION_KEY);
        //返回登录页
        return "login";
    }

    @RequestMapping(value = "/login-in")
    @ResponseBody
    //@OperationLogAnnotation(operationType="查询",module="能管产品前台-登录服务-用户登录",methods="loginIn",description="产品后台登录")
    public String loginIn(HttpServletRequest request, String login, String pwd, String vc) {
        logger.info("登录前台");
        HttpSession session = request.getSession();
        JSONObject jo = new JSONObject();
        jo.put(IS_LOGIN, true);
        try {
//            String kpvc = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//            // 校验验证码
//            if (kpvc.equals(vc)) {
//                jo.put(IS_LOGIN, true);
//            } else {
//                jo.put(IS_LOGIN, false);
//                jo.put("msg", "验证码错误");
//                return jo.toJSONString();
//            }
            // 校验用户密码
            pwd = reversibleEncryption.encode(pwd);
            User loginUser = new User();
            loginUser.setLogin(login);
            loginUser.setPassword(pwd);
            User user = userService.findByLoginAndPwd(loginUser);
            if (user != null) {
                if (UserStatusType.ENABLE.getKey() == Integer.valueOf(user.getUseStatus())) {
                    //登录成功存入用户信息、权限到session，跳转到首页
                    String id = user.getId();
                    Org org = orgService.selectByPrimaryKey(user.getOrgId());
                    Company company = companyService.selectByPrimaryKey(org.getComId());
                    Employee employee = employeeService.getEmployeeByUserId(id);
                    session.setAttribute(Constants.SESSION_KEY, user);
                    session.setAttribute(Constants.SESSION_ORG_KEY, org);
                    session.setAttribute(Constants.SESSION_COM_KEY, company);
                    //员工信息
                    session.setAttribute(Constants.SESSION_EMPLOYEE_KEY, employee);
                    //权限
                    session.setAttribute(com.huak.common.Constants.SESSION_AUTH_KEY, userService.getAuths(id));
                    userService.update2LoginSuccess(id);
                    jo.put(IS_LOGIN, true);
                } else {
                    jo.put(IS_LOGIN, false);
                    jo.put("msg", "用户被禁用，请联系管理员");
                    return jo.toJSONString();
                }

            } else {
                // 登录失败，返回json信息到前端交互
                jo.put(IS_LOGIN, false);
                jo.put("msg", "登录失败，账号密码不匹配");
                return jo.toJSONString();
            }
        } catch (Exception e) {
            jo.put(IS_LOGIN, false);
            jo.put("msg", "登录异常");
            logger.error("登录异常" + e.getMessage());
        }

        return jo.toJSONString();
    }

    @RequestMapping("/generateVc")
    public void generateVc(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setDateHeader("Expires", 0);
            //Set standard HTTP/1.1 no-cache headers.
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            //Set IE extended HTTP/1.1 no-cache headers (use addHeader).
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            //Set standard HTTP/1.0 no-cache header.
            response.setHeader("Pragma", "no-cache");
            //return a jpeg
            response.setContentType("image/jpeg");
            //create the text for the image
            String capText = captchaProducer.createText();
            //store the text in the session
            request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, capText);
            // create the image with the text
            BufferedImage bi = captchaProducer.createImage(capText);
            ServletOutputStream out = response.getOutputStream();
            // write the data out
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value ={"","/index","/"}, method = RequestMethod.GET)
    public String indexPage(){
        logger.info("跳转首页");
        return "index";
    }
}
