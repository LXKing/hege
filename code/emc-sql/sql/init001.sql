/*
初始化数据库
2017年5月3日13:49:38
*/

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50712
Source Host           : 127.0.0.1:3306
Source Database       : emc

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-05-05 08:31:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_emc_carbon_check
-- ----------------------------
DROP TABLE IF EXISTS t_emc_carbon_check;
CREATE TABLE t_emc_carbon_check (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  CARBON_INDEX double(6,2) NOT NULL COMMENT '碳排放强度',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  EDATE date NOT NULL COMMENT '结束日期(%Y-%m-%d)',
  CID varchar(32) NOT NULL COMMENT '创建人',
  CDID varchar(32) NOT NULL COMMENT '创建人部门',
  CTIME datetime NOT NULL COMMENT '创建时间(%Y-%m-%d %H:%i:%s)',
  ISDELETED tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除(0-否 1-删除)',
  UID varchar(32) NOT NULL COMMENT '修改人',
  UDID varchar(32) NOT NULL COMMENT '修改人部门',
  UTIME datetime NOT NULL COMMENT '修改时间(%Y-%m-%d %H:%i:%s)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='碳排放对标表';

-- ----------------------------
-- Table structure for t_emc_carbon_formula
-- ----------------------------
DROP TABLE IF EXISTS t_emc_carbon_formula;
CREATE TABLE t_emc_carbon_formula (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  CAMOUNT double(6,2) NOT NULL COMMENT '单位热值含碳量（吨 碳/TJ）',
  CRATE double(6,2) NOT NULL COMMENT '碳氧化率',
  SDATE date NOT NULL COMMENT '开始日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='碳排放公式表';

-- ----------------------------
-- Table structure for t_emc_coal_coef
-- ----------------------------
DROP TABLE IF EXISTS t_emc_coal_coef;
CREATE TABLE t_emc_coal_coef (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  COEF double(10,6) NOT NULL COMMENT '系数',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='折标煤系数表';

-- ----------------------------
-- Table structure for t_emc_com_model
-- ----------------------------
DROP TABLE IF EXISTS t_emc_com_model;
CREATE TABLE t_emc_com_model (
  MID varchar(32) NOT NULL COMMENT '模块主键',
  CID varchar(32) NOT NULL COMMENT '公司主键',
  PRIMARY KEY (MID,CID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司模块关系表';

-- ----------------------------
-- Table structure for t_emc_cost_manual
-- ----------------------------
DROP TABLE IF EXISTS t_emc_cost_manual;
CREATE TABLE t_emc_cost_manual (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  TYPEID varchar(32) NOT NULL COMMENT '成本类型',
  COST decimal(10,2) NOT NULL COMMENT '成本',
  COST_DATE date NOT NULL COMMENT '日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成本填报表';

-- ----------------------------
-- Table structure for t_emc_cost_type
-- ----------------------------
DROP TABLE IF EXISTS t_emc_cost_type;
CREATE TABLE t_emc_cost_type (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '类型名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '类型名称(英文)',
  DOSAGE_UNIT varchar(16) NOT NULL COMMENT '用量单位',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成本类型表';

-- ----------------------------
-- Table structure for t_emc_energy_com
-- ----------------------------
DROP TABLE IF EXISTS t_emc_energy_com;
CREATE TABLE t_emc_energy_com (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司能源类型关系表';

-- ----------------------------
-- Table structure for t_emc_energy_price
-- ----------------------------
DROP TABLE IF EXISTS t_emc_energy_price;
CREATE TABLE t_emc_energy_price (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  PRICE decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每用量单位单价(元)',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  STIME time NOT NULL COMMENT '开始时间(%H:00:00)(包含)',
  ETIME time NOT NULL COMMENT '结束时间(%H:00:00)(不包含)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='能源单价表';

-- ----------------------------
-- Table structure for t_emc_energy_type
-- ----------------------------
DROP TABLE IF EXISTS t_emc_energy_type;
CREATE TABLE t_emc_energy_type (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '类型名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '类型名称(英文)',
  DOSAGE_UNIT varchar(16) NOT NULL COMMENT '用量单位',
  PRICE decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '每用量单位默认单价(元)',
  COEF double(10,6) NOT NULL DEFAULT '1.000000' COMMENT '每用量单位默认标煤系数',
  ECO_TYPE tinyint(4) NOT NULL COMMENT '经济类型(0-即是产品也是成本1-成本2-产品)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='能源类型表（包括热力站热源）';

-- ----------------------------
-- Table structure for t_emc_expend_check
-- ----------------------------
DROP TABLE IF EXISTS t_emc_expend_check;
CREATE TABLE t_emc_expend_check (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  STANDARD double(6,2) NOT NULL COMMENT '达标线',
  EXCELLENT double(6,2) NOT NULL COMMENT '优秀线',
  SDATE date NOT NULL COMMENT '开始日期(%Y-%m-%d)',
  EDATE date NOT NULL COMMENT '结束日期(%Y-%m-%d)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单耗考核对标表';

-- ----------------------------
-- Table structure for t_emc_final_cost_day
-- ----------------------------
DROP TABLE IF EXISTS t_emc_final_cost_day;
CREATE TABLE t_emc_final_cost_day (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  TYPEID varchar(32) NOT NULL COMMENT '成本类型',
  COST decimal(10,2) NOT NULL COMMENT '成本',
  COST_DATE date NOT NULL COMMENT '日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最终每天成本表';

-- ----------------------------
-- Table structure for t_emc_final_data_hour
-- ----------------------------
DROP TABLE IF EXISTS t_emc_final_data_hour;
CREATE TABLE t_emc_final_data_hour (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  NODEID varchar(32) NOT NULL COMMENT '用能节点',
  TYPEID varchar(32) NOT NULL COMMENT '能源类型',
  DOSAGE_TIME datetime NOT NULL COMMENT '时间(%Y-%m-%d %H:00:00)',
  DOSAGE double(10,2) NOT NULL COMMENT '能源用量',
  AREA double(12,2) NOT NULL COMMENT '能源面积(㎡)',
  PRICE decimal(10,2) NOT NULL COMMENT '能源单价(元)',
  WTEMP double(6,2) NOT NULL COMMENT '天气温度(℃)',
  CWTEMP double(6,2) NOT NULL COMMENT '折算天气温度(℃)',
  COAL_COEF double(10,6) NOT NULL COMMENT '标煤系数',
  C_COEF double(10,6) NOT NULL COMMENT '碳排放系数',
  ITEMP double(6,2) DEFAULT NULL COMMENT '平均室内温度(℃)',
  CITEMP double(6,2) DEFAULT NULL COMMENT '折算室内温度(℃)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最终每小时能源用量表(一个用能单位的一个能源类型在每小时只能有一条记录)';

-- ----------------------------
-- Table structure for t_emc_heat_season
-- ----------------------------
DROP TABLE IF EXISTS t_emc_heat_season;
CREATE TABLE t_emc_heat_season (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  NAME varchar(16) NOT NULL COMMENT '名称',
  SDATE date NOT NULL COMMENT '开始日期',
  EDATE date NOT NULL COMMENT '结束日期',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采暖季度表';

-- ----------------------------
-- Table structure for t_emc_home_model
-- ----------------------------
DROP TABLE IF EXISTS t_emc_home_model;
CREATE TABLE t_emc_home_model (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '名称(英文)',
  TYPE tinyint(4) NOT NULL COMMENT '类型(0-领导 1-员工)',
  SORTNO tinyint(4) NOT NULL COMMENT '排序',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='首页模块表';

-- ----------------------------
-- Table structure for t_emc_log
-- ----------------------------
DROP TABLE IF EXISTS t_emc_log;
CREATE TABLE t_emc_log (
  ID varchar(32) NOT NULL COMMENT '主键',
  NAME_ZH varchar(16) NOT NULL COMMENT '名称(中文)',
  NAME_EN varchar(16) NOT NULL COMMENT '名称(英文)',
  TYPE tinyint(4) NOT NULL COMMENT '类型(0-领导 1-员工)',
  SORTNO tinyint(4) NOT NULL COMMENT '排序',
  CID varchar(32) NOT NULL COMMENT '创建人',
  CDID varchar(32) NOT NULL COMMENT '创建人部门',
  CTIME datetime NOT NULL COMMENT '创建时间(%Y-%m-%d %H:%i:%s)',
  ISDELETED tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除(0-否 1-删除)',
  UID varchar(32) NOT NULL COMMENT '修改人',
  UDID varchar(32) NOT NULL COMMENT '修改人部门',
  UTIME datetime NOT NULL COMMENT '修改时间(%Y-%m-%d %H:%i:%s)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Table structure for t_emc_unit_area
-- ----------------------------
DROP TABLE IF EXISTS t_emc_unit_area;
CREATE TABLE t_emc_unit_area (
  ID varchar(32) NOT NULL COMMENT '主键',
  COMID varchar(32) NOT NULL COMMENT '公司',
  NODEID varchar(32) NOT NULL COMMENT '用能节点',
  UNITID varchar(32) NOT NULL COMMENT '用能单位',
  AREA double(12,2) NOT NULL COMMENT '能源面积(㎡)',
  STIME datetime NOT NULL COMMENT '开始时间(%Y-%m-%d %H:00:00)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位面积表';

-- ----------------------------
-- Table structure for t_emc_user_model
-- ----------------------------
DROP TABLE IF EXISTS t_emc_user_model;
CREATE TABLE t_emc_user_model (
  ID varchar(32) NOT NULL COMMENT '主键',
  MID varchar(32) NOT NULL COMMENT '模块主键',
  STATUS tinyint(4) NOT NULL COMMENT '状态(0显示1不显示)',
  SORTNO tinyint(4) NOT NULL COMMENT '排序(升序)',
  PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户模块表';
drop table if exists t_emc_role;
