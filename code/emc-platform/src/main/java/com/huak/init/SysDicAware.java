package com.huak.init;

import com.huak.common.Constants;
import com.huak.sys.SysDicService;
import com.huak.sys.model.SysDic;
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
 * Description:   字典缓存  <BR>
 * Function List:  <BR>
 */
@Component
public class SysDicAware implements InitializingBean, ServletContextAware {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServletContext servletContext;

    @Resource
    private SysDicService sysDicService;

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
        logger.info("----开始缓存系统字典----");
        Map sysDicMap = new HashMap<String, List<SysDic>>();
        List<Map<String, Object>> typeList = sysDicService.queryGroup(new HashMap<String, Object>());
        List<SysDic> sysDicList = sysDicService.queryAll(new HashMap<String, Object>());
        for (Map<String, Object> map : typeList) {
            List<SysDic> dics = new ArrayList<>();
            String typeUs = map.get("typeUs").toString();
            for (SysDic sysDic : sysDicList) {
                if (sysDic.getTypeUs().equals(typeUs)) {
                    dics.add(sysDic);
                }
            }
            sysDicMap.put(typeUs, dics);
        }

        //存入Application
        servletContext.setAttribute(Constants.SYS_DIC, sysDicMap);
        logger.info("----缓存系统字典成功----");
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
