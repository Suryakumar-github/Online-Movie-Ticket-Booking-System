package model;

import java.sql.Date;

public class Seat {
    public enum Status{
        BOOKED,
        AVAILABLE
    }
    private int seatId;
    private int theatreId;
    private String seatNumber; // e.g., A1, A2
   private Status status;
    private int showtimeId;
    private int movieId;
    private Date seatDate;

    public Seat(int seatId, int theatreId,String seatNumber,Status status,int showtimeId,int movieId,Date seatDate){
        this.seatId = seatId;
        this.theatreId = theatreId;
        this.seatNumber = seatNumber;
        this.status = status;
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.seatDate = seatDate;
    }
    public Seat( int theatreId,String seatNumber,Status status,int showtimeId,int movieId,Date seatDate){
        this.theatreId = theatreId;
        this.seatNumber = seatNumber;
        this.status = status;
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.seatDate = seatDate;
    }
    public Seat(int seatId, String seatNumber,Status status){
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.status = status;
    }
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getSeatDate() {
        return seatDate;
    }

    public void setSeatDate(Date seatDate) {
        this.seatDate = seatDate;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", theatreId=" + theatreId +
                ", seatNumber='" + seatNumber + '\'' +
                ", status=" + status +
                ", showtimeId=" + showtimeId +
                ", movieId=" + movieId +
                ", seatDate=" + seatDate +
                '}';
    }
}

