package com.globant;

import com.globant.controler.RootController;
import com.globant.model.*;
import com.globant.service.OrdersService;
import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //System.out.println(Bitcoin.getInstance());
        User user = new User("Josue", "Murillo", "murillo@gmail.com", "Murillo");
        SellOrder sellOrder1 = new SellOrder(user, Bitcoin.getInstance(), new BigDecimal(10), new BigDecimal("15000.60"));
        SellOrder sellOrder2 = new SellOrder(user, Ethereum.getInstance(), new BigDecimal(5), new BigDecimal("5000.75"));
        BuyOrder buyOrder1 = new BuyOrder(user, Bitcoin.getInstance(), new BigDecimal(1), new BigDecimal("7500.01"));
        BuyOrder buyOrder2 = new BuyOrder(user, Ethereum.getInstance(), new BigDecimal(15), new BigDecimal("25000.5"));
        ConsoleView consoleView = ConsoleView.getInstance();
        SystemService systemService = SystemService.getInstance();
        OrdersService ordersService = OrdersService.getInstance();
        ordersService.addOrder(sellOrder1);
        ordersService.addOrder(sellOrder2);
        ordersService.addOrder(buyOrder1);
        ordersService.addOrder(buyOrder2);
        ConsoleLoggedView consoleLoggedView = ConsoleLoggedView.getInstance();
        RootController rootController = RootController.getInstance(consoleView, systemService, consoleLoggedView, ordersService);
        rootController.run();

    }
}