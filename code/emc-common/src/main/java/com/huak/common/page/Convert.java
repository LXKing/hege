package com.huak.common.page;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:emc<BR>
 * File name:  com.huak.common.page<BR>
 * Author:  lichao  <BR>
 * Project:emc    <BR>
 * Version: v 1.0      <BR>
 * Date: 2017/4/25<BR>
 * Description:  转换器   <BR>
 * Function List:  <BR>
 */
public class Convert {
    /**
     * 转换
     *
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> convert(List<T> datas) {
        PageResult<T> result = new PageResult<T>();
        com.huak.common.page.Page p = new com.huak.common.page.Page();
        if (datas instanceof Page) {
            Page page = (Page) datas;
            p.setPageNumber(page.getPageNum());
            p.setPageSize(page.getPageSize());
            p.setTotal(page.getTotal());
            p.setPages(page.getPages());
            result.setList(page.getResult());
            result.setPage(p);
        } else {
            p.setPageNumber(1);
            p.setPageSize(datas.size());
            p.setTotal(datas.size());
            result.setList(datas);
            result.setPage(p);
        }

        return result;
    }
}
