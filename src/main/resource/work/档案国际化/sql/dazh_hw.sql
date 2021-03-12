/*
 Navicat Premium Data Transfer

 Source Server         : jyyh 192.168.17.43 hw
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 192.168.17.43:3306
 Source Schema         : dzda_hw

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 22/12/2020 11:40:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for aa10
-- ----------------------------
DROP TABLE IF EXISTS `aa10`;
CREATE TABLE `aa10` (
  `AAA100` varchar(20) NOT NULL COMMENT 'AAA100代码类别',
  `AAA101` varchar(100) NOT NULL COMMENT 'AAA102代码值',
  `AAA102` varchar(6) NOT NULL COMMENT 'AAA102代码值',
  `AAA103` varchar(100) NOT NULL COMMENT 'AAA103代码名称',
  `YAB003` varchar(6) NOT NULL COMMENT 'YAB003经办机构',
  `AAE120` varchar(6) NOT NULL COMMENT 'AAE120注销标志',
  `VER` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`AAA100`,`AAA102`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='AA10代码表';

-- ----------------------------
-- Table structure for ab01
-- ----------------------------
DROP TABLE IF EXISTS `ab01`;
CREATE TABLE `ab01` (
  `AAB001` decimal(65,30) NOT NULL COMMENT '组织ID',
  `AAB004` varchar(200) NOT NULL COMMENT '名称',
  `AAE053` varchar(10) NOT NULL COMMENT '组织机构代码',
  `AAB007` varchar(25) DEFAULT NULL COMMENT '工商登记执照号码',
  `AAB030` varchar(20) DEFAULT NULL COMMENT '税号',
  `AAE006` varchar(200) DEFAULT NULL COMMENT '地址(描述)',
  `BZE001` varchar(12) DEFAULT NULL COMMENT '地址(行政区划代码)',
  `AAB019` varchar(6) DEFAULT NULL COMMENT '单位类型',
  `AAB020` varchar(6) DEFAULT NULL COMMENT '经济类型',
  `AAB021` varchar(6) DEFAULT NULL COMMENT '隶属关系',
  `AAB022` varchar(6) DEFAULT NULL COMMENT '行业代码',
  `AAA017` varchar(6) NOT NULL COMMENT '组织有效标志',
  PRIMARY KEY (`AAB001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织信息';

-- ----------------------------
-- Table structure for ac01
-- ----------------------------
DROP TABLE IF EXISTS `ac01`;
CREATE TABLE `ac01` (
  `AAC001` varchar(20) NOT NULL COMMENT '个人编号',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAC003` varchar(50) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(4) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(6) DEFAULT NULL COMMENT '民族',
  `AAB004` varchar(100) DEFAULT NULL COMMENT '所属公司名称',
  `AAB009` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `AAB005` varchar(20) DEFAULT NULL COMMENT '公司电话',
  `AAC022` varchar(50) DEFAULT NULL COMMENT '借阅人提供证件类型',
  `AAC023` varchar(50) DEFAULT NULL COMMENT '证件编号',
  `AAC025` varchar(20) DEFAULT NULL COMMENT '个人联系电话',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YAE116` varchar(20) DEFAULT NULL COMMENT '经办人编号',
  PRIMARY KEY (`AAC001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借阅人基本信息表';

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(130) NOT NULL,
  `TRIGGER_GROUP` varchar(130) NOT NULL,
  `BLOB_DATA` longblob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIG_TO_TRIG_FK` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` longblob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(130) NOT NULL,
  `TRIGGER_GROUP` varchar(130) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` decimal(13,0) NOT NULL,
  `SCHED_TIME` decimal(13,0) NOT NULL,
  `PRIORITY` decimal(13,0) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`INSTANCE_NAME`,`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_J_G` (`JOB_NAME`,`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(130) NOT NULL,
  `JOB_GROUP` varchar(130) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` longblob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_job_log
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_log`;
CREATE TABLE `qrtz_job_log` (
  `LOG_ID` varchar(17) NOT NULL COMMENT '日志序列号',
  `JOB_NAME` varchar(200) NOT NULL COMMENT '任务名称',
  `ADDRESS` varchar(200) NOT NULL COMMENT '服务器地址',
  `SERVICE_ID` varchar(200) NOT NULL COMMENT '服务ID',
  `FIRED_TIME` datetime NOT NULL COMMENT '发生时间',
  `SUCCESS` varchar(6) NOT NULL COMMENT '成功标志',
  `LOG_MSG` varchar(255) DEFAULT NULL COMMENT '日志消息',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Quartz定时任务执行日志';

-- ----------------------------
-- Table structure for qrtz_job_msgs
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_msgs`;
CREATE TABLE `qrtz_job_msgs` (
  `ID` varchar(50) NOT NULL,
  `JOBID` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `USERID` varchar(20) DEFAULT NULL,
  `EXECSTARTTIME` varchar(30) DEFAULT NULL,
  `EXECENDTIME` varchar(30) DEFAULT NULL,
  `ISSUCCESS` varchar(2) DEFAULT NULL,
  `SUCCESSMSG` text,
  `ERRORMSG` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` decimal(13,0) NOT NULL,
  `CHECKIN_INTERVAL` decimal(13,0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(130) NOT NULL,
  `TRIGGER_GROUP` varchar(130) NOT NULL,
  `REPEAT_COUNT` decimal(7,0) NOT NULL,
  `REPEAT_INTERVAL` decimal(12,0) NOT NULL,
  `TIMES_TRIGGERED` decimal(10,0) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(130) NOT NULL,
  `TRIGGER_GROUP` varchar(130) NOT NULL,
  `STR_PROP_1` text,
  `STR_PROP_2` text,
  `STR_PROP_3` text,
  `INT_PROP_1` decimal(10,0) DEFAULT NULL,
  `INT_PROP_2` decimal(10,0) DEFAULT NULL,
  `LONG_PROP_1` decimal(13,0) DEFAULT NULL,
  `LONG_PROP_2` decimal(13,0) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(130) NOT NULL,
  `TRIGGER_GROUP` varchar(130) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` decimal(13,0) DEFAULT NULL,
  `PREV_FIRE_TIME` decimal(13,0) DEFAULT NULL,
  `PRIORITY` decimal(13,0) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` decimal(13,0) NOT NULL,
  `END_TIME` decimal(13,0) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` decimal(2,0) DEFAULT NULL,
  `JOB_DATA` longblob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`,`MISFIRE_INSTR`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_STATE`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGER_TO_JOBS_FK` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for rupd$_uf11
-- ----------------------------
DROP TABLE IF EXISTS `rupd$_uf11`;
CREATE TABLE `rupd$_uf11` (
  `YUF110` varchar(20) DEFAULT NULL,
  `DMLTYPE$$` varchar(1) DEFAULT NULL,
  `SNAPID` decimal(65,30) DEFAULT NULL,
  `CHANGE_VECTOR$$` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='temporary updatable snapshot log';

-- ----------------------------
-- Table structure for rupd$_uf13
-- ----------------------------
DROP TABLE IF EXISTS `rupd$_uf13`;
CREATE TABLE `rupd$_uf13` (
  `YUJ0X0` varchar(50) DEFAULT NULL,
  `YUE030` varchar(50) DEFAULT NULL,
  `YUF04Y` varchar(50) DEFAULT NULL,
  `YUE001` varchar(6) DEFAULT NULL,
  `DMLTYPE$$` varchar(1) DEFAULT NULL,
  `SNAPID` decimal(65,30) DEFAULT NULL,
  `CHANGE_VECTOR$$` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='temporary updatable snapshot log';

-- ----------------------------
-- Table structure for rupd$_uj01
-- ----------------------------
DROP TABLE IF EXISTS `rupd$_uj01`;
CREATE TABLE `rupd$_uj01` (
  `YUJ0X0` varchar(20) DEFAULT NULL,
  `DMLTYPE$$` varchar(1) DEFAULT NULL,
  `SNAPID` decimal(65,30) DEFAULT NULL,
  `CHANGE_VECTOR$$` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='temporary updatable snapshot log';

-- ----------------------------
-- Table structure for rupd$_uj03
-- ----------------------------
DROP TABLE IF EXISTS `rupd$_uj03`;
CREATE TABLE `rupd$_uj03` (
  `YUJ0X0` varchar(50) DEFAULT NULL,
  `DMLTYPE$$` varchar(1) DEFAULT NULL,
  `SNAPID` decimal(65,30) DEFAULT NULL,
  `CHANGE_VECTOR$$` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='temporary updatable snapshot log';

-- ----------------------------
-- Table structure for serveraddress
-- ----------------------------
DROP TABLE IF EXISTS `serveraddress`;
CREATE TABLE `serveraddress` (
  `ADDRESS` varchar(200) NOT NULL COMMENT '应用地址',
  `CANUSE` varchar(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`ADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集群各个SERVER的应用地址';

-- ----------------------------
-- Table structure for signrecord
-- ----------------------------
DROP TABLE IF EXISTS `signrecord`;
CREATE TABLE `signrecord` (
  `USERID` decimal(10,0) DEFAULT NULL COMMENT '人员ID',
  `SIGNTIME` datetime DEFAULT NULL COMMENT '签到时间',
  `SIGNSTATE` varchar(2) DEFAULT NULL COMMENT '签到状态(1 签到 2 签退)',
  `IP` varchar(20) DEFAULT NULL COMMENT 'IP 地址',
  `MAC` varchar(20) DEFAULT NULL COMMENT 'MAC地址',
  `SIGNID` decimal(20,0) NOT NULL COMMENT '流水号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签到签退记录表';

-- ----------------------------
-- Table structure for taaccesslog
-- ----------------------------
DROP TABLE IF EXISTS `taaccesslog`;
CREATE TABLE `taaccesslog` (
  `LOGID` int(15) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `USERID` decimal(10,0) NOT NULL COMMENT '用户id',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `PERMISSIONID` decimal(10,0) NOT NULL COMMENT '功能id',
  `ISPERMISSION` char(1) NOT NULL COMMENT '是否有权限',
  `ACCESSTIME` datetime NOT NULL COMMENT '访问时间',
  `URL` text COMMENT '访问路径',
  `SYSFLAG` varchar(50) DEFAULT NULL COMMENT '系统标识',
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB AUTO_INCREMENT=199283 DEFAULT CHARSET=utf8 COMMENT='功能日志表';

-- ----------------------------
-- Table structure for taadminyab139scope
-- ----------------------------
DROP TABLE IF EXISTS `taadminyab139scope`;
CREATE TABLE `taadminyab139scope` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `YAB139` varchar(6) NOT NULL COMMENT '数据区',
  PRIMARY KEY (`POSITIONID`,`YAB139`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员数据区管理范围';

-- ----------------------------
-- Table structure for tabseq
-- ----------------------------
DROP TABLE IF EXISTS `tabseq`;
CREATE TABLE `tabseq` (
  `seqno` int(10) NOT NULL AUTO_INCREMENT,
  `seqname` varchar(255) NOT NULL,
  `seqval` int(15) NOT NULL,
  PRIMARY KEY (`seqno`),
  KEY `index_tabseq_sqlname` (`seqname`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tacommonmenu
-- ----------------------------
DROP TABLE IF EXISTS `tacommonmenu`;
CREATE TABLE `tacommonmenu` (
  `USERID` decimal(10,0) NOT NULL COMMENT '用户id',
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`USERID`,`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常用菜单';

-- ----------------------------
-- Table structure for taconfig
-- ----------------------------
DROP TABLE IF EXISTS `taconfig`;
CREATE TABLE `taconfig` (
  `CONFIGID` decimal(10,0) NOT NULL COMMENT '配置项ID',
  `CONFIGNAME` varchar(100) NOT NULL COMMENT '配置项名称',
  `CONFIGVALUE` text COMMENT '配置项内容',
  `CONFIGTYPE` text COMMENT '是否系统参数',
  `CONFIGFLAG` varchar(20) NOT NULL COMMENT '系统标识',
  `CONFIGDESC` varchar(200) DEFAULT NULL COMMENT '参数说明',
  PRIMARY KEY (`CONFIGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

-- ----------------------------
-- Table structure for taconfigsyspath
-- ----------------------------
DROP TABLE IF EXISTS `taconfigsyspath`;
CREATE TABLE `taconfigsyspath` (
  `SERIALID` decimal(10,0) NOT NULL COMMENT '流水号',
  `ID` varchar(20) NOT NULL COMMENT '系统ID',
  `NAME` varchar(50) NOT NULL COMMENT '系统名称',
  `URL` varchar(100) NOT NULL COMMENT '系统路径前缀',
  `PY` varchar(20) DEFAULT NULL COMMENT '拼音',
  `CURSYSTEM` varchar(1) NOT NULL COMMENT '是否为当前系统',
  PRIMARY KEY (`SERIALID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统路径配置表';

-- ----------------------------
-- Table structure for taconsolemodule
-- ----------------------------
DROP TABLE IF EXISTS `taconsolemodule`;
CREATE TABLE `taconsolemodule` (
  `MODULE_ID` decimal(10,0) NOT NULL COMMENT '模块编号',
  `MODULE_NAME` varchar(100) NOT NULL COMMENT '模块名称',
  `MODULE_URL` varchar(200) NOT NULL COMMENT '模块链接',
  `MODULE_STA` varchar(1) NOT NULL COMMENT '模块有效标识',
  `MODULE_DEFAULT` varchar(1) DEFAULT NULL COMMENT '是否默认显示',
  `MODULE_HEIGHT` varchar(10) DEFAULT NULL COMMENT '模块默认高度',
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ECADMIN工作台自定义组件';

-- ----------------------------
-- Table structure for taconsolemodulelocation
-- ----------------------------
DROP TABLE IF EXISTS `taconsolemodulelocation`;
CREATE TABLE `taconsolemodulelocation` (
  `MARK` varchar(20) NOT NULL COMMENT '页面标识',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位ID',
  `LOCATION` text NOT NULL COMMENT '位置信息数据'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ECADMIN工作台自定义组件位置信息';

-- ----------------------------
-- Table structure for taconsolemoduleprivilege
-- ----------------------------
DROP TABLE IF EXISTS `taconsolemoduleprivilege`;
CREATE TABLE `taconsolemoduleprivilege` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '角色编号',
  `MODULEID` decimal(10,0) NOT NULL COMMENT '模块编号',
  PRIMARY KEY (`POSITIONID`,`MODULEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CRM工作台自定义组件权限信息';

-- ----------------------------
-- Table structure for tadataaccessdimension
-- ----------------------------
DROP TABLE IF EXISTS `tadataaccessdimension`;
CREATE TABLE `tadataaccessdimension` (
  `DIMENSIONID` decimal(10,0) NOT NULL COMMENT '维度ID',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位ID',
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单ID',
  `DIMENSIONTYPE` varchar(20) NOT NULL COMMENT '维度类型',
  `DIMENSIONPERMISSIONID` varchar(20) DEFAULT NULL COMMENT '维度权限ID',
  `ALLACCESS` varchar(1) DEFAULT NULL COMMENT '是否具有该维度所有权限',
  `SYSPATH` varchar(50) DEFAULT NULL COMMENT '系统标识',
  PRIMARY KEY (`DIMENSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维度数据权限表';

-- ----------------------------
-- Table structure for tafield
-- ----------------------------
DROP TABLE IF EXISTS `tafield`;
CREATE TABLE `tafield` (
  `ID` decimal(10,0) NOT NULL COMMENT '主键',
  `MENUID` decimal(10,0) DEFAULT NULL COMMENT '菜单ID',
  `FIELDID` varchar(100) DEFAULT NULL COMMENT '字段id',
  `FIELDNAME` varchar(100) DEFAULT NULL COMMENT '字段名称',
  `TABLEID` varchar(10) DEFAULT NULL COMMENT '表id(备用)',
  `PID` decimal(10,0) DEFAULT NULL COMMENT '父节点Id',
  `FIELDLEVEL` decimal(65,30) DEFAULT NULL COMMENT '字段层级'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tafieldauthrity
-- ----------------------------
DROP TABLE IF EXISTS `tafieldauthrity`;
CREATE TABLE `tafieldauthrity` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单id',
  `FIELDID` varchar(100) NOT NULL COMMENT '字段id',
  `LOOK` char(1) DEFAULT NULL COMMENT '是否可查看（1：可查看，0不可查看）',
  `EDIT` char(1) DEFAULT NULL COMMENT '是否可编辑（1：可编辑，0，不可查看）',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATEUSER` decimal(10,0) DEFAULT NULL COMMENT '创建人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for talimitrate
-- ----------------------------
DROP TABLE IF EXISTS `talimitrate`;
CREATE TABLE `talimitrate` (
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单id',
  `LIMITOPEN` char(1) NOT NULL COMMENT '是否开启限流',
  `RATE` decimal(16,2) NOT NULL COMMENT '允许的访问频率',
  `TIMEOUT` decimal(65,30) NOT NULL COMMENT '获取访问许可的超时时间',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='访问限流';

-- ----------------------------
-- Table structure for talocalcacheversion
-- ----------------------------
DROP TABLE IF EXISTS `talocalcacheversion`;
CREATE TABLE `talocalcacheversion` (
  `VERSION` decimal(11,0) NOT NULL COMMENT '版本号',
  `CODETYPE` varchar(20) DEFAULT NULL COMMENT '改动的type'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='本地缓存码表版本号';

-- ----------------------------
-- Table structure for taloginhistorylog
-- ----------------------------
DROP TABLE IF EXISTS `taloginhistorylog`;
CREATE TABLE `taloginhistorylog` (
  `LOGID` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(10) NOT NULL,
  `logintime` date NOT NULL,
  `logouttime` date NOT NULL,
  `clientip` varchar(200) NOT NULL,
  `sessionid` varchar(200) NOT NULL,
  `serverip` varchar(200) DEFAULT NULL,
  `syspath` varchar(50) DEFAULT NULL,
  `clientsystem` varchar(50) DEFAULT NULL,
  `clientbrowser` varchar(50) DEFAULT NULL,
  `clientscreensize` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tamanagermg
-- ----------------------------
DROP TABLE IF EXISTS `tamanagermg`;
CREATE TABLE `tamanagermg` (
  `POSITIONID` decimal(10,0) DEFAULT NULL COMMENT '岗位id',
  `ORGID` decimal(10,0) DEFAULT NULL COMMENT '组织id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织负责人（副职）管理表';

-- ----------------------------
-- Table structure for tamenu
-- ----------------------------
DROP TABLE IF EXISTS `tamenu`;
CREATE TABLE `tamenu` (
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单Id',
  `PMENUID` decimal(10,0) NOT NULL COMMENT '父菜单id',
  `MENUNAME` varchar(60) DEFAULT NULL COMMENT '功能地址',
  `URL` varchar(100) DEFAULT NULL COMMENT '功能名称',
  `MENUIDPATH` text COMMENT '菜单id路径',
  `MENUNAMEPATH` text COMMENT '菜单名称路径',
  `ICONSKIN` varchar(200) DEFAULT NULL COMMENT '选择前图片',
  `SELECTIMAGE` varchar(200) DEFAULT NULL COMMENT '选择后图片',
  `REPORTID` varchar(50) DEFAULT NULL COMMENT '查询报表的报表id',
  `ACCESSTIMEEL` varchar(200) DEFAULT NULL COMMENT '访问限制时间表达式',
  `EFFECTIVE` char(1) NOT NULL COMMENT '有效标志',
  `SECURITYPOLICY` char(1) NOT NULL COMMENT '安全策略',
  `ISDISMULTIPOS` char(1) NOT NULL COMMENT '是否显示多岗',
  `QUICKCODE` varchar(20) DEFAULT NULL COMMENT '快捷访问码',
  `SORTNO` decimal(65,30) DEFAULT NULL COMMENT '排序号',
  `RESOURCETYPE` char(2) NOT NULL COMMENT '权限类型（功能权限\\按钮权限\\表单只读\\表单可编辑\\表格列只读\\表格列可编辑）',
  `MENULEVEL` decimal(65,30) DEFAULT NULL COMMENT '菜单层级',
  `ISLEAF` char(1) DEFAULT NULL COMMENT '是否叶子节点',
  `MENUTYPE` char(1) DEFAULT NULL COMMENT '菜单类型',
  `ISCACHE` char(1) DEFAULT NULL COMMENT '是否缓存',
  `SYSPATH` varchar(20) DEFAULT NULL COMMENT '系统路径',
  `USEYAB003` char(1) DEFAULT NULL COMMENT '是否启用分中心数据权限',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT '类标识',
  `ISAUDITE` varchar(1) DEFAULT NULL COMMENT '是否需要审核',
  `CONSOLEMODULE` char(1) DEFAULT NULL COMMENT '是否为工作台模块',
  `CUSTOMENCODING` varchar(20) DEFAULT NULL COMMENT '自定义编码',
  `ISFILEDSCONTROL` char(1) DEFAULT NULL COMMENT '是否需要字段权限控制',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能菜单';

-- ----------------------------
-- Table structure for tamenupositionyab003
-- ----------------------------
DROP TABLE IF EXISTS `tamenupositionyab003`;
CREATE TABLE `tamenupositionyab003` (
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单id',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `YAB003` varchar(6) NOT NULL COMMENT '分中心',
  PRIMARY KEY (`MENUID`,`POSITIONID`,`YAB003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能数据权限';

-- ----------------------------
-- Table structure for tamessagestate
-- ----------------------------
DROP TABLE IF EXISTS `tamessagestate`;
CREATE TABLE `tamessagestate` (
  `MGID` varchar(20) NOT NULL COMMENT '消息id',
  `USERID` decimal(10,0) NOT NULL COMMENT '接收人员',
  `STATE` varchar(6) DEFAULT NULL COMMENT '已读标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for taonlinelog
-- ----------------------------
DROP TABLE IF EXISTS `taonlinelog`;
CREATE TABLE `taonlinelog` (
  `LOGID` int(15) NOT NULL AUTO_INCREMENT,
  `USERID` decimal(10,0) NOT NULL,
  `LOGINTIME` datetime NOT NULL,
  `CURRESOURCE` text,
  `CLIENTIP` varchar(200) NOT NULL,
  `SESSIONID` varchar(200) NOT NULL,
  `SYSPATH` varchar(50) DEFAULT NULL,
  `SERVERIP` varchar(200) DEFAULT NULL,
  `CLIENTSYSTEM` varchar(50) DEFAULT NULL,
  `CLIENTBROWSER` varchar(50) DEFAULT NULL,
  `CLIENTSCREENSIZE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB AUTO_INCREMENT=198382 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for taonlinestatlog
-- ----------------------------
DROP TABLE IF EXISTS `taonlinestatlog`;
CREATE TABLE `taonlinestatlog` (
  `STATDATE` varchar(20) NOT NULL COMMENT '统计日期',
  `POINTINTIME` varchar(20) NOT NULL COMMENT '统计时间点',
  `LOGINNUM` decimal(15,0) NOT NULL COMMENT '在线人数',
  PRIMARY KEY (`STATDATE`,`POINTINTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='时点登录情况统计表';

-- ----------------------------
-- Table structure for taorg
-- ----------------------------
DROP TABLE IF EXISTS `taorg`;
CREATE TABLE `taorg` (
  `ORGID` decimal(10,0) NOT NULL COMMENT '组织id',
  `PORGID` decimal(10,0) DEFAULT NULL COMMENT '组织父id',
  `ORGNAME` varchar(60) DEFAULT NULL COMMENT '组织名称',
  `COSTOMNO` varchar(10) DEFAULT NULL COMMENT '自定义编码',
  `ORGIDPATH` text COMMENT '组织id路径',
  `ORGNAMEPATH` text COMMENT '组织名称路径',
  `COSTOMNOPATH` text COMMENT '自定义编码路径',
  `ORGTYPE` char(2) DEFAULT NULL COMMENT '组织类型（机构（集团、子公司）、部门、组）',
  `SORT` decimal(65,30) DEFAULT NULL COMMENT '排序号',
  `YAB003` varchar(6) DEFAULT NULL COMMENT '经办机构',
  `DIMENSION` char(2) DEFAULT NULL COMMENT '视图维度',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `EFFECTIVE` char(1) NOT NULL COMMENT '有效性',
  `ORGLEVEL` decimal(10,0) DEFAULT NULL COMMENT '组织层级',
  `ISLEAF` char(1) DEFAULT NULL COMMENT '是否叶子节点',
  `ORGMANAGER` decimal(10,0) DEFAULT NULL COMMENT '组织负责人（正职）',
  `DESTORY` char(1) DEFAULT NULL COMMENT '是否销毁',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT '类标识',
  `YAB139` varchar(6) DEFAULT NULL COMMENT '数据区',
  PRIMARY KEY (`ORGID`),
  KEY `FK_REFERENCE_6` (`PORGID`),
  CONSTRAINT `FK_REFERENCE_6` FOREIGN KEY (`PORGID`) REFERENCES `taorg` (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织视图';

-- ----------------------------
-- Table structure for taorgmg
-- ----------------------------
DROP TABLE IF EXISTS `taorgmg`;
CREATE TABLE `taorgmg` (
  `POSITIONID` decimal(10,0) DEFAULT NULL COMMENT '岗位id',
  `ORGID` decimal(10,0) DEFAULT NULL COMMENT '组织id',
  KEY `FK_REFERENCE_8` (`ORGID`),
  KEY `FK_REFERENCE_9` (`POSITIONID`),
  CONSTRAINT `FK_REFERENCE_8` FOREIGN KEY (`ORGID`) REFERENCES `taorg` (`ORGID`),
  CONSTRAINT `FK_REFERENCE_9` FOREIGN KEY (`POSITIONID`) REFERENCES `taposition` (`POSITIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织管理表';

-- ----------------------------
-- Table structure for taorgoplog
-- ----------------------------
DROP TABLE IF EXISTS `taorgoplog`;
CREATE TABLE `taorgoplog` (
  `LOGID` int(10) NOT NULL AUTO_INCREMENT COMMENT '日志id',
  `BATCHNO` varchar(10) DEFAULT NULL COMMENT '操作批次号',
  `OPTYPE` char(2) DEFAULT NULL COMMENT '操作类型（新增、编辑、删除、授权、回收权限）',
  `INFLUENCEBODYTYPE` char(2) DEFAULT NULL COMMENT '影响主体类型（组织、人员、岗位、角色、权限资源）',
  `INFLUENCEBODY` varchar(10) DEFAULT NULL COMMENT '影响主体',
  `OPBODY` char(2) DEFAULT NULL COMMENT '操作主体类型（人员、组织、岗位、角色、权限资源）',
  `OPSUBJEKT` varchar(10) DEFAULT NULL COMMENT '操作主体',
  `CHANGCONTENT` text COMMENT '主体变更内容',
  `OPTIME` datetime NOT NULL COMMENT '经办时间',
  `OPUSER` varchar(10) NOT NULL COMMENT '经办人',
  `OPPOSITION` varchar(10) NOT NULL COMMENT '经办岗位',
  `ISPERMISSION` varchar(1) DEFAULT NULL COMMENT '是否有权限',
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB AUTO_INCREMENT=195397 DEFAULT CHARSET=utf8 COMMENT='组织及权限操作日志';

-- ----------------------------
-- Table structure for taposition
-- ----------------------------
DROP TABLE IF EXISTS `taposition`;
CREATE TABLE `taposition` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `ORGID` decimal(10,0) NOT NULL COMMENT '组织id',
  `POSITIONNAME` varchar(60) NOT NULL COMMENT '岗位名称',
  `POSITIONTYPE` char(1) NOT NULL COMMENT '岗位类型（个人专属岗位/公有岗位）',
  `CREATEPOSITIONID` decimal(10,0) NOT NULL COMMENT '创建人使用的岗位',
  `ORGIDPATH` text COMMENT '所在组织id路径',
  `ORGNAMEPATH` text COMMENT '所在组织名称路径',
  `VALIDTIME` datetime DEFAULT NULL COMMENT '只针对委派生成的岗位',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `EFFECTIVE` char(1) NOT NULL COMMENT '有效标志',
  `ISADMIN` char(1) DEFAULT NULL COMMENT '是否管理员',
  `ISSHARE` char(1) DEFAULT NULL COMMENT '是否为共享岗位',
  `ISCOPY` char(1) DEFAULT NULL COMMENT '是否为复制岗位',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT '类标识',
  `POSITIONCATEGORY` varchar(2) DEFAULT NULL COMMENT '岗位类别',
  PRIMARY KEY (`POSITIONID`),
  KEY `FK_RELATIONSHIP_5` (`ORGID`),
  CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`ORGID`) REFERENCES `taorg` (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位';

-- ----------------------------
-- Table structure for tapositionauthrity
-- ----------------------------
DROP TABLE IF EXISTS `tapositionauthrity`;
CREATE TABLE `tapositionauthrity` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `MENUID` decimal(10,0) NOT NULL COMMENT '菜单Id',
  `USEPERMISSION` char(1) DEFAULT NULL COMMENT '使用权限',
  `REPERMISSION` char(1) DEFAULT NULL COMMENT '授权别人使用权限',
  `REAUTHRITY` char(1) DEFAULT NULL COMMENT '授权别人授权权限',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `EFFECTTIME` datetime DEFAULT NULL COMMENT '有效时间',
  `AUDITEACCESSDATE` datetime DEFAULT NULL COMMENT '审核通过时间',
  `AUDITEUSER` decimal(10,0) DEFAULT NULL COMMENT '审核人',
  `AUDITSTATE` varchar(1) DEFAULT NULL COMMENT '审核状态',
  PRIMARY KEY (`POSITIONID`,`MENUID`),
  KEY `FK_REFERENCE_7` (`MENUID`),
  CONSTRAINT `FK_REFERENCE_7` FOREIGN KEY (`MENUID`) REFERENCES `tamenu` (`MENUID`),
  CONSTRAINT `FK_RELATIONSHIP_11` FOREIGN KEY (`POSITIONID`) REFERENCES `taposition` (`POSITIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='岗位权限表';

-- ----------------------------
-- Table structure for tarunqianad52reference
-- ----------------------------
DROP TABLE IF EXISTS `tarunqianad52reference`;
CREATE TABLE `tarunqianad52reference` (
  `MENUID` decimal(10,0) DEFAULT NULL COMMENT '功能编号',
  `RAQFILENAME` varchar(200) DEFAULT NULL COMMENT '文件名/报表标识（RaqfileName）',
  `LIMITED` decimal(65,30) DEFAULT NULL COMMENT '每页显示数（Limited）',
  `SCALEEXP` decimal(65,30) DEFAULT NULL COMMENT 'JSP中缩放比率（ScaleExp）',
  `ISGROUP` varchar(6) DEFAULT NULL COMMENT '是否按行分页（IsGroup）',
  `NEEDSAVEASEXCEL` varchar(6) DEFAULT NULL COMMENT '是否保存为Excel（NeedSaveAsExcel）',
  `NEEDSAVEASEXCEL2007` varchar(6) DEFAULT NULL COMMENT '是否保存为Excel2007（NeedSaveAsExcel2007）',
  `NEEDSAVEASPDF` varchar(6) DEFAULT NULL COMMENT '是否保存为Pdf（NeedSaveAsPdf）',
  `NEEDSAVEASWORD` varchar(6) DEFAULT NULL COMMENT '是否保存为Word（NeedSaveAsWord）',
  `NEEDSAVEASTEXT` varchar(6) DEFAULT NULL COMMENT '是否保存为Text（NeedSaveAsText）',
  `NEEDPRINT` varchar(6) DEFAULT NULL COMMENT '是否保存为Print（NeedPrint）',
  `ID` decimal(10,0) NOT NULL COMMENT '主键ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='YHCIP_RUNQIAN_AD52_REFERENCE润乾报表菜单信息';

-- ----------------------------
-- Table structure for tarunqianprintsetup
-- ----------------------------
DROP TABLE IF EXISTS `tarunqianprintsetup`;
CREATE TABLE `tarunqianprintsetup` (
  `SETUPID` varchar(200) NOT NULL COMMENT '打印设置编号（SetupId）',
  `SETUPVALUE` text NOT NULL COMMENT '打印设置信息（SetupValue）',
  PRIMARY KEY (`SETUPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='打印设置信息表';

-- ----------------------------
-- Table structure for tarunqianresource
-- ----------------------------
DROP TABLE IF EXISTS `tarunqianresource`;
CREATE TABLE `tarunqianresource` (
  `RAQFILENAME` varchar(200) NOT NULL COMMENT '文件名/报表标识（RaqfileName）',
  `PARENTRAQFILENAME` varchar(200) DEFAULT NULL COMMENT '父报表标识（ParentRaqfileName）',
  `RAQNAME` varchar(200) DEFAULT NULL COMMENT '报表名称（RaqName）',
  `RAQTYPE` varchar(6) DEFAULT NULL COMMENT '报表类型（RaqType）',
  `RAQFILE` longblob COMMENT '资源文件（RaqFile）',
  `UPLOADOR` varchar(19) DEFAULT NULL COMMENT '上传人（Uploador）',
  `UPLOADTIME` datetime DEFAULT NULL COMMENT '上传时间（UploadTime）',
  `SUBROW` decimal(65,30) DEFAULT NULL COMMENT '父报表位置行（SubRow）',
  `SUBCELL` decimal(65,30) DEFAULT NULL COMMENT '父报表位置列（SubCell）',
  `RAQDATASOURCE` varchar(19) DEFAULT NULL COMMENT '数据源（RaqDataSource）',
  `RAQPARAM` text COMMENT '报表参数JSON格式Str（RaqParam）',
  `ORGID` varchar(15) DEFAULT NULL COMMENT '部门编号(OrgId)',
  PRIMARY KEY (`RAQFILENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='润乾报表模板';

-- ----------------------------
-- Table structure for taserverexceptionlog
-- ----------------------------
DROP TABLE IF EXISTS `taserverexceptionlog`;
CREATE TABLE `taserverexceptionlog` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `IPADDRESS` varchar(255) DEFAULT NULL COMMENT '服务器ip地址',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '异常类型',
  `CONTENT` longblob COMMENT '异常内容',
  `TIME` datetime DEFAULT NULL COMMENT '报错时间',
  `SYSPATH` varchar(50) DEFAULT NULL COMMENT '系统标识',
  `CLIENTIP` varchar(50) DEFAULT NULL COMMENT '客户端ip',
  `URL` varchar(100) DEFAULT NULL COMMENT '访问功能url',
  `MENUID` varchar(8) DEFAULT NULL COMMENT '菜单id',
  `MENUNAME` varchar(30) DEFAULT NULL COMMENT '菜单名称',
  `USERAGENT` varchar(200) DEFAULT NULL COMMENT '客户端环境',
  `EXCEPTIONTYPE` varchar(2) DEFAULT NULL COMMENT '异常分类(1 系统异常  2业务异常)',
  `PARAMETER` longblob COMMENT '传入参数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统异常日志表';

-- ----------------------------
-- Table structure for tashareposition
-- ----------------------------
DROP TABLE IF EXISTS `tashareposition`;
CREATE TABLE `tashareposition` (
  `SPOSITIONID` decimal(10,0) DEFAULT NULL COMMENT '源岗位id',
  `DPOSITIONID` decimal(10,0) DEFAULT NULL COMMENT '复制岗位id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='共享岗位表';

-- ----------------------------
-- Table structure for tauser
-- ----------------------------
DROP TABLE IF EXISTS `tauser`;
CREATE TABLE `tauser` (
  `USERID` int(10) NOT NULL AUTO_INCREMENT COMMENT '人员id',
  `NAME` varchar(60) NOT NULL COMMENT '姓名',
  `SEX` char(1) DEFAULT NULL COMMENT '性别',
  `LOGINID` varchar(20) NOT NULL COMMENT '登陆账号',
  `PASSWORD` varchar(50) NOT NULL COMMENT '密码',
  `PASSWORDFAULTNUM` decimal(65,30) DEFAULT NULL COMMENT '口令错误次数',
  `PWDLASTMODIFYDATE` datetime DEFAULT NULL COMMENT '口令最后修改时间',
  `ISLOCK` char(1) DEFAULT NULL COMMENT '锁定标志',
  `SORT` decimal(65,30) DEFAULT NULL COMMENT '排序号',
  `EFFECTIVE` char(1) NOT NULL COMMENT '有效标志',
  `TEL` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `CREATEUSER` decimal(10,0) DEFAULT NULL COMMENT '创建人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  `DIRECTORGID` decimal(10,0) NOT NULL COMMENT '直属组织',
  `DESTORY` char(1) DEFAULT NULL COMMENT '是否销毁',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT '类标识',
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=195009 DEFAULT CHARSET=utf8 COMMENT='人员';

-- ----------------------------
-- Table structure for tauserposition
-- ----------------------------
DROP TABLE IF EXISTS `tauserposition`;
CREATE TABLE `tauserposition` (
  `USERID` int(10) NOT NULL COMMENT '人员id',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT '岗位id',
  `MAINPOSITION` char(1) NOT NULL COMMENT '默认岗位',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT '创建人',
  `CREATETIME` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`USERID`,`POSITIONID`),
  KEY `FK_RELATIONSHIP_10` (`POSITIONID`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`POSITIONID`) REFERENCES `taposition` (`POSITIONID`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`USERID`) REFERENCES `tauser` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员岗位关系表';

-- ----------------------------
-- Table structure for tayab003levelmg
-- ----------------------------
DROP TABLE IF EXISTS `tayab003levelmg`;
CREATE TABLE `tayab003levelmg` (
  `PYAB003` varchar(6) DEFAULT NULL COMMENT '父经办机构',
  `YAB003` varchar(6) DEFAULT NULL COMMENT '经办机构'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经办机构层级关系管理';

-- ----------------------------
-- Table structure for tayab003scope
-- ----------------------------
DROP TABLE IF EXISTS `tayab003scope`;
CREATE TABLE `tayab003scope` (
  `YAB003` varchar(6) NOT NULL COMMENT '分中心',
  `YAB139` varchar(6) NOT NULL COMMENT '分中心数据权限',
  PRIMARY KEY (`YAB003`,`YAB139`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分中心数据权限范围表';

-- ----------------------------
-- Table structure for tayab139mg
-- ----------------------------
DROP TABLE IF EXISTS `tayab139mg`;
CREATE TABLE `tayab139mg` (
  `YAB003` varchar(6) DEFAULT NULL COMMENT '经办机构',
  `YAB139` varchar(20) DEFAULT NULL COMMENT '数据区'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='经办机构管理数据区范围';

-- ----------------------------
-- Table structure for ud01
-- ----------------------------
DROP TABLE IF EXISTS `ud01`;
CREATE TABLE `ud01` (
  `YUD010` varchar(20) NOT NULL COMMENT '档案人员信息序',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  `AAC001` varchar(20) NOT NULL COMMENT '个人编号',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAC003` varchar(50) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(4) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `AAC006` datetime DEFAULT NULL COMMENT '出生年月',
  `YUD011` varchar(6) DEFAULT NULL COMMENT '是否在职',
  `YUD012` varchar(6) DEFAULT NULL COMMENT '是否兼职',
  `YUD013` varchar(6) DEFAULT NULL COMMENT '文化程度',
  `YUD014` varchar(6) DEFAULT NULL COMMENT '档案专业程度',
  `YUD015` varchar(6) DEFAULT NULL COMMENT '档案干部专业技术职务',
  `YUD016` varchar(6) DEFAULT NULL COMMENT '接受在职培训教育',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT '最后修改人部门',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE013` text COMMENT '备注说明',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUD010`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案工作人员信息登记';

-- ----------------------------
-- Table structure for ud02
-- ----------------------------
DROP TABLE IF EXISTS `ud02`;
CREATE TABLE `ud02` (
  `YUD020` varchar(20) NOT NULL COMMENT '档案室设备类型ID',
  `YUD021` varchar(50) DEFAULT NULL COMMENT '档案室设备类型名称',
  `YUD022` varchar(6) DEFAULT NULL COMMENT '档案室设备类别ID',
  `YUD023` varchar(50) DEFAULT NULL COMMENT '档案室设备类别名称',
  `YUD024` varchar(6) DEFAULT NULL COMMENT '设备记录类型',
  `YUD025` varchar(6) DEFAULT NULL COMMENT '设备记录单位',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT '最后修改人部门',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE013` text COMMENT '备注说明',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUD020`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案使用设备类型管理';

-- ----------------------------
-- Table structure for ud03
-- ----------------------------
DROP TABLE IF EXISTS `ud03`;
CREATE TABLE `ud03` (
  `YUD030` varchar(20) NOT NULL COMMENT '档案室设备登记序列号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  `YUD031` varchar(100) DEFAULT NULL COMMENT '设备名称',
  `YUD020` varchar(20) DEFAULT NULL COMMENT '档案设备类型',
  `YUD022` varchar(20) DEFAULT NULL COMMENT '档案设备类别',
  `YUD032` varchar(100) DEFAULT NULL COMMENT '设备编号',
  `YUD033` varchar(100) DEFAULT NULL COMMENT '设备描述',
  `YUD034` varchar(6) DEFAULT NULL COMMENT '是否正常工作',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT '最后修改人部门',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE013` text COMMENT '备注说明',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUD030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案室设备登记记录';

-- ----------------------------
-- Table structure for ud10
-- ----------------------------
DROP TABLE IF EXISTS `ud10`;
CREATE TABLE `ud10` (
  `YUD101` varchar(20) NOT NULL COMMENT 'id',
  `YUD102` decimal(65,30) DEFAULT NULL COMMENT '档案机构',
  `YUD103` decimal(65,30) DEFAULT NULL COMMENT '专职人员',
  `YUD104` decimal(65,30) DEFAULT NULL COMMENT '专职人员女性',
  `YUD105` decimal(65,30) DEFAULT NULL COMMENT '50岁及以上',
  `YUD106` decimal(65,30) DEFAULT NULL COMMENT '35—49岁',
  `YUD107` decimal(65,30) DEFAULT NULL COMMENT '34岁及以下',
  `YUD108` decimal(65,30) DEFAULT NULL COMMENT '博士研究生-文化',
  `YUD109` decimal(65,30) DEFAULT NULL COMMENT '硕士研究生-文化',
  `YUD10A` decimal(65,30) DEFAULT NULL COMMENT '研究生班研究生-文化',
  `YUD10B` decimal(65,30) DEFAULT NULL COMMENT '双学士-文化',
  `YUD10C` decimal(65,30) DEFAULT NULL COMMENT '大学本科-文化',
  `YUD10D` decimal(65,30) DEFAULT NULL COMMENT '大专-文化',
  `YUD10E` decimal(65,30) DEFAULT NULL COMMENT '中专-文化',
  `YUD10F` decimal(65,30) DEFAULT NULL COMMENT '高中-文化',
  `YUD10G` decimal(65,30) DEFAULT NULL COMMENT '初中及以下-文化',
  `YUD10I` decimal(65,30) DEFAULT NULL COMMENT '博士研究生-专业',
  `YUD10J` decimal(65,30) DEFAULT NULL COMMENT '硕士研究生-专业',
  `YUD10K` decimal(65,30) DEFAULT NULL COMMENT '研究生班研究生-专业',
  `YUD10L` decimal(65,30) DEFAULT NULL COMMENT '大学本科-专业',
  `YUD10M` decimal(65,30) DEFAULT NULL COMMENT '大专-专业',
  `YUD10N` decimal(65,30) DEFAULT NULL COMMENT '中专-专业',
  `YUD10O` decimal(65,30) DEFAULT NULL COMMENT '职业高中-专业',
  `YUD10P` decimal(65,30) DEFAULT NULL COMMENT '研究馆员',
  `YUD10Q` decimal(65,30) DEFAULT NULL COMMENT '副研究馆员',
  `YUD10R` decimal(65,30) DEFAULT NULL COMMENT '馆员',
  `YUD10S` decimal(65,30) DEFAULT NULL COMMENT '助理馆员',
  `YUD10T` decimal(65,30) DEFAULT NULL COMMENT '管理员',
  `YUD10U` decimal(65,30) DEFAULT NULL COMMENT '接受在职培训教育',
  `YUD10V` decimal(65,30) DEFAULT NULL COMMENT '兼职人员',
  `YUD10W` decimal(65,30) DEFAULT NULL COMMENT '本年经费投入',
  `YUD10X` decimal(65,30) DEFAULT NULL COMMENT '全    宗',
  `YUD10Y` decimal(65,30) DEFAULT NULL COMMENT '案    卷',
  `YUD10Z` decimal(65,30) DEFAULT NULL COMMENT '案卷排架长度',
  `YUD110` decimal(65,30) DEFAULT NULL COMMENT '所有档案-卷',
  `YUD111` decimal(65,30) DEFAULT NULL COMMENT '所有档案-件',
  `YUD112` decimal(65,30) DEFAULT NULL COMMENT '永久保管-卷',
  `YUD113` decimal(65,30) DEFAULT NULL COMMENT '永久保管-件',
  `YUD114` decimal(65,30) DEFAULT NULL COMMENT '本年-永久-卷',
  `YUD115` decimal(65,30) DEFAULT NULL COMMENT '本年-永久-件',
  `YUD116` decimal(65,30) DEFAULT NULL COMMENT '本年-100年-卷',
  `YUD117` decimal(65,30) DEFAULT NULL COMMENT '本年-100年-件',
  `YUD118` decimal(65,30) DEFAULT NULL COMMENT '本年-50年-卷',
  `YUD119` decimal(65,30) DEFAULT NULL COMMENT '本年-50年-件',
  `YUD11A` decimal(65,30) DEFAULT NULL COMMENT '本年-30年-卷',
  `YUD11B` decimal(65,30) DEFAULT NULL COMMENT '本年-30年-件',
  `YUD11C` decimal(65,30) DEFAULT NULL COMMENT '本年-10年-卷',
  `YUD11D` decimal(65,30) DEFAULT NULL COMMENT '本年-10年-件',
  `YUD11E` decimal(65,30) DEFAULT NULL COMMENT '本年向综合档案馆移交档案-卷',
  `YUD11F` decimal(65,30) DEFAULT NULL COMMENT '本年向综合档案馆移交档案-件',
  `YUD11G` decimal(65,30) DEFAULT NULL COMMENT '本年移出档案-卷',
  `YUD11H` decimal(65,30) DEFAULT NULL COMMENT '本年移出档案-件',
  `YUD11I` decimal(65,30) DEFAULT NULL COMMENT '本年销毁档案-卷',
  `YUD11J` decimal(65,30) DEFAULT NULL COMMENT '本年销毁档案-件',
  `YUD11K` decimal(65,30) DEFAULT NULL COMMENT '机读目录-案卷级',
  `YUD11L` decimal(65,30) DEFAULT NULL COMMENT '机读目录-文件级',
  `YUD11M` decimal(65,30) DEFAULT NULL COMMENT '纸质目录-案卷目录',
  `YUD11N` decimal(65,30) DEFAULT NULL COMMENT '纸质目录-归档文件目录',
  `YUD11O` decimal(65,30) DEFAULT NULL COMMENT '本年利用档案-人',
  `YUD11P` decimal(65,30) DEFAULT NULL COMMENT '本年利用档案-卷',
  `YUD11Q` decimal(65,30) DEFAULT NULL COMMENT '本年利用档案-件',
  `YUD11R` decimal(65,30) DEFAULT NULL COMMENT '陈 列 室',
  `YUD11S` decimal(65,30) DEFAULT NULL COMMENT '公开出版-种',
  `YUD11T` decimal(65,30) DEFAULT NULL COMMENT '公开出版-万字',
  `YUD11U` decimal(65,30) DEFAULT NULL COMMENT '内部参考-种',
  `YUD11V` decimal(65,30) DEFAULT NULL COMMENT '内部参考-万字',
  `YUD11W` decimal(65,30) DEFAULT NULL COMMENT '档案室总建筑面积',
  `YUD11X` decimal(65,30) DEFAULT NULL COMMENT '档案库房建筑面积',
  `YUD11Y` decimal(65,30) DEFAULT NULL COMMENT '档案办公室建筑面积',
  `YUD11Z` decimal(65,30) DEFAULT NULL COMMENT '档案阅览室建筑面积',
  `YUD120` decimal(65,30) DEFAULT NULL COMMENT '密 集 架',
  `YUD121` decimal(65,30) DEFAULT NULL COMMENT '五 节 柜',
  `YUD122` decimal(65,30) DEFAULT NULL COMMENT '档 案 柜',
  `YUD123` decimal(65,30) DEFAULT NULL COMMENT '服 务 器',
  `YUD124` decimal(65,30) DEFAULT NULL COMMENT '微    机',
  `YUD125` decimal(65,30) DEFAULT NULL COMMENT '高    速',
  `YUD126` decimal(65,30) DEFAULT NULL COMMENT '平    板',
  `YUD127` decimal(65,30) DEFAULT NULL COMMENT '复 印 机',
  `YUD128` decimal(65,30) DEFAULT NULL COMMENT '集 中 式',
  `YUD129` decimal(65,30) DEFAULT NULL COMMENT '分 散 式',
  `YUD12A` decimal(65,30) DEFAULT NULL COMMENT '防盗报警系统-探   头',
  `YUD12B` decimal(65,30) DEFAULT NULL COMMENT '消防报警系统-探   头',
  `YUD12C` decimal(65,30) DEFAULT NULL COMMENT '手提灭火器',
  `YUD12D` decimal(65,30) DEFAULT NULL COMMENT '除（加）湿机',
  `YUD12E` decimal(65,30) DEFAULT NULL COMMENT '消毒设备',
  `YUD12F` decimal(65,30) DEFAULT NULL COMMENT '档案塑封机',
  `YUD12G` varchar(100) DEFAULT NULL COMMENT '地区',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `YUD12H` varchar(20) DEFAULT NULL COMMENT '年度',
  `YUD12I` varchar(100) DEFAULT NULL COMMENT '单位负责人',
  `YUD12J` varchar(100) DEFAULT NULL COMMENT '填表人',
  `YUD12K` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `YUD12L` datetime DEFAULT NULL COMMENT '填报日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社会保险业务档案管理基本情况统计表';

-- ----------------------------
-- Table structure for ue01
-- ----------------------------
DROP TABLE IF EXISTS `ue01`;
CREATE TABLE `ue01` (
  `YUE010` varchar(20) NOT NULL COMMENT '业务环节编号（三级类目）',
  `YUE012` varchar(200) DEFAULT NULL COMMENT '业务环节名称',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT '密级',
  `YUE013` varchar(18) DEFAULT NULL COMMENT '案卷类别编号（目录号）',
  `YUE014` varchar(200) DEFAULT NULL COMMENT '案卷类别名称（卷名）',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限',
  `YUE01B` varchar(6) DEFAULT NULL COMMENT '业务主体(0:单位或个人,1:单位,2:个人)',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `AAE000` varchar(6) DEFAULT NULL COMMENT '是否有效（0无效，1有效）',
  `YUE01D` varchar(200) DEFAULT NULL COMMENT '拼音',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUE010`),
  KEY `IDX_UE01_YUE012` (`YUE012`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案收集目录表';

-- ----------------------------
-- Table structure for ue02
-- ----------------------------
DROP TABLE IF EXISTS `ue02`;
CREATE TABLE `ue02` (
  `YUF001` varchar(6) NOT NULL COMMENT '档案对象类型',
  `YUF004` varchar(20) DEFAULT NULL COMMENT '档案对象名称',
  `YUE021` varchar(100) DEFAULT NULL COMMENT '对应的表名',
  `YUE022` varchar(100) DEFAULT NULL COMMENT '对应主键名称',
  `YUE023` varchar(20) DEFAULT NULL COMMENT 'Service名称',
  `YUE024` varchar(50) DEFAULT NULL COMMENT 'Service方法',
  `YUE025` varchar(6) DEFAULT NULL COMMENT '档案对象状态字段',
  `YUE026` varchar(6) DEFAULT NULL COMMENT '子档案对象类型',
  PRIMARY KEY (`YUF001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案对象辅助表';

-- ----------------------------
-- Table structure for ue03
-- ----------------------------
DROP TABLE IF EXISTS `ue03`;
CREATE TABLE `ue03` (
  `YUE030` varchar(50) NOT NULL COMMENT '业务材料编号',
  `YUE031` text COMMENT '业务材料名称',
  `YUE032` decimal(6,0) DEFAULT NULL COMMENT '默认页数',
  `YUE033` varchar(50) DEFAULT NULL COMMENT '原材料编号',
  `AAE100` varchar(1) DEFAULT NULL COMMENT '是否有效 0-无效 1-有效',
  `YUE035` varchar(6) DEFAULT NULL COMMENT '材料主体（01-个人，02-单位）',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE034` varchar(6) DEFAULT NULL COMMENT '材料编号前缀',
  `YUE211` varchar(20) DEFAULT NULL COMMENT '接入系统编码',
  PRIMARY KEY (`YUE030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案业务材料名称表';

-- ----------------------------
-- Table structure for ue04
-- ----------------------------
DROP TABLE IF EXISTS `ue04`;
CREATE TABLE `ue04` (
  `YUE040` varchar(25) NOT NULL COMMENT '关联流水号',
  `YUE010` varchar(25) NOT NULL COMMENT '业务环节流水号',
  `YUE030` varchar(50) NOT NULL COMMENT '附表流水号',
  `YUE041` decimal(6,0) NOT NULL COMMENT '附表序号',
  `YUE042` varchar(6) NOT NULL COMMENT '是否必须收集',
  `YUE043` varchar(6) NOT NULL COMMENT '是否主表',
  `YUE044` varchar(6) DEFAULT NULL COMMENT '影像是否必须提交',
  `YUE045` varchar(6) DEFAULT NULL COMMENT '是否原件',
  `YUE046` varchar(6) DEFAULT NULL COMMENT '是否保存实体',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUE047` varchar(6) DEFAULT NULL COMMENT '是否有实体材料',
  `YUE048` varchar(6) DEFAULT NULL COMMENT '是否必扫（0-否，1-是）',
  PRIMARY KEY (`YUE040`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主表附表关联';

-- ----------------------------
-- Table structure for ue05
-- ----------------------------
DROP TABLE IF EXISTS `ue05`;
CREATE TABLE `ue05` (
  `YUE050` varchar(25) NOT NULL COMMENT '流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE010` varchar(25) DEFAULT NULL COMMENT '业务环节流水号',
  `YUE052` varchar(20) DEFAULT NULL COMMENT '系统标识符',
  `YAE049` varchar(25) DEFAULT NULL COMMENT '业务功能流水号',
  `YAE04A` text COMMENT '业务功能名称',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务环节与业务功能对应表';

-- ----------------------------
-- Table structure for ue05a1
-- ----------------------------
DROP TABLE IF EXISTS `ue05a1`;
CREATE TABLE `ue05a1` (
  `YUE050` varchar(25) NOT NULL COMMENT '流水号',
  `YUE010` varchar(25) DEFAULT NULL COMMENT '业务环节流水号',
  `YAE049` varchar(25) DEFAULT NULL COMMENT '业务功能流水号',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YAE04A` text COMMENT '业务功能名称',
  `YUE051` varchar(6) DEFAULT NULL COMMENT '废弃标识',
  PRIMARY KEY (`YUE050`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务环节与业务功能对应表（已废弃记录）';

-- ----------------------------
-- Table structure for ue06
-- ----------------------------
DROP TABLE IF EXISTS `ue06`;
CREATE TABLE `ue06` (
  `YUE060` varchar(20) NOT NULL COMMENT '档案门类流水号',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `YUE061` varchar(50) NOT NULL COMMENT '档案门类名称',
  `YUE062` varchar(60) DEFAULT NULL COMMENT '对应表名',
  `YUE063` varchar(60) DEFAULT NULL COMMENT '代码服务名',
  `YUF001` varchar(6) NOT NULL COMMENT '业务数据采集时档案类型(2-案卷 3-文件 4-盒 5-箱）',
  `YUE064` varchar(6) NOT NULL COMMENT '启用状态（1：表示启用，0表示不启用）',
  `YUE065` varchar(6) DEFAULT NULL COMMENT '是否自动装盒',
  `YUE066` varchar(6) DEFAULT NULL COMMENT '装盒规格(2-案卷 3-文件 4-盒 5-箱）',
  `YUE067` varchar(6) DEFAULT NULL COMMENT '条码连续不断',
  `YUE068` varchar(6) DEFAULT NULL COMMENT '是否启用ES查询',
  `YUE069` varchar(20) DEFAULT NULL COMMENT 'ES索引名',
  `YUE06A` varchar(6) DEFAULT NULL COMMENT '门类所在档案大类（业务、财务、文书、项目、声像、人事、实物）',
  PRIMARY KEY (`YUE060`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案门类信息表';

-- ----------------------------
-- Table structure for ue07
-- ----------------------------
DROP TABLE IF EXISTS `ue07`;
CREATE TABLE `ue07` (
  `YUE070` varchar(15) NOT NULL COMMENT '保管期限流水号',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限代码',
  `YUE071` varchar(50) DEFAULT NULL COMMENT '保管期限名称',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE072` decimal(4,0) DEFAULT NULL COMMENT '期限年度',
  PRIMARY KEY (`YUE070`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保管期限信息表';

-- ----------------------------
-- Table structure for ue08
-- ----------------------------
DROP TABLE IF EXISTS `ue08`;
CREATE TABLE `ue08` (
  `YUE080` varchar(15) NOT NULL,
  `YUE0C1` varchar(6) DEFAULT NULL COMMENT '字段名称',
  `YUE0C2` varchar(50) DEFAULT NULL COMMENT '字段注释',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  PRIMARY KEY (`YUE080`),
  UNIQUE KEY `UK_YUE01C` (`YUE001`,`YUE0C1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则字段表';

-- ----------------------------
-- Table structure for ue09
-- ----------------------------
DROP TABLE IF EXISTS `ue09`;
CREATE TABLE `ue09` (
  `YUE090` varchar(20) DEFAULT NULL COMMENT '主键',
  `YUE091` varchar(20) DEFAULT NULL COMMENT '功能模块',
  `YUE092` varchar(20) DEFAULT NULL COMMENT '操作对象',
  `YUE093` varchar(20) DEFAULT NULL COMMENT '标准操作次数',
  `YUE094` varchar(20) DEFAULT NULL COMMENT '标准周期',
  `YUE095` varchar(20) DEFAULT NULL COMMENT '是否启用',
  `YUE001` varchar(20) DEFAULT NULL COMMENT '档案门类',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE012` varchar(20) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE018` varchar(20) DEFAULT NULL COMMENT '经办机构名称',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块按钮操作配置';

-- ----------------------------
-- Table structure for ue0a
-- ----------------------------
DROP TABLE IF EXISTS `ue0a`;
CREATE TABLE `ue0a` (
  `YUE0A0` varchar(15) NOT NULL,
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `YUE002` varchar(6) NOT NULL COMMENT '档案类别',
  `YUE0A1` varchar(6) NOT NULL COMMENT '案卷类别',
  `YUF00H` varchar(6) NOT NULL COMMENT '保管期限',
  `YUE013` varchar(12) NOT NULL COMMENT '目录号',
  `YUE014` varchar(200) NOT NULL COMMENT '卷名',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `AAE000` varchar(6) NOT NULL COMMENT '是否有效',
  PRIMARY KEY (`YUE0A0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案卷类别信息表';

-- ----------------------------
-- Table structure for ue0b
-- ----------------------------
DROP TABLE IF EXISTS `ue0b`;
CREATE TABLE `ue0b` (
  `YUE0B0` varchar(20) NOT NULL COMMENT '档案类别信息流水号',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `YUE002` varchar(6) NOT NULL COMMENT '档案类别',
  `YUE0B1` varchar(150) NOT NULL COMMENT '档案类别名称',
  PRIMARY KEY (`YUE0B0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案类别信息表';

-- ----------------------------
-- Table structure for ue0c
-- ----------------------------
DROP TABLE IF EXISTS `ue0c`;
CREATE TABLE `ue0c` (
  `YUE0C0` varchar(20) NOT NULL COMMENT '立卷规则信息流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE0C1` varchar(6) DEFAULT NULL COMMENT '规则涉及字段',
  `YUE0C2` varchar(50) DEFAULT NULL COMMENT '规则涉及字段名',
  `YUE0C3` varchar(6) DEFAULT NULL COMMENT '字段规则 = ！',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE000` varchar(1) DEFAULT NULL COMMENT '是否有效 0-无效 1-有效',
  `YUE0C4` varchar(6) DEFAULT NULL COMMENT '整体功能：立卷DALJ，装盒DAZH，入库DARK，取collection=YUT103',
  PRIMARY KEY (`YUE0C0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='立卷/入库规则信息表';

-- ----------------------------
-- Table structure for ue0d
-- ----------------------------
DROP TABLE IF EXISTS `ue0d`;
CREATE TABLE `ue0d` (
  `YAE092` varchar(15) NOT NULL COMMENT '操作人员编号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUE0D1` varchar(6) DEFAULT NULL COMMENT '所选扫描仪类型',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUE0D2` varchar(20) DEFAULT NULL COMMENT '所选高拍仪类型',
  `YUE0D3` varchar(20) DEFAULT NULL COMMENT '所选平板扫描仪类型',
  `YUE0D4` varchar(6) DEFAULT NULL COMMENT '操作人使用的设备类型 值仅限HS（高扫）、SS（高拍）、FS （平扫）',
  PRIMARY KEY (`YAE092`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='扫描仪使用情况信息';

-- ----------------------------
-- Table structure for ue0e
-- ----------------------------
DROP TABLE IF EXISTS `ue0e`;
CREATE TABLE `ue0e` (
  `YUF00Z` varchar(10) NOT NULL COMMENT '行政区划代码',
  `YUB005` varchar(10) DEFAULT NULL COMMENT '所属省级行政区划代码',
  `YUB004` varchar(10) DEFAULT NULL COMMENT '所属市级行政区划代码',
  `YUE0E2` varchar(10) DEFAULT NULL COMMENT '行政区划级别（1省、2市、3县）',
  `YUE0E3` varchar(200) DEFAULT NULL COMMENT '行政区划名称',
  PRIMARY KEY (`YUF00Z`),
  KEY `IDX_UE0E_01` (`YUB005`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全国行政区划';

-- ----------------------------
-- Table structure for ue0f
-- ----------------------------
DROP TABLE IF EXISTS `ue0f`;
CREATE TABLE `ue0f` (
  `YUE0F0` varchar(20) DEFAULT NULL COMMENT '主键',
  `YUE091` varchar(20) DEFAULT NULL COMMENT '功能模块',
  `YUE0F2` varchar(20) DEFAULT NULL COMMENT '前置条件',
  `YUE0F3` varchar(20) DEFAULT NULL COMMENT '前置条件状态',
  `YUE0F4` varchar(20) DEFAULT NULL COMMENT '是否启用',
  `YUE001` varchar(20) DEFAULT NULL COMMENT '档案门类',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE012` varchar(20) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE018` varchar(20) DEFAULT NULL COMMENT '经办机构名称',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程配置关系表';

-- ----------------------------
-- Table structure for ue0g
-- ----------------------------
DROP TABLE IF EXISTS `ue0g`;
CREATE TABLE `ue0g` (
  `YUB004` varchar(6) NOT NULL COMMENT '市级行政区划代码',
  `YUE0E3` varchar(50) DEFAULT NULL COMMENT '行政区划名称',
  `YUB005` varchar(6) DEFAULT NULL COMMENT '所属省级行政区划代码',
  `YUE0G2` varchar(100) DEFAULT NULL COMMENT '所属省名称',
  `ORGID` varchar(10) DEFAULT NULL COMMENT '在组织机构中的编号',
  PRIMARY KEY (`YUB004`),
  UNIQUE KEY `UNIQ_UE0G` (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='市级机构信息';

-- ----------------------------
-- Table structure for ue0h
-- ----------------------------
DROP TABLE IF EXISTS `ue0h`;
CREATE TABLE `ue0h` (
  `YUE0H0` varchar(25) NOT NULL COMMENT '全宗信息流水号',
  `ORGID` varchar(10) NOT NULL COMMENT '机构Id',
  `YUB001` varchar(6) NOT NULL COMMENT '内部全宗号',
  `YUE0H1` varchar(10) DEFAULT NULL COMMENT '实际全宗号',
  `YUE0H2` varchar(200) NOT NULL COMMENT '全宗名称',
  `PORGID` varchar(10) NOT NULL COMMENT '父机构ID',
  `YUB004` varchar(6) NOT NULL COMMENT '所属市级单位',
  `YUE0H3` varchar(6) DEFAULT NULL COMMENT '条码上全宗代码',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUF00Z` varchar(10) DEFAULT NULL COMMENT '行政区划代码',
  `YUE0H4` varchar(6) DEFAULT NULL COMMENT '全宗是否到区县',
  PRIMARY KEY (`YUE0H0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全宗信息表';

-- ----------------------------
-- Table structure for ue0j
-- ----------------------------
DROP TABLE IF EXISTS `ue0j`;
CREATE TABLE `ue0j` (
  `YUE0J0` varchar(20) NOT NULL COMMENT '复用材料流水号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '材料编号',
  `YUE031` varchar(100) DEFAULT NULL COMMENT '材料名称',
  `YUE035` varchar(6) DEFAULT NULL COMMENT '材料主体(01:个人，02-单位)',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '是否有效',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUE0J0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复用材料信息';

-- ----------------------------
-- Table structure for ue0k
-- ----------------------------
DROP TABLE IF EXISTS `ue0k`;
CREATE TABLE `ue0k` (
  `YUE0K0` varchar(20) NOT NULL COMMENT '授权流水号',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '材料编码',
  `YUE031` text COMMENT '材料名称',
  `YUE0K1` decimal(10,0) DEFAULT NULL COMMENT '组织id',
  `YUE0K2` decimal(10,0) DEFAULT NULL COMMENT '人员id',
  `YUE0K3` varchar(60) DEFAULT NULL COMMENT '姓名',
  `YUE0K4` varchar(60) DEFAULT NULL COMMENT '权限信息',
  `YUE0K5` datetime DEFAULT NULL COMMENT '权限开始时间',
  `YUE0K6` datetime DEFAULT NULL COMMENT '权限结束时间',
  `AAA100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE011` decimal(10,0) DEFAULT NULL COMMENT '经办人id',
  `AAE012` varchar(60) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` decimal(10,0) DEFAULT NULL COMMENT '经办机构id',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUE0K0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密级材料授权表';

-- ----------------------------
-- Table structure for ue0m
-- ----------------------------
DROP TABLE IF EXISTS `ue0m`;
CREATE TABLE `ue0m` (
  `YUE0M0` varchar(50) NOT NULL COMMENT '密级材料流水号',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '材料编号',
  `YUE031` varchar(100) DEFAULT NULL COMMENT '材料名称',
  `YUF00I` varchar(20) DEFAULT NULL COMMENT '材料密级',
  `YAE049` varchar(20) DEFAULT NULL COMMENT '业务功能编号',
  `YAE04A` text COMMENT '业务功能名称',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `AAE100` varchar(50) DEFAULT NULL COMMENT '有效标志',
  PRIMARY KEY (`YUE0M0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='涉密材料配置信息';

-- ----------------------------
-- Table structure for ue0n
-- ----------------------------
DROP TABLE IF EXISTS `ue0n`;
CREATE TABLE `ue0n` (
  `YUE0N0` varchar(20) NOT NULL COMMENT '使用复用的机构信息流水号',
  `ORGID` varchar(50) DEFAULT NULL COMMENT '机构id',
  `ORGNAME` varchar(100) DEFAULT NULL COMMENT '机构名称',
  `ORGPATH` varchar(100) DEFAULT NULL COMMENT '机构路径',
  `YUE0N1` varchar(6) DEFAULT NULL COMMENT '是否使用复用功能',
  `YUE0N2` varchar(6) DEFAULT NULL COMMENT '是否写入复用影像库',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构ID',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  PRIMARY KEY (`YUE0N0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='使用复用的机构信息';

-- ----------------------------
-- Table structure for ue0p
-- ----------------------------
DROP TABLE IF EXISTS `ue0p`;
CREATE TABLE `ue0p` (
  `YUE0P0` varchar(20) NOT NULL COMMENT '业务功能登记流水号',
  `YAE049` varchar(50) DEFAULT NULL COMMENT '业务功能编号',
  `YAE04A` varchar(100) DEFAULT NULL COMMENT '业务功能名称',
  `YUE211` varchar(20) DEFAULT NULL COMMENT '业务系统编码',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构ID',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  PRIMARY KEY (`YUE0P0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务功能登记信息';

-- ----------------------------
-- Table structure for ue0q
-- ----------------------------
DROP TABLE IF EXISTS `ue0q`;
CREATE TABLE `ue0q` (
  `YUE0Q0` varchar(20) NOT NULL COMMENT '财务账套会计科目登记流水号',
  `YUE0Q1` decimal(4,0) DEFAULT NULL COMMENT '账套会计科目年度',
  `YUE0Q2` varchar(20) DEFAULT NULL COMMENT '账套代码',
  `YUE0Q3` varchar(100) DEFAULT NULL COMMENT '账套名称',
  `YUE0Q4` varchar(20) DEFAULT NULL COMMENT '会计科目代码',
  `YUE0Q5` varchar(200) DEFAULT NULL COMMENT '会计科目名称',
  `YUE0Q6` text COMMENT '会计科目全称',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构ID',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  PRIMARY KEY (`YUE0Q0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='财务账套会计科目配置信息';

-- ----------------------------
-- Table structure for ue0r
-- ----------------------------
DROP TABLE IF EXISTS `ue0r`;
CREATE TABLE `ue0r` (
  `YUE0R0` varchar(20) NOT NULL COMMENT '全宗管理门类配置信息',
  `YUE0H0` varchar(25) NOT NULL COMMENT '全宗信息流水号',
  `YUF00Z` varchar(10) NOT NULL COMMENT '行政区划代码',
  `YUB001` varchar(6) NOT NULL COMMENT '内部全宗号',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人id',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  PRIMARY KEY (`YUE0R0`),
  KEY `FK_YUE0H0` (`YUE0H0`),
  CONSTRAINT `FK_YUE0H0` FOREIGN KEY (`YUE0H0`) REFERENCES `ue0h` (`YUE0H0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全宗管理门类配置信息';

-- ----------------------------
-- Table structure for ue10
-- ----------------------------
DROP TABLE IF EXISTS `ue10`;
CREATE TABLE `ue10` (
  `YUE100` varchar(20) DEFAULT NULL COMMENT '主键',
  `YUE091` varchar(20) DEFAULT NULL COMMENT '功能模块',
  `YUE0F2` varchar(20) DEFAULT NULL COMMENT '前置条件',
  `YUE101` varchar(20) DEFAULT NULL COMMENT '是否同时多个条件',
  `YUE001` varchar(20) DEFAULT NULL COMMENT '档案门类',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE012` varchar(20) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE018` varchar(20) DEFAULT NULL COMMENT '经办机构名称',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块与前置条件对应关系';

-- ----------------------------
-- Table structure for ue16
-- ----------------------------
DROP TABLE IF EXISTS `ue16`;
CREATE TABLE `ue16` (
  `YUE160` varchar(20) NOT NULL COMMENT '字段流水号',
  `YUE161` text COMMENT '备注/字段名',
  `YUE162` decimal(4,0) NOT NULL COMMENT '字段在条码中的顺序（必须连续，从1开始）',
  `YUE163` varchar(8) NOT NULL COMMENT '字段代码',
  `YUF001` varchar(1) NOT NULL COMMENT '条码类别代码（2-案卷，3-文件 ,4-盒 ,5-箱）',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `YUE166` decimal(2,0) NOT NULL COMMENT '流水号位数',
  `YUE168` varchar(1) DEFAULT NULL COMMENT '是否流水号依赖（是-Y，否-N）',
  `YUE169` varchar(10) DEFAULT NULL COMMENT '数据类型（String，Integer，TimeStamp，Default）',
  `YUE164` varchar(1) DEFAULT NULL COMMENT '依赖方式（年-Y，月-M，日-D）',
  `YUE16A` varchar(6) DEFAULT NULL COMMENT '是否固定值字段',
  `YUE16B` varchar(10) DEFAULT NULL COMMENT '固定值',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE000` varchar(1) DEFAULT NULL COMMENT '是否有效 0-无效 1-有效',
  PRIMARY KEY (`YUE160`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='条码规则';

-- ----------------------------
-- Table structure for ue18
-- ----------------------------
DROP TABLE IF EXISTS `ue18`;
CREATE TABLE `ue18` (
  `YUE180` varchar(20) NOT NULL COMMENT '人事档案收集目录流水号',
  `YUE181` varchar(6) DEFAULT NULL COMMENT '人事档案收集目录编号',
  `YUE182` varchar(100) DEFAULT NULL COMMENT '收集目录名称',
  `YUE183` varchar(3) DEFAULT NULL COMMENT '是否必须收集',
  `YUE184` decimal(4,0) DEFAULT NULL COMMENT '顺序号',
  `YUE185` varchar(6) DEFAULT NULL COMMENT '有效标示',
  PRIMARY KEY (`YUE180`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事档案收集目录';

-- ----------------------------
-- Table structure for ue19
-- ----------------------------
DROP TABLE IF EXISTS `ue19`;
CREATE TABLE `ue19` (
  `YUE190` varchar(50) NOT NULL COMMENT '档案身份与材料映射流水号',
  `YUJ03C` varchar(50) DEFAULT NULL COMMENT '档案身份',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '附件流水号',
  `YUE031` text COMMENT '附件表名',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT '附表序号',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人编号',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构 ',
  `YAE011` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  PRIMARY KEY (`YUE190`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案身份与材料映射表';

-- ----------------------------
-- Table structure for ue20
-- ----------------------------
DROP TABLE IF EXISTS `ue20`;
CREATE TABLE `ue20` (
  `YUE200` varchar(20) NOT NULL COMMENT '参数代码',
  `YUE201` varchar(200) DEFAULT NULL COMMENT '参数名称',
  `YUE202` varchar(200) DEFAULT NULL COMMENT '参数描述',
  `YUE203` varchar(100) DEFAULT NULL COMMENT '参数值',
  `YUE204` varchar(1) DEFAULT NULL COMMENT '参数类型',
  `YUE205` varchar(1) DEFAULT NULL COMMENT '可编辑标志',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `AAE100` varchar(1) DEFAULT NULL COMMENT '有效标识',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUE200`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案全局参数表';

-- ----------------------------
-- Table structure for ue21
-- ----------------------------
DROP TABLE IF EXISTS `ue21`;
CREATE TABLE `ue21` (
  `YUE210` varchar(20) NOT NULL COMMENT '流水号',
  `YUE211` varchar(20) DEFAULT NULL COMMENT '接入系统编码',
  `YUE212` varchar(50) DEFAULT NULL COMMENT '接入系统名称',
  `AAE100` varchar(10) DEFAULT NULL COMMENT '有效标志',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人id',
  `AAE012` varchar(60) NOT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办人部门',
  `YUE213` varchar(10) DEFAULT NULL COMMENT '启用状态',
  PRIMARY KEY (`YUE210`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案接入系统管理';

-- ----------------------------
-- Table structure for ue30
-- ----------------------------
DROP TABLE IF EXISTS `ue30`;
CREATE TABLE `ue30` (
  `YUE300` varchar(30) NOT NULL COMMENT '字段主键',
  `YUE301` varchar(30) DEFAULT NULL COMMENT '字段代码',
  `YUE302` varchar(50) DEFAULT NULL COMMENT '字段名称',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '所属门类',
  `YUE303` varchar(6) DEFAULT NULL COMMENT '字段所属表',
  `YUE304` varchar(30) DEFAULT NULL COMMENT '字段数据类型',
  `YUE305` varchar(50) DEFAULT NULL COMMENT '字段校验规则',
  `YUE306` decimal(6,0) DEFAULT NULL COMMENT '字段长度限制',
  `YUE30A` varchar(6) DEFAULT NULL COMMENT '是否为索引列',
  `YUE30B` varchar(6) DEFAULT NULL COMMENT '是否使用分词器',
  `YUE30C` varchar(30) DEFAULT NULL COMMENT '分词器类型',
  `YUB004` varchar(50) DEFAULT NULL COMMENT '行政区划',
  `YUB001` varchar(50) DEFAULT NULL COMMENT '全宗',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '所在部门id',
  `AAE018` varchar(50) DEFAULT NULL COMMENT '所在部门名称',
  `AAE036` datetime DEFAULT NULL COMMENT '业务经办时间',
  `YUE308` varchar(30) DEFAULT NULL COMMENT '字段默认值',
  `AAE013` varchar(100) DEFAULT NULL COMMENT '备注信息',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人员id',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人员姓名',
  `YUE307` varchar(6) DEFAULT NULL COMMENT '特有字段标识',
  `YUE309` varchar(6) DEFAULT NULL COMMENT '是否允许为空标识',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  PRIMARY KEY (`YUE300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uf01
-- ----------------------------
DROP TABLE IF EXISTS `uf01`;
CREATE TABLE `uf01` (
  `YUF010` varchar(50) NOT NULL COMMENT '档案装具流水号',
  `YUF011` varchar(50) DEFAULT NULL COMMENT '条码',
  `YUF0B0` varchar(20) DEFAULT NULL COMMENT '装具类型流水号',
  `YUF012` decimal(10,0) DEFAULT NULL COMMENT '装具序号',
  `YUF013` varchar(6) DEFAULT NULL COMMENT '是否使用',
  `YUB002` varchar(20) DEFAULT NULL COMMENT '持有机构',
  `YUC001` varchar(25) DEFAULT NULL COMMENT '持有人',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT '年度',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT '月份',
  `YUH750` varchar(50) DEFAULT NULL COMMENT '格ID',
  `YUF016` decimal(3,0) DEFAULT NULL COMMENT '格内序号',
  `YUF017` datetime DEFAULT NULL COMMENT '档案室入库时间',
  `YUF018` datetime DEFAULT NULL COMMENT '档案馆入库时间',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUF019` varchar(6) DEFAULT NULL COMMENT '档案装具入库状态，默认为0：未入库',
  `YUI880` decimal(15,0) DEFAULT NULL COMMENT '档案对象流程流水号',
  `YUH710` varchar(15) DEFAULT NULL COMMENT '区域代码',
  `YUH770` varchar(15) DEFAULT NULL COMMENT '组代码',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限',
  `YUF12B` varchar(6) DEFAULT NULL COMMENT '是否二次装盒',
  `YUF014` varchar(6) DEFAULT NULL COMMENT '是否自动装盒',
  `YUF01B` decimal(4,0) DEFAULT NULL COMMENT '卷数',
  `YUF00N` decimal(4,0) DEFAULT NULL COMMENT '件数',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT '页数',
  `YUH701` varchar(15) DEFAULT NULL COMMENT '库房对象id',
  `YUF01C` varchar(15) DEFAULT NULL COMMENT '装具内档案对象类型(2：案卷，3：文件)',
  `YUE013` varchar(10) DEFAULT NULL COMMENT '案卷类别编号',
  `YUF015` varchar(6) DEFAULT NULL COMMENT '档案装具是否可入库',
  `YUF01A` varchar(6) DEFAULT NULL COMMENT '档案装具移交状态',
  `YUF005` varchar(6) DEFAULT NULL COMMENT '丢失状态',
  `YUF006` varchar(6) DEFAULT NULL COMMENT '损坏状态',
  `YUF00L` datetime DEFAULT NULL COMMENT '开始保管时间',
  `YUF00M` datetime DEFAULT NULL COMMENT '结束保管时间',
  `YUF00B` varchar(6) DEFAULT NULL COMMENT '档案保管状态',
  `YUF00Y` varchar(6) DEFAULT NULL COMMENT '险种',
  `YUJ001` varchar(25) DEFAULT NULL COMMENT '附表字段一',
  `YUJ002` varchar(25) DEFAULT NULL COMMENT '附表字段二',
  `YUJ003` varchar(25) DEFAULT NULL COMMENT '附表字段三',
  `YUF125` varchar(20) DEFAULT NULL COMMENT '检查人',
  `YUF126` datetime DEFAULT NULL COMMENT '检查时间',
  `YUF127` text COMMENT '备注',
  PRIMARY KEY (`YUF010`),
  KEY `IDX_YUF015` (`YUH750`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案装具';

-- ----------------------------
-- Table structure for uf03
-- ----------------------------
DROP TABLE IF EXISTS `uf03`;
CREATE TABLE `uf03` (
  `YUF030` varchar(50) NOT NULL COMMENT '业务记账会计科目明细号',
  `YUF110` varchar(50) DEFAULT NULL COMMENT '档案系统文件主表流水号',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '财务受理号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(25) DEFAULT NULL COMMENT '市级机构代码',
  `YUF032` varchar(50) DEFAULT NULL COMMENT '会计科目代码',
  `YUF033` varchar(100) DEFAULT NULL COMMENT '会计科目名称',
  `YUF035` varchar(6) DEFAULT NULL COMMENT '会计借贷方',
  `YUF036` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `AAE013` text COMMENT '备注说明',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标注',
  PRIMARY KEY (`YUF030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务记账会计目录信息';

-- ----------------------------
-- Table structure for uf04
-- ----------------------------
DROP TABLE IF EXISTS `uf04`;
CREATE TABLE `uf04` (
  `YUF040` varchar(10) NOT NULL COMMENT '主件明细流水号',
  `YUF110` varchar(50) DEFAULT NULL COMMENT '文件件流水号',
  `YUF031` decimal(8,0) DEFAULT NULL COMMENT '页数',
  `YUF034` decimal(8,0) DEFAULT NULL COMMENT '序号',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `AAB004` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `AAC001` varchar(20) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(20) DEFAULT NULL COMMENT '公民身份证号',
  `AAC003` varchar(100) DEFAULT NULL COMMENT '姓名',
  `AAC021` varchar(50) DEFAULT NULL COMMENT '失业证编号',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT '个人标志码',
  `YUF004` varchar(6) DEFAULT NULL COMMENT '扫描状态 1：未扫描；0、扫描中；2、已扫描',
  PRIMARY KEY (`YUF040`),
  KEY `INDEX_YUF110_UF04` (`YUF110`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='一表多人明细表';

-- ----------------------------
-- Table structure for uf05
-- ----------------------------
DROP TABLE IF EXISTS `uf05`;
CREATE TABLE `uf05` (
  `YUF050` varchar(50) NOT NULL COMMENT '图片明细流水号',
  `YUF052` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `YUF051` decimal(10,0) DEFAULT NULL COMMENT '图片序号',
  `YUF002` varchar(50) DEFAULT NULL COMMENT '业务表主键',
  `YUF055` text COMMENT '图片url(缩略图)',
  `YUF054` varchar(200) DEFAULT NULL COMMENT '文件存储KEY(缩略图)',
  `YUF056` decimal(8,0) DEFAULT NULL COMMENT '图片大小(缩略图)',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '业务材料流水号',
  `YUF053` varchar(6) DEFAULT NULL COMMENT '图片保存方式（0：磁盘；1：ucm）',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案类型（3案件，2案卷）',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人编号',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YAE049` varchar(30) DEFAULT NULL COMMENT '功能编号',
  `YUF05L` varchar(6) DEFAULT NULL COMMENT '图片高（像素）',
  `YUF05M` varchar(6) DEFAULT NULL COMMENT '图片宽（像素）',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF05J` varchar(6) DEFAULT NULL COMMENT '影像来源（1：远程，2：证照库）',
  `YUF05K` varchar(6) DEFAULT NULL COMMENT '是否可编辑',
  `YUF05N` text COMMENT '备注',
  `YUF05P` text COMMENT '图片url(原图)',
  `YUF05Q` varchar(200) DEFAULT NULL COMMENT '文件存储KEY(原图)',
  `YUF05R` decimal(8,0) DEFAULT NULL COMMENT '图片大小(原图)',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT '个人标志码',
  `YUF241` varchar(6) DEFAULT NULL COMMENT '是否图片',
  `YUB004` varchar(25) DEFAULT NULL COMMENT '市级机构代码',
  `YUF060` varchar(20) DEFAULT NULL COMMENT '复用影像流水号',
  `YUR010` varchar(20) DEFAULT NULL COMMENT '档案调整流水号',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `YUF057` varchar(6) DEFAULT NULL COMMENT '识别标识',
  `YUF059` decimal(1,0) DEFAULT NULL COMMENT '旋转次数（-1,0,1,2）',
  `YUT340` varchar(25) DEFAULT NULL COMMENT 'ocr日志操作id',
  `YUF05A` varchar(30) DEFAULT NULL COMMENT '图片唯一id',
  PRIMARY KEY (`YUF050`),
  KEY `INDEX_UF05_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案影像信息';

-- ----------------------------
-- Table structure for uf05a1
-- ----------------------------
DROP TABLE IF EXISTS `uf05a1`;
CREATE TABLE `uf05a1` (
  `YUF130` varchar(20) NOT NULL COMMENT '图片批注流水号',
  `YUF131` decimal(4,0) DEFAULT NULL COMMENT 'X坐标',
  `YUF132` decimal(4,0) DEFAULT NULL COMMENT 'Y坐标',
  `YUF133` varchar(100) DEFAULT NULL COMMENT '批注内容',
  `YUF134` varchar(6) DEFAULT NULL COMMENT '批注类型(1:文字 2:图片)',
  `YUF050` varchar(50) DEFAULT NULL COMMENT '图片流水号',
  PRIMARY KEY (`YUF130`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片批注信息';

-- ----------------------------
-- Table structure for uf06
-- ----------------------------
DROP TABLE IF EXISTS `uf06`;
CREATE TABLE `uf06` (
  `YUF060` varchar(20) NOT NULL COMMENT '复用影像流水号',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象',
  `YUF002` varchar(20) DEFAULT NULL COMMENT '业务表主键',
  `YUF070` varchar(50) DEFAULT NULL COMMENT '材料复用版本流水号',
  `YUE0J0` varchar(20) DEFAULT NULL COMMENT '复用材料流水号',
  `YUF052` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `YUF051` decimal(10,0) DEFAULT NULL COMMENT '图片序号',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '材料编号',
  `YUF05Q` varchar(200) DEFAULT NULL COMMENT '文件存储KEY(原图)',
  `YUF05P` text COMMENT '图片url(原图)',
  `YUF054` varchar(200) DEFAULT NULL COMMENT '文件存储KEY（缩略图）',
  `YUF055` text COMMENT '图片url（缩略图）',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '是否有效',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUF240` varchar(50) DEFAULT NULL COMMENT '原始影像序列号',
  `YUF057` varchar(6) DEFAULT NULL COMMENT '识别标识',
  `YUF05A` varchar(30) DEFAULT NULL COMMENT '图片唯一id',
  `YUT340` varchar(25) DEFAULT NULL COMMENT '识别id',
  PRIMARY KEY (`YUF060`),
  KEY `IN_UF06_YUF070` (`YUF070`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='复用影像信息';

-- ----------------------------
-- Table structure for uf07
-- ----------------------------
DROP TABLE IF EXISTS `uf07`;
CREATE TABLE `uf07` (
  `YUF070` varchar(50) NOT NULL COMMENT '材料复用版本流水号',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(6) DEFAULT NULL COMMENT '市级机构代码',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF002` varchar(100) DEFAULT NULL COMMENT '档案对象ID',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB003` varchar(20) DEFAULT NULL COMMENT '单位代码',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '公民身份证号',
  `AAC003` text COMMENT '个人姓名',
  `YUE035` varchar(6) DEFAULT NULL COMMENT '材料主体(01:个人，02-单位)',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '业务材料编号',
  `YUF071` varchar(100) DEFAULT NULL COMMENT '复用材料版本号',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  `AAE013` text COMMENT '备注',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUE0J0` varchar(20) DEFAULT NULL COMMENT '复用材料流水号',
  PRIMARY KEY (`YUF070`),
  KEY `INDX_UF07_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='材料复用版本记录信息';

-- ----------------------------
-- Table structure for uf11
-- ----------------------------
DROP TABLE IF EXISTS `uf11`;
CREATE TABLE `uf11` (
  `YUF110` varchar(20) NOT NULL COMMENT '档案系统文件主表流水号',
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '档案对象条码',
  `YUF111` varchar(50) DEFAULT NULL COMMENT '案卷文件关联码',
  `YUF112` decimal(4,0) DEFAULT NULL COMMENT '卷内顺序号',
  `YUF114` varchar(6) DEFAULT NULL COMMENT '一件多卷标识',
  `YUF115` varchar(6) DEFAULT NULL COMMENT '含有附件标识',
  `YUF116` decimal(6,0) DEFAULT NULL COMMENT '附件数',
  `YUC001` varchar(20) NOT NULL COMMENT '持有人',
  `YUB002` varchar(50) NOT NULL COMMENT '持有机构',
  `YUB001` varchar(6) NOT NULL COMMENT '全宗号',
  `YUF004` varchar(6) DEFAULT NULL COMMENT '扫描状态（0：未扫描；1、扫描中；2、已扫描）',
  `YUF005` varchar(6) DEFAULT NULL COMMENT '是否丢失',
  `YUF006` varchar(6) DEFAULT NULL COMMENT '是否损坏',
  `YUF007` varchar(6) DEFAULT NULL COMMENT '是否销毁',
  `YUF008` varchar(6) DEFAULT NULL COMMENT '是否入库',
  `YUF009` varchar(6) DEFAULT NULL COMMENT '借出标识（0-未借出，1-已借出，2-生成借阅批次，3-审核通过）',
  `YUF00A` varchar(6) DEFAULT NULL COMMENT '影像加工状态',
  `YUF00B` varchar(6) DEFAULT NULL COMMENT '文件状态',
  `YUF00C` varchar(6) DEFAULT NULL COMMENT '立卷状态',
  `YUF00D` varchar(6) DEFAULT NULL COMMENT '移交状态',
  `YAB003` varchar(20) DEFAULT NULL COMMENT '业务经办分中心',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '业务经办人员',
  `AAE036` datetime DEFAULT NULL COMMENT '业务经办时间',
  `AAE013` text COMMENT '备注',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT '年度',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT '月份',
  `YUE013` varchar(10) DEFAULT NULL COMMENT '案卷类别编号',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT '密级',
  `YUF00J` decimal(6,0) DEFAULT NULL COMMENT '文件份数',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT '文件页数',
  `YUF00L` datetime DEFAULT NULL COMMENT '保管开始时间',
  `YUF00M` datetime DEFAULT NULL COMMENT '保管结束时间',
  `YUF00P` decimal(4,0) DEFAULT NULL COMMENT '扫描页数',
  `YUF010` varchar(50) DEFAULT NULL COMMENT '档案装具流水号',
  `YUF113` datetime DEFAULT NULL COMMENT '文件日期',
  `YUH750` varchar(50) DEFAULT NULL COMMENT '格流水号',
  `YAB001` varchar(20) DEFAULT NULL COMMENT '业务经办分中心（原始数据）',
  `YUF00R` varchar(50) DEFAULT NULL COMMENT '档号',
  `YUF117` varchar(25) DEFAULT NULL COMMENT '原案卷条码',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT '最后修改人部门',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `YUF00S` decimal(4,0) DEFAULT NULL COMMENT '装具内序号',
  `YUF118` decimal(5,0) DEFAULT NULL COMMENT '开始页号',
  `YUF119` decimal(5,0) DEFAULT NULL COMMENT '结束页号',
  `YUF00Z` varchar(10) DEFAULT NULL COMMENT '行政区划代码',
  `YUF128` varchar(6) DEFAULT NULL COMMENT '扫描锁定状态（0未锁定，1锁定）',
  `YUF00Y` varchar(6) DEFAULT NULL COMMENT '险种（收集单位类别）',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '业务受理号',
  `YUF11A` varchar(6) DEFAULT NULL COMMENT '业务系统数据确认标识',
  `YUF11B` varchar(1) DEFAULT NULL COMMENT '档案解封状态（0未封）',
  `YUF00T` varchar(6) DEFAULT NULL COMMENT '档案转移状态（0未转移）',
  `YUF016` decimal(4,0) DEFAULT NULL COMMENT '格子序号',
  `YUF141` varchar(50) DEFAULT NULL COMMENT '箱条码',
  `YUF129` decimal(4,0) DEFAULT NULL COMMENT '箱内序号',
  `YUF12A` varchar(6) DEFAULT NULL COMMENT '装箱状态（是否装箱）',
  `YUF12B` varchar(6) DEFAULT NULL COMMENT '装盒状态（是否装盒）',
  `YUF00X` varchar(6) DEFAULT NULL COMMENT '数据来源',
  `YUF00G` varchar(6) DEFAULT NULL COMMENT '是否一表多人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `YUF00O` varchar(10) DEFAULT NULL COMMENT '是否保存纸质材料',
  `YUF11C` decimal(6,0) DEFAULT NULL COMMENT '件号',
  `YUJ001` varchar(25) DEFAULT NULL COMMENT '附表字段一',
  `YUJ002` varchar(25) DEFAULT NULL COMMENT '附表字段二',
  `YUJ003` varchar(25) DEFAULT NULL COMMENT '附表字段三',
  `YUB004` varchar(10) DEFAULT NULL,
  `YUF11D` text COMMENT '文件影像说明',
  `YUF11E` varchar(6) DEFAULT NULL COMMENT 'ocr识别标识',
  PRIMARY KEY (`YUF110`),
  KEY `INDEX_UF11_1` (`YAZ001`),
  KEY `INDEX_UF11_YUB002` (`YUB002`),
  KEY `INDEX_UF11_YUC001` (`YUC001`),
  KEY `INDEX_UF11_YUE001` (`YUE001`),
  KEY `INDEX_UF11_YUF010` (`YUF010`),
  KEY `INDEX_UF11_YUF111` (`YUF111`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案系统文件信息主表';

-- ----------------------------
-- Table structure for uf12
-- ----------------------------
DROP TABLE IF EXISTS `uf12`;
CREATE TABLE `uf12` (
  `YUF120` varchar(20) NOT NULL COMMENT '档案系统案卷信息流水号',
  `YUJ0X0` varchar(20) DEFAULT NULL COMMENT '档案基础信息主键',
  `YUF003` varchar(50) NOT NULL COMMENT '档案对象条码',
  `YUF111` varchar(50) DEFAULT NULL COMMENT '案卷文件关联码',
  `YUB001` varchar(6) NOT NULL COMMENT '全宗号',
  `YUF004` varchar(6) DEFAULT NULL COMMENT '是否扫描',
  `YUF005` varchar(6) DEFAULT NULL COMMENT '是否丢失',
  `YUF006` varchar(6) DEFAULT NULL COMMENT '是否损坏',
  `YUF007` varchar(6) DEFAULT NULL COMMENT '是否销毁',
  `YUF008` varchar(6) DEFAULT NULL COMMENT '是否入库',
  `YUF009` varchar(6) DEFAULT NULL COMMENT '是否借出',
  `YUF00A` varchar(6) DEFAULT NULL COMMENT '影像化加工状态',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUC001` varchar(20) NOT NULL COMMENT '持有人',
  `YUB002` varchar(50) NOT NULL COMMENT '持有机构',
  `YUF00B` varchar(6) DEFAULT NULL COMMENT '文件状态',
  `YUF00D` varchar(6) DEFAULT NULL COMMENT '移交状态',
  `YAB003` varchar(20) DEFAULT NULL COMMENT '业务经办分中心',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '业务经办人员',
  `AAE036` datetime NOT NULL COMMENT '业务经办时间',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT '年度',
  `YUE013` varchar(10) DEFAULT NULL COMMENT '案卷类别编号',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT '密级',
  `YUF00N` decimal(6,0) DEFAULT NULL COMMENT '案件份数',
  `YUF00L` datetime DEFAULT NULL COMMENT '保管开始时间',
  `YUF00M` datetime DEFAULT NULL COMMENT '保管结束时间',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT '文件页数',
  `YUF00P` decimal(6,0) DEFAULT NULL COMMENT '扫描页数',
  `YUF00Q` decimal(10,0) DEFAULT NULL COMMENT '案卷顺序号',
  `YUF00R` varchar(100) DEFAULT NULL COMMENT '档号',
  `YUF010` varchar(50) DEFAULT NULL COMMENT '档案装具流水号',
  `YUF00S` decimal(65,30) DEFAULT NULL COMMENT '装具内序号',
  `YUH750` varchar(50) DEFAULT NULL COMMENT '格流水号',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT '最后修改人部门',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `YUF121` text COMMENT '题名',
  `YUE014` varchar(100) DEFAULT NULL COMMENT '案卷类别名称',
  `YUF123` varchar(20) DEFAULT NULL COMMENT '立卷人',
  `YUF124` datetime DEFAULT NULL COMMENT '立卷时间',
  `YUF125` varchar(20) DEFAULT NULL COMMENT '检查人',
  `YUF126` datetime DEFAULT NULL COMMENT '检查时间',
  `YUF127` text COMMENT '备注',
  `YUF00Z` varchar(10) DEFAULT NULL COMMENT '行政区划代码',
  `YUF128` varchar(6) DEFAULT NULL COMMENT '扫描锁定状态（0未锁定，1锁定）',
  `YUF00Y` varchar(6) DEFAULT NULL COMMENT '险种（收集单位类别）',
  `YUF12X` varchar(6) DEFAULT NULL COMMENT '单位属性',
  `YUF016` decimal(4,0) DEFAULT NULL COMMENT '格内序号',
  `YUF141` varchar(50) DEFAULT NULL COMMENT '箱条码',
  `YUF129` decimal(4,0) DEFAULT NULL COMMENT '箱内序号',
  `YUF12A` varchar(6) DEFAULT NULL COMMENT '装箱状态（是否装箱）',
  `YUF12B` varchar(6) DEFAULT NULL COMMENT '装盒状态（是否二次装盒）',
  `YUF114` varchar(6) DEFAULT NULL COMMENT '一件多卷标识',
  `YUF118` decimal(5,0) DEFAULT NULL COMMENT '开始页号',
  `YUF119` decimal(5,0) DEFAULT NULL COMMENT '结束页号',
  `YUF12C` decimal(3,0) DEFAULT NULL COMMENT '件内顺序号',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `YUF00C` varchar(6) DEFAULT NULL COMMENT '立卷状态',
  `YUB004` varchar(25) DEFAULT NULL COMMENT '市级机构代码',
  `AAE012` varchar(60) DEFAULT NULL COMMENT '经办人姓名',
  `YUF12D` varchar(60) DEFAULT NULL COMMENT '立卷人姓名',
  `YUF00X` varchar(60) DEFAULT NULL COMMENT '数据来源',
  `YUJ001` varchar(25) DEFAULT NULL COMMENT '附表字段一',
  `YUJ002` varchar(25) DEFAULT NULL COMMENT '附表字段二',
  `YUJ003` varchar(25) DEFAULT NULL COMMENT '附表字段三',
  PRIMARY KEY (`YUF120`),
  KEY `INDEX_UF12_YUB002` (`YUB002`),
  KEY `INDEX_UF12_YUE001` (`YUE001`),
  KEY `INDEX_UF12_YUF010` (`YUF010`),
  KEY `INDEX_UF12_YUF111` (`YUF111`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案系统案卷信息主表';

-- ----------------------------
-- Table structure for uf13
-- ----------------------------
DROP TABLE IF EXISTS `uf13`;
CREATE TABLE `uf13` (
  `YUJ0X0` varchar(50) NOT NULL COMMENT '业务表主键',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `YUE030` varchar(50) NOT NULL COMMENT '材料编号',
  `YUE031` text COMMENT '材料名称',
  `YUE046` decimal(6,0) DEFAULT NULL COMMENT '材料页数',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT '附表顺序号',
  `YUE042` varchar(6) DEFAULT NULL COMMENT '是否必须收集',
  `YUE043` varchar(6) DEFAULT NULL COMMENT '是否主表',
  `YUE044` varchar(6) DEFAULT NULL COMMENT '是否必须提交影像',
  `YUE047` varchar(6) DEFAULT NULL COMMENT '是否有实体材料',
  `YUE048` varchar(6) DEFAULT NULL COMMENT '是否必扫（0-否，1-是）',
  `YUF04Y` varchar(50) NOT NULL COMMENT '个人标志码',
  `YUE035` varchar(6) DEFAULT NULL COMMENT '材料主体(01-个人，02-单位)',
  PRIMARY KEY (`YUJ0X0`,`YUE030`,`YUF04Y`,`YUE001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案材料信息表';

-- ----------------------------
-- Table structure for uf14
-- ----------------------------
DROP TABLE IF EXISTS `uf14`;
CREATE TABLE `uf14` (
  `YUF140` varchar(25) NOT NULL COMMENT '箱流水号',
  `YUF141` varchar(50) DEFAULT NULL COMMENT '箱条码',
  `YUF142` varchar(25) DEFAULT NULL COMMENT '箱位置信息id',
  `YUF143` decimal(6,0) DEFAULT NULL COMMENT '箱容量',
  `YUF00N` decimal(6,0) NOT NULL COMMENT '箱内档案数量（卷数）',
  `YUF00K` decimal(6,0) NOT NULL COMMENT '箱内档案数量（页数）',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT '年度',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '人事档案类型',
  `YUF144` decimal(6,0) NOT NULL COMMENT '箱顺序号',
  `YUB002` varchar(50) DEFAULT NULL COMMENT '持有机构',
  `YUC001` varchar(50) DEFAULT NULL COMMENT '持有人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUF145` text COMMENT '备注',
  `YUF146` varchar(6) DEFAULT NULL COMMENT '箱状态',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT '密级',
  `YUF00L` datetime DEFAULT NULL COMMENT '保管开始时间',
  `YUF00M` datetime DEFAULT NULL COMMENT '保管结束时间',
  `YUF00D` varchar(6) DEFAULT NULL COMMENT '移交状态',
  `YUF00A` varchar(6) DEFAULT NULL COMMENT '影像加工状态',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUF140`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案箱信息';

-- ----------------------------
-- Table structure for uf20
-- ----------------------------
DROP TABLE IF EXISTS `uf20`;
CREATE TABLE `uf20` (
  `yaz001` varchar(50) NOT NULL COMMENT '业务受理号',
  `yae049` varchar(25) DEFAULT NULL COMMENT '菜单编号',
  `yue001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `yae051` varchar(6) DEFAULT NULL COMMENT '业务流程编号',
  `aab001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `aab003` varchar(20) DEFAULT NULL COMMENT '单位代码',
  `aab004` varchar(500) DEFAULT NULL COMMENT '单位名称',
  `aac001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `aac002` varchar(18) DEFAULT NULL COMMENT '公民身份证号',
  `aac003` varchar(500) DEFAULT NULL COMMENT '个人姓名',
  `aae017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `aae011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `yuf00e` int(4) DEFAULT NULL COMMENT '年度',
  `yuf00f` int(2) DEFAULT NULL COMMENT '月份',
  `yuf00k` int(6) DEFAULT NULL COMMENT '页数',
  `aae036` date DEFAULT NULL COMMENT '经办时间',
  `yuf00p` int(4) DEFAULT NULL COMMENT '扫描页数',
  `yuf004` varchar(6) DEFAULT NULL COMMENT '扫描标志',
  `yuf00g` varchar(6) DEFAULT NULL COMMENT '一表多人标志',
  `yuf000` varchar(6) DEFAULT NULL COMMENT '是否传递材料信息标志',
  `yub001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `yug643` varchar(6) DEFAULT NULL COMMENT '交接标志（0-未交接，1-已交接，2-已接收，3-已回退）',
  `yuf201` varchar(6) DEFAULT NULL COMMENT '文件修改标志（0-未修改，1-已修改）',
  `aae000` varchar(6) DEFAULT NULL COMMENT '有效标志',
  `aae001` varchar(50) DEFAULT NULL COMMENT '办结机构',
  `aae002` varchar(50) DEFAULT NULL COMMENT '办结人',
  `aae003` date DEFAULT NULL COMMENT '办结时间',
  `yue003` varchar(6) DEFAULT NULL COMMENT '是否办结',
  `yuf003` varchar(50) DEFAULT NULL COMMENT '档案对象条码',
  `yue010` varchar(20) DEFAULT NULL COMMENT '文件类别编码',
  `yuf203` varchar(6) DEFAULT '0' COMMENT '回退次数',
  `yuf00x` varchar(6) DEFAULT NULL COMMENT '数据来源',
  `yuf00z` varchar(10) DEFAULT NULL COMMENT '行政区划代码',
  `yuf00o` varchar(6) DEFAULT NULL COMMENT '是否保存纸质材料',
  `yub002` varchar(25) DEFAULT NULL COMMENT '持有机构',
  `yuc001` varchar(25) DEFAULT NULL COMMENT '持有人',
  `yab003` varchar(25) DEFAULT NULL COMMENT '分中心',
  `yuj0x0` varchar(20) DEFAULT NULL COMMENT '档案基础信息主键',
  `yuf113` date DEFAULT NULL COMMENT '文件日期',
  `yub004` varchar(25) DEFAULT NULL COMMENT '市级机构代码',
  `yae04a` varchar(50) DEFAULT NULL COMMENT '业务菜单名称',
  `yuf00y` varchar(6) DEFAULT NULL COMMENT '险种',
  `yuf205` varchar(6) DEFAULT NULL COMMENT '业务经办状态(0未受理1受理2经办)',
  `aae013` varchar(2000) DEFAULT NULL COMMENT '备注说明',
  `yuf11d` varchar(500) DEFAULT NULL COMMENT '文件影像备注说明',
  `yuf206` varchar(50) DEFAULT NULL COMMENT '原业务受理号',
  `yuf207` varchar(6) DEFAULT NULL COMMENT '业务操作方式（00-业务正常经办，01-业务办结后修正）',
  `yuf208` varchar(6) DEFAULT NULL COMMENT '修正是否正确',
  `uf20_time_index` datetime GENERATED ALWAYS AS (date_format(`aae036`,'%Y/%m/%d')) VIRTUAL,
  PRIMARY KEY (`yaz001`),
  UNIQUE KEY `yuf003` (`yuf003`),
  KEY `INDEX_UF20_YUB001` (`yub001`),
  KEY `INDEX_UF20_01` (`uf20_time_index`,`aae000`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统接口数据信息表';

-- ----------------------------
-- Table structure for uf21
-- ----------------------------
DROP TABLE IF EXISTS `uf21`;
CREATE TABLE `uf21` (
  `AAZ002` varchar(50) NOT NULL COMMENT '业务办理ID',
  `YAZ001` varchar(50) NOT NULL COMMENT '业务受理ID',
  `YAE049` varchar(25) DEFAULT NULL COMMENT '菜单编号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YAE051` varchar(6) DEFAULT NULL COMMENT '业务流程编号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '档案对象条码',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB003` varchar(20) DEFAULT NULL COMMENT '单位代码',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '公民身份号码',
  `AAC003` text COMMENT '个人姓名',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT '年度',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT '月份',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT '页数',
  `YUF00P` decimal(3,0) DEFAULT NULL COMMENT '扫描页数',
  `YUF004` varchar(6) DEFAULT NULL COMMENT '扫描标志',
  `YUF00G` varchar(6) DEFAULT NULL COMMENT '一表多人标志',
  `YUF213` varchar(6) DEFAULT NULL COMMENT '是否传递材料信息标志',
  `YUE003` varchar(6) DEFAULT NULL COMMENT '业务是否办理完成',
  `YUF11A` varchar(6) DEFAULT NULL COMMENT '数据确认标识',
  `YUJ01K` datetime DEFAULT NULL COMMENT '业务办结时间',
  `YUG643` varchar(6) DEFAULT NULL COMMENT '交接标志（0-未交接，1-已交接,3-已回退,4-进入批次）',
  `YUF211` varchar(6) DEFAULT NULL COMMENT '文件修改标志（0-不修改，1-未修改，2-已修改） ',
  `YUF212` varchar(6) DEFAULT NULL COMMENT '是否为主业务（0-不是，1-是）',
  `AAE000` varchar(6) DEFAULT NULL COMMENT '有效标志',
  `AAE001` varchar(50) DEFAULT NULL COMMENT '办结机构',
  `AAE002` varchar(50) DEFAULT NULL COMMENT '办结人',
  `AAE003` datetime DEFAULT NULL COMMENT '办结时间',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUB004` varchar(6) DEFAULT NULL COMMENT '市级机构代码',
  `YUF113` datetime DEFAULT NULL COMMENT '文件日期',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT '最后修改机构',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`AAZ002`),
  KEY `INDX_UF21_YAZ001` (`YAZ001`),
  KEY `INDX_UF21_YUF003` (`YUF003`),
  CONSTRAINT `F_UF20_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统接口业务办理数据信息表';

-- ----------------------------
-- Table structure for uf22
-- ----------------------------
DROP TABLE IF EXISTS `uf22`;
CREATE TABLE `uf22` (
  `YUF220` varchar(50) NOT NULL COMMENT '流水号',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '业务受理号',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '材料编码',
  `YUE031` text COMMENT '材料名称',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT '材料顺序号',
  `YUE045` decimal(6,0) DEFAULT NULL COMMENT '材料份数',
  `YUE046` decimal(6,0) DEFAULT NULL COMMENT '材料页数',
  `YUE047` varchar(6) DEFAULT NULL COMMENT '是否有实体材料',
  `YUF202` varchar(6) DEFAULT NULL COMMENT '是否为主文件（0-不是，1-是）',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE000` varchar(6) DEFAULT NULL COMMENT '材料有效标志',
  `YUF00V` varchar(50) DEFAULT NULL COMMENT '修改人部门',
  `YUF00U` varchar(50) DEFAULT NULL COMMENT '修改人',
  `YUF00W` datetime DEFAULT NULL COMMENT '修改时间',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT '个人标志码',
  `YUE048` varchar(6) DEFAULT NULL COMMENT '是否必扫（0-否，1-是）',
  `YUE049` varchar(36) DEFAULT NULL COMMENT '多选一标志',
  `YUE043` varchar(6) DEFAULT NULL COMMENT '是否为报表材料',
  `CHECKTYPE` varchar(6) DEFAULT NULL COMMENT '检查类型',
  `YUF221` varchar(200) DEFAULT NULL COMMENT '材料备注信息',
  `YUF222` varchar(20) DEFAULT NULL COMMENT '是否单位材料',
  `YUF223` varchar(6) DEFAULT NULL COMMENT '是否为复用材料',
  `YUF070` varchar(50) DEFAULT NULL COMMENT '材料复用版本流水号',
  `YUF071` varchar(100) DEFAULT NULL COMMENT '材料复用版本号',
  `YUE035` varchar(6) DEFAULT NULL COMMENT '材料主体（01-个人，02-单位）',
  PRIMARY KEY (`YUF220`),
  KEY `INDEX_UF22_YAZ001` (`YAZ001`),
  KEY `INDEX_UF22_YUF070` (`YUF070`),
  CONSTRAINT `F_UF22_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务受理档案材料信息表';

-- ----------------------------
-- Table structure for uf23
-- ----------------------------
DROP TABLE IF EXISTS `uf23`;
CREATE TABLE `uf23` (
  `YUF230` varchar(50) NOT NULL COMMENT '流水号',
  `AAZ002` varchar(50) DEFAULT NULL COMMENT '业务流水号',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '业务受理号',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '材料编码',
  `YUE031` text COMMENT '材料名称',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT '材料顺序号',
  `YUE045` decimal(6,0) DEFAULT NULL COMMENT '材料份数',
  `YUE046` decimal(6,0) DEFAULT NULL COMMENT '材料页数',
  `YUE047` varchar(6) DEFAULT NULL COMMENT '是否有实体材料',
  `YUF202` varchar(6) DEFAULT NULL COMMENT '是否为主文件（0-不是，1-是）',
  `AAE000` varchar(6) DEFAULT NULL COMMENT '材料有效标志',
  `YUF231` varchar(6) DEFAULT NULL COMMENT '归档资料类型',
  `YUF00V` varchar(50) DEFAULT NULL COMMENT '最后修改人部门',
  `YUF00U` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `YUF00W` datetime DEFAULT NULL COMMENT '最后修改时间',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUF230`),
  KEY `INDX_UF23_YAZ001` (`YAZ001`),
  KEY `F_UF21_AAZ002` (`AAZ002`),
  CONSTRAINT `F_UF21_AAZ002` FOREIGN KEY (`AAZ002`) REFERENCES `uf21` (`AAZ002`),
  CONSTRAINT `F_UF23_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务办理档案材料信息表';

-- ----------------------------
-- Table structure for uf24
-- ----------------------------
DROP TABLE IF EXISTS `uf24`;
CREATE TABLE `uf24` (
  `yuf240` varchar(50) NOT NULL COMMENT '流水号',
  `yuf052` varchar(100) DEFAULT NULL COMMENT '图片名称',
  `yuf051` int(10) DEFAULT NULL COMMENT '图片序号',
  `yaz001` varchar(50) DEFAULT NULL COMMENT '业务受理流水号',
  `yuf054` varchar(200) DEFAULT NULL COMMENT '文件存储KEY（缩略图）',
  `yuf055` varchar(500) DEFAULT NULL COMMENT '图片url（缩略图）',
  `yue030` varchar(50) DEFAULT NULL COMMENT '材料编码',
  `yuf053` varchar(6) DEFAULT NULL COMMENT '图片保存方式（0：磁盘；1：ucm）',
  `yuf001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `yae049` varchar(30) DEFAULT NULL COMMENT '功能编码',
  `aae011` varchar(50) DEFAULT NULL COMMENT '经办人编号',
  `aae017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `aae036` date DEFAULT NULL COMMENT '经办时间',
  `yub001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `yub004` varchar(6) DEFAULT NULL COMMENT '市级机构代码',
  `yuf056` int(8) DEFAULT NULL COMMENT '图片大小（缩略图）',
  `yuf05l` varchar(6) DEFAULT NULL COMMENT '图片高（像素）',
  `yuf05m` varchar(6) DEFAULT NULL COMMENT '图片宽（像素）',
  `yuf05j` varchar(6) DEFAULT NULL COMMENT '影像来源（1：远程，2：证照库，3：复用）',
  `yuf05k` varchar(6) DEFAULT NULL COMMENT '是否可编辑',
  `aae013` varchar(300) DEFAULT NULL COMMENT '备注',
  `yuf05p` varchar(500) DEFAULT NULL COMMENT '图片url(原图)',
  `yuf05q` varchar(200) DEFAULT NULL COMMENT '文件存储KEY(原图)',
  `yuf05r` int(8) DEFAULT NULL COMMENT '图片大小(原图)',
  `yuf04y` varchar(50) DEFAULT NULL COMMENT '个人标志码',
  `yuf241` varchar(6) DEFAULT NULL COMMENT '是否为图片',
  `yuf242` varchar(6) DEFAULT NULL COMMENT '是否有效：0 无效',
  `yuf060` varchar(20) DEFAULT NULL COMMENT '复用影像流水号',
  `yuf057` varchar(10) DEFAULT NULL COMMENT '文字识别标识',
  `yuf059` int(1) DEFAULT NULL COMMENT '旋转次数（-1,0,1,2）',
  `yut340` varchar(25) DEFAULT NULL COMMENT 'ocr日志操作id',
  `yuf05a` varchar(30) DEFAULT NULL COMMENT '图片唯一id',
  `uf24_time_index` datetime GENERATED ALWAYS AS (date_format(`aae036`,'%Y/%m/%d')) VIRTUAL,
  PRIMARY KEY (`yuf240`),
  KEY `INDX_UF24_01` (`yaz001`,`yuf242`),
  KEY `INDX_UF24_YAZ001` (`yaz001`),
  KEY `INDX_UF24_02` (`uf24_time_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uf25
-- ----------------------------
DROP TABLE IF EXISTS `uf25`;
CREATE TABLE `uf25` (
  `YUF250` varchar(50) NOT NULL COMMENT '流水号',
  `YAZ001` varchar(50) NOT NULL COMMENT '受理号',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB003` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(25) DEFAULT NULL COMMENT '公民身份号码',
  `AAC003` varchar(100) DEFAULT NULL COMMENT '个人姓名',
  `AAC021` varchar(50) DEFAULT NULL COMMENT '失业证编号',
  `YUF031` decimal(4,0) DEFAULT NULL COMMENT '页数',
  `YUF034` decimal(4,0) DEFAULT NULL COMMENT '序号',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT '个人标志码',
  `YUF004` varchar(6) DEFAULT NULL COMMENT '扫描状态',
  PRIMARY KEY (`YUF250`),
  KEY `INDX_UF25_YAZ001` (`YAZ001`),
  CONSTRAINT `F_UF25_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统接口一表多人人员信息';

-- ----------------------------
-- Table structure for uf26
-- ----------------------------
DROP TABLE IF EXISTS `uf26`;
CREATE TABLE `uf26` (
  `YUF260` varchar(25) NOT NULL COMMENT '影像操作信息流水号',
  `YAZ001` varchar(25) DEFAULT NULL COMMENT '受理编码',
  `YAE049` varchar(25) DEFAULT NULL COMMENT '业务类型',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(6) DEFAULT NULL COMMENT '市级机构代码',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF261` varchar(25) DEFAULT NULL COMMENT '许可码',
  `YUF262` varchar(6) DEFAULT NULL COMMENT '影像操作方式',
  `YUF263` decimal(6,0) DEFAULT NULL COMMENT '已使用次数',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE000` varchar(6) DEFAULT NULL COMMENT '是否有效',
  `YUF264` longblob COMMENT '正在查看的yaz001List',
  PRIMARY KEY (`YUF260`),
  KEY `INDX_UF26_YAZ001` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统影像调用信息记录表';

-- ----------------------------
-- Table structure for uf27
-- ----------------------------
DROP TABLE IF EXISTS `uf27`;
CREATE TABLE `uf27` (
  `YUF270` varchar(50) NOT NULL COMMENT 'sequence名称',
  `YUF271` decimal(8,0) DEFAULT NULL COMMENT 'sequence值',
  PRIMARY KEY (`YUF270`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='各内设机构sequence信息';

-- ----------------------------
-- Table structure for uf28
-- ----------------------------
DROP TABLE IF EXISTS `uf28`;
CREATE TABLE `uf28` (
  `YUF280` varchar(50) NOT NULL COMMENT '流水号',
  `AAZ002` varchar(50) NOT NULL COMMENT '业务登账日志ID',
  `AAB191` datetime DEFAULT NULL COMMENT '到账日期',
  `AAE019` decimal(16,2) DEFAULT NULL COMMENT '到账金额',
  `YAF010` varchar(50) DEFAULT NULL COMMENT '收款银行',
  `AAZ010` varchar(50) DEFAULT NULL COMMENT '当事人ID',
  `AAB069` text COMMENT '当事人名称',
  `AAE009` varchar(100) DEFAULT NULL COMMENT '对方户名',
  `AAE010` varchar(40) DEFAULT NULL COMMENT '对方帐号',
  `YAD008` text COMMENT '摘要信息',
  `YUF281` varchar(6) NOT NULL COMMENT '业务登账前缀',
  `YUF282` decimal(6,0) DEFAULT NULL COMMENT '附件页数',
  `YUF283` decimal(6,0) DEFAULT NULL COMMENT '附近影像数量',
  `YUF004` varchar(6) DEFAULT NULL COMMENT '扫描标志',
  `YUF284` varchar(50) DEFAULT NULL COMMENT '财务记账关联号',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '业务受理号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(25) DEFAULT NULL COMMENT '市级机构代码',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标注',
  PRIMARY KEY (`YUF280`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统接口财务登账信息';

-- ----------------------------
-- Table structure for uf29
-- ----------------------------
DROP TABLE IF EXISTS `uf29`;
CREATE TABLE `uf29` (
  `YUF290` varchar(50) NOT NULL COMMENT '业务记账会计科目明细号',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '财务受理号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(25) DEFAULT NULL COMMENT '市级机构代码',
  `YUF032` varchar(20) DEFAULT NULL COMMENT '会计科目代码',
  `YUF033` varchar(100) DEFAULT NULL COMMENT '会计科目名称',
  `YUF036` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `AAE013` text COMMENT '备注说明',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标注',
  `YUF035` varchar(6) DEFAULT NULL COMMENT '会计借贷方',
  PRIMARY KEY (`YUF290`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务记账会计目录信息';

-- ----------------------------
-- Table structure for uf2a
-- ----------------------------
DROP TABLE IF EXISTS `uf2a`;
CREATE TABLE `uf2a` (
  `YUF2A0` varchar(20) NOT NULL,
  `YAZ001` varchar(20) DEFAULT NULL COMMENT '业务编码',
  `YUF2A1` longblob COMMENT '输入数据',
  `YUF2A2` longblob COMMENT '输出数据',
  `YUF2A3` varchar(200) DEFAULT NULL COMMENT '调用方法',
  `YUF2A4` varchar(6) DEFAULT NULL COMMENT '是否正常返回',
  `AAE036` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`YUF2A0`),
  KEY `IDX_UF2A_01` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案接口日志操作记录';

-- ----------------------------
-- Table structure for ug30
-- ----------------------------
DROP TABLE IF EXISTS `ug30`;
CREATE TABLE `ug30` (
  `YUG300` varchar(20) NOT NULL COMMENT '影像检查流水号',
  `YUG301` varchar(6) DEFAULT NULL COMMENT '检查类型（一级、二级）',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUG305` varchar(100) DEFAULT NULL COMMENT '检查人',
  `YUG302` decimal(6,0) DEFAULT NULL COMMENT '检查数量',
  `YUG304` datetime DEFAULT NULL COMMENT '检查时间',
  `YUG303` text COMMENT '检查情况',
  `YUG306` decimal(3,0) DEFAULT NULL COMMENT '应质检百分比',
  `YUG307` decimal(3,0) DEFAULT NULL COMMENT '已质检百分比',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUG309` varchar(6) DEFAULT NULL COMMENT '检查状态（生成批次、完成批次、开始质检、完成质检）',
  `YUG308` varchar(6) DEFAULT NULL COMMENT '质检方式（逐页、平铺）',
  PRIMARY KEY (`YUG300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查批次';

-- ----------------------------
-- Table structure for ug31
-- ----------------------------
DROP TABLE IF EXISTS `ug31`;
CREATE TABLE `ug31` (
  `YUG310` varchar(50) NOT NULL COMMENT '检查明细_ID',
  `YUF002` varchar(50) DEFAULT NULL COMMENT '检查对象ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUG300` varchar(20) DEFAULT NULL COMMENT '检查批次_ID',
  `YUG312` varchar(6) DEFAULT NULL COMMENT '检查状态',
  `YUG311` decimal(6,0) DEFAULT NULL COMMENT '检查截止页',
  `YUG313` varchar(50) DEFAULT NULL COMMENT '检查截止对象',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUG316` varchar(6) DEFAULT NULL COMMENT '是否通过质检',
  `YUG317` varchar(200) DEFAULT NULL COMMENT '图片错误信息',
  `YUG318` varchar(6) DEFAULT NULL COMMENT '是否处理',
  `AAE036` datetime DEFAULT NULL COMMENT '完成质检时间',
  PRIMARY KEY (`YUG310`),
  KEY `INDEX_UG31_YUF002` (`YUF002`),
  KEY `INDEX_UG31_YUG300` (`YUG300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查明细';

-- ----------------------------
-- Table structure for ug32
-- ----------------------------
DROP TABLE IF EXISTS `ug32`;
CREATE TABLE `ug32` (
  `YUG320` varchar(50) NOT NULL COMMENT '检查明细_ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUF002` varchar(100) DEFAULT NULL COMMENT '档案对象ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '文件条码',
  `YUG325` varchar(6) DEFAULT NULL COMMENT '处理状态',
  `YUG322` char(1) DEFAULT NULL COMMENT '是否合格',
  `YUG323` varchar(6) DEFAULT NULL COMMENT '不合格类型',
  `YUG324` text COMMENT '不合格情况',
  `YUG326` decimal(6,0) DEFAULT NULL COMMENT '文件页码',
  `YUG327` text COMMENT '检查情况',
  `YUG328` varchar(100) DEFAULT NULL COMMENT '检查人',
  `AAE013` text COMMENT '备注',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUG310` varchar(50) NOT NULL COMMENT '检查明细_ID',
  `YUG300` varchar(20) DEFAULT NULL COMMENT '检查批次_ID',
  `YUF050` varchar(20) DEFAULT NULL COMMENT '图片信息流水号',
  PRIMARY KEY (`YUG320`),
  KEY `INDEX_UG32_YUF002` (`YUF002`),
  KEY `INDEX_UG32_YUF003` (`YUF003`),
  KEY `INDEX_UG32_YUG310` (`YUG310`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案检查记录';

-- ----------------------------
-- Table structure for ug33
-- ----------------------------
DROP TABLE IF EXISTS `ug33`;
CREATE TABLE `ug33` (
  `YUG330` varchar(20) NOT NULL COMMENT '案卷质检明细表',
  `YUG310` varchar(50) NOT NULL COMMENT '检查明细_ID',
  `YUF120` varchar(50) DEFAULT NULL COMMENT '检查案卷ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUG300` varchar(20) DEFAULT NULL COMMENT '检查批次_ID',
  `YUG312` varchar(6) DEFAULT NULL COMMENT '检查状态',
  `YUG311` decimal(6,0) DEFAULT NULL COMMENT '检查截止页',
  `YUF110` varchar(50) DEFAULT NULL COMMENT '检查文件ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUG316` varchar(6) DEFAULT NULL COMMENT '是否通过质检',
  `YUG317` varchar(200) DEFAULT NULL COMMENT '图片错误信息',
  `YUG318` varchar(6) DEFAULT NULL COMMENT '是否处理',
  `AAE036` datetime DEFAULT NULL COMMENT '完成质检时间',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '文件条码',
  PRIMARY KEY (`YUG330`),
  KEY `INDEX_UG33_YUF003` (`YUF003`),
  KEY `INDEX_UG33_YUF120` (`YUF120`),
  KEY `INDEX_UG33_YUG300` (`YUG300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='案卷质检明细表';

-- ----------------------------
-- Table structure for ug34
-- ----------------------------
DROP TABLE IF EXISTS `ug34`;
CREATE TABLE `ug34` (
  `YUG340` varchar(50) NOT NULL COMMENT '数字化加工流水号',
  `YUF002` varchar(100) DEFAULT NULL COMMENT '档案对象ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUG341` varchar(3) DEFAULT NULL COMMENT '操作类型',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUG300` varchar(50) DEFAULT NULL COMMENT '质检批次ID',
  `YUG310` varchar(50) DEFAULT NULL COMMENT '质检批次明细ID',
  PRIMARY KEY (`YUG340`),
  KEY `INDEX_UG34_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='质检后图片操作（补扫）信息表';

-- ----------------------------
-- Table structure for ug40
-- ----------------------------
DROP TABLE IF EXISTS `ug40`;
CREATE TABLE `ug40` (
  `YUG400` varchar(20) NOT NULL COMMENT '鉴定记录流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象',
  `YUG401` decimal(6,0) DEFAULT NULL COMMENT '鉴定数',
  `YUG403` char(1) DEFAULT NULL COMMENT '是否批准销毁',
  `YUG404` char(1) DEFAULT NULL COMMENT '是否授权修改保管期限',
  `YUG405` varchar(15) DEFAULT NULL COMMENT '批准人',
  `YUG406` varchar(100) DEFAULT NULL COMMENT '批准部门',
  `YUG407` datetime DEFAULT NULL COMMENT '批准日期',
  `YUG408` text COMMENT '批准意见',
  `YUG409` varchar(6) DEFAULT NULL COMMENT '鉴定状态：0 未鉴定 1 已鉴定未审核 2 已鉴定已审核 3 已执行销毁操作未执行延期操作\n4 已执行延期操作未执行销毁操作 5 全部操作完成，不能再进行更改',
  `YUG40A` varchar(25) DEFAULT NULL COMMENT '销毁人',
  `YUG40B` varchar(25) DEFAULT NULL COMMENT '监销人',
  `YUG40C` varchar(25) DEFAULT NULL COMMENT '延期人',
  `YUG40D` varchar(60) DEFAULT NULL COMMENT '销毁人部门',
  `YUG40E` varchar(60) DEFAULT NULL COMMENT '监销人部门',
  `YUG40F` varchar(60) DEFAULT NULL COMMENT '延期人部门',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUG40G` varchar(20) DEFAULT NULL COMMENT '鉴定人',
  `YUG40H` varchar(20) DEFAULT NULL COMMENT '鉴定部门',
  `YUG40I` datetime DEFAULT NULL COMMENT '鉴定时间',
  `YUF00H` varchar(20) DEFAULT NULL COMMENT '保管期限',
  PRIMARY KEY (`YUG400`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴定批次表';

-- ----------------------------
-- Table structure for ug42
-- ----------------------------
DROP TABLE IF EXISTS `ug42`;
CREATE TABLE `ug42` (
  `YUG420` varchar(20) NOT NULL COMMENT '鉴定明细流水号',
  `YUG400` varchar(20) DEFAULT NULL COMMENT '鉴定记录ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUF002` varchar(25) DEFAULT NULL COMMENT '档案对象流水号',
  `YUG422` varchar(6) DEFAULT NULL COMMENT '鉴定状态：0 未鉴定 1 已鉴定未审核 2 已鉴定已审核 3 已完成（执行销毁或延期）',
  `YUG421` varchar(6) DEFAULT NULL COMMENT '鉴定结果：0 延期 ， 1 销毁',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUG423` varchar(100) DEFAULT NULL COMMENT '鉴定意见',
  `YUG424` datetime DEFAULT NULL COMMENT '原来的保管结束日期',
  PRIMARY KEY (`YUG420`),
  KEY `INDEX_UG42_YUF002` (`YUF002`),
  KEY `INDEX_UG42_YUG400` (`YUG400`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴定明细';

-- ----------------------------
-- Table structure for ug43
-- ----------------------------
DROP TABLE IF EXISTS `ug43`;
CREATE TABLE `ug43` (
  `YUG430` varchar(20) NOT NULL COMMENT '保存期限调整流水号',
  `YUG400` varchar(20) DEFAULT NULL COMMENT '鉴定批次流水号',
  `YUG420` varchar(25) DEFAULT NULL COMMENT '鉴定明细表流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUF002` varchar(20) DEFAULT NULL COMMENT '档案对象ID',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUG436` varchar(6) DEFAULT NULL COMMENT '保存期限',
  `YUG431` varchar(6) DEFAULT NULL COMMENT '是否已执行',
  `YUG423` varchar(100) DEFAULT NULL COMMENT '鉴定意见',
  `AAE013` text COMMENT '备注',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUG430`),
  KEY `INDX_UG43_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保存期限调整记录';

-- ----------------------------
-- Table structure for ug44
-- ----------------------------
DROP TABLE IF EXISTS `ug44`;
CREATE TABLE `ug44` (
  `YUG440` varchar(10) NOT NULL COMMENT '成员id',
  `YUG450` varchar(10) DEFAULT NULL COMMENT '隶属鉴定小组id',
  `YUG441` varchar(60) DEFAULT NULL COMMENT '姓名',
  `YUG442` varchar(6) DEFAULT NULL COMMENT '性别',
  `YUG443` varchar(10) DEFAULT NULL COMMENT '直属组织',
  `YUG444` varchar(20) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`YUG440`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴定小组成员信息表';

-- ----------------------------
-- Table structure for ug45
-- ----------------------------
DROP TABLE IF EXISTS `ug45`;
CREATE TABLE `ug45` (
  `YUG450` varchar(20) NOT NULL COMMENT '鉴定小组ID',
  `YUG451` varchar(60) DEFAULT NULL COMMENT '鉴定小组名',
  `YUE001` varchar(10) DEFAULT NULL COMMENT '档案门类',
  `YUB001` varchar(10) DEFAULT NULL COMMENT '全宗',
  PRIMARY KEY (`YUG450`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='鉴定小组信息表';

-- ----------------------------
-- Table structure for ug46
-- ----------------------------
DROP TABLE IF EXISTS `ug46`;
CREATE TABLE `ug46` (
  `YUG460` varchar(20) NOT NULL COMMENT '销毁信息流水号',
  `YUG420` varchar(20) DEFAULT NULL COMMENT '鉴定明细ID',
  `YUG400` varchar(20) DEFAULT NULL COMMENT '鉴定批次流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUF002` varchar(20) DEFAULT NULL COMMENT '档案对象ID',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUG461` varchar(6) DEFAULT NULL COMMENT '是否已执行',
  `YUG423` varchar(100) DEFAULT NULL COMMENT '鉴定意见',
  `AAE013` text COMMENT '备注',
  `YUG466` varchar(60) DEFAULT NULL COMMENT '销毁原因',
  `YUG467` datetime DEFAULT NULL COMMENT '销毁时间',
  `YUG468` varchar(60) DEFAULT NULL COMMENT '销毁地点',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUG460`),
  KEY `INDX_UG64_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销毁信息记录表';

-- ----------------------------
-- Table structure for ug50
-- ----------------------------
DROP TABLE IF EXISTS `ug50`;
CREATE TABLE `ug50` (
  `YUG500` varchar(20) NOT NULL COMMENT '实体借阅明细流水号',
  `YUG520` varchar(20) DEFAULT NULL COMMENT '借阅信息流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案分类（2-案卷，3-文件，4-盒，5-箱）',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '条码',
  `YUG502` varchar(6) DEFAULT NULL COMMENT '是否归还（0-未归还，1-已归还）',
  `YUG503` datetime DEFAULT NULL COMMENT '实际归还日期',
  `YUG504` varchar(6) DEFAULT NULL COMMENT '延期标识',
  `YUG505` varchar(6) DEFAULT NULL COMMENT '损坏标识',
  `YUG506` varchar(6) DEFAULT NULL COMMENT '丢失标识',
  `YUG507` decimal(6,0) DEFAULT NULL COMMENT '延期次数',
  `YUG508` varchar(6) DEFAULT NULL COMMENT '催还标识',
  `YUG509` decimal(6,0) DEFAULT NULL COMMENT '催还次数',
  `YUG527` datetime DEFAULT NULL COMMENT '应归还日期',
  `YUB001` varchar(50) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUG500`),
  KEY `INDX_UG50_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体借阅明细表';

-- ----------------------------
-- Table structure for ug52
-- ----------------------------
DROP TABLE IF EXISTS `ug52`;
CREATE TABLE `ug52` (
  `YUG520` varchar(15) NOT NULL COMMENT '借阅信息流水号',
  `YUG521` varchar(6) DEFAULT NULL COMMENT '审核标识(0-未审核，1-审核通过，2-审核不通过)',
  `YUG522` varchar(6) DEFAULT NULL COMMENT '借出标识',
  `YUG526` varchar(6) DEFAULT NULL COMMENT '归还标识(0-未归还，1-已归还，2-部分归还)',
  `YUG523` varchar(20) DEFAULT NULL COMMENT '审核人',
  `YUG524` varchar(20) DEFAULT NULL COMMENT '审核机构',
  `YUG525` datetime DEFAULT NULL COMMENT '审核时间',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '申请人姓名',
  `AAC002` varchar(20) DEFAULT NULL COMMENT '申请人身份证编号',
  `AAB004` text COMMENT '申请人单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '申请人单位编号',
  `YUG52C` varchar(50) DEFAULT NULL COMMENT '申请原因',
  `YUG527` datetime DEFAULT NULL COMMENT '应归还日期',
  `YUG528` decimal(6,0) DEFAULT NULL COMMENT '借阅文件份数',
  `YUG529` decimal(6,0) DEFAULT NULL COMMENT '归还文件份数',
  `YUG52A` varchar(6) DEFAULT NULL COMMENT '借阅类型(0-个人，1-单位)',
  `YUG52B` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUG520`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体借阅批次表';

-- ----------------------------
-- Table structure for ug53
-- ----------------------------
DROP TABLE IF EXISTS `ug53`;
CREATE TABLE `ug53` (
  `YUG530` varchar(20) NOT NULL COMMENT '催还信息流水号',
  `YUG520` varchar(50) DEFAULT NULL COMMENT '借阅批次流水号',
  `YUG532` varchar(20) DEFAULT NULL COMMENT '催还方式',
  `YUG533` varchar(20) DEFAULT NULL COMMENT '催还人',
  `YUG534` datetime DEFAULT NULL COMMENT '催还日期',
  `YUG535` text COMMENT '催还情况',
  `YUG536` datetime DEFAULT NULL COMMENT '预计归还日期',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUG530`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='催还信息表';

-- ----------------------------
-- Table structure for ug54
-- ----------------------------
DROP TABLE IF EXISTS `ug54`;
CREATE TABLE `ug54` (
  `YUG540` varchar(20) NOT NULL COMMENT '借阅延期流水号',
  `YUG520` varchar(20) DEFAULT NULL COMMENT '借阅批次流水号',
  `YUG542` datetime DEFAULT NULL COMMENT '原应归还时间',
  `YUG543` datetime DEFAULT NULL COMMENT '现应归还时间',
  `YUG544` varchar(100) DEFAULT NULL COMMENT '延期原因',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUG540`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='借阅延期信息表';

-- ----------------------------
-- Table structure for ug55
-- ----------------------------
DROP TABLE IF EXISTS `ug55`;
CREATE TABLE `ug55` (
  `YUG550` varchar(20) NOT NULL COMMENT '实体借阅归还流水号',
  `YUG500` varchar(20) DEFAULT NULL COMMENT '实体借阅明细流水号',
  `YUG551` datetime DEFAULT NULL COMMENT '归还日期',
  `YUG520` varchar(50) DEFAULT NULL COMMENT '实体借阅流水号',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUE001` varchar(10) DEFAULT NULL COMMENT '档案门类',
  PRIMARY KEY (`YUG550`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实体借阅归还表';

-- ----------------------------
-- Table structure for ug56
-- ----------------------------
DROP TABLE IF EXISTS `ug56`;
CREATE TABLE `ug56` (
  `YUE001` varchar(20) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(20) DEFAULT NULL COMMENT '档案类型',
  `YUG560` varchar(20) NOT NULL COMMENT '流水号',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '条码',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办单位',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUE002` varchar(20) DEFAULT NULL COMMENT '档案类别',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '业务环节编号',
  PRIMARY KEY (`YUG560`),
  KEY `INDX_UG56_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件影像查看记录表';

-- ----------------------------
-- Table structure for ug57
-- ----------------------------
DROP TABLE IF EXISTS `ug57`;
CREATE TABLE `ug57` (
  `YUG570` varchar(25) NOT NULL COMMENT '记录流水号',
  `YUG560` varchar(25) NOT NULL COMMENT '影像查看流水号',
  `YUF050` varchar(20) DEFAULT NULL COMMENT '影像流水号',
  `YUG571` decimal(6,0) DEFAULT NULL COMMENT '影像查看次数',
  `YUE030` varchar(50) DEFAULT NULL COMMENT '业务材料流水号',
  PRIMARY KEY (`YUG570`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影像材料明细表';

-- ----------------------------
-- Table structure for ug58
-- ----------------------------
DROP TABLE IF EXISTS `ug58`;
CREATE TABLE `ug58` (
  `YUG580` varchar(15) NOT NULL COMMENT '流水号',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '利用人姓名',
  `AAC002` varchar(20) DEFAULT NULL COMMENT '利用人身份证编号',
  `AAB004` varchar(20) DEFAULT NULL COMMENT '利用人单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '利用人单位编号',
  `YUG581` varchar(6) DEFAULT NULL COMMENT '利用方式（0：阅览 1：打印）',
  `YUG582` text COMMENT '利用目的',
  `YUG583` text COMMENT '保管单位负责人意见',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  `YUE001` varchar(10) DEFAULT NULL COMMENT '档案门类',
  PRIMARY KEY (`YUG580`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影像调阅登记表';

-- ----------------------------
-- Table structure for ug59
-- ----------------------------
DROP TABLE IF EXISTS `ug59`;
CREATE TABLE `ug59` (
  `YUG590` varchar(15) NOT NULL,
  `YUG580` varchar(15) NOT NULL,
  `YUF003` varchar(50) DEFAULT NULL COMMENT '文件条码',
  `YUF00P` decimal(4,0) DEFAULT NULL COMMENT '扫描页数',
  PRIMARY KEY (`YUG590`),
  KEY `INDX_UG59_YUF003` (`YUF003`),
  KEY `FK_UG58` (`YUG580`),
  CONSTRAINT `FK_UG58` FOREIGN KEY (`YUG580`) REFERENCES `ug58` (`YUG580`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影像调阅明细表';

-- ----------------------------
-- Table structure for ug60
-- ----------------------------
DROP TABLE IF EXISTS `ug60`;
CREATE TABLE `ug60` (
  `YUG600` varchar(20) NOT NULL COMMENT 'ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `AAE017` varchar(200) DEFAULT NULL COMMENT '经办机构',
  `AAE011` varchar(200) DEFAULT NULL COMMENT '经办人',
  `YUG601` decimal(6,0) DEFAULT NULL COMMENT '移交数量',
  `YUG602` decimal(6,0) DEFAULT NULL COMMENT '接收数量',
  `YUG603` decimal(6,0) DEFAULT NULL COMMENT '回退数量',
  `YUG604` varchar(200) DEFAULT NULL COMMENT '移交原因',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUG605` varchar(200) DEFAULT NULL COMMENT '回退原因',
  `YUG606` varchar(200) DEFAULT NULL COMMENT '接收单位',
  `YUG607` varchar(50) DEFAULT NULL COMMENT '接收人',
  `YUG608` datetime DEFAULT NULL COMMENT '接收时间',
  `YUG60A` varchar(6) DEFAULT NULL COMMENT '是否监交通过',
  `YUG60B` varchar(200) DEFAULT NULL COMMENT '监交拒绝原因',
  `YUG60C` varchar(200) DEFAULT NULL COMMENT '监交单位',
  `YUG60D` varchar(50) DEFAULT NULL COMMENT '监交人',
  `YUG60E` datetime DEFAULT NULL COMMENT '监交时间',
  `YUG609` varchar(6) DEFAULT NULL COMMENT '交接标志（0-未交接，1-交接中,2-已回退，3-已接收）',
  `YUB001` varchar(50) DEFAULT NULL COMMENT '全宗号',
  `YUG60O` varchar(6) DEFAULT NULL COMMENT '移交类型',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUB004` varchar(10) DEFAULT NULL COMMENT '市级机构代码',
  `YUG60F` varchar(60) DEFAULT NULL COMMENT '接收人姓名',
  `AAE012` varchar(60) DEFAULT NULL COMMENT '经办人姓名',
  PRIMARY KEY (`YUG600`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移交信息主表';

-- ----------------------------
-- Table structure for ug61
-- ----------------------------
DROP TABLE IF EXISTS `ug61`;
CREATE TABLE `ug61` (
  `YUG610` varchar(20) NOT NULL COMMENT '移交明细流水号',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUF002` varchar(50) DEFAULT NULL COMMENT '档案对象ID',
  `YUG600` varchar(15) DEFAULT NULL COMMENT '移交记录ID',
  `YUG611` varchar(6) DEFAULT NULL COMMENT '是否接收',
  `YUG612` varchar(200) DEFAULT NULL COMMENT '回退原因',
  `YUG606` varchar(20) DEFAULT NULL COMMENT '接收单位',
  `YUG607` varchar(15) DEFAULT NULL COMMENT '接收人',
  `YUG608` datetime DEFAULT NULL COMMENT '接收时间',
  `YUG613` varchar(6) DEFAULT NULL COMMENT '是否处理',
  `YUG609` varchar(6) DEFAULT NULL COMMENT '交接标志（0-未交接，1-交接中,2-已回退，3-已接收）\n',
  PRIMARY KEY (`YUG610`),
  KEY `INDX_UG61_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移交明细';

-- ----------------------------
-- Table structure for ug62
-- ----------------------------
DROP TABLE IF EXISTS `ug62`;
CREATE TABLE `ug62` (
  `YUG620` varchar(200) NOT NULL COMMENT '转移记录流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUG622` varchar(6) DEFAULT NULL COMMENT '转移标志',
  `YUG623` decimal(6,0) DEFAULT NULL COMMENT '转移案卷卷数',
  `YUG624` decimal(6,0) DEFAULT NULL COMMENT '文件份数',
  `YUG625` varchar(15) DEFAULT NULL COMMENT '接收人',
  `YUG626` varchar(100) DEFAULT NULL COMMENT '接收部门',
  `YUG627` datetime DEFAULT NULL COMMENT '接收日期',
  `YUG628` varchar(100) DEFAULT NULL COMMENT '移交原因',
  `YUG629` varchar(50) DEFAULT NULL COMMENT '移交单号',
  `AAE011` varchar(15) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(200) DEFAULT NULL COMMENT '经办单位',
  `YUG621` varchar(100) DEFAULT NULL COMMENT '转移文件份数',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUG620`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转移记录';

-- ----------------------------
-- Table structure for ug63
-- ----------------------------
DROP TABLE IF EXISTS `ug63`;
CREATE TABLE `ug63` (
  `YUG630` varchar(20) NOT NULL COMMENT '转移明细流水号',
  `YUG620` varchar(20) DEFAULT NULL COMMENT '转移批次流水号',
  `YUF002` varchar(25) DEFAULT NULL COMMENT '转移对象ID',
  `YUG622` varchar(6) DEFAULT NULL COMMENT '转移标识',
  PRIMARY KEY (`YUG630`),
  KEY `INDX_UG63_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='转移明细';

-- ----------------------------
-- Table structure for ug64
-- ----------------------------
DROP TABLE IF EXISTS `ug64`;
CREATE TABLE `ug64` (
  `YUG640` varchar(20) NOT NULL COMMENT '交接单流水号',
  `YUG641` varchar(50) NOT NULL COMMENT '交接单号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT '批次总页数',
  `YUG642` datetime DEFAULT NULL COMMENT '文件办结截止时间',
  `YUG643` varchar(6) DEFAULT NULL COMMENT '交接标志（0-未交接，1-已交接，2-已接收，3-已回退）',
  `YUG644` datetime DEFAULT NULL COMMENT '确认时间',
  `YUG646` text COMMENT '回退原因',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUE01C` varchar(6) DEFAULT NULL COMMENT '是否有效',
  `YUG64B` varchar(200) DEFAULT NULL COMMENT '备注',
  `YUF00N` decimal(4,0) DEFAULT NULL COMMENT '批次总份数',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `YUG645` datetime DEFAULT NULL COMMENT '接收时间',
  `YUG647` varchar(50) DEFAULT NULL COMMENT '接收人',
  `YUG648` varchar(50) DEFAULT NULL COMMENT '接收部门',
  `YUG649` varchar(6) DEFAULT NULL COMMENT '交接类型',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `YUG64A` varchar(50) DEFAULT NULL COMMENT '接收人姓名',
  `YUB004` varchar(50) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUG640`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务系统材料交接单批次新';

-- ----------------------------
-- Table structure for ug65
-- ----------------------------
DROP TABLE IF EXISTS `ug65`;
CREATE TABLE `ug65` (
  `YUG650` varchar(20) NOT NULL COMMENT '交接单明细流水号',
  `YUG640` varchar(20) DEFAULT NULL COMMENT '交接单流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型 ',
  `YUE013` varchar(18) DEFAULT NULL COMMENT '案卷类别',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT '页数 ',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB003` varchar(50) DEFAULT NULL COMMENT '单位代码',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(25) DEFAULT NULL COMMENT '公民身份号码',
  `AAC003` varchar(100) DEFAULT NULL COMMENT '个人姓名',
  `YUE010` text COMMENT '业务环节',
  `YUG643` varchar(6) DEFAULT NULL COMMENT '交接标志（1-未交接，2-已交接,3-交接退回,4-已接收，5-已回退）',
  `YUE01C` varchar(6) DEFAULT NULL COMMENT '是否有效',
  `YUG64B` varchar(200) DEFAULT NULL COMMENT '备注',
  `YUG653` datetime DEFAULT NULL COMMENT '接收时间',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT '业务受理号',
  `YUG651` varchar(50) DEFAULT NULL COMMENT '接收人',
  `YUG652` varchar(50) DEFAULT NULL COMMENT '接收部门',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '文件条码',
  `AAE002` varchar(50) DEFAULT NULL COMMENT '办结人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUG650`),
  KEY `INDX_UG65_YAZ001` (`YAZ001`),
  KEY `INDX_UG65_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务材料交接明细';

-- ----------------------------
-- Table structure for ug69
-- ----------------------------
DROP TABLE IF EXISTS `ug69`;
CREATE TABLE `ug69` (
  `YUG690` varchar(20) NOT NULL COMMENT '交接单流水号',
  `YUG691` varchar(50) DEFAULT NULL COMMENT '交接单号',
  `YUG692` varchar(50) DEFAULT NULL COMMENT '申请人姓名',
  `YUG693` varchar(18) DEFAULT NULL COMMENT '申请人身份证号',
  `YUG694` varchar(50) DEFAULT NULL COMMENT '申请人单位名称',
  `YUG695` varchar(200) DEFAULT NULL COMMENT '委托存档单位名称',
  `YUG696` varchar(6) DEFAULT NULL COMMENT '委托存档单位行政区划代码',
  `YUG697` varchar(200) DEFAULT NULL COMMENT '原存档单位名称',
  `YUG698` varchar(20) DEFAULT NULL COMMENT '原存档单位编号',
  `YUG699` varchar(6) DEFAULT NULL COMMENT '原存档单位行政区划代码',
  `YUG69A` varchar(200) DEFAULT NULL COMMENT '转往单位名称',
  `YUG69B` varchar(20) DEFAULT NULL COMMENT '转往单位编号',
  `YUG69C` varchar(6) DEFAULT NULL COMMENT '转往单位行政区划代码',
  `YUG69D` varchar(2) DEFAULT NULL COMMENT '存档性质（01单位委托、02个人委托）',
  `YUG69E` varchar(2) DEFAULT NULL COMMENT '申请类型（01接受申请、02移交申请）',
  `YUG69F` text COMMENT '交接原因',
  `YUG69G` varchar(2) DEFAULT NULL COMMENT '交接标志（00未交接、01已移交、02已接收、03已回退）',
  `YUF110` varchar(20) DEFAULT NULL COMMENT '文件主表流水号',
  `AAC003` varchar(50) DEFAULT NULL COMMENT '姓名',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(6) DEFAULT NULL COMMENT '民族',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `YUF00K` decimal(65,30) DEFAULT NULL COMMENT '页数',
  `YUF00J` decimal(65,30) DEFAULT NULL COMMENT '份数',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUG69H` varchar(20) DEFAULT NULL COMMENT '接收人',
  `YUG69I` varchar(20) DEFAULT NULL COMMENT '接收部门',
  `YUG69J` datetime DEFAULT NULL COMMENT '接收时间',
  `YUG69K` varchar(20) DEFAULT NULL COMMENT '移交人',
  `YUG69M` varchar(20) DEFAULT NULL COMMENT '移交部门',
  `YUG69N` datetime DEFAULT NULL COMMENT '移交时间',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办人部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(2) DEFAULT NULL COMMENT '有效标志',
  `YUG69P` text COMMENT '备注',
  `YUG69Q` varchar(2) DEFAULT NULL COMMENT '是否在同一系统内',
  PRIMARY KEY (`YUG690`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事档案交接申请表';

-- ----------------------------
-- Table structure for ug6a
-- ----------------------------
DROP TABLE IF EXISTS `ug6a`;
CREATE TABLE `ug6a` (
  `YUG6A0` varchar(20) NOT NULL COMMENT '审核流水号',
  `YUG691` varchar(50) DEFAULT NULL COMMENT '交接单号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUG6A1` varchar(50) DEFAULT NULL COMMENT '开具单位名称',
  `YUG6A2` varchar(50) DEFAULT NULL COMMENT '接收单位名称',
  `YUG6A3` text COMMENT '事因（码表）',
  `YUG6A4` varchar(2) DEFAULT NULL COMMENT '审核报表类型（01调档函、02调档函回函、03转递通知单、04转递通知单回执）',
  `YUG6A5` datetime DEFAULT NULL COMMENT '开具日期',
  `YUG6A6` datetime DEFAULT NULL COMMENT '接收日期',
  `YUG6A7` varchar(20) DEFAULT NULL COMMENT '开具人姓名',
  `YUG6A8` text COMMENT '备注',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUG6A0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事档案交接审核表';

-- ----------------------------
-- Table structure for ug6c
-- ----------------------------
DROP TABLE IF EXISTS `ug6c`;
CREATE TABLE `ug6c` (
  `YUG6C0` varchar(20) NOT NULL COMMENT '接转流水号',
  `YUG6C1` varchar(30) DEFAULT NULL COMMENT '接转单号',
  `YUR001` varchar(20) DEFAULT NULL COMMENT '申请人姓名',
  `YUR002` varchar(20) DEFAULT NULL COMMENT '申请人身份证号',
  `YUR003` varchar(50) DEFAULT NULL COMMENT '申请人单位名称',
  `YUR004` datetime DEFAULT NULL COMMENT '申请日期',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `YUG6C5` varchar(50) DEFAULT NULL COMMENT '转出单位名称',
  `YUG6C6` varchar(50) DEFAULT NULL COMMENT '转入单位名称',
  `YUG6C7` varchar(2) DEFAULT NULL COMMENT '接转类型（01转出申请、02转入申请）',
  `YUG6C8` text COMMENT '备注',
  `YUG6C9` varchar(2) DEFAULT NULL COMMENT '是否完成',
  `AAE100` varchar(2) DEFAULT NULL COMMENT '是否有效',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUG69Q` varchar(20) DEFAULT NULL COMMENT '是否在同一系统内',
  PRIMARY KEY (`YUG6C0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='党员组织关系接转表';

-- ----------------------------
-- Table structure for uh70
-- ----------------------------
DROP TABLE IF EXISTS `uh70`;
CREATE TABLE `uh70` (
  `YUB001` varchar(6) NOT NULL COMMENT '全宗号',
  `YUH701` varchar(15) NOT NULL COMMENT '库房代码',
  `YUH702` varchar(100) DEFAULT NULL COMMENT '库房名称',
  `YUH703` text COMMENT '图形信息',
  `AAE013` varchar(200) DEFAULT NULL COMMENT '备注',
  `AAE011` varchar(15) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUH704` varchar(6) DEFAULT NULL COMMENT '水平排列方式',
  `YUH705` varchar(6) DEFAULT NULL COMMENT '垂直排列方式',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  PRIMARY KEY (`YUH701`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库房信息';

-- ----------------------------
-- Table structure for uh71
-- ----------------------------
DROP TABLE IF EXISTS `uh71`;
CREATE TABLE `uh71` (
  `YUH710` varchar(15) NOT NULL COMMENT '区域代码',
  `YUB001` varchar(6) NOT NULL COMMENT '全宗号',
  `YUH701` varchar(15) NOT NULL COMMENT '库房代码',
  `YUH711` varchar(50) DEFAULT NULL COMMENT '区域名称',
  `YUH703` text COMMENT '图形信息',
  `AAE013` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`YUH710`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域信息(上级单位UH70库房信息)';

-- ----------------------------
-- Table structure for uh74
-- ----------------------------
DROP TABLE IF EXISTS `uh74`;
CREATE TABLE `uh74` (
  `YUH740` varchar(15) NOT NULL COMMENT '联代码',
  `YUH701` varchar(15) NOT NULL COMMENT '库房代码',
  `YUH710` varchar(15) NOT NULL COMMENT '区域代码',
  `YUH770` varchar(15) NOT NULL COMMENT '组代码',
  `YUH760` varchar(15) NOT NULL COMMENT '架代码',
  `YUH741` varchar(6) NOT NULL COMMENT 'AB面',
  `YUB001` varchar(20) NOT NULL COMMENT '全宗号',
  `YUH742` varchar(15) DEFAULT NULL COMMENT '联名称',
  `YUH743` decimal(6,0) NOT NULL COMMENT '层数',
  `YUH703` text COMMENT '图形信息',
  `YUH744` decimal(6,0) NOT NULL COMMENT '排序号',
  `AAE013` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`YUH740`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联信息(上级单位UH76架信息)';

-- ----------------------------
-- Table structure for uh75
-- ----------------------------
DROP TABLE IF EXISTS `uh75`;
CREATE TABLE `uh75` (
  `YUH750` varchar(15) NOT NULL COMMENT '格条码',
  `YUB001` varchar(6) NOT NULL COMMENT '全宗号',
  `YUH701` varchar(15) NOT NULL COMMENT '库房代码',
  `YUH710` varchar(15) NOT NULL COMMENT '区域代码',
  `YUH770` varchar(15) NOT NULL COMMENT '组代码',
  `YUH760` varchar(15) NOT NULL COMMENT '架代码',
  `YUH741` varchar(6) NOT NULL COMMENT 'AB面',
  `YUH751` varchar(50) DEFAULT NULL COMMENT '格名称',
  `YUH744` decimal(6,0) NOT NULL COMMENT '排序号',
  `YUH752` decimal(6,0) NOT NULL COMMENT '案卷存储容量',
  `YUH753` decimal(6,0) NOT NULL COMMENT '箱存储容量',
  `YUH754` varchar(6) NOT NULL COMMENT '存储状态',
  `YUH703` text COMMENT '图形信息',
  `YUH740` varchar(15) NOT NULL COMMENT '联代码',
  `AAE013` varchar(200) DEFAULT NULL COMMENT '备注',
  `YUB004` varchar(10) DEFAULT NULL COMMENT '市级机构代码',
  PRIMARY KEY (`YUH750`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='格信息(上级单位UH74联信息)';

-- ----------------------------
-- Table structure for uh76
-- ----------------------------
DROP TABLE IF EXISTS `uh76`;
CREATE TABLE `uh76` (
  `YUH760` varchar(15) NOT NULL COMMENT '架代码',
  `YUH741` varchar(6) NOT NULL COMMENT 'AB面',
  `YUB001` varchar(20) NOT NULL COMMENT '全宗号',
  `YUH701` varchar(15) NOT NULL COMMENT '库房代码',
  `YUH710` varchar(15) NOT NULL COMMENT '区域代码',
  `YUH770` varchar(15) NOT NULL COMMENT '组代码',
  `YUH742` varchar(15) DEFAULT NULL COMMENT '联名称',
  `YUH761` decimal(6,0) NOT NULL COMMENT '联数',
  `YUH703` text COMMENT '图形信息',
  `YUH744` decimal(6,0) NOT NULL COMMENT '排序号',
  `AAE013` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`YUH760`,`YUH741`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='架信息(上级单位UH77组信息)';

-- ----------------------------
-- Table structure for uh77
-- ----------------------------
DROP TABLE IF EXISTS `uh77`;
CREATE TABLE `uh77` (
  `YUH770` varchar(15) NOT NULL COMMENT '组代码',
  `YUH771` varchar(6) NOT NULL COMMENT '组类型',
  `YUB001` varchar(20) NOT NULL COMMENT '全宗号',
  `YUH701` varchar(15) NOT NULL COMMENT '库房代码',
  `YUH710` varchar(15) NOT NULL COMMENT '区域代码',
  `YUH772` varchar(50) DEFAULT NULL COMMENT '组名称',
  `YUH773` varchar(100) DEFAULT NULL COMMENT '绘图坐标',
  `YUH774` decimal(6,0) DEFAULT NULL COMMENT '绘图角度',
  `YUH775` decimal(6,0) NOT NULL COMMENT '列数',
  `YUH703` text COMMENT '图形信息',
  `AAE013` varchar(200) DEFAULT NULL COMMENT '备注',
  `YUH761` decimal(6,0) DEFAULT NULL COMMENT '联数',
  `YUH743` decimal(6,0) DEFAULT NULL COMMENT '层数',
  PRIMARY KEY (`YUH770`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组信息(上级单位UH71区域信息)';

-- ----------------------------
-- Table structure for uh78
-- ----------------------------
DROP TABLE IF EXISTS `uh78`;
CREATE TABLE `uh78` (
  `YUH701` varchar(15) DEFAULT NULL COMMENT '库房代码',
  `YUH781` varchar(20) DEFAULT NULL COMMENT '温度',
  `YUH782` varchar(20) DEFAULT NULL COMMENT '湿度',
  `YUH783` datetime DEFAULT NULL COMMENT '监控时间',
  `AAE013` text COMMENT '备注',
  `AAE011` varchar(100) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办日期',
  `YUB001` varchar(20) DEFAULT NULL COMMENT '全宗号',
  `YUH78A` varchar(6) DEFAULT NULL COMMENT '空调',
  `YUH78B` varchar(6) DEFAULT NULL COMMENT '除湿机',
  `YUH78C` varchar(6) DEFAULT NULL COMMENT '加湿机',
  `YUH78D` varchar(6) DEFAULT NULL COMMENT '其他'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uh79
-- ----------------------------
DROP TABLE IF EXISTS `uh79`;
CREATE TABLE `uh79` (
  `YUH790` varchar(20) NOT NULL COMMENT '出库记录流水号',
  `YUF002` varchar(50) DEFAULT NULL COMMENT '档案对象ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象类型',
  `YUH793` varchar(20) DEFAULT NULL COMMENT '出库类型',
  `AAE036` datetime DEFAULT NULL COMMENT '出库时间',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `YUH794` text COMMENT '出库原因',
  `YAE116` varchar(20) DEFAULT NULL COMMENT '经办人ID',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  PRIMARY KEY (`YUH790`),
  KEY `INDX_UH79_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库信息记录';

-- ----------------------------
-- Table structure for uh7b
-- ----------------------------
DROP TABLE IF EXISTS `uh7b`;
CREATE TABLE `uh7b` (
  `YUH7B0` varchar(20) NOT NULL COMMENT '移库登记流水号',
  `YUF011` varchar(25) DEFAULT NULL COMMENT '条码',
  `YUH7B5` varchar(20) DEFAULT NULL COMMENT '原格条码',
  `YUH7B6` decimal(65,30) DEFAULT NULL COMMENT '原格内序号',
  `YUH7B7` varchar(20) DEFAULT NULL COMMENT '现格条码',
  `YUH7B8` decimal(65,30) DEFAULT NULL COMMENT '现格内序号',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YAE116` varchar(20) DEFAULT NULL COMMENT '经办人ID',
  PRIMARY KEY (`YUH7B0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移库登记表';

-- ----------------------------
-- Table structure for uh7g
-- ----------------------------
DROP TABLE IF EXISTS `uh7g`;
CREATE TABLE `uh7g` (
  `YUH7G0` varchar(20) NOT NULL COMMENT '档案统计表流水号',
  `YUH7G1` varchar(20) DEFAULT NULL COMMENT '上期末卷数',
  `YUH7G2` varchar(20) DEFAULT NULL COMMENT '上期末件数',
  `YUH7G3` varchar(20) DEFAULT NULL COMMENT '上期末页数',
  `YUH7G4` varchar(20) DEFAULT NULL COMMENT '本期新增卷数',
  `YUH7G5` varchar(20) DEFAULT NULL COMMENT '本期新增件数',
  `YUH7G6` varchar(20) DEFAULT NULL COMMENT '本期新增页数',
  `YUH7G7` varchar(20) DEFAULT NULL COMMENT '本期减少卷数',
  `YUH7G8` varchar(20) DEFAULT NULL COMMENT '本期减少件数',
  `YUH7G9` varchar(20) DEFAULT NULL COMMENT '本期减少页数',
  `YUH7GA` varchar(20) DEFAULT NULL COMMENT '本期末卷数',
  `YUH7GB` varchar(20) DEFAULT NULL COMMENT '本期末件数',
  `YUH7GC` varchar(20) DEFAULT NULL COMMENT '本期末页数',
  `YUF00E` varchar(20) DEFAULT NULL COMMENT '年度',
  `YUF00F` varchar(20) DEFAULT NULL COMMENT '月份',
  `YUH701` varchar(20) DEFAULT NULL COMMENT '库房代码',
  `YUE002` varchar(20) DEFAULT NULL COMMENT '档案类别',
  `YUF00H` varchar(20) DEFAULT NULL COMMENT '保管期限',
  `YUB001` varchar(15) DEFAULT NULL COMMENT '分中心',
  PRIMARY KEY (`YUH7G0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uh7h
-- ----------------------------
DROP TABLE IF EXISTS `uh7h`;
CREATE TABLE `uh7h` (
  `YUE002` varchar(10) DEFAULT NULL COMMENT '档案类别',
  `YUF00H` varchar(10) DEFAULT NULL COMMENT '保管期限',
  `YUH7H0` varchar(10) DEFAULT NULL COMMENT '保管期限顺序',
  `YUH7H1` varchar(10) DEFAULT NULL COMMENT '流水号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社会保险档案月（年）统计表';

-- ----------------------------
-- Table structure for uh7j
-- ----------------------------
DROP TABLE IF EXISTS `uh7j`;
CREATE TABLE `uh7j` (
  `YUH7J0` varchar(10) DEFAULT NULL COMMENT '档案类别顺序',
  `YUE002` varchar(10) DEFAULT NULL COMMENT '档案类别'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案类别顺序';

-- ----------------------------
-- Table structure for uh7k
-- ----------------------------
DROP TABLE IF EXISTS `uh7k`;
CREATE TABLE `uh7k` (
  `YUH7K0` varchar(10) NOT NULL COMMENT '设备编号',
  `YUH7K1` varchar(60) DEFAULT NULL COMMENT '设备名称',
  `YUH7K2` varchar(10) DEFAULT NULL COMMENT '设备类型',
  `YUH7K3` varchar(60) DEFAULT NULL COMMENT '设备类型名称',
  `YUH7K4` datetime DEFAULT NULL COMMENT '设备投入使用日期',
  PRIMARY KEY (`YUH7K0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库房设备信息表';

-- ----------------------------
-- Table structure for uh7l
-- ----------------------------
DROP TABLE IF EXISTS `uh7l`;
CREATE TABLE `uh7l` (
  `YUH701` varchar(10) DEFAULT NULL COMMENT '库房编号',
  `YUH7K0` varchar(40) DEFAULT NULL COMMENT '设备编号',
  `YUH7K1` varchar(60) DEFAULT NULL COMMENT '设备名称',
  `YUH7K2` varchar(10) DEFAULT NULL COMMENT '设备类型',
  `YUH7K4` datetime DEFAULT NULL COMMENT '设备投入使用日期',
  `YUH7L0` varchar(20) NOT NULL COMMENT '库房设备主键',
  PRIMARY KEY (`YUH7L0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库房设备关联信息表';

-- ----------------------------
-- Table structure for uh7m
-- ----------------------------
DROP TABLE IF EXISTS `uh7m`;
CREATE TABLE `uh7m` (
  `YUH7M0` varchar(20) NOT NULL COMMENT '监控信息表主键',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `YUB004` varchar(20) DEFAULT NULL COMMENT '市级机构代码',
  `YUH701` varchar(15) DEFAULT NULL COMMENT '库房编号',
  `YUH7M1` datetime DEFAULT NULL COMMENT '监控开始时间',
  `YUH7M2` datetime DEFAULT NULL COMMENT '监控结束时间',
  `YUH7M3` varchar(40) DEFAULT NULL COMMENT '监控人员',
  `YUH7M4` varchar(6) DEFAULT NULL COMMENT '是否投放灭鼠药',
  `YUH7M5` varchar(6) DEFAULT NULL COMMENT '是否投放防虫、霉药品',
  `YUH7M6` varchar(6) DEFAULT NULL COMMENT '是否清洁档案室',
  `YUH7M7` varchar(6) DEFAULT NULL COMMENT '是否通风',
  `YUH7M8` varchar(20) DEFAULT NULL COMMENT '温度',
  `YUH7M9` varchar(6) DEFAULT NULL COMMENT '是否投放吸潮剂',
  `YUH7MA` varchar(20) DEFAULT NULL COMMENT '湿度',
  `YUH7MB` varchar(10) DEFAULT NULL COMMENT '监控安全等级',
  `YUH7MC` varchar(6) DEFAULT NULL COMMENT '是否进行设备检查',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE013` text COMMENT '备注说明',
  PRIMARY KEY (`YUH7M0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='九防监控信息表';

-- ----------------------------
-- Table structure for uh7n
-- ----------------------------
DROP TABLE IF EXISTS `uh7n`;
CREATE TABLE `uh7n` (
  `YUH7N0` varchar(10) NOT NULL COMMENT '监控明细表主键',
  `YUH7M0` varchar(10) DEFAULT NULL COMMENT '监控信息表主键',
  `YUH7K0` varchar(10) DEFAULT NULL COMMENT '设备编号',
  `YUH7N1` varchar(6) DEFAULT NULL COMMENT '设备状态',
  `YUH7N2` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`YUH7N0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='九防监控明细表';

-- ----------------------------
-- Table structure for uj00
-- ----------------------------
DROP TABLE IF EXISTS `uj00`;
CREATE TABLE `uj00` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUJ041` decimal(5,0) DEFAULT NULL COMMENT '件号',
  `YUJ042` varchar(100) DEFAULT NULL COMMENT '文号',
  `YUJ043` text COMMENT '题名',
  `YUJ044` varchar(200) DEFAULT NULL COMMENT '责任者',
  `YUJ045` text COMMENT '主题词',
  `YUJ046` varchar(6) DEFAULT NULL COMMENT '信息类型',
  `YUJ047` varchar(6) DEFAULT NULL COMMENT '载体类型',
  `YUJ048` varchar(200) DEFAULT NULL COMMENT '载体单位',
  `YUJ049` decimal(6,0) DEFAULT NULL COMMENT '载体数量',
  `YUJ04A` varchar(6) DEFAULT NULL COMMENT '载体规格',
  `YUJ04B` varchar(100) DEFAULT NULL COMMENT '电子档号',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUE002` varchar(200) DEFAULT NULL COMMENT '档案类别',
  `YUJ04D` varchar(200) DEFAULT NULL COMMENT '稿本',
  `YUJ04E` varchar(200) DEFAULT NULL COMMENT '附件',
  `YUJ04F` varchar(200) DEFAULT NULL COMMENT '元数据信息',
  `YUJ04G` varchar(50) DEFAULT NULL COMMENT '盒（室）号',
  `YUJ04H` varchar(50) DEFAULT NULL COMMENT '盒（馆）号',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全宗卷档案';

-- ----------------------------
-- Table structure for uj01
-- ----------------------------
DROP TABLE IF EXISTS `uj01`;
CREATE TABLE `uj01` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUE013` varchar(10) DEFAULT NULL COMMENT '案卷类别编号',
  `YUF00Q` decimal(20,0) DEFAULT NULL COMMENT '案卷顺序号',
  `YUJ013` text COMMENT '卷名',
  `YUJ014` text COMMENT '表名',
  `YUJ015` text COMMENT '题名',
  `YUJ016` text COMMENT '主题词',
  `YUJ017` varchar(100) DEFAULT NULL COMMENT '登记号',
  `YUJ018` varchar(50) DEFAULT NULL COMMENT '文件编号',
  `YUJ01B` datetime DEFAULT NULL COMMENT '登记日期',
  `YUJ01E` varchar(50) DEFAULT NULL COMMENT '部门',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB003` varchar(20) DEFAULT NULL COMMENT '单位代码',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(30) DEFAULT NULL COMMENT '身份证号码',
  `AAC003` text COMMENT '姓名',
  `YUJ01F` varchar(20) DEFAULT NULL COMMENT '医院代码',
  `YUJ01G` varchar(100) DEFAULT NULL COMMENT '医院名称',
  `YUJ01H` datetime DEFAULT NULL COMMENT '费用发生时间',
  `YUJ01I` datetime DEFAULT NULL COMMENT '报送时间',
  `YUJ01J` varchar(50) DEFAULT NULL COMMENT '转财务汇总批号',
  `YUJ01N` varchar(20) DEFAULT NULL COMMENT '收集人',
  `YUJ01O` datetime DEFAULT NULL COMMENT '收集时间',
  `AAC021` varchar(50) DEFAULT NULL COMMENT '失业证编号',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社保业务档案';

-- ----------------------------
-- Table structure for uj02
-- ----------------------------
DROP TABLE IF EXISTS `uj02`;
CREATE TABLE `uj02` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUJ021` varchar(21) DEFAULT NULL COMMENT '业务条码',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUE012` text COMMENT '文件类别名称',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT '年度',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT '月份',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT '保管期限',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT '密级',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC003` varchar(200) DEFAULT NULL COMMENT '姓名',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '证件号码',
  `YUJ023` decimal(16,2) DEFAULT NULL COMMENT '金额',
  `YUJ01K` datetime DEFAULT NULL COMMENT '文件日期',
  `YUJ01L` decimal(65,30) DEFAULT NULL COMMENT '文件份数',
  `YUJ01M` decimal(65,30) DEFAULT NULL COMMENT '文件页数',
  `YUJ01P` datetime DEFAULT NULL COMMENT '保管开始时间',
  `YUJ01R` datetime DEFAULT NULL COMMENT '保管结束时间',
  `YUF00P` decimal(65,30) DEFAULT NULL COMMENT '扫描页数',
  `AAZ002` varchar(50) DEFAULT NULL COMMENT '业务编号',
  `YUJ024` varchar(200) DEFAULT NULL COMMENT '银行名称',
  `YUJ025` varchar(200) DEFAULT NULL COMMENT '楼盘名称',
  `YUJ026` varchar(20) DEFAULT NULL COMMENT '批次号',
  `YUJ027` varchar(20) DEFAULT NULL COMMENT '件号',
  `YUJ028` text COMMENT '题名',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公积金业务档案';

-- ----------------------------
-- Table structure for uj03
-- ----------------------------
DROP TABLE IF EXISTS `uj03`;
CREATE TABLE `uj03` (
  `YUJ0X0` varchar(50) NOT NULL COMMENT '档案基础信息主键',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '公民身份号码',
  `AAC003` varchar(50) DEFAULT NULL COMMENT '姓名',
  `AAC006` datetime DEFAULT NULL COMMENT '出生日期',
  `AA0131` varchar(6) DEFAULT NULL COMMENT '人员类别（公务员、事业单位人员）',
  `AA0132` varchar(6) DEFAULT NULL COMMENT '人员状态（非死亡、死亡）',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB004` varchar(200) DEFAULT NULL COMMENT '单位名称',
  `YUJ031` varchar(50) DEFAULT NULL COMMENT '档案号',
  `YUJ032` varchar(50) DEFAULT NULL COMMENT '档案袋条码',
  `YUJ033` varchar(200) DEFAULT NULL COMMENT '档案保管单位',
  `YUJ034` varchar(50) DEFAULT NULL COMMENT '存放库号',
  `YUJ035` varchar(50) DEFAULT NULL COMMENT '存放列号',
  `YUJ036` varchar(50) DEFAULT NULL COMMENT '存放节格号',
  `YUJ037` varchar(50) DEFAULT NULL COMMENT '盒号',
  `YUJ038` varchar(6) DEFAULT NULL COMMENT '人事档案类别(正本;副本;正本、副本)',
  `YUJ039` decimal(6,0) DEFAULT NULL COMMENT '材料类型数量',
  `YUF0C2` decimal(6,0) DEFAULT NULL COMMENT '材料份数',
  `YUF0C3` decimal(6,0) DEFAULT NULL COMMENT '材料页数',
  `YUJ03P` varchar(200) DEFAULT NULL COMMENT '备注',
  `YUJ03O` datetime DEFAULT NULL COMMENT '停发时间',
  `YUJ03Q` varchar(100) DEFAULT NULL COMMENT '停发原因',
  `YUJ03R` datetime DEFAULT NULL COMMENT '退休时间',
  `YUJ03S` datetime DEFAULT NULL COMMENT '录入时间',
  `YUJ03T` varchar(6) DEFAULT NULL COMMENT '人事档案类别',
  `AAC004` varchar(6) DEFAULT NULL COMMENT '性别',
  `YUJ03A` varchar(50) DEFAULT NULL COMMENT '历史档案号',
  `YUJ03B` datetime DEFAULT NULL COMMENT '毕业时间',
  `YUJ03C` varchar(50) DEFAULT NULL COMMENT '档案身份(0,干部;1,工人;2,非普;3,其他;)',
  `YUJ03D` varchar(50) DEFAULT NULL COMMENT '毕业院校或档案来源',
  `YUJ03E` datetime DEFAULT NULL COMMENT '参加工作时间',
  `YUJ03F` varchar(20) DEFAULT NULL COMMENT '学历(0,无;1,中专;2,大专;3,本科;4,硕士;5,博士;)',
  `YUJ03G` datetime DEFAULT NULL COMMENT '到档时间',
  `YUJ03H` varchar(50) DEFAULT NULL COMMENT '专业',
  `YUJ03I` decimal(5,0) DEFAULT NULL COMMENT '修业年限',
  `YUJ03J` varchar(20) DEFAULT NULL COMMENT '教育类型(0,无;1,统招;2,成人脱产;3,夜大;4,函授;5,网络教育;6,自学考试;7,委培定向;8,成教;9,职专;10,小计划;11,留学;)',
  `YUJ03K` varchar(20) DEFAULT NULL COMMENT '是否有党材料(0,否;1,是;)',
  `YUJ03L` varchar(50) DEFAULT NULL COMMENT '库房位置',
  `YUJ03M` varchar(6) DEFAULT NULL COMMENT '档案状态（有码值）',
  `YUJ03N` varchar(6) DEFAULT NULL COMMENT '借阅状态（暂定 0,未借；1,已借）',
  `AAC005` varchar(50) DEFAULT NULL COMMENT '民族',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事档案';

-- ----------------------------
-- Table structure for uj04
-- ----------------------------
DROP TABLE IF EXISTS `uj04`;
CREATE TABLE `uj04` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUJ041` decimal(5,0) DEFAULT NULL COMMENT '件号',
  `YUJ042` varchar(100) DEFAULT NULL COMMENT '文号',
  `YUJ043` text COMMENT '题名',
  `YUJ044` varchar(200) DEFAULT NULL COMMENT '责任者',
  `YUJ045` text COMMENT '主题词',
  `YUJ046` varchar(6) DEFAULT NULL COMMENT '信息类型',
  `YUJ047` varchar(6) DEFAULT NULL COMMENT '载体类型',
  `YUJ048` varchar(200) DEFAULT NULL COMMENT '载体单位',
  `YUJ049` decimal(6,0) DEFAULT NULL COMMENT '载体数量',
  `YUJ04A` varchar(6) DEFAULT NULL COMMENT '载体规格',
  `YUJ04B` varchar(100) DEFAULT NULL COMMENT '电子档号',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUJ04C` varchar(200) DEFAULT NULL COMMENT '机构',
  `YUJ04D` varchar(200) DEFAULT NULL COMMENT '稿本',
  `YUJ04E` varchar(200) DEFAULT NULL COMMENT '附件',
  `YUJ04F` varchar(200) DEFAULT NULL COMMENT '元数据信息',
  `YUJ04G` varchar(50) DEFAULT NULL COMMENT '盒（室）号',
  `YUJ04H` varchar(50) DEFAULT NULL COMMENT '盒（馆）号',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文书档案';

-- ----------------------------
-- Table structure for uj05
-- ----------------------------
DROP TABLE IF EXISTS `uj05`;
CREATE TABLE `uj05` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUJ051` varchar(20) DEFAULT NULL COMMENT '账套代码',
  `YUJ052` varchar(100) DEFAULT NULL COMMENT '账套名称',
  `YUJ053` varchar(20) DEFAULT NULL COMMENT '会计科目代码',
  `YUJ054` varchar(100) DEFAULT NULL COMMENT '会计科目名称',
  `YUJ055` varchar(50) DEFAULT NULL COMMENT '凭证开始号',
  `YUJ056` varchar(50) DEFAULT NULL COMMENT '凭证结束号',
  `YUJ057` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `YUJ058` text COMMENT '摘要',
  `YUJ059` text COMMENT '会计备注',
  `YUJ05A` varchar(50) DEFAULT NULL COMMENT '凭证前缀',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基金会计档案';

-- ----------------------------
-- Table structure for uj08
-- ----------------------------
DROP TABLE IF EXISTS `uj08`;
CREATE TABLE `uj08` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUE010` varchar(20) DEFAULT NULL COMMENT '文件类别编号',
  `YUJ081` varchar(20) DEFAULT NULL COMMENT '实物名称',
  `YUJ082` varchar(20) DEFAULT NULL COMMENT '实物号',
  `YUJ083` varchar(100) DEFAULT NULL COMMENT '备注',
  `YUJ084` varchar(25) DEFAULT NULL COMMENT '机构',
  `YUJ085` decimal(10,0) DEFAULT NULL COMMENT '件号',
  `YUJ044` varchar(25) DEFAULT NULL COMMENT '责任者',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实物档案';

-- ----------------------------
-- Table structure for uj14
-- ----------------------------
DROP TABLE IF EXISTS `uj14`;
CREATE TABLE `uj14` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT '档案基础信息主键',
  `YUE002` varchar(6) DEFAULT NULL COMMENT '档案类别',
  `YUE013` varchar(10) DEFAULT NULL COMMENT '档案目录号',
  `YUF00Q` decimal(20,0) DEFAULT NULL COMMENT '案卷顺序号',
  `YUJ013` text COMMENT '卷名',
  `YUJ014` text COMMENT '表名',
  `YUJ015` text COMMENT '题名',
  `YUJ016` text COMMENT '主题词',
  `YUJ017` varchar(100) DEFAULT NULL COMMENT '登记号',
  `YUJ018` varchar(50) DEFAULT NULL COMMENT '文件编号',
  `YUJ01B` datetime DEFAULT NULL COMMENT '登记日期',
  `YUJ01E` varchar(50) DEFAULT NULL COMMENT '部门',
  `AAB001` varchar(50) DEFAULT NULL COMMENT '单位编号',
  `AAB003` varchar(20) DEFAULT NULL COMMENT '单位代码',
  `AAB004` text COMMENT '单位名称',
  `AAC001` varchar(50) DEFAULT NULL COMMENT '个人编号',
  `AAC002` varchar(30) DEFAULT NULL COMMENT '身份证号码',
  `AAC003` text COMMENT '姓名',
  `YUJ01F` varchar(20) DEFAULT NULL COMMENT '医院代码',
  `YUJ01G` varchar(100) DEFAULT NULL COMMENT '医院名称',
  `YUJ01H` datetime DEFAULT NULL COMMENT '费用发生时间',
  `YUJ01I` datetime DEFAULT NULL COMMENT '报送时间',
  `YUJ01J` varchar(50) DEFAULT NULL COMMENT '转财务汇总批号',
  `YUJ01N` varchar(20) DEFAULT NULL COMMENT '收集人',
  `YUJ01O` datetime DEFAULT NULL COMMENT '收集时间',
  `AAC021` varchar(50) DEFAULT NULL COMMENT '失业证编号',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='就业档案信息';

-- ----------------------------
-- Table structure for ur01
-- ----------------------------
DROP TABLE IF EXISTS `ur01`;
CREATE TABLE `ur01` (
  `YUR010` varchar(20) NOT NULL COMMENT '调整流水号',
  `YUF110` varchar(20) DEFAULT NULL COMMENT '文件主表流水号',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `YUF00J` decimal(65,30) DEFAULT NULL COMMENT '份数',
  `YUF00K` decimal(65,30) DEFAULT NULL COMMENT '页数',
  `YUF00P` decimal(4,0) DEFAULT NULL COMMENT '扫描页数',
  `YUF004` varchar(2) DEFAULT NULL COMMENT '扫描状态（0未扫描、1扫描中、2已扫描）',
  `AAE013` text COMMENT '备注',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUR010`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事档案调整日志主表';

-- ----------------------------
-- Table structure for ur02
-- ----------------------------
DROP TABLE IF EXISTS `ur02`;
CREATE TABLE `ur02` (
  `YUR020` varchar(20) NOT NULL COMMENT '调整明细流水号',
  `YUR010` varchar(20) DEFAULT NULL COMMENT '调整流水号',
  `YUE030` varchar(20) DEFAULT NULL COMMENT '材料编号',
  `YUE031` text COMMENT '材料名称',
  `YUE046` decimal(65,30) DEFAULT NULL COMMENT '材料页数',
  `YUE041` decimal(65,30) DEFAULT NULL COMMENT '附表顺序号',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT '个人标志码',
  PRIMARY KEY (`YUR020`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人事档案调整日志明细表';

-- ----------------------------
-- Table structure for ur03
-- ----------------------------
DROP TABLE IF EXISTS `ur03`;
CREATE TABLE `ur03` (
  `YUR030` varchar(20) NOT NULL COMMENT '登记流水号',
  `YUF110` varchar(20) DEFAULT NULL COMMENT '文件主表流水号',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `YUR031` varchar(2) DEFAULT NULL COMMENT '转换类型（01流动转干部、02干部转流动）',
  `YUR032` datetime DEFAULT NULL COMMENT '转换日期',
  `YUE033` text COMMENT '备注',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUR030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案人员类型转换登记表';

-- ----------------------------
-- Table structure for ur04
-- ----------------------------
DROP TABLE IF EXISTS `ur04`;
CREATE TABLE `ur04` (
  `YUR040` varchar(20) NOT NULL COMMENT '登记流水号',
  `YUF110` varchar(20) DEFAULT NULL COMMENT '文件主表流水号',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `YUR041` datetime DEFAULT NULL COMMENT '死亡日期',
  `YUR042` datetime DEFAULT NULL COMMENT '档案移库时间',
  `YUR043` varchar(200) DEFAULT NULL COMMENT '档案移库后位置',
  `YUR044` text COMMENT '备注',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUR040`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='死亡人员审核登记表';

-- ----------------------------
-- Table structure for ur05
-- ----------------------------
DROP TABLE IF EXISTS `ur05`;
CREATE TABLE `ur05` (
  `YUR050` varchar(20) NOT NULL COMMENT '登记流水号',
  `YUR001` varchar(20) DEFAULT NULL COMMENT '申请人姓名',
  `YUR002` varchar(18) DEFAULT NULL COMMENT '申请人身份证号',
  `YUR003` varchar(50) DEFAULT NULL COMMENT '申请人单位名称',
  `YUR004` datetime DEFAULT NULL COMMENT '申请日期',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `YUR051` varchar(2) DEFAULT NULL COMMENT '证明类型（01存档证明、02经历证明、03亲属关系证明、04无犯罪记录证明、05其他）',
  `YUR052` text COMMENT '备注',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUF110` varchar(20) DEFAULT NULL COMMENT '文件主表流水号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUR050`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出具证明信息登记表';

-- ----------------------------
-- Table structure for ur06
-- ----------------------------
DROP TABLE IF EXISTS `ur06`;
CREATE TABLE `ur06` (
  `YUR060` varchar(20) NOT NULL COMMENT '政审登记流水号',
  `YUR001` varchar(20) DEFAULT NULL COMMENT '申请人姓名',
  `YUR002` varchar(18) DEFAULT NULL COMMENT '申请人身份证号',
  `YUR003` varchar(50) DEFAULT NULL COMMENT '申请人单位名称',
  `AAC003` varchar(20) DEFAULT NULL COMMENT '姓名',
  `AAC004` varchar(2) DEFAULT NULL COMMENT '性别',
  `AAC005` varchar(20) DEFAULT NULL COMMENT '民族',
  `AAC002` varchar(18) DEFAULT NULL COMMENT '身份证号码',
  `AAB004` varchar(50) DEFAULT NULL COMMENT '单位名称',
  `AAB001` varchar(20) DEFAULT NULL COMMENT '单位编号',
  `YUR004` datetime DEFAULT NULL COMMENT '政审日期',
  `YUR061` varchar(2) DEFAULT NULL COMMENT '政审类型（01参军、02录用、03入党、04出国、05升学、06其他）',
  `YUR062` text COMMENT '备注',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办部门',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUF110` varchar(20) DEFAULT NULL COMMENT '文件主表流水号',
  `YUR063` varchar(2) DEFAULT NULL COMMENT '政审是否通过',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  PRIMARY KEY (`YUR060`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='政审信息登记表';

-- ----------------------------
-- Table structure for ut01
-- ----------------------------
DROP TABLE IF EXISTS `ut01`;
CREATE TABLE `ut01` (
  `YUT010` varchar(20) NOT NULL COMMENT '主键',
  `YUE091` varchar(20) DEFAULT NULL COMMENT '功能模块',
  `YUE092` varchar(20) DEFAULT NULL COMMENT '操作对象',
  `YUT011` varchar(20) DEFAULT NULL COMMENT '操作次数',
  `YUT012` datetime DEFAULT NULL COMMENT '第一次操作时间',
  `YUT013` datetime DEFAULT NULL COMMENT '最后操作时间',
  `YUE001` varchar(20) DEFAULT NULL COMMENT '档案门类',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE012` varchar(20) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `AAE018` varchar(20) DEFAULT NULL COMMENT '经办机构名称',
  PRIMARY KEY (`YUT010`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='模块按钮操作日志';

-- ----------------------------
-- Table structure for ut02
-- ----------------------------
DROP TABLE IF EXISTS `ut02`;
CREATE TABLE `ut02` (
  `YUT020` varchar(20) NOT NULL COMMENT '页面还原辅助表主键',
  `YUT021` varchar(20) NOT NULL COMMENT '页面还原对象',
  `YUT022` datetime NOT NULL COMMENT '操作时间',
  `YUT023` varchar(6) NOT NULL COMMENT '是否完成',
  `YUT024` varchar(6) NOT NULL COMMENT '业务功能模块',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办机构',
  `YUT025` varchar(6) DEFAULT NULL COMMENT '是否编辑后初次提醒',
  `YUT026` varchar(50) DEFAULT NULL COMMENT '关键信息',
  PRIMARY KEY (`YUT020`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='页面恢复辅助表';

-- ----------------------------
-- Table structure for ut05
-- ----------------------------
DROP TABLE IF EXISTS `ut05`;
CREATE TABLE `ut05` (
  `YUT050` varchar(20) NOT NULL,
  `YUT051` varchar(100) DEFAULT NULL COMMENT '标题',
  `YUT052` text COMMENT '内容',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(20) DEFAULT NULL COMMENT '经办单位',
  `YUT053` datetime DEFAULT NULL COMMENT '最后修改时间',
  `YUT054` varchar(20) DEFAULT NULL COMMENT '最后修改人',
  `YUT055` varchar(20) DEFAULT NULL COMMENT '最后修改单位',
  `YAE116` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `YUT056` datetime DEFAULT NULL COMMENT '过期时间',
  `YUT057` varchar(6) DEFAULT NULL COMMENT '过期标志',
  PRIMARY KEY (`YUT050`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案系统消息明细表';

-- ----------------------------
-- Table structure for ut07
-- ----------------------------
DROP TABLE IF EXISTS `ut07`;
CREATE TABLE `ut07` (
  `YUT070` varchar(15) NOT NULL COMMENT '入库批次流水号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案规格',
  `YUT071` decimal(6,0) DEFAULT NULL COMMENT '盒数',
  `YUT072` decimal(6,0) DEFAULT NULL COMMENT '卷数',
  `YUT073` decimal(6,0) DEFAULT NULL COMMENT '件数',
  `YUT074` datetime DEFAULT NULL COMMENT '完成时间',
  `YUT075` varchar(6) DEFAULT NULL COMMENT '入库方式：0保管入库（首次入库）、1归还入库（借出归还）、2退回入库（移交档案管）',
  `YUT076` varchar(50) DEFAULT NULL COMMENT '关联主键',
  `YUT077` varchar(6) DEFAULT NULL COMMENT '是否完成',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUT070`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库批次主信息';

-- ----------------------------
-- Table structure for ut08
-- ----------------------------
DROP TABLE IF EXISTS `ut08`;
CREATE TABLE `ut08` (
  `YUT080` varchar(25) NOT NULL COMMENT '入库明细流水号',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案规格',
  `YUF002` varchar(50) DEFAULT NULL COMMENT '档案主键',
  `YUF010` varchar(25) DEFAULT NULL COMMENT '盒流水号',
  `YUH750` varchar(25) DEFAULT NULL COMMENT '格流水号',
  `YUT070` varchar(15) NOT NULL COMMENT '入库批次流水号',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办单位',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUT081` varchar(25) DEFAULT NULL COMMENT '关联主键',
  PRIMARY KEY (`YUT080`),
  KEY `INDX_UT08_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库信息明细表';

-- ----------------------------
-- Table structure for ut10
-- ----------------------------
DROP TABLE IF EXISTS `ut10`;
CREATE TABLE `ut10` (
  `YUT100` varchar(20) NOT NULL COMMENT '系统操作日志流水号',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUB004` varchar(6) DEFAULT NULL COMMENT '市级机构代码',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案对象',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUF002` varchar(50) DEFAULT NULL COMMENT '档案对象ID',
  `YUF003` varchar(50) DEFAULT NULL COMMENT '档案对象条码',
  `YUT101` varchar(6) DEFAULT NULL COMMENT '系统标识(接口、管理系统)',
  `YUT102` varchar(20) DEFAULT NULL COMMENT '模块码值',
  `YUT103` varchar(20) DEFAULT NULL COMMENT '功能码值',
  `YUT104` varchar(20) DEFAULT NULL COMMENT '操作码值',
  `YUT105` varchar(6) DEFAULT NULL COMMENT '对象状态',
  `YUT106` longblob COMMENT '操作结果(以xml格式记录变化前后的字段值)',
  `YUT107` longblob COMMENT '操作前信息(以xml格式记录操作前的信息)',
  `YUT108` varchar(50) DEFAULT NULL COMMENT '操作功能ID',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人编号',
  `AAE012` varchar(100) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构ID',
  `AAE018` varchar(200) DEFAULT NULL COMMENT '经办机构名称',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标志',
  `YUT109` varchar(50) DEFAULT NULL COMMENT '功能操作名称',
  `AAE013` text COMMENT '备注说明',
  PRIMARY KEY (`YUT100`),
  KEY `INDX_UT10_YUF002` (`YUF002`),
  KEY `INDX_UT10_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案系统操作日志';

-- ----------------------------
-- Table structure for ut11
-- ----------------------------
DROP TABLE IF EXISTS `ut11`;
CREATE TABLE `ut11` (
  `YUT110` varchar(20) NOT NULL COMMENT '流水号',
  `YUB001` varchar(10) DEFAULT NULL COMMENT '全宗号',
  `YUT111` decimal(65,30) DEFAULT NULL COMMENT '受理未办结',
  `YUT112` decimal(65,30) DEFAULT NULL COMMENT '办结未交接',
  `YUT113` decimal(65,30) DEFAULT NULL COMMENT '交接过程中',
  `YUT114` decimal(65,30) DEFAULT NULL COMMENT '交接未整理',
  `YUT115` decimal(65,30) DEFAULT NULL COMMENT '整理未入库',
  `YUT116` decimal(65,30) DEFAULT NULL COMMENT '过期未鉴定',
  `YUT117` decimal(65,30) DEFAULT NULL COMMENT '利用过程中',
  `YUT118` decimal(65,30) DEFAULT NULL COMMENT '鉴定过程中',
  `AAE036` datetime DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`YUT110`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut12
-- ----------------------------
DROP TABLE IF EXISTS `ut12`;
CREATE TABLE `ut12` (
  `YUT120` varchar(50) NOT NULL COMMENT '历史记录表主键',
  `YUT121` varchar(50) DEFAULT NULL COMMENT '搜索关键字名称',
  `YUB001` varchar(50) DEFAULT NULL COMMENT '全宗号',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `AAE011` varchar(50) DEFAULT NULL COMMENT '经办人编号',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '经办人姓名',
  `YUB004` varchar(10) DEFAULT NULL COMMENT '行政区划',
  `AAE017` varchar(50) DEFAULT NULL COMMENT '经办机构',
  PRIMARY KEY (`YUT120`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案搜索中心搜索记录日志表';

-- ----------------------------
-- Table structure for ut22
-- ----------------------------
DROP TABLE IF EXISTS `ut22`;
CREATE TABLE `ut22` (
  `YUT220` varchar(25) NOT NULL COMMENT '出库批次流水号',
  `YUT221` decimal(6,0) DEFAULT NULL COMMENT '盒数',
  `YUT222` decimal(6,0) DEFAULT NULL COMMENT '卷数',
  `YUT223` decimal(6,0) DEFAULT NULL COMMENT '件数',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  `YUT224` datetime DEFAULT NULL COMMENT '完成时间',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUT225` varchar(6) DEFAULT NULL COMMENT '出库方式：0销毁出库、1借出出库、2移交档案馆出库',
  `YUT226` varchar(50) DEFAULT NULL COMMENT '关联主键',
  `YUT227` varchar(6) DEFAULT NULL COMMENT '是否完成',
  `YUF001` varchar(6) DEFAULT NULL COMMENT '档案规格',
  `YUE001` varchar(10) DEFAULT NULL COMMENT '档案门类',
  PRIMARY KEY (`YUT220`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库主信息表';

-- ----------------------------
-- Table structure for ut23
-- ----------------------------
DROP TABLE IF EXISTS `ut23`;
CREATE TABLE `ut23` (
  `YUT230` varchar(25) NOT NULL COMMENT '出库明细表',
  `YUE001` varchar(6) NOT NULL COMMENT '档案门类',
  `YUF001` varchar(6) NOT NULL COMMENT '档案规格',
  `YUF002` varchar(25) NOT NULL COMMENT '档案主键',
  `YUF010` varchar(25) DEFAULT NULL COMMENT '盒流水号',
  `YUH750` varchar(25) NOT NULL COMMENT '格流水号',
  `YUT220` varchar(15) NOT NULL COMMENT '出库批次流水号',
  `AAE011` varchar(50) NOT NULL COMMENT '经办人',
  `AAE017` varchar(50) NOT NULL COMMENT '经办单位',
  `AAE036` datetime NOT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUT230`),
  KEY `INDX_UT23_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案出库明细表';

-- ----------------------------
-- Table structure for ut24
-- ----------------------------
DROP TABLE IF EXISTS `ut24`;
CREATE TABLE `ut24` (
  `YUT240` varchar(20) NOT NULL COMMENT '流水号',
  `YUB001` varchar(10) DEFAULT NULL COMMENT '全宗号',
  `YUT241` decimal(65,30) DEFAULT NULL COMMENT '受理未办结',
  `YUT242` decimal(65,30) DEFAULT NULL COMMENT '办结未交接',
  `YUT243` decimal(65,30) DEFAULT NULL COMMENT '交接过程中',
  `YUT244` decimal(65,30) DEFAULT NULL COMMENT '交接未整理',
  `YUT245` decimal(65,30) DEFAULT NULL COMMENT '整理未入库',
  `YUT246` decimal(65,30) DEFAULT NULL COMMENT '过期未鉴定',
  `YUT247` decimal(65,30) DEFAULT NULL COMMENT '利用过程中',
  `YUT248` decimal(65,30) DEFAULT NULL COMMENT '鉴定过程中',
  `AAE036` datetime DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`YUT240`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut33
-- ----------------------------
DROP TABLE IF EXISTS `ut33`;
CREATE TABLE `ut33` (
  `YUT330` varchar(30) NOT NULL COMMENT '复用材料设置记录流水号',
  `YUE0J0` varchar(30) DEFAULT NULL COMMENT '复用材料表流水号',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '操作人',
  `AAE036` datetime DEFAULT NULL COMMENT '操作时间',
  `AAE017` varchar(30) DEFAULT NULL COMMENT '操作机构',
  `AAE012` varchar(50) DEFAULT NULL COMMENT '操作人姓名',
  `YUT031` varchar(30) DEFAULT NULL COMMENT '操作类型(1.添加,2.取消)',
  PRIMARY KEY (`YUT330`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut34
-- ----------------------------
DROP TABLE IF EXISTS `ut34`;
CREATE TABLE `ut34` (
  `YUT340` varchar(25) NOT NULL COMMENT 'ocr日志操作系统流水号',
  `YUT341` longtext COMMENT '识别结果',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗',
  `YUB004` varchar(6) DEFAULT NULL COMMENT '行政区划',
  `AAE013` text COMMENT '备注',
  `YUF05A` varchar(30) DEFAULT NULL COMMENT '图片唯一id',
  `AAE011` varchar(25) DEFAULT NULL COMMENT '经办人编号',
  `AAE012` varchar(200) DEFAULT NULL COMMENT '经办人姓名',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构id',
  `AAE018` varchar(200) DEFAULT NULL COMMENT '经办机构名称',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUT340`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ocr日志操作记录表';

-- ----------------------------
-- Table structure for ut35
-- ----------------------------
DROP TABLE IF EXISTS `ut35`;
CREATE TABLE `ut35` (
  `YUT350` varchar(50) NOT NULL COMMENT '流水号',
  `YUB004` varchar(6) DEFAULT NULL COMMENT '市级机构代码',
  `YUB001` varchar(6) DEFAULT NULL COMMENT '全宗号',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT '行政区划代码',
  `YUE0E3` varchar(200) DEFAULT NULL COMMENT '行政区划名称',
  `YUT351` decimal(10,0) DEFAULT NULL COMMENT '业务档案经办总量',
  `YUT352` decimal(10,0) DEFAULT NULL COMMENT '各地市扫描总量',
  `YUT353` decimal(10,0) DEFAULT NULL COMMENT '各地市库存总量',
  `YUE001` varchar(6) DEFAULT NULL COMMENT '档案门类',
  `YUT354` datetime DEFAULT NULL COMMENT '统计时间',
  `YUT360` varchar(50) DEFAULT NULL COMMENT '定时任务执行流水号',
  `AAE100` varchar(6) DEFAULT NULL COMMENT '有效标识',
  `AAE036` datetime DEFAULT NULL COMMENT '经办时间',
  PRIMARY KEY (`YUT350`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut3a
-- ----------------------------
DROP TABLE IF EXISTS `ut3a`;
CREATE TABLE `ut3a` (
  `YUT3A0` varchar(20) NOT NULL COMMENT '流水号',
  `YUT3A1` longblob COMMENT '输入数据',
  `YUT3A2` longblob COMMENT '输出数据',
  `YUT3A3` varchar(6) DEFAULT NULL COMMENT '是否正常返回',
  `YUT3A4` varchar(20) DEFAULT NULL COMMENT '操作类型(0代表查询,1代表更新)',
  `AAE011` varchar(20) DEFAULT NULL COMMENT '经办人',
  `AAE017` varchar(25) DEFAULT NULL COMMENT '经办机构',
  `AAE036` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`YUT3A0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='档案工位查询更新操作记录';

-- ----------------------------
-- Table structure for yhcip_oracle_jobs
-- ----------------------------
DROP TABLE IF EXISTS `yhcip_oracle_jobs`;
CREATE TABLE `yhcip_oracle_jobs` (
  `JOBID` varchar(20) NOT NULL COMMENT '任务id',
  `ORACLEJOBID` varchar(20) NOT NULL COMMENT 'oracle的jobid',
  `JOBNAME` varchar(100) NOT NULL COMMENT '任务名称',
  `USERID` varchar(20) NOT NULL COMMENT '用户id',
  `WHAT` text NOT NULL COMMENT '执行的过程',
  `STARTTIME` varchar(200) NOT NULL COMMENT '开始时间',
  `ENDTIME` datetime DEFAULT NULL COMMENT '结束时间',
  `INTERVAL` varchar(200) DEFAULT NULL COMMENT '时间间隔',
  `SUBMITTIME` datetime DEFAULT NULL COMMENT '提交时间',
  PRIMARY KEY (`JOBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oracle定时';

-- ----------------------------
-- Table structure for ytaorg
-- ----------------------------
DROP TABLE IF EXISTS `ytaorg`;
CREATE TABLE `ytaorg` (
  `ORGID` decimal(10,0) NOT NULL COMMENT '业务组织id',
  `ORGNAME` varchar(60) DEFAULT NULL COMMENT '业务组织名称',
  `YORGID` decimal(10,0) DEFAULT NULL COMMENT '档案组织id',
  `YORGNAME` varchar(60) DEFAULT NULL COMMENT '档案组织名称',
  PRIMARY KEY (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ytauser
-- ----------------------------
DROP TABLE IF EXISTS `ytauser`;
CREATE TABLE `ytauser` (
  `YTA` varchar(20) NOT NULL COMMENT '主键',
  `USERID` decimal(10,0) DEFAULT NULL COMMENT '作为档案人员id',
  `NAME` varchar(60) DEFAULT NULL COMMENT '姓名',
  `ORGID` decimal(10,0) DEFAULT NULL COMMENT '档案组织id',
  `ORGNAME` varchar(60) DEFAULT NULL COMMENT '档案组织名称',
  `YUSERID` decimal(10,0) DEFAULT NULL COMMENT '作为业务人员id',
  `YNAME` varchar(60) DEFAULT NULL COMMENT '姓名',
  `YORGNAME` varchar(60) DEFAULT NULL COMMENT '业务组织名称',
  `YORGID` decimal(10,0) DEFAULT NULL COMMENT '业务组织id',
  `CONTACT` varchar(10) DEFAULT NULL COMMENT '一对一标志',
  PRIMARY KEY (`YTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for aa10a1ori
-- ----------------------------
DROP VIEW IF EXISTS `aa10a1`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `aa10a1ori` AS select `aa10`.`AAA100` AS `AAA100`,`aa10`.`AAA101` AS `AAA101`,`aa10`.`AAA102` AS `AAA102`,`aa10`.`AAA103` AS `AAA103`,`aa10`.`YAB003` AS `YAB003`,`aa10`.`AAE120` AS `AAE120`,`aa10`.`VER` AS `VER` from `aa10` union select 'YUE010' AS `AAA100`,'业务环节' AS `AAA101`,`ue01`.`YUE010` AS `AAA102`,`ue01`.`YUE012` AS `AAA103`,'9999' AS `YAB003`,'0' AS `AAE120`,0 AS `VER` from `ue01` union select 'YUE013' AS `aaa100`,'案卷类别' AS `aaa101`,`ue0a`.`YUE013` AS `aaa102`,`ue0a`.`YUE014` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue0a` union select 'YUE001' AS `aaa100`,'档案门类' AS `aaa101`,`ue06`.`YUE001` AS `aaa102`,`ue06`.`YUE061` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue06` union select 'YUE002' AS `aaa100`,'档案类别' AS `aaa101`,`ue0b`.`YUE002` AS `aaa102`,`ue0b`.`YUE0B1` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue0b` union select 'YUE030' AS `aaa100`,'材料类型' AS `aaa101`,`ue03`.`YUE030` AS `aaa102`,`ue03`.`YUE031` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue03` union select ('YUF00H' or `ue07`.`YUE001`) AS `aaa100`,'保存期限' AS `aaa101`,`ue07`.`YUF00H` AS `aaa102`,`ue07`.`YUE071` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue07` union select 'YUH701' AS `aaa100`,'库房名称' AS `aaa101`,`uh70`.`YUH701` AS `aaa102`,`uh70`.`YUH702` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `uh70` union select 'ORG' AS `aaa100`,'机构' AS `aaa101`,`taorg`.`ORGID` AS `aaa102`,`taorg`.`ORGNAME` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `taorg` union select 'ORGID' AS `aaa100`,'机构' AS `aaa101`,`taorg`.`ORGID` AS `aaa102`,`taorg`.`ORGNAME` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `taorg` union select 'USER' AS `aaa100`,'人员' AS `aaa101`,`tauser`.`USERID` AS `aaa102`,`tauser`.`NAME` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `tauser` union select distinct 'YUB005' AS `aaa100`,'省' AS `aaa101`,`ue0g`.`YUB005` AS `aaa102`,`ue0g`.`YUE0G2` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue0g` order by `AAA102`;

-- ----------------------------
-- View structure for taorgc
-- ----------------------------
DROP VIEW IF EXISTS `taorgc`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `taorgc` AS select 'ORGID' AS `ORGID`,'PORGID' AS `PORGID`,'ORGNAME' AS `ORGNAME`,'COSTOMNO' AS `COSTOMNO`,'ORGIDPATH' AS `ORGIDPATH`,'ORGNAMEPATH' AS `ORGNAMEPATH`,'COSTOMNOPATH' AS `COSTOMNOPATH`,'ORGTYPE' AS `ORGTYPE`,'SORT' AS `SORT`,'YAB003' AS `YAB003`,'DIMENSION' AS `DIMENSION`,'CREATEUSER' AS `CREATEUSER`,'CREATETIME' AS `CREATETIME`,'EFFECTIVE' AS `EFFECTIVE`,'ORGLEVEL' AS `ORGLEVEL`,'ISLEAF' AS `ISLEAF`,'ORGMANAGER' AS `ORGMANAGER`,'DESTORY' AS `DESTORY`,'TYPEFLAG' AS `TYPEFLAG`,'YAB139' AS `YAB139` from `taorg`;

-- ----------------------------
-- View structure for tauserc
-- ----------------------------
DROP VIEW IF EXISTS `tauserc`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `tauserc` AS select 'USERID' AS `USERID`,'NAME' AS `NAME`,'SEX' AS `SEX`,'LOGINID' AS `LOGINID`,'PASSWORD' AS `PASSWORD`,'PASSWORDFAULTNUM' AS `PASSWORDFAULTNUM`,'PWDLASTMODIFYDATE' AS `PWDLASTMODIFYDATE`,'ISLOCK' AS `ISLOCK`,'SORT' AS `SORT`,'EFFECTIVE' AS `EFFECTIVE`,'TEL' AS `TEL`,'CREATEUSER' AS `CREATEUSER`,'CREATETIME' AS `CREATETIME`,'DIRECTORGID' AS `DIRECTORGID`,'DESTORY' AS `DESTORY`,'TYPEFLAG' AS `TYPEFLAG` from `tauser`;

-- ----------------------------
-- View structure for v_all_menu
-- ----------------------------
DROP VIEW IF EXISTS `v_all_menu`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_all_menu` AS select distinct `a`.`YAE049` AS `menuid`,`a`.`YAE04A` AS `menuname` from `ue0p` `a` where ((1 = 1) and (`a`.`AAE100` = '1'));

-- ----------------------------
-- View structure for v_taloginlog
-- ----------------------------
DROP VIEW IF EXISTS `v_taloginlog`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_taloginlog` AS select `a`.`LOGID` AS `logid`,`a`.`USERID` AS `userid`,(select `tauser`.`NAME` from `tauser` where (`tauser`.`USERID` = `a`.`USERID`)) AS `username`,`a`.`LOGINTIME` AS `logintime`,`a`.`CLIENTIP` AS `clientip`,`a`.`SESSIONID` AS `sessionid`,`a`.`SYSPATH` AS `syspath`,`a`.`SERVERIP` AS `serverip`,(case when (length(`a`.`CLIENTSYSTEM`) = 0) then 'UNKNOW' else ifnull(`a`.`CLIENTSYSTEM`,'UNKNOW') end) AS `clientsystem`,(case when (length(`a`.`CLIENTBROWSER`) = 0) then 'UNKNOW' else ifnull(`a`.`CLIENTBROWSER`,'UNKNOW') end) AS `clientbrowser`,(case when (length(`a`.`CLIENTSCREENSIZE`) = 0) then 'UNKNOW' else ifnull(`a`.`CLIENTSCREENSIZE`,'UNKNOW') end) AS `clientscreensize` from `taonlinelog` `a` union all select `a`.`LOGID` AS `logid`,`a`.`userid` AS `userid`,(select `tauser`.`NAME` from `tauser` where (`tauser`.`USERID` = `a`.`userid`)) AS `username`,`a`.`logintime` AS `logintime`,`a`.`clientip` AS `clientip`,`a`.`sessionid` AS `sessionid`,`a`.`syspath` AS `syspath`,`a`.`serverip` AS `serverip`,(case when (length(`a`.`clientsystem`) = 0) then 'UNKNOW' else ifnull(`a`.`clientsystem`,'UNKNOW') end) AS `clientsystem`,(case when (length(`a`.`clientbrowser`) = 0) then 'UNKNOW' else ifnull(`a`.`clientbrowser`,'UNKNOW') end) AS `clientbrowser`,(case when (length(`a`.`clientscreensize`) = 0) then 'UNKNOW' else ifnull(`a`.`clientscreensize`,'UNKNOW') end) AS `clientscreensize` from `taloginhistorylog` `a`;

-- ----------------------------
-- View structure for v_taorg
-- ----------------------------
DROP VIEW IF EXISTS `v_taorg`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_taorg` AS (select 'ORGID' AS `ORGID`,'PORGID' AS `PORGID`,'ORGNAME' AS `ORGNAME`,'COSTOMNO' AS `COSTOMNO`,'ORGIDPATH' AS `ORGIDPATH`,'ORGNAMEPATH' AS `ORGNAMEPATH`,'COSTOMNOPATH' AS `COSTOMNOPATH`,'ORGTYPE' AS `ORGTYPE`,'SORT' AS `SORT`,'YAB003' AS `YAB003`,'YAB139' AS `YAB139`,'DIMENSION' AS `DIMENSION`,'CREATEUSER' AS `CREATEUSER`,'CREATETIME' AS `CREATETIME`,'EFFECTIVE' AS `EFFECTIVE`,'ORGLEVEL' AS `ORGLEVEL`,'ISLEAF' AS `ISLEAF`,'ORGMANAGER' AS `ORGMANAGER`,'DESTORY' AS `DESTORY`,'TYPEFLAG' AS `TYPEFLAG` from `taorg`);

-- ----------------------------
-- View structure for v_tauser
-- ----------------------------
DROP VIEW IF EXISTS `v_tauser`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_tauser` AS (select 'USERID' AS `USERID`,'NAME' AS `NAME`,'SEX' AS `SEX`,'LOGINID' AS `LOGINID`,'PASSWORD' AS `PASSWORD`,'PASSWORDFAULTNUM' AS `PASSWORDFAULTNUM`,'PWDLASTMODIFYDATE' AS `PWDLASTMODIFYDATE`,'ISLOCK' AS `ISLOCK`,'SORT' AS `SORT`,'EFFECTIVE' AS `EFFECTIVE`,'TEL' AS `TEL`,'CREATEUSER' AS `CREATEUSER`,'CREATETIME' AS `CREATETIME`,'DIRECTORGID' AS `DIRECTORGID`,'DESTORY' AS `DESTORY`,'TYPEFLAG' AS `TYPEFLAG` from `tauser`);

-- ----------------------------
-- Procedure structure for getCurrval
-- ----------------------------
DROP PROCEDURE IF EXISTS `getCurrval`;
delimiter ;;
CREATE PROCEDURE `getCurrval`(IN sequenceName varchar(50),OUT sequenceVal varchar(15))
BEGIN
	  declare seqno int(10);
    set @seqno=(select seqname from tabseq where seqname=sequenceName);
	  if (@seqno is null) then
			insert into tabseq(seqname,seqval) VALUES(sequenceName,1);
		end if;
		commit;
    select CONVERT(seqval,char) into sequenceVal  from tabseq where seqname = sequenceName;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getNextVal
-- ----------------------------
DROP PROCEDURE IF EXISTS `getNextVal`;
delimiter ;;
CREATE PROCEDURE `getNextVal`(IN sequenceName varchar(50) ,OUT sequenceVal varchar(15))
BEGIN
	  declare seqno int(10);
    set @seqno=(select seqname from tabseq where seqname=sequenceName);
	  if (@seqno is null) then
			insert into tabseq(seqname,seqval) VALUES(sequenceName,1);
			commit;
		end if;
		select CONVERT(seqval+1,char) into sequenceVal  from tabseq where seqname = sequenceName;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getSequence
-- ----------------------------
DROP PROCEDURE IF EXISTS `getSequence`;
delimiter ;;
CREATE PROCEDURE `getSequence`(IN sequenceName varchar(50),OUT sequenceVal varchar(15),OUT  AppCode    VARCHAR(30), OUT ErrorMsg        VARCHAR(10))
BEGIN
	  declare seqno varchar(50);
    set @seqno=(select seqname from tabseq where seqname=sequenceName);
	  if (@seqno is null) then
			insert into tabseq(seqname,seqval) VALUES(sequenceName,1);
		else
			update tabseq set seqval = seqval + 1  where seqname = sequenceName;
		end if;
		commit;
    select CONVERT(seqval,char) into sequenceVal  from tabseq where seqname = sequenceName;
		SET AppCode = 'NOERROR';
		SET ErrorMsg = '';
END
;;
delimiter ;

-- ----------------------------
-- Function structure for nextval_t
-- ----------------------------
DROP FUNCTION IF EXISTS `nextval_t`;
delimiter ;;
CREATE FUNCTION `nextval_t`(v_seq_name VARCHAR(50))
 RETURNS int(11)
begin
DECLARE p_currval_val INTEGER;
DECLARE p_next_val INTEGER;
DECLARE p_increment_val INTEGER;
DECLARE p_row_count INTEGER;
	seqloop:loop
		select increment_val into p_increment_val from sequence_t where seq_name = v_seq_name;
		set p_currval_val = currentval(v_seq_name);
		set p_next_val = p_increment_val+p_currval_val;
    update sequence_t set current_val = p_next_val  where seq_name = v_seq_name and current_val = p_currval_val;
    select row_count() into p_row_count;
    IF 1 = p_row_count
		THEN
			LEAVE seqloop;
		end if;
	end loop;
	return p_next_val;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prc_getSequence
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_getSequence`;
delimiter ;;
CREATE PROCEDURE `prc_getSequence`(prm_yuf270    VARCHAR(30),
         OUT prm_yuf271    VARCHAR(30),
         OUT  AppCode    VARCHAR(30),
         OUT ErrorMsg        VARCHAR(10))
BEGIN
        DECLARE CONTINUE HANDLER
            FOR 1329
						insert into uf27 values (prm_yuf270, prm_yuf271);
            SET AppCode='NOERROR';
						SET prm_yuf271=1;
						SET ErrorMsg='No data';

        select yuf271 into prm_yuf271 from uf27 where yuf270 = prm_yuf270;
        update uf27
           set yuf271 = prm_yuf271 + 1
         where yuf270 = prm_yuf270
           and yuf271 = prm_yuf271;
					commit;
        SET prm_yuf271 = prm_yuf271 + 1;
      /*成功处理*/
			 SET AppCode='NOERROR';
       SET ErrorMsg = '';
    END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prc_updateAfterKufangCreated
