package com.solvd.model;

public class Photostudio {

    private int id;
    private String name;
    private int locationId;

    public Photostudio() {
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

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photostudio)) return false;

        Photostudio that = (Photostudio) o;

        if (id != that.id) return false;
        if (locationId != that.locationId) return false;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + locationId;
        return result;
    }

    @Override
    public String toString() {
        return "Photostudio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locationId=" + locationId +
                '}';
    }
}
//
//        CREATE TABLE `photostudio` (
//        `ph_st_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_name` varchar(50) NOT NULL,
//        `ph_st_loc_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_st_id`),
//        KEY `FK_ph_st_loc_id` (`ph_st_loc_id`),
//        CONSTRAINT `FK_ph_st_loc_id` FOREIGN KEY (`ph_st_loc_id`) REFERENCES `location` (`loc_id`)
//        );