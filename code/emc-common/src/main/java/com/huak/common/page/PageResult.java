package com.huak.common.page;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.common.page<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/25<BR>
 * Description:  封装分页结果   <BR>
 * Function List:  <BR>
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据集
     */
    private List<T> list;

    private Page page;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        //将List转换成数组
        T[] obj = (T[]) list.toArray();
        //执行序列化存储
        out.writeObject(obj);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        //执行反序列化读取
        T[] obj = (T[]) in.readObject();
        //将数组转换成List
        list = Arrays.asList(obj);
    }

}
