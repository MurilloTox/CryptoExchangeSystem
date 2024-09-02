package com.globant.model;

import com.globant.service.SystemService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private final String name;
    private final String lastName;
    private final String email;
    private final String password;
    private final static List<User> listUsers=new ArrayList<>();
    private final int id;
    private final DigitalWallet digitalWallet;


    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.id = newID();
        digitalWallet = new DigitalWallet(id);
        System.out.println("User "+ id +" created");
        listUsers.add(this);
        SystemService.getInstance().addUsers(this);
        //OrdersService.getInstance().addDebt(this, new BigDecimal("0.0"));
    }

    private int newID() {
        Random rd = new Random();
        int newID=0;
        boolean idNotNew =true;

        while (idNotNew){
            newID = 1000 + rd.nextInt(9000);
            idNotNew =false;
            for (User u : listUsers) {
                if (u.getId() == newID) {
                    idNotNew = true;
                    break;
                }
            }
        }
        return newID;
    }

    public void depositMoney(BigDecimal amount){
        digitalWallet.depositToWallet(amount);
    }

    public BigDecimal getCurrentMoney(){
        return digitalWallet.getMoney();
    }

    public void setCurrentMoney(BigDecimal amount){
        digitalWallet.setMoney(amount);
    }

    public BigDecimal consultCurrentBitcoin(){
        return digitalWallet.getBitCoinOwned();
    }

    public void changeBitcoin(BigDecimal amount){
        digitalWallet.setBitCoinOwned(amount);
    }

    public BigDecimal consultCurrentEthereum(){
        return digitalWallet.getEthereumOwned();
    }

    public void changeEthereum(BigDecimal amount){
        digitalWallet.setEthereumOwned(amount);
    }

    public String getWalletBalance(){
        return digitalWallet.toString();
    }

    public String getFullName(){
        return name + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Integer.toString(id).hashCode();
    }


    private class DigitalWallet{
        private final int idCustomer;
        private BigDecimal money;
        private BigDecimal bitCoinOwned;
        private BigDecimal ethereumOwned;

        private DigitalWallet(int idCliente){
            this.idCustomer = idCliente;
            money = new BigDecimal("0.00");
            bitCoinOwned = new BigDecimal("0.00");
            ethereumOwned = new BigDecimal("0.00");
        }

        private void depositToWallet(BigDecimal amount){
            money = money.add(amount.setScale(2, RoundingMode.HALF_UP));
        }

        private BigDecimal getBitCoinOwned() {
            return bitCoinOwned;
        }

        private void setBitCoinOwned(BigDecimal amount) {
            bitCoinOwned=amount;
        }

        public BigDecimal getEthereumOwned() {
            return ethereumOwned;
        }

        public void setEthereumOwned(BigDecimal amount) {
            ethereumOwned=amount;
        }

        private BigDecimal getMoney() {
            return money;
        }

        private void setMoney(BigDecimal amount) {
            money=amount.setScale(2, RoundingMode.HALF_UP);
        }

        @Override
        public String toString() {
            return "The balance of your digitalWallet of " +
                    "customer with id " + idCustomer +
                    " is , fiat money= " + money +
                    "$, bitCoinOwned= " + bitCoinOwned +
                    " BTC, ethereumOwned= " + ethereumOwned + " ETR";
        }
    }

}
