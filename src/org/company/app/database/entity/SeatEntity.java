package org.company.app.database.entity;

public class SeatEntity {
    private int id;
    private int carId;
    private int number;
    private int price;

    public SeatEntity(int id, int carId, int number, int price) {
        this.id = id;
        this.carId = carId;
        this.number = number;
        this.price = price;
    }

    public SeatEntity(int carId, int number, int price) {
        this(-1, carId, number, price);
    }

    @Override
    public String toString() {
        return "SeatEntity{" +
                "id=" + id +
                ", carId=" + carId +
                ", number=" + number +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
