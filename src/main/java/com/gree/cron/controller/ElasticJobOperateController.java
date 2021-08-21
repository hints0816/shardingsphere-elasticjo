package com.gree.cron.controller;

import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobOperateAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.ShardingStatisticsAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.domain.ShardingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by 180686 on 2021/8/19 14:42
 * elasticJob 操作类 API
 */

@RestController
@RequestMapping("/elastic/operate")
public class ElasticJobOperateController {

    @Autowired
    private JobOperateAPI jobOperateAPI;

    @Autowired
    private ShardingStatisticsAPI shardingStatisticsAPI;

    /**
     * 触发作业执行
     * @param jobName
     */
    @GetMapping(value = "/{jobName}")
    public void trigger(@PathVariable String jobName){
        jobOperateAPI.trigger(jobName);
    }

    /**
     * 禁用作业
     * @param jobName
     */
    @PutMapping(value = "/{jobName}/disable")
    public void disable(@PathVariable String jobName){
        Collection<ShardingInfo> shardingInfos = shardingStatisticsAPI.getShardingInfo(jobName);
        shardingInfos.forEach(shardingInfo -> {
            jobOperateAPI.disable(jobName,shardingInfo.getServerIp());
        });
    }

    /**
     * 启用作业
     * @param jobName
     */
    @PutMapping(value = "/{jobName}/enable")
    public void enable(@PathVariable String jobName){
        Collection<ShardingInfo> shardingInfos = shardingStatisticsAPI.getShardingInfo(jobName);
        shardingInfos.forEach(shardingInfo -> {
            jobOperateAPI.enable(jobName,shardingInfo.getServerIp());
        });
    }

    /**
     * 停止调度作业
     * @param jobName
     */
    @PutMapping(value = "/{jobName}/shutdown")
    public void shutdown(@PathVariable String jobName){
        Collection<ShardingInfo> shardingInfos = shardingStatisticsAPI.getShardingInfo(jobName);
        shardingInfos.forEach(shardingInfo -> {
            jobOperateAPI.shutdown(jobName,shardingInfo.getServerIp());
        });
    }

    /**
     * 删除作业
     * @param jobName
     */
    @PutMapping(value = "/{jobName}/remove")
    public void remove(@PathVariable String jobName){
        Collection<ShardingInfo> shardingInfos = shardingStatisticsAPI.getShardingInfo(jobName);
        shardingInfos.forEach(shardingInfo -> {
            jobOperateAPI.remove(jobName,shardingInfo.getServerIp());
        });
    }
}
