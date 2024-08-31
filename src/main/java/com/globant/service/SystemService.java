package com.globant.service;

import com.globant.model.User;
import com.globant.view.ConsoleView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SystemService {
    private static SystemService instance;

    private SystemService() {
    }

    public static SystemService getInstance() {
        if (instance == null) {
            instance = new SystemService();
        }
        return instance;
    }

    public void deposit(){

    }

}
