package com.ezb.jdb.tool;

import java.util.Random;

public class ShareCodeUtil {

    /**
     * 生成邀请码
     * @return
     */
    public static String shareCode(){
        String randomStr = "123456789abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder invitateCode = new StringBuilder("");
        for (int i = 0; i < 6; i++) {
            invitateCode.append(randomStr.charAt(random.nextInt(randomStr.length())));
        }
        return invitateCode.toString().toUpperCase();
    }
}
