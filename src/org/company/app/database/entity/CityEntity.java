package org.company.app.database.entity;

public class CityEntity {
    private int id;
    private String cityName;

    public CityEntity(int id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    public CityEntity(String cityName) {
        this(-1, cityName);
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
