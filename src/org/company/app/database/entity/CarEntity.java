package org.company.app.database.entity;

public class CarEntity {
    private int id;
    private int capacity;
    private int scheduleId;
    private int number;

    public CarEntity(int id, int capacity, int scheduleId, int number) {
        this.id = id;
        this.capacity = capacity;
        this.scheduleId = scheduleId;
        this.number = number;
    }

    public CarEntity(int capacity, int scheduleId, int number) {
        this(-1, capacity, scheduleId, number);
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", scheduleId=" + scheduleId +
                ", number=" + number +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
