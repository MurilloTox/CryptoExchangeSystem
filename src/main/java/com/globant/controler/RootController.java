package com.globant.controler;

import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;
import com.globant.view.ConsoleView;

public class RootController {
    private static RootController instance;
    private final ConsoleView view;
    private User currentUser;
    private final ConsoleLoggedView viewLogged;
    private final SystemService systemService;
    private final RegisterUserController registerUserController;
    private final LoginUserController loginUserController;
    private final ViewWalletBalanceController viewWalletBalanceController;
    private final DepositController depositController;
    private final ExchangeController exchangeController;




    private RootController(ConsoleView view, SystemService systemService , ConsoleLoggedView viewLogged) {
        this.view = view;
        this.systemService = systemService;
        this.viewLogged = viewLogged;

        this.registerUserController = new RegisterUserController(view, systemService);
        this.loginUserController = new LoginUserController(view);
        this.viewWalletBalanceController = ViewWalletBalanceController.getInstance();
        this.depositController = DepositController.getInstance();
        this.exchangeController = new ExchangeController();
    }

    public static RootController getInstance(ConsoleView view, SystemService systemService, ConsoleLoggedView viewLogged) {
        if (instance == null) {
            instance = new RootController(ConsoleView.getInstance(), SystemService.getInstance(), ConsoleLoggedView.getInstance());
        }
        return instance;
    }

    public static RootController getInstance(){
        if (instance == null) {
            instance = getInstance(ConsoleView.getInstance(), SystemService.getInstance(), ConsoleLoggedView.getInstance());
        }
        return instance;
    }

    public void run(){
        while(true){
            int choice = view.getUserChoice();
            System.out.println(" ");
            switch(choice){
                case 1:
                    registerUserController.execute();
                    break;
                case 2:
                    loginUserController.execute();
                    runLogged();
                    break;
                case 3:
                    System.exit(0);
                default:
                    view.showError("Invalid option. Please try again.");
            }
        }
    }

    public void runLogged() {
        while (true) {
            int choice = viewLogged.getUserLoggedChoice();
            switch (choice) {
                case 1:
                    depositController.execute();
                    break;
                case 2:
                    viewWalletBalanceController.execute();
                    break;
                case 3:
                    exchangeController.execute();
                    break;
                case 4:
                    viewWalletBalanceController.execute();
                    break;
                case 5:
                    exchangeController.execute();
                    break;
                case 6:
                    viewWalletBalanceController.execute();
                    break;
                case 7:
                    System.exit(0);
                default:
                    view.showError("Invalid option. Please try again.");
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
