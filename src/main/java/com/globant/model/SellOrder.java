package com.globant.model;

import java.math.BigDecimal;

public class SellOrder extends Order {

    public SellOrder(User user, CrytoCurrency crytoCurrency,
                     BigDecimal cryptoAmount, BigDecimal maxPrice) {
        super(user, crytoCurrency, cryptoAmount, maxPrice);
    }

    @Override
    public String toString() {
        return "Sell order: " + super.toString();
    }
}
