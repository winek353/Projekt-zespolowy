CREATE DATABASE  IF NOT EXISTS `projekt-zespołowy_tracker`;
USE `projekt-zespołowy_tracker`;


CREATE TABLE `user_profile` (
  `user_profile_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) BINARY NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  
  -- NA POZNIEJ
  -- adres jako odniesienie do innej tabeli

  PRIMARY KEY (`user_profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_colleague` (
	`user_id` BIGINT(11) NOT NULL,
	`colleague_id` BIGINT(11) NOT NULL,
	PRIMARY KEY (`user_id`, `colleague_id`),
	CONSTRAINT `FK_EMP` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`user_profile_id`),
	CONSTRAINT `FK_COL` FOREIGN KEY (`colleague_id`) REFERENCES `user_profile` (`user_profile_id`)
);

CREATE TABLE `colleague_request` (
	`colleague_request_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(11) NOT NULL,
    `requester_id`BIGINT(11) NOT NULL,
    
	PRIMARY KEY (`colleague_request_id`),
	KEY `user_id` (`user_id`),
	CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`user_profile_id`)
);

CREATE TABLE `message` (
  `message_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
  `message` TEXT DEFAULT NULL,
  `author` varchar(50) NOT NULL,
  -- date
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `user_profile_message` (
  `user_profile_id` BIGINT(11) NOT NULL,
  `message_id` BIGINT(11) NOT NULL,
  PRIMARY KEY (`user_profile_id`,`message_id`),
  KEY `message_id` (`message_id`),
  
  
  CONSTRAINT `user_profile_message_fk_1` 
   FOREIGN KEY (`user_profile_id`) REFERENCES `user_profile` (`user_profile_id`),
   
  CONSTRAINT `user_profile_message_fk_2` 
   FOREIGN KEY (`message_id`) REFERENCES `message` (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*USUWANIE BAZY DANYCH

DROP TABLE colleague_request;
DROP TABLE user_profile_message;
DROP TABLE message;
DROP TABLE user_colleague;
DROP TABLE user_profile;
*/
