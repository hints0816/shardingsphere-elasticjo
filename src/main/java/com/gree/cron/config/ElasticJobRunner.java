package com.gree.cron.config;

import com.gree.cron.dao.ElasticJobConfigDao;
import com.gree.cron.entity.ElasticJobConfigBean;
import com.gree.cron.task.MyJob2;
import com.gree.cron.utils.SpringUtils;
import com.gree.cron.utils.StringUtils;
import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.apache.shardingsphere.elasticjob.dataflow.props.DataflowJobProperties;
import org.apache.shardingsphere.elasticjob.http.props.HttpJobProperties;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.script.props.ScriptJobProperties;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 180686 on 2021/8/18 16:44
 */
@Component
public class ElasticJobRunner implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(ElasticJobRunner.class);

    @Autowired
    private ElasticJobConfigDao elasticJobConfigDao;

    @Resource(name = "elasticRegistryCenter")
    private CoordinatorRegistryCenter zookeeperRegistryCenter;

    @Resource(name = "ElasticJobTracingConfiguration")
    private TracingConfiguration tracingConfiguration;

    @Override
    public void run(String... args) {
        List<ElasticJobConfigBean> elasticJobConfigList = elasticJobConfigDao.getElasticJobConfig();
        if (elasticJobConfigList == null || elasticJobConfigList.size() == 0) {
            return;
        }
        elasticJobConfigList.forEach(elasticJobConfig -> {
            registryJob(elasticJobConfig);
        });
    }

    private void registryJob(ElasticJobConfigBean elasticJobConfigBean) {
        try {
            if (StringUtils.isNotEmpty(elasticJobConfigBean.getJobClass())) {
                Class<? extends ElasticJob> jobClass = (Class<? extends ElasticJob>) Class
                        .forName(elasticJobConfigBean.getJobClass());
                ElasticJob elasticJob = null;
                boolean b = SpringUtils.containsBean(jobClass.getSimpleName());
                if (b) {
                    elasticJob = (ElasticJob) SpringUtils.getBean(jobClass.getSimpleName());
                } else {
                    elasticJob = jobClass.newInstance();
                }
                if (elasticJob instanceof SimpleJob) {
                    new ScheduleJobBootstrap(zookeeperRegistryCenter, elasticJob, createJobConfiguration(elasticJobConfigBean)).schedule();
                }
                if (elasticJob instanceof DataflowJob) {
                    JobConfiguration jobConfiguration = createJobConfiguration(elasticJobConfigBean);
                    jobConfiguration.getProps().setProperty(DataflowJobProperties.STREAM_PROCESS_KEY, Boolean.TRUE.toString());
                    new ScheduleJobBootstrap(zookeeperRegistryCenter, new MyJob2(), jobConfiguration).schedule();
                }
            } else if (elasticJobConfigBean.getType() == 1) {
                JobConfiguration jobConfiguration = createJobConfiguration(elasticJobConfigBean);
                jobConfiguration.getProps().setProperty(HttpJobProperties.URI_KEY, elasticJobConfigBean.getUrl());
                jobConfiguration.getProps().setProperty(HttpJobProperties.METHOD_KEY, elasticJobConfigBean.getMethod());
                jobConfiguration.getProps().setProperty(HttpJobProperties.DATA_KEY, elasticJobConfigBean.getData());
                new ScheduleJobBootstrap(zookeeperRegistryCenter, "HTTP",jobConfiguration).schedule();
            } else if (elasticJobConfigBean.getType() == 2) {
                JobConfiguration jobConfiguration = createJobConfiguration(elasticJobConfigBean);
                jobConfiguration.getProps().setProperty(ScriptJobProperties.SCRIPT_KEY, "echo hello");
                new ScheduleJobBootstrap(zookeeperRegistryCenter, "SCRIPT", jobConfiguration).schedule();
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    private JobConfiguration createJobConfiguration(ElasticJobConfigBean elasticJobConfigBean) {
        JobConfiguration jobConfig = JobConfiguration.newBuilder(elasticJobConfigBean.getJobName(), elasticJobConfigBean.getShardingTotalCount())
                .cron(elasticJobConfigBean.getCron())
                .monitorExecution(true)
                .description(elasticJobConfigBean.getDescription())
                .misfire(elasticJobConfigBean.getMisfire())
                .failover(elasticJobConfigBean.getFailover())
                .jobParameter(elasticJobConfigBean.getJobParameter())
                .overwrite(true)
                .shardingItemParameters(elasticJobConfigBean.getShardingItemParameters())
                .build();
        jobConfig.getExtraConfigurations().add(tracingConfiguration);

        return jobConfig;
    }

}
