package com.company.dao.connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final Logger log = LogManager.getLogger(DataSource.class);
    private static final int LOCAL_CONNECTION = 1;
    private static final int REMOTE_CONNECTION = 2;
    private static final int CONNECTION = 1;
    private Connection connection;

    private String getDataBookstore(String key) {
        log.info("Get data for connection");
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get("src/main/resources/bookstore_bh.properties"))) {
            properties.load(input);
        } catch (IOException e) {
            log.error(e);
        }
        return properties.getProperty(key);
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        log.info("Get connection");
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
                log.info("Connection - on");
            } catch (SQLException e) {
                log.error(e);
            }
        }
        return connection;
    }

    public void close() {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
