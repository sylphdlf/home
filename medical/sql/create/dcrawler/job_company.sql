/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `job_company`;

CREATE TABLE `job_company` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `company_id` BIGINT(20) NOT NULL COMMENT '公司ID',
  `company_name` VARCHAR(50) NOT NULL COMMENT '公司名称',
  `city_area` VARCHAR(50) NOT NULL COMMENT '公司地址',
  `scale_name` VARCHAR(20) NOT NULL COMMENT '公司规模',
  `stage_name` VARCHAR(10) NOT NULL COMMENT '融资',
  `industry_name` VARCHAR(20) NOT NULL COMMENT '行业',
  `create_time` TIMESTAMP NULL DEFAULT current_timestamp COMMENT '创建时间-应用操作时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX unique_company_id(company_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司信息';