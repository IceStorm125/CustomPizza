package com.Andrey.CustomPizza.service;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UserDetails.User;

import java.util.List;

public interface OrderService {

    void addOrder(User user,Pizza pizza, String address) throws Exception;
    void sendOrderDetailsToUserEmail(User user, Pizza pizza);
    List<Order> getAllCurrentOrders();
    void updateOrderConditionFromPreviousToNextById(Long id);
    List<Order> getAllByUser(User user);
}
