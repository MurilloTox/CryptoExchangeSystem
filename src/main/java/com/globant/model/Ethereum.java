package com.globant.model;

import com.globant.model.Interfaces.Fluctuable;

import java.math.BigDecimal;

public class Ethereum extends CrytoCurrency implements Fluctuable {
    private static Ethereum instance;

    private Ethereum() {
        super("Etherium", "ETH",
                new BigDecimal("900.00"), new BigDecimal("3500.00"));
    }

    public static Ethereum getInstance() {
        if (instance == null) {
            instance = new Ethereum();
        }
        return instance;
    }

}
