/*2017年5月3日*/

/*菜单
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_menu;

/*==============================================================*/
/* Table: t_emc_menu                                            */
/*==============================================================*/
create table t_emc_auth_menu
(
  ID                   varchar(32) not null comment '菜单ID',
  MENU_NAME            varchar(32) not null comment '菜单名称',
  MENU_URL             varchar(128) not null comment '菜单路径',
  P_MENU_ID            varchar(128) not null comment '前台直接上级菜单',
  CREATOR              varchar(64) comment '创建人',
  CREAT_TIME           datetime comment '创建时间',
  MENU_TYPE            tinyint not null comment '前后台类型0-通用 1 前台 2 后台 ',
  P_MENU_AF_ID         varchar(128) not null comment '后台直接上级菜单',
  primary key (ID)
);
alter table t_emc_auth_menu comment '权限菜单';
alter table t_emc_auth_menu add SEQ int not Null;
alter table t_emc_auth_menu add MENU_ICON varchar(64) ;
alter table t_emc_auth_menu change P_MENU_AF_ID TYPE varchar(128) NOT NULL;
ALTER TABLE t_emc_auth_menu  MODIFY MENU_TYPE  TINYINT;
/*用户表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_user;
/*==============================================================*/
/* Table: t_emc_user                                            */
/*==============================================================*/
create table t_emc_auth_user
(
  ID                   varchar(32) not null comment '用户主键',
  LOGIN                varchar(64) not null comment '登录账号可以是手机号、工号、电子邮箱和自定义',
  PASSWORD             varchar(32) not null comment '密码32位加密报文',
  LOGIN_TIME           datetime comment '登录时间',
  LATS_LOGIN_TIME      datetime comment '上次登录时间',
  LOGIN_NUM            int not null default 0 comment '登录次数',
  USE_STATUS           tinyint not null default 0 comment '使用状态 0-启用1-禁用',
  MEMO                 varchar(255) comment '备注',
  CREATOR              varchar(64) not null comment '创建者',
  CREATE_TIME          datetime not null comment '创建时间',
  primary key (ID)
);
alter table t_emc_auth_user comment '用户表';

/*员工表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_org_employee;
/*==============================================================*/
/* Table: t_emc_org_employ                                      */
/*==============================================================*/
create table t_emc_org_employee
(
  ID                   varchar(32) not null comment '员工ID',
  EMP_NAME             varchar(16) not null comment '员工名称',
  EMP_CODE             varchar(32) not null comment '员工编号',
  EMP_TEL              varchar(16) comment '联系电话',
  ADDR                 varchar(128) comment '家庭住址',
  ORG_ID               varchar(64) not null comment '上级组织机构ID',
  EMP_AGE              int comment '员工年龄',
  EMP_GENDER           int comment '员工性别',
  EMP_BIRTHDAY         date comment '员工出生日期',
  EMAIL                varchar(64) comment '员工邮箱',
  EMP_MOBILEPHONE      varchar(11) comment '手机号',
  user_id              varchar(32) not null comment '用户主键',
  primary key (ID)
);
alter table t_emc_org_employee comment '员工表';

/*角色-用户关系表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_role_user_rel;
/*==============================================================*/
/* Table: t_emc_role_user_rel                                   */
/*==============================================================*/
create table t_emc_auth_role_user_rel
(
  USER_ID              varchar(32) not null comment '用户主键',
  ROLE_ID              varchar(32) not null comment '角色主键'
);
alter table t_emc_auth_role_user_rel comment '角色用户关系表';

/*菜单功能关系表
  2017年5月5日15:10:10
  sunbinbin
*/
drop table if exists t_emc_auth_menu_func_rel;
/*==============================================================*/
/* Table: t_emc_menu_func_rel                                   */
/*==============================================================*/
create table t_emc_auth_menu_func_rel
(
  ROLE_ID              varbinary(64) not null comment '功能id',
  MENU_ID              varbinary(64) not null comment '菜单id'
);
alter table t_emc_auth_menu_func_rel comment '菜单功能关系表';


/*组织机构   功能2017年5月9日 12:59:12*/


drop table if exists t_emc_org;

