package com.globant.controler;

import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;

public class ExchangeController {
    private final ConsoleLoggedView view;
    private final SystemService systemService;
    private static ExchangeController instance;

    private ExchangeController(ConsoleLoggedView view, SystemService systemService) {
        this.view = view;
        this.systemService = systemService;
    }

    public static ExchangeController getInstance(ConsoleLoggedView view, SystemService systemService) {
        if (instance == null) {
            instance = new ExchangeController(view, systemService);
        }
        return instance;
    }

    public void execute() {

    }
}
