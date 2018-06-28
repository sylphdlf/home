/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `weather`;

CREATE TABLE `weather` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(20) NOT NULL COMMENT '国家',
  `province` VARCHAR(20) NOT NULL COMMENT '省份',
  `city` VARCHAR(20) NOT NULL COMMENT '城市',
  `weather_current` VARCHAR(20) NOT NULL COMMENT '当前天气',
  `weather_whole_day` VARCHAR(20) NOT NULL COMMENT '全天天气情况',
  `temp_current` INT(3) NOT NULL COMMENT '当前温度',
  `temp_max` INT(3) NOT NULL COMMENT '当天最高气温',
  `temp_min` INT(3) NOT NULL COMMENT '当天最低气温',
  `humidity` INT(3) NOT NULL COMMENT '当前湿度',
  `wind` VARCHAR(30) NOT NULL COMMENT '风力和风向',
  `website_update_time` DATETIME DEFAULT NULL COMMENT '气象站更新时间',
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '创建时间',
  `remark` VARCHAR(200) DEFAULT '' COMMENT '备注',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='天气';