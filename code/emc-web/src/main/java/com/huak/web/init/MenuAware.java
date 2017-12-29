package com.huak.web.init;

import com.huak.auth.FuncService;
import com.huak.auth.MenuService;
import com.huak.auth.model.Func;
import com.huak.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.init<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/17<BR>
 * Description:   权限菜单  <BR>
 * Function List:  <BR>
 */
@Component
public class MenuAware implements InitializingBean, ServletContextAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServletContext servletContext;

    @Resource
    private MenuService menuService;
    @Resource
    private FuncService funcService;

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("----开始缓存系统功能菜单------");
        Map<String, Object> paramsMap = new HashMap<>();
        //查询前台菜单
        paramsMap.put("menuType",0);
        paramsMap.put("menuNume","前台");
        Map<String,Object> beforeMenu = menuService.selectAuthByMap(paramsMap).get(0);

        paramsMap.clear();
        paramsMap.put("menuType",0);
        paramsMap.put("pMenuId",beforeMenu.get("id"));
        List<Map<String,Object>> beforeMenuOnes =  menuService.selectAuthByMap(paramsMap);
        List<Map<String,Object>> beforeMenuOnesNew = new ArrayList<>();
        for(Map<String,Object> oneMap:beforeMenuOnes){
            paramsMap.put("pMenuId",oneMap.get("id"));
            List<Map<String,Object>> beforeMenuTwos =  menuService.selectAuthByMap(paramsMap);
            List<Map<String,Object>> beforeMenuTwosNew = new ArrayList<>();
            for(Map<String,Object> twoMap:beforeMenuTwos){
                paramsMap.put("pMenuId",twoMap.get("id"));
                List<Map<String,Object>> beforeMenuThrs =  menuService.selectAuthByMap(paramsMap);
                List<Map<String,Object>> beforeMenuThrsNew = new ArrayList<>();
                for (Map<String,Object> thrMap:beforeMenuThrs){
                    Map<String, Object> funcMap = new HashMap<>();
                    funcMap.put("menuId",thrMap.get("id"));
                    List<Func> funcs = funcService.selectAllByMap(funcMap);
                    thrMap.put("funcs",funcs);
                    beforeMenuThrsNew.add(thrMap);
                }
                Map<String, Object> funcMap = new HashMap<>();
                funcMap.put("menuId",twoMap.get("id"));
                List<Func> funcs = funcService.selectAllByMap(funcMap);
                twoMap.put("funcs",funcs);
                twoMap.put("menus",beforeMenuThrsNew);
                beforeMenuTwosNew.add(twoMap);
            }
            Map<String, Object> funcMap = new HashMap<>();
            funcMap.put("menuId",oneMap.get("id"));
            List<Func> funcs = funcService.selectAllByMap(funcMap);
            oneMap.put("funcs",funcs);
            oneMap.put("menus",beforeMenuTwosNew);
            beforeMenuOnesNew.add(oneMap);
        }
        beforeMenu.put("menus",beforeMenuOnesNew);

        //查询后台菜单
        paramsMap.clear();
        paramsMap.put("menuType",1);
        paramsMap.put("menuNume","后台");
        Map<String,Object> afterMenu = menuService.selectAuthByMap(paramsMap).get(0);

        paramsMap.clear();
        paramsMap.put("menuType",1);
        paramsMap.put("pMenuId",afterMenu.get("id"));
        List<Map<String,Object>> afterMenuOnes =  menuService.selectAuthByMap(paramsMap);
        List<Map<String,Object>> afterMenuOnesNew = new ArrayList<>();
        for(Map<String,Object> oneMap:afterMenuOnes){
            paramsMap.put("pMenuId",oneMap.get("id"));
            List<Map<String,Object>> afterMenuTwos =  menuService.selectAuthByMap(paramsMap);
            List<Map<String,Object>> afterMenuTwosNew = new ArrayList<>();
            for(Map<String,Object> twoMap:afterMenuTwos){
                paramsMap.put("pMenuId",twoMap.get("id"));
                List<Map<String,Object>> afterMenuThrs =  menuService.selectAuthByMap(paramsMap);
                List<Map<String,Object>> afterMenuThrsNew = new ArrayList<>();
                for (Map<String,Object> thrMap:afterMenuThrs){
                    Map<String, Object> funcMap = new HashMap<>();
                    funcMap.put("menuId",thrMap.get("id"));
                    List<Func> funcs = funcService.selectAllByMap(funcMap);
                    thrMap.put("funcs",funcs);
                    afterMenuThrsNew.add(thrMap);
                }
                Map<String, Object> funcMap = new HashMap<>();
                funcMap.put("menuId",twoMap.get("id"));
                List<Func> funcs = funcService.selectAllByMap(funcMap);
                twoMap.put("funcs",funcs);
                twoMap.put("menus",afterMenuThrsNew);
                afterMenuTwosNew.add(twoMap);
            }
            Map<String, Object> funcMap = new HashMap<>();
            funcMap.put("menuId",oneMap.get("id"));
            List<Func> funcs = funcService.selectAllByMap(funcMap);
            oneMap.put("funcs",funcs);
            oneMap.put("menus",afterMenuTwosNew);
            afterMenuOnesNew.add(oneMap);
        }
        afterMenu.put("menus",afterMenuOnesNew);

        servletContext.setAttribute(Constants.GRANT_MENU_BEFORE, beforeMenu);
        servletContext.setAttribute(Constants.GRANT_MENU_AFTER, afterMenu);
        servletContext.setAttribute(Constants.GRANT_MENU_AFTER_ID,afterMenu.get("id"));
        servletContext.setAttribute(Constants.GRANT_MENU_BEFORE_ID,beforeMenu.get("id"));
        logger.info("----缓存系统功能菜单结束------");
    }

    /**
     * Set the {@link javax.servlet.ServletContext} that this object runs in.
     * <p>Invoked after population of normal bean properties but after an init
     * callback like InitializingBean's {@code afterPropertiesSet} or a
     * custom init-method. Invoked after ApplicationContextAware's
     * {@code setApplicationContext}.
     *
     * @param servletContext ServletContext object to be used by this object
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
