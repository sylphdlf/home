/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `p_weather`;

CREATE TABLE `p_weather` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(20) NOT NULL COMMENT '国家',
  `province` VARCHAR(20) NULL COMMENT '省份',
  `city` VARCHAR(20) NULL COMMENT '城市',
  `weather` VARCHAR(20) NULL COMMENT '当前天气',
  `temperature` INT(3) NULL COMMENT '当前温度',
  `pm25` INT(3) NULL COMMENT '当前温度',
  `humidity` INT(3) NULL COMMENT '当前湿度',
  `wind` VARCHAR(30) NULL COMMENT '风力和风向',
  `data_update_time` DATETIME NULL COMMENT '气象站更新时间',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `remarks` VARCHAR(200) NULL COMMENT '备注',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='天气';