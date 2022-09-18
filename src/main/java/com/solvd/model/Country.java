package com.solvd.model;

public class Country {
    private int id;
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country1 = (Country) o;

        if (id != country1.id) return false;
        return name != null ? name.equals(country1.name) : country1.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + name + '\'' +
                '}';
    }
}