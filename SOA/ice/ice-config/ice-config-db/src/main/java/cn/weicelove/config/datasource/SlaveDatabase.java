package cn.weicelove.config.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SlaveDatabase {



    /**
     * 读数据源配置01
     */
    @Bean(name = "read-slave01")
    @ConfigurationProperties(prefix = "spring.datasource.read-slave01")
    public DataSource read01DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 读数据源配置02
     */
    @Bean(name = "read-slave02")
    @ConfigurationProperties(prefix = "spring.datasource.read-slave02")
    public DataSource read02DataSource() {
        return DataSourceBuilder.create().build();
    }

}
