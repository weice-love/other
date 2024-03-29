package cn.weicelove.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceConfig {

    private static final Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig("config.properties");

    private String host;
    private Integer port;
    private String user;
    private String password;
    private String database;

    private DataSourceConfig(String filename) {

        log.info("init datasource config...");
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filename);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("配置文件读取失败!!!", e);
            throw new RuntimeException("配置文件读取失败!!!");
        }
        this.database = properties.getProperty("datasource.database");
        this.password = properties.getProperty("datasource.password");
        this.user = properties.getProperty("datasource.user");
        port = Integer.parseInt(properties.getProperty("datasource.port", "3306"));
        this.host = properties.getProperty("datasource.host");
        log.info("init datasource config success!!! data: {}", this);
    }


    public static DataSourceConfig getInstance() {
        return DATA_SOURCE_CONFIG;
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

    @Override
    public String toString() {
        return "DataSourceConfig{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                '}';
    }
}
