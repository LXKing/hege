package com.huak.web.init;

import com.alibaba.fastjson.JSON;
import com.huak.common.Constants;
import com.huak.tools.NavigationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.init<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/5<BR>
 * Description:    实现ServletConfigAware初始化路径到Application中 <BR>
 * Function List:  <BR>
 */
@Component
public class ClassPathAware implements InitializingBean, ServletContextAware {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServletContext servletContext;

    @Value("${platform.url}")
    private String platPath;
    @Value("${web.url}")
    private String webPath;

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
        logger.info("----开始加载绝对路径----");
        servletContext.setAttribute(Constants.PLATFORM, platPath);
        servletContext.setAttribute(Constants.WEB, webPath);
        logger.info("----加载绝对路径成功----");

        logger.info("----开始面包屑导航----");
        servletContext.setAttribute(Constants.NAVIGATIONS, JSON.toJSON(NavigationConstant.NAVIGATIONS));
        logger.info("----加载面包屑导航-成功----");
    }

    /**
     * Set the {@link javax.servlet.ServletContext} that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
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
