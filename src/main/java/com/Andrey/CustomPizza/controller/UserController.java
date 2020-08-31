package com.Andrey.CustomPizza.controller;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.PizzaRepository;
import com.Andrey.CustomPizza.service.OrderService;
import com.Andrey.CustomPizza.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final PizzaRepository pizzaRepository;
    private final OrderService orderService;

    @Autowired
    public UserController(UserService userService, PizzaRepository pizzaRepository, OrderService orderService) {
        this.userService = userService;
        this.pizzaRepository = pizzaRepository;
        this.orderService = orderService;
    }

    @GetMapping("/registration")
    public String saveUserPage(Model model){
        model.addAttribute("newUser",new User());
        return "/logAndReg/registration";
    }

    @PostMapping("/registration")
    public String saveUser(@Valid @ModelAttribute("newUser") User user, BindingResult result) throws Exception {
        if (result.hasErrors()){
            return "/logAndReg/registration";
        }
        userService.save(user);

        log.info("New user: {}",user);

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


        return "orders/usersOrders";
    }

    @RequestMapping("/repeat-order/{id}")
    public String repeatOrder(@PathVariable("id")Long pizzaId, Principal principal) throws Exception {

        User user = userService.getUserByEmail(principal.getName());
        Pizza pizza = pizzaRepository.getOne(pizzaId);

        orderService.addOrder(user,pizza);

        return "redirect:/";
    }

}
