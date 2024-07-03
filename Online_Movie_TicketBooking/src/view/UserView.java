package view;

import model.*;

import java.sql.SQLOutput;
import java.util.List;

public class UserView {
    public void displayTickets(List<Ticket> tickets) {
        System.out.println("Your Tickets:");
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    public void displayAllLocations(List<String> locations) {
        System.out.println("Available locations:");
        for (String location : locations) {
            System.out.println(location);
        }
    }

    public void displayAllMovies(List<Movie> movies) {
        System.out.println("Available movies:");
        for (Movie movie : movies) {
            System.out.println(movie.getMovieId() + ". " + movie.getName());
        }
    }

    public void displayTheatresInLocation(List<Theatre> theatres) {
        System.out.println("Available theatres:");
        for (Theatre theatre : theatres) {
            System.out.println(theatre.getTheatreId() + ". " + theatre.getName());
        }

    }

    public void displayTheatres(List<Theatre> theatres) {
        System.out.println("Theatres showing this movie:");
        for (Theatre theatre : theatres) {
            System.out.println(theatre.getTheatreId() + ". " + theatre.getName());
        }
    }

    public void displayAllMoviesInTheatre(List<Movie> movies) {
        System.out.println("Movies running in this theatre:");
        for (Movie movie : movies) {
            System.out.println(movie.getMovieId() + ". " + movie.getName());
        }
    }

    public void displayAvailableSeats(List<Seat> availableSeats) {
        System.out.println("Available Seats :");
        for(Seat seat : availableSeats){
            System.out.println("SeatId : "+seat.getSeatId()+", SeatNumber : "+seat.getSeatNumber()+", Status : "+seat.getStatus());
        }
    }

    public void displayShowtimes(List<ShowTime> showtimes) {
        System.out.println("Show Timings :");
        for(ShowTime showTime : showtimes){
            System.out.println("showTime_id : "+showTime.getShowtimeId() + ", ShowNumber : "+showTime.getShowNumber()+", showTime : " +showTime.getShowTime());
        }
    }
}
