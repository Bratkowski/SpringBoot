package com.bratkowski.booklibary.Controllers;

import com.bratkowski.booklibary.domain.Author;
import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.dto.BookDto;
import com.bratkowski.booklibary.dto.UserDto;
import com.bratkowski.booklibary.services.BookService;
import com.bratkowski.booklibary.services.UserService;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooks(Model model){
        List<BookDto> books = bookService.convert(bookService.getBooks());
        UserDto loggedUser = userService.convert(userService.getLoggedUser());
        model.addAttribute("books", books);
        model.addAttribute("user", loggedUser);
        return "books";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectToMainPage (){
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/delete/{id}", method = RequestMethod.GET)
    public String deleteBook (@PathVariable("id") Integer id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public String addBook(Model model){
        Book book = bookService.getNewBook();
        model.addAttribute("book", book);
        return "book";
    }


    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String saveBooks(@Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
           return "book";
        } else {
            bookService.saveBook(book);
            return "redirect:/books";
        }
    }

    @RequestMapping(value = "/books/edit/{id}", method = RequestMethod.GET)
    public String editBook (@PathVariable("id") Integer id, Model model){
        Book book = bookService.getBook(id);
        model.addAttribute("book", book);
        return "book";
    }
}
