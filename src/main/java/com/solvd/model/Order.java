package com.solvd.model;

import java.time.LocalDateTime;

public class Order {
          private int id;
          private LocalDateTime booking;
    private int clientId;
    private int dressRoomId;
    private int studioId;
    private int photographerId;
    private int visagisteId;
    private int hairdresserId;
    private int rateId;
    private int photostudioId;
    private double totalPrice;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getBooking() {
        return booking;
    }

    public void setBooking(LocalDateTime booking) {
        this.booking = booking;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDressRoomId() {
        return dressRoomId;
    }

    public void setDressRoomId(int dressRoomId) {
        this.dressRoomId = dressRoomId;
    }

    public int getStudioId() {
        return studioId;
    }

    public void setStudioId(int studioId) {
        this.studioId = studioId;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }

    public int getVisagisteId() {
        return visagisteId;
    }

    public void setVisagisteId(int visagisteId) {
        this.visagisteId = visagisteId;
    }

    public int getHairdresserId() {
        return hairdresserId;
    }

    public void setHairdresserId(int hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getPhotostudioId() {
        return photostudioId;
    }

    public void setPhotostudioId(int photostudioId) {
        this.photostudioId = photostudioId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (clientId != order.clientId) return false;
        if (dressRoomId != order.dressRoomId) return false;
        if (studioId != order.studioId) return false;
        if (photographerId != order.photographerId) return false;
        if (visagisteId != order.visagisteId) return false;
        if (hairdresserId != order.hairdresserId) return false;
        if (rateId != order.rateId) return false;
        if (photostudioId != order.photostudioId) return false;
        if (Double.compare(order.totalPrice, totalPrice) != 0) return false;
        return booking != null ? booking.equals(order.booking) : order.booking == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (booking != null ? booking.hashCode() : 0);
        result = 31 * result + clientId;
        result = 31 * result + dressRoomId;
        result = 31 * result + studioId;
        result = 31 * result + photographerId;
        result = 31 * result + visagisteId;
        result = 31 * result + hairdresserId;
        result = 31 * result + rateId;
        result = 31 * result + photostudioId;
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", booking=" + booking +
                ", clientId=" + clientId +
                ", dressRoomId=" + dressRoomId +
                ", studioId=" + studioId +
                ", photographerId=" + photographerId +
                ", visagisteId=" + visagisteId +
                ", hairdresserId=" + hairdresserId +
                ", rateId=" + rateId +
                ", photostudioId=" + photostudioId +
                ", totalPrice=" + totalPrice +
                '}';
    }
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