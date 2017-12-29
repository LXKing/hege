package com.huak.base;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.base<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/5/19<BR>
 * Description:     <BR>
 * Function List:  <BR>
 */
public interface ReversibleEncryption {
    /**
     * 解密
     * @return
     */
    public String decode(String data) throws Exception;


    /**
     * 加密
     * @return
     */
    public String encode(String data) throws Exception;
}
