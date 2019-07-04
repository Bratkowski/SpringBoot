package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Role;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.dto.UserDto;
import com.bratkowski.booklibary.repository.RoleRepository;
import com.bratkowski.booklibary.repository.UserRepository;
import com.bratkowski.booklibary.repository.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

    @Autowired
    RoleRepository roleRepository;

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
            PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();

            Role role = new Role(roleName);
            userRepository.addRoleToUser(username, role);
        }
    }

    public void saveUser (User user){
        PasswordEncoder pe = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(pe.encode(user.getPassword()));
        user.setEnabled(true);
        userRepositoryJpa.save(user);

        Role role = new Role();
        role.setUser(user);
        role.setName("USER");

        roleRepository.save(role);
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

    public UserDto convert (User user) {
        if(user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setFullName(user.getFirstName() + ' ' +  user.getLastName());

        return userDto;
    }

    public List<User> getAll () {
        return userRepositoryJpa.findAll();
    }
}

