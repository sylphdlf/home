/*Table structure for table `u_user` */
DROP TABLE IF EXISTS `user_`;

CREATE TABLE `user_` (
  `id` BIGINT(20) NOT NULL,
  `type` TINYINT(2) DEFAULT '0' COMMENT '状态：1：后台运营人员,2：用户账号',
  `username` VARCHAR(255) DEFAULT '' COMMENT '用户名',
  `password` VARCHAR(60) DEFAULT '',
  `nickname` VARCHAR(255) DEFAULT '' COMMENT '昵称',
  `realname` VARCHAR(60) DEFAULT '' COMMENT '真实姓名',
  `sex` TINYINT(1) DEFAULT 0 COMMENT '性别1、男，2、女，3、其他',
  `mobile` VARCHAR(60) DEFAULT 0 COMMENT '手机',
  `status` TINYINT(2) DEFAULT '0' COMMENT '状态：0：待审核；1：审核通过；2：审核不通过；',
  `remarks` VARCHAR(255) DEFAULT '' COMMENT '备注',
  `check_time` TIMESTAMP NULL DEFAULT NULL COMMENT '审核时间',
  `check_user_id` BIGINT(20) DEFAULT 0 COMMENT '审核人id',
  `version_no` INT(11) DEFAULT 0 COMMENT '版本号:默认0,每次更新+1',
  `create_userid` BIGINT(20) DEFAULT 0 COMMENT '创建人ID',
  `create_username` VARCHAR(100) DEFAULT '' COMMENT '创建人姓名',
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '创建时间-应用操作时间',
  `update_userid` BIGINT(20) DEFAULT 0 COMMENT '最后修改人ID',
  `update_username` VARCHAR(100) DEFAULT '' COMMENT '最后修改人姓名',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`),
  KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';