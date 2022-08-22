package com.company.service.impl;

import com.company.dao.BookDAO;
import com.company.entity.Book;
import com.company.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class);
    private final BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAll() {
        log.debug("Use method getAll");
        List<Book> books = bookDAO.findAll();
        return books;
    }

    @Override
    public Book getBookById(Long id) {
        log.debug("Use method getBookById. ID = {}", id);
        Book bookById = bookDAO.findById(id);
        if (bookById == null) {
            try {
                throw new RuntimeException("Book with id: " + id + " - not found");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return bookById;
    }

    @Override
    public void delete(Long id) {
        log.debug("Use method delete. Book delete with ID = {}", id);
        bookDAO.delete(id);
    }

    @Override
    public Long countAllBooks() {
        log.debug("Use method countAllBooks");
        Long count = Long.parseLong(String.valueOf(bookDAO.countAll()));
        if (count == 0) {
            try {
                throw new RuntimeException("List of books is empty");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return count;
    }

    @Override
    public Book create(Book book) {
        log.debug("Use method create book. Book: {}", book);
        if (book.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            try {
                throw new RuntimeException("Price couldn't be < 0");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        Book newBook = bookDAO.create(book);
        if (newBook == null) {
            try {
                throw new RuntimeException("Book:" + book.getName() + "- not created");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return newBook;

    }

    @Override
    public Book update(Book book) {
        log.debug("Use method update book for book: {}", book);
        if (book.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price couldn't be < 0");
        }
        Book upBook = bookDAO.update(book);
        if (upBook == null) {
            try {
                throw new RuntimeException("Book:" + book.getName() + "- not updated");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return upBook;
    }

    @Override
    public List<Book> getBookByAuthor(String author) {
        log.debug("Use method getBookByAuthor. Where author: {}", author);
        List<Book> booksByAuthor = bookDAO.getBooksByAuthor(author);
        return booksByAuthor;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        log.debug("Use method getBookByIsbn. Where isbn: {}", isbn);
        Book bookByIsbn = bookDAO.getBookByIsbn(isbn);
        if (bookByIsbn == null) {
            try {
                throw new RuntimeException("Book with ISBN:" + isbn + "- not founded");
            } catch (RuntimeException e) {
                log.error(e.getMessage(), e);
            }
        }
        return bookByIsbn;
    }

    @Override
    public BigDecimal getCostBookByAuthor(String author) {
        log.debug("Use method getCostBookByAuthor. Where author: {}", author);
        List<Book> books = bookDAO.getBooksByAuthor(author);
        BigDecimal cost = new BigDecimal(0);
        if (!books.isEmpty()) {
            for (Book book : books) {
                cost = cost.add(book.getPrice());
            }
        }
        return cost;
    }
}
