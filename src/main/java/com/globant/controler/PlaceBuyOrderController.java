package com.globant.controler;

import com.globant.service.OrdersService;
import com.globant.view.ConsoleLoggedView;

import java.math.BigDecimal;

public class PlaceBuyOrderController {
    private static PlaceBuyOrderController instance;
    private final ConsoleLoggedView view;
    private final OrdersService ordersService;

    private PlaceBuyOrderController(ConsoleLoggedView view, OrdersService ordersService) {
        this.view = view;
        this.ordersService = ordersService;
    }

    public static PlaceBuyOrderController getInstance(ConsoleLoggedView view, OrdersService ordersService) {
        if (instance == null) {
            instance = new PlaceBuyOrderController(view, ordersService);
        }
        return instance;
    }

    protected void execute() {
        BigDecimal maxValue = view.getAmountSellMaxInput();
        BigDecimal minValue = view.getAmountSellMinInput();

    }


}
