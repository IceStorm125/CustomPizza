package com.Andrey.CustomPizza.api.service;

import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;

import java.util.List;

public interface OrderResponse {
    List<OrderDTO> getOrderResponse(List<Order> orders);
    Pizza parsePizzaFromJSON(String JSONToParse);
}
