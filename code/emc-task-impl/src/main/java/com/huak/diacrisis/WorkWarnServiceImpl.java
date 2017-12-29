package com.huak.diacrisis;

import com.huak.base.dao.DateDao;
import com.huak.common.UUIDGenerator;
import com.huak.health.dao.AlarmRecordDao;
import com.huak.health.model.AlarmRecord;
import com.huak.health.vo.AlarmDataVo;
import com.huak.task.dao.DiacrisisDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.diacrisis<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/9/26<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Service
public class WorkWarnServiceImpl implements WorkWarnService {

    @Resource
    private DiacrisisDao diacrisisDao;
    @Resource
    private AlarmRecordDao alarmRecordDao;
    @Resource
    private DateDao dateDao;

    @Override
    public  void executeWarning() {
        List<AlarmDataVo> list =diacrisisDao.getWorkWarnInfo();

        for (int i = 0; i <list.size(); i++) {
            if(list.get(i).getAlarmType()==0){
                if(list.get(i).getCollectValue().equals(list.get(i).getNum())){
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record==null){
                    AlarmRecord alarmRecord = new AlarmRecord();
                    alarmRecord.setId(UUIDGenerator.getUUID());
                    alarmRecord.setAlarmNum(list.get(i).getCollectValue());
                    alarmRecord.setConfigId(list.get(i).getId());
                    alarmRecord.setStartTime(list.get(i).getCollectTime());
                    alarmRecordDao.insertSelective(alarmRecord);
                    }
                }else {
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record!=null){
                        AlarmRecord re = new AlarmRecord();
                        re.setId(record.getId());
                        re.setEndTime(dateDao.getTime());
                        alarmRecordDao.updateByPrimaryKeySelective(re);
                    }
                }
            }
            if(list.get(i).getAlarmType()==1){
                if(list.get(i).getModel()==0&&list.get(i).getCollectValue()<list.get(i).getNum()){
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record!=null){
                        AlarmRecord re = new AlarmRecord();
                        re.setId(record.getId());
                        re.setEndTime(dateDao.getTime());
                        alarmRecordDao.updateByPrimaryKeySelective(re);
                    }
                }else if(list.get(i).getModel()==1&&list.get(i).getCollectValue()<list.get(i).getNum()){
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record!=null){
                        AlarmRecord re = new AlarmRecord();
                        re.setId(record.getId());
                        re.setEndTime(dateDao.getTime());
                        alarmRecordDao.updateByPrimaryKeySelective(re);
                    }
                }else if(list.get(i).getModel()==2&&list.get(i).getCollectValue()>list.get(i).getNum()){
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record!=null){
                        AlarmRecord re = new AlarmRecord();
                        re.setId(record.getId());
                        re.setEndTime(dateDao.getTime());
                        alarmRecordDao.updateByPrimaryKeySelective(re);
                    }
                }else if(list.get(i).getModel()==3&&list.get(i).getCollectValue()>list.get(i).getNum()){
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record!=null){
                        AlarmRecord re = new AlarmRecord();
                        re.setId(record.getId());
                        re.setEndTime(dateDao.getTime());
                        alarmRecordDao.updateByPrimaryKeySelective(re);
                    }
                }else {
                    AlarmRecord record =  alarmRecordDao.selectByConfigId(list.get(i).getId());
                    if(record==null){
                        AlarmRecord alarmRecord = new AlarmRecord();
                        alarmRecord.setId(UUIDGenerator.getUUID());
                        alarmRecord.setAlarmNum(list.get(i).getCollectValue());
                        alarmRecord.setConfigId(list.get(i).getId());
                        alarmRecord.setStartTime(list.get(i).getCollectTime());
                        alarmRecordDao.insertSelective(alarmRecord);
                    }
                }
            }
       }
    }
}
