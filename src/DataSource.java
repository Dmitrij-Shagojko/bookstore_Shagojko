import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private Connection connection;

    private String getDataBookstore(String key) {
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("bookstore_bh.properties"))) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(key);
    }

    public Connection getConnection() {
        String url = getDataBookstore("url");
        String user = getDataBookstore("user");
        String password = getDataBookstore("password");
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
