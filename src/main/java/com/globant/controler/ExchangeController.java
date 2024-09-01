package com.globant.controler;

import com.globant.model.Bitcoin;
import com.globant.model.Ethereum;
import com.globant.model.User;
import com.globant.service.InsufficientFundsException;
import com.globant.service.SystemService;
import com.globant.view.ConsoleLoggedView;

import java.math.BigDecimal;

public class ExchangeController {
    private final ConsoleLoggedView view;
    private final SystemService systemService;
    private static ExchangeController instance;

    private ExchangeController(ConsoleLoggedView view, SystemService systemService) {
        this.view = view;
        this.systemService = systemService;
    }

    public static ExchangeController getInstance(ConsoleLoggedView view, SystemService systemService) {
        if (instance == null) {
            instance = new ExchangeController(view, systemService);
        }
        return instance;
    }

    public void execute() {
       int choice = view.getCryptoOption();
       System.out.println(" ");
       BigDecimal amount = new BigDecimal("0.0");
       User user = RootController.getInstance().getCurrentUser();
       BigDecimal userMoney = user.getCurrentMoney();
       BigDecimal amountTried = new BigDecimal("0.0");
       BigDecimal currentPrice = new BigDecimal("0.0");
       try {
           switch (choice) {
               //Agregar un controlador para cuando el amountTried es menor a $0.01
               // Tambien la opcion de go back si alcanzo
               case 1:
                   amount = amount.add(view.getAmountCryptoInput());
                   currentPrice = Bitcoin.getInstance().getCurrentPrice();
                   amountTried = amount.multiply(currentPrice);
                   System.out.println("Amount tried: " + amountTried);
                   if (userMoney.compareTo(amountTried) >= 0) {
                       systemService.userMoneySubstract(user, amountTried);
                       systemService.storageBitcoinManegement(amount);
                       systemService.userCryptoManagement(user, Bitcoin.getInstance(), amount);
                       view.showSuccessMessage("Successful purchase.");
                   } else{
                       throw new InsufficientFundsException("Insufficient funds.");
                   }
                   System.out.println(user.getWalletBalance());
                   break;

               case 2:
                   amount = amount.add(view.getAmountCryptoInput());
                   currentPrice = Ethereum.getInstance().getCurrentPrice();
                   amountTried = amount.multiply(currentPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
                   System.out.println("Amount tried: " + amountTried);
                   if (userMoney.compareTo(amountTried) >= 0) {
                       systemService.userMoneySubstract(user, amountTried);
                       systemService.storageEthereumManegement(amount);
                       systemService.userCryptoManagement(user, Ethereum.getInstance(), amount);
                       view.showSuccessMessage("Successful purchase.");
                   } else{
                       throw new InsufficientFundsException("Insufficient funds.");
                   }
                   System.out.println(user.getWalletBalance());
                   break;

               case 3:
                   break;
           }
       } catch (InsufficientFundsException e) {
           System.out.println(e.getMessage());
       }
    }
}
