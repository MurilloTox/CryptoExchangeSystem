package com.globant.controler;

import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

public class RootController {
    private static RootController instance;
    private final ConsoleView view;
    private final RegisterUserController registerUserController;
    private final LoginUserController loginUserController;

    private RootController(ConsoleView view) {
        this.view = view;
        this.registerUserController = RegisterUserController.getInstance(view);
        this.loginUserController = LoginUserController.getInstance(view);
    }

    public static RootController getInstance(ConsoleView view) {
        if (instance == null) {
            instance = new RootController(view);
        }
        return instance;
    }

    public static RootController getInstance(){
        if (instance == null) {
            instance = getInstance(ConsoleView.getInstance());
        }
        return instance;
    }

    public void run(){
        while(true){
            int choice = view.getUserChoice();
            System.out.println(" ");
            switch(choice){
                case 1:
                    registerUserController.execute();
                    break;
                case 2:
                    boolean notlogged = loginUserController.execute();
                    if(notlogged){
                        run();
                    } else {
                        RootLoggedController.getInstance().runLogged();
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    view.showError("Invalid option. Please try again.");
            }
        }
    }
}
