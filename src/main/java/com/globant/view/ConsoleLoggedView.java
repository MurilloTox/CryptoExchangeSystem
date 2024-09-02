package com.globant.view;

import com.globant.model.ExchangeStorage;
import com.globant.model.Order;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleLoggedView {
    private final Scanner scanner = new Scanner(System.in);
    private static final int INVALID_CHOICE = -1;
    private static ConsoleLoggedView instance;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    private ConsoleLoggedView(){}

    public static ConsoleLoggedView getInstance(){
        if(instance == null){
            instance = new ConsoleLoggedView();
        }
        return instance;
    }
    public int getUserLoggedChoice() {
        separation();
        System.out.println("Choose one option to continue:");
        System.out.println("1. Deposit money");
        System.out.println("2. View wallet balance");
        System.out.println("3. Buy Cryptocurrencies from the Exchange");
        System.out.println("4. Place Buy Order");
        System.out.println("5. Place Sell Order");
        System.out.println("6. View Transaction History");
        System.out.println("7. Logout");
        separation();
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public BigDecimal getAmountInput() {
        System.out.print("Enter the amount: ");
        try {
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
            scanner.nextLine();
            return getAmountInput();
        }
    }

    public void currentPrice(){
        System.out.println("Current market prices are: " + ExchangeStorage.getInstance().currentPrices());
    }

    public int getCryptoOption(){
        separation();
        System.out.println("Current storage: " + ExchangeStorage.getInstance().toString());
        currentPrice();
        System.out.println("Choose one option to continue:");
        System.out.println("1. Bitcoin");
        System.out.println("2. Ethereum");
        System.out.println("3. Go back");
        separation();
        System.out.print("Enter your choice: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return INVALID_CHOICE;
        }
    }

    public BigDecimal getAmountCryptoInput(){
        System.out.print("Enter the amount of Cryptocurrency you want: ");
        try {
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
            scanner.nextLine();
            return getAmountCryptoInput();
        }
    }

    public BigDecimal getBuyMoneyInput(){
        System.out.print("Enter the maximum amount of money you are willing to spend: ");
        try {
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
            scanner.nextLine();
            return getBuyMoneyInput();
        }
    }

    public BigDecimal getSellMoneyInput(){
        System.out.print("Enter the minimum amount of money you are willing to accept: ");
        try {
            return scanner.nextBigDecimal();
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount format. Please enter a valid number.");
            scanner.nextLine();
            return getBuyMoneyInput();
        }
    }

    public void separation(){
        System.out.println(ANSI_BLUE + "---------------------------------------" + ANSI_RESET);
    }

    public void showUserBalance(String s) {
        System.out.println(s);
    }

    public void showSystemMessage(String s) {
        System.out.println(ANSI_BLUE + s + ANSI_RESET);
    }

    public void showOrder(Order order){
        System.out.println(order);
    }

    public void showError(String s) {
        System.out.println(ANSI_RED + s + ANSI_RESET);
    }

    public void showSuccessMessage(String s) {
        System.out.println(ANSI_GREEN + s + ANSI_RESET);
    }

}
