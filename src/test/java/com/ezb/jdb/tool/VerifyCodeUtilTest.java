package com.ezb.jdb.tool;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 * author : liufeng
 * create time: 2015/8/6 10:26.
 */
@Slf4j
public class VerifyCodeUtilTest extends TestCase {

    public void testVerifyCode() {
        for (int i = 0; i < 100; i++) {
            log.info(VerifyCodeUtil.makeVerifyCode());
        }
    }
}
