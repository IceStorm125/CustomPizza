package com.Andrey.CustomPizza.repository.Ingredients;

import com.Andrey.CustomPizza.model.Ingredients.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable,Long> {
}
