package com.bratkowski.booklibary;

import com.bratkowski.booklibary.domain.Book;
import com.bratkowski.booklibary.repository.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookLibaryApplicationTests {

	@Autowired
	BookRepository bookRepository;


	@Test
	public void contextLoads() {
	}

	@Test
	public void getBooksByAuthorTest(){
		Collection<Book> books = bookRepository.getBooksByAuthor("Dima Bohun");
		System.out.println(">>>>>>" + books.size());
	}
}

