
DROP TABLE IF EXISTS `work`;
CREATE TABLE `work` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '标题',
  `description` longtext COMMENT '描述',
  `cover_image_url` longtext,
  `pages` json DEFAULT NULL,
  `publish` tinyint(1) NOT NULL DEFAULT '0',
  `template` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `work_forms`;
CREATE TABLE `work_forms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `form` longtext,
  `work_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `work_id` (`work_id`),
  CONSTRAINT `work_forms_ibfk_1` FOREIGN KEY (`work_id`) REFERENCES `work` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
