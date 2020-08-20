package com.Andrey.CustomPizza.repository.PizzaAndOrderDetails;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Order;
import com.Andrey.CustomPizza.model.UserDetails.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.condition.name <> 'Delivered' order by o.date")
    List<Order> findAllCurrentOrders();

    int countAllByUser(User user);
}
