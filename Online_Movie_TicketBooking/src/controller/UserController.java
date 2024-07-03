package controller;

import dao.MovieDAOImplementation;
import dao.TheatreDAOImplementation;
import dao.TicketDAOImplementation;
import dao.UserDAOImplementation;
import model.*;
import view.UserView;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

public class UserController {
    UserView userView;
    private static Scanner scanner = new Scanner(System.in);
    private User loggedInUser;
    private UserDAOImplementation userDao;
    private TicketDAOImplementation ticketDao;
    private MovieDAOImplementation movieDao;
    private TheatreDAOImplementation theatreDao;

    public UserController() {
        userDao = new UserDAOImplementation();
        ticketDao = new TicketDAOImplementation();
        movieDao = new MovieDAOImplementation();
        theatreDao = new TheatreDAOImplementation();
        userView = new UserView();
    }

    public void handleUserOptions() {
        while (true) {
            if (loggedInUser == null) {
                showInitialMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private void showInitialMenu() {
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showUserMenu() {
        System.out.println("1. View Tickets");
        System.out.println("2. Book Ticket");
        System.out.println("3. Cancel Ticket");
        System.out.println("4. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                viewTickets();
                break;
            case 2:
                bookTicket();
                break;
            case 3:
                cancelTicket();
                break;
            case 4:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void register() {
        System.out.println("Enter the name : ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Enter email ID: ");
        String emailId = scanner.nextLine();

        User user = new User(name, username, password, emailId, mobileNumber, User.Role.USER);
        if (userDao.addUser(user)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDao.loginUser(username, password);
        if (user != null) {
            loggedInUser = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void viewTickets() {
        if (loggedInUser == null) {
            System.out.println("Please login first.");
            return;
        }

        List<Ticket> tickets = ticketDao.getTicketsByUserId(loggedInUser.getUserId());
        userView.displayTickets(tickets);
    }

    private void bookTicket() {
        if (loggedInUser == null) {
            System.out.println("Please login first.");
            return;
        }

        //  Display all locations and choose one
        List<String> locations = theatreDao.getAllLocations();
        userView.displayAllLocations(locations);

        System.out.print("Enter location: ");
        String location = scanner.nextLine();

        System.out.println("Do you want to book by: ");
        System.out.println("1. Movie Name");
        System.out.println("2. Theatre");
        int choice = scanner.nextInt();
        scanner.nextLine();

        int movieId = 0;
        int theatreId = 0;

        if (choice == 1) {
            List<Movie> movies = movieDao.getAllMovies();
            userView.displayAllMovies(movies);

            System.out.print("Enter movie ID: ");
            movieId = scanner.nextInt();
            scanner.nextLine();

            List<Theatre> theatres = theatreDao.getTheatresByMovieAndLocation(movieId, location);
            userView.displayTheatres(theatres);

            System.out.print("Enter theatre ID: ");
            theatreId = scanner.nextInt();
            scanner.nextLine();
        } else if (choice == 2) {
            List<Theatre> theatres = theatreDao.getTheatresByLocation(location);
            userView.displayTheatresInLocation(theatres);

            System.out.print("Enter theatre ID: ");
            theatreId = scanner.nextInt();
            scanner.nextLine();

            List<Movie> movies = movieDao.getMoviesByTheatre(theatreId);
            userView.displayAllMoviesInTheatre(movies);

            System.out.print("Enter movie ID: ");
            movieId = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("Invalid choice. Please try again.");
            return;
        }

        //  Ask for date
        System.out.print("Enter date (YYYY-MM-DD): ");
        Date date = Date.valueOf(scanner.nextLine());

        //  Show available showtimes for the selected movie, theatre, and date
        List<ShowTime> showtimes = theatreDao.getShowtimesByMovieTheatreDate(movieId, theatreId);
        userView.displayShowtimes(showtimes);

        System.out.print("Enter showtime ID: ");
        int showtimeId = scanner.nextInt();
        scanner.nextLine();

        //Show available seats for the selected showtime
        List<Seat> availableSeats = theatreDao.getAvailableSeats(theatreId, movieId, showtimeId,date);
        userView.displayAvailableSeats(availableSeats);

        System.out.print("Enter the seat ID: ");
        int seatId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter time (HH:MM:SS): ");
        Time time = Time.valueOf(scanner.nextLine());

        Ticket ticket = new Ticket(loggedInUser.getUserId(), showtimeId, seatId, movieId, theatreId, Ticket.Status.BOOKED, date, time);
        if (ticketDao.addTicket(ticket)) {
            System.out.println("Ticket booked successfully.");
        } else {
            System.out.println("Failed to book ticket.");
        }
    }


    private void cancelTicket() {
        if (loggedInUser == null) {
            System.out.println("Please login first.");
            return;
        }

        System.out.print("Enter ticket ID to cancel: ");
        int ticketId = scanner.nextInt();
        scanner.nextLine();

        if (ticketDao.cancelTicket(ticketId, loggedInUser.getUserId())) {
            System.out.println("Ticket cancelled successfully.");
        } else {
            System.out.println("Failed to cancel ticket.");
        }
    }

    private void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }
}
