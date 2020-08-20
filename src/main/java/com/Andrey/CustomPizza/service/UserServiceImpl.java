package com.Andrey.CustomPizza.service;

import com.Andrey.CustomPizza.mail.MailSender;
import com.Andrey.CustomPizza.model.UserDetails.Role;
import com.Andrey.CustomPizza.model.UserDetails.User;
import com.Andrey.CustomPizza.repository.Users.RoleRepository;
import com.Andrey.CustomPizza.repository.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MailSender mailSender, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user) throws Exception {
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null){
            throw new Exception("This email is already in use");
        }else {
            user.setActivationCode(UUID.randomUUID().toString());

            HashSet<Role> list = new HashSet<>();
            list.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(new HashSet<>(list));

            userRepository.save(user);

            String message = String.format(
                    "Hello, %s \n"+
                            "Click here: http://localhost:8080/user/activate/%s",
                    user.getName(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(),"Activation code", message);
        }
    }

    @Override
    public User getUserByEmail(String email){
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user != null) {
            user.setActivationCode(null);
            userRepository.save(user);
        }
    }
}
