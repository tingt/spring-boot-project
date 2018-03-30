SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ttt_ds1`
-- ----------------------------
DROP DATABASE IF EXISTS `ttt_ds1`;
CREATE DATABASE `ttt_ds1`;
USE ttt_ds1;
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户表';

insert into t_user values(1,'ttt'),(2,'dsw');

SET FOREIGN_KEY_CHECKS = 1;
