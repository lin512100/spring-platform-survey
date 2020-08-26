/*
 Navicat MySQL Data Transfer

 Source Server         : 129.204.1.173
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 129.204.1.173:3306
 Source Schema         : platform-account

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 21/08/2020 11:14:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for platform_menu
-- ----------------------------
DROP TABLE IF EXISTS `platform_menu`;
CREATE TABLE `platform_menu`  (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `pid` bigint(255) NOT NULL DEFAULT 0 COMMENT '父菜单ID',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(1) NULL DEFAULT 0 COMMENT '是否是菜单',
  `server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属于什么服务',
  `level` int(11) NULL DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) NULL DEFAULT NULL COMMENT '菜单排序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单状态',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拦截方法名',
  `is_show` int(2) NULL DEFAULT 0 COMMENT '是否展示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 891 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_menu
-- ----------------------------
INSERT INTO `platform_menu` VALUES (1, NULL, 0, '系统管理', '/account', 1, NULL, NULL, 5, '1', 'el-icon-lx-calendar', '2020-07-09 19:15:31', '2020-07-09 19:15:29', NULL, 1);
INSERT INTO `platform_menu` VALUES (2, NULL, 1, '用户管理', '/account/user', 1, NULL, NULL, 1, '1', 'el-icon-lx-calendar', '2020-07-09 11:15:23', '2020-07-09 11:15:25', NULL, 1);
INSERT INTO `platform_menu` VALUES (3, NULL, 1, '角色管理', '/account/role', 1, NULL, NULL, 3, '1', 'el-icon-lx-calendar', '2020-07-09 11:11:46', '2020-07-09 11:11:46', NULL, 1);
INSERT INTO `platform_menu` VALUES (5, NULL, 1, '菜单管理', '/account/menu', 1, NULL, NULL, 2, '1', 'el-icon-lx-calendar', '2020-07-08 19:13:14', '2020-07-08 19:13:14', NULL, 1);
INSERT INTO `platform_menu` VALUES (864, NULL, 0, '其他', '/other', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);
INSERT INTO `platform_menu` VALUES (865, NULL, 5, '菜单表添加', '/menu', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'POST', 1);
INSERT INTO `platform_menu` VALUES (866, NULL, 5, '根据ID删除信息', '/menu/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'DELETE', 1);
INSERT INTO `platform_menu` VALUES (867, NULL, 5, '菜单表列表查询', '/menu/list', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (868, NULL, 864, '菜单树结构', '/menu/public/tree/{roleId}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 0);
INSERT INTO `platform_menu` VALUES (869, NULL, 5, '根据ID查询菜单表信息', '/menu/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (870, NULL, 5, '修改菜单表信息', '/menu', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'PUT', 1);
INSERT INTO `platform_menu` VALUES (871, NULL, 3, '角色添加', '/role', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'POST', 1);
INSERT INTO `platform_menu` VALUES (872, NULL, 3, '根据ID删除信息', '/role/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'DELETE', 1);
INSERT INTO `platform_menu` VALUES (873, NULL, 3, '角色列表查询', '/role/list', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (874, NULL, 3, '根据ID查询角色信息', '/role/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (875, NULL, 3, '修改角色信息', '/role', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'PUT', 1);
INSERT INTO `platform_menu` VALUES (876, NULL, 3, '修改信息', '/roleMenu', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'PUT', 1);
INSERT INTO `platform_menu` VALUES (877, NULL, 3, '添加', '/roleMenu', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'POST', 1);
INSERT INTO `platform_menu` VALUES (878, NULL, 3, '根据ID删除信息', '/roleMenu/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'DELETE', 1);
INSERT INTO `platform_menu` VALUES (879, NULL, 3, '列表查询', '/roleMenu/list', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (880, NULL, 3, '根据ID查询信息', '/roleMenu/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (881, NULL, 2, '用户添加', '/user', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'POST', 1);
INSERT INTO `platform_menu` VALUES (882, NULL, 2, '根据ID删除信息', '/user/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'DELETE', 1);
INSERT INTO `platform_menu` VALUES (883, NULL, 2, '用户列表查询', '/user/list', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (884, NULL, 2, '根据ID查询用户信息', '/user/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (885, NULL, 2, '修改用户信息', '/user', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'PUT', 1);
INSERT INTO `platform_menu` VALUES (886, NULL, 2, '修改用户角色信息', '/userRole', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'PUT', 1);
INSERT INTO `platform_menu` VALUES (887, NULL, 2, '用户角色添加', '/userRole', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'POST', 1);
INSERT INTO `platform_menu` VALUES (888, NULL, 2, '根据ID删除信息', '/userRole/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'DELETE', 1);
INSERT INTO `platform_menu` VALUES (889, NULL, 2, '用户角色列表查询', '/userRole/list', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);
INSERT INTO `platform_menu` VALUES (890, NULL, 3, '根据ID查询用户角色信息', '/userRole/{id}', 0, 'account', NULL, NULL, NULL, NULL, NULL, NULL, 'GET', 1);

-- ----------------------------
-- Table structure for platform_role
-- ----------------------------
DROP TABLE IF EXISTS `platform_role`;
CREATE TABLE `platform_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_role
-- ----------------------------
INSERT INTO `platform_role` VALUES (1, '系统管理员', '1000', '最高权限-系统管理员', '2020-07-09 03:05:36', '2020-07-09 03:05:33', '1');
INSERT INTO `platform_role` VALUES (2, '普通用户', '101', '普通用户', '2020-07-25 08:59:18', '2020-07-25 08:59:18', '1');

-- ----------------------------
-- Table structure for platform_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `platform_role_menu`;
CREATE TABLE `platform_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  `operate` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '操作权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 173 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_role_menu
-- ----------------------------
INSERT INTO `platform_role_menu` VALUES (2, 2, 22, '');
INSERT INTO `platform_role_menu` VALUES (3, 3, 10, NULL);
INSERT INTO `platform_role_menu` VALUES (62, 1, 6, NULL);
INSERT INTO `platform_role_menu` VALUES (65, 1, 13, NULL);
INSERT INTO `platform_role_menu` VALUES (69, 1, 7, NULL);
INSERT INTO `platform_role_menu` VALUES (75, 1, 8, NULL);
INSERT INTO `platform_role_menu` VALUES (76, 1, 9, NULL);
INSERT INTO `platform_role_menu` VALUES (77, 1, 10, NULL);
INSERT INTO `platform_role_menu` VALUES (79, 1, 11, NULL);
INSERT INTO `platform_role_menu` VALUES (80, 1, 12, NULL);
INSERT INTO `platform_role_menu` VALUES (81, 1, 14, NULL);
INSERT INTO `platform_role_menu` VALUES (82, 1, 15, NULL);
INSERT INTO `platform_role_menu` VALUES (85, 1, 16, NULL);
INSERT INTO `platform_role_menu` VALUES (86, 1, 17, NULL);
INSERT INTO `platform_role_menu` VALUES (87, 1, 18, NULL);
INSERT INTO `platform_role_menu` VALUES (88, 1, 19, NULL);
INSERT INTO `platform_role_menu` VALUES (89, 1, 20, NULL);
INSERT INTO `platform_role_menu` VALUES (94, 1, 21, NULL);
INSERT INTO `platform_role_menu` VALUES (102, 1, 26, NULL);
INSERT INTO `platform_role_menu` VALUES (107, 1, 27, NULL);
INSERT INTO `platform_role_menu` VALUES (108, 1, 1, NULL);
INSERT INTO `platform_role_menu` VALUES (109, 1, 2, NULL);
INSERT INTO `platform_role_menu` VALUES (112, 1, 3, NULL);
INSERT INTO `platform_role_menu` VALUES (113, 1, 5, NULL);
INSERT INTO `platform_role_menu` VALUES (141, 2, 5, NULL);
INSERT INTO `platform_role_menu` VALUES (142, 2, 865, NULL);
INSERT INTO `platform_role_menu` VALUES (143, 2, 866, NULL);
INSERT INTO `platform_role_menu` VALUES (144, 2, 867, NULL);
INSERT INTO `platform_role_menu` VALUES (145, 2, 869, NULL);
INSERT INTO `platform_role_menu` VALUES (146, 2, 870, NULL);
INSERT INTO `platform_role_menu` VALUES (147, 2, 1, NULL);
INSERT INTO `platform_role_menu` VALUES (148, 1, 881, NULL);
INSERT INTO `platform_role_menu` VALUES (149, 1, 882, NULL);
INSERT INTO `platform_role_menu` VALUES (150, 1, 883, NULL);
INSERT INTO `platform_role_menu` VALUES (151, 1, 884, NULL);
INSERT INTO `platform_role_menu` VALUES (152, 1, 885, NULL);
INSERT INTO `platform_role_menu` VALUES (153, 1, 886, NULL);
INSERT INTO `platform_role_menu` VALUES (154, 1, 887, NULL);
INSERT INTO `platform_role_menu` VALUES (155, 1, 888, NULL);
INSERT INTO `platform_role_menu` VALUES (156, 1, 889, NULL);
INSERT INTO `platform_role_menu` VALUES (157, 1, 871, NULL);
INSERT INTO `platform_role_menu` VALUES (158, 1, 872, NULL);
INSERT INTO `platform_role_menu` VALUES (159, 1, 873, NULL);
INSERT INTO `platform_role_menu` VALUES (160, 1, 874, NULL);
INSERT INTO `platform_role_menu` VALUES (161, 1, 875, NULL);
INSERT INTO `platform_role_menu` VALUES (162, 1, 876, NULL);
INSERT INTO `platform_role_menu` VALUES (163, 1, 877, NULL);
INSERT INTO `platform_role_menu` VALUES (164, 1, 878, NULL);
INSERT INTO `platform_role_menu` VALUES (165, 1, 879, NULL);
INSERT INTO `platform_role_menu` VALUES (166, 1, 880, NULL);
INSERT INTO `platform_role_menu` VALUES (167, 1, 890, NULL);
INSERT INTO `platform_role_menu` VALUES (168, 1, 865, NULL);
INSERT INTO `platform_role_menu` VALUES (169, 1, 866, NULL);
INSERT INTO `platform_role_menu` VALUES (170, 1, 867, NULL);
INSERT INTO `platform_role_menu` VALUES (171, 1, 869, NULL);
INSERT INTO `platform_role_menu` VALUES (172, 1, 870, NULL);

-- ----------------------------
-- Table structure for platform_user
-- ----------------------------
DROP TABLE IF EXISTS `platform_user`;
CREATE TABLE `platform_user`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐值',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `userpic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `identity` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `phone` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `status` int(6) NULL DEFAULT NULL COMMENT '用户状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`, `username`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_user
-- ----------------------------
INSERT INTO `platform_user` VALUES (1, 'jtang', '$2a$10$RoIOYBEJeYYYWw7cIiLzP.9PnJMUyXQ9VdBuMHU8N9t59dvpeviSC', ' 1', '林锦堂', ' 1', '2020-07-13 22:10:02', 0, ' 440221199510071913', NULL, ' 15889301808', 1, '2020-06-23 22:09:52', '2020-06-22 14:09:49');
INSERT INTO `platform_user` VALUES (2, 'admin', '$2a$10$RoIOYBEJeYYYWw7cIiLzP.9PnJMUyXQ9VdBuMHU8N9t59dvpeviSC', '7d533828-e201', NULL, NULL, NULL, 0, '440221199510071913', NULL, '15889301809', 1, '2020-07-16 10:44:00', '2020-07-16 10:44:00');

-- ----------------------------
-- Table structure for platform_user_role
-- ----------------------------
DROP TABLE IF EXISTS `platform_user_role`;
CREATE TABLE `platform_user_role`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL COMMENT '用户ID',
  `role_id` bigint(32) NULL DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of platform_user_role
-- ----------------------------
INSERT INTO `platform_user_role` VALUES (7, 1, 1, NULL, NULL);
INSERT INTO `platform_user_role` VALUES (14, 2, 2, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
