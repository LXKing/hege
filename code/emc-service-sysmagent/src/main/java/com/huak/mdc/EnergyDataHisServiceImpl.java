package com.huak.mdc;

import com.huak.common.StringUtils;
import com.huak.common.UUIDGenerator;
import com.huak.common.utils.DateUtils;
import com.huak.common.utils.DoubleUtils;
import com.huak.mdc.dao.EnergyDataHisDao;
import com.huak.mdc.dao.FinalDataHourDao;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.FinalDataHour;
import com.huak.mdc.model.MeterCollect;
import com.huak.org.model.Company;
import com.huak.sys.SysWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 能耗历史    <BR>
 * Function List:  <BR>
 */
@Service
public class EnergyDataHisServiceImpl implements EnergyDataHisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private EnergyDataHisDao energyDataHisDao;
    @Resource
    private MeterCollectDao meterCollectDao;
    @Resource
    private FinalDataHourDao finalDataHourDao;
    //标煤系数
    @Resource
    private CoalCoefService coalCoefService;
    //碳排放
    @Resource
    private CarbonFormulaService carbonFormulaService;
    //能源单价
    @Resource
    private EnergyPriceService energyPriceService;
    //单位面积
    @Resource
    private UnitAreaService unitAreaService;
    //天气
    @Resource
    private SysWeatherService sysWeatherService;


    /**
     * 保存能耗数据
     * 流程：
     * 1.判断本期，本期存在，则修改，否则添加
     * 2.如果后期历史存在，则修改本期到后期时间段的能耗数据
     * 3.如果前期历史存在，则修改本期到前期时间段的能耗数据
     * 修改数据流程：
     * 1.计算两期数据时间差中的每小时
     * 2.两期数据表底差值/时间差小时个数 = 每小时能耗
     * 3.查询标煤系数
     * 4.查询能源单价
     * 5.查询单位面积
     * 6.查询碳排放公式
     * 7.查询温度
     * 8.计算折算温度
     * 9.查询室温
     * 10.计算折算室温
     * 11.组装FinalDataHour保存或修改
     *
     * @param energyDataHis
     * @param company
     */
    private boolean saveEnergyData(EnergyDataHis energyDataHis, Company company) throws Exception {

        // 查询前期历史
        EnergyDataHis qqData = energyDataHisDao.findQqHis(energyDataHis);
        // 查询后期历史
        EnergyDataHis hqData = energyDataHisDao.findHqHis(energyDataHis);

        // 如果后期历史存在，则修改本期到后期时间段的能耗数据
        if (null != hqData && null != hqData.getId()) {
            boolean isSave = saveDataHour(energyDataHis, hqData, company);
            if (!isSave) {
                return false;
            }
        }

        // 如果前期历史存在，则修改本期到前期时间段的能耗数据
        if (null != qqData && null != qqData.getId()) {
            boolean isSave = saveDataHour(energyDataHis, qqData, company);
            if (!isSave) {
                return false;
            }
        }



        return true;

    }
    private boolean saveEnergyHisData(EnergyDataHis energyDataHis) throws Exception {

        // 查询本期历史
        EnergyDataHis bqData = energyDataHisDao.findBqHis(energyDataHis);
        // 如果本期存在，则修改，否则添加
        int addOrUpdate = 0;
        if (null != bqData && null != bqData.getId()) {
            energyDataHis.setId(bqData.getId());
            addOrUpdate = energyDataHisDao.updateByPrimaryKeySelective(energyDataHis);
        } else {
            energyDataHis.setId(UUIDGenerator.getUUID());
            addOrUpdate = energyDataHisDao.insertSelective(energyDataHis);
        }
        if (addOrUpdate <= 0) {
            return false;
        }
        return true;

    }

    /**
     * 批量保存能耗数据
     *
     * @param dataHisList 能耗数据集合
     * @param company     公司
     * @param meterCollectList 虚表集合 （可以为null）
     */
    @Override
    @Transactional(readOnly = false)
    public boolean saveEnergyDatas(List<EnergyDataHis> dataHisList, Company company, final List<MeterCollect> meterCollectList) {
        try {
            // 校验数据是否同期
            String time = "";
            for (int i = 0; i < dataHisList.size(); i++) {
                EnergyDataHis energyDataHis = dataHisList.get(i);
                if (i == 0) {
                    time = energyDataHis.getCollectTime();
                    continue;//第一次赋值
                }
                if (!time.equals(energyDataHis.getCollectTime())) {
                    throw new UniformityException("批量数据不是同期数据 " + energyDataHis.toString() + " time:{" + time + "}");
                }
            }
            // 先保存实表填报数据历史
            for (EnergyDataHis energyDataHis : dataHisList) {
                //保存能耗数据
                boolean isSuccess = saveEnergyHisData(energyDataHis);
                //校验是否成功，保持数据完整性
                if (!isSuccess) {
                    throw new UniformityException("批量数据保存部分异常 " + energyDataHis.toString());
                }else{
                    // 多线程保存能耗数据和虚表数据
                    final List<EnergyDataHis> finalDataList = dataHisList;
                    final Company gs = company;
                    final String dateTime = time;
                    new Thread() {
                        @Override
                        public void run() {
                            logger.info("---------填报数据转存能耗数据----------");
                            for (EnergyDataHis dataHis : finalDataList) {
                                //保存能耗数据
                                try {
                                    saveEnergyData(dataHis, gs);
                                } catch (Exception e) {
                                    logger.error("填报数据转存能耗数据异常：[" + dateTime + "]" + e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            //保存虚表
                            if(null!=meterCollectList && meterCollectList.size()>0){
                                saveVirtualDatas(meterCollectList,dateTime,gs);
                            }
                        }
                    }.start();

                }
            }


        } catch (Exception e) {
            logger.error("批量保存能耗数据失败:" + e.getMessage());
            e.printStackTrace();
        }
        return true;

    }

    /**
     * 批量保存虚表能耗数据
     *
     * @param meterCollectList
     * @param dateTime
     * @param company
     */
    private boolean saveVirtualDatas(List<MeterCollect> meterCollectList, String dateTime, Company company) {
        try {
            for (MeterCollect meterCollect : meterCollectList) {
                //保存虚表能耗数据
                boolean isSuccess = saveVirtualData(meterCollect, dateTime, company);
                //校验是否成功，保持虚表数据完整性
                if (!isSuccess) {
                    throw new UniformityException("批量数据保存部分异常 " + meterCollect.toString());
                }
            }
        } catch (Exception e) {
            logger.error("批量保存虚表能耗数据失败:" + e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 每期数据都是必填
     * 1.根据dateTime查询前期数据
     *
     * @param meterCollect
     * @param dateTime
     * @param company
     * @return
     */
    private boolean saveVirtualData(MeterCollect meterCollect, String dateTime, Company company) throws Exception {
        //获取公式
        String formula = meterCollect.getFormula();
        //获取第一个code
        List<String> codes = StringUtils.paresCodes(formula);
        if (codes.size() == 0) {
            return true;//没有公式不计算
        }
        String code = codes.get(0);
        //根据第一个code获取时间区间
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("comId", company.getId());
        MeterCollect collect = meterCollectDao.checkCode(params).get(0);
        EnergyDataHis energyDataHis = new EnergyDataHis();
        energyDataHis.setCollectId(collect.getId());
        energyDataHis.setCollectTime(dateTime);
        // 查询本期历史
        EnergyDataHis bqData = energyDataHisDao.findBqHis(energyDataHis);
        // 查询前期历史
        EnergyDataHis qqData = energyDataHisDao.findQqHis(energyDataHis);
        // 查询后期历史
        EnergyDataHis hqData = energyDataHisDao.findHqHis(energyDataHis);

        String start = "";
        String end = "";
        if (qqData != null && hqData != null) {
            start = qqData.getCollectTime();
            end = hqData.getCollectTime();
        } else if (qqData != null) {
            start = qqData.getCollectTime();
            end = bqData.getCollectTime();
        } else if (hqData != null) {
            start = bqData.getCollectTime();
            end = hqData.getCollectTime();
        } else {
            return true;//前期后期数据不存在  不用计算能耗
        }
        //时间段每小时集合
        List<String> dateTimes = DateUtils.getDateTimes(start, end);

        return saveVirtualDataHour(meterCollect, dateTimes, codes, company);
    }


    /**
     * 时间段能耗更新
     *
     * @return
     */
    private boolean saveDataHour(EnergyDataHis energyDataHis, EnergyDataHis data, Company company) throws Exception {
        String start = "";
        String end = "";
        if (energyDataHis.getCollectTime().compareTo(data.getCollectTime()) > 0) {
            start = data.getCollectTime();
            end = energyDataHis.getCollectTime();
        } else {
            end = data.getCollectTime();
            start = energyDataHis.getCollectTime();
        }
        //时间段每小时集合
        List<String> dateTimes = DateUtils.getDateTimes(start, end);

        //查询计量采集表
        MeterCollect meterCollect = meterCollectDao.selectByPrimaryKey(energyDataHis.getCollectId());

        // 判断特殊的换表和预存
        Double dosage;
        if (energyDataHis.getCollectTime().compareTo(data.getCollectTime()) > 0) {//判断前期是否换表预存
            Double dosageTotal;
            if (1 == data.getIschange() && 1 == data.getIsprestore()) {//换表且预存，新表表底+预存值
                Double num = DoubleUtils.add(data.getChangeNum(), data.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(energyDataHis.getCollectNum(), num);
            } else if (1 == data.getIschange() && 0 == data.getIsprestore()) {//只换表，新表表底
                dosageTotal = DoubleUtils.sub(energyDataHis.getCollectNum(), data.getChangeNum());
            } else if (0 == data.getIschange() && 1 == data.getIsprestore()) {//只预存，旧表表底+预存值
                Double num = DoubleUtils.add(data.getCollectNum(), data.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(energyDataHis.getCollectNum(), num);
            } else {
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), energyDataHis.getCollectNum());
            }
            dosageTotal = Math.abs(dosageTotal);//绝对值，忽略预存负值
            dosage = DoubleUtils.div(dosageTotal, dateTimes.size(), 2);//每小时平均能耗
        } else {//判断本期是否换表预存
            Double dosageTotal;
            if (1 == energyDataHis.getIschange() && 1 == energyDataHis.getIsprestore()) {//换表且预存，新表表底+预存值
                Double num = DoubleUtils.add(energyDataHis.getChangeNum(), energyDataHis.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), num);
            } else if (1 == energyDataHis.getIschange() && 0 == energyDataHis.getIsprestore()) {//只换表，新表表底
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), energyDataHis.getChangeNum());
            } else if (0 == energyDataHis.getIschange() && 1 == energyDataHis.getIsprestore()) {//只预存，旧表表底+预存值
                Double num = DoubleUtils.add(energyDataHis.getCollectNum(), energyDataHis.getPrestoreNum());
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), num);
            } else {
                dosageTotal = DoubleUtils.sub(data.getCollectNum(), energyDataHis.getCollectNum());
            }
            dosageTotal = Math.abs(dosageTotal);//绝对值，忽略预存负值
            dosage = DoubleUtils.div(dosageTotal, dateTimes.size(), 2);//每小时平均能耗
        }

        //time %Y-%m-%d %H:00:00
        for (String time : dateTimes) {
            //查询该时间的标煤系数
            Double coalCoef = coalCoefService.getCoalCoefByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的碳排放系数
            Double carbonFormula = carbonFormulaService.getCarbonFormulaByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的单位面积
            Double unitArea = unitAreaService.getUnitAreaByTime(company.getId(), meterCollect.getUnitId(), meterCollect.getUnitType(), time);
            //查询该时间的能源单价
            BigDecimal energyPrice = energyPriceService.getEnergyPriceByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的天气温度
            Double weather = sysWeatherService.getWeatherByTime(company.getWcode(), time);
            //todo 计算折算天气温度
            //todo 查询该时间段的室内温度
            Double cWeather = weather;
            //todo 计算折算室内温度
            FinalDataHour dataHour = new FinalDataHour();
            String id = UUIDGenerator.getUUID();
            dataHour.setId(id);//自动生成id
            dataHour.setTableName(company.getTableName());//动态表
            //dataHour.setTableName("t_emc_final_data_hour");
            dataHour.setNodeid(energyDataHis.getCollectId());
            dataHour.setComid(company.getId());
            dataHour.setUnitid(meterCollect.getUnitId());
            dataHour.setTypeid(meterCollect.getEnergyTypeId());
            dataHour.setDosageTime(time);
            dataHour.setDosage(dosage);
            dataHour.setArea(unitArea);
            dataHour.setPrice(energyPrice);
            dataHour.setWtemp(weather);
            dataHour.setCwtemp(cWeather);
            dataHour.setCoalCoef(coalCoef);
            dataHour.setcCoef(carbonFormula);
            int i = finalDataHourDao.insertOrUpdate(dataHour);
            if (1 != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 虚表时间段能耗更新
     *
     * @return
     */
    private boolean saveVirtualDataHour(MeterCollect meterCollect, List<String> dateTimes, List<String> codes, Company company) throws Exception {
        //time %Y-%m-%d %H:00:00
        for (String time : dateTimes) {
            //查询该时间的标煤系数
            Double coalCoef = coalCoefService.getCoalCoefByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的碳排放系数
            Double carbonFormula = carbonFormulaService.getCarbonFormulaByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的单位面积
            Double unitArea = unitAreaService.getUnitAreaByTime(company.getId(), meterCollect.getUnitId(), meterCollect.getUnitType(), time);
            //查询该时间的能源单价
            BigDecimal energyPrice = energyPriceService.getEnergyPriceByTime(company.getId(), meterCollect.getEnergyTypeId(), time);
            //查询该时间的天气温度
            Double weather = sysWeatherService.getWeatherByTime(company.getWcode(), time);
            //todo 计算折算天气温度
            //todo 查询该时间段的室内温度
            Double cWeather = weather;
            //todo 计算折算室内温度

            //根据公式计算能耗
            Map<String, Object> codeValue = getCodeValues(codes, company, time);
            Double dosage = getVirtualDosage(codeValue, meterCollect.getFormula(), codes);

            FinalDataHour dataHour = new FinalDataHour();
            String id = UUIDGenerator.getUUID();
            dataHour.setId(id);//自动生成id
            dataHour.setTableName(company.getTableName());//动态表
            //dataHour.setTableName("t_emc_final_data_hour");
            dataHour.setNodeid(meterCollect.getId());
            dataHour.setComid(company.getId());
            dataHour.setUnitid(meterCollect.getUnitId());
            dataHour.setTypeid(meterCollect.getEnergyTypeId());
            dataHour.setDosageTime(time);
            dataHour.setDosage(dosage);
            dataHour.setArea(unitArea);
            dataHour.setPrice(energyPrice);
            dataHour.setWtemp(weather);
            dataHour.setCwtemp(cWeather);
            dataHour.setCoalCoef(coalCoef);
            dataHour.setcCoef(carbonFormula);
            int i = finalDataHourDao.insertOrUpdate(dataHour);
            if (1 != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据公式返回值
     *
     * @param codeValue
     * @param formula
     * @param codes
     * @return
     * @throws javax.script.ScriptException
     */
    private Double getVirtualDosage(Map<String, Object> codeValue, String formula, List<String> codes) throws ScriptException {
        for (String code : codes) {
            formula = formula.replaceAll(code, codeValue.get(code).toString());
        }
        formula = formula.replaceAll("/+", "+").replaceAll("-", "-").replaceAll("×", "*").replaceAll("÷", "/");
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        Double num = Double.valueOf(jse.eval(formula).toString());
        DecimalFormat df = new DecimalFormat("######0.000");

        return Double.valueOf(df.format(num));
    }

    /**
     * 根据编码和公司及时间取用量
     *
     * @param codes
     * @param company
     * @param time
     * @return
     */
    private Map<String, Object> getCodeValues(List<String> codes, Company company, String time) {
        Map<String, Object> codeValues = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("comId", company.getId());
        params.put("codes", codes);
        params.put("time", time);
        params.put("tableName",company.getTableName());
        //params.put("tableName", "t_emc_final_data_hour");
        List<Map<String, Object>> list = finalDataHourDao.selectCodeValue(params);
        for (Map<String, Object> map : list) {
            codeValues.put(map.get("CODE").toString(), map.get("DOSAGE"));
        }
        return codeValues;
    }

    @Override
    public List<MeterCollect> selectFictitiousMeters(List<String> ids) {
        List<MeterCollect> meterCollects = meterCollectDao.selectFictitiousMeters(ids);
        return meterCollects;
    }

    @Override
    public List<MeterCollect> selectFictitiousMetersByCode(Map<String, Object> params) {
        return meterCollectDao.selectFictitiousMetersByCode(params);
    }
}
