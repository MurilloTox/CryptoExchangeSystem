package com.globant.controler;

import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

public class RegisterUserController {
    private static RegisterUserController instance;
    private final ConsoleView view;
    public RegisterUserController(ConsoleView view, SystemService systemService) {
        this.view = view;
    }

    protected static RegisterUserController getInstance(ConsoleView view, SystemService systemService) {
        if (instance == null) {
            instance = new RegisterUserController(view, systemService);
        }
        return instance;
    }

    protected void execute(){
        String[] userInfo=view.getRegisterUser();
        String name=userInfo[0];
        String lastname=userInfo[1];
        String email=userInfo[2];
        String password=userInfo[3];
        User user=new User(name,lastname,email,password);
    }
}
