package com.Andrey.CustomPizza.service;

import com.Andrey.CustomPizza.model.UserDetails.Role;
import com.Andrey.CustomPizza.model.UserDetails.User;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserAuthService implements UserDetailsService {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserAuthService(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userServiceImpl.getUserByEmail(email);

        if (user.getActivationCode()!=null){
            throw new Exception("Please, activate your account!");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getUsersGrantedAuthorities(user)
        );
    }

    private List<GrantedAuthority> getUsersGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

}
