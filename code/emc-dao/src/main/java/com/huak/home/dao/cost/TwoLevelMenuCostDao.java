package com.huak.home.dao.cost;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
@Repository
public interface TwoLevelMenuCostDao {


    /**
     * 分公司成本趋势对比
     * */
    List<Map<String,Object>> BranchCostTrend(Map<String, Object> params);

    /**
     * 分公司成本占比图
     * */

    List<Map<String,Object>> BranchCostProportion(Map<String, Object> params);


    /** EnergyFlowDetail
     * 集团总成本数据线性图  groubCost
     * */

    List<Map<String,Object>> groubCost(Map<String, Object> params);


    /**
     * 能源流明细
     * */

    List<Map<String,Object>> EnergyFlowDetail(Map<String, Object> params);



    /**groubCostLastAn
     * 集团总成本数据线性图  上一个月
     * */

    List<Map<String,Object>> groubCostLastAn(Map<String, Object> params);


    /**groubCostLastCorrespondingPeriod
     * 集团总成本数据线性图  同比 当前月
     * */

    List<Map<String,Object>> groubCostAn(Map<String, Object> params);


    /**groubCostLastCorrespondingPeriod
     * 集团总成本数据线性图  去年同期
     * */

    List<Map<String,Object>> groubCostLastCorrespondingPeriod(Map<String, Object> params);


    /**
     * 集团总成本数据线性图  去年 groubCost
     * */

    List<Map<String,Object>> groubCostqn(Map<String, Object> params);

    /*
  * 分公司成本排名
  * **/
    List<Map<String,Object>> BranchEnergyCons(Map<String, Object> params);

    /*
 * 分公司去年成本排名
 * **/
    List<Map<String,Object>> BranchEnergyConsqn(Map<String, Object> params);
    /**
     * 能源流成本占比
     * */

    List<Map<String,Object>> TwoEnergyFlowPie(Map<String, Object> params);


    /**
     * 能源流成本同比
     * */

    List<Map<String,Object>> TwoEnergyFlowPieAn(Map<String, Object> params);


    /**
     * 能源流成本趋势对比分析TwoEnergyCostTrend
     * */

    List<Map<String,Object>> TwoEnergyCostTrend(Map<String, Object> params);
    /**
     *  今年能源成本
     * */

    List<Map<String,Object>> TwoEnergyCost(Map<String, Object> params);
    /**
     *  去年能源成本
     * */

    List<Map<String,Object>> TwoEnergyCostLastYear(Map<String, Object> params);
    /**
     * 今年设备成本
     * */
    List<Map<String,Object>> TwoDeviceCost(Map<String, Object> params);

    /**
     * 去年设备成本
     * */
    List<Map<String,Object>>  TwoDeviceCostLastYear(Map<String, Object> params);


    /**
     * 今年管理成本
     * */
    List<Map<String,Object>>  TwoManagerCost(Map<String, Object> params);

    /**
     * 去年管理成本
     * */
    List<Map<String,Object>>  TwoManagerCostLastYear(Map<String, Object> params);




    /**
     * 今年其他成本
     * */
    List<Map<String,Object>>  TwoOtherCost(Map<String, Object> params);

    /**
     * 去年其他成本
     * */
    List<Map<String,Object>>  TwoOtherCostLastYear(Map<String, Object> params);



    /**
     * 今年人工成本
     * */
    List<Map<String,Object>>  TwoLaborCost(Map<String, Object> params);

    /**
     * 去年人工成本
     * */
    List<Map<String,Object>>  TwoLaborCostLastYear(Map<String, Object> params);


    /**
     * 查询该分公司（包含下面所有子公司的）今年所有成本趋势图
     * */
    List<Map<String,Object>>  selectBmTqLine(Map<String, Object> params);

    /**
     * 查询该分公司（包含下面所有子公司的）今年所有成本趋势图
     * */
    List<Map<String,Object>>  selectBmBqLine(Map<String, Object> params);


    /**
     * 查询该分公司（包含下面所有子公司的）今年所有成本总和
     * */
    Double  selectBmBqTotal(Map<String, Object> params);


