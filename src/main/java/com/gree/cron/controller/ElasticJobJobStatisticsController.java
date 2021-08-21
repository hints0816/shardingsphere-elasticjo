package com.gree.cron.controller;

import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobStatisticsAPI;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.domain.JobBriefInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by 180686 on 2021/8/19 14:42
 * elasticJob 作业统计 API
 */

@RestController
@RequestMapping("/elastic/jobstatistics")
public class ElasticJobJobStatisticsController {

    @Autowired
    private JobStatisticsAPI jobStatisticsAPI;

    /**
     * 获取作业总数
     * @return
     */
    @GetMapping("/count")
    public int getJobsTotalCount(){
        int jobsTotalCount = jobStatisticsAPI.getJobsTotalCount();
        return jobsTotalCount;
    }

    /**
     * 获取作业简明信息
     * @param jobName
     * @return
     */
    @GetMapping(value = "jobname/{jobName}")
    public JobBriefInfo getJobBriefInfo(@PathVariable String jobName){
        JobBriefInfo jobBriefInfo = jobStatisticsAPI.getJobBriefInfo(jobName);
        return jobBriefInfo;
    }

    /**
     * 获取所有作业简明信息
     * @return
     */
    @GetMapping("/all")
    public Collection<JobBriefInfo> getAllJobsBriefInfo(){
        Collection<JobBriefInfo> allJobsBriefInfo = jobStatisticsAPI.getAllJobsBriefInfo();
        return allJobsBriefInfo;

    }

    /**
     * 获取该 IP 下所有作业简明信息
     * @param ip
     * @return
     */
    @GetMapping(value = "ip/{ip}")
    public Collection<JobBriefInfo> removeJobConfiguration(@PathVariable String ip){
        Collection<JobBriefInfo> jobsBriefInfos = jobStatisticsAPI.getJobsBriefInfo(ip);
        return jobsBriefInfos;
    }
}
