package com.ezb.jdb.schedule;

import com.ezb.jdb.tool.JdbPvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * pv定时任务
 * author : liufeng
 * create time:2015/9/15 13:56
 */
@Component
@Slf4j
public class PvSchedule {

    @Scheduled(cron="0 0 0/1 * * ?")
    public void resetBloomFilter() {
        JdbPvUtil.resetBloomFilter();
        log.info("reset pvtool success");
    }
}