/*==============================================================*/
/* Table: T_ECC_ORG                                             */
/*==============================================================*/
create table t_emc_org
(
   ID               		bigint not null auto_increment comment '机构主键',
	 COM_ID								varchar(32) comment '公司id',
   ORG_CODE             varchar(16) not null comment '机构代码',
   ORG_NAME             varchar(64) not null comment '机构名称',
   SHORT_NAME           varchar(32) comment '简称',
   P_ORG_ID             bigint not null comment '上级组织机构主键',
   TYPE_ID              varchar(32) not null comment '类型',
   CREATOR              varchar(32) not null comment '创建者',
   CREATE_TIME          datetime not null comment '创建时间',
   MEMO                 varchar(255) comment '备注',
   SEQ                  int not null comment '排序',
   AREA                 double comment '面积',
   primary key (ID)
);

alter table t_emc_org comment '组织机构父表';


drop table if exists t_emc_org_feed;

/*==============================================================*/
/* Table: t_emc_org_feed                                        */
/*==============================================================*/
create table t_emc_org_feed
(
   ID                   bigint not null auto_increment comment '机构主键',
   FEED_TYPE            tinyint not null comment '热源性质 来源字典表 1:热电 2:区域锅炉房 3:核电 4:工业余热 ',
   HEAT_TYPE            tinyint not null comment '供热类型 来源字典表 区域供热 集中供热 尖峰供热',
   INSTALL_CAPACITY     double not null comment '装机容量(MW)',
   HEAT_CAPACITY        double not null default 0 comment '供热能力(㎡)',
   BOILER_NUM           int not null default 0 comment '锅炉数量(自动计算)',
   STEAMTURBINE_NUM     int not null default 0 comment '汽机数量(自动计算)',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ID)
);

alter table t_emc_org_feed comment '热源基本信息表';


drop table if exists t_emc_org_node;

/*==============================================================*/
/* Table: t_emc_org_node                                        */
/*==============================================================*/
create table t_emc_org_node
(
   ID                   varchar(32) not null comment '机构主键',
   MANAGE_TYPE_ID       varchar(32) not null comment '管理类型 来源于字典表 0-统管 1-自管 2-代管',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ID)
);
alter table t_emc_org_node comment '热力站基本信息表';
ALTER TABLE t_emc_org_node DROP PRIMARY KEY ;
alter table t_emc_org_node add STATUS TYPEINT not Null;

drop table if exists t_emc_org_room;

/*==============================================================*/
/* Table: t_emc_org_room                                        */
/*==============================================================*/
create table t_emc_org_room
(
   ORG_ID               bigint not null auto_increment comment '机构主键',
   DWELL_AREA           double not null comment '居民面积',
   primary key (ORG_ID)
);

alter table t_emc_org_room comment '房间基本信息表';


drop table if exists t_emc_org_ban;

/*==============================================================*/
/* Table: t_emc_org_ban                                         */
/*==============================================================*/
create table t_emc_org_ban
(
   ORG_ID               bigint not null auto_increment comment '机构主键',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ORG_ID)
);

alter table t_emc_org_ban comment '建筑基本信息表';


drop table if exists t_emc_org_unit;

/*==============================================================*/
/* Table: t_emc_org_unit                                        */
/*==============================================================*/
create table t_emc_org_unit
(
   ID                   bigint not null comment '机构主键',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(64) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   PUBLIC_AREA          double not null default 0 comment '公建面积',
   DWELL_AREA           double not null default 0 comment '居民面积',
   primary key (ID)
);

alter table t_emc_org_unit comment '单元基本信息表';


drop table if exists t_emc_org_secondne;

/*==============================================================*/
/* Table: t_emc_org_secondne                                    */
/*==============================================================*/
create table t_emc_org_secondnet
(
   ID                   bigint not null auto_increment comment '机构主键',
   NET_TYPE_ID          varchar(32) not null comment '管线类型 来源字典表 干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度(管段长度生成)',
   CELL_NUM             int not null default 0 comment '小室数量',
   PART_NUM             int not null default 0 comment '管段数量',
   MEDIUM               varchar(32) not null comment '输送介质',
   primary key (ID)
);

alter table t_emc_org_secondnet comment '二次管线基本信息表';


drop table if exists t_emc_org_oncenet;

/*==============================================================*/
/* Table: t_emc_org_oncenet                                     */
/*==============================================================*/
create table t_emc_org_oncenet
(
   ID                   bigint not null auto_increment comment '机构主键',
   NET_TYPE_ID          varchar(32) not null comment '管线类型 来源字典表  干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度(管段长度生成)',
   CELL_NUM             int not null default 0 comment '小室数量',
   PART_NUM             int not null default 0 comment '管段数量',
   MEDIUM               varchar(32) not null comment '输送介质',
   primary key (ID)
);

alter table t_emc_org_oncenet comment '一次管网基本信息表';

