package com.solvd.model;

public class Rate {
    private int id;
    private String name;
    private double percentage;

    public Rate() {
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;

        Rate rate = (Rate) o;

        if (id != rate.id) return false;
        if (Double.compare(rate.percentage, percentage) != 0) return false;
        return name != null ? name.equals(rate.name) : rate.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(percentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
//
//        CREATE TABLE `rate` (
//        `rate_id` bigint NOT NULL AUTO_INCREMENT,
//        `rate_name` varchar(25) NOT NULL,
//        `rate_percentage` decimal(19,2) DEFAULT NULL,
//        PRIMARY KEY (`rate_id`)
//        );