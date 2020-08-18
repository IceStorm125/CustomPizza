package com.Andrey.CustomPizza.repository.Ingredients;

import com.Andrey.CustomPizza.model.Ingredients.Sauce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SauceRepository extends JpaRepository<Sauce,Long> {
}
