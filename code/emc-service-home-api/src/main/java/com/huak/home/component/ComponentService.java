package com.huak.home.component;

import java.util.Map;

/**
 * Created by MR-BIN on 2017/6/8.
 */
public interface ComponentService {

    /**
     * 能耗明细查询
     */
    Map<String,Object> energyDetail(Map<String,Object> params);

    /**
     * 成本明细
     * @param params
     * @return
     */
    Map<String,Object> costDetail(Map<String, Object> params);

    /**
     * 单耗趋势
     * @param params
     * @return
     */
    Map<String,Object> energycomparison(Map<String, Object> params);

    /**
     * 天气预测和历史
     * @param params
     * @return
     */
    Map<String,Object> weatherForcast(Map<String, Object> params);

    /**
     * 室温散点图数据查询
     * @param params
     * @return
     */
    Map<String,Object> roomTemperature(Map<String, Object> params);

    /**
     * 近期单耗详情
     * @param paramsMap
     * @return
     */
    java.util.List<Map<String, Object>> selectrecentDetail(Map<String, Object> paramsMap);

    /**
     * 健康指数检测
     * @param paramsMap
     * @return
     */
    Map<String,Object> healthcheck(Map<String, Object> paramsMap);

    /**
     * 报警-单耗接口-实现
     * @param params 说明：
     * {
     *     startTime：#开始时间 格式：yyyy-MM-dd hh:mm:ss,
     *     endTime：#结束时间 格式：yyyy-MM-dd hh:mm:ss,
     *     comId：#当前登录公司id
     * }
     * @return Map 说明:
     * {
     *   message:{flag：true/false,message:描述}
     *   data：{
     *     level1: #轻度,
     *     level2：#中度,
     *     level3：#重度,
     *     total:  #检测总数
     *   }
     * }
     */
    Map<String,Object> getAlarms(Map<String, Object> params);
    /**
     * 报警-工况-实现
     * @param params 说明：
     * {
     *     startTime：#开始时间 格式：yyyy-MM-dd hh:mm:ss,
     *     endTime：#结束时间 格式：yyyy-MM-dd hh:mm:ss,
     *     comId：#当前登录公司id
     * }
     * @return Map 说明:
     * {
     *   message:{flag：true/false,message:描述}
     *   data：{
     *     level1: 1级,
     *     level2：2级,
     *     level3：3级,
     *     level4：4级
     *     total:  总数
     *   }
     * }
     */
    Map<String,Object> getWorkAlarms(Map<String, Object> params);

    Map<String,Object> getTempAlarms(Map<String, Object> params);

    /**
     * 报警详情-单耗接口-实现
     * @param params 说明：
     * {
     *     startTime：#开始时间 格式：yyyy-MM-dd hh:mm:ss,
     *     endTime：#结束时间 格式：yyyy-MM-dd hh:mm:ss,
     *     comId：#当前登录公司id
     * }
     * @return Map 说明:
     * {
     *   message:{flag：true/false,message:#描述}
     *   data：{
     *     enterprise: #企业指标,
     *     industry：#行业指标,
     *     local：#地方指标,
     *     num: #平均单耗
     *     type：#指标类型,
     *     unitname：#用能单位
     *   }
     * }
     */
    Map<String,Object> getAlarmsDetail(Map<String,Object> params);

    Map<String,Object> getWorkAlarmsDetail(Map<String, Object> params);

    Map<String,Object> getTempAlarmsDetail(Map<String, Object> params);
}
