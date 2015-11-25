package com.ezb.jdb.tool;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 页面访问统计工具类
 * author : liufeng
 * create time:2015/9/15 13:39
 */
public class JdbPvUtil {

    private static BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(), 100000);

    /**
     * 判断bloomfiter是否包含制定mid
     *
     * @param mid    mid
     * @return
     */
    public static boolean isVisited(String mid) {
        boolean isVisited = false;
        if (bloomFilter.mightContain(mid)) {
            isVisited = true;
        }
        bloomFilter.put(mid);
        return isVisited;
    }

    /**
     * 重置BloomFilter
     */
    public static void resetBloomFilter() {
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(), 100000);
    }
}
