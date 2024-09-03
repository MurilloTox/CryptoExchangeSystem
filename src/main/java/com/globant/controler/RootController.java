package com.globant.controler;

import com.globant.service.SystemService;
import com.globant.view.ConsoleView;

public class RootController {
    private static RootController instance;
    private final ConsoleView view;
    private final SystemService systemService;
    private final RegisterUserController registerUserController;
    private final LoginUserController loginUserController;

    private RootController(ConsoleView view, SystemService systemService) {
        this.view = view;
        this.systemService = systemService;
        this.registerUserController = RegisterUserController.getInstance(view, systemService);
        this.loginUserController = LoginUserController.getInstance(view);
    }

    public static RootController getInstance(ConsoleView view, SystemService systemService) {
        if (instance == null) {
            instance = new RootController(view, systemService);
        }
        return instance;
    }

    public static RootController getInstance(){
        if (instance == null) {
            instance = getInstance(ConsoleView.getInstance(), SystemService.getInstance());
        }
        return instance;
    }

    //Hacer que los metodos excute sean protected

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
