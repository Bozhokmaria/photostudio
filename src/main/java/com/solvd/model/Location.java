package com.solvd.model;

public class Location {
}
//
//        CREATE TABLE `location` (
//        `loc_id` bigint NOT NULL AUTO_INCREMENT,
//        `loc_address_line` varchar(255) DEFAULT NULL,
//        `loc_city_id` bigint NOT NULL,
//        PRIMARY KEY (`loc_id`),
//        KEY `FK_loc_city_id` (`loc_city_id`),
//        CONSTRAINT `FK_loc_city_id` FOREIGN KEY (`loc_city_id`) REFERENCES `city` (`city_id`)
//        );