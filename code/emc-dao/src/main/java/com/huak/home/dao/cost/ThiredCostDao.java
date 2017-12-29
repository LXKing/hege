package com.huak.home.dao.cost;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/12/7.
 */
@Repository
public interface ThiredCostDao {

    /**
     *三级页面-集团的各个成本汇总趋势图
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> getCostAll(Map<String,String> params);

    /**
     *三级页面-分公司-各个成本类型加载
     * sunbinbin
     * @return Map
     */
    List<Map<String,Object>> getCostDatas(Map<String,String> params);

    /**
     *子公司的各个成本趋势图
     * */
    List<Map<String, Object>>   ThirdSubCostDetailed(Map<String, Object> params);

    /**
    * 各个分公司的换热站成本排名 ，包含管理费，其他费用，人工费，设备费用
    * **/

    List<Map<String,Object>> StationRanking(Map<String, Object> paramsMap);



    /**
    * 各个分公司的换热站成本排名 ，包含能源费
    * **/

    List<Map<String,Object>> StationRankingEnergy(Map<String, Object> paramsMap);

    /**
     *三级页面-各站点成本-同期计划数据表格加载
     * sunbinbin
     * @return List
     */
    List<Map<String,Object>> levelCodeTable(Map<String, Object> paramsMap);
    /**
     *三级页面-分公司-能源成本的趋势图
     * sunbinbin
     * @return strin
     */
    List< Map<String,Object>> thirdSubCompanyEnergyDetail(Map<String, Object> paramsMap) throws Exception;

}
