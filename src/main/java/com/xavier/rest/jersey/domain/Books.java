package com.xavier.rest.jersey.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Xavier on 2017/2/28.
 */
@XmlRootElement(name="books")
public class Books implements Serializable{
    private List<Book> bookList;

    public Books(){

    }

    @XmlElement(name="book")
    @XmlElementWrapper
    public List<Book> getBookList( ) {
        return bookList;
    }

    public void setBookList(final List<Book> bookList) {
        this.bookList = bookList;
    }

    public Books(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookList=" + bookList +
                '}';
    }
}
