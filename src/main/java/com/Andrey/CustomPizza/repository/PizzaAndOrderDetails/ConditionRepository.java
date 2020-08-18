package com.Andrey.CustomPizza.repository.PizzaAndOrderDetails;

import com.Andrey.CustomPizza.model.PizzaAndOrderDetails.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
