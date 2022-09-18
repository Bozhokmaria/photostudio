package com.solvd.model;

public class PhotostudioPhotographer {
    private int id;
    private int photostudioId;
    private int photographerId;

    public PhotostudioPhotographer() {
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

    public int getPhotographerId() {
        return photographerId;
    }

    public void setPhotographerId(int photographerId) {
        this.photographerId = photographerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotostudioPhotographer)) return false;

        PhotostudioPhotographer that = (PhotostudioPhotographer) o;

        if (id != that.id) return false;
        if (photostudioId != that.photostudioId) return false;
        return photographerId == that.photographerId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + photostudioId;
        result = 31 * result + photographerId;
        return result;
    }

    @Override
    public String toString() {
        return "PhotostudioPhotographer{" +
                "id=" + id +
                ", photostudioId=" + photostudioId +
                ", photographerId=" + photographerId +
                '}';
    }
}




//
//
//        CREATE TABLE `photostudio_photographer` (
//        `ph_ph_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_id` bigint NOT NULL,
//        `ph_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_ph_id`),
//        KEY `FK_mm_ph_id` (`ph_id`),
//        KEY `FK_mm_ph_st_id` (`ph_st_id`),
//        CONSTRAINT `FK_pp_ph_id` FOREIGN KEY (`ph_id`) REFERENCES `photographer` (`ph_id`),
//        CONSTRAINT `FK_pp_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );