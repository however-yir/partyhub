/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : djxt

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 13/04/2024 21:49:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_branch
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch`;
CREATE TABLE `sys_branch`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `partybranchname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '党支部名称',
  `establishtime` date NULL DEFAULT NULL COMMENT '成立时间',
  `partynumber` int(0) NULL DEFAULT NULL COMMENT '党员数量',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '所属学院id',
  `dept_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '所属学院',
  `branchuser_id` int(0) NULL DEFAULT NULL COMMENT '党支部管理员id',
  `branchuser_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部管理员姓名',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注,党支部类型(老师，学生)',
  `branch_secretary_id` int(0) NULL DEFAULT NULL COMMENT '党支部书记id',
  `branch_secretary_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部书记姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '党支部信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_branch
-- ----------------------------
INSERT INTO `sys_branch` VALUES (1, '女子学院党委', '2021-11-11', 0, 100, '女子学院', NULL, '', '0', 'admin', '2023-05-11 15:48:52', 'admin', '2023-05-12 11:14:08', '老师', NULL, '');

-- ----------------------------
-- Table structure for sys_branch_star
-- ----------------------------
DROP TABLE IF EXISTS `sys_branch_star`;
CREATE TABLE `sys_branch_star`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `sodsacs` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '申报单位ID党支部ID',
  `partybranchname` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '党支部名称',
  `establishtime` date NULL DEFAULT NULL COMMENT '成立时间',
  `partynumber` int(0) NULL DEFAULT NULL COMMENT '党员数量',
  `dept_id` int(0) NULL DEFAULT NULL COMMENT '所属学院id',
  `dept_name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '所属学院',
  `branchuser_id` int(0) NULL DEFAULT NULL COMMENT '党支部管理员id',
  `branchuser_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部管理员姓名',
  `branch_secretary_id` int(0) NULL DEFAULT NULL COMMENT '党支部书记id',
  `branch_secretary_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部书记姓名',
  `self_star` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '申报星级',
  `context` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '支部建设主要特色和成效',
  `foundationitem` int(0) NULL DEFAULT NULL COMMENT '基础项得分',
  `pluses` int(0) NULL DEFAULT NULL COMMENT '加分项得分',
  `minuses` int(0) NULL DEFAULT NULL COMMENT '减分项得分',
  `score` int(0) NULL DEFAULT NULL COMMENT '总分',
  `starrating` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '星级审定',
  `outcome` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '初评结果',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `organizationcomment` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '学校党委组织部意见',
  `comments` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '学校党委意见',
  `process` int(0) NULL DEFAULT NULL COMMENT '流程节点（1：支部自评，2：评分专家评分，3：管理员审核，4：评分完成）',
  `sb_year` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '申报年份',
  `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '支撑材料名称',
  `file_path` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '支撑材料路径',
  `ygly_id` int(0) NULL DEFAULT NULL COMMENT '院管理员审批人ID',
  `ygly_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '院管理员审批人姓名',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注  备注,党支部类型(老师，学生)',
  `commentitems` int(0) NULL DEFAULT NULL COMMENT '评议项得分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '党支部星级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_branch_star
-- ----------------------------
INSERT INTO `sys_branch_star` VALUES (26, '1', '女子学院党委', '2021-11-11', 0, 100, '女子学院', NULL, NULL, NULL, NULL, '五星', '特色人很对', 30, 20, 10, 40, '三星', '还好', '0', '一般', '一般般', 4, '2024', '申报材料', '/file.txt', NULL, NULL, '', '2024-04-04 15:47:50', '', NULL, NULL, 10);
INSERT INTO `sys_branch_star` VALUES (27, '1', '女子学院党委', '2021-11-11', 0, 100, '女子学院', NULL, NULL, NULL, NULL, '五星', '特色人很对', 30, 20, 10, 40, '三星', '还好', '0', '一般', '一般般', 3, '2024', '申报材料', '/file.txt', NULL, NULL, '', '2024-04-04 15:47:50', '', NULL, NULL, 10);

