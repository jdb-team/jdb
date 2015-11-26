/*
Navicat MySQL Data Transfer

Source Server         : jdbtest
Source Server Version : 50622
Source Host           : 172.16.16.235:3306
Source Database       : jdb

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2015-09-21 16:59:50
*/
USE jdb;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for `accesskey`
-- ----------------------------
CREATE TABLE `accesskey` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `accesskey` LONGTEXT COMMENT '访问控制key',
  `c_time`    DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `type`      VARCHAR(32)      DEFAULT NULL
  COMMENT '类型',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '访问控制key表';

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
CREATE TABLE `activity` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `activitydesc`  VARCHAR(255)     DEFAULT NULL
  COMMENT '活动描述',
  `address`       VARCHAR(128)     DEFAULT NULL
  COMMENT '地址',
  `city`          VARCHAR(16)      DEFAULT NULL
  COMMENT '城市',
  `contactemail`  VARCHAR(32)      DEFAULT NULL
  COMMENT '联系邮箱',
  `contactman`    VARCHAR(16)      DEFAULT NULL
  COMMENT '联系人',
  `contactphone`  VARCHAR(16)      DEFAULT NULL
  COMMENT '联系电话',
  `c_time`        DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `e_time`        DATETIME         DEFAULT NULL
  COMMENT '结束时间',
  `joinfee`       LONGTEXT
  COMMENT '报名费用',
  `picpath`       VARCHAR(255)     DEFAULT NULL
  COMMENT '图片路径',
  `pv`            INT(11)          DEFAULT NULL
  COMMENT '访问次数',
  `s_time`        DATETIME         DEFAULT NULL
  COMMENT '开始时间',
  `state`         INT(11)          DEFAULT NULL
  COMMENT '0 已下线 1正常',
  `title`         VARCHAR(64)      DEFAULT NULL
  COMMENT '标题',
  `create_userid` INT(11)          DEFAULT NULL
  COMMENT '创建者id',
  `close_time`    DATETIME         DEFAULT NULL
  COMMENT '截止时间',
  `otherinfo`     LONGTEXT

  COMMENT '其他信息',
  `personlimit`   INT(11)          DEFAULT NULL
  COMMENT '人数限制',
  `topic`         LONGTEXT
  COMMENT '主题',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '活动表';

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
CREATE TABLE `admin` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `c_time`   DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `level`    INT(11)          DEFAULT NULL
  COMMENT '管理员级别 0 超级管理员 1普通管理员',
  `password` VARCHAR(32)      DEFAULT NULL
  COMMENT '密码',
  `phone`    VARCHAR(16)      DEFAULT NULL
  COMMENT '手机号',
  `realName` VARCHAR(16)      DEFAULT NULL
  COMMENT '真实姓名',
  `username` VARCHAR(32)      DEFAULT NULL
  COMMENT '账号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '管理员表';

