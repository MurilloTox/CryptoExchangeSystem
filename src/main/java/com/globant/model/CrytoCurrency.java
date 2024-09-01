package com.globant.model;

import java.math.BigDecimal;

public abstract class CrytoCurrency {
    private final String name;
    private final String identifier;
    private BigDecimal inicialAmount;
    private BigDecimal currentPrice;

    protected CrytoCurrency(String name, String identifier, BigDecimal inicialAmount, BigDecimal currentPrice) {
        this.name = name;
        this.identifier = identifier;
        this.inicialAmount = inicialAmount;
        this.currentPrice = currentPrice;
    }

    protected String getName() {
        return name;
    }

    protected String getIdentifier() {
        return identifier;
    }

    protected void setAmount(BigDecimal amount) {
        this.inicialAmount = amount;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public BigDecimal getInitialAmount() {
        return inicialAmount;
    }

    @Override
    public String toString() {
        return "CrytoCurrency{" +
                "name='" + name + '\'' +
                ", identifier='" + identifier + '\'' +
                ", inicialAmount=" + inicialAmount +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
