package com.globant.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Order {
    private final User user;
    private final CrytoCurrency crytoCurrency;
    private final BigDecimal cryptoAmount;
    private BigDecimal price;
    private boolean active;
    private final int orderId;
    private static int count = 0;

    protected Order(User user, CrytoCurrency crytoCurrency,
                    BigDecimal cryptoAmount, BigDecimal price) {
        this.user = user;
        this.crytoCurrency = crytoCurrency;
        this.cryptoAmount = cryptoAmount;
        this.price = price;
        this.active = true;
        this.orderId = count++;
    }

    public int getOrderId() {
        return orderId;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "order id= " + orderId +
                ", user= " + user.getFullName() +
                ", cryptoCurrency= " + crytoCurrency.getName() +
                ", cryptoAmount= " + cryptoAmount +
                ", price= " + price +
                ", active= " + active;
    }
}
