package com.bratkowski.booklibary.repository;

import com.bratkowski.booklibary.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Integer> {

    @Transactional
    Role save (Role role);
}
