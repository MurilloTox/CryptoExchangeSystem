package com.globant.service;

import com.globant.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrdersService {
    private static OrdersService instance;
    private static List<Order> orders;

    private OrdersService() {
        orders = new ArrayList<Order>();
    }

    public static OrdersService getInstance() {
        if (instance == null) {
            instance = new OrdersService();
        }
        return instance;
    }

    public void addOrder(BuyOrder order) {
        orders.add(order);
    }

    public void publishOrder(User buyer, CrytoCurrency currency,
                             BigDecimal cryptoAmount, BigDecimal value) {
        BuyOrder order = new BuyOrder(buyer, currency, cryptoAmount, value);
        addOrder(order);
    }

    public void checkOrders(Order newOrder) {
        for (Order order : orders) {
            if (order instanceof BuyOrder) {
                if (order.equals(newOrder) && order.isActive()) {

                }
            } else if (order instanceof SellOrder) {

            }
        }
    }

}
