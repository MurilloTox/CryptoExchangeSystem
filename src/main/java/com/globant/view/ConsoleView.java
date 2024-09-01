package com.globant.view;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner = new Scanner(System.in);
    private static final int INVALID_CHOICE = -1;
    private static ConsoleView instance;

    private ConsoleView(){}

    public static ConsoleView getInstance(){
        if(instance == null){
            instance = new ConsoleView();
        }
        return instance;
    }

    public int getUserChoice() {
        System.out.println("Choose one option to continue:");
        System.out.println("1. Create a new user");
        System.out.println("2. Are you a user already? Then login");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public String[] getRegisterUser() {
        String[] info = new String[4];

        System.out.println("Enter your name: ");
        String name = scanner.next();
        scanner.nextLine();

        System.out.println("Enter your Lastname: ");
        String lastName = scanner.next();
        scanner.nextLine();

        System.out.println("Enter your email: ");
        String email = scanner.next();
        scanner.nextLine();

        System.out.println("Create your password: ");
        String password = scanner.nextLine();

        info[0] = name;
        info[1] = lastName;
        info[2] = email;
        info[3] = password;

        return info;
    }

    public String[] getLoginUser() {
        String[] info = new String[2];
        System.out.println("Enter your email: ");
        String email = scanner.next();
        scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.next();
        info[0] = email;
        info[1] = password;
        return info;
    }

    public int getNotVerifiedUserChoice() {
        System.out.println("Choose one option to continue:");
        System.out.println("1. Try again");
        System.out.println("2. Go back");
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showSuccessMessage(String s) {
        System.out.println(s);
    }
}
