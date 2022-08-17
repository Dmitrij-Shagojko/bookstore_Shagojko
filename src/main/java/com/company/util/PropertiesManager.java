package com.company.util;

import com.company.dao.connection.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static final Logger log = LogManager.getLogger(PropertiesManager.class);
    public final static PropertiesManager PROPERTIES = new PropertiesManager();
    private final Properties props;

    private PropertiesManager() {
        this.props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/bookstore_bh.properties"));
        } catch (IOException e) {
            log.error(e);
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
