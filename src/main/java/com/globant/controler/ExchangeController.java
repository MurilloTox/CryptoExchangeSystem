package com.globant.controler;

import com.globant.model.Bitcoin;
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
       BigDecimal userMoney = RootController.getInstance().getCurrentUser().getCurrentMoney();
       BigDecimal amountTried = new BigDecimal("0.0");
       BigDecimal currentPrice = new BigDecimal("0.0");
       try {
           switch (choice) {
               case 1:
                   amount = amount.add(view.getAmountCryptoInput());
                   currentPrice = Bitcoin.getInstance().getCurrentPrice();
                   amountTried = amount.multiply(currentPrice);
                   if (userMoney.compareTo(amountTried) >= 0) {
                       systemService.storageBitcoinManegement(amount);
                       systemService.userWalletManagement(amountTried, Bitcoin.getInstance(), amount);
                       view.showSuccessMessage("Successful purchase.");
                   } else{
                       throw new InsufficientFundsException("Insufficient funds.");
                   }
           }
       } catch (InsufficientFundsException e) {
           System.out.println("Exception: " + e.getMessage());
       }
    }
}
