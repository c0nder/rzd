package org.company.app.database.entity;

public class TrainEntity {
    private int id;
    private int departureStationId;
    private int destinationStationId;
    private int trainType;

    public TrainEntity(int id, int departureStationId, int destinationStationId, int trainType) {
        this.id = id;
        this.departureStationId = departureStationId;
        this.destinationStationId = destinationStationId;
        this.trainType = trainType;
    }

    public TrainEntity(int departureStationId, int destinationStationId, int trainType) {
        this(-1, departureStationId, destinationStationId, trainType);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", departureStationId=" + departureStationId +
                ", destinationStationId=" + destinationStationId +
                ", trainType=" + trainType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public int getDestinationStationId() {
        return destinationStationId;
    }

    public void setDestinationStationId(int destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    public int getTrainType() {
        return trainType;
    }

    public void setTrainType(int trainType) {
        this.trainType = trainType;
    }
}
