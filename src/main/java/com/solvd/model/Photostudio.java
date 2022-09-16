package com.solvd.model;

public class Photostudio {
}
//
//        CREATE TABLE `photostudio` (
//        `ph_st_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_name` varchar(50) NOT NULL,
//        `ph_st_loc_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_st_id`),
//        KEY `FK_ph_st_loc_id` (`ph_st_loc_id`),
//        CONSTRAINT `FK_ph_st_loc_id` FOREIGN KEY (`ph_st_loc_id`) REFERENCES `location` (`loc_id`)
//        );