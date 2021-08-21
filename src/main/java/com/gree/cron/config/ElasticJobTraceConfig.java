package com.gree.cron.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by 180686 on 2021/8/18 17:30
 */

@Configuration
@ConfigurationProperties(prefix = "shardingsphere.elasticjob.trace-config")
public class ElasticJobTraceConfig {

    private String driverClass;

    private String url;

    private String username;

    private String password;

    private DataSource tracingConfigurationDataSource;

    @Bean(name = "ElasticJobTracingConfiguration")
    public TracingConfiguration tracingConfiguration(){
        TracingConfiguration<DataSource> tracingConfig = new TracingConfiguration<>("RDB", tracingConfigurationDataSource);
        return tracingConfig;
    }

    @Bean(name = "traceDao")
    public Dao loadTraceDao(){
        Dao dao = new NutDao(tracingConfigurationDataSource);
        return dao;
    }

    @PostConstruct
    public void init()
    {
        tracingConfigurationDataSource = tracingConfigurationDataSource();
    }

    public DataSource tracingConfigurationDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setMinIdle(2);
        druidDataSource.setMaxActive(5);
        return druidDataSource;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
