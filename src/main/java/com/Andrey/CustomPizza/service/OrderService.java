package com.Andrey.CustomPizza.service;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UsersAndWorkers.User;

import java.util.List;

public interface OrderService {

    void addOrder(User user,Pizza pizza);
    Order getOrderById(Long id);
    void sendOrderDetailsToUserEmail(User user, Pizza pizza);
    List<Order> getAllCurrentOrders();
    void updateOrderConditionFromPreviousToNextById(Long id);
    int countAllUserOrders(User user);
}
