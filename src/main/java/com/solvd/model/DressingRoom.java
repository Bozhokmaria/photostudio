package com.solvd.model;

public class DressingRoom {
}
//
//        CREATE TABLE `dressing_room` (
//        `dress_room_id` bigint NOT NULL AUTO_INCREMENT,
//        `dress_room_name` varchar(25) NOT NULL,
//        `dress_room_price` decimal(19,2) DEFAULT NULL,
//        `dress_room_ph_st` bigint NOT null,
//        PRIMARY KEY (`dress_room_id`),
//        KEY `FK_dress_room_ph_st` (`dress_room_ph_st`),
//        CONSTRAINT `FK_dress_room_ph_st` FOREIGN KEY (`dress_room_ph_st`) REFERENCES `photostudio` (`ph_st_id`)
//        );