package com.gree.cron.controller;

import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.ShardingStatisticsAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.domain.ShardingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by 180686 on 2021/8/19 14:42
 * elasticJob 作业分片状态展示 API
 */

@RestController
@RequestMapping("/elastic/shardingstatistics")
public class ElasticJobShardingStatisticsController {

    @Autowired
    private ShardingStatisticsAPI shardingStatisticsAPI;

    /**
     * 获取作业分片信息集合
     * @param jobName
     * @return
     */
    @GetMapping(value = "/{jobName}")
    public Collection<ShardingInfo> getJobConfiguration(@PathVariable String jobName){
        Collection<ShardingInfo> shardingInfo = shardingStatisticsAPI.getShardingInfo(jobName);
        return shardingInfo;
    }
}
