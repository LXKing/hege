package com.huak.mdc;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.mdc<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/8/28<BR>
 * Description: 自定义异常类(继承运行时异常)  事物一致性  <BR>
 * Function List:  <BR>
 */
public class UniformityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UniformityException(String msg){
        super(msg);
    }

}
