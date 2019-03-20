package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HireService {

    @Autowired
    HireRepository hireRepository;


    public List<Hire> getHiresByBookId(Integer id){
        return hireRepository.findByHiredBook_Id(id);
    }
}