-- ----------------------------
-- Table structure for `alumnus`
-- ----------------------------
CREATE TABLE `alumnus` (
  `id`           INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `company`      VARCHAR(32)      DEFAULT ''
  COMMENT '公司',
  `department`   VARCHAR(64)      DEFAULT ''
  COMMENT '院系',
  `email`        VARCHAR(32)      DEFAULT ''
  COMMENT '邮箱',
  `enrealname`   VARCHAR(16)      DEFAULT ''
  COMMENT '英文姓名',
  `grade`        VARCHAR(16)      DEFAULT ''
  COMMENT '年级',
  `headpic_path` VARCHAR(255)     DEFAULT ''
  COMMENT '头像路径',
  `lat`          DOUBLE           DEFAULT NULL
  COMMENT '经度',
  `lng`          DOUBLE           DEFAULT NULL
  COMMENT '维度',
  `phone`        VARCHAR(11)      DEFAULT NULL
  COMMENT '手机号',
  `realname`     VARCHAR(16)      DEFAULT ''
  COMMENT '真实姓名',
  `school`       VARCHAR(64)      DEFAULT ''
  COMMENT '学校',
  `sex`          INT(11)          DEFAULT NULL
  COMMENT '性别',
  `title`        VARCHAR(16)      DEFAULT ''
  COMMENT '职位',
  `weixin`       VARCHAR(32)      DEFAULT ''
  COMMENT '微信号',
  `remark`       VARCHAR(64)      DEFAULT ''
  COMMENT '备注信息',
  `user_id`      INT(11)          DEFAULT NULL
  COMMENT '用户编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户详情表';

-- ----------------------------
-- Table structure for `atvcmt`
-- ----------------------------
CREATE TABLE `atvcmt` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `content`        VARCHAR(255)     DEFAULT NULL
  COMMENT '内容',
  `c_time`         DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `likecount`      INT(11)          DEFAULT NULL
  COMMENT '点赞次数',
  `activity_id`    INT(11)          DEFAULT NULL
  COMMENT '活动编号',
  `comment_userid` INT(11)          DEFAULT NULL
  COMMENT '评论者编号',
  `parent_id`      INT(11)          DEFAULT NULL
  COMMENT '父极评论编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '活动评论表';

-- ----------------------------
-- Table structure for `circle`
-- ----------------------------
CREATE TABLE `circle` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `c_time`        DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `iconpath`      VARCHAR(255)     DEFAULT NULL
  COMMENT '图标路径',
  `introduce`     VARCHAR(255)     DEFAULT NULL
  COMMENT '介绍',
  `picpath`       VARCHAR(255)     DEFAULT NULL
  COMMENT '图片路径',
  `state`         INT(11)          DEFAULT NULL
  COMMENT '状态 0 已下线 1正常',
  `title`         VARCHAR(32)      DEFAULT NULL
  COMMENT '标题',
  `create_userid` INT(11)          DEFAULT NULL
  COMMENT '所属用户编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '圈子表';


-- ----------------------------
-- Table structure for `circmt`
-- ----------------------------
CREATE TABLE `circmt` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `content`        VARCHAR(255)     DEFAULT NULL
  COMMENT '内容',
  `c_time`         DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `likecount`      INT(11)          DEFAULT NULL
  COMMENT '点赞次数',
  `circle_id`      INT(11)          DEFAULT NULL
  COMMENT '圈子编号',
  `comment_userid` INT(11)          DEFAULT NULL
  COMMENT '评论者编号',
  `parent_id`      INT(11)          DEFAULT NULL
  COMMENT '父级评论编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '圈子评论表';

-- ----------------------------
-- Table structure for `focus`
-- ----------------------------
CREATE TABLE `focus` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `info`     VARCHAR(255)     DEFAULT NULL
  COMMENT '信息',
  `picpath`  VARCHAR(64)      DEFAULT NULL
  COMMENT '图片路径',
  `position` INT(11)          DEFAULT NULL
  COMMENT '位置',
  `refId`    INT(11)          DEFAULT NULL
  COMMENT '所属编号',
  `title`    VARCHAR(64)      DEFAULT NULL
  COMMENT '标题',
  `type`     VARCHAR(16)      DEFAULT NULL
  COMMENT '类型',
  `viewurl`  VARCHAR(32)      DEFAULT NULL
  COMMENT '查看url',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '焦点图表';

-- ----------------------------
-- Table structure for `friend`
-- ----------------------------
CREATE TABLE `friend` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `a_time`    DATETIME         DEFAULT NULL
  COMMENT '申请时间',
  `c_time`    DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `distance`  DOUBLE           DEFAULT NULL
  COMMENT '距离',
  `state`     BIT(1)           DEFAULT NULL
  COMMENT '状态',
  `friend_id` INT(11)          DEFAULT NULL
  COMMENT '朋友编号',
  `user_id`   INT(11)          DEFAULT NULL
  COMMENT '用户编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '好友表';


-- ----------------------------
-- Table structure for `inform`
-- ----------------------------
CREATE TABLE `inform` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `asso_id`       INT(11)          DEFAULT NULL
  COMMENT '关联编号',
  `asso_name`     VARCHAR(64)      DEFAULT NULL
  COMMENT '关联名称',
  `c_time`        DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `otherinfo`     VARCHAR(255)     DEFAULT NULL
  COMMENT '其他信息',
  `reason`        VARCHAR(16)      DEFAULT NULL
  COMMENT '举报原因',
  `state`         INT(11)          DEFAULT NULL
  COMMENT '状态',
  `type`          VARCHAR(8)       DEFAULT NULL
  COMMENT '举报类型',
  `viewurl`       VARCHAR(64)      DEFAULT NULL
  COMMENT '查看url',
  `create_userid` INT(11)          DEFAULT NULL
  COMMENT '举报者编号',
  `inform_userid` INT(11)          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '举报表';


-- ----------------------------
-- Table structure for `invitatecode`
-- ----------------------------
CREATE TABLE `invitatecode` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `code`          VARCHAR(8)       DEFAULT NULL
  COMMENT '邀请码',
  `c_time`        DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `create_userid` INT(11)          DEFAULT NULL
  COMMENT '创建者编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '邀请码表';

-- ----------------------------
-- Table structure for `join_user_activity`
-- ----------------------------
CREATE TABLE `join_user_activity` (
  `activity_id` INT(11) NOT NULL
  COMMENT '活动编号',
  `user_id`     INT(11) NOT NULL
  COMMENT '用户编号',
  PRIMARY KEY (`activity_id`, `user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户活动关系表';


-- ----------------------------
-- Table structure for `message`
-- ----------------------------
CREATE TABLE `message` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `content`        VARCHAR(255)     DEFAULT NULL
  COMMENT '内容',
  `c_time`         DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `state`          INT(11)          DEFAULT NULL
  COMMENT '状态',
  `receive_userid` INT(11)          DEFAULT NULL
  COMMENT '接受者编号',
  `sender_userid`  INT(11)          DEFAULT NULL
  COMMENT '发送者编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '消息表';


-- ----------------------------
-- Table structure for `msgnotify`
-- ----------------------------
CREATE TABLE `msgnotify` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `c_time`     DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `msg`        VARCHAR(255)     DEFAULT NULL
  COMMENT '消息内容',
  `ctc_userid` INT(11)          DEFAULT NULL
  COMMENT '关联用户id',
  `cur_userid` INT(11)          DEFAULT NULL
  COMMENT '当前用户id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '消息通知表';


-- ----------------------------
-- Table structure for `topic`
-- ----------------------------
CREATE TABLE `topic` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `content`       VARCHAR(255)     DEFAULT NULL
  COMMENT '内容',
  `c_time`        DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `picpath`       VARCHAR(255)     DEFAULT NULL
  COMMENT '图片路径',
  `pv`            INT(11)          DEFAULT '0'
  COMMENT '访问次数',
  `lv`            INT(11)          DEFAULT '0'
  COMMENT '点赞次数',
  `sv`            INT(11)          DEFAULT '0'
  COMMENT '分享次数',
  `state`         INT(11)          DEFAULT NULL
  COMMENT '状态',
  `create_userid` INT(11)          DEFAULT NULL
  COMMENT '创建者编号',
  `type_id`       INT(11)          DEFAULT NULL
  COMMENT '所属类型id',
  `specolumn_id`  INT(11)          DEFAULT NULL
  COMMENT '所属专栏id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '话题表';


-- ----------------------------
-- Table structure for `topictype`
-- ----------------------------
CREATE TABLE `topictype` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `typedesc` VARCHAR(255)     DEFAULT NULL
  COMMENT '类别描述',
  `typename` VARCHAR(32)      DEFAULT NULL
  COMMENT '类别名称',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '话题类别表';

-- ----------------------------
-- Table structure for `topiccmt`
-- ----------------------------
CREATE TABLE `topiccmt` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `content`        VARCHAR(255)     DEFAULT NULL
  COMMENT '内容',
  `c_time`         DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `likecount`      INT(11)          DEFAULT NULL
  COMMENT '点赞次数',
  `comment_userid` INT(11)          DEFAULT NULL
  COMMENT '评论者编号',
  `topic_id`       INT(11)          DEFAULT NULL
  COMMENT '话题编号',
  `parent_id`      INT(11)          DEFAULT NULL
  COMMENT '父级评论编号',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '话题评论表';


-- ----------------------------
-- Table structure for `user`
-- ----------------------------
CREATE TABLE `user` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `c_time`   DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `password` VARCHAR(32)      DEFAULT NULL
  COMMENT '密码',
  `state`    INT(11)          DEFAULT NULL
  COMMENT '状态',
  `username` VARCHAR(32)      DEFAULT NULL
  COMMENT '用户名',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '用户表';


-- ----------------------------
-- Table structure for `verifycode`
-- ----------------------------
CREATE TABLE `verifycode` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `c_time`     DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `phone`      VARCHAR(16)      DEFAULT NULL
  COMMENT '电话',
  `verifycode` VARCHAR(8)       DEFAULT NULL
  COMMENT '验证码',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT = '验证码表';

-- ----------------------------
-- Table structure for `specolumn`
-- ----------------------------
CREATE TABLE `specolumn` (
  `id`         INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `columnname` VARCHAR(32)      DEFAULT NULL
  COMMENT '专栏名称',
  `columndesc` VARCHAR(256)     DEFAULT NULL
  COMMENT '专栏描述',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '专栏表';

-- ----------------------------
-- Table structure for `like_user_topic`
-- ----------------------------
CREATE TABLE `like_user_topic` (
  `topic_id` INT(11) NOT NULL
  COMMENT '话题id',
  `user_id`  INT(11) NOT NULL
  COMMENT '用户id',
  PRIMARY KEY (`topic_id`, `user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户话题点赞关系维护表';

-- ----------------------------
-- Table structure for `share_user_topic`
-- ----------------------------
CREATE TABLE `share_user_topic` (
  `topic_id` INT(11) NOT NULL
  COMMENT '话题id',
  `user_id`  INT(11) NOT NULL
  COMMENT '用户id',
  PRIMARY KEY (`topic_id`, `user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户话题点赞关系维护表';

-- ----------------------------
-- Table structure for `offlinenotice`
-- ----------------------------
CREATE TABLE `offlinenotice` (
  `id`       INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `c1`       VARCHAR(32)      DEFAULT NULL
  COMMENT '模板填充位置1',
  `c2`       VARCHAR(32)      DEFAULT NULL
  COMMENT '模板填充位置2',
  `c3`       VARCHAR(32)      DEFAULT NULL
  COMMENT '模板填充位置3',
  `c_time`   DATETIME         DEFAULT NULL
  COMMENT '创建时间',
  `admin_id` INT(11)          DEFAULT NULL
  COMMENT '创建人',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '下线通知表';

-- ----------------------------
-- Table structure for `join_user_circle`
-- ----------------------------
CREATE TABLE `join_user_circle` (
  `id`        INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `nickname`  VARCHAR(32)      DEFAULT NULL
  COMMENT '昵称',
  `circle_id` INT(11)          DEFAULT NULL
  COMMENT '圈子id',
  `user_id`   INT(11)          DEFAULT NULL
  COMMENT '用户id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户圈子关系维护表';

-- ----------------------------
-- Table structure for `shielduser`
-- ----------------------------
CREATE TABLE `shielduser` (
  `id`             INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `type`           VARCHAR(32)      DEFAULT NULL
  COMMENT '类型',
  `cur_user_id`    INT(11)          DEFAULT NULL
  COMMENT '屏蔽用户id',
  `shield_user_id` INT(11)          DEFAULT NULL
  COMMENT '被屏蔽用户id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '用户屏蔽表';


-- ----------------------------
-- Table structure for `circlemessage`
-- ----------------------------
CREATE TABLE `circlemessage` (
  `id`            INT(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键',
  `content`       LONGTEXT,
  `c_time`        DATETIME         DEFAULT NULL
  COMMENT '消息内容',
  `circle_id`     INT(11)          DEFAULT NULL
  COMMENT '圈子id',
  `sender_userid` INT(11)          DEFAULT NULL
  COMMENT '发送者id',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT '圈子群聊消息表';