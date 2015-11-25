package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.service.IPicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * 图片展示
 * author : liufeng
 * create time:2015/8/18 14:09
 */
@Controller
@Slf4j
public class ImageController {

    @Resource
    private IPicService picServiceImpl;

    /**
     * 图片响应
     *
     * @param request
     * @param response
     * @param picpath    图片路径
     * @param bigorsmall big 大图 small 小图
     */
    @RequestMapping(value = "mobile/image/showimage")
    public void showImage(HttpServletRequest request,
                          HttpServletResponse response,
                          String picpath, String bigorsmall) {

        FileInputStream fis = null;
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        String bigOrSmallAppend = JdbConstants.DEFAULT_NONEPIC_SMALL;
        if (!StringUtils.isEmpty(bigorsmall)) {
            bigOrSmallAppend = JdbConstants.DEFAULT_NONEPIC_BIG;
        }
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(contextPath + File.separator + picpath);
            if (!file.exists() || file.isDirectory()) {
                file = new File(contextPath + File.separator + bigOrSmallAppend);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    /**
     * 图片上传
     *
     * @param request
     */
    @RequestMapping(value = "mobile/image/upload")
    public
    @ResponseBody
    String uploadImage(HttpServletRequest request) {
        return picServiceImpl.uploadImage(request);
    }

}
