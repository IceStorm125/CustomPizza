package com.Andrey.CustomPizza.api.service;

import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderResponseService implements OrderResponse{


    @Override
    public List<OrderDTO> getOrderResponse(List<Order> orders) {
        List<OrderDTO> orderResponses = new ArrayList<>();
        for(Order order:orders){
            orderResponses.add(new OrderDTO(
                    order.getId(),
                    order.getTime(),
                    order.getCondition().getName(),
                    order.getPizza(),
                    order.getUser().getPhoneNumber(),
                    order.getUser().getName(),
                    order.getPriceWithDiscount(),
                    order.getDeliveryAddress()
            ));
        }
        return orderResponses;
    }
}
