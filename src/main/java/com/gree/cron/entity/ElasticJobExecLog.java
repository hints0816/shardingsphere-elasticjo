package com.gree.cron.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Table;

import java.time.LocalDateTime;

/**
 * Created by 180686 on 2021/8/20 19:09
 */

@Table("JOB_EXECUTION_LOG")
@Data
public class ElasticJobExecLog {

    private String id;
    private String job_name;
    private String task_id;
    private String hostname;
    private String ip;
    private Integer sharding_item;
    private String execution_source;
    private String failure_cause;
    private Integer is_success;
    private LocalDateTime start_time;
    private LocalDateTime complete_time;

}
