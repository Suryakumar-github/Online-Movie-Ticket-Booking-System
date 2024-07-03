package view;

import model.Movie;
import model.Theatre;

import java.util.List;

public class AdminView {
    public void displayMovies(List<Movie> movies) {
        System.out.println("All Movies:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    public void displayTheatres(List<Theatre> theatres) {
        System.out.println("All Theatres:");
        for (Theatre theatre : theatres) {
            System.out.println(theatre);
        }
    }
}
