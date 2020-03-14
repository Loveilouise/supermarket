/*
Navicat MySQL Data Transfer

Source Server         : MyLocalhost
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : supermarket

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-03-18 11:22:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(255) DEFAULT NULL,
  `passWord` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '000', '13888888888', '0');
INSERT INTO `admin` VALUES ('2', 'testAdmin', '123', '13916456974', '0');
INSERT INTO `admin` VALUES ('3', '111', '111', '139164569741', '1');
INSERT INTO `admin` VALUES ('4', 'adminTest001', '000', '13916456974', '0');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `isDelete` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '酒', '0');
INSERT INTO `category` VALUES ('2', '饮料', '0');
INSERT INTO `category` VALUES ('3', '零食', '0');
INSERT INTO `category` VALUES ('4', '水果', '0');
INSERT INTO `category` VALUES ('5', '香烟', '0');
INSERT INTO `category` VALUES ('6', '蔬菜', '0');

-- ----------------------------
-- Table structure for category_sec
-- ----------------------------
DROP TABLE IF EXISTS `category_sec`;
CREATE TABLE `category_sec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scname` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `isDelete` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8rqto2vlsd7olxp64lkr2pk2u` (`category_id`),
  CONSTRAINT `category_sec_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category_sec
-- ----------------------------
INSERT INTO `category_sec` VALUES ('1', '白酒', '1', '0');
INSERT INTO `category_sec` VALUES ('2', '啤酒', '1', '0');
INSERT INTO `category_sec` VALUES ('3', '碳酸饮料', '2', '0');
INSERT INTO `category_sec` VALUES ('4', '矿泉水', '2', '0');
INSERT INTO `category_sec` VALUES ('5', '其他饮料', '2', '0');
INSERT INTO `category_sec` VALUES ('6', '干果类', '3', '0');
INSERT INTO `category_sec` VALUES ('7', '饼干', '3', '0');
INSERT INTO `category_sec` VALUES ('8', '膨化食品类', '3', '0');
INSERT INTO `category_sec` VALUES ('9', '凉果蜜饯类', '3', '0');
INSERT INTO `category_sec` VALUES ('10', '瓜类', '4', '0');
INSERT INTO `category_sec` VALUES ('11', '柑橘类', '4', '0');
INSERT INTO `category_sec` VALUES ('12', '浆果类', '4', '0');
INSERT INTO `category_sec` VALUES ('13', '仁果类', '4', '0');
INSERT INTO `category_sec` VALUES ('14', '核果类', '4', '0');
INSERT INTO `category_sec` VALUES ('15', '拷烟型', '5', '0');
INSERT INTO `category_sec` VALUES ('16', '雪茄型', '5', '0');
INSERT INTO `category_sec` VALUES ('17', '混合型', '5', '0');
INSERT INTO `category_sec` VALUES ('18', '芹菜', '6', '0');
INSERT INTO `category_sec` VALUES ('19', '韭菜', '6', '0');
INSERT INTO `category_sec` VALUES ('20', '白菜', '6', '0');
INSERT INTO `category_sec` VALUES ('21', '花菜', '6', '0');
INSERT INTO `category_sec` VALUES ('22', '生菜', '6', '0');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total` double(255,0) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '2017-03-17 00:00:00', '1', '11', '10000001489758100444', '0', '1');
INSERT INTO `order` VALUES ('2', '2017-03-17 21:43:15', '0', '8', '10000001489758195349', '0', '1');
INSERT INTO `order` VALUES ('3', '2017-03-17 21:47:03', '0', '12', '10000001489758423795', '0', '1');
INSERT INTO `order` VALUES ('4', '2017-03-17 21:51:28', '3', '15', '10000001489758688612', '0', '1');
INSERT INTO `order` VALUES ('5', '2017-03-17 00:00:00', '3', '0', '10000001489758723853', '0', '1');
INSERT INTO `order` VALUES ('6', '2017-03-17 00:00:00', '1', '32', '10000001489759263428', '0', '1');
INSERT INTO `order` VALUES ('7', '2017-03-17 00:00:00', '2', '40', '10000001489759394539', '1', '1');
INSERT INTO `order` VALUES ('8', '2017-03-17 23:23:40', '0', '84', '10000001489764220367', '0', '1');
INSERT INTO `order` VALUES ('9', '2017-03-17 23:24:30', '0', '0', '10000001489764270089', '0', '1');
INSERT INTO `order` VALUES ('10', '2017-03-17 00:05:43', '1', '0', '10000001489764283206', '0', '1');
INSERT INTO `order` VALUES ('11', '2017-03-17 00:45:09', '1', '70', '10000001489764369653', '0', '1');
INSERT INTO `order` VALUES ('12', '2017-03-18 01:27:16', '1', '640', '10000001489764436630', '0', '1');
INSERT INTO `order` VALUES ('13', '2017-03-18 10:33:59', '2', '61', '10000001489804439249', '0', '2');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double(255,0) DEFAULT NULL,
  `orderId` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('1', '1', '11', '1', '62');
INSERT INTO `orderitem` VALUES ('2', '1', '8', '2', '59');
INSERT INTO `orderitem` VALUES ('3', '1', '12', '3', '57');
INSERT INTO `orderitem` VALUES ('4', '1', '15', '4', '61');
INSERT INTO `orderitem` VALUES ('5', '2', '32', '6', '44');
INSERT INTO `orderitem` VALUES ('7', '4', '84', '8', '43');
INSERT INTO `orderitem` VALUES ('8', '10', '70', '11', '58');
INSERT INTO `orderitem` VALUES ('9', '80', '640', '12', '5');
INSERT INTO `orderitem` VALUES ('10', '1', '40', '13', '45');
INSERT INTO `orderitem` VALUES ('11', '1', '21', '13', '43');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `categorySecId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '\\upload\\175b7510a81811e651eddf8ba2d107692.jpg', '0', '海之蓝', '100', '这个酒不错哦', '1');
INSERT INTO `product` VALUES ('2', '\\upload\\587a4b70a81811e651eddf8ba2d107693.jpg', '0', '天之蓝', '200', '此商品不错', '1');
INSERT INTO `product` VALUES ('3', '\\upload\\67377cf0a81811e651eddf8ba2d107691.jpg', '0', '梦之蓝', '400', '此商品不错', '1');
INSERT INTO `product` VALUES ('4', '\\upload\\e1eebda0a81811e651eddf8ba2d107694.jpg', '0', '雪花啤酒', '8', '此商品不错', '2');
INSERT INTO `product` VALUES ('5', '\\upload\\f3148ab0a81811e651eddf8ba2d107695.jpg', '0', '纯生啤酒', '8', '此商品不错', '2');
INSERT INTO `product` VALUES ('6', '\\upload\\00613c40a81911e651eddf8ba2d107696.jpg', '0', '大富豪啤酒', '10', '此商品不错', '2');
INSERT INTO `product` VALUES ('7', '\\upload\\bece78f0a81911e651eddf8ba2d107697.jpg', '0', '红牛', '8', '此商品不错', '5');
INSERT INTO `product` VALUES ('8', '\\upload\\e2ab8880a81911e651eddf8ba2d107698.jpg', '0', '可乐', '5', '此商品不错', '3');
INSERT INTO `product` VALUES ('9', '\\upload\\f3691de0a81911e651eddf8ba2d107699.jpg', '0', '冰红茶', '4', '此商品不错', '5');
INSERT INTO `product` VALUES ('10', '\\upload\\fffcdfb0a81911e651eddf8ba2d1076910.jpg', '0', '矿泉水', '3', '此商品不错', '4');
INSERT INTO `product` VALUES ('11', '\\upload\\0ba1ada0a81a11e651eddf8ba2d1076911.jpg', '0', '脉动', '6', '此商品不错', '5');
INSERT INTO `product` VALUES ('12', '\\upload\\7366aac0a87f11e64657b968ca66631a24.jpg', '0', '花生', '10', '此商品不错', '6');
INSERT INTO `product` VALUES ('13', '\\upload\\8de84020a87f11e64657b968ca66631a25.jpg', '0', '瓜子', '8', '此商品不错', '6');
INSERT INTO `product` VALUES ('14', '\\upload\\9d24dad0a87f11e64657b968ca66631a26.jpg', '0', '开心果', '20', '此商品不错', '6');
INSERT INTO `product` VALUES ('15', '\\upload\\c3807ef0a87f11e64657b968ca66631a27.jpg', '0', '话梅', '15', '此商品不错', '9');
INSERT INTO `product` VALUES ('16', '\\upload\\d3754610a87f11e64657b968ca66631a28.jpg', '0', '果脯', '15', '此商品不错', '9');
INSERT INTO `product` VALUES ('17', '\\upload\\0bcddd60a88011e64657b968ca66631a29.jpg', '0', '虾条', '12', '此商品不错', '8');
INSERT INTO `product` VALUES ('18', '\\upload\\1ae16c40a88011e64657b968ca66631a30.jpg', '0', '薯片', '14', '此商品不错', '8');
INSERT INTO `product` VALUES ('19', '\\upload\\29dba6c0a88011e64657b968ca66631a31.jpg', '0', '爆米花', '20', '此商品不错', '8');
INSERT INTO `product` VALUES ('20', '\\upload\\3fa02940a88011e64657b968ca66631a32.jpg', '0', '饼干', '16', '此商品不错', '7');
INSERT INTO `product` VALUES ('21', '\\upload\\4fafcb60a88011e64657b968ca66631a33.jpg', '0', '压缩饼干', '24', '此商品不错', '7');
INSERT INTO `product` VALUES ('22', '\\upload\\648e5ab0a88011e64657b968ca66631a12.jpg', '0', '西瓜', '20', '此商品不错', '10');
INSERT INTO `product` VALUES ('23', '\\upload\\716f0400a88011e64657b968ca66631a13.jpg', '0', '哈密瓜', '24', '此商品不错', '10');
INSERT INTO `product` VALUES ('24', '\\upload\\8227b760a88011e64657b968ca66631a14.jpg', '0', '桔子', '14', '此商品不错', '11');
INSERT INTO `product` VALUES ('25', '\\upload\\9a22c8f0a88011e64657b968ca66631a15.jpg', '0', '橙子', '16', '此商品不错', '11');
INSERT INTO `product` VALUES ('26', '\\upload\\b3b76640a88011e64657b968ca66631a16.jpg', '0', '葡萄', '20', '此商品不错', '12');
INSERT INTO `product` VALUES ('27', '\\upload\\c0f39d10a88011e64657b968ca66631a17.jpg', '0', '草莓', '22', '此商品不错', '12');
INSERT INTO `product` VALUES ('28', '\\upload\\c9e72db0a88011e64657b968ca66631a18.jpg', '0', '香蕉', '12', '此商品不错', '12');
INSERT INTO `product` VALUES ('29', '\\upload\\ddef3320a88011e64657b968ca66631a19.jpg', '0', '苹果', '12', '此商品不错', '13');
INSERT INTO `product` VALUES ('30', '\\upload\\ebb9ee50a88011e64657b968ca66631a20.jpg', '0', '梨', '10', '此商品不错', '13');
INSERT INTO `product` VALUES ('31', '\\upload\\0f69d360a88111e64657b968ca66631a21.jpg', '0', '桃', '14', '此商品不错', '14');
INSERT INTO `product` VALUES ('32', '\\upload\\1a1c2b00a88111e64657b968ca66631a22.jpg', '0', '樱桃', '12', '此商品不错', '14');
INSERT INTO `product` VALUES ('33', '\\upload\\372bbe40a88111e64657b968ca66631a23.jpg', '0', '梅子', '15', '此商品不错', '14');
INSERT INTO `product` VALUES ('34', '\\upload\\dcf298c0a88211e64657b968ca66631a34.jpg', '0', '长白山', '15', '此商品不错', '15');
INSERT INTO `product` VALUES ('35', '\\upload\\ea49f8b0a88211e64657b968ca66631a35.jpg', '0', '黄鹤楼', '12', '此商品不错', '15');
INSERT INTO `product` VALUES ('36', '\\upload\\02c9caa0a88311e64657b968ca66631a36.jpg', '0', '红金龙', '14', '此商品不错', '15');
INSERT INTO `product` VALUES ('37', '\\upload\\3a501dd0a88311e64657b968ca66631a37.jpg', '0', '马坝', '16', '此商品不错', '16');
INSERT INTO `product` VALUES ('38', '\\upload\\46f96370a88311e64657b968ca66631a38.jpg', '0', '希尔顿', '11', '此商品不错', '16');
INSERT INTO `product` VALUES ('39', '\\upload\\67309c80a88311e64657b968ca66631a39.jpg', '0', '中南海', '20', '此商品不错', '17');
INSERT INTO `product` VALUES ('40', '\\upload\\77f9a390a88311e64657b968ca66631a40.jpg', '0', '金桥', '20', '此商品不错', '17');
INSERT INTO `product` VALUES ('41', '\\upload\\8c3e8af0a88311e64657b968ca66631a41.jpg', '0', '紫南京', '15', '此商品不错', '17');
INSERT INTO `product` VALUES ('42', '\\upload\\95c5d010a88311e64657b968ca66631a42.jpg', '0', '红南京', '12', '此商品不错', '17');
INSERT INTO `product` VALUES ('43', '\\upload\\a248c900a88311e64657b968ca66631a43.jpg', '0', '金南京', '21', '此商品不错', '17');
INSERT INTO `product` VALUES ('44', '\\upload\\acd98ee0a88311e64657b968ca66631a44.jpg', '0', '利群', '16', '此商品不错', '17');
INSERT INTO `product` VALUES ('45', '\\upload\\b9bdbaa0a88311e64657b968ca66631a45.jpg', '0', '硬中华', '40', '此商品不错', '17');
INSERT INTO `product` VALUES ('46', '\\upload\\c320d5f0a88311e64657b968ca66631a46.jpg', '0', '软中华', '80', '此商品不错', '17');
INSERT INTO `product` VALUES ('47', '\\upload\\cc2ad4c0a88311e64657b968ca66631a47.jpg', '0', '红双喜', '14', '此商品不错', '17');
INSERT INTO `product` VALUES ('54', '\\upload\\5.jpg', '0', '花菜', '9', '此商品不错', '21');
INSERT INTO `product` VALUES ('55', '\\upload\\5.jpg', '0', '生菜', '8', '此商品不错', '22');
INSERT INTO `product` VALUES ('56', '\\upload\\4.jpg', '0', '花菜', '10', '此商品不错', '21');
INSERT INTO `product` VALUES ('57', '\\upload\\4.jpg', '0', '花菜', '12', '此商品不错', '21');
INSERT INTO `product` VALUES ('58', '\\upload\\3.jpg', '0', '白菜', '7', '此商品不错', '20');
INSERT INTO `product` VALUES ('59', '\\upload\\3.jpg', '0', '白菜', '8', '此商品不错', '20');
INSERT INTO `product` VALUES ('60', '\\upload\\2.jpg', '0', '韭菜', '14', '此商品不错', '19');
INSERT INTO `product` VALUES ('61', '\\upload\\2.jpg', '0', '韭菜', '15', '此商品不错', '19');
INSERT INTO `product` VALUES ('62', '\\upload\\1.jpg', '0', '芹菜', '11', '此商品不错', '18');
INSERT INTO `product` VALUES ('63', '\\upload\\1.jpg', '0', '芹菜', '10', '此商品不错', '18');
INSERT INTO `product` VALUES ('64', '\\upload\\6.jpg', '0', '韭菜', '5', '商品不错呢', '19');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `isVip` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `PassWord` varchar(255) DEFAULT NULL,
  `jf` int(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `level` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zs', '张三', '1', '13916456974', '0', '1', '000', '200', '江苏省', '2');
INSERT INTO `user` VALUES ('2', 'test001', '测试001', '1', '139164569741', '0', '1', '000', '6', '江苏', '0');