/**
  角色表
  sunbinbin
  2017/5/9 14:03
 */
drop table if exists t_emc_auth_role;
/*==============================================================*/
/* Table: t_emc_role                                            */
/*==============================================================*/
create table t_emc_auth_role
(
  ID                   varchar(32) not null comment '角色主键',
  ROLE_NAME            varchar(16) not null comment '角色名称',
  ROLE_DES             varchar(32) not null comment '角色描述',
  MEMO                 varchar(255) comment '备注',
  CREATOR              varchar(64) not null comment '创建者',
  CREATE_TIME          datetime not null comment '创建时间',
  primary key (ID)
);

alter table t_emc_auth_role comment '角色基本信息表';

/*
时间2017年5月10日08:13:59
*/
alter table t_emc_auth_role modify column CREATOR varchar(32);

/**
2017年5月12日14:35:55
 */
 drop table if exists t_emc_auth_func;

/*==============================================================*/
/* Table: T_ECC_AUTH_FUNC                                       */
/*==============================================================*/
create table t_emc_auth_func
(
   ID              varchar(32) not null comment '功能主键',
   MENU_ID              varchar(32) not null comment '菜单主键',
   UNAME                varchar(32) not null comment '唯一名称 功能唯一标识',
   FUNC_NAME            varchar(32) not null comment '功能名称',
   ISSEARCH              tinyint not null comment '是否查询 0-是 1-否',
   SEQ                  int not null comment '排序',
   primary key (ID)
);

alter table t_emc_auth_func comment '功能基本信息表';

/**
2017年5月16日14:51:27
 */
drop table if exists t_emc_sys_dic;

/*==============================================================*/
/* Table: t_emc_sys_dic                                         */
/*==============================================================*/
create table t_emc_sys_dic
(
   ID                   varchar(32) not null comment '字典主键',
   DES                  varchar(16) not null comment '字典名称',
   TYPE_US              varchar(32) not null comment '字典类型',
   TYPE_ZH              varchar(32) not null comment '字典类型名称',
   SEQ                  int not null comment '排序',
   primary key (ID)
);

alter table t_emc_sys_dic comment '系统字典表';

/**
2017年5月17日13:03:29
 */
 drop table if exists t_emc_role_func_rel;

/*==============================================================*/
/* Table: t_emc_role_func_rel                                   */
/*==============================================================*/
create table t_emc_role_func_rel
(
   ROLE_ID              varchar(32) not null comment '角色主键',
   FUNC_ID              varchar(32) not null comment '功能主键'
);

alter table t_emc_role_func_rel comment '角色权限功能表';

drop table if exists t_emc_company;

/*==============================================================*/
/* Table: t_emc_company                                         */
/*==============================================================*/
create table t_emc_company
(
   ID                   varchar(32) not null comment '公司主键',
   CNAME                varchar(64) comment '公司名称',
   primary key (id)
);

alter table t_emc_company comment '公司信息表';


/**
2017年5月22日09:05:51
 */
 ALTER TABLE t_emc_role_func_rel RENAME t_emc_auth_role_func_rel;

 DROP TABLE t_emc_auth_menu_func_rel;

/**
  2017年5月23日 10:26:22 修改org表中字段类型
 */
 alter table t_emc_org modify column TYPE_ID VARCHAR(32);
 alter table t_emc_org modify column CREATOR VARCHAR(32);

/**
2017年5月26日14:49:36
 */
 ALTER TABLE t_emc_energy_type ADD TYPE TINYINT NOT NULL COMMENT '类型 1水、2电、3气、4煤、5热、6太阳能';

/**
2017年5月27日11:02:53
 */

 DROP FUNCTION IF EXISTS emc_func_org_getparents;

CREATE FUNCTION emc_func_org_getparents(orgid varchar(32))
 RETURNS varchar(4000) CHARSET utf8
BEGIN
      DECLARE sTemp VARCHAR(10000);

      DECLARE sTempChd VARCHAR(10000);
				SET sTemp = '$';
       SET sTempChd =cast(orgid as CHAR);

      WHILE sTempChd is not null DO
         SET sTemp = concat(sTemp,',',sTempChd);
         SELECT group_concat(P_ORG_ID) INTO sTempChd FROM t_emc_org where FIND_IN_SET(ID,sTempChd)>0;
      END WHILE;
       RETURN REPLACE(sTemp,'$,','');
END;

/**
2017年5月31日10:57:47
 */
 drop table if exists t_emc_org;
