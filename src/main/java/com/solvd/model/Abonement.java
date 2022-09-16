package com.solvd.model;

public class Abonement {
}
//
//        CREATE TABLE `abonement` (
//        `abon_id` bigint NOT NULL AUTO_INCREMENT,
//        `client_id` bigint NOT NULL,
//        `abon_name` varchar(25) NOT NULL,
//        `abon_photosession_amount` bigint NOT NULL,
//        `abon_price` decimal(19,2) DEFAULT NULL,
//        PRIMARY KEY (`abon_id`),
//        KEY `FK_client_id` (`client_id`),
//        CONSTRAINT `FK_client_abt_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`)
//        );