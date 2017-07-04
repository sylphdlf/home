/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `job_keyword`;

CREATE TABLE `job_keyword` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `batch_num` INT(11) NOT NULL COMMENT '获取批次',
  `website` VARCHAR(50) NOT NULL COMMENT '网站',
  `keyword` VARCHAR(50) NOT NULL COMMENT '关键字',
  `keyword_num` INT(11) NOT NULL COMMENT '关键字出现次数',
  `create_userid` BIGINT(20) DEFAULT 0 COMMENT '创建人ID',
  `create_username` VARCHAR(100) DEFAULT '' COMMENT '创建人姓名',
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '创建时间-应用操作时间',
  `update_userid` BIGINT(20) DEFAULT 0 COMMENT '最后修改人ID',
  `update_username` VARCHAR(100) DEFAULT '' COMMENT '最后修改人姓名',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='招聘网站关键字表';