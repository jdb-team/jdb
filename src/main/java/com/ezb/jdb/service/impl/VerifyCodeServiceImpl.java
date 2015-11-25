package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.VerifyCodeDao;
import com.ezb.jdb.model.SmsRes;
import com.ezb.jdb.model.VerifyCode;
import com.ezb.jdb.service.ISmsService;
import com.ezb.jdb.service.IVerifyCodeService;
import com.ezb.jdb.tool.JdbSMSUtil;
import com.ezb.jdb.tool.VerifyCodeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * author : liufeng
 * create time: 2015/8/6 10:38.
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Resource
    private VerifyCodeDao verifyCodeDao;

    @Resource
    private ISmsService smsServiceImpl;

    public void save(VerifyCode verifyCode) {
        verifyCodeDao.add(verifyCode);
    }

    public String generateCode(String phone) {

        String verifyCodeStr = VerifyCodeUtil.makeVerifyCode();
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setPhone(phone);

        verifyCode.setVerifyCode(verifyCodeStr);
        verifyCode.setCreateTime(new Date());
        save(verifyCode);

        SmsRes smsRes = JdbSMSUtil.parse(
                smsServiceImpl.sendMes(
                        verifyCode.getPhone(),
                        verifyCode.getVerifyCode()
                )
        );

        return ResponseState.getResState(smsRes.getState().toString(),
                smsRes.getMsgState());
    }

    public int clearExpire() {
        return verifyCodeDao.clearExpire();
    }
}
