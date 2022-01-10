package cn.weicelove.config.datasource;


import cn.weicelove.common.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("write-datasource")
    private DataSource writeDataSource;

    @Value("${spring.datasource.slave-num:1}")
    private Integer slaveNum;

    /**
     * desc:
     *      返回生效的数据源名称
     * @return 数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dbType = DataSourceSelector.getDbType();
        if (dbType != null && dbType.startsWith("write")) {
            // 配置写
            return dbType;
        } else {
            // 读数据库，选择方式 TODO
            return dbType;
        }
    }


    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> dataSource = new HashMap<>();
        dataSource.put("write", writeDataSource);
        for (Integer i = 0; i < slaveNum; i++) {
            String slaveName = "read-slave" + String.format("%02d", i);
            dataSource.put(slaveName, SpringUtil.getBean(slaveName, DataSource.class));
        }
        setTargetDataSources(dataSource);
        setDefaultTargetDataSource(writeDataSource);
        super.afterPropertiesSet();
    }
}
