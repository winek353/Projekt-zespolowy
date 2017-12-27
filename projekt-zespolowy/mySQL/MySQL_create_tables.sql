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

/*USUWANIE BAZY DANYCH

DROP TABLE user_colleague;
DROP TABLE user_profile;
*/
