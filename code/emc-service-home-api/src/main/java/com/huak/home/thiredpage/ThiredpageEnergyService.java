package com.huak.home.thiredpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/8/2.
 */
public interface ThiredpageEnergyService {

    /**
     *三级页面-集团能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    Map<String, Object> getDatasAll(Map<String, Object> params) throws Exception;
    /**
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return map
     */
    Map<String, Object> getDatas(Map<String, Object> params) throws Exception;

    /**
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getassessment(Map<String, Object> paramsMap) throws Exception;

    /**
     *三级页面-表单
     * sunbinbin
     * @return map
     */
    Map<String, Object> getTable(Map<String, Object> params) throws Exception;

    /**
     *三级页面-用能单位类型-个能源能耗趋势图数据加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getUnitEnergyDetail(Map paramsMap);

    /**
     *三级页面-用能单位类型-个能源能耗排名加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getUnitAssessments(Map paramsMap);

    /**
     *三级页面-用能单位类型-个能源能耗趋势加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getUnitAllAssessment(Map paramsMap) throws Exception;


    /**
     *三级页面-用能单位类型-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    Map<String,Object> getThirdTables(Map paramsMap) throws Exception;

    /**
     *三级页面-分公司-能源类型数据加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getFgsEnergyTypes(Map paramsMap)  throws Exception;

    /**
     *三级页面-分公司-换热站排名趋势图
     * sunbinbin
     * @return Map
     */
    Map<String, Object> assessments(Map<String, Object> paramsMap) throws Exception;
}
