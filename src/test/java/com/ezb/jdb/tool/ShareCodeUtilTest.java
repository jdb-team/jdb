package com.ezb.jdb.tool;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * author : liufeng
 * create time: 2015/8/6 9:26.
 */
@Slf4j
public class ShareCodeUtilTest extends TestCase {

    @Test
    public void testShareCode(){
        log.info(ShareCodeUtil.shareCode());
    }
}
