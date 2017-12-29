package com.huak.home.cost;

import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/12/7.
 */

public interface ThiredPageCostService {

    /**
     *三级页面-集团的各个成本汇总趋势图
     * sunbinbin
     * @return List
     */
    Map<String,Object> getCostAll(Map<String,String> params) throws Exception;
    /**
     *三级页面-分公司-各个成本类型加载
     * sunbinbin
     * @return Map
     */
    Map<String,Object> getCostDatas(Map<String,String> params) throws Exception;

    /**
     *子公司的各个成本趋势图
     * */
    Map<String, Object> ThirdSubCostDetailed(Map<String, Object> params) throws Exception;

    /**
    * 各个分公司的换热站成本排名 ，包含能源费，管理费，其他费用，人工费，设备费用
    * **/

    Map<String,Object> StationRanking(Map<String, Object> paramsMap)throws Exception;

    /**
     *三级页面-各站点成本-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    Map<String,Object> levelCodeTable(Map<String, Object> paramsMap);

    /**
     *三级页面-分公司-能源成本的趋势图
     * sunbinbin
     * @return strin
     */
    Map<String,Object> thirdSubCompanyEnergyDetail(Map<String, Object> paramsMap) throws Exception;


}
