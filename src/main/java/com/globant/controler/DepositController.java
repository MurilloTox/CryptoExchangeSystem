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
            systemService.deposit(RootController.getInstance().getCurrentUser(), amount);
            view.showSuccessMessage("The deposit was successful.");
        } catch (UnknownUserException e) {
            System.out.println("Error al realizar el deposito.");
            RootController.getInstance().runLogged();
        }
    }
}
