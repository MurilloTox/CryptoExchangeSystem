package com.globant.controler;

import com.globant.model.User;
import com.globant.service.OrdersService;
import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;

public class RootLoggedController {
    private static RootLoggedController instance;
    private final ConsoleLoggedView viewLogged;
    private User currentUser;
    private SystemService systemService;
    private OrdersService ordersService;
    private final ViewWalletBalanceController viewWalletBalanceController;
    private final DepositController depositController;
    private final ExchangeController exchangeController;
    private final PlaceBuyOrderController placeBuyOrderController;
    private final PlaceSellOrderController placeSellOrderController;
    private final ViewTransactionHistory viewTransactionHistory;

    private RootLoggedController(ConsoleLoggedView view, SystemService systemService, OrdersService ordersService) {
        this.viewLogged = view;
        this.systemService = systemService;
        this.ordersService = ordersService;
        this.viewWalletBalanceController = ViewWalletBalanceController.getInstance(viewLogged, systemService);
        this.depositController = DepositController.getInstance(viewLogged, systemService);
        this.exchangeController = ExchangeController.getInstance(viewLogged, systemService);
        this.placeBuyOrderController = PlaceBuyOrderController.getInstance(viewLogged, ordersService);
        this.placeSellOrderController = PlaceSellOrderController.getInstance(viewLogged, ordersService);
        this.viewTransactionHistory = ViewTransactionHistory.getInstance(viewLogged, ordersService);
    }

    public static RootLoggedController getInstance(ConsoleLoggedView view, SystemService systemService, OrdersService ordersService) {
        if (instance == null) {
            instance = new RootLoggedController(view, systemService, ordersService);
        }
        return instance;
    }

    public static RootLoggedController getInstance() {
        if (instance == null) {
            instance = new RootLoggedController(ConsoleLoggedView.getInstance(), SystemService.getInstance(), OrdersService.getInstance());
        }
        return instance;
    }

    protected void runLogged() {
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
                    viewLogged.showSuccessMessage("You have logged out.");
                    setCurrentUser(null);
                    RootController.getInstance().run();
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
    }
}
