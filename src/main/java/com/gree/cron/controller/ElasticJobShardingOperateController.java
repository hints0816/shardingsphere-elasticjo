package com.gree.cron.controller;

import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.ShardingOperateAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 180686 on 2021/8/19 14:42
 * elasticJob 操作分片的 API
 */

@RestController
@RequestMapping("/elastic/shardingoperate")
public class ElasticJobShardingOperateController {

    @Autowired
    private ShardingOperateAPI shardingOperateAPI;

    /**
     * 禁用作业分片
     * @param jobName
     * @param item
     */
    @PutMapping("/disable")
    public void getJobConfiguration(@RequestParam("jobname")String jobName,@RequestParam("item")String item){
        shardingOperateAPI.disable(jobName,item);
    }

    /**
     * 启用作业分片
     * @param jobName
     * @param item
     */
    @PutMapping("/enable")
    public void updateJobConfiguration(@RequestParam("jobname")String jobName,@RequestParam("item")String item){
        shardingOperateAPI.enable(jobName,item);
    }

}
