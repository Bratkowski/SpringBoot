package com.bratkowski.booklibary.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Random;

@Entity
//@Table(name = "book")
//@Scope("prototype")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //@Column(name = "bookTitle")
    @NotNull
    @Size(min=2, message = "Tytuł musi posiadać conajmniej 2 litery")
    private String title;
    @NotNull(message = "Rok wydania musi być z przedziału 1 - 9999")
    @Range(min=1, max = 9999, message = "Rok wydania musi być z przedziału 1 - 9999")
    private Integer year;
    @NotNull
    @Size(min=2, message = "Wydawca musi posiadać conajmniej 2 litery")
    private String publisher;
    @NotNull
    @Size(min=5, message = "isbn usi posiadać conajmniej 5 znaków")
    private String isbn;

    @OneToOne
    @Valid
    private Author author;

    public Book(){

    }

    public Book(String title, int year, String publisher, String isbn, Author author) {
        this.title = title;
        this.year = year;
        this.publisher = publisher;
        this.isbn = isbn;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {return author;}

    public void setAuthor(Author author) {this.author = author;}
    @Override
    public String toString() {
        return "book{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
