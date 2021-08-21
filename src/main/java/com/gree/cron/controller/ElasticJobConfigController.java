package com.gree.cron.controller;

import org.apache.shardingsphere.elasticjob.api.JobExtraConfiguration;
import org.apache.shardingsphere.elasticjob.infra.pojo.JobConfigurationPOJO;
import org.apache.shardingsphere.elasticjob.infra.yaml.config.YamlConfiguration;
import org.apache.shardingsphere.elasticjob.lite.lifecycle.api.JobConfigurationAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by 180686 on 2021/8/19 14:42
 * elasticJob 配置类 API
 */
@RestController
@RequestMapping("/elastic/config")
public class ElasticJobConfigController {

    @Autowired
    private JobConfigurationAPI jobConfigurationAPI;

    /**
     * 获取作业配置
     * @param jobName
     * @return
     */
    @GetMapping(value = "/{jobName}")
    public JobConfigurationPOJO getJobConfiguration(@PathVariable String jobName){
        JobConfigurationPOJO jobConfiguration = jobConfigurationAPI.getJobConfiguration(jobName);
        return jobConfiguration;
    }

    /**
     * 更新作业配置
     * @param jobConfiguration
     */
    @PutMapping
    public void updateJobConfiguration(@RequestBody JobConfigurationPOJO jobConfiguration){
        JobConfigurationPOJO jobConfigurationPOJO = jobConfigurationAPI.getJobConfiguration(jobConfiguration.getJobName());
        Collection<YamlConfiguration<JobExtraConfiguration>> jobExtraConfigurations = jobConfigurationPOJO.getJobExtraConfigurations();
        jobConfiguration.setJobExtraConfigurations(jobExtraConfigurations);
        jobConfigurationAPI.updateJobConfiguration(jobConfiguration);
    }

    /**
     * 删除作业设置
     * @param jobNames
     */
    @DeleteMapping("/{jobNames}")
    public void removeJobConfiguration(@PathVariable String[] jobNames){
        Arrays.asList(jobNames).forEach(jobName -> {
            jobConfigurationAPI.removeJobConfiguration(jobName);
        });
    }
}
