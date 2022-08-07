import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final int LOCAL_CONNECTION = 1;
    private static final int REMOTE_CONNECTION = 2;
    private static final int CONNECTION = 2;
    private Connection connection;

    private String getDataBookstore(String key) {
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/bookstore_bh.properties"))) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }

    public Connection getConnection() {
        String url = null;
        String user = null;
        String password = null;
        if (LOCAL_CONNECTION == CONNECTION) {
            url = getDataBookstore("url");
            user = getDataBookstore("user");
            password = getDataBookstore("password");
        } else if (REMOTE_CONNECTION == CONNECTION) {
            url = getDataBookstore("url_Elephant");
            user = getDataBookstore("user_Elephant");
            password = getDataBookstore("password_Elephant");
        }
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
