package com.globant.controler;

import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;

public class ViewWalletBalanceController {
    private final ConsoleLoggedView view;
    private final SystemService systemService;
    private static ViewWalletBalanceController instance;

    private ViewWalletBalanceController(ConsoleLoggedView view, SystemService systemService) {
        this.view = view;
        this.systemService = systemService;
    }

    public static ViewWalletBalanceController getInstance() {
        if (instance == null) {
            instance = new ViewWalletBalanceController(ConsoleLoggedView.getInstance(), SystemService.getInstance());
        }
        return instance;
    }

    public void execute() {

    }
}
