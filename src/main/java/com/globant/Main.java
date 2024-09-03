package com.globant;

import com.globant.controler.RootController;
import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

public class Main {
    public static void main(String[] args) {

        ConsoleView consoleView = ConsoleView.getInstance();
        //OrdersService ordersService = OrdersService.getInstance();
        //ConsoleLoggedView consoleLoggedView = ConsoleLoggedView.getInstance();
        RootController rootController = RootController.getInstance(consoleView);
        //RootLoggedController rootLoggedController = RootLoggedController.getInstance(consoleLoggedView, systemService, ordersService);
        rootController.run();

    }
}