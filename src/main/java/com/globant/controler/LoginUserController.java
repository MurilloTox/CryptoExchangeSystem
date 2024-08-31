package com.globant.controler;

import com.globant.model.User;
import com.globant.service.SystemService;
import com.globant.service.UnknownUserException;
import com.globant.view.ConsoleLoggedView;
import com.globant.view.ConsoleView;

public class LoginUserController {
    private ConsoleView view;

    public LoginUserController(ConsoleView view) {
        this.view = view;
    }

    public void execute(){
        String[] userInfo=view.getLoginUser();
        boolean notVerified=true;
        try {
            for (User user:User.listUsers){
                if (userInfo[0].equals(user.getEmail()) && userInfo[0].equals(user.getPassword())){
                    view.showSuccessMessage("Successfully logged in");
                    notVerified=false;
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
