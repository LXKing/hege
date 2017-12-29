package com.huak.init;

import com.alibaba.fastjson.JSONArray;
import com.huak.sys.type.AdminLevelType;
import com.huak.org.AdministrativeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by MR-BIN on 2017/5/17.
 */
@Component
public class AdministrativeInitAware implements InitializingBean, ServletContextAware {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ServletContext servletContext;
    @Resource
    private AdministrativeService administrativeService;
//
    @Override
    public void afterPropertiesSet() throws Exception {
//
        logger.info("----开始缓存行政区划----");

        Map<String, String> paramsMap = new HashMap<>();
        //缓存省
        paramsMap.put("admLevel", String.valueOf(AdminLevelType.PROVINCE.getKey()));
        List<Map<String, Object>> province = administrativeService.findAllByLevel(paramsMap);
        JSONArray pa = new JSONArray();
        pa.addAll(province);
        servletContext.setAttribute("province", pa);

        //缓存市
        paramsMap.put("admLevel", String.valueOf(AdminLevelType.CITY.getKey()));
        List<Map<String, Object>> city = administrativeService.findAllByLevel(paramsMap);
        JSONArray pci = new JSONArray();
        pci.addAll(city);
        servletContext.setAttribute("city",pci);

        //缓存县
        paramsMap.put("admLevel", String.valueOf(AdminLevelType.COUNTY.getKey()));
        List<Map<String, Object>> county = administrativeService.findAllByLevel(paramsMap);
        JSONArray pc = new JSONArray();
        pc.addAll(county);
        servletContext.setAttribute("county",pc);

        //缓存镇
        paramsMap.put("admLevel", String.valueOf(AdminLevelType.TOWN.getKey()));
        List<Map<String, Object>> town = administrativeService.findAllByLevel(paramsMap);
        JSONArray pt = new JSONArray();
        pt.addAll(town);
        servletContext.setAttribute("town",pt);

        //缓存村
/*        paramsMap.put("admLevel", String.valueOf(AdminLevelType.VILLAGE.getKey()));
        List<Map<String, Object>> village = administrativeDao.findAllByLevel(paramsMap);
        JSONArray pv = new JSONArray();
        pv.addAll(village);
        servletContext.setAttribute("village",pv);*/

        province.clear();
        city.clear();
        county.clear();
        town.clear();
        logger.info("----缓存行政区划成功----");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
