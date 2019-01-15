package com.bratkowski.booklibary;

import com.bratkowski.booklibary.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class AppStarter implements CommandLineRunner {

    @Autowired
    Book book;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(book);
    }
}
