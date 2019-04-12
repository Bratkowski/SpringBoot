package com.bratkowski.booklibary.Controllers;


import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.dto.BookDto;
import com.bratkowski.booklibary.dto.UserDto;
import com.bratkowski.booklibary.services.BookService;
import com.bratkowski.booklibary.services.HireService;
import com.bratkowski.booklibary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HireController {

    @Autowired
    BookService bookService;

    @Autowired
    HireService hireService;

    @Autowired
    UserService userService;


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
        Hire hire = hireService.hire(id);
        List<BookDto> books = bookService.convert(bookService.getBooks());
        UserDto loggedUser = userService.convert(userService.getLoggedUser());
        model.addAttribute("books", books);
        model.addAttribute("user", loggedUser);
        model.addAttribute("hireStatus", hire != null);

        if (hire != null)
            model.addAttribute("giveBackDate", hire.getPlannedGiveBackDate());

        return "books";
    }


    @RequestMapping (value = "/books/hires", method = RequestMethod.GET)
    public String loggedUserHires () {
        UserDto loggedUser = userService.convert(userService.getLoggedUser());
        List <Hire> hires = hireService.getHireListById(userService.getLoggedUser().getId());
        return "own-hires";
    }
}
