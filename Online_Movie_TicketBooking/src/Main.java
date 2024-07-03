import controller.AdminController;
import controller.TheatreAdminController;
import controller.UserController;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AdminController adminController = new AdminController();
    private static TheatreAdminController theatreAdminController = new TheatreAdminController();
    private static UserController userController = new UserController();

    public static void main(String[] args) {
        start();
    }
    public static void start(){
        while(true){
            displayMainmenu();
            int choice =scanner.nextInt();
            switch (choice){
                case 1 :
                    adminController.handleAdminOptions();
                    break;

                case 2 :
                    theatreAdminController.handleTheatreAdminOptions();
                    break;

                case 3 :
                    userController.handleUserOptions();
                    break;

                case 4 :
                    System.exit(0);

                default:
                    System.out.println("Invalid choice , Please try Again");
            }
        }
    }

    private static void displayMainmenu() {
        System.out.println("Enter the choice ");
        System.out.println("1 : Admin");
        System.out.println("2 : TheatreAdmin");
        System.out.println("3 : User");
        System.out.println("4 : Exit");

    }
}