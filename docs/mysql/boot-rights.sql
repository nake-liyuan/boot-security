/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : boot-rights

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 18/05/2023 13:45:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父级ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `level` int(4) NOT NULL COMMENT '菜单级数',
  `sort` int(4) NOT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '前端名称',
  `icon` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '前端图标',
  `hidden` tinyint(1) NOT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `category_id` bigint(20) NOT NULL COMMENT '资源分类ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源URL',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, 1, '后台用户管理', '/user/**', '后台用户管理');
INSERT INTO `resource` VALUES (2, 1, '后台用户角色管理', '/role/**', '后台用户角色管理');
INSERT INTO `resource` VALUES (3, 1, '后台菜单管理', '/menu/**', '后台用户角色管理');
INSERT INTO `resource` VALUES (4, 1, '后台资源分类管理', '/resourceCategory/**', '后台资源分类管理');
INSERT INTO `resource` VALUES (5, 1, '后台资源管理', '/resource/**', '后台资源管理');

-- ----------------------------
-- Table structure for resource_category
-- ----------------------------
DROP TABLE IF EXISTS `resource_category`;
CREATE TABLE `resource_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_category
-- ----------------------------
INSERT INTO `resource_category` VALUES (1, '权限模块', 0);

-- ----------------------------
-- Table structure for resource_role
-- ----------------------------
DROP TABLE IF EXISTS `resource_role`;
CREATE TABLE `resource_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `resource_role_id`(`resource_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `resource_id`(`resource_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_role
-- ----------------------------
INSERT INTO `resource_role` VALUES (1, 1, 1);
INSERT INTO `resource_role` VALUES (6, 1, 2);
INSERT INTO `resource_role` VALUES (2, 2, 1);
INSERT INTO `resource_role` VALUES (3, 3, 1);
INSERT INTO `resource_role` VALUES (4, 4, 1);
INSERT INTO `resource_role` VALUES (5, 5, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `user_count` int(11) NOT NULL COMMENT '后台用户数量',
  `enabled` tinyint(1) NOT NULL COMMENT '启用',
  `sort` int(11) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '系统管理员', '拥有全部资源', 1, 0, 1);
INSERT INTO `role` VALUES (2, '普通用户', '拥有普通用户资源', 1, 0, 2);
INSERT INTO `role` VALUES (3, '游客', '拥有游客资源', 1, 0, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `avatar` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `enabled` tinyint(1) NOT NULL COMMENT '启用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `userName`(`user_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$YF7/ttyxm9/BayenG3S1VOCFJx9wpuL8lK445PNS4pOxkfaDiYN7a', 'http://114.116.114.181:9000/exam-file/headPortrait/admin.png', 0);
INSERT INTO `user` VALUES (2, 'user', '$2a$10$YF7/ttyxm9/BayenG3S1VOCFJx9wpuL8lK445PNS4pOxkfaDiYN7a', 'http://114.116.114.181:9000/exam-file/tmp_571460fb14744714dcf2ce4a8020f55acc0d406afc9ac89d.jpg', 0);
INSERT INTO `user` VALUES (3, 'blr', '$2a$10$HFoq4HxzM/q8zThgJ9QpLeQX6irtJNBmTpLgQGjM4RI5Gg8tgKYxa', 'http://114.116.114.181:9000/exam-file/tmp_f9a442111817981a7c55f3c59de302d1.jpg', 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_role_id`(`user_id` ASC, `role_id` ASC) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (4, 1, 2);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
