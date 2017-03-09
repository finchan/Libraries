package com.xavier.rest.jersey.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Xavier on 2017-03-09.
 */
@XmlRootElement(name="books")
public class BookWrapper {
    private Book book;

    public BookWrapper( ) {

    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @XmlAttribute(name = "bookId")
    public Long getBookId( ) {
        return book.getBookId();
    }

    @XmlAttribute(name = "bookName")
    public String getBookName( ) {
        return book.getBookName();
    }

    @XmlAttribute(name = "publisher")
    public String getPublisher ( ) {
        return book.getPublisher();
    }

    @Override
    public String toString( ) {
        return book.toString();
    }
}
