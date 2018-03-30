SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ttt_ds1`
-- ----------------------------
DROP DATABASE IF EXISTS `ttt_ds1`;
CREATE DATABASE `ttt_ds1`;
USE ttt_ds1;
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='市级信息';

-- ----------------------------
--  Records of `city`
-- ----------------------------
BEGIN;
INSERT INTO `city` VALUES ('1', '石家庄', '河北'), ('2', '邯郸', '河北'), ('3', '东莞', '广东'), ('4', '广州', '广东'), ('5', '深圳', '广东');
COMMIT;

DROP DATABASE IF EXISTS `ttt_ds1_s`;
CREATE DATABASE `ttt_ds1_s`;
USE ttt_ds1_s;
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='市级信息';

-- ----------------------------
--  Records of `city`
-- ----------------------------
BEGIN;
INSERT INTO `city` VALUES ('1', '测试城市', '测试省');
COMMIT;


DROP DATABASE IF EXISTS `ttt_ds2`;
CREATE DATABASE `ttt_ds2`;
USE ttt_ds2;
DROP TABLE IF EXISTS `ttt_ds2`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(128) DEFAULT '' COMMENT '产品名称',
  `price` decimal(9,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `product`
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES ('1', '牙膏', '9.90'), ('2', '蚊香', '9.50');
COMMIT;

DROP DATABASE IF EXISTS `ttt_ds2_s`;
CREATE DATABASE `ttt_ds2_s`;
USE ttt_ds2_s;
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `name` varchar(128) DEFAULT '' COMMENT '产品名称',
  `price` decimal(9,2) DEFAULT '0.00' COMMENT '价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

-- ----------------------------
--  Records of `product`
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES ('3', '从库产品测试', '1.00');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;