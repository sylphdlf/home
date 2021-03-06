DROP TABLE IF EXISTS `s_market`;

CREATE TABLE IF NOT EXISTS `s_market` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NOT NULL COMMENT '编号',
  `name` VARCHAR(20) NOT NULL COMMENT '名称',
  `market` VARCHAR(10) NOT NULL COMMENT '市场，sh，sz等',
  `type` INT(2) NULL COMMENT '1、涨，2、跌',
  `price` DECIMAL(10,2) NULL COMMENT '当前价格',
  `rise_fall` DECIMAL(10,2) NULL COMMENT '涨跌',
  `rise_fall_ratio` DECIMAL(10,2) NULL COMMENT '涨跌%',
  `rise_fall_history` VARCHAR(2000) NULL COMMENT '历史涨跌幅',
  `days` INT(11) NULL COMMENT '持续天数',
  `high_low` VARCHAR(20) NULL COMMENT '最高/最低',
  `earnings_ratio` DECIMAL(10,2) NULL COMMENT '市盈率',
  `earnings_ratio_history` VARCHAR(2000) NULL COMMENT '市盈率历史',
  `exchange_ratio` DECIMAL(10,2) NULL COMMENT '换手率',
  `exchange_ratio_history` VARCHAR(2000) NULL COMMENT '换手率历史',
  `price_boot_ratio` DECIMAL(10,2) NULL COMMENT '市净率',
  `price_boot_ratio_history` VARCHAR(2000) NULL COMMENT '市净率历史',
  `watching_status` INT(2) DEFAULT 0 COMMENT '监控，0停止，1开启',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX unique_code(code)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='市场';

DROP TABLE IF EXISTS `s_market_info`;

CREATE TABLE IF NOT EXISTS `s_market_info` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NOT NULL COMMENT '编号',
  `name` VARCHAR(20) NOT NULL COMMENT '名称',
  `market` VARCHAR(10) NULL COMMENT '市场，sh，sz等',
  `price` DECIMAL(10,2) NULL COMMENT '当前价格',
  `price_close` DECIMAL(10,2) NULL COMMENT '昨日收盘价',
  `price_open` DECIMAL(10,2) NULL COMMENT '今日开盘价',
  `deal_count` INT(11) NULL COMMENT '成交量（手）',
  `deal_price` DECIMAL(10,2) NULL COMMENT '成交额（万）',
  `outer_count` INT(11) NULL COMMENT '外盘',
  `inner_count` INT(11) NULL COMMENT '内盘',
  `buying_one` DECIMAL(10,2) NULL COMMENT '买一',
  `buying_one_count` INT(11) NULL COMMENT '买一量（手）',
  `buying_two` DECIMAL(10,2) NULL COMMENT '买二',
  `buying_two_count` INT(11) NULL COMMENT '买二量（手）',
  `buying_three` DECIMAL(10,2) NULL COMMENT '买三',
  `buying_three_count` INT(11) NULL COMMENT '买三量（手）',
  `buying_four` DECIMAL(10,2) NULL COMMENT '买四',
  `buying_four_count` INT(11) NULL COMMENT '买四量（手）',
  `buying_five` DECIMAL(10,2) NULL COMMENT '买五',
  `buying_five_count` INT(11) NULL COMMENT '买五量（手）',
  `selling_one` DECIMAL(10,2) NULL COMMENT '卖一',
  `selling_one_count` INT(11) NULL COMMENT '卖一量（手）',
  `selling_two` DECIMAL(10,2) NULL COMMENT '卖二',
  `selling_two_count` INT(11) NULL COMMENT '卖二量（手）',
  `selling_three` DECIMAL(10,2) NULL COMMENT '卖三',
  `selling_three_count` INT(11) NULL COMMENT '卖三量（手）',
  `selling_four` DECIMAL(10,2) NULL COMMENT '卖四',
  `selling_four_count` INT(11) NULL COMMENT '卖四量（手）',
  `selling_five` DECIMAL(10,2) NULL COMMENT '卖五',
  `selling_five_count` INT(11) NULL COMMENT '卖五量（手）',
  `recently_deal_info` VARCHAR(1000) NULL COMMENT '最近逐笔成交',
  `refresh_time` TIMESTAMP NULL COMMENT '数据更新时间',
  `rise_fall` DECIMAL(10,2) NULL COMMENT '涨跌',
  `rise_fall_ratio` DECIMAL(5,2) NULL COMMENT '涨跌%',
  `price_highest` DECIMAL(10,2) NULL COMMENT '最高',
  `price_lowest` DECIMAL(10,2) NULL COMMENT '最低',
  `price_deal_info` VARCHAR(200) NULL COMMENT '价格/成交量（手）/成交额',
  `exchange_ratio` DECIMAL(5,2) NOT NULL COMMENT '换手率',
  `earnings_ratio` DECIMAL(5,2) NULL COMMENT '市盈率',
  `stock_amplitude` DECIMAL(5,2) NULL COMMENT '振幅',
  `live_value` DECIMAL(10,2) NULL COMMENT '流通市值',
  `total_value` DECIMAL(10,2) NULL COMMENT '总市值',
  `price_boot_ratio` DECIMAL(10,2) NULL COMMENT '市净率',
  `limit_up` DECIMAL(10,2) NULL COMMENT '涨停价',
  `limit_down` DECIMAL(10,2) NULL COMMENT '跌停价',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='market_info';


