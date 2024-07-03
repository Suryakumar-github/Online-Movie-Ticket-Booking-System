package controller;

import dao.MovieDAOImplementation;
import dao.TheatreDAOImplementation;
import dao.UserDAOImplementation;
import model.Movie;
import model.Theatre;
import model.User;
import view.AdminView;

import java.util.List;
import java.util.Scanner;

public class AdminController {
    private Scanner scanner = new Scanner(System.in);
    private User loggedInAdmin;
    private UserDAOImplementation userDao = new UserDAOImplementation();
    private MovieDAOImplementation movieDao = new MovieDAOImplementation();
    private TheatreDAOImplementation theatreDao = new TheatreDAOImplementation();
    private AdminView adminView = new AdminView();

    public AdminController(){

    }
    public void handleAdminOptions() {
        while (true) {
            if (loggedInAdmin == null) {
                showInitialMenu();
            } else {
                showAdminMenu();
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

    private void showAdminMenu() {
        System.out.println("1. Add Admin");
        System.out.println("2. Add Theatre Admin");
        System.out.println("3. Add Movie");
        System.out.println("4. Remove Movie");
        System.out.println("5. Add Theatre");
        System.out.println("6. Remove Theatre");
        System.out.println("7. View All Movies");
        System.out.println("8. View All Theatres");
        System.out.println("9. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addAdmin();
                break;
            case 2:
                addTheatreAdmin();
                break;
            case 3:
                addMovie();
                break;
            case 4:
                removeMovie();
                break;
            case 5:
                addTheatre();
                break;
            case 6:
                removeTheatre();
                break;
            case 7:
                viewAllMovies();
                break;
            case 8:
                viewAllTheatres();
                break;
            case 9:
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
        if (user != null && user.getRole().equals(User.Role.ADMIN)) {
            loggedInAdmin = user;
            System.out.println("Login successful.");
        } else {
            System.out.println("Invalid username, password, or role.");
        }
    }

    private void addAdmin() {
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Enter email ID: ");
        String emailId = scanner.nextLine();

        User user = new User(name, username, password, emailId, mobileNumber, User.Role.ADMIN);
        if (userDao.addUser(user)) {
            System.out.println("Admin added successfully.");
        } else {
            System.out.println("Failed to add admin.");
        }
    }

    private void addTheatreAdmin() {
        System.out.println("Enter name");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter mobile number: ");
        String mobileNumber = scanner.nextLine();
        System.out.print("Enter email ID: ");
        String emailId = scanner.nextLine();

        User user = new User(name, username, password, emailId, mobileNumber, User.Role.THEATRE_ADMIN);
        if (userDao.addUser(user)) {
            System.out.println("Theatre admin added successfully.");
        } else {
            System.out.println("Failed to add theatre admin.");
        }
    }

    private void addMovie() {
        System.out.print("Enter movie name: ");
        String name = scanner.nextLine();
        System.out.print("Enter movie type (ACTION, ROMANCE, COMEDY, HORROR): ");
        Movie.MovieType type = Movie.MovieType.valueOf(scanner.nextLine().toLowerCase());
        System.out.print("Enter movie language (TAMIL, TELUGU, HINDI): ");
        Movie.Language language = Movie.Language.valueOf(scanner.nextLine().toLowerCase());
        System.out.print("Enter movie duration (in minutes): ");
        float duration = scanner.nextFloat();
        scanner.nextLine();

        Movie movie = new Movie(name, type, language, duration);
        if (movieDao.addMovie(movie)) {
            System.out.println("Movie added successfully.");
        } else {
            System.out.println("Failed to add movie.");
        }
    }

    private void removeMovie() {
        System.out.print("Enter movie ID to remove: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();

        if (movieDao.removeMovie(movieId)) {
            System.out.println("Movie removed successfully.");
        } else {
            System.out.println("Failed to remove movie.");
        }
    }

    private void addTheatre() {
        System.out.print("Enter theatre name: ");
        String name = scanner.nextLine();
        System.out.print("Enter total screens: ");
        int totalScreens = scanner.nextInt();
        System.out.print("Enter total capacity: ");
        int totalCapacity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the location: ");
        String location = scanner.nextLine();
        System.out.print("Enter theatre admin ID: ");
        int theatreAdminId = scanner.nextInt();
        scanner.nextLine();

        Theatre theatre = new Theatre(name, totalScreens, totalCapacity, location, theatreAdminId);
        if (theatreDao.addTheatre(theatre)) {
            System.out.println("Theatre added successfully.");
        } else {
            System.out.println("Failed to add theatre.");
        }
    }

    private void removeTheatre() {
        System.out.print("Enter theatre ID to remove: ");
        int theatreId = scanner.nextInt();
        scanner.nextLine();

        if (theatreDao.removeTheatre(theatreId)) {
            System.out.println("Theatre removed successfully.");
        } else {
            System.out.println("Failed to remove theatre.");
        }
    }

    private void viewAllMovies() {
        List<Movie> movies = movieDao.getAllMovies();
        adminView.displayMovies(movies);
    }

    private void viewAllTheatres() {
        List<Theatre> theatres = theatreDao.getAllTheatres();
       adminView.displayTheatres(theatres);
    }

    private void logout() {
        loggedInAdmin = null;
        System.out.println("Logged out successfully.");
    }
}
