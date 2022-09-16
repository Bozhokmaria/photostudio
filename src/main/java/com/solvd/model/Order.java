package com.solvd.model;

public class Order {
}
//
//        CREATE TABLE `order` (
//        `order_id` bigint NOT NULL AUTO_INCREMENT,
//        `order_booking` datetime(6) NOT NULL,
//        `client_id` bigint NOT NULL,
//        `dress_room_id` bigint NOT NULL,
//        `studio_id` bigint NOT NULL,
//        `ph_id` bigint NOT NULL,
//        `vs_id` bigint NOT NULL,
//        `hd_id` bigint NOT NULL,
//        `rate_id` bigint NOT NULL,
//        `ph_st_id` bigint NOT NULL,
//        `order_total_price` decimal(19,2) DEFAULT NULL,
//        PRIMARY KEY (`order_id`),
//
//        KEY `FK_client_id` (`client_id`),
//        CONSTRAINT `FK_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`),
//
//        KEY `FK_dress_room_id` (`dress_room_id`),
//        CONSTRAINT `FK_dress_room_id` FOREIGN KEY (`dress_room_id`) REFERENCES `dressing_room` (`dress_room_id`),
//
//        KEY `FK_studio_id` (`studio_id`),
//        CONSTRAINT `FK_studio_id` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`),
//
//        KEY `FK_ph_id` (`ph_id`),
//        CONSTRAINT `FK_ph_id` FOREIGN KEY (`ph_id`) REFERENCES `photographer` (`ph_id`),
//
//        KEY `FK_vs_id` (`vs_id`),
//        CONSTRAINT `FK_vs_id` FOREIGN KEY (`vs_id`) REFERENCES `visagiste` (`vs_id`),
//
//        KEY `FK_hd_id` (`hd_id`),
//        CONSTRAINT `FK_hd_id` FOREIGN KEY (`hd_id`) REFERENCES `hairdresser` (`hd_id`),
//
//        KEY `FK_rate_id` (`rate_id`),
//        CONSTRAINT `FK_rate_id` FOREIGN KEY (`rate_id`) REFERENCES `rate` (`rate_id`),
//
//        KEY `FK_ph_st_id` (`ph_st_id`),
//        CONSTRAINT `FK_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );