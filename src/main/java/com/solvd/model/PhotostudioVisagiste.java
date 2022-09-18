package com.solvd.model;

public class PhotostudioVisagiste {
    private int id;
    private int photostudioId;
    private int visagisteId;

    public PhotostudioVisagiste() {
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

    public int getVisagisteId() {
        return visagisteId;
    }

    public void setVisagisteId(int visagisteId) {
        this.visagisteId = visagisteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotostudioVisagiste)) return false;

        PhotostudioVisagiste that = (PhotostudioVisagiste) o;

        if (id != that.id) return false;
        if (photostudioId != that.photostudioId) return false;
        return visagisteId == that.visagisteId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + photostudioId;
        result = 31 * result + visagisteId;
        return result;
    }

    @Override
    public String toString() {
        return "PhotostudioVisagiste{" +
                "id=" + id +
                ", photostudioId=" + photostudioId +
                ", visagisteId=" + visagisteId +
                '}';
    }
}


//
//        CREATE TABLE `photostudio_visagiste` (
//        `ph_vs_id` bigint NOT NULL AUTO_INCREMENT,
//        `ph_st_id` bigint NOT NULL,
//        `vs_id` bigint NOT NULL,
//        PRIMARY KEY (`ph_vs_id`),
//        KEY `FK_mm_vs_id` (`vs_id`),
//        KEY `FK_mm_ph_st_id` (`ph_st_id`),
//        CONSTRAINT `FK_pv_vs_id` FOREIGN KEY (`vs_id`) REFERENCES `visagiste` (`vs_id`),
//        CONSTRAINT `FK_pv_ph_st_id` FOREIGN KEY (`ph_st_id`) REFERENCES `photostudio` (`ph_st_id`)
//        );
//