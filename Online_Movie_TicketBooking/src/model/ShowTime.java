package model;

import java.sql.Time;

public class ShowTime {
    private int showtimeId;
    private int showNumber;
    private Time showTime;

    public ShowTime(int showtimeId, int showNumber, Time showTime){
        this.showtimeId = showtimeId;
        this.showNumber = showNumber;
        this.showTime = showTime;
    }
    public ShowTime(int showNumber, Time showTime){
        this.showNumber = showNumber;
        this.showTime = showTime;
    }
    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getShowNumber() {
        return showNumber;
    }

    public void setShowNumber(int showNumber) {
        this.showNumber = showNumber;
    }

    public Time getShowTime() {
        return showTime;
    }

    public void setShowTime(Time showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "showtimeId=" + showtimeId +
                ", showNumber=" + showNumber +
                ", showTime=" + showTime +
                '}';
    }
}

