package com.gree.cron.controller;

import com.gree.cron.dao.ElasticJobTraceDao;
import com.gree.cron.entity.ElasticJobExecLog;
import org.nutz.lang.util.NutMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 180686 on 2021/8/20 14:27
 */

@RestController
@RequestMapping("/elastic/trace")
public class ElasticJobTraceController {

    @Autowired
    private ElasticJobTraceDao elasticJobTraceDao;

    @GetMapping
    public NutMap getJobConfiguration(ElasticJobExecLog elasticJobExecLog){
        return elasticJobTraceDao.getElasticJobConfig(elasticJobExecLog);
    }

}
