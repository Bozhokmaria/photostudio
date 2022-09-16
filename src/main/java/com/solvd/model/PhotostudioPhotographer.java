package com.solvd.model;

public class PhotostudioPhotographer {
}




//
//
//        CREATE TABLE `photostudio_photographer` (
//        `ph_ph_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_id` bigint NOT NULL,
//        `ph_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_ph_id`),
//        KEY `FK_mm_ph_id` (`ph_id`),
//        KEY `FK_mm_ph_st_id` (`ph_st_id`),
//        CONSTRAINT `FK_pp_ph_id` FOREIGN KEY (`ph_id`) REFERENCES `photographer` (`ph_id`),
//        CONSTRAINT `FK_pp_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );