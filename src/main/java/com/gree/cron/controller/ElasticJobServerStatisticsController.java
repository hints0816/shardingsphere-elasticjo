package com.gree.cron.controller;

import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.ServerStatisticsAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.domain.ServerBriefInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by 180686 on 2021/8/19 14:42
 * elasticJob 作业服务器状态展示 API
 */
@RestController
@RequestMapping("/elastic/serverstatistics")
public class ElasticJobServerStatisticsController {

    @Autowired
    private ServerStatisticsAPI serverStatisticsAPI;

    /**
     * 获取作业服务器总数
     * @return
     */
    @GetMapping(value = "/count")
    public int getJobConfiguration(){
        int serversTotalCount = serverStatisticsAPI.getServersTotalCount();
        return serversTotalCount;
    }

    /**
     * 获取所有作业服务器简明信息
     * @return
     */
    @GetMapping
    public Collection<ServerBriefInfo> updateJobConfiguration(){
        Collection<ServerBriefInfo> allServersBriefInfo = serverStatisticsAPI.getAllServersBriefInfo();
        return allServersBriefInfo;
    }
}
