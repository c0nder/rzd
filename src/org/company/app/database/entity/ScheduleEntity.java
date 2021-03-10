package org.company.app.database.entity;

import java.util.Date;

public class ScheduleEntity {
    private int id;
    private Date departure;
    private Date arrival;
    private int trainId;

    // Private fields with aggregated info
    private String departureStationName;
    private String destinationStationName;
    private String trainTypeName;

    public ScheduleEntity(int id, Date departure, Date arrival, int trainId) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.trainId = trainId;
    }

    public ScheduleEntity(Date departure, Date arrival, int trainId) {
        this(-1, departure, arrival, trainId);
    }

    @Override
    public String toString() {
        return "ScheduleEntity{" +
                "id=" + id +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", trainId=" + trainId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public void setDestinationStationName(String destinationStationName) {
        this.destinationStationName = destinationStationName;
    }

    public String getTrainTypeName() {
        return trainTypeName;
    }

    public void setTrainTypeName(String trainTypeName) {
        this.trainTypeName = trainTypeName;
    }
}
