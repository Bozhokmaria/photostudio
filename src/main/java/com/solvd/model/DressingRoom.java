package com.solvd.model;

public class DressingRoom {
    private int id;
    private String name;
    private double price;
    private int photostudioId;

    public DressingRoom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPhotostudioId() {
        return photostudioId;
    }

    public void setPhotostudioId(int photostudioId) {
        this.photostudioId = photostudioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DressingRoom)) return false;

        DressingRoom that = (DressingRoom) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (photostudioId != that.photostudioId) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + photostudioId;
        return result;
    }

    @Override
    public String toString() {
        return "DressingRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", photostudioId=" + photostudioId +
                '}';
    }
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