package com.Andrey.CustomPizza.api.controller;

import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.api.service.OrderResponse;
import com.Andrey.CustomPizza.model.Ingredients.Dough;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.Ingredients.DoughRepository;
import com.Andrey.CustomPizza.service.OrderService;
import com.Andrey.CustomPizza.service.UserService;
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
    private final UserService userService;
    private final DoughRepository doughRepository;

    @Autowired
    public OrderController(OrderResponse orderResponse, OrderService orderService, UserService userService, DoughRepository doughRepository) {
        this.orderResponse = orderResponse;
        this.orderService = orderService;
        this.userService = userService;
        this.doughRepository = doughRepository;
    }

    @GetMapping("/user/current")
    public List<OrderDTO> getAllCurrentOrders(){
        return orderResponse.getOrderResponse(orderService.getAllCurrentOrders());
    }

    @GetMapping("/user/orders")
    public List<OrderDTO> getAllUserOrders(){
        User user = userService.getUserByEmail("tasali7350@aenmail.net");
        return orderResponse.getOrderResponse(orderService.getAllByUser(user));
    }

    @GetMapping("/dough")
    public List<Dough> getDough(){
        return doughRepository.findAll();
    }

}
