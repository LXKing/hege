package com.huak.home.dao.thiredpage;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by MR-BIN on 2017/8/2.
 */
@Repository
public interface ThiredpageEnergyDao {
    /**
     *三级页面-集团的各个能源类型的能耗趋势图
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getDatasAll(Map<String, Object> params);

    /**
     *三级页面-源、网、站、线、户的各个能源类型的能耗趋势图
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getDatas(Map<String, Object> params);

    /**
     *三级页面-换热站排名趋势图
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> selectassessment(Map<String, Object> paramsMap);

    /**
     *三级页面-表单数据
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getTables(Map<String, Object> params);
    /**
     *三级页面-表单数据-用能单位7天的总量
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getTotal(Map<String, Object> params);

    /**
     * 获取当前查询条件的所有用能单位
     * @param paramsMap
     * @return
     */
    List<Map<String,Object>> getUnitsEnergyDetail(Map paramsMap);

    /**
     *三级页面-用能单位类型-各个能源类型用量趋势图加载
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getUnitAllAssessment(Map params);

    /**
     *三级页面-用能单位类型-各个能源类型用量排名加载
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getUnitsAssessment(Map paramsMap);
    /**
     *三级页面-用能单位类型-各个能源类型同期计划加载
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getThirdTables(Map paramsMap);

    /**
     *三级页面-用能单位类型-各个能源类型同期计划总值加载
     * sunbinbin
     * @return Map
     */
    List<Map<String,Object>> getThirdTotals(Map paramsMap);

    /**
     *三级页面-分公司-各个能源类型加载
     * sunbinbin
     * @return Map
     */
    List<Map<String,Object>> getFgsDatas(Map params);
}
