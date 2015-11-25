package com.ezb.jdb.service;

/**
 * 邮件服务
 * author : liufeng
 * create time:2015/8/17 11:29
 */
public interface IMailService {
    String sendMail(String toEmail, String subject, String content);
    String sendHtmlMail(String toEmail, String subject, String content);
}
