/*
MySQL Data Transfer
Source Host: localhost
Source Database: javaweb
Target Host: localhost
Target Database: javaweb
Date: 2017/12/13 14:23:40
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for order_product
-- ----------------------------
DROP TABLE IF EXISTS `order_product`;
CREATE TABLE `order_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `orderid` int(10) NOT NULL,
  `productId` int(10) NOT NULL,
  `num` int(10) NOT NULL,
  `price` int(10) DEFAULT NULL,
  `sell` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `order_product` VALUES ('1', '5', '4257', '1', null, '');
INSERT INTO `order_product` VALUES ('2', '5', '4258', '1', null, '');
INSERT INTO `order_product` VALUES ('3', '6', '4257', '1', '36', '');
INSERT INTO `order_product` VALUES ('4', '6', '4258', '1', '77', '');
INSERT INTO `order_product` VALUES ('5', '7', '4257', '1', '22', '');
INSERT INTO `order_product` VALUES ('6', '7', '4258', '1', '33', '');
INSERT INTO `order_product` VALUES ('7', '8', '4261', '1', null, '');
INSERT INTO `order_product` VALUES ('8', '8', '4258', '1', null, '');
INSERT INTO `order_product` VALUES ('9', '9', '4261', '1', null, '');
INSERT INTO `order_product` VALUES ('10', '9', '4258', '1', null, '');
INSERT INTO `order_product` VALUES ('11', '13', '4261', '1', null, '');
INSERT INTO `order_product` VALUES ('12', '13', '4258', '1', null, '');
INSERT INTO `order_product` VALUES ('13', '21', '4265', '1', null, '');
INSERT INTO `order_product` VALUES ('14', '21', '4258', '1', null, '');
INSERT INTO `order_product` VALUES ('15', '22', '4265', '1', null, '');
INSERT INTO `order_product` VALUES ('17', '24', '4265', '1', null, '');
