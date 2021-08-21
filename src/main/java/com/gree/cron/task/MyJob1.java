package com.gree.cron.task;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 180686 on 2021/8/3 14:41
 */
public class MyJob1 implements SimpleJob {

    private final FooRepository fooRepository = FooRepositoryFactory.getFooRepository();
    private static Logger logger = LoggerFactory.getLogger(MyJob1.class);
    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info(shardingContext.getJobName() + "|" + shardingContext.getShardingItem() + "|" + shardingContext.getShardingParameter());
        System.out.printf("Item: %s | Time: %s | Thread: %s | %s%n",
                shardingContext.getShardingItem(), new SimpleDateFormat("HH:mm:ss").format(new Date()), Thread.currentThread().getId(), "SIMPLE");
        List<Foo> data = fooRepository.findTodoData(shardingContext.getShardingParameter(), 10);
        for (Foo each : data) {
            fooRepository.setCompleted(each.getId());
        }
    }
}