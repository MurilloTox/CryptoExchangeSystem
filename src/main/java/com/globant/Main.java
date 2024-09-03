package com.globant;

import com.globant.controler.RootController;
import com.globant.controler.RootLoggedController;
import com.globant.model.*;
import com.globant.service.OrdersService;
import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;


public class Main {
    public static void main(String[] args) {

        ConsoleView consoleView = ConsoleView.getInstance();
        SystemService systemService = SystemService.getInstance();
        //OrdersService ordersService = OrdersService.getInstance();
        //ConsoleLoggedView consoleLoggedView = ConsoleLoggedView.getInstance();
        RootController rootController = RootController.getInstance(consoleView, systemService);
        //RootLoggedController rootLoggedController = RootLoggedController.getInstance(consoleLoggedView, systemService, ordersService);
        rootController.run();

    }
}