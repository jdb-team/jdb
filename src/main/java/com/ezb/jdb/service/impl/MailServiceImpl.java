package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 邮件发送服务
 * author  :  liufeng
 * create  time:2015/8/17  11:30
 */
@Service
@Slf4j
public class MailServiceImpl implements IMailService {

    @Value("${mail_host}")
    private String host;

    @Value("${mail_from}")
    private String from;

    @Value("${mail_username}")
    private String username;

    @Value("${mail_password}")
    private String password;

    public String sendMail(String toEmail, String subject, String content) {
        Email email = new SimpleEmail();
        email.setHostName(host);
        email.setAuthentication(username, password);
        email.setCharset("UTF-8");

        try {
            email.setFrom(from);
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setMsg(content);
            email.send();
        } catch (EmailException e) {
            log.error(e.getMessage());
            return ResponseState.MAIL_ERR;
        }
        return ResponseState.SUCCESS;
    }

    public String sendHtmlMail(String toEmail, String subject, String htmlContent) {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(host);
        email.setAuthentication(username, password);
        email.setCharset("UTF-8");

        try {
            email.setFrom(from);
            email.addTo(toEmail);
            email.setSubject(subject);
            email.setHtmlMsg(htmlContent);
            email.send();
        } catch (EmailException e) {
            log.error(e.getMessage());
            return ResponseState.MAIL_ERR;
        }
        return ResponseState.SUCCESS;
    }
}
