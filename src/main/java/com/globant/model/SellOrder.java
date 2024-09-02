package com.globant.model;

import java.math.BigDecimal;

public class SellOrder extends Order {
    private int orderId;
    private static int count = 0;

    public SellOrder(User user, CrytoCurrency crytoCurrency,
                     BigDecimal cryptoAmount, BigDecimal maxPrice) {
        super(user, crytoCurrency, cryptoAmount, maxPrice);
        this.orderId = count++;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "SellOrder was completed = " +
                "orderId=" + orderId +
                ", user=" + super.getUser() +
                ", crytoCurrency=" + super.getCrytoCurrency() +
                ", cryptoAmount=" + super.getCryptoAmount() +
                ", price=" + super.getPrice() + " ";
    }
}
