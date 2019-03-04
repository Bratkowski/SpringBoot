package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Role;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void createUser (String username, String password){
        if(username != null && password != null){
            User user = userRepository.getUser(username);

            if(user == null) {
                User newUser = new User(username, password);
                userRepository.addUser(newUser);
            }
        }
    }

    @Transactional
    public void addRoleToUser(String username , String roleName){
        if(username != null && roleName != null) {
            User user = userRepository.getUser(username);
            System.out.println(">>>>>>>>>>>>>>>" + user.getRoles() == null);

            if (user != null) {
                Role role = new Role(roleName);
                userRepository.addRoleToUser(user, role);
            }
        }
    }
}

