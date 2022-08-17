package com.company.dao.connection;

import com.company.util.PropertiesManager;
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

    private DataSource() {
    }

    private static final Logger log = LogManager.getLogger(DataSource.class);
    private Connection connection;

    public Connection getConnection() {
        String url = null;
        String user = null;
        String password = null;
        String typeOfConnection = PropertiesManager.PROPERTIES.getProperty("connection");
        if (typeOfConnection.equals("local")) {
            url = PropertiesManager.PROPERTIES.getProperty("url");
            log.info("URL of database" + url);
            user = PropertiesManager.PROPERTIES.getProperty("user");
            password = PropertiesManager.PROPERTIES.getProperty("password");
        } else if (typeOfConnection.equals("remote")) {
            url = PropertiesManager.PROPERTIES.getProperty("url_Elephant");
            log.info("URL of database" + url);
            user = PropertiesManager.PROPERTIES.getProperty("user_Elephant");
            password = PropertiesManager.PROPERTIES.getProperty("password_Elephant");
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
        log.info("Connection - off");
    }
}