-- ----------------------------
-- Table structure for sys_committee_star
-- ----------------------------
DROP TABLE IF EXISTS `sys_committee_star`;
CREATE TABLE `sys_committee_star`  (
  `star_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号id',
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门ID',
  `star_year` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '任期年度批次',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark1` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注1',
  `remark2` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注2',
  `remark3` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`star_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '评星定级名册委员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_committee_star
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '女子学院', 0, '', '', '', '0', '0', 'admin', '2023-05-11 15:48:52', 'admin', '2023-05-12 11:14:08');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(0) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-05-11 15:48:52', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-05-11 15:48:52', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (4, 1, '本科', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-05-11 15:48:53', '', NULL, '学历');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2023-05-11 15:48:52', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '用户学历', 'sys_show_hide', '0', 'admin', '2023-05-11 15:48:52', '', NULL, '用户学历列表');

-- ----------------------------
-- Table structure for sys_feedback
-- ----------------------------
DROP TABLE IF EXISTS `sys_feedback`;
CREATE TABLE `sys_feedback`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '反馈ID',
  `messageid` int(0) NULL DEFAULT NULL COMMENT '通知ID',
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '反馈内容',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '反馈评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `file_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号id',
  `branch_id` int(0) NULL DEFAULT NULL COMMENT '党支部id',
  `branch_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部名称',
  `type_id` int(0) NULL DEFAULT NULL COMMENT '一级材料id',
  `type_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '一级材料名称',
  `classify_id` int(0) NULL DEFAULT NULL COMMENT '二级材料id',
  `classify_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '二级材料名称',
  `title` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '标题',
  `content` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '简介',
  `file_path` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '文件路径',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark1` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注1-活动时间',
  `remark2` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注2-评星支部ID',
  `remark3` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '支撑材料信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(0) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(0) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '菜单权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2023-05-11 15:48:52', '', NULL, '系统管理目录');

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `message_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `message_title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '通知标题',
  `message_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '通知下发标志（0未下发 1已下发,2结束）',
  `message_content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '通知内容',
  `start_date` date NULL DEFAULT NULL COMMENT '开始日期',
  `end_date` date NULL DEFAULT NULL COMMENT '结束日期',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '通知状态（0未读 1已读）',
  `receive_user_id` int(0) NULL DEFAULT NULL COMMENT '通知接收人id',
  `receive_user_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '通知接收人',
  `message_file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '通知附件名称',
  `message_file_path` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '通知附件路径',
  `message_level` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '节点',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注--评星批次',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '评星通知表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_message
-- ----------------------------

-- ----------------------------
-- Table structure for sys_news
-- ----------------------------
DROP TABLE IF EXISTS `sys_news`;
CREATE TABLE `sys_news`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '新闻标题',
  `type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '新闻类型',
  `notice_content` longblob NULL COMMENT '新闻内容',
  `url` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '新闻地址',
  `img_path` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '图片地址',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '党建要闻表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_news
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(0) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '岗位信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '辅导员', 1, '0', 'admin', '2023-05-11 15:48:52', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '教师', 2, '0', 'admin', '2023-05-11 15:48:52', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2023-05-11 15:48:52', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(0) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '角色信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '数据管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2023-05-11 15:48:52', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '院管理员', 'ygly', 2, '3', 1, 1, '0', '0', 'admin', '2023-05-11 15:48:52', 'admin', '2023-05-25 15:12:28', '普通角色');
INSERT INTO `sys_role` VALUES (3, '党支部管理员', 'dzbgly', 3, '4', 1, 1, '0', '0', 'admin', '2023-05-12 11:51:50', 'admin', '2023-05-25 09:49:01', NULL);
INSERT INTO `sys_role` VALUES (4, '专家评委', 'dzbgly', 3, '4', 1, 1, '0', '0', 'admin', '2023-05-12 11:51:50', 'admin', '2023-05-25 09:49:01', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(0) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_score_stand
-- ----------------------------
DROP TABLE IF EXISTS `sys_score_stand`;
CREATE TABLE `sys_score_stand`  (
  `score_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '评分项id',
  `parent_id` bigint(0) NULL DEFAULT 0 COMMENT '父评分项id',
  `stand_type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '标准分类(老师，学生)',
  `stand_title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '标准名称',
  `score_num` int(0) NULL DEFAULT 0 COMMENT '分值',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark1` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注1  所属评星年度',
  `remark2` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注2  活动日期',
  `remark3` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`score_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '评分标准表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_score_stand
-- ----------------------------
INSERT INTO `sys_score_stand` VALUES (1, 0, '老师评分标准', '主体作用发挥-30分', 30, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (2, 1, '老师评分标准', '政治引领-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (3, 1, '老师评分标准', '规范党的组织生活-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (4, 1, '老师评分标准', '组织宣传凝聚服务师生-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (5, 1, '老师评分标准', '促进中心工作-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (6, 0, '老师评分标准', '班子和制度建设-25分', 25, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (7, 6, '老师评分标准', '支部班子建设-15分', 15, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (8, 6, '老师评分标准', '制度建设-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (9, 0, '老师评分标准', '发展党员工作-25分', 25, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (10, 9, '老师评分标准', '发展原则-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (11, 9, '老师评分标准', '发展重点-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (12, 9, '老师评分标准', '发展质量-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (13, 9, '老师评分标准', '发展程序-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (14, 0, '老师评分标准', '党员教育管理监督服务和作用发挥-50分', 50, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (15, 14, '老师评分标准', '党员教育-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (16, 14, '老师评分标准', '党员管理监督-15分', 15, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (17, 14, '老师评分标准', '党建信息化建设-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (18, 14, '老师评分标准', '党员服务-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (19, 14, '老师评分标准', '党员作用发挥-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (20, 0, '老师评分标准', '党费收缴、使用与管理-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (21, 20, '老师评分标准', '党费收缴-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (22, 20, '老师评分标准', '党费使用管理-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (23, 0, '老师评分标准', '党内民主-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (24, 23, '老师评分标准', '保障党员民主权利-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (25, 23, '老师评分标准', '推进党务公开-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (26, 0, '学生评分标准', '组织和制度建设-35分', 35, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (27, 26, '学生评分标准', '支部班子建设-15分', 15, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (28, 26, '学生评分标准', '制度建设-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (29, 26, '学生评分标准', '作用发挥-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (30, 0, '学生评分标准', '发展党员工作-40分', 40, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (31, 30, '学生评分标准', '发展原则-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (32, 30, '学生评分标准', '发展重点-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (33, 30, '学生评分标准', '发展质量-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (34, 30, '学生评分标准', '发展程序-25分', 25, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (35, 0, '学生评分标准', '党员教育管理监督服务与作用-55分', 55, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (36, 35, '学生评分标准', '党员教育-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (37, 35, '学生评分标准', '党员管理监督-20分', 20, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (38, 35, '学生评分标准', '党建信息化建设-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (39, 35, '学生评分标准', '党员作用发挥-20分', 20, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (40, 0, '学生评分标准', '党费收缴、使用与管理-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (41, 40, '学生评分标准', '党费收缴-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (42, 40, '学生评分标准', '党费使用管理-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (43, 0, '学生评分标准', '发展党内民主-10分', 10, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (44, 43, '学生评分标准', '保障党员民主权利-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_score_stand` VALUES (45, 43, '学生评分标准', '推进党务公开-5分', 5, '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_star_score
-- ----------------------------
DROP TABLE IF EXISTS `sys_star_score`;
CREATE TABLE `sys_star_score`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '评分人编号',
  `branch_star_id` int(0) NULL DEFAULT NULL COMMENT '评星定级id',
  `branch_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部名称',
  `score_stand_id` int(0) NULL DEFAULT NULL COMMENT '评分项目标准ID',
  `score` int(0) NULL DEFAULT NULL COMMENT '得分',
  `score_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '分值类别(基础得分，加分项目，减分项目，评议项目)',
  `score_answer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '得分原因',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark1` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注1',
  `remark2` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注2',
  `remark3` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注3',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_star_score
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(0) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(14) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `idcard` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '身份证号',
  `enterpartytime` date NULL DEFAULT NULL COMMENT '入党时间',
  `origin` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '籍贯',
  `avatar` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '密码',
  `branch_id` int(0) NULL DEFAULT NULL COMMENT '党支部id',
  `branch_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '党支部名称',
  `post_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '岗位ID',
  `post_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `branch_star_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 100, 'admin', '管理员', '00', 'xiaoguanliyuan@163.com', '13344444444', '1', '370481111111111111', '2023-05-01', NULL, '', '123456', NULL, '', NULL, NULL, '0', '0', '127.0.0.1', '2023-12-02 17:06:49', 'admin', '2023-05-25 09:25:43', '', '2023-12-02 17:06:49', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(0) NOT NULL COMMENT '用户ID',
  `role_id` bigint(0) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_unicode_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
