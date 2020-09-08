package com.Andrey.CustomPizza.api.controller;

import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.api.model.PizzaDTO;
import com.Andrey.CustomPizza.api.service.OrderResponse;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.Ingredients.*;
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
    private final SauceRepository sauceRepository;
    private final CheeseRepository cheeseRepository;
    private final MeetRepository meetRepository;
    private final OtherRepository otherRepository;
    private final SausageRepository sausageRepository;
    private final VegetableRepository vegetableRepository;

    @Autowired
    public OrderController(OrderResponse orderResponse, OrderService orderService,
                           UserService userService, DoughRepository doughRepository,
                           SauceRepository sauceRepository, CheeseRepository cheeseRepository,
                           MeetRepository meetRepository, OtherRepository otherRepository,
                           SausageRepository sausageRepository, VegetableRepository vegetableRepository) {
        this.orderResponse = orderResponse;
        this.orderService = orderService;
        this.userService = userService;
        this.doughRepository = doughRepository;
        this.sauceRepository = sauceRepository;
        this.cheeseRepository = cheeseRepository;
        this.meetRepository = meetRepository;
        this.otherRepository = otherRepository;
        this.sausageRepository = sausageRepository;
        this.vegetableRepository = vegetableRepository;
    }

    @GetMapping("/current")
    public List<OrderDTO> getAllCurrentOrders(){
        return orderResponse.getOrderResponse(orderService.getAllCurrentOrders());
    }

    @GetMapping("/user/orders")
    public List<OrderDTO> getAllUserOrders(){
        User user = userService.getUserByEmail("tasali7350@aenmail.net");
        return orderResponse.getOrderResponse(orderService.getAllByUser(user));
    }

    @GetMapping("/ingredients")
    public PizzaDTO getIngredients(){
        return new PizzaDTO(
                cheeseRepository.findAll(),
                doughRepository.findAll(),
                meetRepository.findAll(),
                otherRepository.findAll(),
                sauceRepository.findAll(),
                sausageRepository.findAll(),
                vegetableRepository.findAll()
        );
    }

}
