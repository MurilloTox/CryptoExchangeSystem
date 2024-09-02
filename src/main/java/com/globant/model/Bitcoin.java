package com.globant.model;

import com.globant.model.Interfaces.Fluctuable;

import java.math.BigDecimal;

public class Bitcoin extends CrytoCurrency implements Fluctuable {
    private static Bitcoin instance;

    private Bitcoin() {
        super("Bitcoin", "BTC",
                new BigDecimal("500.00"), new BigDecimal("5000.00"));
    }

    public static Bitcoin getInstance() {
        if (instance == null) {
            instance = new Bitcoin();
        }
        return instance;
    }

}
