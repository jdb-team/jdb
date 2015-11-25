package com.ezb.jdb.tool;

import com.ezb.jdb.common.ResponseState;
import org.apache.commons.lang.StringUtils;

/**
 * 地图坐标处理相关方法
 * author : liufeng
 * create time:2015/8/11 15:45
 */
public class JdbGisUtil {

    private static double EARTH_RADIUS = 6378.138;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离（单位为KM）
     * 参数为String类型
     *
     * @param lat1 经度1
     * @param lng1 纬度1
     * @param lat2 经度2
     * @param lng2 纬度2
     * @return
     */
    public static Double getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {

        Double[] values = new Double[]{lat1, lng1, lat2, lng2};
        for (Double value : values) {
            if (null == value) {
                return 0.0;
            }
        }
        double radLat1 = rad(values[0]);
        double radLat2 = rad(values[2]);
        double difference = radLat1 - radLat2;
        double mdifference = rad(values[1]) - rad(values[3]);
        double distance = Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * EARTH_RADIUS * 2;
        distance = distance * 1000 / 1000;

        return distance;
    }

    /**
     * 计算给定的坐标距离
     *
     * @param location1 坐标1 格式：经度,纬度
     * @param location2 坐标2 格式：经度,纬度
     * @return 两个坐标相差的距离
     */
    public static String getDistance(String location1, String location2) {
        if (StringUtils.isEmpty(location1) || StringUtils.isEmpty(location2)) {
            return ResponseState.EMPTY_STR;
        }
        String regex = "([1-9]\\d*(\\.\\d+)?),([1-9]\\d*(\\.\\d+)?)";
        if (!location1.matches(regex) || !location2.matches(regex)) {
            return ResponseState.LOCATION_FMT_ERR;
        }
        String[] locationXy1 = StringUtils.splitByWholeSeparator(location1, ",");
        String[] locationXy2 = StringUtils.splitByWholeSeparator(location2, ",");
        return String.valueOf(
                getDistance(
                        Double.valueOf(locationXy1[0]),
                        Double.valueOf(locationXy1[1]),
                        Double.valueOf(locationXy2[0]),
                        Double.valueOf(locationXy2[1])
                )
        );
    }
}
