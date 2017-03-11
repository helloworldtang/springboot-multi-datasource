CREATE DATABASE /*!32312 IF NOT EXISTS */`database1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `database1`;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `net_name` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL,
  `family_native_place` varchar(255) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

INSERT INTO `user_info` (`id`, `username`, `net_name`, `job`, `family_native_place`, `mobile`, `email`)
VALUES ('1', 'Tom', 'unknown', 'senior engineer', 'China', '123456789', '793059909@qq.com');
