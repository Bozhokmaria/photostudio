package com.solvd.model;

public class Studio {
    private int id;
    private int photostudioId;
    private String name;
    private double price;

    public Studio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhotostudioId() {
        return photostudioId;
    }

    public void setPhotostudioId(int photostudioId) {
        this.photostudioId = photostudioId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Studio)) return false;

        Studio studio = (Studio) o;

        if (id != studio.id) return false;
        if (photostudioId != studio.photostudioId) return false;
        if (Double.compare(studio.price, price) != 0) return false;
        return name != null ? name.equals(studio.name) : studio.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + photostudioId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "id=" + id +
                ", photostudioId=" + photostudioId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
//
//        CREATE TABLE `studio` (
//        `studio_id` bigint NOT NULL AUTO_INCREMENT,
//        `studio_ph_st_id` bigint NOT NULL,
//        `studio_name` varchar(25) NOT NULL,
//        `studio_price` decimal(19,2) DEFAULT NULL,
//        PRIMARY KEY (`studio_id`),
//        UNIQUE KEY `UK_studio_name` (`studio_name`),
//        KEY `FK_st_ph_st_id` (`studio_ph_st_id`),
//        CONSTRAINT `FK_st_ph_st_id` FOREIGN KEY (`studio_ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );