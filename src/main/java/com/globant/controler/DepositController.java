package com.globant.controler;

import com.globant.service.SystemService;
import com.globant.service.UnknownUserException;
import com.globant.view.ConsoleLoggedView;

import java.math.BigDecimal;

public class DepositController {
    private final ConsoleLoggedView view;
    private final SystemService systemService;
    private static DepositController instance;

    private DepositController(ConsoleLoggedView view, SystemService systemService) {
        this.view = view;
        this.systemService = systemService;
    }

    public static DepositController getInstance(ConsoleLoggedView view, SystemService systemService) {
        if (instance == null) {
            instance = new DepositController(view, systemService);
        }
        return instance;
    }

    public void execute() {
        try {
            BigDecimal amount = view.getAmountInput();
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                systemService.deposit(RootController.getInstance().getCurrentUser(), amount);
                view.showSuccessMessage("The deposit was successful.");
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error while depositing, amount no valid.");
            RootController.getInstance().runLogged();
        } catch (UnknownUserException e) {
            System.out.println("Error to find the current user.");
            RootController.getInstance().runLogged();
        }
    }
}
