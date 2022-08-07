import java.math.BigDecimal;
import java.util.List;

public class BookService {
    private final BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<Book> getAll() {
        LogUtil.logger.debug(BookService.class + " - use method getAll");
        List<Book> books = bookDAO.getAll();
        if (books.isEmpty()) {
            throw new RuntimeException("List of books is empty");
        }
        return books;
    }

    public Book getBookById(Long id) {
        LogUtil.logger.debug(BookService.class + " - use method getBookById");
        Book bookById = bookDAO.getBookById(id);
        if (bookById == null) {
            throw new RuntimeException("Book with id: " + id + " - not found");
        }
        return bookById;
    }

    public void delete(Long id) {
        LogUtil.logger.debug(BookService.class + " - use method delete");
        if (!bookDAO.delete(id)) {
            throw new RuntimeException("Couldn't delete book (id = " + id + ");");
        }
    }

    public Long countAllBooks() {
        LogUtil.logger.debug(BookService.class + " - use method countAllBooks");
        Long count = Long.parseLong(String.valueOf(bookDAO.countAllBooks()));
        if (count == 0) {
            throw new RuntimeException("List of books is empty");
        }
        return count;
    }

    public Book create(Book book) {
        LogUtil.logger.debug(BookService.class + " - use method create");
        if (book.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price couldn't be < 0");
        }
        Book newBook = bookDAO.create(book);
        if (newBook == null) {
            throw new RuntimeException("Book:" + book.getName() + "- not created");
        }
        return newBook;

    }

    public Book update(Book book) {
        LogUtil.logger.debug(BookService.class + " - use method update");
        if (book.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Price couldn't be < 0");
        }
        Book upBook = bookDAO.update(book);
        if (upBook == null) {
            throw new RuntimeException("Book:" + book.getName() + "- not updated");
        }
        return upBook;
    }

    public List<Book> getBookByAuthor(String author) {
        LogUtil.logger.debug(BookService.class + " - use method getBookByAuthor");
        List<Book> booksByAuthor = bookDAO.getBooksByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            throw new RuntimeException("List of books is empty");
        }
        return booksByAuthor;
    }

    public Book getBookByIsbn(String isbn) {
        LogUtil.logger.debug(BookService.class + " - use method getBookByIsbn");
        Book bookByIsbn = bookDAO.getBookByIsbn(isbn);
        if (bookByIsbn == null) {
            throw new RuntimeException("Book with ISBN:" + isbn + "- not founded");
        }
        return bookByIsbn;
    }

    public BigDecimal getCostBookByAuthor(String author) {
        LogUtil.logger.debug(BookService.class + " - use method getCostBookByAuthor");
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
