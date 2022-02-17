/*
 Navicat Premium Data Transfer

 Source Server         : ljg
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 139.196.255.97:3306
 Source Schema         : qiyuesuo_test

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 17/02/2022 14:59:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuidName` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `oldName` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `size` bigint(20) NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (46, '1b4a5784-d038-407f-908c-82ded2fe6a12', '1.docx', 'D:\\20220217\\1b4a5784-d038-407f-908c-82ded2fe6a12.docx', '.docx', 0, '2022-02-17');
INSERT INTO `file` VALUES (49, 'bb0c2022-c2b4-4cf5-a6c3-71fc0c1c56ae', 'sync.svg', 'D:\\20220217\\bb0c2022-c2b4-4cf5-a6c3-71fc0c1c56ae.svg', '.svg', 2805, '2022-02-17');

SET FOREIGN_KEY_CHECKS = 1;
