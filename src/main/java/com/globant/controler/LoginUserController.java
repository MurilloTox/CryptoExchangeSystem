package com.globant.controler;

import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.service.UnknownUserException;
import com.globant.view.ConsoleLoggedView;
import com.globant.view.ConsoleView;

public class LoginUserController {
    private final ConsoleView view;

    public LoginUserController(ConsoleView view) {
        this.view = view;
    }

    public void execute(){
        String[] userInfo=view.getLoginUser();
        boolean notVerified=true;
        try {
            for (User user:SystemService.getInstance().getUsers().values()){
                if (userInfo[0].equals(user.getEmail()) && userInfo[1].equals(user.getPassword())){
                    view.showSuccessMessage("Successfully logged in");
                    RootController.getInstance().setCurrentUser(user);
                    notVerified=false;
                    break;
                }
            }
            if (notVerified){
                throw new UnknownUserException("This user doesn´t exist");
            }
        } catch (UnknownUserException e) {
            System.out.println(e.getMessage());
        }

        if (notVerified){
            while(true){
                int choice = view.getNotVerifiedUserChoice();
                System.out.println(" ");
                switch(choice){
                    case 1:
                        execute();
                        break;
                    case 2:
                        RootController.getInstance(ConsoleView.getInstance(), SystemService.getInstance(), ConsoleLoggedView.getInstance()).run();
                        break;
                }
            }
        }
    }

}
