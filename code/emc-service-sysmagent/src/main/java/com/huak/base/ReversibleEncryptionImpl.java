package com.huak.base;

import com.huak.common.des.DESUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
@Service
public class ReversibleEncryptionImpl implements ReversibleEncryption {
    @Value("${huak.des.key}")
    private String key;

    /**
     * 解密
     *
     * @param data
     * @return
     */
    @Override
    public String decode(String data) throws Exception {
        return DESUtil.decrypt(data, key);
    }


    /**
     * 加密
     *
     * @param data
     * @return
     */
    @Override
    public String encode(String data) throws Exception {
        return DESUtil.encrypt(data, key);
    }
}
