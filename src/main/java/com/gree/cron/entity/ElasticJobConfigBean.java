package com.gree.cron.entity;

import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 180686 on 2021/8/19 9:41
 */

@Table("SYS_CRON_CONFIG")
public class ElasticJobConfigBean {
    private Long id;

    private String jobName;
    private String cron;
    private Integer shardingTotalCount;
    private String shardingItemParameters;
    private String jobParameter;
    private Integer failover;
    private Integer misfire;
    private String description;
    private String jobClass;
    private String streamingProcess;
    private String jobConfig;

    private Integer type;
    private String url;
    private String method;
    private String data;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getShardingTotalCount() {
        return shardingTotalCount;
    }

    public void setShardingTotalCount(Integer shardingTotalCount) {
        this.shardingTotalCount = shardingTotalCount;
    }

    public String getShardingItemParameters() {
        return shardingItemParameters;
    }

    public void setShardingItemParameters(String shardingItemParameters) {
        this.shardingItemParameters = shardingItemParameters;
    }

    public String getJobParameter() {
        return jobParameter;
    }

    public void setJobParameter(String jobParameter) {
        this.jobParameter = jobParameter;
    }

    public boolean getFailover() {
        return failover==0?false:true;
    }

    public void setFailover(boolean failover) {
        this.failover = failover?1:0;
    }

    public boolean getMisfire() {
        return misfire==0?false:true;
    }

    public void setMisfire(boolean misfire) {
        this.misfire = misfire?1:0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getStreamingProcess() {
        return streamingProcess;
    }

    public void setStreamingProcess(String streamingProcess) {
        this.streamingProcess = streamingProcess;
    }

    public String getJobConfig() {
        return jobConfig;
    }

    public void setJobConfig(String jobConfig) {
        this.jobConfig = jobConfig;
    }

    @Override
    public String toString() {
        return "ElasticJobConfigBean [id=" + id + ", jobName=" + jobName + ", cron=" + cron + ", shardingTotalCount="
                + shardingTotalCount + ", shardingItemParameters=" + shardingItemParameters + ", jobParameter="
                + jobParameter + ", failover=" + failover + ", misfire=" + misfire + ", description=" + description
                + ", jobClass=" + jobClass + ", streamingProcess=" + streamingProcess + ", jobConfig=" + jobConfig
                + "]";
    }

}