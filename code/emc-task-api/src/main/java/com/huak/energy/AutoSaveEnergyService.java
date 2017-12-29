package com.huak.energy;

import com.alibaba.fastjson.JSONObject;


import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.energy<BR>
 * Author:  Administrator  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017-11-16<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface AutoSaveEnergyService {
    /*JSONObject selectDatas(Company company);*/

    List<String> selectTags(Map<String, Object> paramsMap);

    void updateStates(Map<String, Object> params);
}
