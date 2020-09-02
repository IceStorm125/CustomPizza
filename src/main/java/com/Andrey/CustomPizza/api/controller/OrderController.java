package com.Andrey.CustomPizza.api.controller;

import com.Andrey.CustomPizza.api.service.OrderResponse;
import com.Andrey.CustomPizza.api.model.OrderResponseApiModel;
import com.Andrey.CustomPizza.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@Slf4j
public class OrderController {

    private final OrderResponse orderResponse;
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderResponse orderResponse, OrderService orderService) {
        this.orderResponse = orderResponse;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public List<OrderResponseApiModel> test(){
        return orderResponse.getOrderResponse(orderService.getAllCurrentOrders());
    }

}
