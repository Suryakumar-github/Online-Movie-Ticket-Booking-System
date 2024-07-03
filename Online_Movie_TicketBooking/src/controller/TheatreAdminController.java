package controller;

import dao.MovieDAOImplementation;
import dao.TheatreDAOImplementation;
import dao.UserDAOImplementation;
import model.Movie;
import model.MovieInTheatre;
import model.User;
import view.TheatreAdminView;

import java.util.List;
import java.util.Scanner;

public class TheatreAdminController {
    private Scanner scanner = new Scanner(System.in);
    private User loggedInTheatreAdmin;
    private UserDAOImplementation userDao;
    private MovieDAOImplementation movieDao;
    private TheatreDAOImplementation theatreDao;
    private TheatreAdminView theatreAdminView;

    public TheatreAdminController(){

    }
    public void handleTheatreAdminOptions() {
        while (true) {
            if (loggedInTheatreAdmin == null) {
                showInitialMenu();
            } else {
                showTheatreAdminMenu();
            }
        }
    }

    private void showInitialMenu() {
        System.out.println("1. Login");
        System.out.println("2. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void showTheatreAdminMenu() {
        System.out.println("1. Schedule Movie");
        System.out.println("2. Remove Scheduled Movie");
        System.out.println("3. View Movies by Theatre");
        System.out.println("4. View Booking Count");
        System.out.println("5. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                scheduleMovie();
                break;
            case 2:
                removeScheduledMovie();
                break;
            case 3:
                viewMoviesByTheatre();
                break;
            case 4:
                viewBookingCount();
                break;
            case 5:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDao.loginUser(username, password);
        if (user != null && user.getRole() == User.Role.THEATRE_ADMIN) {
            loggedInTheatreAdmin = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username, password, or role.");
        }
    }

    private void scheduleMovie() {
        List<Movie> movies = movieDao.getAllMovies();
        theatreAdminView.displayMovies(movies);

        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter showtime ID: ");
        int showtimeId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the price for the ticket: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        MovieInTheatre movieInTheatre = new MovieInTheatre(theatreId, movieId, showtimeId, price, MovieInTheatre.Status.ACTIVE);
        if (movieDao.sheduleMovie(movieInTheatre)) {
            System.out.println("Movie scheduled successfully.");
        } else {
            System.out.println("Failed to schedule movie.");
        }
    }

    private void removeScheduledMovie() {
        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter showtime ID: ");
        int showtimeId = scanner.nextInt();
        scanner.nextLine();

        MovieInTheatre movieInTheatre = new MovieInTheatre(theatreId, movieId, showtimeId, 0.0, MovieInTheatre.Status.INACTIVE);
        if (movieDao.removeScheduledMovie(movieInTheatre)) {
            System.out.println("Scheduled movie removed successfully.");
        } else {
            System.out.println("Failed to remove scheduled movie.");
        }
    }

    private void viewMoviesByTheatre() {
        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        List<Movie> movies = movieDao.getMoviesByTheatre(theatreId);
        theatreAdminView.displayMovies(movies);
    }

    private void viewBookingCount() {
        System.out.print("Enter theatre ID: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        int count = theatreDao.getBookingCount(theatreId, movieId);
        System.out.println("Booking Count: " + count);
    }

    private void logout() {
        loggedInTheatreAdmin = null;
        System.out.println("Logged out successfully.");
    }
}
