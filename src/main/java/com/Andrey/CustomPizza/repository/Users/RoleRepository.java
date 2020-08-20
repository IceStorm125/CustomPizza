package com.Andrey.CustomPizza.repository.Users;

import com.Andrey.CustomPizza.model.UserDetails.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);

}
