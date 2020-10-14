package com.Andrey.CustomPizza.repository.Users;

import com.Andrey.CustomPizza.model.UserDetails.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<Usr, String> {
}
