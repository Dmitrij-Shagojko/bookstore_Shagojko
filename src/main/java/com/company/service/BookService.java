package com.company.service;

import com.company.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    List<Book> getAll();

    Book getBookById(Long id);

    void delete(Long id);

    Long countAllBooks();

    Book create(Book book);

    Book update(Book book);

    List<Book> getBookByAuthor(String author);

    Book getBookByIsbn(String isbn);

    BigDecimal getCostBookByAuthor(String author);
}
