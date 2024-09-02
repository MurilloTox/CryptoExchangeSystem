package com.globant;

import com.globant.controler.RootController;
import com.globant.model.Bitcoin;
import com.globant.model.Ethereum;
import com.globant.model.SellOrder;
import com.globant.model.User;
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
        ConsoleView consoleView = ConsoleView.getInstance();
        SystemService systemService = SystemService.getInstance();
        OrdersService ordersService = OrdersService.getInstance();
        ordersService.addOrder(sellOrder1);
        ordersService.addOrder(sellOrder2);
        ConsoleLoggedView consoleLoggedView = ConsoleLoggedView.getInstance();
        RootController rootController = RootController.getInstance(consoleView, systemService, consoleLoggedView, ordersService);
        rootController.run();


    }
}