package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.service.ISmsService;
import com.ezb.jdb.tool.JdbSMSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

/**
 * author : liufeng
 * create time: 2015/8/6 10:04.
 */
@Service
@Slf4j
public class SmsServiceImpl implements ISmsService {

    @Value("${sms_postdata}")
    private String postData;

    @Value("${sms_posturl}")
    private String postUrl;

    public String sendMes(String phone, String content) {
        String result = ResponseState.FAIL;
        try {
            result = JdbSMSUtil.smsPost(MessageFormat.format(postData, phone, content), postUrl);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }
}
