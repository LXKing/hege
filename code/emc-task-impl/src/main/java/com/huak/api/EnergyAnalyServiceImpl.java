package com.huak.api;

import com.huak.org.dao.CompanyDao;
import com.huak.org.model.Company;
import com.huak.task.dao.EmcOrgInterDao;
import com.huak.task.dao.EnergyAnalySisdataDao;
import com.huak.task.model.EmcOrgInter;
import com.huak.task.model.EnergyAnalySisdata;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/7/13<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class EnergyAnalyServiceImpl implements EnergyAnalyService {

    @Resource
    EnergyAnalySisdataDao energyAnalySisdataDao;
    @Resource
    EmcOrgInterDao emcOrgInterDao;
    @Resource
    private CompanyDao companyDao;

    @Override
    public boolean selectEnergyAnalySisdata(String unitid) {
        boolean flag=false;
        EmcOrgInter eoi= new EmcOrgInter();
        eoi.setOrgId(unitid);
        List<EmcOrgInter> list = emcOrgInterDao.selectAllByMap(eoi);
        if(list.size()>0){
            flag=true;
        }
        return flag;
    }

    @Override
    public void insetEnergyAnalySisdata(EnergyAnalySisdata record) {
        energyAnalySisdataDao.insertSelective(record);
    }

    @Override
    public void selectInsertIntoFinalDataHourById(Map<String,Object> map) {
        energyAnalySisdataDao.selectFinalDataHourById(map);
    }

    @Override
    public void selectHeatInsertFinalDataById(Map<String,Object> map) {
        energyAnalySisdataDao.selectHeatById(map);
    }

    @Override
    public void selectPowerInsertFinalDataById(Map<String,Object> map) {
        energyAnalySisdataDao.selectPowerById(map);
    }
    @Override
    public void selectQiInsertFinalDataById(Map<String,Object> map) {
        energyAnalySisdataDao.selectQiById(map);
    }

    @Override
    public String selectCoal(Map<String,Object> map) {
        return energyAnalySisdataDao.selectCoal(map);
    }

    /**
     * 查询公司
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Company selectCompanyByKey(String id) {
        return companyDao.selectByPrimaryKey(id);
    }
}