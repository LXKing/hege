package com.huak.mdc.dao;

import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.vo.MeterCollectA;
import com.huak.mdc.vo.MeterCollectDataA;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MeterCollectDao {
    int deleteByPrimaryKey(String id);

    int insert(MeterCollect record);

    int insertSelective(MeterCollect record);

    MeterCollect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeterCollect record);

    int updateByPrimaryKey(MeterCollect record);

    List<MeterCollectA> selectPageByMap(Map<String,Object> paramsMap);

    List<Map<String,Object>> selectAllByMap(Map<String, Object> paramsMap);

    List<MeterCollect>  checkName(Map<String,Object> paramsMap);

    List<MeterCollect>  checkCode(Map<String,Object> paramsMap);

    List<MeterCollect>  checkSerialNo(String serialNo);

    List<Map<String,Object>> getUnitInfo(Map<String,Object> paramsMap);

    List<Map<String,Object>> selectByMaps(Map<String, Object> paramsMap);

    /**
     * 填报数据表查询
     * @param params
     * @return
     */
    List<Map<String, Object>>  getMeterDatas(Map<String, Object> params);

    /**
     * 填报数据表查询
     * @param params
     * @return
     */
    List<MeterCollectDataA>  getDataLoad(Map<String, Object> params);

    /**
     * 查询虚表
     * @param
     * @return
     */
    List<MeterCollect> selectFictitiousMeters(List<String> ids);

    public String getGeneralCode(String comId);

    public Integer getFormulaByIsReal(String formula);

    List<MeterCollect> selectFictitiousMetersByCode(Map<String, Object> params);


    List<Map<String,Object>> selectTags();

    List<MeterCollect> selectAutoMeters(Map<String, Object> paramsMap);
}