package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.repository.BookRepository;
import com.bratkowski.booklibary.repository.HireRepository;
import com.bratkowski.booklibary.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Value("${library.hire.dailyPenalty}")
    BigDecimal dailyPenalty;

    public List<Hire> getHiresByBookId(Integer id){

        return hireRepository.findByHiredBook_Id(id);
    }

    public Hire hire(Integer bookId, User user){
        boolean isBookAvailable = hireRepository.findByIdAndNotGiveBack(bookId).isEmpty();


        if (isBookAvailable) {
            Book book = bookRepository.getBook(bookId);
            //User user = userService.getLoggedUser();


            if (book != null && user != null){
                Hire hire = new Hire();
                hire.setHiredBook(book);
                hire.setHireUser(user);

                Date hireDate = new Date();
                Date plannedGiveBackDate = DateUtils.addDaysToDate(hireDate,giveBackDays );

                hire.setHireDate(hireDate);
                hire.setPlannedGiveBackDate(plannedGiveBackDate);

                hire.setDailyPenalty(dailyPenalty);

                hireRepository.save(hire);
                return hire;
            }
        }

        return null;

        /*Hire hire = new Hire();
        hire.setHiredBook(bookRepository.getBook(bookId));
        hire.setHireUser(userService.getLoggedUser());
        hire.setHireDate(new Date());


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(hire.getHireDate());
        calendar.add(Calendar.DATE, giveBackDays);

        hire.setPlannedGiveBackDate(calendar.getTime());

        hireRepository.hire(hire);*/

    }

    public List<Hire> getHireListById (Integer id){
        return hireRepository.findByHireUser_Id(id);
    }

    public void setHireAsGiveBack (Long id ) {
        hireRepository.setHireAsGiveBack(id);
    }

    public List<Hire> notGiveBackHireList () {
        return hireRepository.findHiresNotGiveBack();
    }

    public Hire getHireById (Long id){
        return hireRepository.findById(id).get();
    }
}
