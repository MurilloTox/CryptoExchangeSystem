package com.globant;

import com.globant.controler.RootController;
import com.globant.model.Bitcoin;
import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println(Bitcoin.getInstance());

        ConsoleView consoleView = ConsoleView.getInstance();
        SystemService systemService = SystemService.getInstance();
        ConsoleLoggedView consoleLoggedView = ConsoleLoggedView.getInstance();
        RootController rootController = RootController.getInstance(consoleView, systemService, consoleLoggedView);
        rootController.run();


    }
}