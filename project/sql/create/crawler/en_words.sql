/*Table structure for table `job_keyword` */
DROP TABLE IF EXISTS `en_words`;

CREATE TABLE `en_words` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `words` VARCHAR (20) NOT NULL COMMENT '单词或短语',
  `meaning` VARCHAR (200) NOT NULL COMMENT '意思',
  `type` INT (2) NOT NULL DEFAULT '1' COMMENT '类型 1、单词，2、短语',
  `category_id` VARCHAR (20) NULL COMMENT '类目，可多个',
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` TIMESTAMP NULL DEFAULT NULL COMMENT '最后修改时间',
  `is_deleted` TINYINT(1) DEFAULT 0 COMMENT '是否逻辑删除:默认0正常;1已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX unique_words(words)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='词汇表';