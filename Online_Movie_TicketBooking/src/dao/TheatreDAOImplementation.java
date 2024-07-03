package dao;

import model.Seat;
import model.ShowTime;
import model.Theatre;
import util.Database;
import util.SQLQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TheatreDAOImplementation implements TheatreDAO {

    public TheatreDAOImplementation(){

    }
    @Override
    public boolean addTheatre(Theatre theatre) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.INSERT_THEATRE);
            ps.setString(1, theatre.getName());
            ps.setInt(2, theatre.getTotalScreens());
            ps.setInt(3, theatre.getTotalCapacity());
            ps.setString(4,theatre.getLocation());
            ps.setInt(5, theatre.getTheatreAdmin_id());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Theatre getTheatreById(int theatreId) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_THEATRE_BY_ID);
            ps.setInt(1, theatreId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Theatre theatre =null;
                int theatre_id = rs.getInt("theatre_id");
                String theatreName = rs.getString("name");
                int totalScreens = rs.getInt("total_screens");
                int totalCapacity = rs.getInt("total_capacity");
                String location = rs.getString("location");
                int theatreAdminId = rs.getInt("theatreAdmin_id");
                theatre = new Theatre(theatre_id,theatreName,totalScreens,totalCapacity,location,theatreAdminId);
                return theatre;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Theatre> getAllTheatres() {
        List<Theatre> theatres = new ArrayList<>();
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.GET_ALL_THEATRES);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Theatre theatre =null;
                int theatre_id = rs.getInt("theatre_id");
                String theatreName = rs.getString("name");
                int totalScreens = rs.getInt("total_screens");
                int totalCapacity = rs.getInt("total_capacity");
                String location = rs.getString("location");
                int theatreAdminId = rs.getInt("theatreAdmin_id");
                theatre = new Theatre(theatre_id,theatreName,totalScreens,totalCapacity,location,theatreAdminId);
                theatres.add(theatre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return theatres;
    }

    @Override
    public boolean updateTheatre(Theatre theatre) {
        try {
            PreparedStatement ps = Database.getPreparedStatement(SQLQueries.UPDATE_THEATRE);
            ps.setString(1, theatre.getName());
            ps.setInt(2, theatre.getTotalScreens());
            ps.setInt(3, theatre.getTotalCapacity());
            ps.setInt(4, theatre.getTheatreAdmin_id());
            ps.setInt(5, theatre.getTheatreId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeTheatre(int theatreId) {
        try {
            PreparedStatement ps =Database.getPreparedStatement(SQLQueries.REMOVE_THEATRE);
            ps.setInt(1, theatreId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public int getBookingCount(int theatreId, int movieId) {
        try (PreparedStatement statement = Database.getPreparedStatement(SQLQueries.GET_BOOKING_COUNT)) {
            statement.setInt(1, theatreId);
            statement.setInt(2, movieId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("booking_count");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public List<String> getAllLocations() {
        List<String> locations = new ArrayList<>();
        String query = "SELECT location FROM theatres";

        try (Statement statement = Database.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String location = resultSet.getString("location");
                locations.add(location);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return locations;
    }

    public List<Theatre> getTheatresByMovieAndLocation(int movieId, String location) {
        List<Theatre> theatres = new ArrayList<>();
        try (PreparedStatement statement = Database.getPreparedStatement(SQLQueries.GET_THEATERS_BY_MOVIE_AND_LOCATION)) {

            statement.setInt(1, movieId);
            statement.setString(2, location);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int theatreId = resultSet.getInt("theatre_id");
                    String name = resultSet.getString("name");
                    int totalScreens = resultSet.getInt("total_screens");
                    int totalCapacity = resultSet.getInt("total_capacity");
                    int theatreAdminId = resultSet.getInt("theatreAdmin_id");

                    Theatre theatre = new Theatre(theatreId, name, totalScreens, totalCapacity, location, theatreAdminId);
                    theatres.add(theatre);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return theatres;
    }

    public List<Theatre> getTheatresByLocation(String location) {
        List<Theatre> theatres = new ArrayList<>();

        try (PreparedStatement statement = Database.getPreparedStatement(SQLQueries.GET_THEATRES_BY_LOCATION)) {

            statement.setString(1, location);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int theatreId = resultSet.getInt("theatre_id");
                    String name = resultSet.getString("name");
                    int totalScreens = resultSet.getInt("total_screens");
                    int totalCapacity = resultSet.getInt("total_capacity");
                    int theatreAdminId = resultSet.getInt("theatre_admin_id");

                    Theatre theatre = new Theatre(theatreId, name, totalScreens, totalCapacity, location, theatreAdminId);
                    theatres.add(theatre);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return theatres;
    }

    public List<ShowTime> getShowtimesByMovieTheatreDate(int movieId, int theatreId) {
        List<ShowTime> showtimes = new ArrayList<>();

        try (Connection connection = Database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQLQueries.GET_SHOWTIMES)) {

            preparedStatement.setInt(1, movieId);
            preparedStatement.setInt(2, theatreId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int showtimeId = resultSet.getInt("showtime_Id");
                int showNumber = resultSet.getInt("show_Number");
                Time showTime = resultSet.getTime("show_Time");
                showtimes.add(new ShowTime(showtimeId, showNumber, showTime));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return showtimes;
    }

    public List<Seat> getAvailableSeats(int theatreId, int movieId, int showtimeId, Date date) {
        List<Seat> availableSeats = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.getPreparedStatement(SQLQueries.GET_AVAILABLE_TICKETS)) {

            preparedStatement.setInt(1, theatreId);
            preparedStatement.setInt(2, movieId);
            preparedStatement.setInt(3, showtimeId);
            preparedStatement.setDate(4, date);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int seatId = resultSet.getInt("seat_id");
                String seatNumber = resultSet.getString("seat_number");
                availableSeats.add(new Seat(seatId, seatNumber, Seat.Status.AVAILABLE));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return availableSeats;
    }

}
