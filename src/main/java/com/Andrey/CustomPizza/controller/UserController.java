package com.Andrey.CustomPizza.controller;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UsersAndWorkers.User;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.ConditionRepository;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.PizzaRepository;
import com.Andrey.CustomPizza.service.OrderService;
import com.Andrey.CustomPizza.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PizzaRepository pizzaRepository;
    private final ConditionRepository conditionRepository;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,
                          PizzaRepository pizzaRepository, ConditionRepository conditionRepository, OrderService orderService) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.pizzaRepository = pizzaRepository;
        this.conditionRepository = conditionRepository;
        this.orderService = orderService;
    }

    @GetMapping("/registration")
    public String createUserPage(Model model){
        model.addAttribute("newUser",new User());
        return "/logAndReg/registration";
    }

    @PostMapping("/registration")
    public String saveUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, SessionStatus status) throws Exception {
        if (result.hasErrors()){
            return "/logAndReg/registration";
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.saveNewUser(user);

        log.info("New user: {}",user);

        status.setComplete();

        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model){

        model.addAttribute("user",new User());

        return "/logAndReg/login";
    }

    @GetMapping("activate/{code}")
    public String activate(@PathVariable("code") String code){
        userService.activateUser(code);
        return "/logAndReg/login";
    }

    @GetMapping("/orders")
    public String getUsersOrdersPage(Model model, Principal principal){

        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("orders",user.getOrders());

        System.out.println(orderService.countAllUserOrders(user));

        return "orders/usersOrders";
    }

    @RequestMapping("/repeat-order/{id}")
    public String repeatOrder(@PathVariable("id")Long pizzaId, Principal principal){

        User user = userService.getUserByEmail(principal.getName());
        Pizza pizza = pizzaRepository.getOne(pizzaId);

        orderService.addOrder(user,pizza);

        return "redirect:/";
    }

}
