package dao;

import model.Movie;
import java.util.List;

public interface MovieDAO {
    boolean addMovie(Movie movie);
    Movie getMovieById(int movieId);
    List<Movie> getAllMovies();
    boolean updateMovie(Movie movie);
    boolean removeMovie(int movieId);
}
