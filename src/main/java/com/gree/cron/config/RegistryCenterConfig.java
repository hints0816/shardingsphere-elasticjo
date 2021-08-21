package com.gree.cron.config;

import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.*;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.internal.operate.JobOperateAPIImpl;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.internal.operate.ShardingOperateAPIImpl;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.internal.settings.JobConfigurationAPIImpl;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.internal.statistics.JobStatisticsAPIImpl;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.internal.statistics.ServerStatisticsAPIImpl;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.internal.statistics.ShardingStatisticsAPIImpl;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 180686 on 2021/8/18 17:13
 */


@Configuration
@ConfigurationProperties(prefix = "shardingsphere.elasticjob.zk-config")
public class RegistryCenterConfig {

    /**
     * 连接Zookeeper服务器的列表. 包括IP地址和端口号. 多个地址用逗号分隔. 如: host1:2181,host2:2181
     */
    private String serverLists;

    /**
     * 命名空间.
     */
    private String namespace;

    /**
     * 等待重试的间隔时间的初始值. 单位毫秒.
     */
    private int baseSleepTimeMilliseconds;

    /**
     * 等待重试的间隔时间的最大值. 单位毫秒.
     */
    private int maxSleepTimeMilliseconds;

    /**
     * 最大重试次数.
     */
    private int maxRetries;


    @Bean(name = "elasticRegistryCenter",initMethod = "init")
    public CoordinatorRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverLists, namespace);
        zookeeperConfiguration.setMaxRetries(maxRetries);
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(baseSleepTimeMilliseconds);
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(maxSleepTimeMilliseconds);
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
        regCenter.init();
        return regCenter;
    }

    @Bean
    public JobConfigurationAPI jobConfigurationAPI() {
        return new JobConfigurationAPIImpl(zookeeperRegistryCenter());
    }

    @Bean
    public JobStatisticsAPI jobtatisticsAPI() {
        return new JobStatisticsAPIImpl(zookeeperRegistryCenter());
    }

    @Bean
    public JobOperateAPI jobOperateAPI() {
        return new JobOperateAPIImpl(zookeeperRegistryCenter());
    }

    @Bean
    public ShardingStatisticsAPI shardingStatisticsAPI() {
        return new ShardingStatisticsAPIImpl(zookeeperRegistryCenter());
    }

    @Bean
    public ServerStatisticsAPI serverStatisticsAPI() {
        return new ServerStatisticsAPIImpl(zookeeperRegistryCenter());
    }

    @Bean
    public ShardingOperateAPI shardingOperateAPI() {
        return new ShardingOperateAPIImpl(zookeeperRegistryCenter());
    }

    public String getServerLists() {
        return serverLists;
    }

    public void setServerLists(String serverLists) {
        this.serverLists = serverLists;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getBaseSleepTimeMilliseconds() {
        return baseSleepTimeMilliseconds;
    }

    public void setBaseSleepTimeMilliseconds(int baseSleepTimeMilliseconds) {
        this.baseSleepTimeMilliseconds = baseSleepTimeMilliseconds;
    }

    public int getMaxSleepTimeMilliseconds() {
        return maxSleepTimeMilliseconds;
    }

    public void setMaxSleepTimeMilliseconds(int maxSleepTimeMilliseconds) {
        this.maxSleepTimeMilliseconds = maxSleepTimeMilliseconds;
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

}