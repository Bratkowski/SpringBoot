package com.bratkowski.booklibary;

import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.repository.BookRepository;
import com.bratkowski.booklibary.services.BookService;
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


    /*@Autowired
    Book book;

    Book book2;

    @Value("${spring.pagesize:666}")
    Integer size;


    */
    @Override
    public void run(String... args) throws Exception {
        init();
    }/*

    @Autowired
    public void setBook2(Book book2) {
        this.book2 = book2;
    }*/

    public void init () {
        Book book = new Book("Ogniem i pierogiem",1865,"Zbigniew Zagłowa","ijojdasoi123","Dima Bohun");
        bookService.saveBook(book);

        Book book2 = new Book("Ogniem i laserem",1345,"Zbigniew Zagłowa","ijojdasoi123","Dima Bohun");
        bookService.saveBook(book2);
    }
}
