drop database photostudio;
create database photostudio;
use photostudio;

CREATE TABLE `country` (
`country_id` bigint NOT NULL AUTO_INCREMENT,
`country_name` varchar(255) NOT NULL,
PRIMARY KEY (`country_id`)
);

CREATE TABLE `city` (
`city_id` bigint NOT NULL AUTO_INCREMENT,
`city_name` varchar(255) NOT NULL,
`country_id` bigint NOT NULL,
PRIMARY KEY (`city_id`),
KEY `FK_country_id` (`country_id`),
CONSTRAINT `FK_country_id` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`)
);

CREATE TABLE `location` (
  `loc_id` bigint NOT NULL AUTO_INCREMENT,
  `loc_address_line` varchar(255) DEFAULT NULL,
  `loc_city_id` bigint NOT NULL,
  PRIMARY KEY (`loc_id`),
  KEY `FK_loc_city_id` (`loc_city_id`),
  CONSTRAINT `FK_loc_city_id` FOREIGN KEY (`loc_city_id`) REFERENCES `city` (`city_id`)
);

CREATE TABLE `photostudio` (
  `ph_st_id` bigint NOT NULL AUTO_INCREMENT,
  `ph_st_name` varchar(50) NOT NULL,
  `ph_st_loc_id` bigint NOT NULL,
  PRIMARY KEY (`ph_st_id`),
  KEY `FK_ph_st_loc_id` (`ph_st_loc_id`),
  CONSTRAINT `FK_ph_st_loc_id` FOREIGN KEY (`ph_st_loc_id`) REFERENCES `location` (`loc_id`)
);

CREATE TABLE `studio` (
  `studio_id` bigint NOT NULL AUTO_INCREMENT,
  `studio_ph_st_id` bigint NOT NULL,
  `studio_name` varchar(25) NOT NULL,
  `studio_price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`studio_id`),
  UNIQUE KEY `UK_studio_name` (`studio_name`),
  KEY `FK_st_ph_st_id` (`studio_ph_st_id`),
  CONSTRAINT `FK_st_ph_st_id` FOREIGN KEY (`studio_ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
);

CREATE TABLE `dressing_room` (
  `dress_room_id` bigint NOT NULL AUTO_INCREMENT,
  `dress_room_name` varchar(25) NOT NULL,
  `dress_room_price` decimal(19,2) DEFAULT NULL,
  `dress_room_ph_st` bigint NOT null,
  PRIMARY KEY (`dress_room_id`),
  KEY `FK_dress_room_ph_st` (`dress_room_ph_st`),
  CONSTRAINT `FK_dress_room_ph_st` FOREIGN KEY (`dress_room_ph_st`) REFERENCES `photostudio` (`ph_st_id`)
);

CREATE TABLE `hairdresser` (
  `hd_id` bigint NOT NULL AUTO_INCREMENT,
  `hd_first_name` varchar(25) NOT NULL,
  `hd_last_name` varchar(25) NOT NULL,
  `hd_price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`hd_id`)
);

CREATE TABLE `photographer` (
  `ph_id` bigint NOT NULL AUTO_INCREMENT,
  `ph_first_name` varchar(25) NOT NULL,
  `ph_last_name` varchar(25) NOT NULL,
  `ph_price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`ph_id`)
);

CREATE TABLE `visagiste` (
  `vs_id` bigint NOT NULL AUTO_INCREMENT,
  `vs_first_name` varchar(25) NOT NULL,
  `vs_last_name` varchar(25) NOT NULL,
  `vs_price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`vs_id`)
);

CREATE TABLE `client` (
  `client_id` bigint NOT NULL AUTO_INCREMENT,
  `client_email` varchar(255) NOT NULL,
  `client_first_name` varchar(50) NOT NULL,
  `client_last_name` varchar(50) NOT NULL,
  `client_password` varchar(510) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE KEY `UK_client_email` (`client_email`)
);

CREATE TABLE `rate` (
  `rate_id` bigint NOT NULL AUTO_INCREMENT,
  `rate_name` varchar(25) NOT NULL,
  `rate_percentage` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`rate_id`)
);

