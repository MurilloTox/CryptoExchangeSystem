package com.globant.model;

import java.math.BigDecimal;

public class ExchangeStorage {
    private final Bitcoin bitcoin;
    private final Ethereum ethereum;
    private static ExchangeStorage instance;

    private ExchangeStorage(){
        bitcoin = Bitcoin.getInstance();
        ethereum = Ethereum.getInstance();
    }

    public static ExchangeStorage getInstance(){
        if(instance == null){
            instance = new ExchangeStorage();
        }
        return instance;
    }

    public BigDecimal getBitcoinAvailable(){
        return bitcoin.getInitialAmount();
    }

    public BigDecimal getEthereumAvailable(){
        return ethereum.getInitialAmount();
    }

    public void setBitcoinAvailable(BigDecimal bitcoinAvailable){
        bitcoin.setAmount(bitcoinAvailable);
    }


    public void setEthereumAvailable(BigDecimal ethereumAvailable){
        ethereum.setAmount(ethereumAvailable);
    }

    public String currentPrices(){
        return "Bitcoin: " + bitcoin.getCurrentPrice().toString()+" - Ethereum: "
                + ethereum.getCurrentPrice().toString();
    }

    @Override
    public String toString() {
        return "ExchangeStorage{" +
                "bitcoin=" + getBitcoinAvailable() +
                ", ethereum=" + getEthereumAvailable() +
                '}';
    }
}
