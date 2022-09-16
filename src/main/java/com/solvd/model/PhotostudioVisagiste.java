package com.solvd.model;

public class PhotostudioVisagiste {
}


//
//        CREATE TABLE `photostudio_visagiste` (
//        `ph_vs_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_id` bigint NOT NULL,
//        `vs_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_vs_id`),
//        KEY `FK_mm_vs_id` (`vs_id`),
//        KEY `FK_mm_ph_st_id` (`ph_st_id`),
//        CONSTRAINT `FK_pv_vs_id` FOREIGN KEY (`vs_id`) REFERENCES `visagiste` (`vs_id`),
//        CONSTRAINT `FK_pv_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );
//