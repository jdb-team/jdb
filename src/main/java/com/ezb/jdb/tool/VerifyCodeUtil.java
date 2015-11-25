package com.ezb.jdb.tool;

/**
 * 短信验证码生成器
 * author : liufeng
 * create time: 2015/8/6 10:23.
 */
public class VerifyCodeUtil {

    public static String makeVerifyCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }
}
