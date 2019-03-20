package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Role;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void createUser (String username, String password){
        if(username != null && password != null){
            User user = userRepository.getUser(username);

                PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                User newUser = new User(username, pe.encode(password));
                userRepository.addUser(newUser);

        }
    }

    @Transactional
    public void addRoleToUser(String username , String roleName){
        if(username != null && roleName != null) {
            Role role = new Role(roleName);
            userRepository.addRoleToUser(username, role);
        }
    }

    public User getUser(String username){
        return userRepository.getUser(username);
    }

    public  User getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null){
            String username = auth.getName();
            return getUser(username);
        } else
            return null;
    }
}