drop table if exists t_emc_org_feed;
drop table if exists t_emc_org_node;
drop table if exists t_emc_org_ban;
drop table if exists t_emc_org_employee;
drop table if exists t_emc_org_oncenet;
drop table if exists t_emc_org_room;
drop table if exists t_emc_org_secondnet;
drop table if exists t_emc_org_unit;



/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/31 10:11:04                           */
/*==============================================================*/


drop table if exists t_emc_org;


drop table if exists t_emc_unit_ban;


drop table if exists t_emc_unit_cell;


drop table if exists t_emc_unit_community;


drop table if exists t_emc_unit_feed;


drop table if exists t_emc_unit_line;


drop table if exists t_emc_unit_net;


drop table if exists t_emc_unit_room;


drop table if exists t_emc_unit_station;

drop table if exists t_emc_unit_type_rel;

/*==============================================================*/
/* Table: t_emc_org                                             */
/*==============================================================*/
create table t_emc_org
(
   ID                   bigint not null auto_increment comment '机构主键',
   ORG_CODE             varchar(16) not null comment '机构代码',
   ORG_NAME             varchar(64) not null comment '机构名称',
   SHORT_NAME           varchar(32) comment '简称',
   P_ORG_ID             bigint not null comment '上级组织机构主键',
   TYPE_ID              bigint not null comment '类型',
   CREATOR              bigint not null comment '创建者',
   CREATE_TIME          datetime not null comment '创建时间',
   MEMO                 varchar(255) comment '备注',
   SEQ                  int not null comment '排序',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_org comment '组织机构表';


/*==============================================================*/
/* Table: t_emc_unit_ban                                        */
/*==============================================================*/
create table t_emc_unit_ban
(
   ID                   varchar(32) not null comment 'ID',
   BAN_NAME             varchar(64) not null comment '名称',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(128) not null comment '详细地址',
   COMMUNITY_ID         varchar(32) comment '小区',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_ban comment '楼栋基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_cell                                       */
/*==============================================================*/
create table t_emc_unit_cell
(
   ID                   varchar(32) not null comment 'ID',
   NAME                 varchar(64) not null comment '名称',
   BAN_ID               varchar(32) not null comment '楼栋',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_cell comment '单元基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_community                                  */
/*==============================================================*/
create table t_emc_unit_community
(
   ID                   varchar(32) not null comment 'ID',
   COMMUNITY_NAME       varchar(64) not null comment '名称',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_community comment '小区基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_feed                                       */
/*==============================================================*/
create table t_emc_unit_feed
(
   ID                   varchar(32) not null comment '主键',
   FEED_NAME            varchar(64) not null comment '热源名称',
   FEED_CODE            varchar(32) comment '热源编码',
   FEED_TYPE            tinyint not null comment '热源性质 来源字典表 1:热电 2:区域锅炉房 3:核电 4:工业余热 ',
   HEAT_TYPE            tinyint not null comment '供热类型 来源字典表 区域供热 集中供热  尖峰供热',
   INSTALL_CAPACITY     double comment '装机容量(MW)',
   HEAT_CAPACITY        double default 0 comment '供热能力(㎡)',
   BOILER_NUM           int default 0 comment '锅炉数量',
   STEAMTURBINE_NUM     int default 0 comment '汽机数量',
   PROVINCE_ID          varchar(12) not null comment '省主键  关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(128) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   HEAT_AREA            double not null default 0 comment '供热面积',
   ORG_ID               bigint not null comment '所属机构',
   NET_ID               varchar(32) comment '所属管网',
   LINE_ID              varchar(32) comment '所属管线',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_feed comment '用能单位-热源基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_line                                       */
/*==============================================================*/
create table t_emc_unit_line
(
   ID                   varchar(32) not null comment '主键',
   LINE_NAME            varchar(64) not null comment '名称',
   LINE_CODE            varchar(32) not null comment '代码',
   NET_TYPE_ID          tinyint not null comment '管线类型 来源字典表 干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度',
   CELL_NUM             int default 0 comment '小室数量',
   PART_NUM             int default 0 comment '管段数量',
   MEDIUM               varchar(32) comment '输送介质',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_line comment '用能单位-二次线基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_net                                        */
/*==============================================================*/
create table t_emc_unit_net
(
   ID                   varchar(32) not null comment '主键',
   NET_NAME             varchar(64) not null comment '名称',
   NET_CODE             varchar(32) not null comment '代码',
   NET_TYPE_ID          tinyint not null comment '管线类型 来源字典表 干线、支线、连通线、户线',
   LENGTH               double not null default 0 comment '管线长度',
   CELL_NUM             int default 0 comment '小室数量',
   PART_NUM             int default 0 comment '管段数量',
   MEDIUM               varchar(32) comment '输送介质',
   ORG_ID               bigint not null comment '所属机构',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_net comment '用能单位-一次网基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_room                                       */
/*==============================================================*/
create table t_emc_unit_room
(
   ID                   varchar(32) not null comment 'ID',
   ROOM_NAME            varchar(64) not null comment '名称',
   ROOM_CODE            varchar(32) not null comment '代码',
   HEAT_AREA            double not null default 0 comment '供热面积',
   ORG_ID               bigint not null comment '所属机构',
   LINE_ID              varchar(32) not null comment '所属管线',
   COMMUNITY_ID         varchar(32) comment '小区',
   BAN_ID               varchar(32) comment '楼栋',
   CELL_ID              varchar(32) comment '单元',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_room comment '用能单位-户基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_station                                    */
/*==============================================================*/
create table t_emc_unit_station
(
   ID                   varchar(32) not null comment '主键',
   MANAGE_TYPE_ID       tinyint not null comment '管理类型 来源于字典表 0-统管 1-自管 2-代管',
   PROVINCE_ID          varchar(12) not null comment '省主键 关联行政区划表T_ECC_SYS_ADMINISTRATIVE',
   CITY_ID              varchar(12) not null comment '市主键',
   COUNTY_ID            varchar(12) not null comment '县主键',
   TOWN_ID              varchar(12) comment '乡主键',
   VILLAGE_ID           varchar(12) comment '村主键',
   ADDR                 varchar(128) not null comment '详细地址',
   LNG                  double(10,6) comment '经度',
   LAT                  double(10,6) comment '纬度',
   STATION_NAME         varchar(64) not null comment '名称',
   STATION_CODE         varchar(32) not null comment '代码',
   HEAT_AREA            double not null default 0 comment '供热面积',
   ORG_ID               bigint not null comment '所属机构',
   NET_ID               varchar(32) comment '所属管网',
   FEED_ID              varchar(32) comment '所属热源',
   LINE_ID              varchar(32) not null comment '所属管线',
   COM_ID               varchar(32) not null comment '公司',
primary key (id)
);

alter table t_emc_unit_station comment '用能单位-热力站基本信息表';


/*==============================================================*/
/* Table: t_emc_unit_type_rel                                   */
/*==============================================================*/
create table t_emc_unit_type_rel
(
   TYPE_ID              varchar(32) not null comment '类型',
   COM_ID               varchar(32) not null comment '公司'
);

alter table t_emc_unit_type_rel comment '公司类型关系表';



alter table t_emc_org modify column TYPE_ID TINYINT(4);

alter table t_emc_org modify column CREATOR VARCHAR(32);


CREATE VIEW v_emc_unit   AS
select f.COM_ID comid,f.ID ryid,n.ID ycwid,s.ID rlzid,l.ID ecxid,r.ID hid
from t_emc_unit_feed f
LEFT JOIN t_emc_unit_net n ON f.NET_ID = n.ID
LEFT JOIN t_emc_unit_station s ON s.NET_ID = n.ID
LEFT JOIN t_emc_unit_line l ON s.LINE_ID = l.ID
LEFT JOIN t_emc_unit_room r ON r.LINE_ID = l.ID
UNION
select f.COM_ID comid,f.ID ryid,null ycwid,s.ID rlzid,l.ID ecxid,r.ID hid
from t_emc_unit_feed f
LEFT JOIN t_emc_unit_station s ON s.FEED_ID = f.ID
LEFT JOIN t_emc_unit_line l ON s.LINE_ID = l.ID
LEFT JOIN t_emc_unit_room r ON r.LINE_ID = l.ID
UNION
select f.COM_ID comid,f.ID ryid,null ycwid,null rlzid,l.ID ecxid,r.ID hid
from t_emc_unit_feed f
LEFT JOIN t_emc_unit_line l ON f.LINE_ID = l.ID
LEFT JOIN t_emc_unit_room r ON r.LINE_ID = l.ID;


CREATE VIEW v_emc_room   AS
select r.COM_ID comid,r.ID roomid,c.ID ycwid,b.ID rlzid,v.ID ecxid
from t_emc_unit_room r
LEFT JOIN t_emc_unit_cell c ON r.CELL_ID = c.ID
LEFT JOIN t_emc_unit_ban b ON r.BAN_ID = b.ID
LEFT JOIN t_emc_unit_community v ON r.COMMUNITY_ID = v.ID;
