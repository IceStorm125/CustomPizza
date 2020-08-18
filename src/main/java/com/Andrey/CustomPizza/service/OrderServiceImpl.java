package com.Andrey.CustomPizza.service;

import com.Andrey.CustomPizza.mail.MailSender;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UsersAndWorkers.User;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.ConditionRepository;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MailSender mailSender;
    private final ConditionRepository conditionRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MailSender mailSender, ConditionRepository conditionRepository) {
        this.orderRepository = orderRepository;
        this.mailSender = mailSender;
        this.conditionRepository = conditionRepository;
    }


    @Override
    public void addOrder(User user, Pizza pizza) {

        Order order = new Order();

        order.setDate(new Date());
        order.setCondition(conditionRepository.getOne(1L));
        order.setPizza(pizza);
        order.setUser(user);

        orderRepository.save(order);

        sendOrderDetailsToUserEmail(user,pizza);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public void sendOrderDetailsToUserEmail(User user, Pizza pizza){

        String message = String.format(
                "Hello, %s\n\n" +
                        "Your order:\n" +
                        "Pizza %s with ingredients:\n" +
                        "Dough: %s\n" +
                        "Cheese: %s\n" +
                        "Meet: %s\n" +
                        "Sauces: %s\n" +
                        "Sausages: %s\n" +
                        "Vegetables: %s\n" +
                        "Others: %s\n\n" +
                        "Price: %s"+"p" ,
                user.getName(),
                pizza.getName().toUpperCase(),
                pizza.getDough() == null ? "" : pizza.getDough(),
                pizza.getCheese() == null ? "" : pizza.getCheese(),
                pizza.getMeet() == null ? "" : pizza.getMeet(),
                pizza.getSauce() == null ? "" : pizza.getSauce(),
                pizza.getSausage() == null ? "" : pizza.getSausage(),
                pizza.getVegetables() == null ? "" : pizza.getVegetables(),
                pizza.getOthers() == null ? "" : pizza.getOthers(),
                pizza.getPrice()
        );
        mailSender.send(user.getEmail(),"Your order",message);
    }

    @Override
    public List<Order> getAllCurrentOrders() {
        return orderRepository.findAllCurrentOrders();
    }

    @Override
    public void updateOrderConditionFromPreviousToNextById(Long id) {
        Order order = orderRepository.getOne(id);
        Long currentConditionID = order.getCondition().getId();
        order.setCondition(conditionRepository.getOne(++currentConditionID));
        orderRepository.save(order);
    }

    @Override
    public int countAllUserOrders(User user) {
        return orderRepository.countAllByUser(user);
    }

}
