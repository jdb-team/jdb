package com.ezb.jdb.tool;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Slf4j
public class JdbFileUtil {

    /**
     * 上传文件
     *
     * @param file
     * @param prePath 文件存储路径前缀
     * @return
     */
    public static String uploadFile(MultipartFile file, String prePath) {

        String originName = file.getOriginalFilename();

        DateTime dateTime = DateTime.now();
        String dayStr = dateTime.toString(JdbConstants.DAY_FMT);
        String millSecStr = dateTime.toString(JdbConstants.MILL_SEC_FMT);

        String rpath = dayStr + "/" + millSecStr + "/" + originName;
        File uploadFile = new File(prePath + rpath);

        try {
            FileUtils.forceMkdir(uploadFile.getParentFile());
            file.transferTo(uploadFile);
        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseState.PIC_ERR;
        }
        return rpath;
    }

    /**
     * 上传request请求中的文件
     *
     * @param request
     * @return 保存的路径，以逗号分隔
     */
    public static String uploadFile(HttpServletRequest request, String uploadWarPath) {

        StringBuilder resultPathBuffer = new StringBuilder("");

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {

            String prePath = request.getSession().getServletContext().getRealPath("/")
                    + File.separator + uploadWarPath;

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());

                if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
                    String rpath = uploadFile(file, prePath);
                    if (StringUtils.equals(rpath, ResponseState.PIC_ERR)) {
                        return ResponseState.PIC_ERR;
                    }
                    resultPathBuffer.append(uploadWarPath + rpath).append(",");
                }
            }
        }

        String result = resultPathBuffer.toString();
        if (result.length() > 1) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }
}
