package com.xavier.rest.jersey.dao;

import com.xavier.rest.jersey.domain.Book;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by Xavier on 2017/2/28.
 */
@Repository
public class BookDao {
    private static final Logger LOGGER = Logger.getLogger(BookDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public BookDao() {
    }

    public Book findById(final Long bookId) {
        try{
            return entityManager.find(Book.class, bookId);
        }catch (Exception e) {
            return null;
        }
    }

    public List<Book> findAll() {
        return findAll(false, 0, 0);
    }

    public List<Book> findAll(final boolean isPaging, final int firstResult, final int maxResults){
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        final TypedQuery<Book> q = entityManager.createQuery(cq);
        if (isPaging) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Transactional
    public void save(final Book entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public Book store(Book book) {
        return entityManager.merge(book);
    }

    @Transactional
    public void update(Book book) {
        final StringBuilder jpql = new StringBuilder();
        jpql.append("UPDATE Book b SET b.bookName='").append(book.getBookName());
        jpql.append("', b.publisher='").append(book.getPublisher());
        jpql.append("' WHERE b.bookId=").append(book.getBookId());
        entityManager.createQuery(jpql.toString()).executeUpdate();
    }

    @Transactional
    public boolean remove(final Long bookId) {
        final Book book0 = findById(bookId);
        if (book0 != null) {
            entityManager.remove(book0);
            return true;
        } else {
            return false;
        }
    }
}
