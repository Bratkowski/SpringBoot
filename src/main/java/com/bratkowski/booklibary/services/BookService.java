package com.bratkowski.booklibary.services;

import com.bratkowski.booklibary.domain.Author;
import com.bratkowski.booklibary.domain.Hire;
import com.bratkowski.booklibary.dto.BookDto;
import com.bratkowski.booklibary.repository.AuthorRepository;
import com.bratkowski.booklibary.repository.BookRepository;
import com.bratkowski.booklibary.repository.HireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bratkowski.booklibary.domain.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    HireRepository hireRepository;

    public List<Book> getBooks() {
        return new ArrayList<>(bookRepository.getBooks());
    }

    public void saveBook (Book book) {
        if (book != null) {
            System.out.println("Zapiasuję ksiązkę o id: " + book.getId());
            boolean bookExist = bookRepository.getBook(book.getId()) != null;

            if(bookExist){
                authorRepository.updateAuthor(book.getAuthor());
                bookRepository.updateBook(book);
            } else {
                authorRepository.saveAuthor(book.getAuthor());
                bookRepository.saveBook(book);
            }
        }
    }

    public void deleteBook(int id) {
        Book bookToRemove = bookRepository.getBook(id);
        Author authorToRemove = bookToRemove.getAuthor();
        bookRepository.deleteBook(bookToRemove);
        authorRepository.removeAuthor(authorToRemove);
    }

    public Book getNewBook () {
        Book newBook = new Book();
        new Book().setAuthor(new Author());
        return newBook;
    }

    public Book getBook (int id){
        return bookRepository.getBook(id);
    }

    public List<Book> getBookByAuthor (String authorName) {
        if(authorName != null){
            return new ArrayList<>(bookRepository.getBooksByAuthor(authorName));
        } else
            return null;
    }

    public List<Book> getBooks(Integer year, String publisher, String isbn) {
        return new ArrayList<>(bookRepository.getBooks(year,publisher,isbn));
    }
    public List<Book> getBookByTitle (String title) {
        if(title != null){
            return new ArrayList<>(bookRepository.getBooksByTitle(title));
        } else
            return null;
    }

    public BookDto convert(Book book) {
        if (book == null)
            return null;

        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setPublisher(book.getPublisher());
        bookDto.setYear(book.getYear());
        bookDto.setIsbn(book.getIsbn());

        List<Hire> hires = hireRepository.findByIdAndNotGiveBack(book.getId());

        bookDto.setHireStatus(hires.size() > 0);

        if (hires.size() > 0)
            bookDto.setGiveBackDate(hires.get(0).getPlannedGiveBackDate());

        return bookDto;
    }

    public List<BookDto> convert (List<Book> books){
        if (books == null)
            return null;

        List<BookDto> bookDtoList = new ArrayList<>();
        for (Book book: books)
            bookDtoList.add(convert(book));

        return bookDtoList;


    }
}
