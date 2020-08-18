package com.Andrey.CustomPizza.repository.PizzaAndOrderDetails;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
