/*Table structure for table `u_user` */
-- DROP TABLE IF EXISTS `user_`;

-- CREATE TABLE `user_` (
--   `id` BIGINT(20) NOT NULL,
--   `type` TINYINT(2) DEFAULT '0' COMMENT '状态：1：后台运营人员,2：用户账号',
--   `username` VARCHAR(255) DEFAULT '' COMMENT '用户名',
--   `password` VARCHAR(60) DEFAULT '',
--   `nickname` VARCHAR(255) DEFAULT '' COMMENT '昵称',
--   `realname` VARCHAR(60) DEFAULT '' COMMENT '真实姓名',
--   `sex` TINYINT(1) DEFAULT 0 COMMENT '性别1、男，2、女，3、其他',
--   `mobile` VARCHAR(60) DEFAULT 0 COMMENT '手机',
--   `status` TINYINT(2) DEFAULT '0' COMMENT '状态：0：待审核；1：审核通过；2：审核不通过；',
--   `remarks` VARCHAR(255) DEFAULT '' COMMENT '备注',
--   `check_time` TIMESTAMP NULL DEFAULT NULL COMMENT '审核时间',
--   `check_user_id` BIGINT(20) DEFAULT 0 COMMENT '审核人id',
--   `version_no` INT(11) DEFAULT 0 COMMENT '版本号:默认0,每次更新+1',
--   `create_userid` BIGINT(20) DEFAULT 0 COMMENT '创建人ID',
--   `create_username` VARCHAR(100) DEFAULT '' COMMENT '创建人姓名',
--   `create_time` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '创建时间-应用操作时间',
--   `update_userid` BIGINT(20) DEFAULT 0 COMMENT '最后修改人ID',
--   `update_username` VARCHAR(100) DEFAULT '' COMMENT '最后修改人姓名',
--   `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
--   `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
--   PRIMARY KEY (`id`),
--   KEY `username` (`username`) USING BTREE
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `u_user`;

CREATE TABLE `u_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL COMMENT '用户名',
  `password` VARCHAR(60) NOT NULL,
  `real_name` VARCHAR(60) DEFAULT NULL COMMENT '真实姓名',
  `gender` INT(2) DEFAULT NULL COMMENT '性别',
  `mobile` VARCHAR(60) DEFAULT NULL COMMENT '手机',
  `telephone` VARCHAR(60) DEFAULT NULL COMMENT '座机',
  `status` INT(2) DEFAULT '0' COMMENT '状态：0：待审核；1：审核通过；2：审核不通过；',
  `org_code` VARCHAR(32) NOT NULL COMMENT '组织机构代码',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

DROP TABLE IF EXISTS `u_organization`;

CREATE TABLE `u_organization` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(32) DEFAULT NULL COMMENT '组织机构代码',
  `parent_code` VARCHAR(32) DEFAULT '0' COMMENT '上级组织机构代码',
  `name` VARCHAR(255) DEFAULT NULL COMMENT '组织机构名',
  `level` INT(2) DEFAULT NULL COMMENT '部门等级 1 一级部门  2 二级部门 3 三级部门 4 四级部门...',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='组织机构表';

DROP TABLE IF EXISTS `u_org_user`;

CREATE TABLE `u_org_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `org_id` BIGINT(20) DEFAULT NULL COMMENT '组织机构id',
  `user_id` BIGINT(20) DEFAULT '0' COMMENT '用户id',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_organization_id` (`org_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户组织关联表';

DROP TABLE IF EXISTS `u_user_role`;

CREATE TABLE `u_user_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) NOT NULL COMMENT '用户id',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

DROP TABLE IF EXISTS `u_org_role`;

CREATE TABLE `u_org_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `org_id` BIGINT(20) NOT NULL COMMENT '组织机构id',
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_org_id` (`org_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='组织角色关联表';

DROP TABLE IF EXISTS `u_role`;

CREATE TABLE `u_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(60) DEFAULT NULL COMMENT '角色编码,方便业务系统赋权限',
  `name` VARCHAR(128) NOT NULL COMMENT '角色名称',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色表';

DROP TABLE IF EXISTS `u_role_function`;

CREATE TABLE `u_role_function` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT(20) NOT NULL COMMENT '角色id',
  `function_id` BIGINT(20) NOT NULL COMMENT '权限id',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

DROP TABLE IF EXISTS `u_function`;

CREATE TABLE `u_function` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(32) DEFAULT NULL COMMENT '权限code',
  `parent_code` VARCHAR(32) NOT NULL DEFAULT '0' COMMENT '父节点',
  `type` INT(2) NOT NULL COMMENT '权限类型 1 菜单、2按钮、3资源',
  `path` VARCHAR(128) DEFAULT NULL COMMENT '访问路径',
  `name` VARCHAR(128) DEFAULT NULL COMMENT '权限名称',
  `remarks` VARCHAR(255) DEFAULT NULL COMMENT '权限描述',
  `level` INT(2) DEFAULT NULL COMMENT '菜单层级：1：一级菜单、2：二级菜单',
  `icon` VARCHAR(255) DEFAULT NULL,
  `sort_value` INT(4) NULL COMMENT '菜单排序字段',
  `create_time` TIMESTAMP NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `create_user_id` BIGINT(20) NULL COMMENT '创建人',
  `update_user_id` BIGINT(20) NULL COMMENT '最后修改人',
  `is_deleted` INT(2) DEFAULT '0' COMMENT '是否逻辑删除:默认0未删除;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限表';