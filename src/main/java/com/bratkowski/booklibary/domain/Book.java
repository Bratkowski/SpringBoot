package com.bratkowski.booklibary.domain;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private String title;
    private int year;
    private String publisher;
    private String isbn;


    public Book() {
        this.title = "ogniem i laserem";
        this.year = 1976;
        this.publisher = "Kurpis";
        this.isbn = "AZ3422342";
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
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

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

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
