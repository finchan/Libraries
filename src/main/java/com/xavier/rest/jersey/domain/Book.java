package com.xavier.rest.jersey.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Xavier on 2017/2/28.
 */
@Entity
@Table(name = "simple_book", schema = "simple_service_book")
@XmlRootElement
public class Book implements Serializable{
    private Long bookId;
    private String bookName;
    private String publisher;

    public Book() {
    }

    public Book(Long bookId) {
        this.bookId = bookId;
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public Book(Long bookId, String bookName, String publisher) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publisher = publisher;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKID")
    @XmlAttribute(name = "bookId")
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Column(name = "BOOKNAME")
    @XmlAttribute(name = "bookName")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Column(name = "PUBLISHER")
    @XmlAttribute( name= "publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString( ) {
        return bookId + ": " + bookName + ": " + publisher;
    }
}
