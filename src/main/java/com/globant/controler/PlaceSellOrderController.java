package com.globant.controler;

import com.globant.model.*;
import com.globant.service.Exception.InsufficientFundsException;
import com.globant.service.OrdersService;
import com.globant.view.ConsoleLoggedView;

import java.math.BigDecimal;

public class PlaceSellOrderController {
    private static PlaceSellOrderController instance;
    private final ConsoleLoggedView view;
    private final OrdersService service;

    private PlaceSellOrderController(ConsoleLoggedView view, OrdersService service) {
        this.view = view;
        this.service = service;
    }

    public static PlaceSellOrderController getInstance(ConsoleLoggedView view, OrdersService service) {
        if (instance == null) {
            instance = new PlaceSellOrderController(view, service);
        }
        return instance;
    }

    protected void execute() {
        int choice = view.getCryptoOption();
        User user = RootLoggedController.getInstance().getCurrentUser();
        switch (choice) {
            case 1:
                Bitcoin btc = Bitcoin.getInstance();
                BigDecimal cryptoAmountB = view.getAmountCryptoInput();
                BigDecimal minMoneyB = view.getSellMoneyInput();
                try {
                    sellController(user, btc, cryptoAmountB, minMoneyB);
                    break;
                } catch (InsufficientFundsException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            case 2:
                Ethereum etm = Ethereum.getInstance();
                BigDecimal cryptoAmountE = view.getAmountCryptoInput();
                BigDecimal maxMoneyE = view.getBuyMoneyInput();
                try {
                    sellController(user, etm, cryptoAmountE, maxMoneyE);
                    break;
                } catch (InsufficientFundsException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            case 3:
                break;
        }

    }
    private void sellController (User user, CrytoCurrency currency, BigDecimal cryptoAmount,
                                 BigDecimal minMoney) throws InsufficientFundsException {
        if (service.ableToSell(minMoney, user, currency)){
            SellOrder order = service.publishSellOrder(user, currency, cryptoAmount, minMoney);
            service.addCryptoDetention(user, minMoney);
            ConsoleLoggedView.getInstance().showSuccessMessage("Your order was placed");
            service.checkOrders(order);
        } else {
            throw new InsufficientFundsException("Insufficient funds to sell.");
        }
    }
}

