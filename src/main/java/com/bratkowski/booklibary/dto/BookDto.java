package com.bratkowski.booklibary.dto;


import java.util.Date;

public class BookDto {
    private Integer id;
    private String title;
    private Integer year;
    private String publisher;
    private String isbn;
    private String authorName;
    private boolean hireStatus;
    private Date giveBackDate;



    public Integer getId() {
        return id;
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

    public String getAuthorName() {
        return authorName;
    }

    public boolean isHireStatus() {
        return hireStatus;
    }

    public Date getGiveBackDate() {
        return giveBackDate;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setHireStatus(boolean hireStatus) {
        this.hireStatus = hireStatus;
    }

    public void setGiveBackDate(Date giveBackDate) {
        this.giveBackDate = giveBackDate;
    }
}
