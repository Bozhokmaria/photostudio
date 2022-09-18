package com.solvd.model;

public class Hairdresser {
    private int id;
    private String firstName;
    private String lastName;
    private double price;

    public Hairdresser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        if (!(o instanceof Hairdresser)) return false;

        Hairdresser that = (Hairdresser) o;

        if (id != that.id) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Hairdresser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", price=" + price +
                '}';
    }
}
//
//        CREATE TABLE `hairdresser` (
//        `hd_id` bigint NOT NULL AUTO_INCREMENT,
//        `hd_first_name` varchar(25) NOT NULL,
//        `hd_last_name` varchar(25) NOT NULL,
//        `hd_price` decimal(19,2) DEFAULT NULL,
//        PRIMARY KEY (`hd_id`)
//        );