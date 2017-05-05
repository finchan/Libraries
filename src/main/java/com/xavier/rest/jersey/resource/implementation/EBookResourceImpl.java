package com.xavier.rest.jersey.resource.implementation;

import com.xavier.rest.jersey.domain.Book;
import com.xavier.rest.jersey.resource.interf.BookResourceInterface;
import com.xavier.rest.jersey.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Xavier on 2017/3/27.
 */
public class EBookResourceImpl implements BookResourceInterface {
    @Autowired
    private BookService bookService;

    public String getWeight() {
        return "150M";
    }

    public String newBook(final Book book) {
        Book bk = bookService.saveBook(book);
        return bk.toString();
    }

    public void delete(final long bookId) {
        bookService.deleteBook(bookId);
    }

    public Book createBook(final Book book) {
        Book bk = bookService.saveBook(book);
        return bk;
    }
}
