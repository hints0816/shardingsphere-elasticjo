package com.gree.cron.dao;

import com.gree.cron.entity.ElasticJobConfigBean;
import org.nutz.dao.Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 180686 on 2021/8/19 9:47
 */

@Repository
public class ElasticJobConfigDao {

    @Autowired
    private Dao dao;

    public List<ElasticJobConfigBean> getElasticJobConfig(){
        return dao.query(ElasticJobConfigBean.class,null);
    }

}
