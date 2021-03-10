package org.company.app.database.entity;

public class UserTicketEntity {
    private int id;
    private int userId;
    private int seatId;
    private int scheduleId;

    public UserTicketEntity(int id, int userId, int seatId, int scheduleId) {
        this.id = id;
        this.userId = userId;
        this.seatId = seatId;
        this.scheduleId = scheduleId;
    }

    public UserTicketEntity(int userId, int seatId, int scheduleId) {
        this(-1, userId, seatId, scheduleId);
    }

    @Override
    public String toString() {
        return "UserTicketEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", seatId=" + seatId +
                ", scheduleId=" + scheduleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
