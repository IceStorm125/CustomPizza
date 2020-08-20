package com.Andrey.CustomPizza.repository.Users;

import com.Andrey.CustomPizza.model.UserDetails.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByActivationCode(String code);
}
