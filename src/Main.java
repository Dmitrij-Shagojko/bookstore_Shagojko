import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BookController bookController = new BookController();
        bookController.run();
        DataSource dataSource = new DataSource();
        dataSource.close();
    }
}