package com.xavier.rest.jersey.service;
import com.xavier.rest.jersey.dao.BookDao;
import com.xavier.rest.jersey.domain.Books;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xavier.rest.jersey.domain.Book;

/**
 * Created by Xavier on 2017/2/28.
 */
@Service
public class BookService {
    private static final Logger LOGGER = Logger.getLogger(BookService.class);

    @Autowired
    private BookDao bookDao;

    public BookService() {
    }

    public Book saveBook(final Book book) {
        return bookDao.store(book);
    }

    public Book getBook(final Long bookId) {
        try{
            return bookDao.findById(bookId);
        }catch(Exception e) {
            return new Book(-1L, "", "");
        }
    }

    public Books getBooks() {
        return new Books(bookDao.findAll());
    }

    public Book updateBook(final Long bookId, final Book book) {
        book.setBookId(bookId);
        bookDao.update(book);
        return book;
    }

    public boolean deleteBook(final Long bookId) {
        return bookDao.remove(bookId);
    }
}
