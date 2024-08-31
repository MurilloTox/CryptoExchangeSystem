package com.globant.model;

import com.globant.service.SystemService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private static List<User> listUsers=new ArrayList<>();
    private int id;
    private DigitalWallet digitalWallet;


    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.id = newID();
        DigitalWallet digitalWallet = new DigitalWallet(id);
        this.digitalWallet = digitalWallet;
        System.out.println("User "+ id +" created");
        listUsers.add(this);
        SystemService.getInstance().addUsers(this);
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

    public String getName() {
        return name;
    }


    public String getLastName() {
        return lastName;
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
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }


    private class DigitalWallet{
        private int idCustomer;
        private BigDecimal money;
        private BigDecimal BitCoin;
        private BigDecimal Etherium;

        private DigitalWallet(int idCliente){
            this.idCustomer = idCliente;
            money = new BigDecimal("0.00");
            BitCoin = new BigDecimal("0.00");
            Etherium = new BigDecimal("0.00");
        }

        private void depositToWallet(BigDecimal amount){
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                money = money.add(amount);
            } else {
                throw new IllegalArgumentException("El monto a depositar no es válido.");
            }
        }

        @Override
        public String toString() {
            return "The balance of your digitalWallet of " +
                    "customer with id " + idCustomer +
                    " is , money=" + money +
                    " $, BitCoin=" + BitCoin +
                    " BTC, Etherium=" + Etherium + " ETR";
        }
    }

}
