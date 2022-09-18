package com.solvd.model;

public class Abonement {
    private int id;
    private int clientId;
    private String name;
    private int photosessionAmount;
    private double price;

    public Abonement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhotosessionAmount() {
        return photosessionAmount;
    }

    public void setPhotosessionAmount(int photosessionAmount) {
        this.photosessionAmount = photosessionAmount;
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
        if (!(o instanceof Abonement)) return false;

        Abonement abonement = (Abonement) o;

        if (id != abonement.id) return false;
        if (clientId != abonement.clientId) return false;
        if (photosessionAmount != abonement.photosessionAmount) return false;
        if (Double.compare(abonement.price, price) != 0) return false;
        return name != null ? name.equals(abonement.name) : abonement.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + clientId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + photosessionAmount;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Abonement{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", name='" + name + '\'' +
                ", photosessionAmount=" + photosessionAmount +
                ", price=" + price +
                '}';
    }
}