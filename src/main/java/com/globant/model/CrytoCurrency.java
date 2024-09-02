package com.globant.model;

import java.math.BigDecimal;
import java.util.Objects;

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

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getInitialAmount() {
        return inicialAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrytoCurrency that = (CrytoCurrency) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getIdentifier(), that.getIdentifier());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getIdentifier());
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
