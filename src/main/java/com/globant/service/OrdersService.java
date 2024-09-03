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
    private final  HashMap<User, BigDecimal> cryptoDetention;

    private OrdersService() {
        orders = new ArrayList<>();
        debts = new HashMap<>();
        cryptoDetention = new HashMap<>();
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

    public void addCryptoDetention(User user, BigDecimal amount) {
        if (!cryptoDetention.containsKey(user)) {
            cryptoDetention.put(user, amount);
        } else {
            cryptoDetention.put(user, cryptoDetention.get(user).add(amount));
        }
    }

    public void subtractDebt(User user, BigDecimal amount) {
        debts.put(user, debts.get(user).subtract(amount));
    }

    public void subtractCryptoDetention(User user, BigDecimal amount) {
        cryptoDetention.put(user, cryptoDetention.get(user).subtract(amount));
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
            return possibleDebt.compareTo(BigDecimal.ZERO) >= 0;
        } catch (NullPointerException e) {
            return user.getCurrentMoney().compareTo(amount) >= 0;
        }
    }

    private boolean ableTo(HashMap<User, BigDecimal> map, BigDecimal amount, User user, CrytoCurrency currency) {
        try {
            if (currency instanceof Bitcoin) {
                BigDecimal possibleDebt = map.get(user).add(amount);
                return possibleDebt.compareTo(BigDecimal.ZERO) >= 0;
            } else {
                return user.consultCurrentEthereum().compareTo(amount) >= 0;
            }

        } catch (NullPointerException e) {
            if (currency instanceof Bitcoin) {
                return user.consultCurrentBitcoin().compareTo(amount) >= 0;
            } else {
                return user.consultCurrentEthereum().compareTo(amount) >= 0;
            }
        }
    }

    public boolean ableToBuy(BigDecimal amount, User user) {
        return ableTo(debts, amount, user);
    }

    public boolean ableToSell(BigDecimal amount, User user, CrytoCurrency currency) {
        return ableTo(cryptoDetention, amount, user, currency);
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
                        subtractDebt(user,newOrder.getPrice());
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
                        subtractCryptoDetention(user,newOrder.getPrice());
                        break;
                    }
                }
            }
        }
    }

    public void processOrders(BuyOrder buyOrder, SellOrder sellOrder) {
        User buyer = buyOrder.getUser();
        User seller = sellOrder.getUser();
        buyOrder.setPrice(sellOrder.getPrice());
        BigDecimal money = sellOrder.getPrice();
        BigDecimal cryptoAmount = sellOrder.getCryptoAmount();
        buyer.setCurrentMoney(buyer.getCurrentMoney().subtract(money));
        seller.setCurrentMoney(seller.getCurrentMoney().add(money));
        if (sellOrder.getCrytoCurrency() instanceof Bitcoin){
            buyer.changeBitcoin(buyer.consultCurrentBitcoin().add(cryptoAmount));
            seller.changeBitcoin(seller.consultCurrentBitcoin().subtract(cryptoAmount));
            BigDecimal currentPrice = Bitcoin.getInstance().getCurrentPrice();
            BigDecimal newPrice = Bitcoin.getInstance().generateNewValue(currentPrice);
            Bitcoin.getInstance().setCurrentPrice(newPrice);
        } else if (sellOrder.getCrytoCurrency() instanceof Ethereum) {
            buyer.changeEthereum(buyer.consultCurrentEthereum().add(cryptoAmount));
            seller.changeEthereum(seller.consultCurrentEthereum().subtract(cryptoAmount));
            BigDecimal currentPrice = Ethereum.getInstance().getCurrentPrice();
            BigDecimal newPrice = Ethereum.getInstance().generateNewValue(currentPrice);
            Ethereum.getInstance().setCurrentPrice(newPrice);
        }
        buyOrder.setActive(false);
        sellOrder.setActive(false);
    }

    public List<Order> obtainUserHistory(User user){
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().equals(user)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}
