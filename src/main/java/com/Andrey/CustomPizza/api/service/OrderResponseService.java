package com.Andrey.CustomPizza.api.service;

import com.Andrey.CustomPizza.api.model.OrderResponseApiModel;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderResponseService implements OrderResponse{


    @Override
    public List<OrderResponseApiModel> getOrderResponse(List<Order> orders) {
        List<OrderResponseApiModel> orderResponses = new ArrayList<>();
        for(Order order:orders){
            orderResponses.add(new OrderResponseApiModel(
                    order.getTime(),
                    order.getCondition().getName(),
                    order.getPizza(),
                    order.getUser().getName(),
                    order.getPriceWithDiscount(),
                    order.getDeliveryAddress()
            ));
        }
        return orderResponses;
    }
}
