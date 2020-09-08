package com.Andrey.CustomPizza.api.service;

import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;

import java.util.List;

public interface OrderResponse {
    List<OrderDTO> getOrderResponse(List<Order> orders);
}
