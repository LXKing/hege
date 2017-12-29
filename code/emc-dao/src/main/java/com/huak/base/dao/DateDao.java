package com.huak.base.dao;

import org.springframework.stereotype.Repository;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.base<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/10<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
@Repository
public interface DateDao {

    public String getDate();


    public String getTime();
}
