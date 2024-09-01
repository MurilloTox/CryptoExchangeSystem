package com.globant.model;

import java.math.BigDecimal;

public class Ethereum extends CrytoCurrency implements Fluctuable{
    private static Ethereum instance;

    private Ethereum() {
        super("Etherium", "ETH",
                new BigDecimal("9000.00"), new BigDecimal("300.00"));
    }

    public static Ethereum getInstance() {
        if (instance == null) {
            instance = new Ethereum();
        }
        return instance;
    }

}
