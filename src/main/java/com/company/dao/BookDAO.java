package com.company.dao;

import com.company.entity.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getAll();

    Book getBookById(Long id);

    Book getBookByIsbn(String isbn);

    Book create(Book book);

    Book update(Book book);

    boolean delete(Long id);

    List<Book> getBooksByAuthor(String author);

    int countAllBooks();
}
