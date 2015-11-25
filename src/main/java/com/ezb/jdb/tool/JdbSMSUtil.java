package com.ezb.jdb.tool;

import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.SmsRes;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 短信发送
 * author : liufeng
 * create time:2015/8/11 15:45
 */
@Slf4j
public class JdbSMSUtil {

    public static String smsPost(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.error("connect failed!");
                return ResponseState.SMS_CONN_ERR;
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return URLDecoder.decode(result, "utf-8");
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseState.SMS_IO_ERR;
        }
    }

    public static SmsRes parse(String response) {
        SmsRes smsRes = new SmsRes();
        try {
            Document document = DocumentHelper.parseText(response);
            Element root = document.getRootElement();// 指向根节点

            Element state = root.element("State");
            smsRes.setState(Integer.parseInt(state.getTextTrim()));
            Element msgState = root.element("MsgState");
            smsRes.setMsgState(msgState.getTextTrim());
        } catch (DocumentException e) {
            log.error(e.getMessage());
        }
        return smsRes;
    }
}
