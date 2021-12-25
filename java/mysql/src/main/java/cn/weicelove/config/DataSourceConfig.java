package cn.weicelove.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    private String host;
    private int port;
    private String user;
    private String password;
    private String database;

    public DataSourceConfig(String filename) {

        log.info("init datasource config...");
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("配置文件读取失败!!!", e);
            throw new RuntimeException("配置文件读取失败!!!");
        }
        this.database = properties.getProperty("database");
        this.password = properties.getProperty("password");
        this.user = properties.getProperty("user");
        this.port =(int)properties.getOrDefault("port", 3306);
        this.host = properties.getProperty("host");
        log.info("init datasource config success!!!");
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }

    public static void main(String[] args) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig("config.properties");
    }
}
