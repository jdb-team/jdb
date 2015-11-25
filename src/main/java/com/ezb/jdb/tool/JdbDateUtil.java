package com.ezb.jdb.tool;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Date;

/**
 * 日期处理辅助类
 * author : liufeng
 * create time: 2015/8/7 14:06.
 */
public class JdbDateUtil {

    /**
     * 获取时间间隔提示串
     *
     * @param date
     * @return
     */
    public static String interTime(Date date) {
        String result = "";

        DateTime dateTime = new DateTime(date);
        DateTime nowTime = DateTime.now();
        Period period = new Period(dateTime, nowTime, PeriodType.seconds());

        int totalSecond = period.getSeconds();
        int second = totalSecond % 60;
        int minute = totalSecond / 60 % 60;
        int hour = totalSecond / 3600 % 24;
        int day = totalSecond / (3600 * 24);
        int year = day / 365;

        if (year == 0) {
            if (day == 0) {
                if (hour == 0) {
                    if (minute == 0) {
                        result = Math.abs(second) + "秒前";
                    } else {
                        result = Math.abs(minute) + "分钟前";
                    }
                } else {
                    result = Math.abs(hour) + "小时前";
                }
            } else {
                if(1 == day){
                    result = "昨日";
                }else{
                    result = Math.abs(day) + "天前";
                }
            }
        } else {
            result = Math.abs(year) + "年前";
        }

        if(totalSecond < 0){
            result = result.replace("前","后");
        }
        return result;
    }
}
