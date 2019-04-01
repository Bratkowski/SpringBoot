package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.repository.BookRepository;
import com.bratkowski.booklibary.repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@PropertySource("classpath:custom.properties")
public class HireService {

    @Autowired
    HireRepository hireRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserService userService;

    @Value("${library.hire.giveBackDays}")
    Integer giveBackDays;

    public List<Hire> getHiresByBookId(Integer id){

        return hireRepository.findByHiredBook_Id(id);
    }

    public void save(Integer bookId){
        Hire hire = new Hire();
        hire.setHiredBook(bookRepository.getBook(bookId));
        hire.setHireUser(userService.getLoggedUser());
        hire.setHireDate(new Date());


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hire.getHireDate());
        calendar.add(Calendar.DATE, giveBackDays);

        hire.setPlannedGiveBackDate(calendar.getTime());

        hireRepository.save(hire);

    }
}
