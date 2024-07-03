package model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Ticket {
    public enum Status{
        BOOKED,
        CANCELED
    }
    private int ticketId;
    private int userId;
    private int showtimeId;
    private int movieId;
    private int theatreId;
    private int seatId;
    private Status status; // booked, cancelled
    private Date bookingDate;
    private Time bookingTime;

    public Ticket(int ticketId,int userId, int showtimeId,int seatId,int movieId,int theatreId,Status status,Date bookingDate,Time bookingTime){
        this.ticketId = ticketId;
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.seatId = seatId;
        this.status = status;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }
    public Ticket(int userId,int showtimeId,int seatId,int movieId,int theatreId,Status status,Date bookingDate,Time bookingTime){
        this.userId = userId;
        this.showtimeId = showtimeId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.seatId = seatId;
        this.status = status;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
    }
    public Ticket(){

    }
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(int showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Time getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Time bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", userId=" + userId +
                ", showtimeId=" + showtimeId +
                ", movieId=" + movieId +
                ", theatreId=" + theatreId +
                ", seatId=" + seatId +
                ", status=" + status +
                ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime +
                '}';
    }
}

