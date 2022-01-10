package cn.weicelove.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MasterDatabase {

    /**
     * 写数据源配置
     */
    @Bean(name = "write-datasource")
    @ConfigurationProperties(prefix = "spring.datasource.write-datasource")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }
}
