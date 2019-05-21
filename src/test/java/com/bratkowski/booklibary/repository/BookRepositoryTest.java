package com.bratkowski.booklibary.repository;


import com.bratkowski.booklibary.domain.Author;
import com.bratkowski.booklibary.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    public static final Integer BOOK_ID_EXESIT = 30;
    public static final Integer BOOK_ID_NOT_EXESIT = 31;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void isBookRepositoryNotNull() {
        assertNotNull(bookRepository);
    }

    @Test
    public void getBooksTest() {
        Collection<Book> books = bookRepository.getBooks();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        for(Book book : books)
            validBook(book);
    }


    @Test
    public void getBookById(){
        Book bookexist = bookRepository.getBook(BOOK_ID_EXESIT);
        assertNotNull(bookexist);
        validBook(bookexist);

        Book bookNoExist = bookRepository.getBook(BOOK_ID_NOT_EXESIT);
        assertNull(bookNoExist);

    }

    @Test
    public void saveBookTest() {
        int bookCount = bookRepository.getBooks().size();
        bookRepository.saveBook(null);
        assertEquals(bookCount, bookRepository.getBooks().size());

        bookRepository.saveBook(getBookTemplate());
        assertEquals(bookCount + 1,bookRepository.getBooks().size());
    }

    @Test
    public void updateBookTest(){
        Book book = bookRepository.getBook(BOOK_ID_EXESIT);
        assertNotNull(book);


        book.setTitle("TEST2100");
        book.setYear(2100);

        bookRepository.updateBook(book);
        Book bookModified = bookRepository.getBook(BOOK_ID_EXESIT);
        assertEquals(bookModified.getTitle(), "TEST2100");
        assertEquals(bookModified.getYear(), new Integer (2100));
    }

    public void validBook(Book book){
        assertNotNull(book.getId());
        assertTrue(book.getId() >= 0);
        assertNotNull(book.getTitle());
        assertTrue(book.getTitle().length() >= 2);
    }

    public Book getBookTemplate() {
        Book book = new Book();
        book.setTitle("TEST");
        //book.setAuthor(new Author("TEST"));
        book.setYear(2000);
        book.setPublisher("wytylka");
        book.setIsbn("isdb321");
        return book;
    }
}

