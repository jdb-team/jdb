package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.service.IPicService;
import com.ezb.jdb.tool.JdbFileUtil;
import com.ezb.jdb.tool.JdbPicUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * author : liufeng
 * create time:2015/9/14 10:10
 */
@Service
public class PicServiceImpl implements IPicService {

    @Value("${uploadWarPath}")
    private String uploadWarPath;

    public String uploadResizeCut(HttpServletRequest request, double width, double heigth) {
        StringBuilder urcPath = new StringBuilder("");
        String rpath = JdbFileUtil.uploadFile(request, uploadWarPath);
        if (!StringUtils.isEmpty(rpath)) {

            String[] rpathArr = StringUtils.splitByWholeSeparator(rpath, ",");
            for (String rPathArrElement : rpathArr) {
                String inputPath = request.getSession().getServletContext().getRealPath("/")
                        + File.separator + rPathArrElement;
                String cpath = JdbPicUtil.resizecut(inputPath, uploadWarPath, width, heigth);
                if (!StringUtils.equals(rpath, cpath)) {
                    FileUtils.deleteQuietly(new File(inputPath));
                }
                urcPath.append(cpath + ",");
            }
        }

        String result = urcPath.toString();
        if (result.length() > 1) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public String uploadImage(HttpServletRequest request) {

        return ResponseData.getResData(
                uploadResizeCut(
                        request,
                        JdbConstants.PIC_WIDTH,
                        JdbConstants.PIC_HEIGHT)
        );
    }
}
