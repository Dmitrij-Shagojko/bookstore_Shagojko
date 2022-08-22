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
        String urlKey;
        String userKey;
        String passwordKey;
        String typeOfConnection = PropertiesManager.PROPERTIES.getProperty("connection");
        if (typeOfConnection.equals("remote")) {
            urlKey = "db.remote.url";
            userKey = "db.remote.user";
            passwordKey = "db.remote.password";
        } else{
            urlKey = "db.local.url";
            userKey = "db.local.user";
            passwordKey = "db.local.password";
        }
           String url = PropertiesManager.PROPERTIES.getProperty(urlKey);
           log.info("URL of database" + url);
           String user = PropertiesManager.PROPERTIES.getProperty(userKey);
           String password = PropertiesManager.PROPERTIES.getProperty(passwordKey);
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
