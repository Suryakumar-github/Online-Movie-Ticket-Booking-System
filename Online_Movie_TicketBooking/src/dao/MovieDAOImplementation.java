package dao;


import model.Movie;
import model.MovieInTheatre;
import util.Database;
import util.SQLQueries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImplementation implements MovieDAO {
    public MovieDAOImplementation(){

    }
    @Override
    public boolean addMovie(Movie movie) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.INSERT_MOVIE);
            ps.setString(1, movie.getName());
            ps.setString(2, movie.getType().toString());
            ps.setString(3, movie.getLanguage().toString());
            ps.setFloat(4, movie.getDuration());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Movie getMovieById(int movieId) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_MOVIE_BY_ID);
            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setName(rs.getString("name"));
                movie.setType(Movie.MovieType.valueOf(rs.getString("type")));
                movie.setLanguage(Movie.Language.valueOf(rs.getString("language")));
                movie.setDuration(rs.getInt("duration"));
                return movie;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_ALL_MOVIES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getInt("movie_id"));
                movie.setName(rs.getString("name"));
                movie.setType(Movie.MovieType.valueOf(rs.getString("type")));
                movie.setLanguage(Movie.Language.valueOf(rs.getString("language")));
                movie.setDuration(rs.getInt("duration"));
                movies.add(movie);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return movies;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.UPDATE_MOVIE);
            ps.setString(1, movie.getName());
            ps.setString(2, movie.getType().toString());
            ps.setString(3, movie.getLanguage().toString());
            ps.setFloat(4, movie.getDuration());
            ps.setInt(5, movie.getMovieId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeMovie(int movieId) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.DELETE_MOVIE);
            ps.setInt(1, movieId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean sheduleMovie(MovieInTheatre movieInTheatre){
        try{
            PreparedStatement statement = Database.getPreparedStatement(SQLQueries.INSERT_MOVIE);
            statement.setInt(1,movieInTheatre.getTheatreId());
            statement.setInt(2,movieInTheatre.getMovieId());
            statement.setInt(3,movieInTheatre.getShowtimeId());
            statement.setDouble(4,movieInTheatre.getPrice());
            statement.setString(5,movieInTheatre.getStatus().toString());
            int rowAffected = statement.executeUpdate();
            return rowAffected > 0;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean removeScheduledMovie(MovieInTheatre movieInTheatre) {
        try {
            PreparedStatement statement = Database.getPreparedStatement(SQLQueries.REMOVE_SHEDULED_MOVIE);
            statement.setInt(1, movieInTheatre.getTheatreId());
            statement.setInt(2, movieInTheatre.getMovieId());
            statement.setInt(3, movieInTheatre.getShowtimeId());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Movie> getMoviesByTheatre(int theatreId) {
        List<Movie> movies = new ArrayList<>();
        try (PreparedStatement statement = Database.getPreparedStatement(SQLQueries.GET_MOVIES_BY_THEATRE)) {
            statement.setInt(1, theatreId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int movieId = rs.getInt("movie_id");
                String movieName = rs.getString("movie_name");
                String movieType = rs.getString("movie_type");
                String language = rs.getString("language");
                int duration = rs.getInt("duration");

                Movie movie = new Movie(movieId, movieName, Movie.MovieType.valueOf(movieType.toUpperCase()), Movie.Language.valueOf(language.toUpperCase()), duration);
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return movies;
    }
}

