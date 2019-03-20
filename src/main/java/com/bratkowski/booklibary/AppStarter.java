package com.bratkowski.booklibary;

import com.bratkowski.booklibary.domain.Author;
import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.domain.User;
import com.bratkowski.booklibary.repository.BookRepository;
import com.bratkowski.booklibary.services.BookService;
import com.bratkowski.booklibary.services.HireService;
import com.bratkowski.booklibary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:custom.properties")
public class AppStarter implements CommandLineRunner {

    @Autowired

    BookService bookService;

    @Autowired

    UserService userService;

    @Autowired
    HireService hireService;

    /*@Autowired
    Book book;

    Book book2;

    @Value("${spring.pagesize:666}")
    Integer size;


    */
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + hireService.getHiresByBookId(4).size());
    }/*

    @Autowired
    public void setBook2(Book book2) {
        this.book2 = book2;
    }*/

    public void init () {
        Book book = new Book("Ogniem i pierogiem",1865,"Zbigniew Zagłowa","ijojdasoi123",new Author("Dima Bohun"));
        bookService.saveBook(book);

        Book book2 = new Book("Ogniem i laserem",1345,"Zbigniew Zagłowa","ijojdasoi123",new Author("Dima Bohun"));
        bookService.saveBook(book2);

        Book book3 = new Book("Fizyka dla opornych",1345,"Kolegium Fizyków, Wróżbitów i Zbieraczy runa leśnego","ifsdfsd",new Author("Dr. reh. Ambroży Melchlor Brzezina"));
        bookService.saveBook(book3);


        Book book4 = new Book("Fizyka i jak z nią żyć",1345,"Wydawnictwo no i po co","ijojdasoi123",new Author("Kajetan Szymczak"));
        bookService.saveBook(book4);
    }

    public void initUser(){
        userService.createUser("admin","pass");

        userService.addRoleToUser("admin","ADMIN");
        userService.addRoleToUser("admin","DEV");
        userService.addRoleToUser("admin","USER");

        userService.createUser("user", "pass");

        userService.addRoleToUser("user", "USER");
    }
}
