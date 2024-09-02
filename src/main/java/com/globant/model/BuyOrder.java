package com.globant.model;

import java.math.BigDecimal;

public class BuyOrder extends Order {
    private int orderId;
    private static int count = 0;

    public BuyOrder(User user, CrytoCurrency crytoCurrency,
                     BigDecimal cryptoAmount, BigDecimal maxPrice) {
        super(user, crytoCurrency, cryptoAmount, maxPrice);
        this.orderId = count++;
    }

    public int getOrderId() {
        return orderId;
    }
}
