package com.huak.mdc;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/29<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface UnitAreaService {
    /**
     *
     * @param id 公司主键
     * @param unitId 用能单位主键
     * @param unitType 用能单位类型
     * @param time %Y-%m-%d %H:00:00
     * @return
     */
    Double getUnitAreaByTime(String id, String unitId, Byte unitType, String time);
}
