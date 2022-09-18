package com.solvd.model;

public class Location {
    private int id;
    private String address;
    private int cityId;

    public Location() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (id != location.id) return false;
        if (cityId != location.cityId) return false;
        return address != null ? address.equals(location.address) : location.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + cityId;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
//
//        CREATE TABLE `location` (
//        `loc_id` bigint NOT NULL AUTO_INCREMENT,
//        `loc_address_line` varchar(255) DEFAULT NULL,
//        `loc_city_id` bigint NOT NULL,
//        PRIMARY KEY (`loc_id`),
//        KEY `FK_loc_city_id` (`loc_city_id`),
//        CONSTRAINT `FK_loc_city_id` FOREIGN KEY (`loc_city_id`) REFERENCES `city` (`city_id`)
//        );