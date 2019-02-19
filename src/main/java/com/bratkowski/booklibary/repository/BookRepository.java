package com.bratkowski.booklibary.repository;

import com.bratkowski.booklibary.domain.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public Collection<Book> getBooks () {
        return em.createQuery("from Book", Book.class).getResultList();
    }

    public Book getBook(int id){
        return em.find(Book.class, id);
    }

    @Transactional
    public void saveBook(Book book){
        if(book!= null)
            em.persist(book);
    }

    @Transactional
    public void updateBook (Book book) {
        if (book != null){
            em.merge(book);
        }
    }

    @Transactional
    public void  deleteBook (Book book) {
       if (book != null) {
           em.remove(book);
       }
    }

    public Collection<Book> getBooksByAuthor(String authorName) {
        return em.createQuery("from Book b WHERE LOWER (b.author.name) LIKE concat ('%', :authorName, '%')", Book.class)
                .setParameter("authorName", authorName.toLowerCase())
                .getResultList();
    }

    public Collection<Book> getBooks(Integer year, String publisher, String isbn) {
        String query = "from Book b";
        String conditions = "";

        System.out.println(">>>>>>" + year);
        System.out.println(">>>>>>" + publisher);
        System.out.println(">>>>>>" + isbn);


        if(year != null)
            conditions += "b.year = :year";

        if(publisher != null)
            if (conditions.isEmpty())
            conditions += "b.publisher = :publisher";
        else
            conditions += " AND b.publisher = :publisher";

        if(isbn != null)
            if (conditions.isEmpty())
            conditions += "b.isbn = :isbn";
        else
            conditions +=" AND b.isbn = :isbn";

        if(!conditions.isEmpty())
            query += " WHERE " + conditions;

        System.out.println(query);

        TypedQuery typedQuery = em.createQuery(query, Book.class);

        if(year != null)
            typedQuery.setParameter("year", year);

        if(publisher != null)
            typedQuery.setParameter("publisher", publisher);

        if(isbn != null)
            typedQuery.setParameter("isbn", isbn);

        return typedQuery.getResultList();
    }
}
