/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : bkmkdb

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 06/10/2020 16:07:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
-- we don't know how to generate schema bkmkdb (class Schema) :(
create table book
(
  id          int auto_increment
    primary key,
  name        varchar(100) null
  comment '书名',
  price       float(10, 2) null
  comment '价格',
  author      varchar(100) null
  comment '作者',
  publisher   varchar(100) null
  comment '出版社',
  page_number int          null
  comment '页数',
  type        tinyint      null
  comment '类型',
  process     int          null
  comment '进度（%）',
  remark      varchar(255) null
  comment '备注',
  create_time datetime(6)  null
  comment '创建时间',
  create_user varchar(100) null
  comment '创建用户'
)
  charset = utf8mb4;

create table schedule
(
  id              int auto_increment
    primary key,
  first_dead_line datetime(6)  null
  comment '结束时间',
  is_circle       tinyint      null
  comment '是否循环',
  level           int          null
  comment '级别',
  schedule_num    int          null
  comment '循环数',
  schedule_type   int          null
  comment '计划类型',
  remark          varchar(255) null
  comment '备注',
  create_time     datetime(6)  null
  comment '创建时间',
  title           varchar(100) null
  comment '标题',
  user_name       varchar(100) null
  comment '创建用户'
)
  charset = utf8mb4;

create table schedule_record
(
  id          int auto_increment
    primary key,
  create_time datetime(6)  null
  comment '创建时间',
  dead_line   datetime(6)  null
  comment '结束时间',
  state       tinyint      null
  comment '状态',
  update_time datetime(6)  null
  comment '更新时间',
  schedule_id int          null
  comment '计划id',
  user_id     int          null
  comment '用户id',
  remark      varchar(255) null
  comment '备注'
)
  charset = utf8mb4;


SET FOREIGN_KEY_CHECKS = 1;
