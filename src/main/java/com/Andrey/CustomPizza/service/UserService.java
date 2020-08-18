package com.Andrey.CustomPizza.service;


import com.Andrey.CustomPizza.model.UsersAndWorkers.User;

public interface UserService {

    User getUserByEmail(String email);
    void saveNewUser(User user) throws Exception;
    void activateUser(String code);
}
