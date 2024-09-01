package com.globant.model;

import java.math.BigDecimal;

public class Etherium extends CrytoCurrency implements Fluctuable{
    private static Etherium instance;

    private Etherium() {
        super("Etherium", "ETH",
                new BigDecimal("300.00"), new BigDecimal("9000.00"));
    }

    public static Etherium getInstance() {
        if (instance == null) {
            instance = new Etherium();
        }
        return instance;
    }

}
