package com.ezb.jdb.schedule;

import com.ezb.jdb.service.IInvitateCodeService;
import com.ezb.jdb.service.IVerifyCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * 邀请码、验证码任务
 * author : liufeng
 * create time:2015/9/15 13:56
 */
@Component
@Slf4j
public class CodeSchedule {

    @Resource
    private IInvitateCodeService invitateCodeServiceImpl;

    @Resource
    private IVerifyCodeService verifyCodeServiceImpl;

    /**
     * 过期邀请码、验证码定期清理任务
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void clearExpireCode() {
        int affactNum0 = invitateCodeServiceImpl.clearExpire();
        int affactNum1 = verifyCodeServiceImpl.clearExpire();
        log.info("clearExpireCode " + affactNum0 + " invitateCode," + affactNum1 + " verifyCode success");
    }
}
