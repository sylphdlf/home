CREATE TABLE IF NOT EXISTS `f_file` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL COMMENT '文件名',
  `org_name` VARCHAR(255) NOT NULL COMMENT '原始文件名',
  `suffix` VARCHAR(10) NOT NULL COMMENT '文件后缀',
  `path_root` VARCHAR(255) NOT NULL COMMENT '存储位置根目录',
  `path_relative` VARCHAR(255) NOT NULL COMMENT '存储位置相对',
  `size` BIGINT(20) NOT NULL COMMENT '文件大小(b)',
  `md5` VARCHAR(32) NULL COMMENT 'MD5',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除,1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='文件表';
