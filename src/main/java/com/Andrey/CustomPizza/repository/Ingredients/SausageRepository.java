package com.Andrey.CustomPizza.repository.Ingredients;

import com.Andrey.CustomPizza.model.Ingredients.Sausage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SausageRepository extends JpaRepository<Sausage, Long> {
}
