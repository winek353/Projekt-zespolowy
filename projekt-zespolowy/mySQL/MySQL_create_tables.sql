CREATE DATABASE  IF NOT EXISTS `projekt-zespołowy_tracker`;
USE `projekt-zespołowy_tracker`;


CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) BINARY NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  
  -- NA POZNIEJ
  -- adres jako odniesienie do innej tabeli

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

