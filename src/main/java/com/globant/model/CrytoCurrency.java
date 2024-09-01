package com.globant.model;

import java.math.BigDecimal;

public abstract class CrytoCurrency {
    protected String name;
    protected String identifier;
    protected BigDecimal inicialAmount;
    protected BigDecimal currentPrice;

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

    protected BigDecimal getInicialAmount() {
        return inicialAmount;
    }

    protected void setAmount(BigDecimal amount) {
        this.inicialAmount = amount;
    }

    protected BigDecimal getCurrentPrice() {
        return currentPrice;
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
