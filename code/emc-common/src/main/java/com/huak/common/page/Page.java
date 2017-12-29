package com.huak.common.page;

import java.io.Serializable;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common.page<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/26<BR>
 * Description:  分页对象   <BR>
 * Function List:  <BR>
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 6731271266202997438L;
    public int pageNumber = 1; // 当前页
    public int pageSize = 10; // 每页多少行
    public long total; // 共多少行
    public int pages; // 共多少页

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber < 1) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public static void main(String[] args) {
        String table = "A_BAK_RB";

        for (int i = 0; i < 24; i++) {

            for (int j = 10; j < 70; j += 10) {
                System.out.println("update " + table + " set mtime= replace(mtime,'" + a(i, j) + ":" + b(j) + ":00','" + c(i, j) + "') where mtime like '% " + a(i, j) + ":" + b(j) + ":00';");
            }
        }


    }

    private static String a(int i, int j) {
        if (j == 60) {
            i += 1;
        }
        if (i > 9) {
            return String.valueOf(i);
        } else {
            return "0" + i;
        }
    }

    private static String b(int j) {
        if (j == 60) {
            return "00";
        } else {
            return "" + j;
        }
    }

    private static String c(int i, int j) {
        i = i + 1;
        if (i > 9) {
            return String.valueOf(i);
        } else {
            return "0" + String.valueOf(i);
        }
    }
}
