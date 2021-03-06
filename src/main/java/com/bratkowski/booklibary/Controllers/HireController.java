package com.bratkowski.booklibary.Controllers;


import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.domain.Payment;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.dto.BookDto;
import com.bratkowski.booklibary.dto.UserDto;
import com.bratkowski.booklibary.services.BookService;
import com.bratkowski.booklibary.services.HireService;
import com.bratkowski.booklibary.services.PaymentService;
import com.bratkowski.booklibary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class HireController {

    @Autowired
    BookService bookService;

    @Autowired
    HireService hireService;

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;


    @RequestMapping (value = "/books/hires/{id}", method = RequestMethod.GET)
    public String getHires(Model model, @PathVariable("id") Integer id) {

        Book book = bookService.getBook(id);
        List<Hire> hires = hireService.getHiresByBookId(id);

        model.addAttribute("book", book);
        model.addAttribute("hires", hires);

        return "hires";
    }


    @RequestMapping (value = "/books/hire/{id}", method = RequestMethod.GET)
    public String hire (Model model,@PathVariable("id") Integer id){
        //Hire hire = hireService.hire(id);
        Hire hire = hireService.hire( id, userService.getLoggedUser());
        List<BookDto> books = bookService.convert(bookService.getBooks());
        UserDto loggedUser = userService.convert(userService.getLoggedUser());
        model.addAttribute("books", books);
        model.addAttribute("user", loggedUser);
        model.addAttribute("hireStatus", hire != null);

        if (hire != null)
            model.addAttribute("giveBackDate", hire.getPlannedGiveBackDate());

        return "books";
    }


    @RequestMapping (value = "/user/hires", method = RequestMethod.GET)
    public String loggedUserHires (Model model) {
        User loggedUser = userService.getLoggedUser() ;
        UserDto loggedUserDto = userService.convert(userService.getLoggedUser());
        List <Hire> hires = hireService.getHireListById(userService.getLoggedUser().getId());
        BigDecimal payment = paymentService.getPaymentSumByUser(loggedUser.getId());
        BigDecimal penalty = paymentService.getPenaltySumByUser(loggedUser.getId());

        model.addAttribute("user", loggedUserDto);
        model.addAttribute("hires", hires);
        model.addAttribute("payment", payment);
        model.addAttribute("penalty", penalty);

        return "hires-own";
    }

    @RequestMapping (value = "/admin/hires", method = RequestMethod.GET)
    public String notGiveBackHires (Model model) {
        User loggedUser = userService.getLoggedUser() ;
        UserDto loggedUserDto = userService.convert(userService.getLoggedUser());
        List <Hire> hires = hireService.notGiveBackHireList();
        Map<User, BigDecimal> sanctionUsers = paymentService.getUserWithNegativeBalance();

        model.addAttribute("user", loggedUserDto);
        model.addAttribute("hires", hires);
        model.addAttribute("showMessage","");
        model.addAttribute("sanctionUsers", sanctionUsers);

        return "hires-admin";
    }

    @RequestMapping (value = "/admin/hires/giveBack/{id}", method = RequestMethod.GET)
    public String giveBack(Model model, @PathVariable("id") Long id){
        User loggedUser = userService.getLoggedUser() ;
        UserDto loggedUserDto = userService.convert(userService.getLoggedUser());

        hireService.setHireAsGiveBack(id);
        String bookName = hireService.getHireById(id).getHiredBook().getTitle();
        List <Hire> hires = hireService.notGiveBackHireList();
        Map<User, BigDecimal> sanctionUsers = paymentService.getUserWithNegativeBalance();


        model.addAttribute("user", loggedUserDto);
        model.addAttribute("hires", hires);
        model.addAttribute("showMessage", "GIVEBACK");
        model.addAttribute("bookName", bookName);
        model.addAttribute("sanctionUsers", sanctionUsers);

        return "hires-admin";
    }

    @RequestMapping (value = "/admin/hires/pay/{id}", method = RequestMethod.GET)
    public String pay(Model model, @PathVariable("id") Integer id){
        User loggedUser = userService.getLoggedUser() ;
        UserDto loggedUserDto = userService.convert(userService.getLoggedUser());

        paymentService.pay(id);

        List <Hire> hires = hireService.notGiveBackHireList();

        Map<User, BigDecimal> sanctionUsers = paymentService.getUserWithNegativeBalance();


        model.addAttribute("user", loggedUserDto);
        model.addAttribute("hires", hires);
        model.addAttribute("showMessage", "PAY");
        model.addAttribute("sanctionUsers", sanctionUsers);

        return "hires-admin";
    }


}
