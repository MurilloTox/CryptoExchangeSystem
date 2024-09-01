package com.globant.service;

import com.globant.view.ConsoleLoggedView;

import java.math.BigDecimal;

public class OrdersService {
    private static OrdersService instance;

    private OrdersService() {}

    public static OrdersService getInstance() {
        if (instance == null) {
            instance = new OrdersService();
        }
        return instance;
    }

    public boolean inRange(BigDecimal maxValue, BigDecimal minValue, BigDecimal value) {
        return (value.compareTo(maxValue) <= 0) && (value.compareTo(minValue) >= 0);
    }


}
