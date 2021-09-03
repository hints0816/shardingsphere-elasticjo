package com.gree.corn.config;

import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 180686 on 2021/8/31 16:32
 * 监听器配置
 */

class SimpleJobListener implements ElasticJobListener {

    private Logger log = LoggerFactory.getLogger(SimpleJobListener.class);

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("beforeJobExecuted");
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("afterJobExecuted");
    }

    @Override
    public String getType() {
        return "simpleJobListener";
    }
}