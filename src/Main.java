import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataSource dataSourceBook = new DataSource();
        BookDAO bookDAO = new BookDAOImpl(dataSourceBook);
        BookService bookService = new BookService(bookDAO);
        BookController bookController = new BookController(bookService);
        bookController.run();
        dataSourceBook.close();
    }
}