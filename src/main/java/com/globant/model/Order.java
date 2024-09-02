package com.globant.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Order {
    private User user;
    private CrytoCurrency crytoCurrency;
    private BigDecimal cryptoAmount;
    private BigDecimal price;
    private boolean active;

    protected Order(User user, CrytoCurrency crytoCurrency,
                    BigDecimal cryptoAmount, BigDecimal price) {
        this.user = user;
        this.crytoCurrency = crytoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.price = price;
        this.active = true;
    }

    public User getUser() {
        return user;
    }

    public CrytoCurrency getCrytoCurrency() {
        return crytoCurrency;
    }

    public BigDecimal getCryptoAmount() {
        return cryptoAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", crytoCurrency=" + crytoCurrency +
                ", cryptoAmount=" + cryptoAmount +
                ", price=" + price +
                ", active=" + active +
                '}';
    }
}
