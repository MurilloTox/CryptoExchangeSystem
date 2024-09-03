package com.globant.service;

import com.globant.controler.RootLoggedController;
import com.globant.model.*;

import java.math.BigDecimal;
import java.util.HashMap;


public class SystemService {
    private static SystemService instance;
    private final HashMap<String, User> users;

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

    public String currentUserBalance() {
        return RootLoggedController.getInstance().getCurrentUser().getWalletBalance();
    }

    public void storageBitcoinManegement(BigDecimal amount) {
        BigDecimal currentBTC= ExchangeStorage.getInstance().getBitcoinAvailable();
        currentBTC = currentBTC.subtract(amount);
        ExchangeStorage.getInstance().setBitcoinAvailable(currentBTC);
    }

    public void storageEthereumManegement(BigDecimal amount) {
        BigDecimal currentETH = ExchangeStorage.getInstance().getEthereumAvailable();
        currentETH = currentETH.subtract(amount);
        ExchangeStorage.getInstance().setEthereumAvailable(currentETH);
    }

    public void userMoneySubstract(User user, BigDecimal amount) {
        BigDecimal userCurrentMoney = user.getCurrentMoney();
        userCurrentMoney = userCurrentMoney.subtract(amount);
        user.setCurrentMoney(userCurrentMoney);
    }

    public void userCryptoManagement(User user, CrytoCurrency currency, BigDecimal currecyAmount) {
        if (currency.getClass()== Bitcoin.class){
            BigDecimal userCurrency = user.consultCurrentBitcoin();
            user.changeBitcoin(userCurrency.add(currecyAmount));
        } else if (currency.getClass()== Ethereum.class) {
            BigDecimal userCurrency = user.consultCurrentEthereum();
            user.changeEthereum(userCurrency.add(currecyAmount));
        }
    }



}
