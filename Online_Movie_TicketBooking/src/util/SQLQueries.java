package util;

public class SQLQueries {
    public static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";;
    public static final String INSERT_USER  = "INSERT INTO users (name, username, password,email , mobile ,role) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE users SET username = ?, password = ?, role = ?, mobile_number = ?, email_id = ? WHERE user_id = ?";
    public static final String GET_USER_BY_ID =  "SELECT * FROM users WHERE user_id = ?";
    public static final String GET_USER_BY_USERNAME_AND_PASSWORD =  "SELECT * FROM users WHERE username = ? AND password = ?";
    public static final String GET_USERS = "SELECT * FROM users";
    public static final String INSERT_MOVIE= "INSERT INTO movies (name, type, language, duration) VALUES (?, ?, ?, ?)";
     public static final String GET_MOVIE_BY_ID = "SELECT * FROM movies WHERE movie_id = ?";
     public static final String GET_ALL_MOVIES = "SELECT * FROM movies";
     public static final String UPDATE_MOVIE = "UPDATE movies SET name = ?, type = ?, language = ?, duration = ? WHERE movie_id = ?";
     public static final String DELETE_MOVIE = "DELETE FROM movies WHERE movie_id = ?";
    public static final String REMOVE_SHEDULED_MOVIE = "UPDATE movie_in_theatre SET status = 'inactive' WHERE theatre_id = ? AND movie_id = ? AND showtime_id = ?";;
    public static final String GET_MOVIES_BY_THEATRE =  "SELECT m.movie_id, m.movie_name, m.movie_type, m.language, m.duration " +
            "FROM movies m " +
            "JOIN movie_in_theatre mit ON m.movie_id = mit.movie_id " +
            "WHERE mit.theatre_id = ? AND mit.status = 'active'";
    public static final String INSERT_THEATRE = "INSERT INTO theatres (name, total_screens, total_capacity, location, theatreAdmin_id) VALUES (?, ?, ?, ?, ?)";
    public static final String GET_THEATRE_BY_ID =  "SELECT * FROM theatres WHERE theatre_id = ?";
    public static final String GET_ALL_THEATRES = "SELECT * FROM theatres";
    public static final String UPDATE_THEATRE = "UPDATE theatres SET name = ?, total_screens = ?, total_capacity = ?, theatreAdmin_id = ? WHERE theatre_id = ?";
    public static final String REMOVE_THEATRE = "DELETE FROM theatres WHERE theatre_id = ?";
    public static final String GET_BOOKING_COUNT =  "SELECT COUNT(*) AS booking_count " +
            "FROM seats s " +
            "JOIN movie_in_theatre mit ON s.movie_in_theatre_id = mit.id " +
            "WHERE mit.theatre_id = ? AND mit.movie_id = ? AND s.status = 'BOOKED'";
    public static final String INSERT_TICKET = "INSERT INTO tickets (user_id, showtime_id, seat_id, movie_id, theatre_id, status, booking_date, booking_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String GET_TICKET_BY_ID = "SELECT * FROM tickets WHERE ticket_id = ?";
    public static final String GET_TICKET_BY_USERID = "SELECT * FROM tickets WHERE user_id = ?";
    public static final String UPDATE_TICKET_STATUS =  "UPDATE tickets SET status = ? WHERE ticket_id = ?";
     public static final String GET_THEATERS_BY_MOVIE_AND_LOCATION = "SELECT t.* FROM theatres t JOIN movie_in_theatre mit ON t.theatre_id = mit.theatre_id WHERE mit.movie_id = ? AND t.location = ?";
    public static final String GET_THEATRES_BY_LOCATION = "SELECT * FROM theatre WHERE location = ?";
    public static final String GET_SHOWTIMES =  "SELECT st.showtime_id, st.show_number, st.show_time FROM movie_in_theatre mit JOIN show_timing st ON mit.showtime_id = st.showtime_id WHERE mit.movie_id = ? AND mit.theatre_id = ? AND mit.status = 'ACTIVE'";
    public static final String GET_AVAILABLE_TICKETS =  "SELECT seat_id, seat_number FROM Seats WHERE theatre_id = ? AND movie_id = ? AND showtime_id = ? AND seat_date = ? AND status = 'AVAILABLE'";;
}

