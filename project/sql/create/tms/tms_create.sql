DROP TABLE IF EXISTS `t_contacts`;

CREATE TABLE `t_contacts` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) DEFAULT NULL COMMENT '助记码',
  `name` VARCHAR(50) DEFAULT NULL COMMENT '姓名',
  `type` INT(2) DEFAULT NULL COMMENT '联系人类型1、发货人，2、收货人',
  `telephone` VARCHAR(20) DEFAULT NULL COMMENT '座机',
  `mobile` VARCHAR(60) DEFAULT NULL COMMENT '手机',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '地址',
  `account_bank` VARCHAR(100) DEFAULT NULL COMMENT '开户行',
  `account_card` VARCHAR(20) DEFAULT NULL COMMENT '卡号',
  `account_name` VARCHAR(20) DEFAULT NULL COMMENT '账户名',
  `invoice_type` INT(2) DEFAULT NULL COMMENT '是否开票1、是，2、否',
  `often_send` VARCHAR(50) DEFAULT NULL COMMENT '常发物品',
  `often_package` VARCHAR(50) DEFAULT NULL COMMENT '常用包装',
  `pay_type` INT(2) DEFAULT NULL COMMENT '结算方式',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='联系人信息';

DROP TABLE IF EXISTS `t_vehicle`;

CREATE TABLE `t_vehicle` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `vehicle_number` VARCHAR(20) DEFAULT NULL COMMENT '车牌号',
  `vehicle_type` INT(2) DEFAULT NULL COMMENT '车辆类型',
  `vehicle_length` INT(6) DEFAULT NULL COMMENT '车辆长度(单位：毫米)',
  `vehicle_width` INT(6) DEFAULT NULL COMMENT '车辆宽度(单位：毫米)',
  `vehicle_height` INT(6) DEFAULT NULL COMMENT '车辆高度(单位：毫米)',
  `vehicle_vin` VARCHAR(100) DEFAULT NULL COMMENT '车架号或发动机编号',
  `driver_name` VARCHAR(20) DEFAULT NULL COMMENT '司机姓名',
  `driver_mobile` VARCHAR(60) DEFAULT NULL COMMENT '司机手机',
  `driver_telephone` VARCHAR(60) DEFAULT NULL COMMENT '司机座机',
  `driver_addr` VARCHAR(100) DEFAULT NULL COMMENT '司机住址',
  `driver_licence` VARCHAR(20) DEFAULT NULL COMMENT '驾照',
  `owner_name` VARCHAR(20) DEFAULT NULL COMMENT '车主姓名',
  `owner_mobile` VARCHAR(60) DEFAULT NULL COMMENT '车主手机',
  `account_bank` VARCHAR(100) DEFAULT NULL COMMENT '开户行',
  `account_name` VARCHAR(20) DEFAULT NULL COMMENT '开户名',
  `account_card` VARCHAR(20) DEFAULT NULL COMMENT '开户卡号',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_vehicle_number` (`vehicle_number`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='车辆信息';

DROP TABLE IF EXISTS `t_consign`;

CREATE TABLE `t_consign` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `consign_code` VARCHAR(50) NULL COMMENT '托运单号',
  `departure` VARCHAR(50) NULL COMMENT '始发地',
  `destination` VARCHAR(100) NULL COMMENT '目的地',
  `consignor_id` BIGINT(20) NULL COMMENT '发货人id',
  `consignee_id` BIGINT(20) NULL COMMENT '收货人id',
  `vehicle_id` BIGINT(20) NULL COMMENT '车辆id',
  `product_id` BIGINT(20) NULL COMMENT '商品id',
  `loading_place` VARCHAR(255) NULL COMMENT '装货地址',
  `vehicle_leave_time` TIMESTAMP NULL COMMENT '出车时间',
  `cargo_leave_time` TIMESTAMP NULL COMMENT '发货时间',
  `expected_arrive_time` TIMESTAMP NULL COMMENT '要求到货时间',
  `cargo_package_time` TIMESTAMP NULL COMMENT '装货时间',
  `pay_type` INT(2) DEFAULT NULL COMMENT '结算方式',
  `transport_type` INT(2) DEFAULT NULL COMMENT '运输方式',
  `receipt_type` INT(2) DEFAULT NULL COMMENT '回单类型',
  `delivery_type` INT(2) DEFAULT NULL COMMENT '接货方式',
  `basic_price` DECIMAL(12,2) DEFAULT NULL COMMENT '基本运费',
  `vehicle_price` DECIMAL(12,2) DEFAULT NULL COMMENT '车费',
  `pay_now` DECIMAL(12,2) DEFAULT NULL COMMENT '现付',
  `pay_delivery` DECIMAL(12,2) DEFAULT NULL COMMENT '提付(货到付款)',
  `pay_receipt` DECIMAL(12,2) DEFAULT NULL COMMENT '回单付',
  `pay_monthly` DECIMAL(12,2) DEFAULT NULL COMMENT '月结',
  `pay_account` DECIMAL(12,2) DEFAULT NULL COMMENT '扣付',
  `pay_total` DECIMAL(12,2) DEFAULT NULL COMMENT '合计',
  `status` INT(2) DEFAULT NULL COMMENT '状态：0、草稿，1、完成，2、提交',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='托运单';

DROP TABLE IF EXISTS `t_consign_detail`;

# CREATE TABLE `t_consign_detail` (
#   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
#   `consign_id` BIGINT(20) NOT NULL COMMENT '托运单主表ID',
#   `product_name` VARCHAR(50) NULL COMMENT '品名',
#   `product_type` VARCHAR(30) NULL COMMENT '型号',
#   `product_count` INT(11) NULL COMMENT '数量',
#   `product_package` VARCHAR(20) NULL COMMENT '包装',
#   `product_volume` INT(11) NULL COMMENT '体积(立方厘米)',
#   `product_weight` INT(11) NULL COMMENT '重量（kg）',
#   `product_price` DECIMAL(12,2) NULL COMMENT '价格(元)',
#   `product_length` INT(11) NULL COMMENT '长度(mm)',
#   `product_width` INT(11) NULL COMMENT '宽度(mm)',
#   `product_height` INT(11) NULL COMMENT '高度(mm)',
#   `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
#   `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
#   `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
#   `create_user_id` BIGINT(20) NULL COMMENT '创建人',
#   `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
#   `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
#   PRIMARY KEY (`id`)
# ) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='托运单子表';

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NULL COMMENT '品名',
  `type` VARCHAR(30) NULL COMMENT '型号',
  `packaging` VARCHAR(20) NULL COMMENT '包装',
  `volume` INT(11) NULL COMMENT '体积(立方厘米)',
  `weight` INT(11) NULL COMMENT '重量（kg）',
  `price` DECIMAL(12,2) NULL COMMENT '价格(元)',
  `length` INT(11) NULL COMMENT '长度(mm)',
  `width` INT(11) NULL COMMENT '宽度(mm)',
  `height` INT(11) NULL COMMENT '高度(mm)',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='商品表';

