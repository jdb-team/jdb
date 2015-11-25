package com.ezb.jdb.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流处理工具类
 * author : liufeng
 * create time:2015/8/12 17:09
 */
@Slf4j
public class JdbStreamUtil {

    public static String stream2Str(InputStream inputStream) {

        try {
            byte[] bytes = new byte[1024 * 1024];

            int nRead = 1;
            int nTotalRead = 0;
            while (nRead > 0) {
                nRead = inputStream.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0)
                    nTotalRead = nTotalRead + nRead;
            }
            return new String(bytes, 0, nTotalRead, "utf-8");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }
}
