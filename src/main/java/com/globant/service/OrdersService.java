package com.globant.service;

import com.globant.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersService {
    private static OrdersService instance;
    private static List<Order> orders;
    private final  HashMap<User, BigDecimal> debts;
    private final  HashMap<User, BigDecimal> crytoDetention;

    private OrdersService() {
        orders = new ArrayList<Order>();
        debts = new HashMap<>();
        crytoDetention = new HashMap<>();
    }

    public static OrdersService getInstance() {
        if (instance == null) {
            instance = new OrdersService();
        }
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addDebt(User user, BigDecimal amount) {
        if (!debts.containsKey(user)) {
            debts.put(user, amount);
        } else {
            debts.put(user, debts.get(user).add(amount));
        }
    }

    public void addCrytoDetention(User user, BigDecimal amount) {
        if (!crytoDetention.containsKey(user)) {
            crytoDetention.put(user, amount);
        } else {
            crytoDetention.put(user, crytoDetention.get(user).add(amount));
        }
    }

    public void substractDebt(User user, BigDecimal amount) {
        debts.put(user, debts.get(user).subtract(amount));
    }

    public void substractCrytoDetention(User user, BigDecimal amount) {
        crytoDetention.put(user, crytoDetention.get(user).subtract(amount));
    }

    public BuyOrder publishBuyOrder(User buyer, CrytoCurrency currency,
                             BigDecimal cryptoAmount, BigDecimal value) {
        BuyOrder order = new BuyOrder(buyer, currency, cryptoAmount, value);
        addOrder(order);
        return order;
    }

    public SellOrder publishSellOrder(User seller, CrytoCurrency currency,
                                      BigDecimal cryptoAmount, BigDecimal value) {
        SellOrder order = new SellOrder(seller, currency, cryptoAmount, value);
        addOrder(order);
        return order;
    }

    private boolean ableTo(HashMap<User, BigDecimal> map, BigDecimal amount, User user) {
        try {
            BigDecimal possibleDebt = map.get(user).add(amount);
            if (possibleDebt.compareTo(BigDecimal.ZERO) >= 0) {
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            if (user.getCurrentMoney().compareTo(amount) >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean ableToBuy(BigDecimal amount, User user) {
        return ableTo(debts, amount, user);
    }

    public boolean ableToSell(BigDecimal amount, User user) {
        return ableTo(crytoDetention, amount, user);
    }

    public void checkOrders(Order newOrder) {
        if (newOrder instanceof BuyOrder) {
            for (Order order : orders) {
                if (order instanceof SellOrder) {
                    int check = (order.getPrice().compareTo(newOrder.getPrice()));
                    boolean match = (newOrder.getCrytoCurrency().equals(order.getCrytoCurrency()))
                            && (newOrder.getCryptoAmount().compareTo(order.getCryptoAmount()) == 0);
                    if (match && order.isActive() && check <= 0) {
                        SellOrder order1 = (SellOrder) order;
                        BuyOrder order2 = (BuyOrder) newOrder;
                        processOrders(order2, order1);
                        User user = newOrder.getUser();
                        substractDebt(user,newOrder.getPrice());
                        break;
                    }
                }
            }
        } else if (newOrder instanceof SellOrder) {
            for (Order order : orders) {
                if (order instanceof BuyOrder) {
                    int check = (order.getPrice().compareTo(newOrder.getPrice()));
                    boolean match = (newOrder.getCrytoCurrency().equals(order.getCrytoCurrency()))
                            && (newOrder.getCryptoAmount().compareTo(order.getCryptoAmount()) == 0);
                    if (match && order.isActive() && check >= 0) {
                        SellOrder order1 = (SellOrder) newOrder;
                        BuyOrder order2 = (BuyOrder) order;
                        processOrders(order2, order1);
                        User user = newOrder.getUser();
                        substractCrytoDetention(user,newOrder.getPrice());
                        break;
                    }
                }
            }
        }
    }

    public void processOrders(BuyOrder buyOrder, SellOrder sellOrder) {
        User buyer = buyOrder.getUser();
        User seller = sellOrder.getUser();
        BigDecimal money = sellOrder.getPrice();
        BigDecimal cryptoAmount = sellOrder.getCryptoAmount();
        buyer.setCurrentMoney(buyer.getCurrentMoney().subtract(money));
        seller.setCurrentMoney(seller.getCurrentMoney().add(money));
        if (sellOrder.getCrytoCurrency() instanceof Bitcoin){
            buyer.changeBitcoin(buyer.consultCurrentBitcoin().add(cryptoAmount));
            seller.changeBitcoin(seller.consultCurrentBitcoin().subtract(cryptoAmount));
        } else if (sellOrder.getCrytoCurrency() instanceof Ethereum) {
            buyer.changeEthereum(buyer.consultCurrentEthereum().add(cryptoAmount));
            seller.changeEthereum(seller.consultCurrentEthereum().subtract(cryptoAmount));
        }
        buyOrder.setActive(false);
        sellOrder.setActive(false);
    }
}
