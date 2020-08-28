package com.Andrey.CustomPizza.controller;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.Ingredients.*;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.ConditionRepository;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.PizzaRepository;
import com.Andrey.CustomPizza.service.OrderService;
import com.Andrey.CustomPizza.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class OrderController {


    private final PizzaRepository pizzaRepository;
    private final SauceRepository sauceRepository;
    private final DoughRepository doughRepository;
    private final CheeseRepository cheeseRepository;
    private final MeetRepository meetRepository;
    private final OtherRepository otherRepository;
    private final SausageRepository sausageRepository;
    private final VegetableRepository vegetableRepository;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    public OrderController(PizzaRepository pizzaRepository, SauceRepository sauceRepository,
                           DoughRepository doughRepository, CheeseRepository cheeseRepository,
                           MeetRepository meetRepository, OtherRepository otherRepository,
                           SausageRepository sausageRepository, VegetableRepository vegetableRepository,
                           UserService userService, OrderService orderService) {
        this.pizzaRepository = pizzaRepository;
        this.sauceRepository = sauceRepository;
        this.doughRepository = doughRepository;
        this.cheeseRepository = cheeseRepository;
        this.meetRepository = meetRepository;
        this.otherRepository = otherRepository;
        this.sausageRepository = sausageRepository;
        this.vegetableRepository = vegetableRepository;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getMainPage(){
        return "index";
    }

    @GetMapping("/orderPizza")
    public String getOrderPizzaPage(Model model){
        model.addAttribute("newPizza", new Pizza());
        model.addAttribute("sauces",sauceRepository.findAll());
        model.addAttribute("doughs",doughRepository.findAll());
        model.addAttribute("cheese",cheeseRepository.findAll());
        model.addAttribute("meet",meetRepository.findAll());
        model.addAttribute("others",otherRepository.findAll());
        model.addAttribute("sausages",sausageRepository.findAll());
        model.addAttribute("vegetables",vegetableRepository.findAll());
        return "orderPizza";
    }

    @PostMapping("/newOrder")
    public String addOrderToDB(Pizza pizza,Principal principal) throws Exception {
        pizzaRepository.save(pizza);

        User user = userService.getUserByEmail(principal.getName());

        orderService.addOrder(user,pizza);

        return "redirect:/";
    }

    @GetMapping("/orders/waiting")
    public String getAllWaitingOrdersPage(Model model){
        model.addAttribute("orders",orderService.getAllCurrentOrders());
        return "orders/waitingOrders";
    }

    @RequestMapping("/delivered/{id}")
    public String updateOrderConditionById(@PathVariable("id")Long orderId){
        orderService.updateOrderConditionFromPreviousToNextById(orderId);
        return "redirect:/orders/waiting";
    }

}
