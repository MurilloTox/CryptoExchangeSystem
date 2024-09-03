package com.globant.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {
    private static ConsoleView instance;
    private final Scanner scanner = new Scanner(System.in);
    private static final int INVALID_CHOICE = -1;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    private ConsoleView(){}

    public static ConsoleView getInstance(){
        if(instance == null){
            instance = new ConsoleView();
        }
        return instance;
    }

    public int getUserChoice() {
        separation();
        System.out.println("Choose one option to continue:");
        System.out.println("1. Create a new user");
        System.out.println("2. Are you a user already? Then login");
        System.out.println("3. Quit");
        separation();
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
        separation();
        System.out.print("Enter your name: ");
        String name = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your Lastname: ");
        String lastName = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.next();
        scanner.nextLine();

        System.out.print("Create your password: ");
        String password = scanner.nextLine();

        info[0] = name;
        info[1] = lastName;
        info[2] = email;
        info[3] = password;

        separation();
        return info;
    }

    public String[] getLoginUser() {
        String[] info = new String[2];
        separation();
        System.out.print("Enter your email: ");
        String email = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.next();

        info[0] = email;
        info[1] = password;

        separation();
        return info;
    }

    public void separation(){
        System.out.println(ANSI_YELLOW + "---------------------------------------" + ANSI_RESET);
    }

    public void showError(String s) {
        System.out.println(ANSI_RED + s + ANSI_RESET);
    }

    public void showSuccessMessage(String s) {
        System.out.println(ANSI_GREEN + s + ANSI_RESET);
    }
}