    /**
     * 查询该分公司（包含下面所有子公司的）去年所有成本总和
     * */
    Double  selectBmTqTotal(Map<String, Object> params);

    /**
     * 各个子成本今年成本趋势图
     * */
    List<Map<String, Object>>selectSubcostLine(Map<String, Object> params);
    /**
     * 各个子成本去年成本趋势图
     * */
    List<Map<String, Object>>selectSubcostLineLastYear(Map<String, Object> params);
    /**
     * 今年各个子成本的各自总成本
     * */
    Map<String, Object> selectSubcostTotal(Map<String, Object> params);
    /**
     * 去年各个子成本的各自总成本
     * */
    Map<String, Object> selectSubcostTotalLastyear(Map<String, Object> params);

    /**
     *各个子成本今年能源趋势图

     * */
    List<Map<String, Object>> selectSubcostEnergyLine(Map<String, Object> params);

    /**
     *各个子成本去年能源趋势图
     * */
    List<Map<String, Object>> selectSubcostEnergyLineLastYear(Map<String, Object> params);


    /**
     * 今年能源总成本
     * */
    Double selectSubcostTotalEnergy(Map<String, Object> params);

    /**
     * 去年能源总成本
     * */
    Double selectSubcostTotalEnergyLastYear(Map<String, Object> params);

    /**
     * 分公司成本明细表
     * */
    List<Map<String, Object>> SubCostDetailed(Map<String, Object> params);

    /**
     * 分公司成本明细表   SubCostDetailedEnergyLastYear
     * */
    List<Map<String, Object>> SubCostDetailedEnergy(Map<String, Object> params);

    /**
     * 分公司成本明细表  去年的
     * */
    List<Map<String, Object>> SubCostDetailedEnergyLastYear(Map<String, Object> params);
    /**
     * 查询组织机构
     * */
    Map<String, Object> selectOrg(Map<String, Object> params);


    /*
    * 集团能源费用
    * **/

    List<Map<String,Object>> groupEnergyCost(Map<String, Object> paramsMap);

       /*
    * 集团 其他费用费用
    * **/

    List<Map<String,Object>> groupCost(Map<String, Object> paramsMap);



    /*
    * 集团去年能源费用
    * **/

    List<Map<String,Object>> groupEnergyCostLastYear(Map<String, Object> paramsMap);

       /*
    * 集团 其他费用费用
    * **/

    List<Map<String,Object>> groupCostLastYear(Map<String, Object> paramsMap);


         /*
    * 集团 组织机构
    * **/

    List<Map<String,Object>> groupOrg(Map<String, Object> paramsMap);

             /*
    * 分公司 组织机构
    * **/

    List<Map<String,Object>> subOrg(Map<String, Object> paramsMap);
    /**
     * 分公司费用占比分布图里面的管理费用+设备费用+其他费用+人工费用
     *
     * */
    List<Map<String,Object>> BranchCostProportionCost(Map<String, Object> paramsMap);

    /**
     * 分公司费用占比分布图里面的能源费用
     *
     * */
    List<Map<String,Object>> BranchCostProportionCostEnergy(Map<String, Object> paramsMap);


    /**
     * 分公司费用占比分布图里面的管理费用+设备费用+其他费用+人工费用
     *
     * */
    List<Map<String,Object>> BranchCostProportionCostTq(Map<String, Object> paramsMap);

    /**
     * 分公司费用占比分布图里面的能源费用
     *
     * */
    List<Map<String,Object>> BranchCostProportionCostEnergyTq(Map<String, Object> paramsMap);

    /**
     * 二级菜单能源流成本趋势对比图
     *
     * */
    List<Map<String,Object>> twolEvelEnergyFlowLine(Map<String, String> paramsMap);


    /**
     * 二级菜单分公司成本趋势对比图
     *
     * */
    List<Map<String,Object>> twolEvelSubFlowLine(Map<String, String> paramsMap);

    /**
     * 二级菜单能源组织fgsEnergyAn
     *
     * */
    List<Map<String,Object>> selectSubcostEnergyOrg(Map<String, Object> paramsMap);

    /**
     * 分公司成本同比
     *
     * */
    List<Map<String,Object>> subEnergyAn(Map<String, Object> paramsMap);
}