DROP TABLE IF EXISTS `t_stock`;

CREATE TABLE `t_stock` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20) NOT NULL COMMENT '商品ID',
  `count` INT(11) NULL COMMENT '数量',
  `count_frozen` INT(11) NULL COMMENT '数量(冻结)',
  `volume` INT(11) NULL COMMENT '体积(cm³)',
  `weight` INT(11) NULL COMMENT '重量（kg）',
  `price` DECIMAL(12,2) NULL COMMENT '价格(元)',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='库存表';

DROP TABLE IF EXISTS `t_pickup`;

CREATE TABLE `t_pickup` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pickup_code` VARCHAR(50) NULL COMMENT '批次',
  `site` INT(2) NULL COMMENT '接货网点',
  `vehicle_id` BIGINT(20) NULL COMMENT '车辆id',
  `price` DECIMAL(12,2) NULL COMMENT '派车成本',
  `count` INT(3) NULL COMMENT '订单数量',
  `pickup_time` TIMESTAMP NULL COMMENT '接货时间',
  `remarks` VARCHAR(255) NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='接货表';

DROP TABLE IF EXISTS `t_pickup_detail`;

CREATE TABLE `t_pickup_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pickup_id` BIGINT(20) NULL COMMENT '接货ID',
  `consign_id` BIGINT(20) NULL COMMENT '托运单id',
  `price` DECIMAL(12,2) NULL COMMENT '派车成本(分摊)',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='接货子表';
