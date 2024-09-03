package com.globant.controler;

import com.globant.model.*;
import com.globant.service.Exception.InsufficientFundsException;
import com.globant.service.OrdersService;
import com.globant.view.ConsoleLoggedView;

import java.math.BigDecimal;

public class PlaceBuyOrderController {
    private static PlaceBuyOrderController instance;
    private final ConsoleLoggedView view;
    private final OrdersService service;

    private PlaceBuyOrderController(ConsoleLoggedView view, OrdersService ordersService) {
        this.view = view;
        this.service = ordersService;
    }

    protected static PlaceBuyOrderController getInstance(ConsoleLoggedView view, OrdersService ordersService) {
        if (instance == null) {
            instance = new PlaceBuyOrderController(view, ordersService);
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
                BigDecimal maxMoneyB = view.getBuyMoneyInput();
                try {
                    buyController(user, btc, cryptoAmountB, maxMoneyB);
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
                    buyController(user, etm, cryptoAmountE, maxMoneyE);
                    break;
                } catch (InsufficientFundsException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            case 3:
                break;

            default:
                view.showError("That option doesn't exist, please try again.");
        }

    }

    private void buyController(User user, CrytoCurrency currency, BigDecimal cryptoAmount,
                                BigDecimal maxMoney) throws InsufficientFundsException {
        if (service.ableToBuy(maxMoney, user)){
            BuyOrder order = service.publishBuyOrder(user, currency, cryptoAmount, maxMoney);
            service.addDebt(user, maxMoney);
            ConsoleLoggedView.getInstance().showSuccessMessage("Your order was placed");
            service.checkOrders(order);
        } else {
            throw new InsufficientFundsException("Insufficient funds to buy.");
        }
    }
}
