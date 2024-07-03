package view;

import model.Movie;

import java.util.List;

public class TheatreAdminView {
    public void displayMovies(List<Movie> movies) {
        System.out.println("Movies by Theatre:");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
