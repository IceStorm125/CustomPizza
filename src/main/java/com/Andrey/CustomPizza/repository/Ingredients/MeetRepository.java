package com.Andrey.CustomPizza.repository.Ingredients;

import com.Andrey.CustomPizza.model.Ingredients.Meet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetRepository extends JpaRepository<Meet,Long> {
}
