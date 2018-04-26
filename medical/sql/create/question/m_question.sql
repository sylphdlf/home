/*Table structure for table `m_question` */
DROP TABLE IF EXISTS `m_question`;

CREATE TABLE `m_question` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL COMMENT '题干',
  `type` INT(1) NOT NULL COMMENT '题型：1、选择题，2、填空题',
  `content` VARCHAR(1000) NOT NULL COMMENT '选项|填空',
  `description` VARCHAR(2000) DEFAULT '' COMMENT '描述',
  `create_time` TIMESTAMP DEFAULT current_timestamp COMMENT '创建时间-应用操作时间',
  `update_time` TIMESTAMP NULL COMMENT '最后修改时间',
  `is_deleted` INT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题库表';