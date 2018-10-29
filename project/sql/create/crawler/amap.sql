/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `p_amap`;

CREATE TABLE `p_amap` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `origin` VARCHAR(20) NOT NULL COMMENT '出发点',
  `destination` VARCHAR(20) NULL COMMENT '目的地',
  `waypoints` VARCHAR(20) NULL COMMENT '途径地',
  `time_cost` INT(11) NOT NULL COMMENT '时间(秒)',
  `distance` INT(11) NOT NULL COMMENT '距离(米)',
  `remarks` VARCHAR(200) NULL COMMENT '备注',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通行时间统计';