import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAOImpl();
    }

    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    public Book getBookById(Long id) {
        return bookDAO.getBookById(id);
    }

    public boolean delete(Long id) {
        return bookDAO.delete(id);
    }

    public Long countAllBooks() {
        return Long.parseLong(String.valueOf(bookDAO.countAllBooks()));
    }

    public Book create(Book book) {
        return bookDAO.create(book);
    }

    public Book update(Book book) {
        return bookDAO.update(book);
    }

    public List<Book> getBookByAuthor(String author) {
        return bookDAO.getBooksByAuthor(author);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }
    public BigDecimal getCostBookByAuthor(String author){
        List<Book> books = bookDAO.getBooksByAuthor(author);
        BigDecimal cost = new BigDecimal(0);
        for (Book book : books) {
            cost = cost.add(book.getPrice());
        }
        return cost;
    }
}
