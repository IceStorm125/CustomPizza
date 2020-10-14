package com.Andrey.CustomPizza.api.controller;

import com.Andrey.CustomPizza.api.model.OrderDTO;
import com.Andrey.CustomPizza.api.model.PizzaDTO;
import com.Andrey.CustomPizza.api.service.OrderResponse;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.Ingredients.*;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.PizzaRepository;
import com.Andrey.CustomPizza.service.OrderService;
import com.Andrey.CustomPizza.service.UserService;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin
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
    private final PizzaRepository pizzaRepository;

    @Autowired
    public OrderController(OrderResponse orderResponse, OrderService orderService,
                           UserService userService, DoughRepository doughRepository,
                           SauceRepository sauceRepository, CheeseRepository cheeseRepository,
                           MeetRepository meetRepository, OtherRepository otherRepository,
                           SausageRepository sausageRepository, VegetableRepository vegetableRepository, PizzaRepository pizzaRepository) {
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
        this.pizzaRepository = pizzaRepository;
    }
    @GetMapping("/currentOrders")
    public ResponseEntity<?> getCurrentOrders() {
        List<OrderDTO> orderDTOS = orderResponse.getOrderResponse(orderService.getAllCurrentOrders());
        return new ResponseEntity<>(orderDTOS,HttpStatus.OK);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<?> getIngredients(Authentication authentication) {
        if (authentication==null || !authentication.isAuthenticated()){
            return new ResponseEntity<>("Not authenticated",HttpStatus.UNAUTHORIZED);
        }
        PizzaDTO pizzaDTO = new PizzaDTO(
                cheeseRepository.findAll(), doughRepository.findAll(),
                meetRepository.findAll(), otherRepository.findAll(),
                sauceRepository.findAll(), sausageRepository.findAll(),
                vegetableRepository.findAll());
        return new ResponseEntity<>(pizzaDTO, HttpStatus.OK);
    }

    @PostMapping("/order")
    public void postPizza(@RequestBody String Json) throws Exception {
        Pizza pizza = orderResponse.parsePizzaFromJSON(Json);
        User user = userService.getUserByEmail("tasali7350@aenmail.net");
        String address = new JsonParser().parse(Json).getAsJsonObject().get("deliveryAddress").getAsString();
        orderService.addOrder(user,pizza,address);
    }

}
