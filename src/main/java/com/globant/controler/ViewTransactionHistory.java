package com.globant.controler;

import com.globant.model.Order;
import com.globant.model.User;
import com.globant.service.OrdersService;
import com.globant.view.ConsoleLoggedView;

public class ViewTransactionHistory {
    private static ViewTransactionHistory instance;
    private final ConsoleLoggedView view;
    private final OrdersService service;

    private ViewTransactionHistory(ConsoleLoggedView view, OrdersService service) {
        this.view = view;
        this.service = service;
    }

    protected static ViewTransactionHistory getInstance(ConsoleLoggedView view, OrdersService ordersService) {
        if (instance == null) {
            instance = new ViewTransactionHistory(view, ordersService);
        }
        return instance;
    }

    protected void execute() {
        User user = RootLoggedController.getInstance().getCurrentUser();
        view.showSuccessMessage("Your orders history: ");
        for (Order order:service.obtainUserHistory(user)){
            view.showOrder(order);
        }
    }
}
