package com.globant.controler;

import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

public class RootController {
    private static RootController instance;
    private final ConsoleView view;
    //private User currentUser;
    //private final ConsoleLoggedView viewLogged;
    private final SystemService systemService;
    private final RegisterUserController registerUserController;
    private final LoginUserController loginUserController;
    /*private final ViewWalletBalanceController viewWalletBalanceController;
    private final DepositController depositController;
    private final ExchangeController exchangeController;
    private final OrdersService ordersService;
    private final PlaceBuyOrderController placeBuyOrderController;
    private final PlaceSellOrderController placeSellOrderController;
    private final ViewTransactionHistory viewTransactionHistory;*/




    private RootController(ConsoleView view, SystemService systemService) {
        this.view = view;
        this.systemService = systemService;
        //this.ordersService = ordersService;
        //this.viewLogged = viewLogged;

        this.registerUserController = RegisterUserController.getInstance(view, systemService);
        this.loginUserController = LoginUserController.getInstance(view);
        /*this.viewWalletBalanceController = ViewWalletBalanceController.getInstance(viewLogged, systemService);
        this.depositController = DepositController.getInstance(viewLogged, systemService);
        this.exchangeController = ExchangeController.getInstance(viewLogged, systemService);
        this.placeBuyOrderController = PlaceBuyOrderController.getInstance(viewLogged, ordersService);
        this.placeSellOrderController = PlaceSellOrderController.getInstance(viewLogged, ordersService);
        this.viewTransactionHistory = ViewTransactionHistory.getInstance(viewLogged, ordersService);
         */
    }

    public static RootController getInstance(ConsoleView view, SystemService systemService) {
        if (instance == null) {
            instance = new RootController(view, systemService);
        }
        return instance;
    }

    public static RootController getInstance(){
        if (instance == null) {
            instance = getInstance(ConsoleView.getInstance(), SystemService.getInstance());
        }
        return instance;
    }

    //Hacer que los metodos excute sean protected

    public void run(){
        while(true){
            int choice = view.getUserChoice();
            System.out.println(" ");
            switch(choice){
                case 1:
                    registerUserController.execute();
                    break;
                case 2:
                    boolean notlogged = loginUserController.execute();
                    if(notlogged){
                        run();
                    } else {
                        RootLoggedController.getInstance().runLogged();
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    view.showError("Invalid option. Please try again.");
            }
        }
    }

    /*public void runLogged() {
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
                    placeBuyOrderController.execute();
                    break;
                case 5:
                    placeSellOrderController.execute();
                    break;
                case 6:
                    viewTransactionHistory.execute();
                    break;
                case 7:
                    view.showSuccessMessage("You have logged out.");
                    setCurrentUser(null);
                    run();
                    break;
                default:
                    viewLogged.showError("Invalid option. Please try again.");
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    protected void setCurrentUser(User user) {
        this.currentUser = user;
    }*/

}
