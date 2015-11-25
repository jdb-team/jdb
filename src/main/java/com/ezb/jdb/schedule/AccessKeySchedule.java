package com.ezb.jdb.schedule;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.service.IAccessKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务
 * author : liufeng
 * create time:2015/9/15 13:56
 */
@Component
@Slf4j
public class AccessKeySchedule {

    @Resource
    private IAccessKeyService accessKeyServiceImpl;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void expireToken() {
        int size = accessKeyServiceImpl.expireDay(JdbConstants.ACCKEY_TOKEN,1);
        log.info("expireToken " + size + " accessKey success");
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void expireInvatate() {
        int size = accessKeyServiceImpl.expireMinute(JdbConstants.ACCKEY_INVITATE, 30);
        log.info("expireInvatate " + size + " accessKey success");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void expireActivity() {
        int size = accessKeyServiceImpl.expireHour(JdbConstants.ACCKEY_ACTIVITY, 1);
        log.info("expireActivity " + size + " accessKey success");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void expireAtvcmt() {
        int size = accessKeyServiceImpl.expireHour(JdbConstants.ACCKEY_ATVCMT, 1);
        log.info("expireAtvcmt " + size + " accessKey success");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void expireTopiccmt() {
        int size = accessKeyServiceImpl.expireHour(JdbConstants.ACCKEY_TOPICCMT, 1);
        log.info("expireTopiccmt " + size + " accessKey success");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void expireTopic() {
        int size = accessKeyServiceImpl.expireHour(JdbConstants.ACCKEY_TOPIC, 1);
        log.info("expireTopic " + size + " accessKey success");
    }
}
