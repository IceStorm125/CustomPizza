package com.Andrey.CustomPizza.repository.Ingredients;

import com.Andrey.CustomPizza.model.Ingredients.Dough;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoughRepository extends JpaRepository<Dough, Long> {
}
