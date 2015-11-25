package com.ezb.jdb.common;

/**
 * 焦点类型
 * author : liufeng
 * create time:2015/8/21 17:37
 */
public enum NavType {

    CIRCLE("圈子"),TOPIC("话题"), SPECOL("专栏"), ACTIVITY("活动"),OTHER("自定义"),USER("用户");

    private String name;

    NavType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
