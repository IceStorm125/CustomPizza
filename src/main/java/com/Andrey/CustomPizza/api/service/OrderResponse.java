package com.Andrey.CustomPizza.api.service;

import com.Andrey.CustomPizza.api.model.OrderResponseApiModel;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;

import java.util.List;

public interface OrderResponse {
    List<OrderResponseApiModel> getOrderResponse(List<Order> orders);
}
