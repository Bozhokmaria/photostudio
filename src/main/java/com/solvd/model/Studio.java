package com.solvd.model;

public class Studio {
}
//
//        CREATE TABLE `studio` (
//        `studio_id` bigint NOT NULL AUTO_INCREMENT,
//        `studio_ph_st_id` bigint NOT NULL,
//        `studio_name` varchar(25) NOT NULL,
//        `studio_price` decimal(19,2) DEFAULT NULL,
//        PRIMARY KEY (`studio_id`),
//        UNIQUE KEY `UK_studio_name` (`studio_name`),
//        KEY `FK_st_ph_st_id` (`studio_ph_st_id`),
//        CONSTRAINT `FK_st_ph_st_id` FOREIGN KEY (`studio_ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );