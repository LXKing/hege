package com.huak.prst;

import com.github.pagehelper.PageHelper;
import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.common.page.Convert;
import com.huak.common.page.Page;
import com.huak.common.page.PageResult;
import com.huak.mdc.EnergyDataHisService;
import com.huak.mdc.dao.MeterCollectDao;
import com.huak.mdc.dao.RecordPrestoreDao;
import com.huak.mdc.model.EnergyDataHis;
import com.huak.mdc.model.MeterCollect;
import com.huak.mdc.model.RecordPrestore;
import com.huak.mdc.vo.PrestoreA;
import com.huak.org.dao.CompanyDao;
import com.huak.org.model.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.prst<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/31<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */

@Service
public class PrestoreServiceImpl implements PrestoreService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private RecordPrestoreDao prestoreDao;
    @Resource
    private DateDao dateDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private MeterCollectDao meterCollectDao;
    @Resource
    private EnergyDataHisService energyDataHisService;


    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    /**
     * 计量器具-预存
     * @param record
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int insert(RecordPrestore record) throws  Exception {
            int flag = 0 ;
            String uuid = UUIDGenerator.getUUID();
            record.setId(uuid);
            String date = dateDao.getTime();
            record.setCreateTime(date);
            MeterCollect meterCollect = meterCollectDao.selectByPrimaryKey(record.getCollectId());
            Company company = companyDao.selectByPrimaryKey(meterCollect.getComId());
            flag = prestoreDao.insertSelective(record);
            if(flag >0){
                List<EnergyDataHis> datalist = new ArrayList<>();
                EnergyDataHis data = new EnergyDataHis();
                data.setCollectId(record.getCollectId());
                data.setCollectTime(record.getPrestoreTime());
                data.setIsprestore((byte) 1);
                data.setIschange((byte) 0);
                data.setPrestoreNum(record.getPrestoreNum());
                data.setCollectNum(record.getUsedNum());
                datalist.add(data);
                try {
                   energyDataHisService.saveEnergyDatas(datalist, company,null);

                }catch (Exception e){
                  prestoreDao.deleteByPrimaryKey(uuid);
                  logger.error("预存能耗计算异常："+ e);
                }
    }
        return flag;
    }

    @Override
    public int insertSelective(RecordPrestore record) {
        return 0;
    }

    @Override
    public RecordPrestore selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(RecordPrestore record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(RecordPrestore record) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public PageResult<PrestoreA> queryByPage(Map<String, Object> paramsMap,Page page) {

        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        return Convert.convert(prestoreDao.selectPageByMap(paramsMap));
    }
    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> exporPrestore(Map<String, Object> paramsMap) {
        return prestoreDao.selectAllByMap(paramsMap);
    }
}
