package com.globant.controler;

import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;

public class DepositController {
    private final ConsoleView view;
    private final SystemService systemServicestemSer;

    public DepositController(ConsoleView view, SystemService accountService){
        this.view = view;
        systemServicestemSer = accountService;
    }
    public void execute() {
        System.out.print("Enter deposit amount: ");
        /*try {
            BigDecimal amount = view.getAmountInput();
            SystemService.deposit(amount);
            view.showSuccessMessage("The deposit was successful.");
        }*/
    }
}
