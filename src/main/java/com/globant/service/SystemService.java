package com.globant.service;

import com.globant.controler.RootController;
import com.globant.model.User;
import com.globant.view.ConsoleView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

}
