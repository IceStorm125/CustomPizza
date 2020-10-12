package com.Andrey.CustomPizza.service;

import com.Andrey.CustomPizza.mail.MailSender;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.ConditionRepository;
import com.Andrey.CustomPizza.repository.PizzaAndOrderDetails.OrderRepository;
import org.decimal4j.util.DoubleRounder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderSericeImpl implements OrderService {

    public static final int PRECISION = 2;

    private final OrderRepository orderRepository;
    private final MailSender mailSender;
    private final ConditionRepository conditionRepository;


    @Autowired
    public OrderSericeImpl(OrderRepository orderRepository, MailSender mailSender,
                           ConditionRepository conditionRepository) {
        this.orderRepository = orderRepository;
        this.mailSender = mailSender;
        this.conditionRepository = conditionRepository;
    }


    @Override
    public void addOrder(User user, Pizza pizza, String address) {

        Order order = new Order();

        order.setTime(new Date());
        order.setCondition(conditionRepository.getOne(1L));
        order.setPizza(pizza);
        order.setUser(user);


        double price = order.getPizza().getPrice() * user.getDiscountFactor();
        double roundedPrice = new DoubleRounder(PRECISION).round(price);

        order.setPriceWithDiscount(roundedPrice);
        order.setDeliveryAddress(address);
        orderRepository.save(order);

        updateUserDiscountFactor(user);

        sendOrderDetailsToUserEmail(user, pizza);
    }

    private int getCountOfAllUserOrders(User user) {
        return orderRepository.countAllByUser(user);
    }

    private void updateUserDiscountFactor(User user) {

        int countOfOrders = getCountOfAllUserOrders(user);

        if (countOfOrders > 5) {
            user.setDiscountFactor(0.9);
        }
        if (countOfOrders > 10) {
            user.setDiscountFactor(0.85);
        }
        if (countOfOrders > 20) {
            user.setDiscountFactor(0.80);
        }
    }

    @Override
    public void sendOrderDetailsToUserEmail(User user, Pizza pizza) {

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
                        "Price: %s" + "p",
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
        mailSender.send(user.getEmail(), "Your order", message);
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
    }

    @Override
    public List<Order> getAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }


}
