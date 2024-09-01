package com.globant.model;

import java.math.BigDecimal;

public class Bitcoin extends CrytoCurrency implements Fluctuable {
    private static Bitcoin instance;

    private Bitcoin() {
        super("Bitcoin", "BTC",
                new BigDecimal("5000.00"), new BigDecimal("500.00"));
    }

    public static Bitcoin getInstance() {
        if (instance == null) {
            instance = new Bitcoin();
        }
        return instance;
    }

}
