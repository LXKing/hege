package com.huak.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (C), 2009-2012, 北京华热科技发展有限公司.<BR>
 * ProjectName:eccp<BR>
 * File name:  com.huak.common<BR>
 * Author:  lichao  <BR>
 * Project:eccp    <BR>
 * Version: v 1.0      <BR>
 * Date: 2016/8/26<BR>
 * Description:    常量类 <BR>
 * Function List:  <BR>
 */
public class Constants {
    /* 能耗数据异常数据统一返回值 */
    public final static Double EXPTION_NUM = -1d;
    /* 统一登录url */
    //public final static String LOGIN_URL = "http://www.e-heating.org/login.html";
    public final static String LOGIN_URL = "http://localhost:8081/emc-web/login";

    /* 分页常量 */
    public final static String OFFSET = "offset";
    public final static String LIMIT = "limit";
    public final static String PAGE = "page";
    public final static String DATA = "data";
    public final static String PAGENO = "pageNo";
    /* 分页返回名称 */
    public final static String LIST = "list";

    /* JSON返回boolean常量名称 */
    public final static String FLAG = "flag";
    /* JSON返回消息常量名称 */
    public final static String MSG = "msg";

    /*修改页面返回实体对象*/
    public final static String OBJECT = "object";

    /*授权菜单功能*/
    public final static String GRANT_MENU_AFTER = "grantMenuAfter";
    /*授权菜单功能*/
    public final static String GRANT_MENU_BEFORE = "grantMenuBefore";
    /*子菜单*/
    public final static String CHILDREN_MENUS = "children";
    /*功能*/
    public final static String FUNCTIONS = "functions";
    /*新增或者保存 0 SAVE 1 UPDATE*/
    public final static String SAVE_OR_UPDATE = "saveOrUpdate";

    /*字典*/
    public final static String SYS_DIC = "sysDic";
    /*后台系统*/
    public final static String PLATFORM = "platform";
    /*前台系统*/
    public final static String WEB = "web";
    /*字典 MAP<类型标识，字典列表>*/
    public final static String DIC = "dicList";
    /*字典 MAP<类型主键，字典列表>*/
    public final static String DIC_LIST = "diclist";
    /*字典标识MAP<标识,说明>*/
    public final static String DIC_DES = "dicDes";
    /*字典类型MAP<类型主键,说明>*/
    public final static String DIC_MAP = "dicType";
    /*字典类型MAP<类型标识,类型主键>*/
    public final static String DIC_TYPE_MAP = "dicTypeMap";
    /*字典类型MAP<类型标识,类型主键>*/
    public final static String DIC_DES_MAP = "dicDesMap";

    /*session user key*/
    public final static String SESSION_KEY = "_user";

    /*session timeout status*/
    public final static String SESSION_STATUS = "sessionStatus";

    /* session auth key*/
    public final static String SESSION_AUTH_KEY = "_auth";

    /* session role key*/
    public final static String SESSION_ROLE_KEY = "_role";

    /* session menu key*/
    public final static String SESSION_MENU_KEY = "_menu";

    /* session cell key*/
    public final static String SESSION_CELL_KEY = "_cell";

    /* session part key*/
    public final static String SESSION_PART_KEY = "_part";

    /* 管理员账号*/
    public final static String ADMIN_LOGIN = "admin";
    public static final String GRANT_MENU_AFTER_ID = "afterMenuId";
    public static final String GRANT_MENU_BEFORE_ID = "beforeMenuId";
    
    /* 操作类型--新增*/
    public final static String OPT_TYPE_INSERT = "0";
    
    /* 操作类型--删除*/
    public final static String OPT_TYPE_DELETE = "1";
    
    /* 操作类型--修改*/
    public final static String OPT_TYPE_UPDATE = "2";
    
    /* 操作类型--查询*/
    public final static String OPT_TYPE_SELECT = "3";

    public static final String NAVIGATIONS = "navigations";

    //所属组织
    public final static String SESSION_ORG_KEY = "org_key";
    //所属公司
    public final static String SESSION_COM_KEY = "com_key";
    //员工
    public final static String SESSION_EMPLOYEE_KEY = "employee_key";
    public static final String XAXIS = "xaxis";
    public static final String LEGENDS = "legends";
    public static Map<String,String> CELL_NAME = new LinkedHashMap<>();
    static {
        CELL_NAME.put("ID", "主键");
        CELL_NAME.put("CODE", "代码");
        CELL_NAME.put("SERIAL_NO", "出厂编号");
        CELL_NAME.put("NAME", "名称");
        CELL_NAME.put("UNIT_ID", "单位名称主键");
        CELL_NAME.put("ENERGY_TYPE_ID", "能源类型1-水2-电3-气4-热5-煤");
        CELL_NAME.put("ISREAL", "实虚表(0-实表 1-虚表)");
        CELL_NAME.put("ISTOTAL", "是否总表(0-否 1-单位总表 2-系统总表)");
        CELL_NAME.put("COEF", "系数");
        CELL_NAME.put("UNIT_TYPE", "单位类型1-源2-网3-站4-线5-户");
        CELL_NAME.put("ISAUTO", "采集(0-自动采集 1-手工)");
        CELL_NAME.put("TAG", "点表");
        CELL_NAME.put("FORMULA", "公式");
        CELL_NAME.put("ISPRESTORE", "预存(0-不是 1-是)");
        CELL_NAME.put("ISDELETE", "删除标识(软删除标识1 未删除0)");
        CELL_NAME.put("DEPICT", "描述");
        CELL_NAME.put("COM_ID", "所属公司id");
        CELL_NAME.put("CNAME", "所属公司名称");
        CELL_NAME.put("UNITNAME", "用能单位名称");

    }
}
