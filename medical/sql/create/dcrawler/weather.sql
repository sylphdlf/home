/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `weather`;

CREATE TABLE `weather` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(20) NOT NULL COMMENT '国家',
  `province` VARCHAR(20) NOT NULL COMMENT '省份',
  `city` VARCHAR(20) NOT NULL COMMENT '城市',
  `weather_current` VARCHAR(20) NOT NULL COMMENT '当前天气',
  `weather_whole_day` VARCHAR(20) NOT NULL COMMENT '全天天气情况',
  `temp_current` VARCHAR(10) NOT NULL COMMENT '当前温度',
  `temp_max` VARCHAR(10) NOT NULL COMMENT '当天最高气温',
  `temp_min` VARCHAR(10) NOT NULL COMMENT '当天最低气温',
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='天气表';