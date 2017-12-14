/*
MySQL Data Transfer
Source Host: localhost
Source Database: javaweb
Target Host: localhost
Target Database: javaweb
Date: 2017/12/13 14:23:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for order_
-- ----------------------------
DROP TABLE IF EXISTS `order_`;
CREATE TABLE `order_` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `customerid` int(10) NOT NULL,
  `productnames` varchar(256) NOT NULL,
  `total` int(10) DEFAULT NULL,
  `remarks` varchar(256) DEFAULT NULL,
  `valid` bit(1) NOT NULL,
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `order_` VALUES ('5', '1', 'asdfe12344,???3', null, '佛挡杀佛刷单士大夫发发发', '', '2017-12-12 18:38:15');
INSERT INTO `order_` VALUES ('6', '4', 'asdfe474544,???3', null, '刚发的感受到', '', '2017-12-11 20:42:38');
INSERT INTO `order_` VALUES ('7', '8', 'asdfe48644,???3', null, '创造性做相册士大夫相册', '', '2017-12-12 23:38:23');
INSERT INTO `order_` VALUES ('8', '1', 'asdfe453564,???3', null, null, '', '2017-12-11 20:42:42');
INSERT INTO `order_` VALUES ('9', '1', 'asdfe453244,???3', null, null, '', '2017-12-11 20:43:12');
INSERT INTO `order_` VALUES ('10', '1', 'undefined,undefined', null, null, '', '2017-12-12 21:17:00');
INSERT INTO `order_` VALUES ('11', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:24:00');
INSERT INTO `order_` VALUES ('12', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:38:29');
INSERT INTO `order_` VALUES ('13', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:46:39');
INSERT INTO `order_` VALUES ('14', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:47:24');
INSERT INTO `order_` VALUES ('15', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:48:53');
INSERT INTO `order_` VALUES ('16', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:49:40');
INSERT INTO `order_` VALUES ('17', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-12 21:55:29');
INSERT INTO `order_` VALUES ('21', '4', '下护板,发生的3', null, '退货', '', '2017-12-12 23:23:36');
INSERT INTO `order_` VALUES ('22', '7', '下护板', null, null, '', '2017-12-12 23:39:33');
INSERT INTO `order_` VALUES ('24', '1', '下护板', null, null, '', '2017-12-12 23:43:21');
