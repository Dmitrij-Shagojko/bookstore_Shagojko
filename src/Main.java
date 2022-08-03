import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        BookDAO bookDAO = new BookDAOImpl(new DataSource());
        DataSource dataSource = new DataSource();
        App app = new App(bookDAO);
        app.run();
        dataSource.close();
    }
}