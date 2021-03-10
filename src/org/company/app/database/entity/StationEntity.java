package org.company.app.database.entity;

public class StationEntity {
    private int id;
    private int cityId;
    private String name;

    public StationEntity(int id, int cityId, String name) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
    }

    public StationEntity(int cityId, String name) {
        this(-1, cityId, name);
    }

    @Override
    public String toString() {
        return "StationEntity{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
