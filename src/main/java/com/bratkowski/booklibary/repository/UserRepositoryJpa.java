package com.bratkowski.booklibary.repository;

import com.bratkowski.booklibary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User,Integer> {

    List<User> findAll();

    @Transactional
    User save(User user);
}

