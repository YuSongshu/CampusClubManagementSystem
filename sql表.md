```
-- 创建数据库
CREATE DATABASE IF NOT EXISTS club_management DEFAULT CHARACTER SET utf8mb4;
USE club_management;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `clazz` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT '正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `club` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `info` varchar(200) DEFAULT NULL,
  `max_num` int(11) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `fund` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `club_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `act_time` datetime DEFAULT NULL,
  `max_num` int(11) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_activity_club` (`club_id`),
  CONSTRAINT `fk_activity_club` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `activity_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `sign_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sign_activity` (`activity_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `activity_sign_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_sign_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL,
  `reason` varchar(200) DEFAULT NULL,
  `apply_type` varchar(10) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_apply_club` (`club_id`),
  KEY `apply_ibfk_1` (`student_id`),
  CONSTRAINT `apply_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_apply_club` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `club_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `club_id` int(11) DEFAULT NULL,
  `join_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cm_club` (`club_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `club_member_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_cm_club` FOREIGN KEY (`club_id`) REFERENCES `club` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

