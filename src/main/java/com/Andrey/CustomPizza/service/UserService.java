package com.Andrey.CustomPizza.service;


import com.Andrey.CustomPizza.model.UserDetails.User;

import java.util.List;

public interface UserService {

    User getUserByEmail(String email);
    void save(User user) throws Exception;
    void activateUser(String code);
    List<User> findAll();
}
