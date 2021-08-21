package com.gree.cron.dao;

import com.gree.cron.entity.ElasticJobExecLog;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.lang.util.NutMap;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by 180686 on 2021/8/21 10:46
 */
@Repository
public class ElasticJobTraceDao {

    @Resource(name = "traceDao")
    private Dao dao;

    public NutMap getElasticJobConfig(ElasticJobExecLog elasticJobExecLog){
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String pagenum = httpServletRequest.getParameter("pagenum");
        String pagesize = httpServletRequest.getParameter("pagesize");
        Pager pager = dao.createPager(Integer.valueOf(pagenum),Integer.valueOf(pagesize));
        Cnd cnd = Cnd.from(dao,elasticJobExecLog);
        return NutMap.NEW().addv("datelist",dao.query(ElasticJobExecLog.class,cnd,pager)).addv("page",pager);
    }

}
