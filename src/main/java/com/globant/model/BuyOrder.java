package com.globant.model;

import java.math.BigDecimal;

public class BuyOrder extends Order {

    public BuyOrder(User user, CrytoCurrency crytoCurrency,
                     BigDecimal cryptoAmount, BigDecimal maxPrice) {
        super(user, crytoCurrency, cryptoAmount, maxPrice);
    }

    @Override
    public String toString() {
        return "Buy order = " + super.toString();
    }
}
