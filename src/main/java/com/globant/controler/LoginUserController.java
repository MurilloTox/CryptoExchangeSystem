package com.globant.controler;

import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.service.Exception.UnknownUserException;
import com.globant.view.ConsoleView;

public class LoginUserController {
    private static LoginUserController instance;
    private final ConsoleView view;

    private LoginUserController(ConsoleView view) {
        this.view = view;
    }

    protected static LoginUserController getInstance(ConsoleView view) {
        if (instance == null) {
            instance = new LoginUserController(view);
        }
        return instance;
    }

    protected boolean execute(){
        String[] userInfo=view.getLoginUser();
        boolean notVerified=true;
        try {
            for (User user:SystemService.getInstance().getUsers().values()){
                if (userInfo[0].equals(user.getEmail()) && userInfo[1].equals(user.getPassword())){
                    view.showSuccessMessage("Successfully logged in.");
                    RootLoggedController.getInstance().setCurrentUser(user);
                    notVerified=false;
                    break;
                }
            }
            //Arreglar fallo cuando se hace el try again o directamente quitarlo
            if (notVerified){
                throw new UnknownUserException("This user does not exist");
                /*view.showError("This user doesn´t exist");
                while(true){
                    int choice = view.getNotVerifiedUserChoice();
                    System.out.println(" ");
                    switch(choice){
                        case 1:
                            execute();
                            break;
                        case 2:
                            RootController.getInstance().run();
                            break;
                    }
                }*/
            }
        } catch (UnknownUserException e) {
            System.out.println(e.getMessage());
        }
        return notVerified;
    }

}
