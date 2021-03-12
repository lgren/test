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
  `AAA100` varchar(20) NOT NULL COMMENT 'Aaa100 code category',
  `AAA101` varchar(100) NOT NULL COMMENT 'Aaa102 code value',
  `AAA102` varchar(6) NOT NULL COMMENT 'Aaa102 code value',
  `AAA103` varchar(100) NOT NULL COMMENT 'Aaa103 code name',
  `YAB003` varchar(6) NOT NULL COMMENT 'Yab003 agency',
  `AAE120` varchar(6) NOT NULL COMMENT 'Aae120 sign off',
  `VER` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`AAA100`,`AAA102`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Aa10 code table';

-- ----------------------------
-- Table structure for ab01
-- ----------------------------
DROP TABLE IF EXISTS `ab01`;
CREATE TABLE `ab01` (
  `AAB001` decimal(65,30) NOT NULL COMMENT 'Organization ID',
  `AAB004` varchar(200) NOT NULL COMMENT 'name',
  `AAE053` varchar(10) NOT NULL COMMENT 'Organization code',
  `AAB007` varchar(25) DEFAULT NULL COMMENT 'Business registration license number',
  `AAB030` varchar(20) DEFAULT NULL COMMENT 'duty paragraph',
  `AAE006` varchar(200) DEFAULT NULL COMMENT 'Address (description)',
  `BZE001` varchar(12) DEFAULT NULL COMMENT 'Address (administrative code)',
  `AAB019` varchar(6) DEFAULT NULL COMMENT 'Unit type',
  `AAB020` varchar(6) DEFAULT NULL COMMENT 'Economic type',
  `AAB021` varchar(6) DEFAULT NULL COMMENT 'Subordination',
  `AAB022` varchar(6) DEFAULT NULL COMMENT 'Industry code',
  `AAA017` varchar(6) NOT NULL COMMENT 'Effective mark of organization',
  PRIMARY KEY (`AAB001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Organization information';

-- ----------------------------
-- Table structure for ac01
-- ----------------------------
DROP TABLE IF EXISTS `ac01`;
CREATE TABLE `ac01` (
  `AAC001` varchar(20) NOT NULL COMMENT 'Personal number',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAC003` varchar(50) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(4) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(6) DEFAULT NULL COMMENT 'nation',
  `AAB004` varchar(100) DEFAULT NULL COMMENT 'Company name',
  `AAB009` varchar(100) DEFAULT NULL COMMENT 'Company address',
  `AAB005` varchar(20) DEFAULT NULL COMMENT 'Company Office Number',
  `AAC022` varchar(50) DEFAULT NULL COMMENT 'Type of certificate provided by Borrower',
  `AAC023` varchar(50) DEFAULT NULL COMMENT 'Certificate number',
  `AAC025` varchar(20) DEFAULT NULL COMMENT 'Personal contact number',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YAE116` varchar(20) DEFAULT NULL COMMENT 'Agent No',
  PRIMARY KEY (`AAC001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Basic information form of Borrower';

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
  `LOG_ID` varchar(17) NOT NULL COMMENT 'Log sequence number',
  `JOB_NAME` varchar(200) NOT NULL COMMENT 'Task name',
  `ADDRESS` varchar(200) NOT NULL COMMENT 'server address',
  `SERVICE_ID` varchar(200) NOT NULL COMMENT 'Service ID',
  `FIRED_TIME` datetime NOT NULL COMMENT 'Time of occurrence',
  `SUCCESS` varchar(6) NOT NULL COMMENT 'Success sign',
  `LOG_MSG` varchar(255) DEFAULT NULL COMMENT 'Log messages',
  PRIMARY KEY (`LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Quartz timing task execution log';

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
  `ADDRESS` varchar(200) NOT NULL COMMENT 'Application address',
  `CANUSE` varchar(1) DEFAULT NULL COMMENT 'Enable',
  PRIMARY KEY (`ADDRESS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Application address of each server in the cluster';

-- ----------------------------
-- Table structure for signrecord
-- ----------------------------
DROP TABLE IF EXISTS `signrecord`;
CREATE TABLE `signrecord` (
  `USERID` decimal(10,0) DEFAULT NULL COMMENT 'Person Id',
  `SIGNTIME` datetime DEFAULT NULL COMMENT 'Check in time',
  `SIGNSTATE` varchar(2) DEFAULT NULL COMMENT 'Check in status (1 sign in 2 sign out)',
  `IP` varchar(20) DEFAULT NULL COMMENT 'IP address',
  `MAC` varchar(20) DEFAULT NULL COMMENT 'MAC address',
  `SIGNID` decimal(20,0) NOT NULL COMMENT 'Serial number'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Check in and sign out record form';

-- ----------------------------
-- Table structure for taaccesslog
-- ----------------------------
DROP TABLE IF EXISTS `taaccesslog`;
CREATE TABLE `taaccesslog` (
  `LOGID` int(15) NOT NULL AUTO_INCREMENT COMMENT 'Log ID',
  `USERID` decimal(10,0) NOT NULL COMMENT 'User ID',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `PERMISSIONID` decimal(10,0) NOT NULL COMMENT 'Function ID',
  `ISPERMISSION` char(1) NOT NULL COMMENT 'Do you have permission',
  `ACCESSTIME` datetime NOT NULL COMMENT 'Visit time',
  `URL` text COMMENT 'Access path',
  `SYSFLAG` varchar(50) DEFAULT NULL COMMENT 'System identification',
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB AUTO_INCREMENT=199283 DEFAULT CHARSET=utf8 COMMENT='Function log table';

-- ----------------------------
-- Table structure for taadminyab139scope
-- ----------------------------
DROP TABLE IF EXISTS `taadminyab139scope`;
CREATE TABLE `taadminyab139scope` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `YAB139` varchar(6) NOT NULL COMMENT 'Data area',
  PRIMARY KEY (`POSITIONID`,`YAB139`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Management scope of administrator data area';

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
  `USERID` decimal(10,0) NOT NULL COMMENT 'User ID',
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  PRIMARY KEY (`USERID`,`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Common menu';

-- ----------------------------
-- Table structure for taconfig
-- ----------------------------
DROP TABLE IF EXISTS `taconfig`;
CREATE TABLE `taconfig` (
  `CONFIGID` decimal(10,0) NOT NULL COMMENT 'Configuration item ID',
  `CONFIGNAME` varchar(100) NOT NULL COMMENT 'Configuration item name',
  `CONFIGVALUE` text COMMENT 'Configuration item content',
  `CONFIGTYPE` text COMMENT 'Is it a system parameter',
  `CONFIGFLAG` varchar(20) NOT NULL COMMENT 'System identification',
  `CONFIGDESC` varchar(200) DEFAULT NULL COMMENT 'Parameter description',
  PRIMARY KEY (`CONFIGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='System configuration table';

-- ----------------------------
-- Table structure for taconfigsyspath
-- ----------------------------
DROP TABLE IF EXISTS `taconfigsyspath`;
CREATE TABLE `taconfigsyspath` (
  `SERIALID` decimal(10,0) NOT NULL COMMENT 'Serial number',
  `ID` varchar(20) NOT NULL COMMENT 'System ID',
  `NAME` varchar(50) NOT NULL COMMENT 'System name',
  `URL` varchar(100) NOT NULL COMMENT 'System path prefix',
  `PY` varchar(20) DEFAULT NULL COMMENT 'Pinyin',
  `CURSYSTEM` varchar(1) NOT NULL COMMENT 'Is it the current system',
  PRIMARY KEY (`SERIALID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='System path configuration table';

-- ----------------------------
-- Table structure for taconsolemodule
-- ----------------------------
DROP TABLE IF EXISTS `taconsolemodule`;
CREATE TABLE `taconsolemodule` (
  `MODULE_ID` decimal(10,0) NOT NULL COMMENT 'Module number',
  `MODULE_NAME` varchar(100) NOT NULL COMMENT 'Module name',
  `MODULE_URL` varchar(200) NOT NULL COMMENT 'Module link',
  `MODULE_STA` varchar(1) NOT NULL COMMENT 'Module valid identification',
  `MODULE_DEFAULT` varchar(1) DEFAULT NULL COMMENT 'Display by default',
  `MODULE_HEIGHT` varchar(10) DEFAULT NULL COMMENT 'Module default height',
  PRIMARY KEY (`MODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ecadmin workbench custom component';

-- ----------------------------
-- Table structure for taconsolemodulelocation
-- ----------------------------
DROP TABLE IF EXISTS `taconsolemodulelocation`;
CREATE TABLE `taconsolemodulelocation` (
  `MARK` varchar(20) NOT NULL COMMENT 'Page ID',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `LOCATION` text NOT NULL COMMENT 'Location information data'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Ecadmin workbench custom component location information';

-- ----------------------------
-- Table structure for taconsolemoduleprivilege
-- ----------------------------
DROP TABLE IF EXISTS `taconsolemoduleprivilege`;
CREATE TABLE `taconsolemoduleprivilege` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Role number',
  `MODULEID` decimal(10,0) NOT NULL COMMENT 'Module number',
  PRIMARY KEY (`POSITIONID`,`MODULEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CRM workbench custom component permission information';

-- ----------------------------
-- Table structure for tadataaccessdimension
-- ----------------------------
DROP TABLE IF EXISTS `tadataaccessdimension`;
CREATE TABLE `tadataaccessdimension` (
  `DIMENSIONID` decimal(10,0) NOT NULL COMMENT 'Dimension ID',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  `DIMENSIONTYPE` varchar(20) NOT NULL COMMENT 'Dimension type',
  `DIMENSIONPERMISSIONID` varchar(20) DEFAULT NULL COMMENT 'Dimension permission ID',
  `ALLACCESS` varchar(1) DEFAULT NULL COMMENT 'Do you have all permissions for this dimension',
  `SYSPATH` varchar(50) DEFAULT NULL COMMENT 'System identification',
  PRIMARY KEY (`DIMENSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Dimension data permission table';

-- ----------------------------
-- Table structure for tafield
-- ----------------------------
DROP TABLE IF EXISTS `tafield`;
CREATE TABLE `tafield` (
  `ID` decimal(10,0) NOT NULL COMMENT 'Primary key',
  `MENUID` decimal(10,0) DEFAULT NULL COMMENT 'Menu ID',
  `FIELDID` varchar(100) DEFAULT NULL COMMENT 'Field ID',
  `FIELDNAME` varchar(100) DEFAULT NULL COMMENT 'Field name',
  `TABLEID` varchar(10) DEFAULT NULL COMMENT 'Table ID (spare)',
  `PID` decimal(10,0) DEFAULT NULL COMMENT 'Parent node ID',
  `FIELDLEVEL` decimal(65,30) DEFAULT NULL COMMENT 'Field level'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tafieldauthrity
-- ----------------------------
DROP TABLE IF EXISTS `tafieldauthrity`;
CREATE TABLE `tafieldauthrity` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  `FIELDID` varchar(100) NOT NULL COMMENT 'Field ID',
  `LOOK` char(1) DEFAULT NULL COMMENT 'Viewable (1: viewable, 0 not viewable)',
  `EDIT` char(1) DEFAULT NULL COMMENT 'Whether it can be edited (1: can be edited, 0, can''t be viewed)',
  `CREATETIME` datetime DEFAULT NULL COMMENT 'Creation time',
  `CREATEUSER` decimal(10,0) DEFAULT NULL COMMENT 'Founder'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for talimitrate
-- ----------------------------
DROP TABLE IF EXISTS `talimitrate`;
CREATE TABLE `talimitrate` (
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  `LIMITOPEN` char(1) NOT NULL COMMENT 'Whether to turn on current limiting',
  `RATE` decimal(16,2) NOT NULL COMMENT 'Allowed access frequency',
  `TIMEOUT` decimal(65,30) NOT NULL COMMENT 'Timeout for obtaining access permission',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Access restriction';

-- ----------------------------
-- Table structure for talocalcacheversion
-- ----------------------------
DROP TABLE IF EXISTS `talocalcacheversion`;
CREATE TABLE `talocalcacheversion` (
  `VERSION` decimal(11,0) NOT NULL COMMENT 'Version number',
  `CODETYPE` varchar(20) DEFAULT NULL COMMENT 'Changed type'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Local cache code table version number';

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
  `POSITIONID` decimal(10,0) DEFAULT NULL COMMENT 'Job ID',
  `ORGID` decimal(10,0) DEFAULT NULL COMMENT 'Organization ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Organization leader (deputy) management table';

-- ----------------------------
-- Table structure for tamenu
-- ----------------------------
DROP TABLE IF EXISTS `tamenu`;
CREATE TABLE `tamenu` (
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  `PMENUID` decimal(10,0) NOT NULL COMMENT 'Parent menu ID',
  `MENUNAME` varchar(60) DEFAULT NULL COMMENT 'Function address',
  `URL` varchar(100) DEFAULT NULL COMMENT 'Function name',
  `MENUIDPATH` text COMMENT 'Menu ID path',
  `MENUNAMEPATH` text COMMENT 'Menu name path',
  `ICONSKIN` varchar(200) DEFAULT NULL COMMENT 'Select front picture',
  `SELECTIMAGE` varchar(200) DEFAULT NULL COMMENT 'Picture after selection',
  `REPORTID` varchar(50) DEFAULT NULL COMMENT 'Report ID of query report',
  `ACCESSTIMEEL` varchar(200) DEFAULT NULL COMMENT 'Access restriction time expression',
  `EFFECTIVE` char(1) NOT NULL COMMENT 'Valid sign',
  `SECURITYPOLICY` char(1) NOT NULL COMMENT 'security policy',
  `ISDISMULTIPOS` char(1) NOT NULL COMMENT 'Display multiple posts',
  `QUICKCODE` varchar(20) DEFAULT NULL COMMENT 'Quick access code',
  `SORTNO` decimal(65,30) DEFAULT NULL COMMENT 'Sort number',
  `RESOURCETYPE` char(2) NOT NULL COMMENT 'Permission type (function permission   button permission   form read only   form can be edited   table column read only   table column can be edited)',
  `MENULEVEL` decimal(65,30) DEFAULT NULL COMMENT 'Menu level',
  `ISLEAF` char(1) DEFAULT NULL COMMENT 'Leaf node or not',
  `MENUTYPE` char(1) DEFAULT NULL COMMENT 'Menu type',
  `ISCACHE` char(1) DEFAULT NULL COMMENT 'Cache or not',
  `SYSPATH` varchar(20) DEFAULT NULL COMMENT 'System path',
  `USEYAB003` char(1) DEFAULT NULL COMMENT 'Whether to enable data permission of sub center',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT 'Class ID',
  `ISAUDITE` varchar(1) DEFAULT NULL COMMENT 'Is audit required',
  `CONSOLEMODULE` char(1) DEFAULT NULL COMMENT 'Is it a workbench module',
  `CUSTOMENCODING` varchar(20) DEFAULT NULL COMMENT 'Custom coding',
  `ISFILEDSCONTROL` char(1) DEFAULT NULL COMMENT 'Do you need field permission control',
  PRIMARY KEY (`MENUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Function menu';

-- ----------------------------
-- Table structure for tamenupositionyab003
-- ----------------------------
DROP TABLE IF EXISTS `tamenupositionyab003`;
CREATE TABLE `tamenupositionyab003` (
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `YAB003` varchar(6) NOT NULL COMMENT 'Sub center',
  PRIMARY KEY (`MENUID`,`POSITIONID`,`YAB003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Function data permission';

-- ----------------------------
-- Table structure for tamessagestate
-- ----------------------------
DROP TABLE IF EXISTS `tamessagestate`;
CREATE TABLE `tamessagestate` (
  `MGID` varchar(20) NOT NULL COMMENT 'Message ID',
  `USERID` decimal(10,0) NOT NULL COMMENT 'Receiver',
  `STATE` varchar(6) DEFAULT NULL COMMENT 'Read flag'
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
  `STATDATE` varchar(20) NOT NULL COMMENT 'Statistical date',
  `POINTINTIME` varchar(20) NOT NULL COMMENT 'Statistical time point',
  `LOGINNUM` decimal(15,0) NOT NULL COMMENT 'Number of people online',
  PRIMARY KEY (`STATDATE`,`POINTINTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Time point login statistics';

-- ----------------------------
-- Table structure for taorg
-- ----------------------------
DROP TABLE IF EXISTS `taorg`;
CREATE TABLE `taorg` (
  `ORGID` decimal(10,0) NOT NULL COMMENT 'Organization ID',
  `PORGID` decimal(10,0) DEFAULT NULL COMMENT 'Organization parent ID',
  `ORGNAME` varchar(60) DEFAULT NULL COMMENT 'Name of organization',
  `COSTOMNO` varchar(10) DEFAULT NULL COMMENT 'Custom coding',
  `ORGIDPATH` text COMMENT 'Organization ID path',
  `ORGNAMEPATH` text COMMENT 'Organization name path',
  `COSTOMNOPATH` text COMMENT 'Custom encoding path',
  `ORGTYPE` char(2) DEFAULT NULL COMMENT 'Organization type (Organization (group, subsidiary), Department, group)',
  `SORT` decimal(65,30) DEFAULT NULL COMMENT 'Sort number',
  `YAB003` varchar(6) DEFAULT NULL COMMENT 'Agency',
  `DIMENSION` char(2) DEFAULT NULL COMMENT 'View dimension',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT 'Founder',
  `CREATETIME` datetime NOT NULL COMMENT 'Creation time',
  `EFFECTIVE` char(1) NOT NULL COMMENT 'Effectiveness',
  `ORGLEVEL` decimal(10,0) DEFAULT NULL COMMENT 'Organizational hierarchy',
  `ISLEAF` char(1) DEFAULT NULL COMMENT 'Leaf node or not',
  `ORGMANAGER` decimal(10,0) DEFAULT NULL COMMENT 'Head of organization (principal)',
  `DESTORY` char(1) DEFAULT NULL COMMENT 'Is it destroyed',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT 'Class ID',
  `YAB139` varchar(6) DEFAULT NULL COMMENT 'Data area',
  PRIMARY KEY (`ORGID`),
  KEY `FK_REFERENCE_6` (`PORGID`),
  CONSTRAINT `FK_REFERENCE_6` FOREIGN KEY (`PORGID`) REFERENCES `taorg` (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Organization view';

-- ----------------------------
-- Table structure for taorgmg
-- ----------------------------
DROP TABLE IF EXISTS `taorgmg`;
CREATE TABLE `taorgmg` (
  `POSITIONID` decimal(10,0) DEFAULT NULL COMMENT 'Job ID',
  `ORGID` decimal(10,0) DEFAULT NULL COMMENT 'Organization ID',
  KEY `FK_REFERENCE_8` (`ORGID`),
  KEY `FK_REFERENCE_9` (`POSITIONID`),
  CONSTRAINT `FK_REFERENCE_8` FOREIGN KEY (`ORGID`) REFERENCES `taorg` (`ORGID`),
  CONSTRAINT `FK_REFERENCE_9` FOREIGN KEY (`POSITIONID`) REFERENCES `taposition` (`POSITIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Organization management table';

-- ----------------------------
-- Table structure for taorgoplog
-- ----------------------------
DROP TABLE IF EXISTS `taorgoplog`;
CREATE TABLE `taorgoplog` (
  `LOGID` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Log ID',
  `BATCHNO` varchar(10) DEFAULT NULL COMMENT 'Operation batch number',
  `OPTYPE` char(2) DEFAULT NULL COMMENT 'Operation type (add, edit, delete, authorize, reclaim permissions)',
  `INFLUENCEBODYTYPE` char(2) DEFAULT NULL COMMENT 'Type of influencing entity (organization, personnel, position, role, permission resource)',
  `INFLUENCEBODY` varchar(10) DEFAULT NULL COMMENT 'Influence subject',
  `OPBODY` char(2) DEFAULT NULL COMMENT 'Operation entity type (personnel, organization, position, role, permission resource)',
  `OPSUBJEKT` varchar(10) DEFAULT NULL COMMENT 'Operator',
  `CHANGCONTENT` text COMMENT 'Content of subject change',
  `OPTIME` datetime NOT NULL COMMENT 'Handling time',
  `OPUSER` varchar(10) NOT NULL COMMENT 'Agent',
  `OPPOSITION` varchar(10) NOT NULL COMMENT 'Handling post',
  `ISPERMISSION` varchar(1) DEFAULT NULL COMMENT 'Do you have permission',
  PRIMARY KEY (`LOGID`)
) ENGINE=InnoDB AUTO_INCREMENT=195397 DEFAULT CHARSET=utf8 COMMENT='Organization and authority operation log';

-- ----------------------------
-- Table structure for taposition
-- ----------------------------
DROP TABLE IF EXISTS `taposition`;
CREATE TABLE `taposition` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `ORGID` decimal(10,0) NOT NULL COMMENT 'Organization ID',
  `POSITIONNAME` varchar(60) NOT NULL COMMENT 'Job title',
  `POSITIONTYPE` char(1) NOT NULL COMMENT 'Job type (personal / public)',
  `CREATEPOSITIONID` decimal(10,0) NOT NULL COMMENT 'Position used by Creator',
  `ORGIDPATH` text COMMENT 'Organization ID path',
  `ORGNAMEPATH` text COMMENT 'Organization name path',
  `VALIDTIME` datetime DEFAULT NULL COMMENT 'Only for posts generated by delegation',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT 'Founder',
  `CREATETIME` datetime NOT NULL COMMENT 'Creation time',
  `EFFECTIVE` char(1) NOT NULL COMMENT 'Valid sign',
  `ISADMIN` char(1) DEFAULT NULL COMMENT 'Is it an administrator',
  `ISSHARE` char(1) DEFAULT NULL COMMENT 'Is it a shared position',
  `ISCOPY` char(1) DEFAULT NULL COMMENT 'Copy position or not',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT 'Class ID',
  `POSITIONCATEGORY` varchar(2) DEFAULT NULL COMMENT 'Job category',
  PRIMARY KEY (`POSITIONID`),
  KEY `FK_RELATIONSHIP_5` (`ORGID`),
  CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`ORGID`) REFERENCES `taorg` (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='post';

-- ----------------------------
-- Table structure for tapositionauthrity
-- ----------------------------
DROP TABLE IF EXISTS `tapositionauthrity`;
CREATE TABLE `tapositionauthrity` (
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `MENUID` decimal(10,0) NOT NULL COMMENT 'Menu ID',
  `USEPERMISSION` char(1) DEFAULT NULL COMMENT 'Permission to use',
  `REPERMISSION` char(1) DEFAULT NULL COMMENT 'Authorizing others to use rights',
  `REAUTHRITY` char(1) DEFAULT NULL COMMENT 'Authorize others to authorize authority',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT 'Founder',
  `CREATETIME` datetime NOT NULL COMMENT 'Creation time',
  `EFFECTTIME` datetime DEFAULT NULL COMMENT 'Effective time',
  `AUDITEACCESSDATE` datetime DEFAULT NULL COMMENT 'Approval time',
  `AUDITEUSER` decimal(10,0) DEFAULT NULL COMMENT 'Reviewer',
  `AUDITSTATE` varchar(1) DEFAULT NULL COMMENT 'Audit status',
  PRIMARY KEY (`POSITIONID`,`MENUID`),
  KEY `FK_REFERENCE_7` (`MENUID`),
  CONSTRAINT `FK_REFERENCE_7` FOREIGN KEY (`MENUID`) REFERENCES `tamenu` (`MENUID`),
  CONSTRAINT `FK_RELATIONSHIP_11` FOREIGN KEY (`POSITIONID`) REFERENCES `taposition` (`POSITIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Post authority table';

-- ----------------------------
-- Table structure for tarunqianad52reference
-- ----------------------------
DROP TABLE IF EXISTS `tarunqianad52reference`;
CREATE TABLE `tarunqianad52reference` (
  `MENUID` decimal(10,0) DEFAULT NULL COMMENT 'Function number',
  `RAQFILENAME` varchar(200) DEFAULT NULL COMMENT 'File name / report ID (raqfilename)',
  `LIMITED` decimal(65,30) DEFAULT NULL COMMENT 'Display per page (limited)',
  `SCALEEXP` decimal(65,30) DEFAULT NULL COMMENT 'Scale exp in JSP',
  `ISGROUP` varchar(6) DEFAULT NULL COMMENT 'Pagination by row (isgroup)',
  `NEEDSAVEASEXCEL` varchar(6) DEFAULT NULL COMMENT 'Save as Excel (needsaveasexcel)',
  `NEEDSAVEASEXCEL2007` varchar(6) DEFAULT NULL COMMENT 'Save as Excel2007 (needsaveasexcel2007)',
  `NEEDSAVEASPDF` varchar(6) DEFAULT NULL COMMENT 'Save as PDF (needsaveaspdf)',
  `NEEDSAVEASWORD` varchar(6) DEFAULT NULL COMMENT 'Save as word (needsaveasword)',
  `NEEDSAVEASTEXT` varchar(6) DEFAULT NULL COMMENT 'Save as text (needsaveastext)',
  `NEEDPRINT` varchar(6) DEFAULT NULL COMMENT 'Save as print (needprint)',
  `ID` decimal(10,0) NOT NULL COMMENT 'Primary key ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='YHCIP_ RUNQIAN_ AD52_ Reference moisten report menu information';

-- ----------------------------
-- Table structure for tarunqianprintsetup
-- ----------------------------
DROP TABLE IF EXISTS `tarunqianprintsetup`;
CREATE TABLE `tarunqianprintsetup` (
  `SETUPID` varchar(200) NOT NULL COMMENT 'Print setup number (setup ID)',
  `SETUPVALUE` text NOT NULL COMMENT 'Print setup information (setup value)',
  PRIMARY KEY (`SETUPID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Print setup information table';

-- ----------------------------
-- Table structure for tarunqianresource
-- ----------------------------
DROP TABLE IF EXISTS `tarunqianresource`;
CREATE TABLE `tarunqianresource` (
  `RAQFILENAME` varchar(200) NOT NULL COMMENT 'File name / report ID (raqfilename)',
  `PARENTRAQFILENAME` varchar(200) DEFAULT NULL COMMENT 'Parent report ID (parentraqfilename)',
  `RAQNAME` varchar(200) DEFAULT NULL COMMENT 'Report name (raqname)',
  `RAQTYPE` varchar(6) DEFAULT NULL COMMENT 'Report type (raqtype)',
  `RAQFILE` longblob COMMENT 'Resource file (raqfile)',
  `UPLOADOR` varchar(19) DEFAULT NULL COMMENT 'Uploador',
  `UPLOADTIME` datetime DEFAULT NULL COMMENT 'Upload time',
  `SUBROW` decimal(65,30) DEFAULT NULL COMMENT 'Parent report location row (subrow)',
  `SUBCELL` decimal(65,30) DEFAULT NULL COMMENT 'Parent report location column (subcell)',
  `RAQDATASOURCE` varchar(19) DEFAULT NULL COMMENT 'Raqdatasource',
  `RAQPARAM` text COMMENT 'Report parameter JSON format str (raqparam)',
  `ORGID` varchar(15) DEFAULT NULL COMMENT 'Department number (orgid)',
  PRIMARY KEY (`RAQFILENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Run dry report template';

-- ----------------------------
-- Table structure for taserverexceptionlog
-- ----------------------------
DROP TABLE IF EXISTS `taserverexceptionlog`;
CREATE TABLE `taserverexceptionlog` (
  `ID` varchar(32) NOT NULL COMMENT 'id',
  `IPADDRESS` varchar(255) DEFAULT NULL COMMENT 'server ip address',
  `TYPE` varchar(255) DEFAULT NULL COMMENT 'Exception type',
  `CONTENT` longblob COMMENT 'Abnormal content',
  `TIME` datetime DEFAULT NULL COMMENT 'Error reporting time',
  `SYSPATH` varchar(50) DEFAULT NULL COMMENT 'System identification',
  `CLIENTIP` varchar(50) DEFAULT NULL COMMENT 'Client IP',
  `URL` varchar(100) DEFAULT NULL COMMENT 'Access function URL',
  `MENUID` varchar(8) DEFAULT NULL COMMENT 'Menu ID',
  `MENUNAME` varchar(30) DEFAULT NULL COMMENT 'Menu name',
  `USERAGENT` varchar(200) DEFAULT NULL COMMENT 'Client environment',
  `EXCEPTIONTYPE` varchar(2) DEFAULT NULL COMMENT 'Anomaly classification (1 system exception 2 business exception)',
  `PARAMETER` longblob COMMENT 'Pass in parameters'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='System exception log table';

-- ----------------------------
-- Table structure for tashareposition
-- ----------------------------
DROP TABLE IF EXISTS `tashareposition`;
CREATE TABLE `tashareposition` (
  `SPOSITIONID` decimal(10,0) DEFAULT NULL COMMENT 'Source position ID',
  `DPOSITIONID` decimal(10,0) DEFAULT NULL COMMENT 'Copy position ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Shared position table';

-- ----------------------------
-- Table structure for tauser
-- ----------------------------
DROP TABLE IF EXISTS `tauser`;
CREATE TABLE `tauser` (
  `USERID` int(10) NOT NULL AUTO_INCREMENT COMMENT 'Person Id',
  `NAME` varchar(60) NOT NULL COMMENT 'full name',
  `SEX` char(1) DEFAULT NULL COMMENT 'Gender',
  `LOGINID` varchar(20) NOT NULL COMMENT 'Login account',
  `PASSWORD` varchar(50) NOT NULL COMMENT 'password',
  `PASSWORDFAULTNUM` decimal(65,30) DEFAULT NULL COMMENT 'Number of password errors',
  `PWDLASTMODIFYDATE` datetime DEFAULT NULL COMMENT 'Password last modified time',
  `ISLOCK` char(1) DEFAULT NULL COMMENT 'Lock flag',
  `SORT` decimal(65,30) DEFAULT NULL COMMENT 'Sort number',
  `EFFECTIVE` char(1) NOT NULL COMMENT 'Valid sign',
  `TEL` varchar(15) DEFAULT NULL COMMENT 'contact number',
  `CREATEUSER` decimal(10,0) DEFAULT NULL COMMENT 'Founder',
  `CREATETIME` datetime NOT NULL COMMENT 'Creation time',
  `DIRECTORGID` decimal(10,0) NOT NULL COMMENT 'Direct organizations',
  `DESTORY` char(1) DEFAULT NULL COMMENT 'Is it destroyed',
  `TYPEFLAG` decimal(10,0) DEFAULT NULL COMMENT 'Class ID',
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=195009 DEFAULT CHARSET=utf8 COMMENT='personnel';

-- ----------------------------
-- Table structure for tauserposition
-- ----------------------------
DROP TABLE IF EXISTS `tauserposition`;
CREATE TABLE `tauserposition` (
  `USERID` int(10) NOT NULL COMMENT 'Person Id',
  `POSITIONID` decimal(10,0) NOT NULL COMMENT 'Job ID',
  `MAINPOSITION` char(1) NOT NULL COMMENT 'Default position',
  `CREATEUSER` decimal(10,0) NOT NULL COMMENT 'Founder',
  `CREATETIME` datetime NOT NULL COMMENT 'Creation time',
  PRIMARY KEY (`USERID`,`POSITIONID`),
  KEY `FK_RELATIONSHIP_10` (`POSITIONID`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`POSITIONID`) REFERENCES `taposition` (`POSITIONID`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`USERID`) REFERENCES `tauser` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Personnel post relationship table';

-- ----------------------------
-- Table structure for tayab003levelmg
-- ----------------------------
DROP TABLE IF EXISTS `tayab003levelmg`;
CREATE TABLE `tayab003levelmg` (
  `PYAB003` varchar(6) DEFAULT NULL COMMENT 'Parent agency',
  `YAB003` varchar(6) DEFAULT NULL COMMENT 'Agency'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Hierarchical relationship management of agency';

-- ----------------------------
-- Table structure for tayab003scope
-- ----------------------------
DROP TABLE IF EXISTS `tayab003scope`;
CREATE TABLE `tayab003scope` (
  `YAB003` varchar(6) NOT NULL COMMENT 'Sub center',
  `YAB139` varchar(6) NOT NULL COMMENT 'Data authority of sub center',
  PRIMARY KEY (`YAB003`,`YAB139`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Sub center data authority range table';

-- ----------------------------
-- Table structure for tayab139mg
-- ----------------------------
DROP TABLE IF EXISTS `tayab139mg`;
CREATE TABLE `tayab139mg` (
  `YAB003` varchar(6) DEFAULT NULL COMMENT 'Agency',
  `YAB139` varchar(20) DEFAULT NULL COMMENT 'Data area'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Scope of agency management data area';

-- ----------------------------
-- Table structure for ud01
-- ----------------------------
DROP TABLE IF EXISTS `ud01`;
CREATE TABLE `ud01` (
  `YUD010` varchar(20) NOT NULL COMMENT 'Information order of Archivists',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT 'area number',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `AAC001` varchar(20) NOT NULL COMMENT 'Personal number',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAC003` varchar(50) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(4) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `AAC006` datetime DEFAULT NULL COMMENT 'date of birth',
  `YUD011` varchar(6) DEFAULT NULL COMMENT 'On the job or not',
  `YUD012` varchar(6) DEFAULT NULL COMMENT 'Part time or not',
  `YUD013` varchar(6) DEFAULT NULL COMMENT 'degree of education',
  `YUD014` varchar(6) DEFAULT NULL COMMENT 'Professional degree of Archives',
  `YUD015` varchar(6) DEFAULT NULL COMMENT 'Professional and technical posts of archives cadres',
  `YUD016` varchar(6) DEFAULT NULL COMMENT 'Receive on-the-job training and education',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT 'Last modified by Department',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE013` text COMMENT 'Remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUD010`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information registration of Archivists';

-- ----------------------------
-- Table structure for ud02
-- ----------------------------
DROP TABLE IF EXISTS `ud02`;
CREATE TABLE `ud02` (
  `YUD020` varchar(20) NOT NULL COMMENT 'Archive equipment type ID',
  `YUD021` varchar(50) DEFAULT NULL COMMENT 'Equipment type name of Archives',
  `YUD022` varchar(6) DEFAULT NULL COMMENT 'Equipment category ID of Archives',
  `YUD023` varchar(50) DEFAULT NULL COMMENT 'Equipment category name of Archives',
  `YUD024` varchar(6) DEFAULT NULL COMMENT 'Equipment record type',
  `YUD025` varchar(6) DEFAULT NULL COMMENT 'Equipment record unit',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT 'Last modified by Department',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE013` text COMMENT 'Remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUD020`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Type management of archives using equipment';

-- ----------------------------
-- Table structure for ud03
-- ----------------------------
DROP TABLE IF EXISTS `ud03`;
CREATE TABLE `ud03` (
  `YUD030` varchar(20) NOT NULL COMMENT 'Equipment registration serial number of Archives',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT 'area number',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUD031` varchar(100) DEFAULT NULL COMMENT 'Equipment name',
  `YUD020` varchar(20) DEFAULT NULL COMMENT 'File equipment type',
  `YUD022` varchar(20) DEFAULT NULL COMMENT 'File equipment category',
  `YUD032` varchar(100) DEFAULT NULL COMMENT 'Equipment number',
  `YUD033` varchar(100) DEFAULT NULL COMMENT 'Equipment description',
  `YUD034` varchar(6) DEFAULT NULL COMMENT 'Is it working properly',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT 'Last modified by Department',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE013` text COMMENT 'Remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUD030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Equipment registration records of Archives';

-- ----------------------------
-- Table structure for ud10
-- ----------------------------
DROP TABLE IF EXISTS `ud10`;
CREATE TABLE `ud10` (
  `YUD101` varchar(20) NOT NULL COMMENT 'id',
  `YUD102` decimal(65,30) DEFAULT NULL COMMENT 'Archives organization',
  `YUD103` decimal(65,30) DEFAULT NULL COMMENT 'professional',
  `YUD104` decimal(65,30) DEFAULT NULL COMMENT 'Full time female',
  `YUD105` decimal(65,30) DEFAULT NULL COMMENT '50 years and above',
  `YUD106` decimal(65,30) DEFAULT NULL COMMENT '35-49 years old',
  `YUD107` decimal(65,30) DEFAULT NULL COMMENT '34 years and under',
  `YUD108` decimal(65,30) DEFAULT NULL COMMENT 'Doctoral students - Culture',
  `YUD109` decimal(65,30) DEFAULT NULL COMMENT 'Master degree - Culture',
  `YUD10A` decimal(65,30) DEFAULT NULL COMMENT 'Graduate class graduate student culture',
  `YUD10B` decimal(65,30) DEFAULT NULL COMMENT 'Double degree - Culture',
  `YUD10C` decimal(65,30) DEFAULT NULL COMMENT 'Undergraduate - Culture',
  `YUD10D` decimal(65,30) DEFAULT NULL COMMENT 'College Culture',
  `YUD10E` decimal(65,30) DEFAULT NULL COMMENT 'Technical secondary school - Culture',
  `YUD10F` decimal(65,30) DEFAULT NULL COMMENT 'High school - Culture',
  `YUD10G` decimal(65,30) DEFAULT NULL COMMENT 'Junior high school and below - Culture',
  `YUD10I` decimal(65,30) DEFAULT NULL COMMENT 'Ph.D. - Major',
  `YUD10J` decimal(65,30) DEFAULT NULL COMMENT 'Master degree - Major',
  `YUD10K` decimal(65,30) DEFAULT NULL COMMENT 'Graduate class graduate student - Major',
  `YUD10L` decimal(65,30) DEFAULT NULL COMMENT 'Undergraduate - Major',
  `YUD10M` decimal(65,30) DEFAULT NULL COMMENT 'Junior college major',
  `YUD10N` decimal(65,30) DEFAULT NULL COMMENT 'Technical secondary school major',
  `YUD10O` decimal(65,30) DEFAULT NULL COMMENT 'Vocational high school - Major',
  `YUD10P` decimal(65,30) DEFAULT NULL COMMENT 'professor of archives science',
  `YUD10Q` decimal(65,30) DEFAULT NULL COMMENT 'Associate Research Librarian',
  `YUD10R` decimal(65,30) DEFAULT NULL COMMENT 'staff member',
  `YUD10S` decimal(65,30) DEFAULT NULL COMMENT 'library assistant',
  `YUD10T` decimal(65,30) DEFAULT NULL COMMENT 'administrators',
  `YUD10U` decimal(65,30) DEFAULT NULL COMMENT 'Receive on-the-job training and education',
  `YUD10V` decimal(65,30) DEFAULT NULL COMMENT 'Part time staff',
  `YUD10W` decimal(65,30) DEFAULT NULL COMMENT 'Investment in this year',
  `YUD10X` decimal(65,30) DEFAULT NULL COMMENT 'The whole family',
  `YUD10Y` decimal(65,30) DEFAULT NULL COMMENT 'file',
  `YUD10Z` decimal(65,30) DEFAULT NULL COMMENT 'File shelving length',
  `YUD110` decimal(65,30) DEFAULT NULL COMMENT 'All files volume',
  `YUD111` decimal(65,30) DEFAULT NULL COMMENT 'All files - piece',
  `YUD112` decimal(65,30) DEFAULT NULL COMMENT 'Permanent custody - Volume',
  `YUD113` decimal(65,30) DEFAULT NULL COMMENT 'Permanent storage - piece',
  `YUD114` decimal(65,30) DEFAULT NULL COMMENT 'Current year permanent volume',
  `YUD115` decimal(65,30) DEFAULT NULL COMMENT 'This year - permanent',
  `YUD116` decimal(65,30) DEFAULT NULL COMMENT 'This year - 100 years - Volume',
  `YUD117` decimal(65,30) DEFAULT NULL COMMENT 'This year - 100 years - piece',
  `YUD118` decimal(65,30) DEFAULT NULL COMMENT 'This year - 50 years - Volume',
  `YUD119` decimal(65,30) DEFAULT NULL COMMENT 'This year - 50 years - piece',
  `YUD11A` decimal(65,30) DEFAULT NULL COMMENT 'This year - 30 years - Volume',
  `YUD11B` decimal(65,30) DEFAULT NULL COMMENT 'This year - 30 years - piece',
  `YUD11C` decimal(65,30) DEFAULT NULL COMMENT 'This year - 10 years - Volume',
  `YUD11D` decimal(65,30) DEFAULT NULL COMMENT 'This year - 10 years - piece',
  `YUD11E` decimal(65,30) DEFAULT NULL COMMENT 'Transfer to General Archives',
  `YUD11F` decimal(65,30) DEFAULT NULL COMMENT 'Transfer of archives to General Archives this year',
  `YUD11G` decimal(65,30) DEFAULT NULL COMMENT 'Files removed this year - Volume',
  `YUD11H` decimal(65,30) DEFAULT NULL COMMENT 'Files removed this year',
  `YUD11I` decimal(65,30) DEFAULT NULL COMMENT 'Destruction of archives this year - Volume',
  `YUD11J` decimal(65,30) DEFAULT NULL COMMENT 'Files destroyed this year',
  `YUD11K` decimal(65,30) DEFAULT NULL COMMENT 'Machine readable catalogue - file level',
  `YUD11L` decimal(65,30) DEFAULT NULL COMMENT 'Machine readable directory - file level',
  `YUD11M` decimal(65,30) DEFAULT NULL COMMENT 'Paper catalogue - file catalogue',
  `YUD11N` decimal(65,30) DEFAULT NULL COMMENT 'Paper catalogue - catalogue of filing documents',
  `YUD11O` decimal(65,30) DEFAULT NULL COMMENT 'Archives used in this year - person',
  `YUD11P` decimal(65,30) DEFAULT NULL COMMENT 'Archives used in this year volume',
  `YUD11Q` decimal(65,30) DEFAULT NULL COMMENT 'Files used this year',
  `YUD11R` decimal(65,30) DEFAULT NULL COMMENT 'Exhibition Room',
  `YUD11S` decimal(65,30) DEFAULT NULL COMMENT 'Public publication',
  `YUD11T` decimal(65,30) DEFAULT NULL COMMENT 'Public publication - Wanzi',
  `YUD11U` decimal(65,30) DEFAULT NULL COMMENT 'Internal reference - species',
  `YUD11V` decimal(65,30) DEFAULT NULL COMMENT 'Internal reference - Wanzi',
  `YUD11W` decimal(65,30) DEFAULT NULL COMMENT 'Total building area of Archives',
  `YUD11X` decimal(65,30) DEFAULT NULL COMMENT 'Construction area of archives warehouse',
  `YUD11Y` decimal(65,30) DEFAULT NULL COMMENT 'Construction area of Archives Office',
  `YUD11Z` decimal(65,30) DEFAULT NULL COMMENT 'Construction area of archives reading room',
  `YUD120` decimal(65,30) DEFAULT NULL COMMENT 'Compact shelf',
  `YUD121` decimal(65,30) DEFAULT NULL COMMENT 'five section cabinet',
  `YUD122` decimal(65,30) DEFAULT NULL COMMENT 'File cabinet',
  `YUD123` decimal(65,30) DEFAULT NULL COMMENT 'The server',
  `YUD124` decimal(65,30) DEFAULT NULL COMMENT 'Microcomputer',
  `YUD125` decimal(65,30) DEFAULT NULL COMMENT 'High speed',
  `YUD126` decimal(65,30) DEFAULT NULL COMMENT 'Flat',
  `YUD127` decimal(65,30) DEFAULT NULL COMMENT 'Copier',
  `YUD128` decimal(65,30) DEFAULT NULL COMMENT 'Centralized',
  `YUD129` decimal(65,30) DEFAULT NULL COMMENT 'Decentralized',
  `YUD12A` decimal(65,30) DEFAULT NULL COMMENT 'Anti theft alarm system - probe',
  `YUD12B` decimal(65,30) DEFAULT NULL COMMENT 'Fire alarm system - probe',
  `YUD12C` decimal(65,30) DEFAULT NULL COMMENT 'portable fire-extinguisher apparatus',
  `YUD12D` decimal(65,30) DEFAULT NULL COMMENT 'Dehumidifier (humidifier)',
  `YUD12E` decimal(65,30) DEFAULT NULL COMMENT 'Disinfection equipment',
  `YUD12F` decimal(65,30) DEFAULT NULL COMMENT 'File laminating machine',
  `YUD12G` varchar(100) DEFAULT NULL COMMENT 'region',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `YUD12H` varchar(20) DEFAULT NULL COMMENT 'year',
  `YUD12I` varchar(100) DEFAULT NULL COMMENT 'Person in charge of the unit',
  `YUD12J` varchar(100) DEFAULT NULL COMMENT 'Completed by',
  `YUD12K` varchar(50) DEFAULT NULL COMMENT 'contact number',
  `YUD12L` datetime DEFAULT NULL COMMENT 'Filling date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Statistical table of basic situation of social insurance business archives management';

-- ----------------------------
-- Table structure for ue01
-- ----------------------------
DROP TABLE IF EXISTS `ue01`;
CREATE TABLE `ue01` (
  `YUE010` varchar(20) NOT NULL COMMENT 'Business link number (Level 3)',
  `YUE012` varchar(200) DEFAULT NULL COMMENT 'Name of business link',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT 'Classification',
  `YUE013` varchar(18) DEFAULT NULL COMMENT 'File category number (catalog number)',
  `YUE014` varchar(200) DEFAULT NULL COMMENT 'File category name (volume name)',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period',
  `YUE01B` varchar(6) DEFAULT NULL COMMENT 'Business entity (0: company or individual, 1: company, 2: individual)',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `AAE000` varchar(6) DEFAULT NULL COMMENT 'Is it valid (0 is invalid, 1 is valid)',
  `YUE01D` varchar(200) DEFAULT NULL COMMENT 'Pinyin',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUE010`),
  KEY `IDX_UE01_YUE012` (`YUE012`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File collection table of contents';

-- ----------------------------
-- Table structure for ue02
-- ----------------------------
DROP TABLE IF EXISTS `ue02`;
CREATE TABLE `ue02` (
  `YUF001` varchar(6) NOT NULL COMMENT 'File object type',
  `YUF004` varchar(20) DEFAULT NULL COMMENT 'File object name',
  `YUE021` varchar(100) DEFAULT NULL COMMENT 'Corresponding table name',
  `YUE022` varchar(100) DEFAULT NULL COMMENT 'Corresponding primary key name',
  `YUE023` varchar(20) DEFAULT NULL COMMENT 'Service name',
  `YUE024` varchar(50) DEFAULT NULL COMMENT 'Service method',
  `YUE025` varchar(6) DEFAULT NULL COMMENT 'File object status field',
  `YUE026` varchar(6) DEFAULT NULL COMMENT 'Sub file object type',
  PRIMARY KEY (`YUF001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Auxiliary table of file objects';

-- ----------------------------
-- Table structure for ue03
-- ----------------------------
DROP TABLE IF EXISTS `ue03`;
CREATE TABLE `ue03` (
  `YUE030` varchar(50) NOT NULL COMMENT 'Business material number',
  `YUE031` text COMMENT 'Name of business material',
  `YUE032` decimal(6,0) DEFAULT NULL COMMENT 'Default number of pages',
  `YUE033` varchar(50) DEFAULT NULL COMMENT 'Raw material No',
  `AAE100` varchar(1) DEFAULT NULL COMMENT 'Valid or not 0-invalid 1-valid',
  `YUE035` varchar(6) DEFAULT NULL COMMENT 'Material subject (01 individual, 02 unit)',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE034` varchar(6) DEFAULT NULL COMMENT 'Material number prefix',
  `YUE211` varchar(20) DEFAULT NULL COMMENT 'Access system code',
  PRIMARY KEY (`YUE030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Name table of archival business materials';

-- ----------------------------
-- Table structure for ue04
-- ----------------------------
DROP TABLE IF EXISTS `ue04`;
CREATE TABLE `ue04` (
  `YUE040` varchar(25) NOT NULL COMMENT 'Related serial number',
  `YUE010` varchar(25) NOT NULL COMMENT 'Business link serial number',
  `YUE030` varchar(50) NOT NULL COMMENT 'Serial number of attached table',
  `YUE041` decimal(6,0) NOT NULL COMMENT 'Schedule No',
  `YUE042` varchar(6) NOT NULL COMMENT 'Is it necessary to collect',
  `YUE043` varchar(6) NOT NULL COMMENT 'Primary table or not',
  `YUE044` varchar(6) DEFAULT NULL COMMENT 'Does the image have to be submitted',
  `YUE045` varchar(6) DEFAULT NULL COMMENT 'Original or not',
  `YUE046` varchar(6) DEFAULT NULL COMMENT 'Save entity',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUE047` varchar(6) DEFAULT NULL COMMENT 'Is there any physical material',
  `YUE048` varchar(6) DEFAULT NULL COMMENT 'Must scan (0-no, 1-yes)',
  PRIMARY KEY (`YUE040`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Main table and attached table Association';

-- ----------------------------
-- Table structure for ue05
-- ----------------------------
DROP TABLE IF EXISTS `ue05`;
CREATE TABLE `ue05` (
  `YUE050` varchar(25) NOT NULL COMMENT 'Serial number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE010` varchar(25) DEFAULT NULL COMMENT 'Business link serial number',
  `YUE052` varchar(20) DEFAULT NULL COMMENT 'system Identifier',
  `YAE049` varchar(25) DEFAULT NULL COMMENT 'Business function serial number',
  `YAE04A` text COMMENT 'Business function name',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Correspondence table of business links and business functions';

-- ----------------------------
-- Table structure for ue05a1
-- ----------------------------
DROP TABLE IF EXISTS `ue05a1`;
CREATE TABLE `ue05a1` (
  `YUE050` varchar(25) NOT NULL COMMENT 'Serial number',
  `YUE010` varchar(25) DEFAULT NULL COMMENT 'Business link serial number',
  `YAE049` varchar(25) DEFAULT NULL COMMENT 'Business function serial number',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YAE04A` text COMMENT 'Business function name',
  `YUE051` varchar(6) DEFAULT NULL COMMENT 'Abandoned label',
  PRIMARY KEY (`YUE050`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Correspondence table of business links and business functions (abandoned records)';

-- ----------------------------
-- Table structure for ue06
-- ----------------------------
DROP TABLE IF EXISTS `ue06`;
CREATE TABLE `ue06` (
  `YUE060` varchar(20) NOT NULL COMMENT 'Serial number of file category',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `YUE061` varchar(50) NOT NULL COMMENT 'File category name',
  `YUE062` varchar(60) DEFAULT NULL COMMENT 'Corresponding table name',
  `YUE063` varchar(60) DEFAULT NULL COMMENT 'Code service name',
  `YUF001` varchar(6) NOT NULL COMMENT 'File type during business data collection (2-file 3-document 4-Box 5-box)',
  `YUE064` varchar(6) NOT NULL COMMENT 'Enabled status (1: enabled, 0 not enabled)',
  `YUE065` varchar(6) DEFAULT NULL COMMENT 'Automatic packing',
  `YUE066` varchar(6) DEFAULT NULL COMMENT 'Packing specification (2-file 3-document 4-Box 5-box)',
  `YUE067` varchar(6) DEFAULT NULL COMMENT 'Bar code continuous',
  `YUE068` varchar(6) DEFAULT NULL COMMENT 'Enable es query',
  `YUE069` varchar(20) DEFAULT NULL COMMENT 'Es index name',
  `YUE06A` varchar(6) DEFAULT NULL COMMENT 'File categories (business, finance, documents, projects, audio-visual, personnel, physical objects)',
  PRIMARY KEY (`YUE060`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File category information table';

-- ----------------------------
-- Table structure for ue07
-- ----------------------------
DROP TABLE IF EXISTS `ue07`;
CREATE TABLE `ue07` (
  `YUE070` varchar(15) NOT NULL COMMENT 'Serial number of storage period',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period code',
  `YUE071` varchar(50) DEFAULT NULL COMMENT 'Term of custody',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE072` decimal(4,0) DEFAULT NULL COMMENT 'Term year',
  PRIMARY KEY (`YUE070`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Retention period of information sheet';

-- ----------------------------
-- Table structure for ue08
-- ----------------------------
DROP TABLE IF EXISTS `ue08`;
CREATE TABLE `ue08` (
  `YUE080` varchar(15) NOT NULL,
  `YUE0C1` varchar(6) DEFAULT NULL COMMENT 'Field name',
  `YUE0C2` varchar(50) DEFAULT NULL COMMENT 'field comment',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  PRIMARY KEY (`YUE080`),
  UNIQUE KEY `UK_YUE01C` (`YUE001`,`YUE0C1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Rule field table';

-- ----------------------------
-- Table structure for ue09
-- ----------------------------
DROP TABLE IF EXISTS `ue09`;
CREATE TABLE `ue09` (
  `YUE090` varchar(20) DEFAULT NULL COMMENT 'Primary key',
  `YUE091` varchar(20) DEFAULT NULL COMMENT 'functional module',
  `YUE092` varchar(20) DEFAULT NULL COMMENT 'Operation object',
  `YUE093` varchar(20) DEFAULT NULL COMMENT 'Standard operation times',
  `YUE094` varchar(20) DEFAULT NULL COMMENT 'Standard period',
  `YUE095` varchar(20) DEFAULT NULL COMMENT 'Enable',
  `YUE001` varchar(20) DEFAULT NULL COMMENT 'Archives category',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE012` varchar(20) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE018` varchar(20) DEFAULT NULL COMMENT 'Name of agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Module button operation configuration';

-- ----------------------------
-- Table structure for ue0a
-- ----------------------------
DROP TABLE IF EXISTS `ue0a`;
CREATE TABLE `ue0a` (
  `YUE0A0` varchar(15) NOT NULL,
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `YUE002` varchar(6) NOT NULL COMMENT 'File category',
  `YUE0A1` varchar(6) NOT NULL COMMENT 'File category',
  `YUF00H` varchar(6) NOT NULL COMMENT 'Storage period',
  `YUE013` varchar(12) NOT NULL COMMENT 'Catalog number',
  `YUE014` varchar(200) NOT NULL COMMENT 'Volume name',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `AAE000` varchar(6) NOT NULL COMMENT 'Is it effective',
  PRIMARY KEY (`YUE0A0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File category information table';

-- ----------------------------
-- Table structure for ue0b
-- ----------------------------
DROP TABLE IF EXISTS `ue0b`;
CREATE TABLE `ue0b` (
  `YUE0B0` varchar(20) NOT NULL COMMENT 'File type information serial number',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `YUE002` varchar(6) NOT NULL COMMENT 'File category',
  `YUE0B1` varchar(150) NOT NULL COMMENT 'File category name',
  PRIMARY KEY (`YUE0B0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File category information table';

-- ----------------------------
-- Table structure for ue0c
-- ----------------------------
DROP TABLE IF EXISTS `ue0c`;
CREATE TABLE `ue0c` (
  `YUE0C0` varchar(20) NOT NULL COMMENT 'Information serial number of Filing Rules',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE0C1` varchar(6) DEFAULT NULL COMMENT 'The rule involves fields',
  `YUE0C2` varchar(50) DEFAULT NULL COMMENT 'The rule involves field names',
  `YUE0C3` varchar(6) DEFAULT NULL COMMENT 'Field rule =!',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE000` varchar(1) DEFAULT NULL COMMENT 'Valid or not 0-invalid 1-valid',
  `YUE0C4` varchar(6) DEFAULT NULL COMMENT 'Overall function: filing dalj, packing dazh, warehousing dart, taking collection = yut103',
  PRIMARY KEY (`YUE0C0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information table of filing / warehousing rules';

-- ----------------------------
-- Table structure for ue0d
-- ----------------------------
DROP TABLE IF EXISTS `ue0d`;
CREATE TABLE `ue0d` (
  `YAE092` varchar(15) NOT NULL COMMENT 'Operator number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUE0D1` varchar(6) DEFAULT NULL COMMENT 'Scanner type selected',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUE0D2` varchar(20) DEFAULT NULL COMMENT 'Type of altimeter selected',
  `YUE0D3` varchar(20) DEFAULT NULL COMMENT 'Type of flatbed scanner selected',
  `YUE0D4` varchar(6) DEFAULT NULL COMMENT 'High scan (FS), high scan operation (SS only)',
  PRIMARY KEY (`YAE092`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Scanner usage information';

-- ----------------------------
-- Table structure for ue0e
-- ----------------------------
DROP TABLE IF EXISTS `ue0e`;
CREATE TABLE `ue0e` (
  `YUF00Z` varchar(10) NOT NULL COMMENT 'area number',
  `YUB005` varchar(10) DEFAULT NULL COMMENT 'Code of provincial administrative division',
  `YUB004` varchar(10) DEFAULT NULL COMMENT 'Code of municipal administrative division',
  `YUE0E2` varchar(10) DEFAULT NULL COMMENT 'Administrative division level (1 Province, 2 cities, 3 counties)',
  `YUE0E3` varchar(200) DEFAULT NULL COMMENT 'Name of administrative division',
  PRIMARY KEY (`YUF00Z`),
  KEY `IDX_UE0E_01` (`YUB005`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='National administrative divisions';

-- ----------------------------
-- Table structure for ue0f
-- ----------------------------
DROP TABLE IF EXISTS `ue0f`;
CREATE TABLE `ue0f` (
  `YUE0F0` varchar(20) DEFAULT NULL COMMENT 'Primary key',
  `YUE091` varchar(20) DEFAULT NULL COMMENT 'functional module',
  `YUE0F2` varchar(20) DEFAULT NULL COMMENT 'Preconditions',
  `YUE0F3` varchar(20) DEFAULT NULL COMMENT 'Precondition status',
  `YUE0F4` varchar(20) DEFAULT NULL COMMENT 'Enable',
  `YUE001` varchar(20) DEFAULT NULL COMMENT 'Archives category',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE012` varchar(20) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE018` varchar(20) DEFAULT NULL COMMENT 'Name of agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Process configuration relation table';

-- ----------------------------
-- Table structure for ue0g
-- ----------------------------
DROP TABLE IF EXISTS `ue0g`;
CREATE TABLE `ue0g` (
  `YUB004` varchar(6) NOT NULL COMMENT 'Code of municipal administrative division',
  `YUE0E3` varchar(50) DEFAULT NULL COMMENT 'Name of administrative division',
  `YUB005` varchar(6) DEFAULT NULL COMMENT 'Code of provincial administrative division',
  `YUE0G2` varchar(100) DEFAULT NULL COMMENT 'Name of province',
  `ORGID` varchar(10) DEFAULT NULL COMMENT 'Number in organization',
  PRIMARY KEY (`YUB004`),
  UNIQUE KEY `UNIQ_UE0G` (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information of municipal institutions';

-- ----------------------------
-- Table structure for ue0h
-- ----------------------------
DROP TABLE IF EXISTS `ue0h`;
CREATE TABLE `ue0h` (
  `YUE0H0` varchar(25) NOT NULL COMMENT 'Serial number of complete information',
  `ORGID` varchar(10) NOT NULL COMMENT 'Organization ID',
  `YUB001` varchar(6) NOT NULL COMMENT 'Internal family number',
  `YUE0H1` varchar(10) DEFAULT NULL COMMENT 'Actual family number',
  `YUE0H2` varchar(200) NOT NULL COMMENT 'Full name',
  `PORGID` varchar(10) NOT NULL COMMENT 'Parent organization ID',
  `YUB004` varchar(6) NOT NULL COMMENT 'Municipal unit',
  `YUE0H3` varchar(6) DEFAULT NULL COMMENT 'Whole family code on bar code',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUF00Z` varchar(10) DEFAULT NULL COMMENT 'area number',
  `YUE0H4` varchar(6) DEFAULT NULL COMMENT 'Does the whole clan go to the district or county',
  PRIMARY KEY (`YUE0H0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Complete information sheet';

-- ----------------------------
-- Table structure for ue0j
-- ----------------------------
DROP TABLE IF EXISTS `ue0j`;
CREATE TABLE `ue0j` (
  `YUE0J0` varchar(20) NOT NULL COMMENT 'Serial number of reusable materials',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT 'area number',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Material number',
  `YUE031` varchar(100) DEFAULT NULL COMMENT 'Material name',
  `YUE035` varchar(6) DEFAULT NULL COMMENT 'Material subject (01: individual, 02 unit)',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Is it effective',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUE0J0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Reuse material information';

-- ----------------------------
-- Table structure for ue0k
-- ----------------------------
DROP TABLE IF EXISTS `ue0k`;
CREATE TABLE `ue0k` (
  `YUE0K0` varchar(20) NOT NULL COMMENT 'Authorized serial number',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Material code',
  `YUE031` text COMMENT 'Material name',
  `YUE0K1` decimal(10,0) DEFAULT NULL COMMENT 'Organization ID',
  `YUE0K2` decimal(10,0) DEFAULT NULL COMMENT 'Person Id',
  `YUE0K3` varchar(60) DEFAULT NULL COMMENT 'full name',
  `YUE0K4` varchar(60) DEFAULT NULL COMMENT 'permissions information',
  `YUE0K5` datetime DEFAULT NULL COMMENT 'Permission start time',
  `YUE0K6` datetime DEFAULT NULL COMMENT 'Permission end time',
  `AAA100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE011` decimal(10,0) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(60) DEFAULT NULL COMMENT 'attention line',
  `AAE017` decimal(10,0) DEFAULT NULL COMMENT 'Agency ID',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUE0K0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Authorization form of classified materials';

-- ----------------------------
-- Table structure for ue0m
-- ----------------------------
DROP TABLE IF EXISTS `ue0m`;
CREATE TABLE `ue0m` (
  `YUE0M0` varchar(50) NOT NULL COMMENT 'Serial number of classified materials',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Material number',
  `YUE031` varchar(100) DEFAULT NULL COMMENT 'Material name',
  `YUF00I` varchar(20) DEFAULT NULL COMMENT 'Material classification',
  `YAE049` varchar(20) DEFAULT NULL COMMENT 'Business function number',
  `YAE04A` text COMMENT 'Business function name',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `AAE100` varchar(50) DEFAULT NULL COMMENT 'Valid sign',
  PRIMARY KEY (`YUE0M0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Configuration information of classified materials';

-- ----------------------------
-- Table structure for ue0n
-- ----------------------------
DROP TABLE IF EXISTS `ue0n`;
CREATE TABLE `ue0n` (
  `YUE0N0` varchar(20) NOT NULL COMMENT 'Serial number of institution information using reuse',
  `ORGID` varchar(50) DEFAULT NULL COMMENT 'Institution ID',
  `ORGNAME` varchar(100) DEFAULT NULL COMMENT 'Name of organization',
  `ORGPATH` varchar(100) DEFAULT NULL COMMENT 'Institutional path',
  `YUE0N1` varchar(6) DEFAULT NULL COMMENT 'Whether to use reuse function',
  `YUE0N2` varchar(6) DEFAULT NULL COMMENT 'Write to multiplex image library',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency ID',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  PRIMARY KEY (`YUE0N0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Using reusable institutional information';

-- ----------------------------
-- Table structure for ue0p
-- ----------------------------
DROP TABLE IF EXISTS `ue0p`;
CREATE TABLE `ue0p` (
  `YUE0P0` varchar(20) NOT NULL COMMENT 'Business function registration serial number',
  `YAE049` varchar(50) DEFAULT NULL COMMENT 'Business function number',
  `YAE04A` varchar(100) DEFAULT NULL COMMENT 'Business function name',
  `YUE211` varchar(20) DEFAULT NULL COMMENT 'Business system code',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency ID',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  PRIMARY KEY (`YUE0P0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business function registration information';

-- ----------------------------
-- Table structure for ue0q
-- ----------------------------
DROP TABLE IF EXISTS `ue0q`;
CREATE TABLE `ue0q` (
  `YUE0Q0` varchar(20) NOT NULL COMMENT 'Account registration serial number of financial account set',
  `YUE0Q1` decimal(4,0) DEFAULT NULL COMMENT 'A / C set accounting subject year',
  `YUE0Q2` varchar(20) DEFAULT NULL COMMENT 'A / C set code',
  `YUE0Q3` varchar(100) DEFAULT NULL COMMENT 'A / C set name',
  `YUE0Q4` varchar(20) DEFAULT NULL COMMENT 'Account code',
  `YUE0Q5` varchar(200) DEFAULT NULL COMMENT 'Account name',
  `YUE0Q6` text COMMENT 'Full name of accounting subject',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency ID',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  PRIMARY KEY (`YUE0Q0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Account configuration information of financial A / C set';

-- ----------------------------
-- Table structure for ue0r
-- ----------------------------
DROP TABLE IF EXISTS `ue0r`;
CREATE TABLE `ue0r` (
  `YUE0R0` varchar(20) NOT NULL COMMENT 'Configuration information of whole management category',
  `YUE0H0` varchar(25) NOT NULL COMMENT 'Serial number of complete information',
  `YUF00Z` varchar(10) NOT NULL COMMENT 'area number',
  `YUB001` varchar(6) NOT NULL COMMENT 'Internal family number',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  PRIMARY KEY (`YUE0R0`),
  KEY `FK_YUE0H0` (`YUE0H0`),
  CONSTRAINT `FK_YUE0H0` FOREIGN KEY (`YUE0H0`) REFERENCES `ue0h` (`YUE0H0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Configuration information of whole management category';

-- ----------------------------
-- Table structure for ue10
-- ----------------------------
DROP TABLE IF EXISTS `ue10`;
CREATE TABLE `ue10` (
  `YUE100` varchar(20) DEFAULT NULL COMMENT 'Primary key',
  `YUE091` varchar(20) DEFAULT NULL COMMENT 'functional module',
  `YUE0F2` varchar(20) DEFAULT NULL COMMENT 'Preconditions',
  `YUE101` varchar(20) DEFAULT NULL COMMENT 'Multiple conditions at the same time',
  `YUE001` varchar(20) DEFAULT NULL COMMENT 'Archives category',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE012` varchar(20) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE018` varchar(20) DEFAULT NULL COMMENT 'Name of agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Correspondence between modules and preconditions';

-- ----------------------------
-- Table structure for ue16
-- ----------------------------
DROP TABLE IF EXISTS `ue16`;
CREATE TABLE `ue16` (
  `YUE160` varchar(20) NOT NULL COMMENT 'Field serial number',
  `YUE161` text COMMENT 'Comment / field name',
  `YUE162` decimal(4,0) NOT NULL COMMENT 'The order of fields in the barcode (must be continuous, starting with 1)',
  `YUE163` varchar(8) NOT NULL COMMENT 'Field code',
  `YUF001` varchar(1) NOT NULL COMMENT 'Barcode category code (2-file, 3-document, 4-Box, 5-box)',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `YUE166` decimal(2,0) NOT NULL COMMENT 'Number of digits of serial number',
  `YUE168` varchar(1) DEFAULT NULL COMMENT 'Is serial number dependent (yes - y, no - n)',
  `YUE169` varchar(10) DEFAULT NULL COMMENT 'Data type (string, integer, timestamp, default)',
  `YUE164` varchar(1) DEFAULT NULL COMMENT 'Year, month, and day',
  `YUE16A` varchar(6) DEFAULT NULL COMMENT 'Fixed value field',
  `YUE16B` varchar(10) DEFAULT NULL COMMENT 'Fixed value',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE000` varchar(1) DEFAULT NULL COMMENT 'Valid or not 0-invalid 1-valid',
  PRIMARY KEY (`YUE160`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Bar code rules';

-- ----------------------------
-- Table structure for ue18
-- ----------------------------
DROP TABLE IF EXISTS `ue18`;
CREATE TABLE `ue18` (
  `YUE180` varchar(20) NOT NULL COMMENT 'Serial number of personnel file collection directory',
  `YUE181` varchar(6) DEFAULT NULL COMMENT 'Personnel file collection directory number',
  `YUE182` varchar(100) DEFAULT NULL COMMENT 'Collection directory name',
  `YUE183` varchar(3) DEFAULT NULL COMMENT 'Is it necessary to collect',
  `YUE184` decimal(4,0) DEFAULT NULL COMMENT 'Sequence number',
  `YUE185` varchar(6) DEFAULT NULL COMMENT 'Effective marking',
  PRIMARY KEY (`YUE180`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Personnel file collection directory';

-- ----------------------------
-- Table structure for ue19
-- ----------------------------
DROP TABLE IF EXISTS `ue19`;
CREATE TABLE `ue19` (
  `YUE190` varchar(50) NOT NULL COMMENT 'File identity and material mapping serial number',
  `YUJ03C` varchar(50) DEFAULT NULL COMMENT 'File identity',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Serial number of accessories',
  `YUE031` text COMMENT 'Attachment table name',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT 'Schedule No',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent No',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `YAE011` varchar(50) DEFAULT NULL COMMENT 'attention line',
  PRIMARY KEY (`YUE190`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File identity and material mapping table';

-- ----------------------------
-- Table structure for ue20
-- ----------------------------
DROP TABLE IF EXISTS `ue20`;
CREATE TABLE `ue20` (
  `YUE200` varchar(20) NOT NULL COMMENT 'Parameter code',
  `YUE201` varchar(200) DEFAULT NULL COMMENT 'Parameter name',
  `YUE202` varchar(200) DEFAULT NULL COMMENT 'Parameter description',
  `YUE203` varchar(100) DEFAULT NULL COMMENT 'Parameter value',
  `YUE204` varchar(1) DEFAULT NULL COMMENT 'Parameter type',
  `YUE205` varchar(1) DEFAULT NULL COMMENT 'Editable flag',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `AAE100` varchar(1) DEFAULT NULL COMMENT 'Valid identification',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUE200`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File global parameter table';

-- ----------------------------
-- Table structure for ue21
-- ----------------------------
DROP TABLE IF EXISTS `ue21`;
CREATE TABLE `ue21` (
  `YUE210` varchar(20) NOT NULL COMMENT 'Serial number',
  `YUE211` varchar(20) DEFAULT NULL COMMENT 'Access system code',
  `YUE212` varchar(50) DEFAULT NULL COMMENT 'Access system name',
  `AAE100` varchar(10) DEFAULT NULL COMMENT 'Valid sign',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(60) NOT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Operator Department',
  `YUE213` varchar(10) DEFAULT NULL COMMENT 'Enable status',
  PRIMARY KEY (`YUE210`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File access system management';

-- ----------------------------
-- Table structure for ue30
-- ----------------------------
DROP TABLE IF EXISTS `ue30`;
CREATE TABLE `ue30` (
  `YUE300` varchar(30) NOT NULL COMMENT 'Field primary key',
  `YUE301` varchar(30) DEFAULT NULL COMMENT 'Field code',
  `YUE302` varchar(50) DEFAULT NULL COMMENT 'Field name',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Category',
  `YUE303` varchar(6) DEFAULT NULL COMMENT 'Table to which field belongs',
  `YUE304` varchar(30) DEFAULT NULL COMMENT 'Field data type',
  `YUE305` varchar(50) DEFAULT NULL COMMENT 'Field verification rules',
  `YUE306` decimal(6,0) DEFAULT NULL COMMENT 'Field length limit',
  `YUE30A` varchar(6) DEFAULT NULL COMMENT 'Is it an index column',
  `YUE30B` varchar(6) DEFAULT NULL COMMENT 'Whether to use word segmentation',
  `YUE30C` varchar(30) DEFAULT NULL COMMENT 'Word breaker type',
  `YUB004` varchar(50) DEFAULT NULL COMMENT 'administrative division',
  `YUB001` varchar(50) DEFAULT NULL COMMENT 'The whole family',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Department ID',
  `AAE018` varchar(50) DEFAULT NULL COMMENT 'Department name',
  `AAE036` datetime DEFAULT NULL COMMENT 'Business handling time',
  `YUE308` varchar(30) DEFAULT NULL COMMENT 'Field default value',
  `AAE013` varchar(100) DEFAULT NULL COMMENT 'Remarks',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'Name of operator',
  `YUE307` varchar(6) DEFAULT NULL COMMENT 'Unique field identification',
  `YUE309` varchar(6) DEFAULT NULL COMMENT 'Is null ID allowed',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  PRIMARY KEY (`YUE300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uf01
-- ----------------------------
DROP TABLE IF EXISTS `uf01`;
CREATE TABLE `uf01` (
  `YUF010` varchar(50) NOT NULL COMMENT 'Serial number of file fittings',
  `YUF011` varchar(50) DEFAULT NULL COMMENT 'bar code',
  `YUF0B0` varchar(20) DEFAULT NULL COMMENT 'Fixture type serial number',
  `YUF012` decimal(10,0) DEFAULT NULL COMMENT 'Fixture serial number',
  `YUF013` varchar(6) DEFAULT NULL COMMENT 'Use or not',
  `YUB002` varchar(20) DEFAULT NULL COMMENT 'Holding institution',
  `YUC001` varchar(25) DEFAULT NULL COMMENT 'holder',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT 'year',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT 'month',
  `YUH750` varchar(50) DEFAULT NULL COMMENT 'Lattice ID',
  `YUF016` decimal(3,0) DEFAULT NULL COMMENT 'Cell number',
  `YUF017` datetime DEFAULT NULL COMMENT 'Storage time of Archives',
  `YUF018` datetime DEFAULT NULL COMMENT 'Storage time of Archives',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF019` varchar(6) DEFAULT NULL COMMENT 'File installation warehousing status, the default value is 0: not in storage',
  `YUI880` decimal(15,0) DEFAULT NULL COMMENT 'File object process serial number',
  `YUH710` varchar(15) DEFAULT NULL COMMENT 'Area code',
  `YUH770` varchar(15) DEFAULT NULL COMMENT 'Group code',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period',
  `YUF12B` varchar(6) DEFAULT NULL COMMENT 'Whether to pack the box again',
  `YUF014` varchar(6) DEFAULT NULL COMMENT 'Automatic packing',
  `YUF01B` decimal(4,0) DEFAULT NULL COMMENT 'Number of volumes',
  `YUF00N` decimal(4,0) DEFAULT NULL COMMENT 'number',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT 'the number of pages',
  `YUH701` varchar(15) DEFAULT NULL COMMENT 'Warehouse object ID',
  `YUF01C` varchar(15) DEFAULT NULL COMMENT 'File object type in the equipment (2: file, 3: file)',
  `YUE013` varchar(10) DEFAULT NULL COMMENT 'File category number',
  `YUF015` varchar(6) DEFAULT NULL COMMENT 'Whether the file fixture can be put into storage',
  `YUF01A` varchar(6) DEFAULT NULL COMMENT 'File fixture transfer status',
  `YUF005` varchar(6) DEFAULT NULL COMMENT 'Lost state',
  `YUF006` varchar(6) DEFAULT NULL COMMENT 'Damage status',
  `YUF00L` datetime DEFAULT NULL COMMENT 'Starting time of storage',
  `YUF00M` datetime DEFAULT NULL COMMENT 'End of storage time',
  `YUF00B` varchar(6) DEFAULT NULL COMMENT 'File keeping status',
  `YUF00Y` varchar(6) DEFAULT NULL COMMENT 'type of insurance',
  `YUJ001` varchar(25) DEFAULT NULL COMMENT 'Table field 1',
  `YUJ002` varchar(25) DEFAULT NULL COMMENT 'Table field 2',
  `YUJ003` varchar(25) DEFAULT NULL COMMENT 'Table field 3',
  `YUF125` varchar(20) DEFAULT NULL COMMENT 'Inspector',
  `YUF126` datetime DEFAULT NULL COMMENT 'Inspection time',
  `YUF127` text COMMENT 'remarks',
  PRIMARY KEY (`YUF010`),
  KEY `IDX_YUF015` (`YUH750`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File equipment';

-- ----------------------------
-- Table structure for uf03
-- ----------------------------
DROP TABLE IF EXISTS `uf03`;
CREATE TABLE `uf03` (
  `YUF030` varchar(50) NOT NULL COMMENT 'Business accounting account details No',
  `YUF110` varchar(50) DEFAULT NULL COMMENT 'Serial number of main table of file system',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Financial acceptance number',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(25) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUF032` varchar(50) DEFAULT NULL COMMENT 'Account code',
  `YUF033` varchar(100) DEFAULT NULL COMMENT 'Account name',
  `YUF035` varchar(6) DEFAULT NULL COMMENT 'Accounting debit and credit',
  `YUF036` decimal(18,2) DEFAULT NULL COMMENT 'amount of money',
  `AAE013` text COMMENT 'Remarks',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Effective labeling',
  PRIMARY KEY (`YUF030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business bookkeeping accounting directory information';

-- ----------------------------
-- Table structure for uf04
-- ----------------------------
DROP TABLE IF EXISTS `uf04`;
CREATE TABLE `uf04` (
  `YUF040` varchar(10) NOT NULL COMMENT 'Serial number of main part details',
  `YUF110` varchar(50) DEFAULT NULL COMMENT 'Document serial number',
  `YUF031` decimal(8,0) DEFAULT NULL COMMENT 'the number of pages',
  `YUF034` decimal(8,0) DEFAULT NULL COMMENT 'Serial number',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `AAB004` varchar(100) DEFAULT NULL COMMENT 'Unit name',
  `AAC001` varchar(20) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(20) DEFAULT NULL COMMENT 'Citizenship card number',
  `AAC003` varchar(100) DEFAULT NULL COMMENT 'full name',
  `AAC021` varchar(50) DEFAULT NULL COMMENT 'Unemployment certificate No',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT 'Personal identification code',
  `YUF004` varchar(6) DEFAULT NULL COMMENT 'Scanning status: 1: not scanned; 0, scanning; 2, scanned',
  PRIMARY KEY (`YUF040`),
  KEY `INDEX_YUF110_UF04` (`YUF110`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='One table multi person list';

-- ----------------------------
-- Table structure for uf05
-- ----------------------------
DROP TABLE IF EXISTS `uf05`;
CREATE TABLE `uf05` (
  `YUF050` varchar(50) NOT NULL COMMENT 'Serial number of picture details',
  `YUF052` varchar(100) DEFAULT NULL COMMENT 'Picture name',
  `YUF051` decimal(10,0) DEFAULT NULL COMMENT 'Picture serial number',
  `YUF002` varchar(50) DEFAULT NULL COMMENT 'Business table primary key',
  `YUF055` text COMMENT 'Image URL (thumbnail)',
  `YUF054` varchar(200) DEFAULT NULL COMMENT 'Key (store thumbnail file)',
  `YUF056` decimal(8,0) DEFAULT NULL COMMENT 'Picture size (thumbnail)',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Business material serial number',
  `YUF053` varchar(6) DEFAULT NULL COMMENT 'Picture saving method (0: disk; 1: UCM)',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File type (3 cases, 2 files)',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent No',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YAE049` varchar(30) DEFAULT NULL COMMENT 'Function number',
  `YUF05L` varchar(6) DEFAULT NULL COMMENT 'Picture height (pixels)',
  `YUF05M` varchar(6) DEFAULT NULL COMMENT 'Picture width (pixels)',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF05J` varchar(6) DEFAULT NULL COMMENT 'Image source (1: remote, 2: license Library)',
  `YUF05K` varchar(6) DEFAULT NULL COMMENT 'Can I edit it',
  `YUF05N` text COMMENT 'remarks',
  `YUF05P` text COMMENT 'Image URL (original)',
  `YUF05Q` varchar(200) DEFAULT NULL COMMENT 'File storage key (original)',
  `YUF05R` decimal(8,0) DEFAULT NULL COMMENT 'Picture size (original)',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT 'Personal identification code',
  `YUF241` varchar(6) DEFAULT NULL COMMENT 'Is it a picture',
  `YUB004` varchar(25) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUF060` varchar(20) DEFAULT NULL COMMENT 'Multiplex image serial number',
  `YUR010` varchar(20) DEFAULT NULL COMMENT 'File adjustment serial number',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `YUF057` varchar(6) DEFAULT NULL COMMENT 'Identification mark',
  `YUF059` decimal(1,0) DEFAULT NULL COMMENT 'Number of rotations (- 1,0,1,2)',
  `YUT340` varchar(25) DEFAULT NULL COMMENT 'OCR log operation ID',
  `YUF05A` varchar(30) DEFAULT NULL COMMENT 'Picture unique ID',
  PRIMARY KEY (`YUF050`),
  KEY `INDEX_UF05_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Archives image information';

-- ----------------------------
-- Table structure for uf05a1
-- ----------------------------
DROP TABLE IF EXISTS `uf05a1`;
CREATE TABLE `uf05a1` (
  `YUF130` varchar(20) NOT NULL COMMENT 'Serial number of picture annotation',
  `YUF131` decimal(4,0) DEFAULT NULL COMMENT 'X coordinate',
  `YUF132` decimal(4,0) DEFAULT NULL COMMENT 'Y coordinate',
  `YUF133` varchar(100) DEFAULT NULL COMMENT 'Comment content',
  `YUF134` varchar(6) DEFAULT NULL COMMENT 'Comment type (1: text 2: picture)',
  `YUF050` varchar(50) DEFAULT NULL COMMENT 'Picture serial number',
  PRIMARY KEY (`YUF130`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Picture annotation information';

-- ----------------------------
-- Table structure for uf06
-- ----------------------------
DROP TABLE IF EXISTS `uf06`;
CREATE TABLE `uf06` (
  `YUF060` varchar(20) NOT NULL COMMENT 'Multiplex image serial number',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'Archives object',
  `YUF002` varchar(20) DEFAULT NULL COMMENT 'Business table primary key',
  `YUF070` varchar(50) DEFAULT NULL COMMENT 'Serial number of material reuse version',
  `YUE0J0` varchar(20) DEFAULT NULL COMMENT 'Serial number of reusable materials',
  `YUF052` varchar(100) DEFAULT NULL COMMENT 'Picture name',
  `YUF051` decimal(10,0) DEFAULT NULL COMMENT 'Picture serial number',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Material number',
  `YUF05Q` varchar(200) DEFAULT NULL COMMENT 'File storage key (original)',
  `YUF05P` text COMMENT 'Image URL (original)',
  `YUF054` varchar(200) DEFAULT NULL COMMENT 'File storage key (thumbnail)',
  `YUF055` text COMMENT 'Image URL (thumbnail)',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Is it effective',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUF240` varchar(50) DEFAULT NULL COMMENT 'Original image serial number',
  `YUF057` varchar(6) DEFAULT NULL COMMENT 'Identification mark',
  `YUF05A` varchar(30) DEFAULT NULL COMMENT 'Picture unique ID',
  `YUT340` varchar(25) DEFAULT NULL COMMENT 'Identification ID',
  PRIMARY KEY (`YUF060`),
  KEY `IN_UF06_YUF070` (`YUF070`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Multiplex image information';

-- ----------------------------
-- Table structure for uf07
-- ----------------------------
DROP TABLE IF EXISTS `uf07`;
CREATE TABLE `uf07` (
  `YUF070` varchar(50) NOT NULL COMMENT 'Serial number of material reuse version',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(6) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF002` varchar(100) DEFAULT NULL COMMENT 'File object ID',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB003` varchar(20) DEFAULT NULL COMMENT 'Unit code',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'Citizenship card number',
  `AAC003` text COMMENT 'Personal name',
  `YUE035` varchar(6) DEFAULT NULL COMMENT 'Material subject (01: individual, 02 unit)',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Business material number',
  `YUF071` varchar(100) DEFAULT NULL COMMENT 'Version number of reusable materials',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  `AAE013` text COMMENT 'remarks',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUE0J0` varchar(20) DEFAULT NULL COMMENT 'Serial number of reusable materials',
  PRIMARY KEY (`YUF070`),
  KEY `INDX_UF07_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Material reuse version record information';

-- ----------------------------
-- Table structure for uf11
-- ----------------------------
DROP TABLE IF EXISTS `uf11`;
CREATE TABLE `uf11` (
  `YUF110` varchar(20) NOT NULL COMMENT 'Serial number of main table of file system',
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'File object barcode',
  `YUF111` varchar(50) DEFAULT NULL COMMENT 'File file association code',
  `YUF112` decimal(4,0) DEFAULT NULL COMMENT 'Serial number in the volume',
  `YUF114` varchar(6) DEFAULT NULL COMMENT 'One multi volume identification',
  `YUF115` varchar(6) DEFAULT NULL COMMENT 'With attachment identification',
  `YUF116` decimal(6,0) DEFAULT NULL COMMENT 'Number of attachments',
  `YUC001` varchar(20) NOT NULL COMMENT 'holder',
  `YUB002` varchar(50) NOT NULL COMMENT 'Holding institution',
  `YUB001` varchar(6) NOT NULL COMMENT 'Quanzong number',
  `YUF004` varchar(6) DEFAULT NULL COMMENT 'Scanning status (0: not scanned; 1, scanning; 2, scanned)',
  `YUF005` varchar(6) DEFAULT NULL COMMENT 'Is it lost',
  `YUF006` varchar(6) DEFAULT NULL COMMENT 'Is it damaged',
  `YUF007` varchar(6) DEFAULT NULL COMMENT 'Is it destroyed',
  `YUF008` varchar(6) DEFAULT NULL COMMENT 'Warehousing or not',
  `YUF009` varchar(6) DEFAULT NULL COMMENT 'Lending ID (0-not lent, 1-lent, 2-generated borrowing batch, 3-approved)',
  `YUF00A` varchar(6) DEFAULT NULL COMMENT 'Image processing status',
  `YUF00B` varchar(6) DEFAULT NULL COMMENT 'File status',
  `YUF00C` varchar(6) DEFAULT NULL COMMENT 'Filing status',
  `YUF00D` varchar(6) DEFAULT NULL COMMENT 'Handover status',
  `YAB003` varchar(20) DEFAULT NULL COMMENT 'Business handling sub center',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Business operators',
  `AAE036` datetime DEFAULT NULL COMMENT 'Business handling time',
  `AAE013` text COMMENT 'remarks',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT 'year',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT 'month',
  `YUE013` varchar(10) DEFAULT NULL COMMENT 'File category number',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT 'Classification',
  `YUF00J` decimal(6,0) DEFAULT NULL COMMENT 'Number of copies of documents',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT 'Number of document pages',
  `YUF00L` datetime DEFAULT NULL COMMENT 'Storage start time',
  `YUF00M` datetime DEFAULT NULL COMMENT 'Storage end time',
  `YUF00P` decimal(4,0) DEFAULT NULL COMMENT 'Page Scanned',
  `YUF010` varchar(50) DEFAULT NULL COMMENT 'Serial number of file fittings',
  `YUF113` datetime DEFAULT NULL COMMENT 'Date of document',
  `YUH750` varchar(50) DEFAULT NULL COMMENT 'Grid number',
  `YAB001` varchar(20) DEFAULT NULL COMMENT 'Business handling sub center (original data)',
  `YUF00R` varchar(50) DEFAULT NULL COMMENT 'reference',
  `YUF117` varchar(25) DEFAULT NULL COMMENT 'Original volume barcode',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT 'Last modified by Department',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `YUF00S` decimal(4,0) DEFAULT NULL COMMENT 'Serial number of equipment',
  `YUF118` decimal(5,0) DEFAULT NULL COMMENT 'start page number',
  `YUF119` decimal(5,0) DEFAULT NULL COMMENT 'End page number',
  `YUF00Z` varchar(10) DEFAULT NULL COMMENT 'area number',
  `YUF128` varchar(6) DEFAULT NULL COMMENT 'Scan lock status (0 not locked, 1 locked)',
  `YUF00Y` varchar(6) DEFAULT NULL COMMENT 'Type of insurance (type of collection unit)',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Business acceptance number',
  `YUF11A` varchar(6) DEFAULT NULL COMMENT 'Business system data confirmation identification',
  `YUF11B` varchar(1) DEFAULT NULL COMMENT 'File unsealing status (0 unsealed)',
  `YUF00T` varchar(6) DEFAULT NULL COMMENT 'File transfer status (0 not transferred)',
  `YUF016` decimal(4,0) DEFAULT NULL COMMENT 'Grid number',
  `YUF141` varchar(50) DEFAULT NULL COMMENT 'Box bar code',
  `YUF129` decimal(4,0) DEFAULT NULL COMMENT 'Box serial number',
  `YUF12A` varchar(6) DEFAULT NULL COMMENT 'Packing status (packed or not)',
  `YUF12B` varchar(6) DEFAULT NULL COMMENT 'Packing status (whether or not to pack)',
  `YUF00X` varchar(6) DEFAULT NULL COMMENT 'data sources',
  `YUF00G` varchar(6) DEFAULT NULL COMMENT 'Is there more than one person per watch',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `YUF00O` varchar(10) DEFAULT NULL COMMENT 'Is paper material kept',
  `YUF11C` decimal(6,0) DEFAULT NULL COMMENT 'Part number',
  `YUJ001` varchar(25) DEFAULT NULL COMMENT 'Table field 1',
  `YUJ002` varchar(25) DEFAULT NULL COMMENT 'Table field 2',
  `YUJ003` varchar(25) DEFAULT NULL COMMENT 'Table field 3',
  `YUB004` varchar(10) DEFAULT NULL,
  `YUF11D` text COMMENT 'Document image description',
  `YUF11E` varchar(6) DEFAULT NULL COMMENT 'OCR identification mark',
  PRIMARY KEY (`YUF110`),
  KEY `INDEX_UF11_1` (`YAZ001`),
  KEY `INDEX_UF11_YUB002` (`YUB002`),
  KEY `INDEX_UF11_YUC001` (`YUC001`),
  KEY `INDEX_UF11_YUE001` (`YUE001`),
  KEY `INDEX_UF11_YUF010` (`YUF010`),
  KEY `INDEX_UF11_YUF111` (`YUF111`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File information main table of file system';

-- ----------------------------
-- Table structure for uf12
-- ----------------------------
DROP TABLE IF EXISTS `uf12`;
CREATE TABLE `uf12` (
  `YUF120` varchar(20) NOT NULL COMMENT 'File information serial number of file system',
  `YUJ0X0` varchar(20) DEFAULT NULL COMMENT 'File basic information primary key',
  `YUF003` varchar(50) NOT NULL COMMENT 'File object barcode',
  `YUF111` varchar(50) DEFAULT NULL COMMENT 'File file association code',
  `YUB001` varchar(6) NOT NULL COMMENT 'Quanzong number',
  `YUF004` varchar(6) DEFAULT NULL COMMENT 'Scan',
  `YUF005` varchar(6) DEFAULT NULL COMMENT 'Is it lost',
  `YUF006` varchar(6) DEFAULT NULL COMMENT 'Is it damaged',
  `YUF007` varchar(6) DEFAULT NULL COMMENT 'Is it destroyed',
  `YUF008` varchar(6) DEFAULT NULL COMMENT 'Warehousing or not',
  `YUF009` varchar(6) DEFAULT NULL COMMENT 'Loan or not',
  `YUF00A` varchar(6) DEFAULT NULL COMMENT 'Image processing status',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUC001` varchar(20) NOT NULL COMMENT 'holder',
  `YUB002` varchar(50) NOT NULL COMMENT 'Holding institution',
  `YUF00B` varchar(6) DEFAULT NULL COMMENT 'File status',
  `YUF00D` varchar(6) DEFAULT NULL COMMENT 'Handover status',
  `YAB003` varchar(20) DEFAULT NULL COMMENT 'Business handling sub center',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Business operators',
  `AAE036` datetime NOT NULL COMMENT 'Business handling time',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT 'year',
  `YUE013` varchar(10) DEFAULT NULL COMMENT 'File category number',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT 'Classification',
  `YUF00N` decimal(6,0) DEFAULT NULL COMMENT 'Number of cases',
  `YUF00L` datetime DEFAULT NULL COMMENT 'Storage start time',
  `YUF00M` datetime DEFAULT NULL COMMENT 'Storage end time',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT 'Number of document pages',
  `YUF00P` decimal(6,0) DEFAULT NULL COMMENT 'Page Scanned',
  `YUF00Q` decimal(10,0) DEFAULT NULL COMMENT 'File sequence number',
  `YUF00R` varchar(100) DEFAULT NULL COMMENT 'reference',
  `YUF010` varchar(50) DEFAULT NULL COMMENT 'Serial number of file fittings',
  `YUF00S` decimal(65,30) DEFAULT NULL COMMENT 'Serial number of equipment',
  `YUH750` varchar(50) DEFAULT NULL COMMENT 'Grid number',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT 'Last modified by Department',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `YUF121` text COMMENT 'title',
  `YUE014` varchar(100) DEFAULT NULL COMMENT 'File category name',
  `YUF123` varchar(20) DEFAULT NULL COMMENT 'Filing person',
  `YUF124` datetime DEFAULT NULL COMMENT 'Filing time',
  `YUF125` varchar(20) DEFAULT NULL COMMENT 'Inspector',
  `YUF126` datetime DEFAULT NULL COMMENT 'Inspection time',
  `YUF127` text COMMENT 'remarks',
  `YUF00Z` varchar(10) DEFAULT NULL COMMENT 'area number',
  `YUF128` varchar(6) DEFAULT NULL COMMENT 'Scan lock status (0 not locked, 1 locked)',
  `YUF00Y` varchar(6) DEFAULT NULL COMMENT 'Type of insurance (type of collection unit)',
  `YUF12X` varchar(6) DEFAULT NULL COMMENT 'Unit attribute',
  `YUF016` decimal(4,0) DEFAULT NULL COMMENT 'Cell number',
  `YUF141` varchar(50) DEFAULT NULL COMMENT 'Box bar code',
  `YUF129` decimal(4,0) DEFAULT NULL COMMENT 'Box serial number',
  `YUF12A` varchar(6) DEFAULT NULL COMMENT 'Packing status (packed or not)',
  `YUF12B` varchar(6) DEFAULT NULL COMMENT 'Packing status (secondary packing or not)',
  `YUF114` varchar(6) DEFAULT NULL COMMENT 'One multi volume identification',
  `YUF118` decimal(5,0) DEFAULT NULL COMMENT 'start page number',
  `YUF119` decimal(5,0) DEFAULT NULL COMMENT 'End page number',
  `YUF12C` decimal(3,0) DEFAULT NULL COMMENT 'Internal sequence number',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `YUF00C` varchar(6) DEFAULT NULL COMMENT 'Filing status',
  `YUB004` varchar(25) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `AAE012` varchar(60) DEFAULT NULL COMMENT 'attention line',
  `YUF12D` varchar(60) DEFAULT NULL COMMENT 'Name of filing person',
  `YUF00X` varchar(60) DEFAULT NULL COMMENT 'data sources',
  `YUJ001` varchar(25) DEFAULT NULL COMMENT 'Table field 1',
  `YUJ002` varchar(25) DEFAULT NULL COMMENT 'Table field 2',
  `YUJ003` varchar(25) DEFAULT NULL COMMENT 'Table field 3',
  PRIMARY KEY (`YUF120`),
  KEY `INDEX_UF12_YUB002` (`YUB002`),
  KEY `INDEX_UF12_YUE001` (`YUE001`),
  KEY `INDEX_UF12_YUF010` (`YUF010`),
  KEY `INDEX_UF12_YUF111` (`YUF111`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Main file information table of archives system';

-- ----------------------------
-- Table structure for uf13
-- ----------------------------
DROP TABLE IF EXISTS `uf13`;
CREATE TABLE `uf13` (
  `YUJ0X0` varchar(50) NOT NULL COMMENT 'Business table primary key',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `YUE030` varchar(50) NOT NULL COMMENT 'Material number',
  `YUE031` text COMMENT 'Material name',
  `YUE046` decimal(6,0) DEFAULT NULL COMMENT 'Material pages',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT 'Schedule number sequence',
  `YUE042` varchar(6) DEFAULT NULL COMMENT 'Is it necessary to collect',
  `YUE043` varchar(6) DEFAULT NULL COMMENT 'Primary table or not',
  `YUE044` varchar(6) DEFAULT NULL COMMENT 'Do I have to submit images',
  `YUE047` varchar(6) DEFAULT NULL COMMENT 'Is there any physical material',
  `YUE048` varchar(6) DEFAULT NULL COMMENT 'Must scan (0-no, 1-yes)',
  `YUF04Y` varchar(50) NOT NULL COMMENT 'Personal identification code',
  `YUE035` varchar(6) DEFAULT NULL COMMENT 'Material subject (01 individual, 02 unit)',
  PRIMARY KEY (`YUJ0X0`,`YUE030`,`YUF04Y`,`YUE001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information table of archival materials';

-- ----------------------------
-- Table structure for uf14
-- ----------------------------
DROP TABLE IF EXISTS `uf14`;
CREATE TABLE `uf14` (
  `YUF140` varchar(25) NOT NULL COMMENT 'Case serial number',
  `YUF141` varchar(50) DEFAULT NULL COMMENT 'Box bar code',
  `YUF142` varchar(25) DEFAULT NULL COMMENT 'Bin location information ID',
  `YUF143` decimal(6,0) DEFAULT NULL COMMENT 'Box capacity',
  `YUF00N` decimal(6,0) NOT NULL COMMENT 'Number of files in the box (number of volumes)',
  `YUF00K` decimal(6,0) NOT NULL COMMENT 'Number of files in the box (pages)',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT 'year',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'Personnel file type',
  `YUF144` decimal(6,0) NOT NULL COMMENT 'Case serial number',
  `YUB002` varchar(50) DEFAULT NULL COMMENT 'Holding institution',
  `YUC001` varchar(50) DEFAULT NULL COMMENT 'holder',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUF145` text COMMENT 'remarks',
  `YUF146` varchar(6) DEFAULT NULL COMMENT 'Case status',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT 'Classification',
  `YUF00L` datetime DEFAULT NULL COMMENT 'Storage start time',
  `YUF00M` datetime DEFAULT NULL COMMENT 'Storage end time',
  `YUF00D` varchar(6) DEFAULT NULL COMMENT 'Handover status',
  `YUF00A` varchar(6) DEFAULT NULL COMMENT 'Image processing status',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUF140`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File box information';

-- ----------------------------
-- Table structure for uf20
-- ----------------------------
DROP TABLE IF EXISTS `uf20`;
CREATE TABLE `uf20` (
  `yaz001` varchar(50) NOT NULL COMMENT 'Business acceptance number',
  `yae049` varchar(25) DEFAULT NULL COMMENT 'Menu number',
  `yue001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `yae051` varchar(6) DEFAULT NULL COMMENT 'Business process number',
  `aab001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `aab003` varchar(20) DEFAULT NULL COMMENT 'Unit code',
  `aab004` varchar(500) DEFAULT NULL COMMENT 'Unit name',
  `aac001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `aac002` varchar(18) DEFAULT NULL COMMENT 'Citizenship card number',
  `aac003` varchar(500) DEFAULT NULL COMMENT 'Personal name',
  `aae017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `aae011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `yuf00e` int(4) DEFAULT NULL COMMENT 'year',
  `yuf00f` int(2) DEFAULT NULL COMMENT 'month',
  `yuf00k` int(6) DEFAULT NULL COMMENT 'the number of pages',
  `aae036` date DEFAULT NULL COMMENT 'Handling time',
  `yuf00p` int(4) DEFAULT NULL COMMENT 'Page Scanned',
  `yuf004` varchar(6) DEFAULT NULL COMMENT 'Scan flag',
  `yuf00g` varchar(6) DEFAULT NULL COMMENT 'Multi person logo',
  `yuf000` varchar(6) DEFAULT NULL COMMENT 'Whether to transfer material information sign',
  `yub001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `yug643` varchar(6) DEFAULT NULL COMMENT 'Handover mark (0 - not handed over, 1 - handed over, 2 - received, 3 - returned)',
  `yuf201` varchar(6) DEFAULT NULL COMMENT 'File modification flag (0-not modified, 1-modified)',
  `aae000` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  `aae001` varchar(50) DEFAULT NULL COMMENT 'Organization',
  `aae002` varchar(50) DEFAULT NULL COMMENT 'Organizer',
  `aae003` date DEFAULT NULL COMMENT 'Completion time',
  `yue003` varchar(6) DEFAULT NULL COMMENT 'Is it finished',
  `yuf003` varchar(50) DEFAULT NULL COMMENT 'File object barcode',
  `yue010` varchar(20) DEFAULT NULL COMMENT 'Document category code',
  `yuf203` varchar(6) DEFAULT '0' COMMENT 'Fallback times',
  `yuf00x` varchar(6) DEFAULT NULL COMMENT 'data sources',
  `yuf00z` varchar(10) DEFAULT NULL COMMENT 'area number',
  `yuf00o` varchar(6) DEFAULT NULL COMMENT 'Is paper material kept',
  `yub002` varchar(25) DEFAULT NULL COMMENT 'Holding institution',
  `yuc001` varchar(25) DEFAULT NULL COMMENT 'holder',
  `yab003` varchar(25) DEFAULT NULL COMMENT 'Sub center',
  `yuj0x0` varchar(20) DEFAULT NULL COMMENT 'File basic information primary key',
  `yuf113` date DEFAULT NULL COMMENT 'Date of document',
  `yub004` varchar(25) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `yae04a` varchar(50) DEFAULT NULL COMMENT 'Business menu name',
  `yuf00y` varchar(6) DEFAULT NULL COMMENT 'type of insurance',
  `yuf205` varchar(6) DEFAULT NULL COMMENT 'Business handling status (0 not accepted, 1 accepted, 2 handled)',
  `aae013` varchar(2000) DEFAULT NULL COMMENT 'Remarks',
  `yuf11d` varchar(500) DEFAULT NULL COMMENT 'Notes on document image',
  `yuf206` varchar(50) DEFAULT NULL COMMENT 'Original business acceptance number',
  `yuf207` varchar(6) DEFAULT NULL COMMENT 'Business operation mode (00 - normal operation of business, 01 - modification after completion of business)',
  `yuf208` varchar(6) DEFAULT NULL COMMENT 'Is the correction correct',
  `uf20_time_index` datetime GENERATED ALWAYS AS (date_format(`aae036`,'%Y/%m/%d')) VIRTUAL,
  PRIMARY KEY (`yaz001`),
  UNIQUE KEY `yuf003` (`yuf003`),
  KEY `INDEX_UF20_YUB001` (`yub001`),
  KEY `INDEX_UF20_01` (`uf20_time_index`,`aae000`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business system interface data information table';

-- ----------------------------
-- Table structure for uf21
-- ----------------------------
DROP TABLE IF EXISTS `uf21`;
CREATE TABLE `uf21` (
  `AAZ002` varchar(50) NOT NULL COMMENT 'Business ID',
  `YAZ001` varchar(50) NOT NULL COMMENT 'Business acceptance ID',
  `YAE049` varchar(25) DEFAULT NULL COMMENT 'Menu number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YAE051` varchar(6) DEFAULT NULL COMMENT 'Business process number',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'File object barcode',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB003` varchar(20) DEFAULT NULL COMMENT 'Unit code',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'Citizenship number',
  `AAC003` text COMMENT 'Personal name',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT 'year',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT 'month',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT 'the number of pages',
  `YUF00P` decimal(3,0) DEFAULT NULL COMMENT 'Page Scanned',
  `YUF004` varchar(6) DEFAULT NULL COMMENT 'Scan flag',
  `YUF00G` varchar(6) DEFAULT NULL COMMENT 'Multi person logo',
  `YUF213` varchar(6) DEFAULT NULL COMMENT 'Whether to transfer material information sign',
  `YUE003` varchar(6) DEFAULT NULL COMMENT 'Is the business completed',
  `YUF11A` varchar(6) DEFAULT NULL COMMENT 'Data confirmation identification',
  `YUJ01K` datetime DEFAULT NULL COMMENT 'Business completion time',
  `YUG643` varchar(6) DEFAULT NULL COMMENT 'Handover mark (0 - not handed over, 1 - handed over, 3 - returned, 4 - entering batch)',
  `YUF211` varchar(6) DEFAULT NULL COMMENT 'File modification flag (0 - not modified, 1 - not modified, 2 - modified)',
  `YUF212` varchar(6) DEFAULT NULL COMMENT 'Is it the main business (0-no, 1-yes)',
  `AAE000` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  `AAE001` varchar(50) DEFAULT NULL COMMENT 'Organization',
  `AAE002` varchar(50) DEFAULT NULL COMMENT 'Organizer',
  `AAE003` datetime DEFAULT NULL COMMENT 'Completion time',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUB004` varchar(6) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUF113` datetime DEFAULT NULL COMMENT 'Date of document',
  `YUF00U` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00V` varchar(20) DEFAULT NULL COMMENT 'Finally, modify the organization',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  PRIMARY KEY (`AAZ002`),
  KEY `INDX_UF21_YAZ001` (`YAZ001`),
  KEY `INDX_UF21_YUF003` (`YUF003`),
  CONSTRAINT `F_UF20_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business system interface business processing data information table';

-- ----------------------------
-- Table structure for uf22
-- ----------------------------
DROP TABLE IF EXISTS `uf22`;
CREATE TABLE `uf22` (
  `YUF220` varchar(50) NOT NULL COMMENT 'Serial number',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Business acceptance number',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Material code',
  `YUE031` text COMMENT 'Material name',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT 'Material sequence number',
  `YUE045` decimal(6,0) DEFAULT NULL COMMENT 'Copies of materials',
  `YUE046` decimal(6,0) DEFAULT NULL COMMENT 'Material pages',
  `YUE047` varchar(6) DEFAULT NULL COMMENT 'Is there any physical material',
  `YUF202` varchar(6) DEFAULT NULL COMMENT 'Is the main file (0-no, 1-yes)',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE000` varchar(6) DEFAULT NULL COMMENT 'Effective mark of material',
  `YUF00V` varchar(50) DEFAULT NULL COMMENT 'Modified by Department',
  `YUF00U` varchar(50) DEFAULT NULL COMMENT 'Modified by',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Modification time',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT 'Personal identification code',
  `YUE048` varchar(6) DEFAULT NULL COMMENT 'Must scan (0-no, 1-yes)',
  `YUE049` varchar(36) DEFAULT NULL COMMENT 'Choose one more sign',
  `YUE043` varchar(6) DEFAULT NULL COMMENT 'Is it report material',
  `CHECKTYPE` varchar(6) DEFAULT NULL COMMENT 'Type of examination',
  `YUF221` varchar(200) DEFAULT NULL COMMENT 'Material notes',
  `YUF222` varchar(20) DEFAULT NULL COMMENT 'Unit material or not',
  `YUF223` varchar(6) DEFAULT NULL COMMENT 'Is it a reusable material',
  `YUF070` varchar(50) DEFAULT NULL COMMENT 'Serial number of material reuse version',
  `YUF071` varchar(100) DEFAULT NULL COMMENT 'Material reuse version number',
  `YUE035` varchar(6) DEFAULT NULL COMMENT 'Material subject (01 individual, 02 unit)',
  PRIMARY KEY (`YUF220`),
  KEY `INDEX_UF22_YAZ001` (`YAZ001`),
  KEY `INDEX_UF22_YUF070` (`YUF070`),
  CONSTRAINT `F_UF22_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business acceptance file information table';

-- ----------------------------
-- Table structure for uf23
-- ----------------------------
DROP TABLE IF EXISTS `uf23`;
CREATE TABLE `uf23` (
  `YUF230` varchar(50) NOT NULL COMMENT 'Serial number',
  `AAZ002` varchar(50) DEFAULT NULL COMMENT 'Business serial number',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Business acceptance number',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Material code',
  `YUE031` text COMMENT 'Material name',
  `YUE041` decimal(6,0) DEFAULT NULL COMMENT 'Material sequence number',
  `YUE045` decimal(6,0) DEFAULT NULL COMMENT 'Copies of materials',
  `YUE046` decimal(6,0) DEFAULT NULL COMMENT 'Material pages',
  `YUE047` varchar(6) DEFAULT NULL COMMENT 'Is there any physical material',
  `YUF202` varchar(6) DEFAULT NULL COMMENT 'Is the main file (0-no, 1-yes)',
  `AAE000` varchar(6) DEFAULT NULL COMMENT 'Effective mark of material',
  `YUF231` varchar(6) DEFAULT NULL COMMENT 'Archive type',
  `YUF00V` varchar(50) DEFAULT NULL COMMENT 'Last modified by Department',
  `YUF00U` varchar(50) DEFAULT NULL COMMENT 'Last modified by',
  `YUF00W` datetime DEFAULT NULL COMMENT 'Last modification time',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUF230`),
  KEY `INDX_UF23_YAZ001` (`YAZ001`),
  KEY `F_UF21_AAZ002` (`AAZ002`),
  CONSTRAINT `F_UF21_AAZ002` FOREIGN KEY (`AAZ002`) REFERENCES `uf21` (`AAZ002`),
  CONSTRAINT `F_UF23_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information table of business handling archives';

-- ----------------------------
-- Table structure for uf24
-- ----------------------------
DROP TABLE IF EXISTS `uf24`;
CREATE TABLE `uf24` (
  `yuf240` varchar(50) NOT NULL COMMENT 'Serial number',
  `yuf052` varchar(100) DEFAULT NULL COMMENT 'Picture name',
  `yuf051` int(10) DEFAULT NULL COMMENT 'Picture serial number',
  `yaz001` varchar(50) DEFAULT NULL COMMENT 'Business acceptance serial number',
  `yuf054` varchar(200) DEFAULT NULL COMMENT 'File storage key (thumbnail)',
  `yuf055` varchar(500) DEFAULT NULL COMMENT 'Image URL (thumbnail)',
  `yue030` varchar(50) DEFAULT NULL COMMENT 'Material code',
  `yuf053` varchar(6) DEFAULT NULL COMMENT 'Picture saving method (0: disk; 1: UCM)',
  `yuf001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `yae049` varchar(30) DEFAULT NULL COMMENT 'Function coding',
  `aae011` varchar(50) DEFAULT NULL COMMENT 'Agent No',
  `aae017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `aae036` date DEFAULT NULL COMMENT 'Handling time',
  `yub001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `yub004` varchar(6) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `yuf056` int(8) DEFAULT NULL COMMENT 'Picture size (thumbnail)',
  `yuf05l` varchar(6) DEFAULT NULL COMMENT 'Picture height (pixels)',
  `yuf05m` varchar(6) DEFAULT NULL COMMENT 'Picture width (pixels)',
  `yuf05j` varchar(6) DEFAULT NULL COMMENT 'Image source (1: remote, 2: license library, 3: multiplexing)',
  `yuf05k` varchar(6) DEFAULT NULL COMMENT 'Can I edit it',
  `aae013` varchar(300) DEFAULT NULL COMMENT 'remarks',
  `yuf05p` varchar(500) DEFAULT NULL COMMENT 'Image URL (original)',
  `yuf05q` varchar(200) DEFAULT NULL COMMENT 'File storage key (original)',
  `yuf05r` int(8) DEFAULT NULL COMMENT 'Picture size (original)',
  `yuf04y` varchar(50) DEFAULT NULL COMMENT 'Personal identification code',
  `yuf241` varchar(6) DEFAULT NULL COMMENT 'Is it a picture',
  `yuf242` varchar(6) DEFAULT NULL COMMENT 'Valid or not: 0 is not valid',
  `yuf060` varchar(20) DEFAULT NULL COMMENT 'Multiplex image serial number',
  `yuf057` varchar(10) DEFAULT NULL COMMENT 'Character recognition',
  `yuf059` int(1) DEFAULT NULL COMMENT 'Number of rotations (- 1,0,1,2)',
  `yut340` varchar(25) DEFAULT NULL COMMENT 'OCR log operation ID',
  `yuf05a` varchar(30) DEFAULT NULL COMMENT 'Picture unique ID',
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
  `YUF250` varchar(50) NOT NULL COMMENT 'Serial number',
  `YAZ001` varchar(50) NOT NULL COMMENT 'Acceptance number',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB003` varchar(50) DEFAULT NULL COMMENT 'Unit code',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(25) DEFAULT NULL COMMENT 'Citizenship number',
  `AAC003` varchar(100) DEFAULT NULL COMMENT 'Personal name',
  `AAC021` varchar(50) DEFAULT NULL COMMENT 'Unemployment certificate No',
  `YUF031` decimal(4,0) DEFAULT NULL COMMENT 'the number of pages',
  `YUF034` decimal(4,0) DEFAULT NULL COMMENT 'Serial number',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT 'Personal identification code',
  `YUF004` varchar(6) DEFAULT NULL COMMENT 'Scan status',
  PRIMARY KEY (`YUF250`),
  KEY `INDX_UF25_YAZ001` (`YAZ001`),
  CONSTRAINT `F_UF25_YAZ001` FOREIGN KEY (`YAZ001`) REFERENCES `uf20` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business system interface one table multi person information';

-- ----------------------------
-- Table structure for uf26
-- ----------------------------
DROP TABLE IF EXISTS `uf26`;
CREATE TABLE `uf26` (
  `YUF260` varchar(25) NOT NULL COMMENT 'Serial number of image operation information',
  `YAZ001` varchar(25) DEFAULT NULL COMMENT 'Acceptance code',
  `YAE049` varchar(25) DEFAULT NULL COMMENT 'Business type',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(6) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF261` varchar(25) DEFAULT NULL COMMENT 'License code',
  `YUF262` varchar(6) DEFAULT NULL COMMENT 'Image operation mode',
  `YUF263` decimal(6,0) DEFAULT NULL COMMENT 'Used times',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE000` varchar(6) DEFAULT NULL COMMENT 'Is it effective',
  `YUF264` longblob COMMENT 'Yaz001list being viewed for',
  PRIMARY KEY (`YUF260`),
  KEY `INDX_UF26_YAZ001` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business system image call information record table';

-- ----------------------------
-- Table structure for uf27
-- ----------------------------
DROP TABLE IF EXISTS `uf27`;
CREATE TABLE `uf27` (
  `YUF270` varchar(50) NOT NULL COMMENT 'Sequence name',
  `YUF271` decimal(8,0) DEFAULT NULL COMMENT 'Sequence value',
  PRIMARY KEY (`YUF270`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Sequence information of each internal organization';

-- ----------------------------
-- Table structure for uf28
-- ----------------------------
DROP TABLE IF EXISTS `uf28`;
CREATE TABLE `uf28` (
  `YUF280` varchar(50) NOT NULL COMMENT 'Serial number',
  `AAZ002` varchar(50) NOT NULL COMMENT 'Business log ID',
  `AAB191` datetime DEFAULT NULL COMMENT 'Date of receipt',
  `AAE019` decimal(16,2) DEFAULT NULL COMMENT 'Amount received',
  `YAF010` varchar(50) DEFAULT NULL COMMENT 'Receiving bank',
  `AAZ010` varchar(50) DEFAULT NULL COMMENT 'Party ID',
  `AAB069` text COMMENT 'Name of Party',
  `AAE009` varchar(100) DEFAULT NULL COMMENT 'Account name of the other party',
  `AAE010` varchar(40) DEFAULT NULL COMMENT 'Account number of the other party',
  `YAD008` text COMMENT 'Summary information',
  `YUF281` varchar(6) NOT NULL COMMENT 'Business registration prefix',
  `YUF282` decimal(6,0) DEFAULT NULL COMMENT 'Page number of attachments',
  `YUF283` decimal(6,0) DEFAULT NULL COMMENT 'Number of nearby images',
  `YUF004` varchar(6) DEFAULT NULL COMMENT 'Scan flag',
  `YUF284` varchar(50) DEFAULT NULL COMMENT 'Financial bookkeeping association number',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Business acceptance number',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(25) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Effective labeling',
  PRIMARY KEY (`YUF280`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business system interface financial accounting information';

-- ----------------------------
-- Table structure for uf29
-- ----------------------------
DROP TABLE IF EXISTS `uf29`;
CREATE TABLE `uf29` (
  `YUF290` varchar(50) NOT NULL COMMENT 'Business accounting account details No',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Financial acceptance number',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(25) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUF032` varchar(20) DEFAULT NULL COMMENT 'Account code',
  `YUF033` varchar(100) DEFAULT NULL COMMENT 'Account name',
  `YUF036` decimal(18,2) DEFAULT NULL COMMENT 'amount of money',
  `AAE013` text COMMENT 'Remarks',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Effective labeling',
  `YUF035` varchar(6) DEFAULT NULL COMMENT 'Accounting debit and credit',
  PRIMARY KEY (`YUF290`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business bookkeeping accounting directory information';

-- ----------------------------
-- Table structure for uf2a
-- ----------------------------
DROP TABLE IF EXISTS `uf2a`;
CREATE TABLE `uf2a` (
  `YUF2A0` varchar(20) NOT NULL,
  `YAZ001` varchar(20) DEFAULT NULL COMMENT 'Business coding',
  `YUF2A1` longblob COMMENT 'input data',
  `YUF2A2` longblob COMMENT 'output data',
  `YUF2A3` varchar(200) DEFAULT NULL COMMENT 'Call method',
  `YUF2A4` varchar(6) DEFAULT NULL COMMENT 'Normal return',
  `AAE036` datetime DEFAULT NULL COMMENT 'Record the time',
  PRIMARY KEY (`YUF2A0`),
  KEY `IDX_UF2A_01` (`YAZ001`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File interface log operation record';

-- ----------------------------
-- Table structure for ug30
-- ----------------------------
DROP TABLE IF EXISTS `ug30`;
CREATE TABLE `ug30` (
  `YUG300` varchar(20) NOT NULL COMMENT 'Serial number of imaging examination',
  `YUG301` varchar(6) DEFAULT NULL COMMENT 'Type of examination (Level 1, level 2)',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUG305` varchar(100) DEFAULT NULL COMMENT 'Inspector',
  `YUG302` decimal(6,0) DEFAULT NULL COMMENT 'Number of checks',
  `YUG304` datetime DEFAULT NULL COMMENT 'Inspection time',
  `YUG303` text COMMENT 'Check the situation',
  `YUG306` decimal(3,0) DEFAULT NULL COMMENT 'Percentage to be inspected',
  `YUG307` decimal(3,0) DEFAULT NULL COMMENT 'Percent inspected',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG309` varchar(6) DEFAULT NULL COMMENT 'Inspection status (generate batch, complete batch, start quality inspection, finish quality inspection)',
  `YUG308` varchar(6) DEFAULT NULL COMMENT 'Quality inspection method (page by page, tile)',
  PRIMARY KEY (`YUG300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Inspection batch';

-- ----------------------------
-- Table structure for ug31
-- ----------------------------
DROP TABLE IF EXISTS `ug31`;
CREATE TABLE `ug31` (
  `YUG310` varchar(50) NOT NULL COMMENT 'Inspection details_ ID',
  `YUF002` varchar(50) DEFAULT NULL COMMENT 'Check object ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUG300` varchar(20) DEFAULT NULL COMMENT 'Inspection batch_ ID',
  `YUG312` varchar(6) DEFAULT NULL COMMENT 'Check status',
  `YUG311` decimal(6,0) DEFAULT NULL COMMENT 'Check due page',
  `YUG313` varchar(50) DEFAULT NULL COMMENT 'Check cut-off objects',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUG316` varchar(6) DEFAULT NULL COMMENT 'Pass quality inspection',
  `YUG317` varchar(200) DEFAULT NULL COMMENT 'Picture error message',
  `YUG318` varchar(6) DEFAULT NULL COMMENT 'Is it handled',
  `AAE036` datetime DEFAULT NULL COMMENT 'Completion time of quality inspection',
  PRIMARY KEY (`YUG310`),
  KEY `INDEX_UG31_YUF002` (`YUF002`),
  KEY `INDEX_UG31_YUG300` (`YUG300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Inspection details';

-- ----------------------------
-- Table structure for ug32
-- ----------------------------
DROP TABLE IF EXISTS `ug32`;
CREATE TABLE `ug32` (
  `YUG320` varchar(50) NOT NULL COMMENT 'Inspection details_ ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUF002` varchar(100) DEFAULT NULL COMMENT 'File object ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'Document bar code',
  `YUG325` varchar(6) DEFAULT NULL COMMENT 'Processing status',
  `YUG322` char(1) DEFAULT NULL COMMENT 'Is it qualified',
  `YUG323` varchar(6) DEFAULT NULL COMMENT 'Type of disqualification',
  `YUG324` text COMMENT 'Disqualification',
  `YUG326` decimal(6,0) DEFAULT NULL COMMENT 'Page number file',
  `YUG327` text COMMENT 'Check the situation',
  `YUG328` varchar(100) DEFAULT NULL COMMENT 'Inspector',
  `AAE013` text COMMENT 'remarks',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG310` varchar(50) NOT NULL COMMENT 'Inspection details_ ID',
  `YUG300` varchar(20) DEFAULT NULL COMMENT 'Inspection batch_ ID',
  `YUF050` varchar(20) DEFAULT NULL COMMENT 'Serial number of picture information',
  PRIMARY KEY (`YUG320`),
  KEY `INDEX_UG32_YUF002` (`YUF002`),
  KEY `INDEX_UG32_YUF003` (`YUF003`),
  KEY `INDEX_UG32_YUG310` (`YUG310`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Records of file inspection';

-- ----------------------------
-- Table structure for ug33
-- ----------------------------
DROP TABLE IF EXISTS `ug33`;
CREATE TABLE `ug33` (
  `YUG330` varchar(20) NOT NULL COMMENT 'File quality inspection list',
  `YUG310` varchar(50) NOT NULL COMMENT 'Inspection details_ ID',
  `YUF120` varchar(50) DEFAULT NULL COMMENT 'Check file ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUG300` varchar(20) DEFAULT NULL COMMENT 'Inspection batch_ ID',
  `YUG312` varchar(6) DEFAULT NULL COMMENT 'Check status',
  `YUG311` decimal(6,0) DEFAULT NULL COMMENT 'Check due page',
  `YUF110` varchar(50) DEFAULT NULL COMMENT 'Check file ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUG316` varchar(6) DEFAULT NULL COMMENT 'Pass quality inspection',
  `YUG317` varchar(200) DEFAULT NULL COMMENT 'Picture error message',
  `YUG318` varchar(6) DEFAULT NULL COMMENT 'Is it handled',
  `AAE036` datetime DEFAULT NULL COMMENT 'Completion time of quality inspection',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'Document bar code',
  PRIMARY KEY (`YUG330`),
  KEY `INDEX_UG33_YUF003` (`YUF003`),
  KEY `INDEX_UG33_YUF120` (`YUF120`),
  KEY `INDEX_UG33_YUG300` (`YUG300`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File quality inspection list';

-- ----------------------------
-- Table structure for ug34
-- ----------------------------
DROP TABLE IF EXISTS `ug34`;
CREATE TABLE `ug34` (
  `YUG340` varchar(50) NOT NULL COMMENT 'Digital processing serial number',
  `YUF002` varchar(100) DEFAULT NULL COMMENT 'File object ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUG341` varchar(3) DEFAULT NULL COMMENT 'Operation type',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUG300` varchar(50) DEFAULT NULL COMMENT 'Quality inspection lot ID',
  `YUG310` varchar(50) DEFAULT NULL COMMENT 'Quality inspection batch details ID',
  PRIMARY KEY (`YUG340`),
  KEY `INDEX_UG34_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information table of picture operation (supplementary scanning) after quality inspection';

-- ----------------------------
-- Table structure for ug40
-- ----------------------------
DROP TABLE IF EXISTS `ug40`;
CREATE TABLE `ug40` (
  `YUG400` varchar(20) NOT NULL COMMENT 'Identification record serial number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'Archives object',
  `YUG401` decimal(6,0) DEFAULT NULL COMMENT 'Identification number',
  `YUG403` char(1) DEFAULT NULL COMMENT 'Is destruction approved',
  `YUG404` char(1) DEFAULT NULL COMMENT 'Is it authorized to modify the storage period',
  `YUG405` varchar(15) DEFAULT NULL COMMENT 'Approved by',
  `YUG406` varchar(100) DEFAULT NULL COMMENT 'Approval department',
  `YUG407` datetime DEFAULT NULL COMMENT 'Date of approval',
  `YUG408` text COMMENT 'Approval opinions',
  `YUG409` varchar(6) DEFAULT NULL COMMENT 'Identification status: 0 not identified 1 identified not reviewed 2 identified reviewed 3 destroyed but not postponed operation 4 postponed operation executed 5 all operations completed and cannot be changed',
  `YUG40A` varchar(25) DEFAULT NULL COMMENT 'Destroyer',
  `YUG40B` varchar(25) DEFAULT NULL COMMENT 'Supervisor',
  `YUG40C` varchar(25) DEFAULT NULL COMMENT 'Deferrer',
  `YUG40D` varchar(60) DEFAULT NULL COMMENT 'Destroyer Department',
  `YUG40E` varchar(60) DEFAULT NULL COMMENT 'Supervisor department',
  `YUG40F` varchar(60) DEFAULT NULL COMMENT 'Deferrer Department',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG40G` varchar(20) DEFAULT NULL COMMENT 'Appraiser',
  `YUG40H` varchar(20) DEFAULT NULL COMMENT 'Identification Department',
  `YUG40I` datetime DEFAULT NULL COMMENT 'Identification time',
  `YUF00H` varchar(20) DEFAULT NULL COMMENT 'Storage period',
  PRIMARY KEY (`YUG400`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Identification batch table';

-- ----------------------------
-- Table structure for ug42
-- ----------------------------
DROP TABLE IF EXISTS `ug42`;
CREATE TABLE `ug42` (
  `YUG420` varchar(20) NOT NULL COMMENT 'Identification details serial number',
  `YUG400` varchar(20) DEFAULT NULL COMMENT 'Identification record ID',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUF002` varchar(25) DEFAULT NULL COMMENT 'File object serial number',
  `YUG422` varchar(6) DEFAULT NULL COMMENT 'Qualification status: 0 not identified 1 identified not reviewed 2 identified reviewed 3 completed (destroyed or postponed)',
  `YUG421` varchar(6) DEFAULT NULL COMMENT 'Identification results: 0 delay, 1 destruction',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUG423` varchar(100) DEFAULT NULL COMMENT 'Expert opinion',
  `YUG424` datetime DEFAULT NULL COMMENT 'Original storage end date',
  PRIMARY KEY (`YUG420`),
  KEY `INDEX_UG42_YUF002` (`YUF002`),
  KEY `INDEX_UG42_YUG400` (`YUG400`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Appraisal details';

-- ----------------------------
-- Table structure for ug43
-- ----------------------------
DROP TABLE IF EXISTS `ug43`;
CREATE TABLE `ug43` (
  `YUG430` varchar(20) NOT NULL COMMENT 'Serial number of shelf life adjustment',
  `YUG400` varchar(20) DEFAULT NULL COMMENT 'Identification batch serial number',
  `YUG420` varchar(25) DEFAULT NULL COMMENT 'Serial number of identification list',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUF002` varchar(20) DEFAULT NULL COMMENT 'File object ID',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG436` varchar(6) DEFAULT NULL COMMENT 'Shelf life',
  `YUG431` varchar(6) DEFAULT NULL COMMENT 'Has it been implemented',
  `YUG423` varchar(100) DEFAULT NULL COMMENT 'Expert opinion',
  `AAE013` text COMMENT 'remarks',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUG430`),
  KEY `INDX_UG43_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Record of retention period adjustment';

-- ----------------------------
-- Table structure for ug44
-- ----------------------------
DROP TABLE IF EXISTS `ug44`;
CREATE TABLE `ug44` (
  `YUG440` varchar(10) NOT NULL COMMENT 'Member ID',
  `YUG450` varchar(10) DEFAULT NULL COMMENT 'Membership identification group ID',
  `YUG441` varchar(60) DEFAULT NULL COMMENT 'full name',
  `YUG442` varchar(6) DEFAULT NULL COMMENT 'Gender',
  `YUG443` varchar(10) DEFAULT NULL COMMENT 'Direct organizations',
  `YUG444` varchar(20) DEFAULT NULL COMMENT 'Telephone',
  PRIMARY KEY (`YUG440`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Identification team member information sheet';

-- ----------------------------
-- Table structure for ug45
-- ----------------------------
DROP TABLE IF EXISTS `ug45`;
CREATE TABLE `ug45` (
  `YUG450` varchar(20) NOT NULL COMMENT 'Identification team ID',
  `YUG451` varchar(60) DEFAULT NULL COMMENT 'Name of identification team',
  `YUE001` varchar(10) DEFAULT NULL COMMENT 'Archives category',
  `YUB001` varchar(10) DEFAULT NULL COMMENT 'The whole family',
  PRIMARY KEY (`YUG450`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Identification team information sheet';

-- ----------------------------
-- Table structure for ug46
-- ----------------------------
DROP TABLE IF EXISTS `ug46`;
CREATE TABLE `ug46` (
  `YUG460` varchar(20) NOT NULL COMMENT 'Serial number of destruction information',
  `YUG420` varchar(20) DEFAULT NULL COMMENT 'Identification details ID',
  `YUG400` varchar(20) DEFAULT NULL COMMENT 'Identification batch serial number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUF002` varchar(20) DEFAULT NULL COMMENT 'File object ID',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG461` varchar(6) DEFAULT NULL COMMENT 'Has it been implemented',
  `YUG423` varchar(100) DEFAULT NULL COMMENT 'Expert opinion',
  `AAE013` text COMMENT 'remarks',
  `YUG466` varchar(60) DEFAULT NULL COMMENT 'Reasons for destruction',
  `YUG467` datetime DEFAULT NULL COMMENT 'Destruction time',
  `YUG468` varchar(60) DEFAULT NULL COMMENT 'Destruction site',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUG460`),
  KEY `INDX_UG64_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Destruction information record form';

-- ----------------------------
-- Table structure for ug50
-- ----------------------------
DROP TABLE IF EXISTS `ug50`;
CREATE TABLE `ug50` (
  `YUG500` varchar(20) NOT NULL COMMENT 'Serial number of entity borrowing details',
  `YUG520` varchar(20) DEFAULT NULL COMMENT 'Serial number of borrowing information',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File classification (2-file, 3-document, 4-Box, 5-box)',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'bar code',
  `YUG502` varchar(6) DEFAULT NULL COMMENT 'Return or not (0 - not returned, 1 - returned)',
  `YUG503` datetime DEFAULT NULL COMMENT 'Actual return date',
  `YUG504` varchar(6) DEFAULT NULL COMMENT 'Delay sign',
  `YUG505` varchar(6) DEFAULT NULL COMMENT 'Damage identification',
  `YUG506` varchar(6) DEFAULT NULL COMMENT 'Missing identity',
  `YUG507` decimal(6,0) DEFAULT NULL COMMENT 'Delay times',
  `YUG508` varchar(6) DEFAULT NULL COMMENT 'Reminder mark',
  `YUG509` decimal(6,0) DEFAULT NULL COMMENT 'Recall times',
  `YUG527` datetime DEFAULT NULL COMMENT 'Due date',
  `YUB001` varchar(50) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUG500`),
  KEY `INDX_UG50_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='List of entity borrowing';

-- ----------------------------
-- Table structure for ug52
-- ----------------------------
DROP TABLE IF EXISTS `ug52`;
CREATE TABLE `ug52` (
  `YUG520` varchar(15) NOT NULL COMMENT 'Serial number of borrowing information',
  `YUG521` varchar(6) DEFAULT NULL COMMENT 'Audit identification (0-not approved, 1-approved, 2-failed)',
  `YUG522` varchar(6) DEFAULT NULL COMMENT 'Lending identification',
  `YUG526` varchar(6) DEFAULT NULL COMMENT 'Return identification (0 - not returned, 1 - returned, 2 - partially returned)',
  `YUG523` varchar(20) DEFAULT NULL COMMENT 'Reviewer',
  `YUG524` varchar(20) DEFAULT NULL COMMENT 'Audit organization',
  `YUG525` datetime DEFAULT NULL COMMENT 'Audit time',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'Name of applicant',
  `AAC002` varchar(20) DEFAULT NULL COMMENT 'ID card number of applicant',
  `AAB004` text COMMENT 'Name of applicant',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Applicant''s unit number',
  `YUG52C` varchar(50) DEFAULT NULL COMMENT 'Reasons for application',
  `YUG527` datetime DEFAULT NULL COMMENT 'Due date',
  `YUG528` decimal(6,0) DEFAULT NULL COMMENT 'Number of borrowed documents',
  `YUG529` decimal(6,0) DEFAULT NULL COMMENT 'Number of returned documents',
  `YUG52A` varchar(6) DEFAULT NULL COMMENT 'Borrowing type (0-individual, 1-unit)',
  `YUG52B` varchar(15) DEFAULT NULL COMMENT 'contact number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'Archives object',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUG520`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Entity borrowing batch table';

-- ----------------------------
-- Table structure for ug53
-- ----------------------------
DROP TABLE IF EXISTS `ug53`;
CREATE TABLE `ug53` (
  `YUG530` varchar(20) NOT NULL COMMENT 'Serial number of recall information',
  `YUG520` varchar(50) DEFAULT NULL COMMENT 'Serial number of borrowing batch',
  `YUG532` varchar(20) DEFAULT NULL COMMENT 'Method of recall',
  `YUG533` varchar(20) DEFAULT NULL COMMENT 'Recall',
  `YUG534` datetime DEFAULT NULL COMMENT 'Recall date',
  `YUG535` text COMMENT 'Recall',
  `YUG536` datetime DEFAULT NULL COMMENT 'Expected return date',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUG530`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Reminder information sheet';

-- ----------------------------
-- Table structure for ug54
-- ----------------------------
DROP TABLE IF EXISTS `ug54`;
CREATE TABLE `ug54` (
  `YUG540` varchar(20) NOT NULL COMMENT 'Borrowing extension serial number',
  `YUG520` varchar(20) DEFAULT NULL COMMENT 'Serial number of borrowing batch',
  `YUG542` datetime DEFAULT NULL COMMENT 'Original return time',
  `YUG543` datetime DEFAULT NULL COMMENT 'Return time',
  `YUG544` varchar(100) DEFAULT NULL COMMENT 'Reasons for delay',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUG540`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Borrowing delay information form';

-- ----------------------------
-- Table structure for ug55
-- ----------------------------
DROP TABLE IF EXISTS `ug55`;
CREATE TABLE `ug55` (
  `YUG550` varchar(20) NOT NULL COMMENT 'Serial number of entity borrowing return',
  `YUG500` varchar(20) DEFAULT NULL COMMENT 'Serial number of entity borrowing details',
  `YUG551` datetime DEFAULT NULL COMMENT 'Return date',
  `YUG520` varchar(50) DEFAULT NULL COMMENT 'Serial number of entity borrowing',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUE001` varchar(10) DEFAULT NULL COMMENT 'Archives category',
  PRIMARY KEY (`YUG550`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Entity borrowing return form';

-- ----------------------------
-- Table structure for ug56
-- ----------------------------
DROP TABLE IF EXISTS `ug56`;
CREATE TABLE `ug56` (
  `YUE001` varchar(20) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(20) DEFAULT NULL COMMENT 'File type',
  `YUG560` varchar(20) NOT NULL COMMENT 'Serial number',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'bar code',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling unit',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUE002` varchar(20) DEFAULT NULL COMMENT 'File category',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Business link number',
  PRIMARY KEY (`YUG560`),
  KEY `INDX_UG56_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File image viewing record';

-- ----------------------------
-- Table structure for ug57
-- ----------------------------
DROP TABLE IF EXISTS `ug57`;
CREATE TABLE `ug57` (
  `YUG570` varchar(25) NOT NULL COMMENT 'Serial number record',
  `YUG560` varchar(25) NOT NULL COMMENT 'Serial number of image viewing',
  `YUF050` varchar(20) DEFAULT NULL COMMENT 'Image serial number',
  `YUG571` decimal(6,0) DEFAULT NULL COMMENT 'Video viewing times',
  `YUE030` varchar(50) DEFAULT NULL COMMENT 'Business material serial number',
  PRIMARY KEY (`YUG570`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='List of image materials';

-- ----------------------------
-- Table structure for ug58
-- ----------------------------
DROP TABLE IF EXISTS `ug58`;
CREATE TABLE `ug58` (
  `YUG580` varchar(15) NOT NULL COMMENT 'Serial number',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'User''s name',
  `AAC002` varchar(20) DEFAULT NULL COMMENT 'ID card number of user',
  `AAB004` varchar(20) DEFAULT NULL COMMENT 'Name of employer',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'User unit number',
  `YUG581` varchar(6) DEFAULT NULL COMMENT 'Usage (0: Reading 1: printing)',
  `YUG582` text COMMENT 'Purpose of utilization',
  `YUG583` text COMMENT 'Opinions of the person in charge of the safekeeping unit',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUE001` varchar(10) DEFAULT NULL COMMENT 'Archives category',
  PRIMARY KEY (`YUG580`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Image access registration form';

-- ----------------------------
-- Table structure for ug59
-- ----------------------------
DROP TABLE IF EXISTS `ug59`;
CREATE TABLE `ug59` (
  `YUG590` varchar(15) NOT NULL,
  `YUG580` varchar(15) NOT NULL,
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'Document bar code',
  `YUF00P` decimal(4,0) DEFAULT NULL COMMENT 'Page Scanned',
  PRIMARY KEY (`YUG590`),
  KEY `INDX_UG59_YUF003` (`YUF003`),
  KEY `FK_UG58` (`YUG580`),
  CONSTRAINT `FK_UG58` FOREIGN KEY (`YUG580`) REFERENCES `ug58` (`YUG580`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Image access list';

-- ----------------------------
-- Table structure for ug60
-- ----------------------------
DROP TABLE IF EXISTS `ug60`;
CREATE TABLE `ug60` (
  `YUG600` varchar(20) NOT NULL COMMENT 'ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `AAE017` varchar(200) DEFAULT NULL COMMENT 'Agency',
  `AAE011` varchar(200) DEFAULT NULL COMMENT 'Agent',
  `YUG601` decimal(6,0) DEFAULT NULL COMMENT 'Transfer quantity',
  `YUG602` decimal(6,0) DEFAULT NULL COMMENT 'Quantity received',
  `YUG603` decimal(6,0) DEFAULT NULL COMMENT 'Quantity returned',
  `YUG604` varchar(200) DEFAULT NULL COMMENT 'Reasons for transfer',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUG605` varchar(200) DEFAULT NULL COMMENT 'Reasons for regression',
  `YUG606` varchar(200) DEFAULT NULL COMMENT 'Receiving unit',
  `YUG607` varchar(50) DEFAULT NULL COMMENT 'Receiver',
  `YUG608` datetime DEFAULT NULL COMMENT 'Receiving time',
  `YUG60A` varchar(6) DEFAULT NULL COMMENT 'Is it approved by supervision',
  `YUG60B` varchar(200) DEFAULT NULL COMMENT 'Reasons for refusal',
  `YUG60C` varchar(200) DEFAULT NULL COMMENT 'Supervision unit',
  `YUG60D` varchar(50) DEFAULT NULL COMMENT 'supervisor',
  `YUG60E` datetime DEFAULT NULL COMMENT 'Supervision time',
  `YUG609` varchar(6) DEFAULT NULL COMMENT 'Handover mark (0-not handed over, 1-in hand over, 2-returned, 3-received)',
  `YUB001` varchar(50) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG60O` varchar(6) DEFAULT NULL COMMENT 'Transfer type',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUB004` varchar(10) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUG60F` varchar(60) DEFAULT NULL COMMENT 'Name of receiver',
  `AAE012` varchar(60) DEFAULT NULL COMMENT 'attention line',
  PRIMARY KEY (`YUG600`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Main table of handover information';

-- ----------------------------
-- Table structure for ug61
-- ----------------------------
DROP TABLE IF EXISTS `ug61`;
CREATE TABLE `ug61` (
  `YUG610` varchar(20) NOT NULL COMMENT 'Serial number of handover details',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUF002` varchar(50) DEFAULT NULL COMMENT 'File object ID',
  `YUG600` varchar(15) DEFAULT NULL COMMENT 'Handover record ID',
  `YUG611` varchar(6) DEFAULT NULL COMMENT 'Receive or not',
  `YUG612` varchar(200) DEFAULT NULL COMMENT 'Reasons for regression',
  `YUG606` varchar(20) DEFAULT NULL COMMENT 'Receiving unit',
  `YUG607` varchar(15) DEFAULT NULL COMMENT 'Receiver',
  `YUG608` datetime DEFAULT NULL COMMENT 'Receiving time',
  `YUG613` varchar(6) DEFAULT NULL COMMENT 'Is it handled',
  `YUG609` varchar(6) DEFAULT NULL COMMENT 'Handover flag (0-not handed over, 1-in hand over, 2-returned, 3-received)',
  PRIMARY KEY (`YUG610`),
  KEY `INDX_UG61_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Handover details';

-- ----------------------------
-- Table structure for ug62
-- ----------------------------
DROP TABLE IF EXISTS `ug62`;
CREATE TABLE `ug62` (
  `YUG620` varchar(200) NOT NULL COMMENT 'Transfer record serial number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUG622` varchar(6) DEFAULT NULL COMMENT 'Transfer flag',
  `YUG623` decimal(6,0) DEFAULT NULL COMMENT 'Number of transferred files',
  `YUG624` decimal(6,0) DEFAULT NULL COMMENT 'Number of copies of documents',
  `YUG625` varchar(15) DEFAULT NULL COMMENT 'Receiver',
  `YUG626` varchar(100) DEFAULT NULL COMMENT 'receiving department',
  `YUG627` datetime DEFAULT NULL COMMENT 'Date of receipt',
  `YUG628` varchar(100) DEFAULT NULL COMMENT 'Reasons for transfer',
  `YUG629` varchar(50) DEFAULT NULL COMMENT 'Transfer order No',
  `AAE011` varchar(15) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(200) DEFAULT NULL COMMENT 'Handling unit',
  `YUG621` varchar(100) DEFAULT NULL COMMENT 'Number of copies of transferred documents',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUG620`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Transfer record';

-- ----------------------------
-- Table structure for ug63
-- ----------------------------
DROP TABLE IF EXISTS `ug63`;
CREATE TABLE `ug63` (
  `YUG630` varchar(20) NOT NULL COMMENT 'Transfer details serial number',
  `YUG620` varchar(20) DEFAULT NULL COMMENT 'Transfer batch serial number',
  `YUF002` varchar(25) DEFAULT NULL COMMENT 'Transfer object ID',
  `YUG622` varchar(6) DEFAULT NULL COMMENT 'Transfer identification',
  PRIMARY KEY (`YUG630`),
  KEY `INDX_UG63_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Transfer details';

-- ----------------------------
-- Table structure for ug64
-- ----------------------------
DROP TABLE IF EXISTS `ug64`;
CREATE TABLE `ug64` (
  `YUG640` varchar(20) NOT NULL COMMENT 'Serial number of the delivery slip',
  `YUG641` varchar(50) NOT NULL COMMENT 'Delivery order No',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT 'Total pages of batch',
  `YUG642` datetime DEFAULT NULL COMMENT 'Deadline for document completion',
  `YUG643` varchar(6) DEFAULT NULL COMMENT 'Handover mark (0 - not handed over, 1 - handed over, 2 - received, 3 - returned)',
  `YUG644` datetime DEFAULT NULL COMMENT 'Confirmation time',
  `YUG646` text COMMENT 'Reasons for regression',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUE01C` varchar(6) DEFAULT NULL COMMENT 'Is it effective',
  `YUG64B` varchar(200) DEFAULT NULL COMMENT 'remarks',
  `YUF00N` decimal(4,0) DEFAULT NULL COMMENT 'Total number of batches',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `YUG645` datetime DEFAULT NULL COMMENT 'Receiving time',
  `YUG647` varchar(50) DEFAULT NULL COMMENT 'Receiver',
  `YUG648` varchar(50) DEFAULT NULL COMMENT 'receiving department',
  `YUG649` varchar(6) DEFAULT NULL COMMENT 'Handover type',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `YUG64A` varchar(50) DEFAULT NULL COMMENT 'Name of receiver',
  `YUB004` varchar(50) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUG640`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business system material delivery order batch new';

-- ----------------------------
-- Table structure for ug65
-- ----------------------------
DROP TABLE IF EXISTS `ug65`;
CREATE TABLE `ug65` (
  `YUG650` varchar(20) NOT NULL COMMENT 'Detailed serial no. of delivery order',
  `YUG640` varchar(20) DEFAULT NULL COMMENT 'Serial number of the delivery slip',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUE013` varchar(18) DEFAULT NULL COMMENT 'File category',
  `YUF00K` decimal(6,0) DEFAULT NULL COMMENT 'the number of pages',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB003` varchar(50) DEFAULT NULL COMMENT 'Unit code',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(25) DEFAULT NULL COMMENT 'Citizenship number',
  `AAC003` varchar(100) DEFAULT NULL COMMENT 'Personal name',
  `YUE010` text COMMENT 'Business links',
  `YUG643` varchar(6) DEFAULT NULL COMMENT 'Handover mark (1 - not handed over, 2 - handed over, 3 - handed over and returned, 4 - received, 5 - returned)',
  `YUE01C` varchar(6) DEFAULT NULL COMMENT 'Is it effective',
  `YUG64B` varchar(200) DEFAULT NULL COMMENT 'remarks',
  `YUG653` datetime DEFAULT NULL COMMENT 'Receiving time',
  `YAZ001` varchar(50) DEFAULT NULL COMMENT 'Business acceptance number',
  `YUG651` varchar(50) DEFAULT NULL COMMENT 'Receiver',
  `YUG652` varchar(50) DEFAULT NULL COMMENT 'receiving department',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'Document bar code',
  `AAE002` varchar(50) DEFAULT NULL COMMENT 'Organizer',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUG650`),
  KEY `INDX_UG65_YAZ001` (`YAZ001`),
  KEY `INDX_UG65_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Handover details of business materials';

-- ----------------------------
-- Table structure for ug69
-- ----------------------------
DROP TABLE IF EXISTS `ug69`;
CREATE TABLE `ug69` (
  `YUG690` varchar(20) NOT NULL COMMENT 'Serial number of the delivery slip',
  `YUG691` varchar(50) DEFAULT NULL COMMENT 'Delivery order No',
  `YUG692` varchar(50) DEFAULT NULL COMMENT 'Name of applicant',
  `YUG693` varchar(18) DEFAULT NULL COMMENT 'Applicant ID number',
  `YUG694` varchar(50) DEFAULT NULL COMMENT 'Name of applicant',
  `YUG695` varchar(200) DEFAULT NULL COMMENT 'Name of entrusted filing unit',
  `YUG696` varchar(6) DEFAULT NULL COMMENT 'Administrative division code of entrusted filing unit',
  `YUG697` varchar(200) DEFAULT NULL COMMENT 'Name of original filing unit',
  `YUG698` varchar(20) DEFAULT NULL COMMENT 'Original filing Unit No',
  `YUG699` varchar(6) DEFAULT NULL COMMENT 'Administrative division code of original filing unit',
  `YUG69A` varchar(200) DEFAULT NULL COMMENT 'Name of transfer unit',
  `YUG69B` varchar(20) DEFAULT NULL COMMENT 'Transfer to Unit No',
  `YUG69C` varchar(6) DEFAULT NULL COMMENT 'Code of administrative division of transfer unit',
  `YUG69D` varchar(2) DEFAULT NULL COMMENT 'Filing nature (01 unit entrustment, 02 individual entrustment)',
  `YUG69E` varchar(2) DEFAULT NULL COMMENT 'Application type (01 accepted application, 02 transfer application)',
  `YUG69F` text COMMENT 'Reasons for handover',
  `YUG69G` varchar(2) DEFAULT NULL COMMENT 'Handover mark (00 not handed over, 01 handed over, 02 received, 03 returned)',
  `YUF110` varchar(20) DEFAULT NULL COMMENT 'Serial number of document main table',
  `AAC003` varchar(50) DEFAULT NULL COMMENT 'full name',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(6) DEFAULT NULL COMMENT 'nation',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `YUF00K` decimal(65,30) DEFAULT NULL COMMENT 'the number of pages',
  `YUF00J` decimal(65,30) DEFAULT NULL COMMENT 'Number of copies',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG69H` varchar(20) DEFAULT NULL COMMENT 'Receiver',
  `YUG69I` varchar(20) DEFAULT NULL COMMENT 'receiving department',
  `YUG69J` datetime DEFAULT NULL COMMENT 'Receiving time',
  `YUG69K` varchar(20) DEFAULT NULL COMMENT 'Transferor',
  `YUG69M` varchar(20) DEFAULT NULL COMMENT 'Transfer department',
  `YUG69N` datetime DEFAULT NULL COMMENT 'Handover time',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Operator Department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(2) DEFAULT NULL COMMENT 'Valid sign',
  `YUG69P` text COMMENT 'remarks',
  `YUG69Q` varchar(2) DEFAULT NULL COMMENT 'Is it in the same system',
  PRIMARY KEY (`YUG690`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Personnel file handover application form';

-- ----------------------------
-- Table structure for ug6a
-- ----------------------------
DROP TABLE IF EXISTS `ug6a`;
CREATE TABLE `ug6a` (
  `YUG6A0` varchar(20) NOT NULL COMMENT 'Audit serial number',
  `YUG691` varchar(50) DEFAULT NULL COMMENT 'Delivery order No',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUG6A1` varchar(50) DEFAULT NULL COMMENT 'Name of issuing unit',
  `YUG6A2` varchar(50) DEFAULT NULL COMMENT 'Name of receiving unit',
  `YUG6A3` text COMMENT 'Cause (code table)',
  `YUG6A4` varchar(2) DEFAULT NULL COMMENT 'Audit report type (01 file transfer letter, 02 file transfer letter reply letter, 03 transfer notice, 04 transfer notice receipt)',
  `YUG6A5` datetime DEFAULT NULL COMMENT 'Date of issue',
  `YUG6A6` datetime DEFAULT NULL COMMENT 'Date of receipt',
  `YUG6A7` varchar(20) DEFAULT NULL COMMENT 'Name of issuer',
  `YUG6A8` text COMMENT 'remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUG6A0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Personnel file handover audit form';

-- ----------------------------
-- Table structure for ug6c
-- ----------------------------
DROP TABLE IF EXISTS `ug6c`;
CREATE TABLE `ug6c` (
  `YUG6C0` varchar(20) NOT NULL COMMENT 'Transfer serial number',
  `YUG6C1` varchar(30) DEFAULT NULL COMMENT 'Transfer order No',
  `YUR001` varchar(20) DEFAULT NULL COMMENT 'Name of applicant',
  `YUR002` varchar(20) DEFAULT NULL COMMENT 'Applicant ID number',
  `YUR003` varchar(50) DEFAULT NULL COMMENT 'Name of applicant',
  `YUR004` datetime DEFAULT NULL COMMENT 'Application date',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `YUG6C5` varchar(50) DEFAULT NULL COMMENT 'Name of transfer out unit',
  `YUG6C6` varchar(50) DEFAULT NULL COMMENT 'Name of transfer in unit',
  `YUG6C7` varchar(2) DEFAULT NULL COMMENT 'Transfer type (01 transfer out application, 02 transfer in application)',
  `YUG6C8` text COMMENT 'remarks',
  `YUG6C9` varchar(2) DEFAULT NULL COMMENT 'Is it completed',
  `AAE100` varchar(2) DEFAULT NULL COMMENT 'Is it effective',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUG69Q` varchar(20) DEFAULT NULL COMMENT 'Is it in the same system',
  PRIMARY KEY (`YUG6C0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Transfer form of Party member''s organizational relationship';

-- ----------------------------
-- Table structure for uh70
-- ----------------------------
DROP TABLE IF EXISTS `uh70`;
CREATE TABLE `uh70` (
  `YUB001` varchar(6) NOT NULL COMMENT 'Quanzong number',
  `YUH701` varchar(15) NOT NULL COMMENT 'Warehouse code',
  `YUH702` varchar(100) DEFAULT NULL COMMENT 'Warehouse name',
  `YUH703` text COMMENT 'Graphic information',
  `AAE013` varchar(200) DEFAULT NULL COMMENT 'remarks',
  `AAE011` varchar(15) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUH704` varchar(6) DEFAULT NULL COMMENT 'Horizontal arrangement',
  `YUH705` varchar(6) DEFAULT NULL COMMENT 'Vertical arrangement',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  PRIMARY KEY (`YUH701`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Warehouse information';

-- ----------------------------
-- Table structure for uh71
-- ----------------------------
DROP TABLE IF EXISTS `uh71`;
CREATE TABLE `uh71` (
  `YUH710` varchar(15) NOT NULL COMMENT 'Area code',
  `YUB001` varchar(6) NOT NULL COMMENT 'Quanzong number',
  `YUH701` varchar(15) NOT NULL COMMENT 'Warehouse code',
  `YUH711` varchar(50) DEFAULT NULL COMMENT 'Area name',
  `YUH703` text COMMENT 'Graphic information',
  `AAE013` varchar(200) DEFAULT NULL COMMENT 'remarks',
  PRIMARY KEY (`YUH710`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Area information (uh70 warehouse information of superior unit)';

-- ----------------------------
-- Table structure for uh74
-- ----------------------------
DROP TABLE IF EXISTS `uh74`;
CREATE TABLE `uh74` (
  `YUH740` varchar(15) NOT NULL COMMENT 'Joint code',
  `YUH701` varchar(15) NOT NULL COMMENT 'Warehouse code',
  `YUH710` varchar(15) NOT NULL COMMENT 'Area code',
  `YUH770` varchar(15) NOT NULL COMMENT 'Group code',
  `YUH760` varchar(15) NOT NULL COMMENT 'Shelf code',
  `YUH741` varchar(6) NOT NULL COMMENT 'AB surface',
  `YUB001` varchar(20) NOT NULL COMMENT 'Quanzong number',
  `YUH742` varchar(15) DEFAULT NULL COMMENT 'Name of couplet',
  `YUH743` decimal(6,0) NOT NULL COMMENT 'Number of layers',
  `YUH703` text COMMENT 'Graphic information',
  `YUH744` decimal(6,0) NOT NULL COMMENT 'Sort number',
  `AAE013` varchar(200) DEFAULT NULL COMMENT 'remarks',
  PRIMARY KEY (`YUH740`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Joint information (uh76 information of superior units)';

-- ----------------------------
-- Table structure for uh75
-- ----------------------------
DROP TABLE IF EXISTS `uh75`;
CREATE TABLE `uh75` (
  `YUH750` varchar(15) NOT NULL COMMENT 'Bar code',
  `YUB001` varchar(6) NOT NULL COMMENT 'Quanzong number',
  `YUH701` varchar(15) NOT NULL COMMENT 'Warehouse code',
  `YUH710` varchar(15) NOT NULL COMMENT 'Area code',
  `YUH770` varchar(15) NOT NULL COMMENT 'Group code',
  `YUH760` varchar(15) NOT NULL COMMENT 'Shelf code',
  `YUH741` varchar(6) NOT NULL COMMENT 'AB surface',
  `YUH751` varchar(50) DEFAULT NULL COMMENT 'Case name',
  `YUH744` decimal(6,0) NOT NULL COMMENT 'Sort number',
  `YUH752` decimal(6,0) NOT NULL COMMENT 'File storage capacity',
  `YUH753` decimal(6,0) NOT NULL COMMENT 'Box storage capacity',
  `YUH754` varchar(6) NOT NULL COMMENT 'Storage status',
  `YUH703` text COMMENT 'Graphic information',
  `YUH740` varchar(15) NOT NULL COMMENT 'Joint code',
  `AAE013` varchar(200) DEFAULT NULL COMMENT 'remarks',
  `YUB004` varchar(10) DEFAULT NULL COMMENT 'Municipal Organization Code',
  PRIMARY KEY (`YUH750`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Grid information (uh74 joint information of superior unit)';

-- ----------------------------
-- Table structure for uh76
-- ----------------------------
DROP TABLE IF EXISTS `uh76`;
CREATE TABLE `uh76` (
  `YUH760` varchar(15) NOT NULL COMMENT 'Shelf code',
  `YUH741` varchar(6) NOT NULL COMMENT 'AB surface',
  `YUB001` varchar(20) NOT NULL COMMENT 'Quanzong number',
  `YUH701` varchar(15) NOT NULL COMMENT 'Warehouse code',
  `YUH710` varchar(15) NOT NULL COMMENT 'Area code',
  `YUH770` varchar(15) NOT NULL COMMENT 'Group code',
  `YUH742` varchar(15) DEFAULT NULL COMMENT 'Name of couplet',
  `YUH761` decimal(6,0) NOT NULL COMMENT 'Couplet number',
  `YUH703` text COMMENT 'Graphic information',
  `YUH744` decimal(6,0) NOT NULL COMMENT 'Sort number',
  `AAE013` varchar(200) DEFAULT NULL COMMENT 'remarks',
  PRIMARY KEY (`YUH760`,`YUH741`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Frame information (uh77 group information of superior unit)';

-- ----------------------------
-- Table structure for uh77
-- ----------------------------
DROP TABLE IF EXISTS `uh77`;
CREATE TABLE `uh77` (
  `YUH770` varchar(15) NOT NULL COMMENT 'Group code',
  `YUH771` varchar(6) NOT NULL COMMENT 'Group type',
  `YUB001` varchar(20) NOT NULL COMMENT 'Quanzong number',
  `YUH701` varchar(15) NOT NULL COMMENT 'Warehouse code',
  `YUH710` varchar(15) NOT NULL COMMENT 'Area code',
  `YUH772` varchar(50) DEFAULT NULL COMMENT 'Group name',
  `YUH773` varchar(100) DEFAULT NULL COMMENT 'Drawing coordinates',
  `YUH774` decimal(6,0) DEFAULT NULL COMMENT 'Drawing angle',
  `YUH775` decimal(6,0) NOT NULL COMMENT 'Number of columns',
  `YUH703` text COMMENT 'Graphic information',
  `AAE013` varchar(200) DEFAULT NULL COMMENT 'remarks',
  `YUH761` decimal(6,0) DEFAULT NULL COMMENT 'Couplet number',
  `YUH743` decimal(6,0) DEFAULT NULL COMMENT 'Number of layers',
  PRIMARY KEY (`YUH770`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Group information (uh71 area information of superior unit)';

-- ----------------------------
-- Table structure for uh78
-- ----------------------------
DROP TABLE IF EXISTS `uh78`;
CREATE TABLE `uh78` (
  `YUH701` varchar(15) DEFAULT NULL COMMENT 'Warehouse code',
  `YUH781` varchar(20) DEFAULT NULL COMMENT 'temperature',
  `YUH782` varchar(20) DEFAULT NULL COMMENT 'humidity',
  `YUH783` datetime DEFAULT NULL COMMENT 'Monitoring time',
  `AAE013` text COMMENT 'remarks',
  `AAE011` varchar(100) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Date of handling',
  `YUB001` varchar(20) DEFAULT NULL COMMENT 'Quanzong number',
  `YUH78A` varchar(6) DEFAULT NULL COMMENT 'air conditioner',
  `YUH78B` varchar(6) DEFAULT NULL COMMENT 'dehumidifier',
  `YUH78C` varchar(6) DEFAULT NULL COMMENT 'humidifier',
  `YUH78D` varchar(6) DEFAULT NULL COMMENT 'other'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uh79
-- ----------------------------
DROP TABLE IF EXISTS `uh79`;
CREATE TABLE `uh79` (
  `YUH790` varchar(20) NOT NULL COMMENT 'Serial number of delivery record',
  `YUF002` varchar(50) DEFAULT NULL COMMENT 'File object ID',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File object type',
  `YUH793` varchar(20) DEFAULT NULL COMMENT 'Delivery type',
  `AAE036` datetime DEFAULT NULL COMMENT 'Delivery time',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `YUH794` text COMMENT 'Reason for delivery',
  `YAE116` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  PRIMARY KEY (`YUH790`),
  KEY `INDX_UH79_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Storage information record';

-- ----------------------------
-- Table structure for uh7b
-- ----------------------------
DROP TABLE IF EXISTS `uh7b`;
CREATE TABLE `uh7b` (
  `YUH7B0` varchar(20) NOT NULL COMMENT 'Serial number of transfer registration',
  `YUF011` varchar(25) DEFAULT NULL COMMENT 'bar code',
  `YUH7B5` varchar(20) DEFAULT NULL COMMENT 'Original bar code',
  `YUH7B6` decimal(65,30) DEFAULT NULL COMMENT 'Original cell number',
  `YUH7B7` varchar(20) DEFAULT NULL COMMENT 'Current bar code',
  `YUH7B8` decimal(65,30) DEFAULT NULL COMMENT 'Serial number in current format',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YAE116` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  PRIMARY KEY (`YUH7B0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Transfer registration form';

-- ----------------------------
-- Table structure for uh7g
-- ----------------------------
DROP TABLE IF EXISTS `uh7g`;
CREATE TABLE `uh7g` (
  `YUH7G0` varchar(20) NOT NULL COMMENT 'Serial number of file statistical table',
  `YUH7G1` varchar(20) DEFAULT NULL COMMENT 'Number of papers at the end of last year',
  `YUH7G2` varchar(20) DEFAULT NULL COMMENT 'Number of pieces at the end of last period',
  `YUH7G3` varchar(20) DEFAULT NULL COMMENT 'Last page of last issue',
  `YUH7G4` varchar(20) DEFAULT NULL COMMENT 'Number of new volumes in this period',
  `YUH7G5` varchar(20) DEFAULT NULL COMMENT 'Number of new units in the current period',
  `YUH7G6` varchar(20) DEFAULT NULL COMMENT 'New pages in this period',
  `YUH7G7` varchar(20) DEFAULT NULL COMMENT 'Volume reduction in this period',
  `YUH7G8` varchar(20) DEFAULT NULL COMMENT 'Number of units reduced in the current period',
  `YUH7G9` varchar(20) DEFAULT NULL COMMENT 'Decrease of pages in this period',
  `YUH7GA` varchar(20) DEFAULT NULL COMMENT 'Number of papers at the end of the period',
  `YUH7GB` varchar(20) DEFAULT NULL COMMENT 'Number of units at the end of the current period',
  `YUH7GC` varchar(20) DEFAULT NULL COMMENT 'Number of pages at the end of the period',
  `YUF00E` varchar(20) DEFAULT NULL COMMENT 'year',
  `YUF00F` varchar(20) DEFAULT NULL COMMENT 'month',
  `YUH701` varchar(20) DEFAULT NULL COMMENT 'Warehouse code',
  `YUE002` varchar(20) DEFAULT NULL COMMENT 'File category',
  `YUF00H` varchar(20) DEFAULT NULL COMMENT 'Storage period',
  `YUB001` varchar(15) DEFAULT NULL COMMENT 'Sub center',
  PRIMARY KEY (`YUH7G0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uh7h
-- ----------------------------
DROP TABLE IF EXISTS `uh7h`;
CREATE TABLE `uh7h` (
  `YUE002` varchar(10) DEFAULT NULL COMMENT 'File category',
  `YUF00H` varchar(10) DEFAULT NULL COMMENT 'Storage period',
  `YUH7H0` varchar(10) DEFAULT NULL COMMENT 'Order of storage period',
  `YUH7H1` varchar(10) DEFAULT NULL COMMENT 'Serial number'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Monthly (annual) statistics of Social Insurance Archives';

-- ----------------------------
-- Table structure for uh7j
-- ----------------------------
DROP TABLE IF EXISTS `uh7j`;
CREATE TABLE `uh7j` (
  `YUH7J0` varchar(10) DEFAULT NULL COMMENT 'File category order',
  `YUE002` varchar(10) DEFAULT NULL COMMENT 'File category'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File category order';

-- ----------------------------
-- Table structure for uh7k
-- ----------------------------
DROP TABLE IF EXISTS `uh7k`;
CREATE TABLE `uh7k` (
  `YUH7K0` varchar(10) NOT NULL COMMENT 'Equipment number',
  `YUH7K1` varchar(60) DEFAULT NULL COMMENT 'Equipment name',
  `YUH7K2` varchar(10) DEFAULT NULL COMMENT 'Equipment type',
  `YUH7K3` varchar(60) DEFAULT NULL COMMENT 'Equipment type name',
  `YUH7K4` datetime DEFAULT NULL COMMENT 'Date of equipment operation',
  PRIMARY KEY (`YUH7K0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Warehouse equipment information table';

-- ----------------------------
-- Table structure for uh7l
-- ----------------------------
DROP TABLE IF EXISTS `uh7l`;
CREATE TABLE `uh7l` (
  `YUH701` varchar(10) DEFAULT NULL COMMENT 'Warehouse number',
  `YUH7K0` varchar(40) DEFAULT NULL COMMENT 'Equipment number',
  `YUH7K1` varchar(60) DEFAULT NULL COMMENT 'Equipment name',
  `YUH7K2` varchar(10) DEFAULT NULL COMMENT 'Equipment type',
  `YUH7K4` datetime DEFAULT NULL COMMENT 'Date of equipment operation',
  `YUH7L0` varchar(20) NOT NULL COMMENT 'Warehouse equipment primary key',
  PRIMARY KEY (`YUH7L0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Warehouse equipment associated information table';

-- ----------------------------
-- Table structure for uh7m
-- ----------------------------
DROP TABLE IF EXISTS `uh7m`;
CREATE TABLE `uh7m` (
  `YUH7M0` varchar(20) NOT NULL COMMENT 'Monitoring information table primary key',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT 'area number',
  `YUB004` varchar(20) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUH701` varchar(15) DEFAULT NULL COMMENT 'Warehouse number',
  `YUH7M1` datetime DEFAULT NULL COMMENT 'Monitoring start time',
  `YUH7M2` datetime DEFAULT NULL COMMENT 'Monitoring end time',
  `YUH7M3` varchar(40) DEFAULT NULL COMMENT 'Monitoring staff',
  `YUH7M4` varchar(6) DEFAULT NULL COMMENT 'Is rodenticide put in',
  `YUH7M5` varchar(6) DEFAULT NULL COMMENT 'Whether anti insect and mould drugs are put in',
  `YUH7M6` varchar(6) DEFAULT NULL COMMENT 'Is the archive clean',
  `YUH7M7` varchar(6) DEFAULT NULL COMMENT 'Ventilation',
  `YUH7M8` varchar(20) DEFAULT NULL COMMENT 'temperature',
  `YUH7M9` varchar(6) DEFAULT NULL COMMENT 'Whether to put in moisture absorbent',
  `YUH7MA` varchar(20) DEFAULT NULL COMMENT 'humidity',
  `YUH7MB` varchar(10) DEFAULT NULL COMMENT 'Monitoring security level',
  `YUH7MC` varchar(6) DEFAULT NULL COMMENT 'Is equipment inspection conducted',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Operator ID',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE013` text COMMENT 'Remarks',
  PRIMARY KEY (`YUH7M0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Nine prevention monitoring information table';

-- ----------------------------
-- Table structure for uh7n
-- ----------------------------
DROP TABLE IF EXISTS `uh7n`;
CREATE TABLE `uh7n` (
  `YUH7N0` varchar(10) NOT NULL COMMENT 'Monitoring detail list primary key',
  `YUH7M0` varchar(10) DEFAULT NULL COMMENT 'Monitoring information table primary key',
  `YUH7K0` varchar(10) DEFAULT NULL COMMENT 'Equipment number',
  `YUH7N1` varchar(6) DEFAULT NULL COMMENT 'Equipment status',
  `YUH7N2` varchar(200) DEFAULT NULL COMMENT 'remarks',
  PRIMARY KEY (`YUH7N0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Nine prevention monitoring list';

-- ----------------------------
-- Table structure for uj00
-- ----------------------------
DROP TABLE IF EXISTS `uj00`;
CREATE TABLE `uj00` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUJ041` decimal(5,0) DEFAULT NULL COMMENT 'Part number',
  `YUJ042` varchar(100) DEFAULT NULL COMMENT 'Document number',
  `YUJ043` text COMMENT 'title',
  `YUJ044` varchar(200) DEFAULT NULL COMMENT 'Responsible person',
  `YUJ045` text COMMENT 'Subject words',
  `YUJ046` varchar(6) DEFAULT NULL COMMENT 'Information type',
  `YUJ047` varchar(6) DEFAULT NULL COMMENT 'Carrier type',
  `YUJ048` varchar(200) DEFAULT NULL COMMENT 'Carrier unit',
  `YUJ049` decimal(6,0) DEFAULT NULL COMMENT 'Number of carriers',
  `YUJ04A` varchar(6) DEFAULT NULL COMMENT 'Carrier specification',
  `YUJ04B` varchar(100) DEFAULT NULL COMMENT 'Electronic file number',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUE002` varchar(200) DEFAULT NULL COMMENT 'File category',
  `YUJ04D` varchar(200) DEFAULT NULL COMMENT 'manuscript',
  `YUJ04E` varchar(200) DEFAULT NULL COMMENT 'enclosure',
  `YUJ04F` varchar(200) DEFAULT NULL COMMENT 'Metadata information',
  `YUJ04G` varchar(50) DEFAULT NULL COMMENT 'Box (room) No',
  `YUJ04H` varchar(50) DEFAULT NULL COMMENT 'Box (Hall) No',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Complete file';

-- ----------------------------
-- Table structure for uj01
-- ----------------------------
DROP TABLE IF EXISTS `uj01`;
CREATE TABLE `uj01` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUE013` varchar(10) DEFAULT NULL COMMENT 'File category number',
  `YUF00Q` decimal(20,0) DEFAULT NULL COMMENT 'File sequence number',
  `YUJ013` text COMMENT 'Volume name',
  `YUJ014` text COMMENT 'Table name',
  `YUJ015` text COMMENT 'title',
  `YUJ016` text COMMENT 'Subject words',
  `YUJ017` varchar(100) DEFAULT NULL COMMENT 'Registration number',
  `YUJ018` varchar(50) DEFAULT NULL COMMENT 'Document number',
  `YUJ01B` datetime DEFAULT NULL COMMENT 'Date of registration',
  `YUJ01E` varchar(50) DEFAULT NULL COMMENT 'department',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB003` varchar(20) DEFAULT NULL COMMENT 'Unit code',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(30) DEFAULT NULL COMMENT 'ID card No.',
  `AAC003` text COMMENT 'full name',
  `YUJ01F` varchar(20) DEFAULT NULL COMMENT 'Hospital code',
  `YUJ01G` varchar(100) DEFAULT NULL COMMENT 'Name of hospital',
  `YUJ01H` datetime DEFAULT NULL COMMENT 'Time of expenses',
  `YUJ01I` datetime DEFAULT NULL COMMENT 'Submission time',
  `YUJ01J` varchar(50) DEFAULT NULL COMMENT 'Transfer to financial summary batch No',
  `YUJ01N` varchar(20) DEFAULT NULL COMMENT 'collector',
  `YUJ01O` datetime DEFAULT NULL COMMENT 'Collection time',
  `AAC021` varchar(50) DEFAULT NULL COMMENT 'Unemployment certificate No',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Social security business archives';

-- ----------------------------
-- Table structure for uj02
-- ----------------------------
DROP TABLE IF EXISTS `uj02`;
CREATE TABLE `uj02` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUJ021` varchar(21) DEFAULT NULL COMMENT 'Business bar code',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUE012` text COMMENT 'File category name',
  `YUF00E` decimal(4,0) DEFAULT NULL COMMENT 'year',
  `YUF00F` decimal(2,0) DEFAULT NULL COMMENT 'month',
  `YUF00H` varchar(6) DEFAULT NULL COMMENT 'Storage period',
  `YUF00I` varchar(6) DEFAULT NULL COMMENT 'Classification',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC003` varchar(200) DEFAULT NULL COMMENT 'full name',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'Identification Number',
  `YUJ023` decimal(16,2) DEFAULT NULL COMMENT 'amount of money',
  `YUJ01K` datetime DEFAULT NULL COMMENT 'Date of document',
  `YUJ01L` decimal(65,30) DEFAULT NULL COMMENT 'Number of copies of documents',
  `YUJ01M` decimal(65,30) DEFAULT NULL COMMENT 'Number of document pages',
  `YUJ01P` datetime DEFAULT NULL COMMENT 'Storage start time',
  `YUJ01R` datetime DEFAULT NULL COMMENT 'Storage end time',
  `YUF00P` decimal(65,30) DEFAULT NULL COMMENT 'Page Scanned',
  `AAZ002` varchar(50) DEFAULT NULL COMMENT 'Business number',
  `YUJ024` varchar(200) DEFAULT NULL COMMENT 'Bank name',
  `YUJ025` varchar(200) DEFAULT NULL COMMENT 'Property name',
  `YUJ026` varchar(20) DEFAULT NULL COMMENT 'Batch number',
  `YUJ027` varchar(20) DEFAULT NULL COMMENT 'Part number',
  `YUJ028` text COMMENT 'title',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Business Archives of provident fund';

-- ----------------------------
-- Table structure for uj03
-- ----------------------------
DROP TABLE IF EXISTS `uj03`;
CREATE TABLE `uj03` (
  `YUJ0X0` varchar(50) NOT NULL COMMENT 'File basic information primary key',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'Citizenship number',
  `AAC003` varchar(50) DEFAULT NULL COMMENT 'full name',
  `AAC006` datetime DEFAULT NULL COMMENT 'date of birth',
  `AA0131` varchar(6) DEFAULT NULL COMMENT 'Types of personnel (civil servants, personnel of public institutions)',
  `AA0132` varchar(6) DEFAULT NULL COMMENT 'Personnel status (non death, death)',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB004` varchar(200) DEFAULT NULL COMMENT 'Unit name',
  `YUJ031` varchar(50) DEFAULT NULL COMMENT 'File number',
  `YUJ032` varchar(50) DEFAULT NULL COMMENT 'File bag barcode',
  `YUJ033` varchar(200) DEFAULT NULL COMMENT 'Archives keeping unit',
  `YUJ034` varchar(50) DEFAULT NULL COMMENT 'Storage number',
  `YUJ035` varchar(50) DEFAULT NULL COMMENT 'Storage column number',
  `YUJ036` varchar(50) DEFAULT NULL COMMENT 'Section number storage',
  `YUJ037` varchar(50) DEFAULT NULL COMMENT 'Box number',
  `YUJ038` varchar(6) DEFAULT NULL COMMENT 'Types of personnel files (original; duplicate; original and duplicate)',
  `YUJ039` decimal(6,0) DEFAULT NULL COMMENT 'Material type quantity',
  `YUF0C2` decimal(6,0) DEFAULT NULL COMMENT 'Copies of materials',
  `YUF0C3` decimal(6,0) DEFAULT NULL COMMENT 'Material pages',
  `YUJ03P` varchar(200) DEFAULT NULL COMMENT 'remarks',
  `YUJ03O` datetime DEFAULT NULL COMMENT 'Stop time',
  `YUJ03Q` varchar(100) DEFAULT NULL COMMENT 'Reasons for stopping',
  `YUJ03R` datetime DEFAULT NULL COMMENT 'Retirement time',
  `YUJ03S` datetime DEFAULT NULL COMMENT 'Entry time',
  `YUJ03T` varchar(6) DEFAULT NULL COMMENT 'Personnel file category',
  `AAC004` varchar(6) DEFAULT NULL COMMENT 'Gender',
  `YUJ03A` varchar(50) DEFAULT NULL COMMENT 'Historical file number',
  `YUJ03B` datetime DEFAULT NULL COMMENT 'Graduation time',
  `YUJ03C` varchar(50) DEFAULT NULL COMMENT 'File identity (0, cadre; 1, worker; 2, non general; 3, other;)',
  `YUJ03D` varchar(50) DEFAULT NULL COMMENT 'Graduate school or file source',
  `YUJ03E` datetime DEFAULT NULL COMMENT 'Working hours',
  `YUJ03F` varchar(20) DEFAULT NULL COMMENT 'Educational background (0, none; 1, technical secondary school; 2, junior college; 3, undergraduate; 4, master; 5, doctor;)',
  `YUJ03G` datetime DEFAULT NULL COMMENT 'Arrival time',
  `YUJ03H` varchar(50) DEFAULT NULL COMMENT 'major',
  `YUJ03I` decimal(5,0) DEFAULT NULL COMMENT 'length of schooling',
  `YUJ03J` varchar(20) DEFAULT NULL COMMENT 'Type of Education (0, none; 1, unified enrollment; 2, full-time adult; 3, night University; 4, correspondence; 5, network education; 6, self-study examination; 7, commissioned training orientation; 8, adult education; 9, vocational college; 10, small plan; 11, studying abroad;)',
  `YUJ03K` varchar(20) DEFAULT NULL COMMENT 'Whether there are party materials (0, no; 1, yes;)',
  `YUJ03L` varchar(50) DEFAULT NULL COMMENT 'Warehouse location',
  `YUJ03M` varchar(6) DEFAULT NULL COMMENT 'File status (with code value)',
  `YUJ03N` varchar(6) DEFAULT NULL COMMENT 'Borrowing status (tentatively 0, not borrowed; 1, borrowed)',
  `AAC005` varchar(50) DEFAULT NULL COMMENT 'nation',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Personnel files';

-- ----------------------------
-- Table structure for uj04
-- ----------------------------
DROP TABLE IF EXISTS `uj04`;
CREATE TABLE `uj04` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUJ041` decimal(5,0) DEFAULT NULL COMMENT 'Part number',
  `YUJ042` varchar(100) DEFAULT NULL COMMENT 'Document number',
  `YUJ043` text COMMENT 'title',
  `YUJ044` varchar(200) DEFAULT NULL COMMENT 'Responsible person',
  `YUJ045` text COMMENT 'Subject words',
  `YUJ046` varchar(6) DEFAULT NULL COMMENT 'Information type',
  `YUJ047` varchar(6) DEFAULT NULL COMMENT 'Carrier type',
  `YUJ048` varchar(200) DEFAULT NULL COMMENT 'Carrier unit',
  `YUJ049` decimal(6,0) DEFAULT NULL COMMENT 'Number of carriers',
  `YUJ04A` varchar(6) DEFAULT NULL COMMENT 'Carrier specification',
  `YUJ04B` varchar(100) DEFAULT NULL COMMENT 'Electronic file number',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUJ04C` varchar(200) DEFAULT NULL COMMENT 'mechanism',
  `YUJ04D` varchar(200) DEFAULT NULL COMMENT 'manuscript',
  `YUJ04E` varchar(200) DEFAULT NULL COMMENT 'enclosure',
  `YUJ04F` varchar(200) DEFAULT NULL COMMENT 'Metadata information',
  `YUJ04G` varchar(50) DEFAULT NULL COMMENT 'Box (room) No',
  `YUJ04H` varchar(50) DEFAULT NULL COMMENT 'Box (Hall) No',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='administrative archives';

-- ----------------------------
-- Table structure for uj05
-- ----------------------------
DROP TABLE IF EXISTS `uj05`;
CREATE TABLE `uj05` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUJ051` varchar(20) DEFAULT NULL COMMENT 'A / C set code',
  `YUJ052` varchar(100) DEFAULT NULL COMMENT 'A / C set name',
  `YUJ053` varchar(20) DEFAULT NULL COMMENT 'Account code',
  `YUJ054` varchar(100) DEFAULT NULL COMMENT 'Account name',
  `YUJ055` varchar(50) DEFAULT NULL COMMENT 'Voucher start number',
  `YUJ056` varchar(50) DEFAULT NULL COMMENT 'Voucher end No',
  `YUJ057` decimal(18,2) DEFAULT NULL COMMENT 'amount of money',
  `YUJ058` text COMMENT 'abstract',
  `YUJ059` text COMMENT 'Accounting notes',
  `YUJ05A` varchar(50) DEFAULT NULL COMMENT 'Voucher prefix',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Fund accounting files';

-- ----------------------------
-- Table structure for uj08
-- ----------------------------
DROP TABLE IF EXISTS `uj08`;
CREATE TABLE `uj08` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUE010` varchar(20) DEFAULT NULL COMMENT 'Document category number',
  `YUJ081` varchar(20) DEFAULT NULL COMMENT 'Physical name',
  `YUJ082` varchar(20) DEFAULT NULL COMMENT 'Physical number',
  `YUJ083` varchar(100) DEFAULT NULL COMMENT 'remarks',
  `YUJ084` varchar(25) DEFAULT NULL COMMENT 'mechanism',
  `YUJ085` decimal(10,0) DEFAULT NULL COMMENT 'Part number',
  `YUJ044` varchar(25) DEFAULT NULL COMMENT 'Responsible person',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Physical Archives';

-- ----------------------------
-- Table structure for uj14
-- ----------------------------
DROP TABLE IF EXISTS `uj14`;
CREATE TABLE `uj14` (
  `YUJ0X0` varchar(20) NOT NULL COMMENT 'File basic information primary key',
  `YUE002` varchar(6) DEFAULT NULL COMMENT 'File category',
  `YUE013` varchar(10) DEFAULT NULL COMMENT 'File Catalog Number',
  `YUF00Q` decimal(20,0) DEFAULT NULL COMMENT 'File sequence number',
  `YUJ013` text COMMENT 'Volume name',
  `YUJ014` text COMMENT 'Table name',
  `YUJ015` text COMMENT 'title',
  `YUJ016` text COMMENT 'Subject words',
  `YUJ017` varchar(100) DEFAULT NULL COMMENT 'Registration number',
  `YUJ018` varchar(50) DEFAULT NULL COMMENT 'Document number',
  `YUJ01B` datetime DEFAULT NULL COMMENT 'Date of registration',
  `YUJ01E` varchar(50) DEFAULT NULL COMMENT 'department',
  `AAB001` varchar(50) DEFAULT NULL COMMENT 'Unit No',
  `AAB003` varchar(20) DEFAULT NULL COMMENT 'Unit code',
  `AAB004` text COMMENT 'Unit name',
  `AAC001` varchar(50) DEFAULT NULL COMMENT 'Personal number',
  `AAC002` varchar(30) DEFAULT NULL COMMENT 'ID card No.',
  `AAC003` text COMMENT 'full name',
  `YUJ01F` varchar(20) DEFAULT NULL COMMENT 'Hospital code',
  `YUJ01G` varchar(100) DEFAULT NULL COMMENT 'Name of hospital',
  `YUJ01H` datetime DEFAULT NULL COMMENT 'Time of expenses',
  `YUJ01I` datetime DEFAULT NULL COMMENT 'Submission time',
  `YUJ01J` varchar(50) DEFAULT NULL COMMENT 'Transfer to financial summary batch No',
  `YUJ01N` varchar(20) DEFAULT NULL COMMENT 'collector',
  `YUJ01O` datetime DEFAULT NULL COMMENT 'Collection time',
  `AAC021` varchar(50) DEFAULT NULL COMMENT 'Unemployment certificate No',
  PRIMARY KEY (`YUJ0X0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Employment file information';

-- ----------------------------
-- Table structure for ur01
-- ----------------------------
DROP TABLE IF EXISTS `ur01`;
CREATE TABLE `ur01` (
  `YUR010` varchar(20) NOT NULL COMMENT 'Adjust serial number',
  `YUF110` varchar(20) DEFAULT NULL COMMENT 'Serial number of document main table',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `YUF00J` decimal(65,30) DEFAULT NULL COMMENT 'Number of copies',
  `YUF00K` decimal(65,30) DEFAULT NULL COMMENT 'the number of pages',
  `YUF00P` decimal(4,0) DEFAULT NULL COMMENT 'Page Scanned',
  `YUF004` varchar(2) DEFAULT NULL COMMENT 'Scan status (0 not scanned, 1 being scanned, 2 scanned)',
  `AAE013` text COMMENT 'remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUR010`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Main table of personnel file adjustment log';

-- ----------------------------
-- Table structure for ur02
-- ----------------------------
DROP TABLE IF EXISTS `ur02`;
CREATE TABLE `ur02` (
  `YUR020` varchar(20) NOT NULL COMMENT 'Adjustment details serial number',
  `YUR010` varchar(20) DEFAULT NULL COMMENT 'Adjust serial number',
  `YUE030` varchar(20) DEFAULT NULL COMMENT 'Material number',
  `YUE031` text COMMENT 'Material name',
  `YUE046` decimal(65,30) DEFAULT NULL COMMENT 'Material pages',
  `YUE041` decimal(65,30) DEFAULT NULL COMMENT 'Schedule number sequence',
  `YUF04Y` varchar(50) DEFAULT NULL COMMENT 'Personal identification code',
  PRIMARY KEY (`YUR020`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Personnel file adjustment log details';

-- ----------------------------
-- Table structure for ur03
-- ----------------------------
DROP TABLE IF EXISTS `ur03`;
CREATE TABLE `ur03` (
  `YUR030` varchar(20) NOT NULL COMMENT 'Registration serial number',
  `YUF110` varchar(20) DEFAULT NULL COMMENT 'Serial number of document main table',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `YUR031` varchar(2) DEFAULT NULL COMMENT 'Conversion type (01 floating cadres, 02 cadres transferring to floating)',
  `YUR032` datetime DEFAULT NULL COMMENT 'Conversion date',
  `YUE033` text COMMENT 'remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUR030`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Registration form of archives personnel type conversion';

-- ----------------------------
-- Table structure for ur04
-- ----------------------------
DROP TABLE IF EXISTS `ur04`;
CREATE TABLE `ur04` (
  `YUR040` varchar(20) NOT NULL COMMENT 'Registration serial number',
  `YUF110` varchar(20) DEFAULT NULL COMMENT 'Serial number of document main table',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `YUR041` datetime DEFAULT NULL COMMENT 'Date of death',
  `YUR042` datetime DEFAULT NULL COMMENT 'Time of file transfer',
  `YUR043` varchar(200) DEFAULT NULL COMMENT 'Location of files after transfer',
  `YUR044` text COMMENT 'remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUR040`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Death check registration form';

-- ----------------------------
-- Table structure for ur05
-- ----------------------------
DROP TABLE IF EXISTS `ur05`;
CREATE TABLE `ur05` (
  `YUR050` varchar(20) NOT NULL COMMENT 'Registration serial number',
  `YUR001` varchar(20) DEFAULT NULL COMMENT 'Name of applicant',
  `YUR002` varchar(18) DEFAULT NULL COMMENT 'Applicant ID number',
  `YUR003` varchar(50) DEFAULT NULL COMMENT 'Name of applicant',
  `YUR004` datetime DEFAULT NULL COMMENT 'Application date',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `YUR051` varchar(2) DEFAULT NULL COMMENT 'Types of proof (01 Filing Certificate, 02 experience certificate, 03 kinship certificate, 04 no criminal record certificate, 05 others)',
  `YUR052` text COMMENT 'remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUF110` varchar(20) DEFAULT NULL COMMENT 'Serial number of document main table',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUR050`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Registration form of certification information';

-- ----------------------------
-- Table structure for ur06
-- ----------------------------
DROP TABLE IF EXISTS `ur06`;
CREATE TABLE `ur06` (
  `YUR060` varchar(20) NOT NULL COMMENT 'Serial number of political examination registration',
  `YUR001` varchar(20) DEFAULT NULL COMMENT 'Name of applicant',
  `YUR002` varchar(18) DEFAULT NULL COMMENT 'Applicant ID number',
  `YUR003` varchar(50) DEFAULT NULL COMMENT 'Name of applicant',
  `AAC003` varchar(20) DEFAULT NULL COMMENT 'full name',
  `AAC004` varchar(2) DEFAULT NULL COMMENT 'Gender',
  `AAC005` varchar(20) DEFAULT NULL COMMENT 'nation',
  `AAC002` varchar(18) DEFAULT NULL COMMENT 'ID card No.',
  `AAB004` varchar(50) DEFAULT NULL COMMENT 'Unit name',
  `AAB001` varchar(20) DEFAULT NULL COMMENT 'Unit No',
  `YUR004` datetime DEFAULT NULL COMMENT 'Date of political review',
  `YUR061` varchar(2) DEFAULT NULL COMMENT 'Types of political examination (01 joining the army, 02 hiring, 03 joining the party, 04 going abroad, 05 entering school, 06 others)',
  `YUR062` text COMMENT 'remarks',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling department',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUF110` varchar(20) DEFAULT NULL COMMENT 'Serial number of document main table',
  `YUR063` varchar(2) DEFAULT NULL COMMENT 'Is the political review passed',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  PRIMARY KEY (`YUR060`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Registration form of political examination information';

-- ----------------------------
-- Table structure for ut01
-- ----------------------------
DROP TABLE IF EXISTS `ut01`;
CREATE TABLE `ut01` (
  `YUT010` varchar(20) NOT NULL COMMENT 'Primary key',
  `YUE091` varchar(20) DEFAULT NULL COMMENT 'functional module',
  `YUE092` varchar(20) DEFAULT NULL COMMENT 'Operation object',
  `YUT011` varchar(20) DEFAULT NULL COMMENT 'Number of operations',
  `YUT012` datetime DEFAULT NULL COMMENT 'First operation time',
  `YUT013` datetime DEFAULT NULL COMMENT 'Last operation time',
  `YUE001` varchar(20) DEFAULT NULL COMMENT 'Archives category',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE012` varchar(20) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `AAE018` varchar(20) DEFAULT NULL COMMENT 'Name of agency',
  PRIMARY KEY (`YUT010`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Module button operation log';

-- ----------------------------
-- Table structure for ut02
-- ----------------------------
DROP TABLE IF EXISTS `ut02`;
CREATE TABLE `ut02` (
  `YUT020` varchar(20) NOT NULL COMMENT 'Page restore secondary table primary key',
  `YUT021` varchar(20) NOT NULL COMMENT 'Page restore object',
  `YUT022` datetime NOT NULL COMMENT 'Operation time',
  `YUT023` varchar(6) NOT NULL COMMENT 'Is it completed',
  `YUT024` varchar(6) NOT NULL COMMENT 'Business function module',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Agency',
  `YUT025` varchar(6) DEFAULT NULL COMMENT 'First reminder after editing',
  `YUT026` varchar(50) DEFAULT NULL COMMENT 'Key information',
  PRIMARY KEY (`YUT020`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Page recovery auxiliary table';

-- ----------------------------
-- Table structure for ut05
-- ----------------------------
DROP TABLE IF EXISTS `ut05`;
CREATE TABLE `ut05` (
  `YUT050` varchar(20) NOT NULL,
  `YUT051` varchar(100) DEFAULT NULL COMMENT 'title',
  `YUT052` text COMMENT 'content',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(20) DEFAULT NULL COMMENT 'Handling unit',
  `YUT053` datetime DEFAULT NULL COMMENT 'Last modification time',
  `YUT054` varchar(20) DEFAULT NULL COMMENT 'Last modified by',
  `YUT055` varchar(20) DEFAULT NULL COMMENT 'Last modified unit',
  `YAE116` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `YUT056` datetime DEFAULT NULL COMMENT 'Expiration time',
  `YUT057` varchar(6) DEFAULT NULL COMMENT 'Expiration mark',
  PRIMARY KEY (`YUT050`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File system message list';

-- ----------------------------
-- Table structure for ut07
-- ----------------------------
DROP TABLE IF EXISTS `ut07`;
CREATE TABLE `ut07` (
  `YUT070` varchar(15) NOT NULL COMMENT 'Serial number of warehousing batch',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File specification',
  `YUT071` decimal(6,0) DEFAULT NULL COMMENT 'Number of boxes',
  `YUT072` decimal(6,0) DEFAULT NULL COMMENT 'Number of volumes',
  `YUT073` decimal(6,0) DEFAULT NULL COMMENT 'number',
  `YUT074` datetime DEFAULT NULL COMMENT 'Completion time',
  `YUT075` varchar(6) DEFAULT NULL COMMENT 'Storage method: 0 storage (first warehousing), 1 return warehousing (lending return), 2 return warehousing (transfer to file management)',
  `YUT076` varchar(50) DEFAULT NULL COMMENT 'Associated primary key',
  `YUT077` varchar(6) DEFAULT NULL COMMENT 'Is it completed',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUT070`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Main information of stock in batch';

-- ----------------------------
-- Table structure for ut08
-- ----------------------------
DROP TABLE IF EXISTS `ut08`;
CREATE TABLE `ut08` (
  `YUT080` varchar(25) NOT NULL COMMENT 'Serial number of warehousing details',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File specification',
  `YUF002` varchar(50) DEFAULT NULL COMMENT 'File primary key',
  `YUF010` varchar(25) DEFAULT NULL COMMENT 'Serial number of box',
  `YUH750` varchar(25) DEFAULT NULL COMMENT 'Grid number',
  `YUT070` varchar(15) NOT NULL COMMENT 'Serial number of warehousing batch',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Handling unit',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUT081` varchar(25) DEFAULT NULL COMMENT 'Associated primary key',
  PRIMARY KEY (`YUT080`),
  KEY `INDX_UT08_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Detailed list of warehousing information';

-- ----------------------------
-- Table structure for ut10
-- ----------------------------
DROP TABLE IF EXISTS `ut10`;
CREATE TABLE `ut10` (
  `YUT100` varchar(20) NOT NULL COMMENT 'System operation log serial number',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUB004` varchar(6) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT 'area number',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'Archives object',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUF002` varchar(50) DEFAULT NULL COMMENT 'File object ID',
  `YUF003` varchar(50) DEFAULT NULL COMMENT 'File object barcode',
  `YUT101` varchar(6) DEFAULT NULL COMMENT 'System identification (interface, management system)',
  `YUT102` varchar(20) DEFAULT NULL COMMENT 'Module code value',
  `YUT103` varchar(20) DEFAULT NULL COMMENT 'Function code value',
  `YUT104` varchar(20) DEFAULT NULL COMMENT 'Opcode value',
  `YUT105` varchar(6) DEFAULT NULL COMMENT 'Object status',
  `YUT106` longblob COMMENT 'Operation results (record the field values before and after the change in XML format)',
  `YUT107` longblob COMMENT 'Pre operation information (pre operation information is recorded in XML format)',
  `YUT108` varchar(50) DEFAULT NULL COMMENT 'Operation function ID',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent No',
  `AAE012` varchar(100) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency ID',
  `AAE018` varchar(200) DEFAULT NULL COMMENT 'Name of agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid sign',
  `YUT109` varchar(50) DEFAULT NULL COMMENT 'Function operation name',
  `AAE013` text COMMENT 'Remarks',
  PRIMARY KEY (`YUT100`),
  KEY `INDX_UT10_YUF002` (`YUF002`),
  KEY `INDX_UT10_YUF003` (`YUF003`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File system operation log';

-- ----------------------------
-- Table structure for ut11
-- ----------------------------
DROP TABLE IF EXISTS `ut11`;
CREATE TABLE `ut11` (
  `YUT110` varchar(20) NOT NULL COMMENT 'Serial number',
  `YUB001` varchar(10) DEFAULT NULL COMMENT 'Quanzong number',
  `YUT111` decimal(65,30) DEFAULT NULL COMMENT 'The acceptance has not been completed',
  `YUT112` decimal(65,30) DEFAULT NULL COMMENT 'Completed but not handed over',
  `YUT113` decimal(65,30) DEFAULT NULL COMMENT 'During handover',
  `YUT114` decimal(65,30) DEFAULT NULL COMMENT 'Handover not sorted out',
  `YUT115` decimal(65,30) DEFAULT NULL COMMENT 'Sorting and not warehousing',
  `YUT116` decimal(65,30) DEFAULT NULL COMMENT 'No identification after expiration',
  `YUT117` decimal(65,30) DEFAULT NULL COMMENT 'In the process of utilization',
  `YUT118` decimal(65,30) DEFAULT NULL COMMENT 'In the process of identification',
  `AAE036` datetime DEFAULT NULL COMMENT 'Statistical date',
  PRIMARY KEY (`YUT110`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut12
-- ----------------------------
DROP TABLE IF EXISTS `ut12`;
CREATE TABLE `ut12` (
  `YUT120` varchar(50) NOT NULL COMMENT 'History table primary key',
  `YUT121` varchar(50) DEFAULT NULL COMMENT 'Search keyword name',
  `YUB001` varchar(50) DEFAULT NULL COMMENT 'Quanzong number',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `AAE011` varchar(50) DEFAULT NULL COMMENT 'Agent No',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'attention line',
  `YUB004` varchar(10) DEFAULT NULL COMMENT 'administrative division',
  `AAE017` varchar(50) DEFAULT NULL COMMENT 'Agency',
  PRIMARY KEY (`YUT120`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File search center search log table';

-- ----------------------------
-- Table structure for ut22
-- ----------------------------
DROP TABLE IF EXISTS `ut22`;
CREATE TABLE `ut22` (
  `YUT220` varchar(25) NOT NULL COMMENT 'Serial number of delivery batch',
  `YUT221` decimal(6,0) DEFAULT NULL COMMENT 'Number of boxes',
  `YUT222` decimal(6,0) DEFAULT NULL COMMENT 'Number of volumes',
  `YUT223` decimal(6,0) DEFAULT NULL COMMENT 'number',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  `YUT224` datetime DEFAULT NULL COMMENT 'Completion time',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUT225` varchar(6) DEFAULT NULL COMMENT 'Warehouse out method: 0 destroy stock out, 1 lend out stock out, 2 transfer archives warehouse out',
  `YUT226` varchar(50) DEFAULT NULL COMMENT 'Associated primary key',
  `YUT227` varchar(6) DEFAULT NULL COMMENT 'Is it completed',
  `YUF001` varchar(6) DEFAULT NULL COMMENT 'File specification',
  `YUE001` varchar(10) DEFAULT NULL COMMENT 'Archives category',
  PRIMARY KEY (`YUT220`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Delivery master information table';

-- ----------------------------
-- Table structure for ut23
-- ----------------------------
DROP TABLE IF EXISTS `ut23`;
CREATE TABLE `ut23` (
  `YUT230` varchar(25) NOT NULL COMMENT 'Delivery details',
  `YUE001` varchar(6) NOT NULL COMMENT 'Archives category',
  `YUF001` varchar(6) NOT NULL COMMENT 'File specification',
  `YUF002` varchar(25) NOT NULL COMMENT 'File primary key',
  `YUF010` varchar(25) DEFAULT NULL COMMENT 'Serial number of box',
  `YUH750` varchar(25) NOT NULL COMMENT 'Grid number',
  `YUT220` varchar(15) NOT NULL COMMENT 'Serial number of delivery batch',
  `AAE011` varchar(50) NOT NULL COMMENT 'Agent',
  `AAE017` varchar(50) NOT NULL COMMENT 'Handling unit',
  `AAE036` datetime NOT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUT230`),
  KEY `INDX_UT23_YUF002` (`YUF002`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File delivery details';

-- ----------------------------
-- Table structure for ut24
-- ----------------------------
DROP TABLE IF EXISTS `ut24`;
CREATE TABLE `ut24` (
  `YUT240` varchar(20) NOT NULL COMMENT 'Serial number',
  `YUB001` varchar(10) DEFAULT NULL COMMENT 'Quanzong number',
  `YUT241` decimal(65,30) DEFAULT NULL COMMENT 'The acceptance has not been completed',
  `YUT242` decimal(65,30) DEFAULT NULL COMMENT 'Completed but not handed over',
  `YUT243` decimal(65,30) DEFAULT NULL COMMENT 'During handover',
  `YUT244` decimal(65,30) DEFAULT NULL COMMENT 'Handover not sorted out',
  `YUT245` decimal(65,30) DEFAULT NULL COMMENT 'Sorting and not warehousing',
  `YUT246` decimal(65,30) DEFAULT NULL COMMENT 'No identification after expiration',
  `YUT247` decimal(65,30) DEFAULT NULL COMMENT 'In the process of utilization',
  `YUT248` decimal(65,30) DEFAULT NULL COMMENT 'In the process of identification',
  `AAE036` datetime DEFAULT NULL COMMENT 'Statistical date',
  PRIMARY KEY (`YUT240`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut33
-- ----------------------------
DROP TABLE IF EXISTS `ut33`;
CREATE TABLE `ut33` (
  `YUT330` varchar(30) NOT NULL COMMENT 'Reuse material to set record serial number',
  `YUE0J0` varchar(30) DEFAULT NULL COMMENT 'Serial number of reusable material list',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Operator',
  `AAE036` datetime DEFAULT NULL COMMENT 'Operation time',
  `AAE017` varchar(30) DEFAULT NULL COMMENT 'Operating mechanism',
  `AAE012` varchar(50) DEFAULT NULL COMMENT 'Name of operator',
  `YUT031` varchar(30) DEFAULT NULL COMMENT 'Operation type (1. Add, 2. Cancel)',
  PRIMARY KEY (`YUT330`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut34
-- ----------------------------
DROP TABLE IF EXISTS `ut34`;
CREATE TABLE `ut34` (
  `YUT340` varchar(25) NOT NULL COMMENT 'OCR log OS serial number',
  `YUT341` longtext COMMENT 'Identification results',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'The whole family',
  `YUB004` varchar(6) DEFAULT NULL COMMENT 'administrative division',
  `AAE013` text COMMENT 'remarks',
  `YUF05A` varchar(30) DEFAULT NULL COMMENT 'Picture unique ID',
  `AAE011` varchar(25) DEFAULT NULL COMMENT 'Agent No',
  `AAE012` varchar(200) DEFAULT NULL COMMENT 'attention line',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency ID',
  `AAE018` varchar(200) DEFAULT NULL COMMENT 'Name of agency',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUT340`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='OCR log operation record table';

-- ----------------------------
-- Table structure for ut35
-- ----------------------------
DROP TABLE IF EXISTS `ut35`;
CREATE TABLE `ut35` (
  `YUT350` varchar(50) NOT NULL COMMENT 'Serial number',
  `YUB004` varchar(6) DEFAULT NULL COMMENT 'Municipal Organization Code',
  `YUB001` varchar(6) DEFAULT NULL COMMENT 'Quanzong number',
  `YUF00Z` varchar(6) DEFAULT NULL COMMENT 'area number',
  `YUE0E3` varchar(200) DEFAULT NULL COMMENT 'Name of administrative division',
  `YUT351` decimal(10,0) DEFAULT NULL COMMENT 'Total amount of business archives handling',
  `YUT352` decimal(10,0) DEFAULT NULL COMMENT 'Total scanning volume of each city',
  `YUT353` decimal(10,0) DEFAULT NULL COMMENT 'Total inventory in different cities',
  `YUE001` varchar(6) DEFAULT NULL COMMENT 'Archives category',
  `YUT354` datetime DEFAULT NULL COMMENT 'Statistical time',
  `YUT360` varchar(50) DEFAULT NULL COMMENT 'Timing task execution serial number',
  `AAE100` varchar(6) DEFAULT NULL COMMENT 'Valid identification',
  `AAE036` datetime DEFAULT NULL COMMENT 'Handling time',
  PRIMARY KEY (`YUT350`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ut3a
-- ----------------------------
DROP TABLE IF EXISTS `ut3a`;
CREATE TABLE `ut3a` (
  `YUT3A0` varchar(20) NOT NULL COMMENT 'Serial number',
  `YUT3A1` longblob COMMENT 'input data',
  `YUT3A2` longblob COMMENT 'output data',
  `YUT3A3` varchar(6) DEFAULT NULL COMMENT 'Normal return',
  `YUT3A4` varchar(20) DEFAULT NULL COMMENT 'Operation type (0 for query, 1 for update)',
  `AAE011` varchar(20) DEFAULT NULL COMMENT 'Agent',
  `AAE017` varchar(25) DEFAULT NULL COMMENT 'Agency',
  `AAE036` datetime DEFAULT NULL COMMENT 'Record the time',
  PRIMARY KEY (`YUT3A0`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='File station query update operation record';

-- ----------------------------
-- Table structure for yhcip_oracle_jobs
-- ----------------------------
DROP TABLE IF EXISTS `yhcip_oracle_jobs`;
CREATE TABLE `yhcip_oracle_jobs` (
  `JOBID` varchar(20) NOT NULL COMMENT 'Task ID',
  `ORACLEJOBID` varchar(20) NOT NULL COMMENT 'Jobid of Oracle',
  `JOBNAME` varchar(100) NOT NULL COMMENT 'Task name',
  `USERID` varchar(20) NOT NULL COMMENT 'User ID',
  `WHAT` text NOT NULL COMMENT 'The process of execution',
  `STARTTIME` varchar(200) NOT NULL COMMENT 'start time',
  `ENDTIME` datetime DEFAULT NULL COMMENT 'End time',
  `INTERVAL` varchar(200) DEFAULT NULL COMMENT 'time interval',
  `SUBMITTIME` datetime DEFAULT NULL COMMENT 'Submission time',
  PRIMARY KEY (`JOBID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Oracle timing';

-- ----------------------------
-- Table structure for ytaorg
-- ----------------------------
DROP TABLE IF EXISTS `ytaorg`;
CREATE TABLE `ytaorg` (
  `ORGID` decimal(10,0) NOT NULL COMMENT 'Business organization ID',
  `ORGNAME` varchar(60) DEFAULT NULL COMMENT 'Name of business organization',
  `YORGID` decimal(10,0) DEFAULT NULL COMMENT 'File organization ID',
  `YORGNAME` varchar(60) DEFAULT NULL COMMENT 'File organization name',
  PRIMARY KEY (`ORGID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ytauser
-- ----------------------------
DROP TABLE IF EXISTS `ytauser`;
CREATE TABLE `ytauser` (
  `YTA` varchar(20) NOT NULL COMMENT 'Primary key',
  `USERID` decimal(10,0) DEFAULT NULL COMMENT 'As archivist ID',
  `NAME` varchar(60) DEFAULT NULL COMMENT 'full name',
  `ORGID` decimal(10,0) DEFAULT NULL COMMENT 'File organization ID',
  `ORGNAME` varchar(60) DEFAULT NULL COMMENT 'File organization name',
  `YUSERID` decimal(10,0) DEFAULT NULL COMMENT 'As business person Id',
  `YNAME` varchar(60) DEFAULT NULL COMMENT 'full name',
  `YORGNAME` varchar(60) DEFAULT NULL COMMENT 'Name of business organization',
  `YORGID` decimal(10,0) DEFAULT NULL COMMENT 'Business organization ID',
  `CONTACT` varchar(10) DEFAULT NULL COMMENT 'One to one logo',
  PRIMARY KEY (`YTA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for aa10a1ori
-- ----------------------------
DROP VIEW IF EXISTS `aa10a1`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `aa10a1ori` AS select `aa10`.`AAA100` AS `AAA100`,`aa10`.`AAA101` AS `AAA101`,`aa10`.`AAA102` AS `AAA102`,`aa10`.`AAA103` AS `AAA103`,`aa10`.`YAB003` AS `YAB003`,`aa10`.`AAE120` AS `AAE120`,`aa10`.`VER` AS `VER` from `aa10` union select 'YUE010' AS `AAA100`,'Business links' AS `AAA101`,`ue01`.`YUE010` AS `AAA102`,`ue01`.`YUE012` AS `AAA103`,'9999' AS `YAB003`,'0' AS `AAE120`,0 AS `VER` from `ue01` union select 'YUE013' AS `aaa100`,'File category' AS `aaa101`,`ue0a`.`YUE013` AS `aaa102`,`ue0a`.`YUE014` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue0a` union select 'YUE001' AS `aaa100`,'Archives category' AS `aaa101`,`ue06`.`YUE001` AS `aaa102`,`ue06`.`YUE061` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue06` union select 'YUE002' AS `aaa100`,'File category' AS `aaa101`,`ue0b`.`YUE002` AS `aaa102`,`ue0b`.`YUE0B1` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue0b` union select 'YUE030' AS `aaa100`,'Material type' AS `aaa101`,`ue03`.`YUE030` AS `aaa102`,`ue03`.`YUE031` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue03` union select ('YUF00H' or `ue07`.`YUE001`) AS `aaa100`,'Shelf life' AS `aaa101`,`ue07`.`YUF00H` AS `aaa102`,`ue07`.`YUE071` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue07` union select 'YUH701' AS `aaa100`,'Warehouse name' AS `aaa101`,`uh70`.`YUH701` AS `aaa102`,`uh70`.`YUH702` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `uh70` union select 'ORG' AS `aaa100`,'mechanism' AS `aaa101`,`taorg`.`ORGID` AS `aaa102`,`taorg`.`ORGNAME` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `taorg` union select 'ORGID' AS `aaa100`,'mechanism' AS `aaa101`,`taorg`.`ORGID` AS `aaa102`,`taorg`.`ORGNAME` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `taorg` union select 'USER' AS `aaa100`,'personnel' AS `aaa101`,`tauser`.`USERID` AS `aaa102`,`tauser`.`NAME` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `tauser` union select distinct 'YUB005' AS `aaa100`,'province' AS `aaa101`,`ue0g`.`YUB005` AS `aaa102`,`ue0g`.`YUE0G2` AS `aaa103`,'9999' AS `yab003`,'0' AS `aae120`,0 AS `ver` from `ue0g` order by `AAA102`;

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
