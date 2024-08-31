package com.globant.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    public static List<User> listUsers=new ArrayList<User>();
    //Hacer que esta lista sea privada, tal vez con un metodo y reemplazar las referencias que le he hecho
    private int id;
    private DigitalWallet digitalWallet;


    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.id = newID();
        listUsers.add(this);
        DigitalWallet digitalWallet = new DigitalWallet(id);
        this.digitalWallet = digitalWallet;
        System.out.println("User "+ id +" created");

    }

    private int newID() {
        Random rd = new Random();
        int newID=0;
        boolean idNotNew =true;

        while (idNotNew){
            newID = 100000 + rd.nextInt(900000);
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

    public void depositMoney(BigDecimal amount){
        digitalWallet.depositToWallet(amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public DigitalWallet getDigitalWallet() {
        return digitalWallet;
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
}
