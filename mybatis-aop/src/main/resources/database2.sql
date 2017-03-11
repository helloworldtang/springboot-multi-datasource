CREATE DATABASE /*!32312 IF NOT EXISTS*/`database2` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `database2`;

DROP TABLE IF EXISTS `user_detail`;
CREATE TABLE `user_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `user_detail`(`id`,`name`,`qq`) values (1,'mybatis-aop','10000');