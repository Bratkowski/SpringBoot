package com.bratkowski.booklibary.repository;

import com.bratkowski.booklibary.domain.Hire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface HireRepository extends JpaRepository<Hire, Long> {

    @Override
    List<Hire> findAll();

    @Override
    Optional<Hire> findById (Long along);


    List<Hire> findByHiredBook_Id(Integer id);
}
