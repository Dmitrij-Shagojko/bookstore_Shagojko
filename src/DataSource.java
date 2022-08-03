import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    public static final String URL = "jdbc:postgresql://127.0.0.1:5433/bookstore_bh";
    public static final String USER = "postgres";
    public static final String PASSWORD = "6744uytr";
    private Connection connection;
    public Connection getConnection(){
        if (connection == null){
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
    public void close() throws SQLException {
        if (connection != null){
            connection.close();
        }
    }
}
