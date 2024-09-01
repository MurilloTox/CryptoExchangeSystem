package com.globant.service;

import com.globant.controler.RootController;
import com.globant.model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;


public class SystemService {
    private static SystemService instance;
    private HashMap<String, User> users;

    private SystemService() {
        this.users = new HashMap<>();
    }

    public static SystemService getInstance() {
        if (instance == null) {
            instance = new SystemService();
        }
        return instance;
    }

    public void addUsers(User user) {
        users.put(String.valueOf(user.getId()), user);
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void deposit(User user, BigDecimal amount) {
        for (User u : users.values()) {
            if (u.getId() == user.getId()) {
                u.depositMoney(amount);
            }
        }
    }

    public BigDecimal bigDecimalManagement(BigDecimal amount, BigDecimal total) {
        return total = total.add(amount.setScale(2, RoundingMode.HALF_UP));
    }

    public String currentUserBalance() {
        return RootController.getInstance().getCurrentUser().getWalletBalance();
    }

    public void storageBitcoinManegement(BigDecimal amount) {
        BigDecimal currentBTC= ExchangeStorage.getInstance().getBitcoinAvailable();
        currentBTC = currentBTC.subtract(amount);
        ExchangeStorage.getInstance().setBitcoinAvailable(currentBTC);
    }

    public void userWalletManagement(BigDecimal amount, CrytoCurrency currency, BigDecimal currecyAmount) {
        User user = RootController.getInstance().getCurrentUser();
        BigDecimal userMoney = RootController.getInstance().getCurrentUser().getCurrentMoney();
        user.setCurrentMoney(userMoney.add(amount.setScale(2, RoundingMode.HALF_UP)));
        if (currency.getClass()== Bitcoin.class){
            BigDecimal userCurrency = user.consultCurrentBitcoin();
            user.changeBitcoin(userCurrency.add(currecyAmount.setScale(2, RoundingMode.HALF_UP)));
        } else if (currency.getClass()== Ethereum.class) {
            BigDecimal userCurrency = user.consultCurrentEthereum();
            user.changeEthereum(userCurrency.add(amount.setScale(2, RoundingMode.HALF_UP)));
        }

    }



}