CREATE TABLE `order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `order_booking` datetime(6) NOT NULL,
   `client_id` bigint NOT NULL,
   `dress_room_id` bigint NOT NULL,
   `studio_id` bigint NOT NULL,
   `ph_id` bigint NOT NULL,
   `vs_id` bigint NOT NULL,
   `hd_id` bigint NOT NULL,
   `rate_id` bigint NOT NULL,
	`ph_st_id` bigint NOT NULL,
   `order_total_price` decimal(19,2) DEFAULT NULL,
    PRIMARY KEY (`order_id`),
    
    KEY `FK_client_id` (`client_id`),
   CONSTRAINT `FK_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
   
   KEY `FK_dress_room_id` (`dress_room_id`),
   CONSTRAINT `FK_dress_room_id` FOREIGN KEY (`dress_room_id`) REFERENCES `dressing_room` (`dress_room_id`),
   
   KEY `FK_studio_id` (`studio_id`),
   CONSTRAINT `FK_studio_id` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`),
   
   KEY `FK_ph_id` (`ph_id`),
   CONSTRAINT `FK_ph_id` FOREIGN KEY (`ph_id`) REFERENCES `photographer` (`ph_id`),
   
      KEY `FK_vs_id` (`vs_id`),
   CONSTRAINT `FK_vs_id` FOREIGN KEY (`vs_id`) REFERENCES `visagiste` (`vs_id`),  

   KEY `FK_hd_id` (`hd_id`),
   CONSTRAINT `FK_hd_id` FOREIGN KEY (`hd_id`) REFERENCES `hairdresser` (`hd_id`),
   
   KEY `FK_rate_id` (`rate_id`),
   CONSTRAINT `FK_rate_id` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`rate_id`),
   
	KEY `FK_ph_st_id` (`ph_st_id`),
   CONSTRAINT `FK_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)  
);

CREATE TABLE `abonement` (
  `abon_id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` bigint NOT NULL,
  `abon_name` varchar(25) NOT NULL,
  `abon_photosession_amount` bigint NOT NULL,
  `abon_price` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`abon_id`),
  KEY `FK_client_id` (`client_id`),
  CONSTRAINT `FK_client_abt_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
);


CREATE TABLE `photostudio_photographer` (
  `ph_ph_id` bigint NOT NULL AUTO_INCREMENT,
  `ph_st_id` bigint NOT NULL,
  `ph_id` bigint NOT NULL,
  PRIMARY KEY (`ph_ph_id`),
  KEY `FK_mm_ph_id` (`ph_id`),
  KEY `FK_mm_ph_st_id` (`ph_st_id`),
  CONSTRAINT `FK_pp_ph_id` FOREIGN KEY (`ph_id`) REFERENCES `photographer` (`ph_id`),
  CONSTRAINT `FK_pp_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
);

CREATE TABLE `photostudio_hairdresser` (
 `ph_hd_id` bigint NOT NULL AUTO_INCREMENT,
  `ph_st_id` bigint NOT NULL,
  `hd_id` bigint NOT NULL,
  PRIMARY KEY (`ph_hd_id`),
  KEY `FK_mm_hd_id` (`hd_id`),
  KEY `FK_mm_ph_st_id` (`ph_st_id`),
  CONSTRAINT `FK_ph_hd_id` FOREIGN KEY (`hd_id`) REFERENCES `hairdresser` (`hd_id`),
  CONSTRAINT `FK_ph_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
);

CREATE TABLE `photostudio_visagiste` (
`ph_vs_id` bigint NOT NULL AUTO_INCREMENT,
  `ph_st_id` bigint NOT NULL,
  `vs_id` bigint NOT NULL,
  PRIMARY KEY (`ph_vs_id`),
  KEY `FK_mm_vs_id` (`vs_id`),
  KEY `FK_mm_ph_st_id` (`ph_st_id`),
  CONSTRAINT `FK_pv_vs_id` FOREIGN KEY (`vs_id`) REFERENCES `visagiste` (`vs_id`),
  CONSTRAINT `FK_pv_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
);