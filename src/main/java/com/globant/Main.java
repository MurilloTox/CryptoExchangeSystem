package com.globant;

import com.globant.controler.RootController;
import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        /*User user1=new User("asdfaw", "awdawd", "adweaD", "qwdwqe");
        User user2=new User("asdfaw", "awdawd", "adweaD", "qwdwqe");
        User user3=new User("asdfaw", "awdawd", "adweaD", "qwdwqe");
        User user4=new User("asdfaw", "awdawd", "adweaD", "qwdwqe");
        User user5=new User("asdfaw", "awdawd", "adweaD", "qwdwqe");
        User user6=new User("asdfaw", "awdawd", "adweaD", "qwdwqe");

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);
        System.out.println(user4);
        System.out.println(user5);
        System.out.println(user6);

        System.out.println(user1.getDigitalWallet());
        BigDecimal bd=new BigDecimal("234.34");
        user1.depositMoney(bd);
        System.out.println(user1.getDigitalWallet());*/
        ConsoleView consoleView = ConsoleView.getInstance();
        SystemService systemService = SystemService.getInstance();
        RootController rootController = RootController.getInstance(consoleView, systemService);
        rootController.run();


    }
}