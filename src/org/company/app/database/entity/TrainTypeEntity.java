package org.company.app.database.entity;

public class TrainTypeEntity {
    private int id;
    private String name;

    public TrainTypeEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TrainTypeEntity(String name) {
        this(-1, name);
    }

    @Override
    public String toString() {
        return "TrainTypeEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
