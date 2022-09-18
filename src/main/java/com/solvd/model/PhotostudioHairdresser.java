package com.solvd.model;

public class PhotostudioHairdresser {
    private int id;
    private int photostudioId;
    private int hairdresserId;

    public PhotostudioHairdresser() {
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

    public int getHairdresserId() {
        return hairdresserId;
    }

    public void setHairdresserId(int hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotostudioHairdresser)) return false;

        PhotostudioHairdresser that = (PhotostudioHairdresser) o;

        if (id != that.id) return false;
        if (photostudioId != that.photostudioId) return false;
        return hairdresserId == that.hairdresserId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + photostudioId;
        result = 31 * result + hairdresserId;
        return result;
    }

    @Override
    public String toString() {
        return "PhotostudioHairdresser{" +
                "id=" + id +
                ", photostudioId=" + photostudioId +
                ", hairdresserId=" + hairdresserId +
                '}';
    }
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