-- ----------------------------
DROP PROCEDURE IF EXISTS `prc_updateAfterKufangCreated`;
delimiter ;;
CREATE PROCEDURE `prc_updateAfterKufangCreated`(in prm_yuh701 VARCHAR(255),
                                                OUT prm_AppCode  VARCHAR(255),
                                                OUT prm_ErrorMsg  VARCHAR(255))
begin
        DECLARE done INT DEFAULT 0;
        DECLARE _yuh744 int DEFAULT 0;
        DECLARE _yuh750 varchar(255) DEFAULT '';
    /*游标声明*/
    declare cur cursor for
    select @index:=@index+1 as yuh744, a.yuh750 as yuh750
    from (select yuh750, yuh751
          from uh75
          where yuh701 = prm_yuh701
          order by yuh751) a,(select @index:=0) b;

     --  游标中的内容执行完后将done设置为1
     DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
		 open cur;
     --  执行循环
     read_loop: LOOP
         -- 提取游标里的数据
         FETCH cur INTO _yuh744,_yuh750;

         -- 判断是否结束循环
         IF done =1 THEN
             LEAVE read_loop;
         END IF;
         update uh75 set yuh744 = _yuh744 where yuh750 = _yuh750;
     End LOOP;
     -- 关闭游标
     close cur;
		  /*成功处理*/
			 SET prm_AppCode='NOERROR';
       SET prm_ErrorMsg = '';
END
;;
delimiter ;

-- ----------------------------
-- Function structure for seq
-- ----------------------------
DROP FUNCTION IF EXISTS `seq`;
delimiter ;;
CREATE FUNCTION `seq`(seq_name varchar(20))
 RETURNS bigint(20)
BEGIN
 UPDATE seq SET val = LAST_INSERT_ID(val+1) WHERE name = seq_name;
 RETURN LAST_INSERT_ID();
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
