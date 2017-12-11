/*
MySQL Data Transfer
Source Host: localhost
Source Database: javaweb
Target Host: localhost
Target Database: javaweb
Date: 2017/12/11 13:23:02
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `controller` varchar(255) NOT NULL,
  `method` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `carTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `carTpyeId` (`carTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=878 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car_product
-- ----------------------------
DROP TABLE IF EXISTS `car_product`;
CREATE TABLE `car_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carId` int(11) NOT NULL,
  `productId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cartype
-- ----------------------------
DROP TABLE IF EXISTS `cartype`;
CREATE TABLE `cartype` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `telephone` varchar(32) DEFAULT NULL,
  `telephone2` varchar(32) DEFAULT NULL,
  `area` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `info` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22880 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `num` int(11) NOT NULL,
  `version` varchar(128) DEFAULT NULL,
  `ownerprice` int(10) DEFAULT NULL,
  `otherprice` int(10) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `carstr` varchar(1024) NOT NULL,
  `display` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4262 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL,
  `password` varchar(256) NOT NULL,
  `registerDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_authority
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `userId` int(11) NOT NULL,
  `authorityId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`authorityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `authority` VALUES ('1', '添加汽车品牌', 'CarTypeController', 'delete');
INSERT INTO `authority` VALUES ('2', '删除汽车品牌', 'CarTypeController', 'add');
INSERT INTO `authority` VALUES ('5', '添加车型', 'CarController', 'add');
INSERT INTO `authority` VALUES ('6', '删除车型', 'CarController', 'delete');
INSERT INTO `authority` VALUES ('7', '去往商品更新页面', 'ProductController', 'toUpdatePage');
INSERT INTO `authority` VALUES ('8', '删除商品', 'ProductController', 'del');
INSERT INTO `authority` VALUES ('9', '去往商品添加页面', 'ProductController', 'toAddPage');
INSERT INTO `authority` VALUES ('10', 'ajax商品数量加一', 'ProductAjaxController', 'addNum');
INSERT INTO `authority` VALUES ('11', 'ajax商品数量减一', 'ProductAjaxController', 'subNum');
INSERT INTO `authority` VALUES ('14', '添加商品', 'ProductController', 'add');
INSERT INTO `authority` VALUES ('15', '更新商品', 'ProductController', 'update');
INSERT INTO `authority` VALUES ('16', '快速添加商品', 'ProductController', 'quickadd');
INSERT INTO `authority` VALUES ('17', '去往快速商品添加页面', 'ProductController', 'toQuickAddPage');
INSERT INTO `authority` VALUES ('18', '去往商品搜索商品更新页面', 'ProductSearchController', 'toUpdatePage');
INSERT INTO `authority` VALUES ('19', 'ajax商品更新', 'ProductAjaxController', 'update');
INSERT INTO `authority` VALUES ('20', 'ajax更新删除', 'ProductAjaxController', 'del');
INSERT INTO `car` VALUES ('875', '12朗逸', '121');
INSERT INTO `car` VALUES ('876', '13朗逸', '121');
INSERT INTO `car` VALUES ('877', '17朗逸', '121');
INSERT INTO `car_product` VALUES ('12', '875', '4258');
INSERT INTO `car_product` VALUES ('19', '875', '4261');
INSERT INTO `car_product` VALUES ('20', '876', '4261');
INSERT INTO `car_product` VALUES ('21', '877', '4261');
INSERT INTO `cartype` VALUES ('121', '大众');
INSERT INTO `customer` VALUES ('1', '个人', '', '', '', '');
INSERT INTO `customer` VALUES ('4', '张思', '', '', '', '');
INSERT INTO `customer` VALUES ('5', '张第三方', '', '', '', '');
INSERT INTO `customer` VALUES ('6', '张发生的', '', '', '', '');
INSERT INTO `customer` VALUES ('7', '方式张', '', '', '', '');
INSERT INTO `customer` VALUES ('8', '士张大夫', '', '', '', '');
INSERT INTO `order_` VALUES ('5', '1', 'asdfe44444,发生的3', null, '佛挡杀佛刷单士大夫', '', '2017-12-10 21:37:05');
INSERT INTO `order_` VALUES ('6', '4', 'asdfe44444,发生的3', null, '刚发的感受到', '', '2017-12-10 21:37:03');
INSERT INTO `order_` VALUES ('7', '8', 'asdfe44444,发生的3', null, '1111111111111111', '', '2017-12-10 21:37:02');
INSERT INTO `order_` VALUES ('8', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-10 21:37:01');
INSERT INTO `order_` VALUES ('9', '1', 'asdfe44444,发生的3', null, null, '', '2017-12-10 21:33:45');
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
INSERT INTO `product` VALUES ('4243', '多对多', '5', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4244', '多对多', '5', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4245', '多对多', '3', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4246', '多对多', '3', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4247', '多对多', '7', '', '1', '4', '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4248', '多对多', '7', 'aaaa', '1', '4', '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4249', '多对多', '7', 'aaaa', '1', '4', 'sdf', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4250', '多对多4', '7', 'aaaa', '1', '4', 'sdf', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4251', '多对多4', '7', 'aaaa', '1', '4', 'sdf', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4252', '多对多4', '7', 'aaaa', '1', '4', 'sdf', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4253', '发斯蒂芬', '34', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4254', '发斯蒂芬', '34', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4255', 'sadfsdf', '33', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4256', 'sdfs', '4', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4257', 'asdfewf', '4', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4258', '发生的3', '10', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4259', 'asdfewf', '4', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4260', 'asdfe', '4', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `product` VALUES ('4261', 'asdfe44444', '4', '', null, null, '', '[{\"id\":875,\"name\":\"12朗逸\",\"carTypeId\":121},{\"id\":876,\"name\":\"13朗逸\",\"carTypeId\":121},{\"id\":877,\"name\":\"17朗逸\",\"carTypeId\":121}]', '');
INSERT INTO `user` VALUES ('1', 'shihu', '9358242E5818783E2EA6A692A5134683', '2017-01-10 13:39:51');
INSERT INTO `user` VALUES ('4', 'xy', '3E44107170A520582ADE522FA73C1D15', '2017-01-10 13:43:37');
INSERT INTO `user_authority` VALUES ('1', '1');
INSERT INTO `user_authority` VALUES ('1', '2');
INSERT INTO `user_authority` VALUES ('1', '5');
INSERT INTO `user_authority` VALUES ('1', '6');
INSERT INTO `user_authority` VALUES ('1', '7');
INSERT INTO `user_authority` VALUES ('1', '8');
INSERT INTO `user_authority` VALUES ('1', '9');
INSERT INTO `user_authority` VALUES ('1', '10');
INSERT INTO `user_authority` VALUES ('1', '11');
INSERT INTO `user_authority` VALUES ('1', '12');
INSERT INTO `user_authority` VALUES ('1', '14');
INSERT INTO `user_authority` VALUES ('1', '15');
INSERT INTO `user_authority` VALUES ('1', '16');
INSERT INTO `user_authority` VALUES ('1', '17');
INSERT INTO `user_authority` VALUES ('1', '18');
INSERT INTO `user_authority` VALUES ('1', '19');
INSERT INTO `user_authority` VALUES ('1', '20');
INSERT INTO `user_authority` VALUES ('4', '1');
INSERT INTO `user_authority` VALUES ('4', '2');
INSERT INTO `user_authority` VALUES ('4', '5');
INSERT INTO `user_authority` VALUES ('4', '6');
INSERT INTO `user_authority` VALUES ('4', '7');
INSERT INTO `user_authority` VALUES ('4', '8');
INSERT INTO `user_authority` VALUES ('4', '9');
INSERT INTO `user_authority` VALUES ('4', '10');
INSERT INTO `user_authority` VALUES ('4', '11');
INSERT INTO `user_authority` VALUES ('4', '14');
INSERT INTO `user_authority` VALUES ('4', '15');
INSERT INTO `user_authority` VALUES ('4', '16');
INSERT INTO `user_authority` VALUES ('4', '17');
