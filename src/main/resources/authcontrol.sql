/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.39 : Database - authcontrol
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`authcontrol` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `authcontrol`;

/*Table structure for table `auth` */

DROP TABLE IF EXISTS `auth`;

CREATE TABLE `auth` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(32) NOT NULL COMMENT '权限名称',
  `des` varchar(255) NOT NULL COMMENT '权限描述',
  `mark` varchar(32) NOT NULL COMMENT '权限标识',
  `type` varchar(32) NOT NULL COMMENT '权限类型;api接口、menu菜单、button按钮',
  `subject` varchar(255) NOT NULL COMMENT '权限保护的目标对象',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '权限状态,1正常、-1停用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mark_uq` (`mark`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限';

/*Data for the table `auth` */

insert  into `auth`(`id`,`del`,`create_time`,`update_time`,`name`,`des`,`mark`,`type`,`subject`,`status`) values 
(1,0,'2023-03-16 18:10:53','2023-03-16 18:10:55','查看一个用户','查看一个用户','ckygyh','api','/user/info/**',1),
(2,0,'2023-03-16 18:12:47','2023-03-16 18:12:46','查看全部用户','查看全部用户','ckqbyh','api','/user/list',1),
(3,0,'2023-03-17 12:54:34','2023-03-17 12:54:32','登录','登录','LOGIN','api','/user/login',1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `des` varchar(255) NOT NULL COMMENT '角色描述',
  `mark` varchar(32) NOT NULL COMMENT '角色标识',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '角色状态,1正常、-1停用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mark_uq` (`mark`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色';

/*Data for the table `role` */

insert  into `role`(`id`,`del`,`create_time`,`update_time`,`name`,`des`,`mark`,`status`) values 
(1,0,'2023-03-16 18:09:55','2023-03-16 18:09:57','开发','开发','kf',1),
(2,0,'2023-03-16 18:10:19','2023-03-16 18:10:21','技术经理','技术经理','jsjl',1),
(3,0,'2023-03-17 10:25:06','2023-03-17 10:25:08','默认','默认','DEF',1);

/*Table structure for table `role_mtm_auth` */

DROP TABLE IF EXISTS `role_mtm_auth`;

CREATE TABLE `role_mtm_auth` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `role_id` bigint(64) NOT NULL COMMENT '关联role表',
  `auth_id` bigint(64) NOT NULL COMMENT '关联auth表',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_auth_uq` (`role_id`,`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色与权限中间表(多对多)';

/*Data for the table `role_mtm_auth` */

insert  into `role_mtm_auth`(`id`,`del`,`create_time`,`update_time`,`role_id`,`auth_id`) values 
(1,0,'2023-03-16 18:15:11','2023-03-16 18:15:13',1,1),
(2,0,'2023-03-16 18:15:29','2023-03-16 18:15:30',2,1),
(3,0,'2023-03-16 18:15:40','2023-03-16 18:15:41',2,2),
(4,0,'2023-03-17 12:55:15','2023-03-17 12:55:16',3,3);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(127) NOT NULL COMMENT '密码',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '用户状态,1正常、-1禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户';

/*Data for the table `user` */

insert  into `user`(`id`,`del`,`create_time`,`update_time`,`account`,`password`,`nickname`,`status`) values 
(1,0,'2023-03-16 18:09:31','2023-03-16 18:09:34','hhc','123456','hhc',1);

/*Table structure for table `user_mtm_role` */

DROP TABLE IF EXISTS `user_mtm_role`;

CREATE TABLE `user_mtm_role` (
  `id` bigint(64) NOT NULL COMMENT 'id',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(64) NOT NULL COMMENT '关联user表',
  `role_id` bigint(64) NOT NULL COMMENT '关联role表',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_uq` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户与角色中间表(多对多)';

/*Data for the table `user_mtm_role` */

insert  into `user_mtm_role`(`id`,`del`,`create_time`,`update_time`,`user_id`,`role_id`) values 
(1,0,'2023-03-16 18:16:13','2023-03-16 18:16:15',1,1),
(2,0,'2023-03-16 18:49:11','2023-03-16 18:49:09',1,2),
(3,0,'2023-03-17 10:26:35','2023-03-17 10:26:29',1,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
