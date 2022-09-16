package com.solvd.model;

public class PhotostudioHairdresser {
}

//
//        CREATE TABLE `photostudio_hairdresser` (
//        `ph_hd_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_id` bigint NOT NULL,
//        `hd_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_hd_id`),
//        KEY `FK_mm_hd_id` (`hd_id`),
//        KEY `FK_mm_ph_st_id` (`ph_st_id`),
//        CONSTRAINT `FK_ph_hd_id` FOREIGN KEY (`hd_id`) REFERENCES `hairdresser` (`hd_id`),
//        CONSTRAINT `FK_ph_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );