package com.company.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class DataSource {
    public static final DataSource INSTANCE = new DataSource();

    private static final Logger log = LogManager.getLogger(DataSource.class);
    private static final int LOCAL_CONNECTION = 1;
    private static final int REMOTE_CONNECTION = 2;
    private static final int CONNECTION = REMOTE_CONNECTION;
    private Connection connection;

    private HashMap<String, String> getDataBookstore() {
        Properties properties = new Properties();
        HashMap<String, String> dataConnection = new HashMap<>();
        try (InputStream input = getClass().getResourceAsStream("/bookstore_bh.properties")) {
            properties.load(input);
            dataConnection.put("url", properties.getProperty("url"));
            dataConnection.put("user", properties.getProperty("user"));
            dataConnection.put("password", properties.getProperty("password"));
            dataConnection.put("url_Elephant", properties.getProperty("url_Elephant"));
            dataConnection.put("user_Elephant", properties.getProperty("user_Elephant"));
            dataConnection.put("password_Elephant", properties.getProperty("password_Elephant"));
        } catch (IOException e) {
            log.error(e);
        }
        return dataConnection;
    }

    public Connection getConnection() {
        String url = null;
        String user = null;
        String password = null;
        HashMap<String, String> dataConnection = getDataBookstore();
        if (LOCAL_CONNECTION == CONNECTION) {
            url = dataConnection.get("url");
            log.info("URL of database" + url);
            user = dataConnection.get("user");
            password = dataConnection.get("password");
        } else if (REMOTE_CONNECTION == CONNECTION) {
            url = dataConnection.get("url_Elephant");
            log.info("URL of database" + url);
            user = dataConnection.get("user_Elephant");
            password = dataConnection.get("password_Elephant");
        }
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                log.info("Connection - on");
            } catch (SQLException | ClassNotFoundException e) {
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
