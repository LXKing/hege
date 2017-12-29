package com.huak.easygo;

import com.huak.auth.UserService;
import com.huak.auth.model.User;
import com.huak.common.Constants;
import com.huak.org.CompanyService;
import com.huak.org.OrgService;
import com.huak.org.model.Company;
import com.huak.org.model.Org;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 各服务（消费者）,验证登录后重置Redis中的Session时间
 *
 * @author IG
 * @version 1.0.0
 * @ClassName LoginInterceptor
 * @Date 2017年4月6日 下午3:04:16
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String RSESSIONID = "RSESSIONID";
    private static int EXPIRETIME = 600000;

    //EMC service
    @Resource
    private UserService userService;
    @Resource
    private OrgService orgService;
    @Resource
    private CompanyService companyService;

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // Do nothing because of temporary use.
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // Do nothing because of temporary use.
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {
        Cookie[] cookies = request.getCookies();
        Jedis jedis = RedisTools.getJedis();
        HttpSession session = request.getSession();
//        logger.error("request------>getContextPath:"+request.getContextPath()+" getLocalAddr:"+request.getLocalAddr()+" getRemoteAddr:"+request.getRemoteAddr());

        try {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    //logger.error("cookie----->getName:"+cookie.getName()+"getValue"+cookie.getValue());
//					   if(cookie.getName().equals(RSESSIONID)){ 
                    if (RSESSIONID.equals(cookie.getName())) {
                        //是否有
                        if (jedis.exists(cookie.getValue())) {  //有，续期
                            //判断 ressionId
                            String rid = cookie.getValue();
                            boolean isSession = false;
                            String orid = (String) session.getAttribute("rid");
                            if (null == orid || !rid.equals(orid) || "".equals(orid)) {
                                isSession = true;
                            }
                            //logger.error("isSession----->"+isSession);
                            if(isSession){ // EMC模拟登陆
                                String userName = jedis.hget(cookie.getValue(), "username");
                                User user = userService.getUserByName(userName);
                                //logger.error("user----->"+user);
                                if(null == user){
                                    //response.sendRedirect(request.getContextPath() + "/login");
                                    response.sendRedirect(Constants.LOGIN_URL);
                                    return false;
                                }
                                Org org = orgService.selectByPrimaryKey(user.getOrgId());
                                Company company = companyService.selectByPrimaryKey(org.getComId());
                                session.setAttribute(Constants.SESSION_KEY, user);
                                session.setAttribute(Constants.SESSION_ORG_KEY, org);
                                session.setAttribute(Constants.SESSION_COM_KEY, company);
                            }else{//判断本身系统用户信息是否存在
                                String userName = jedis.hget(cookie.getValue(), "username");
                                User user = null;
                                Org org = null;
                                Company company = null;
                                if(null == session.getAttribute(Constants.SESSION_KEY)){
                                    user = userService.getUserByName(userName);
                                    session.setAttribute(Constants.SESSION_KEY, user);
                                }
                                if(null == session.getAttribute(Constants.SESSION_ORG_KEY)){
                                    if(null == user || null == user.getOrgId()){
                                        //response.sendRedirect(request.getContextPath() + "/login");
                                        response.sendRedirect(Constants.LOGIN_URL);
                                        return false;
                                    }else{
                                        org = orgService.selectByPrimaryKey(user.getOrgId());
                                        session.setAttribute(Constants.SESSION_ORG_KEY, org);
                                    }

                                }
                                if(null == session.getAttribute(Constants.SESSION_COM_KEY)){
                                    if(null == org || null == org.getComId()){
                                        //response.sendRedirect(request.getContextPath() + "/login");
                                        response.sendRedirect(Constants.LOGIN_URL);
                                        return false;
                                    }else{
                                        company = companyService.selectByPrimaryKey(org.getComId());
                                        session.setAttribute(Constants.SESSION_COM_KEY, company);
                                    }

                                }

                            }
                            session.setAttribute("rid", rid);
                            jedis.expire(cookie.getValue(), EXPIRETIME);
                            return true;
                        } else { //没有,返回提醒
                            //response.sendRedirect(request.getContextPath() + "/login");
                            response.sendRedirect(Constants.LOGIN_URL);
                            return false;
                        }
                    }
                }
            }

            //logger.error("session------>:"+session.getAttribute(Constants.SESSION_KEY));
            if (null != session.getAttribute(Constants.SESSION_KEY)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RedisTools.returnResource(RedisTools.getPool(), jedis);
        }
        //response.sendRedirect(request.getContextPath() + "/login");
        response.sendRedirect(Constants.LOGIN_URL);
        return false;
    }


}
