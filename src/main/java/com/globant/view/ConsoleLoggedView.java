package com.globant.view;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleLoggedView {
    private final Scanner scanner = new Scanner(System.in);
    private static final int INVALID_CHOICE = -1;
    private static ConsoleLoggedView instance;

    private ConsoleLoggedView(){}

    public static ConsoleLoggedView getInstance(){
        if(instance == null){
            instance = new ConsoleLoggedView();
        }
        return instance;
    }
    public int getUserLoggedChoice() {
        System.out.println("Choose one option to continue:");
        System.out.println("1. Deposit money");
        System.out.println("2. View wallet balance");
        System.out.println("3. Buy Cryptocurrencies from the Exchange");
        System.out.println("4. Place Buy Order");
        System.out.println("5. Place Sell Order");
        System.out.println("6. View Transaction History");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public BigDecimal getAmountInput() {
        try {
            System.out.print("Enter the inicialAmount: ");
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e) {
            System.out.println("Invalid inicialAmount format. Please enter a valid number.");
            scanner.nextLine();
            return getAmountInput();
        }
    }

    public void showUserBalance(String s) {
        System.out.println(s);
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void showSuccessMessage(String s) {
        System.out.println(s);
    }

}
