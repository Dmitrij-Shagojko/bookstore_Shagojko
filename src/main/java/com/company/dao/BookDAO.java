package com.company.dao;

import com.company.entity.Book;

import java.util.List;

public interface BookDAO extends AbstractDao<Long, Book> {

    Book getBookByIsbn(String isbn);

    List<Book> getBooksByAuthor(String author);
}
