package com.ezb.jdb.tool;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.ResponseState;
import com.mortennobel.imagescaling.ResampleOp;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * 图片处理工具
 * author : liufeng
 * create time:2015/9/12 11:52
 */
@Slf4j
public class JdbPicUtil {

    /**
     * 图片按比例缩放
     *
     * @param bufferedImage
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage resize(BufferedImage bufferedImage, double width, double height) {

        BufferedImage rBufferedImage = null;

        int originWidth = bufferedImage.getWidth();
        int originHeight = bufferedImage.getHeight();

        double whRate = width * 1.0 / height;
        double originWhRate = originWidth * 1.0 / originHeight;

        int targetWidth;
        int targetHeight;

        //以height为标准
        if (originWhRate > whRate) {
            targetWidth = (int) (height * originWhRate);
            targetHeight = (int) height;
        } else {//以width为标准缩放
            targetWidth = (int) width;
            targetHeight = (int) (width / originWhRate);
        }

        //缩放
        ResampleOp resampleOp = new ResampleOp(targetWidth, targetHeight);
        rBufferedImage = resampleOp.filter(bufferedImage, null);

        //补白
        BufferedImage image = new BufferedImage(targetWidth, targetHeight,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.fillRect(0, 0, targetWidth, targetHeight);
        g.drawImage(rBufferedImage, 0, 0,
                rBufferedImage.getWidth(null),
                rBufferedImage.getHeight(null),
                Color.white, null);
        g.dispose();

        return image;
    }

    /**
     * 图片切割
     *
     * @param inputStream 图片文件
     * @param srcWidth    原图片宽度
     * @param srcHeight   原图片高度
     * @param width       宽
     * @param height      高
     */
    public final static BufferedImage cut(InputStream inputStream, int srcWidth, int srcHeight, int width, int height) {
        try {
            ImageInputStream iis = ImageIO.createImageInputStream(inputStream);
            Iterator<ImageReader> iterator = ImageIO.getImageReaders(iis);
            ImageReader reader = iterator.next();
            reader.setInput(iis, true);

            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rectangle = new Rectangle((srcWidth - width) / 2, (srcHeight - height) / 2, width, height);
            param.setSourceRegion(rectangle);
            return reader.read(0, param);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 图片压缩与剪切
     *
     * @param inputPath
     * @param width
     * @param height
     * @return
     */
    public static String resizecut(String inputPath, String uploadWarPath, double width, double height) {

        String preFileName = inputPath.substring(0, inputPath.lastIndexOf("."));
        String outputPath = preFileName + ".jpg";

        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(inputPath));
            bufferedImage = resize(bufferedImage, width, height);
            ImageIO.write(bufferedImage, JdbConstants.IMG_FMT_JPG,
                    new FileOutputStream(outputPath));

            bufferedImage = cut(
                    new FileInputStream(outputPath),
                    bufferedImage.getWidth(),
                    bufferedImage.getHeight(),
                    (int) width, (int) height);

            File outputFile = new File(outputPath);
            ImageIO.write(bufferedImage, JdbConstants.IMG_FMT_JPG, outputFile);

        } catch (IOException e) {
            log.error(e.getMessage());
            return ResponseState.PIC_ERR;
        }
        int index = outputPath.indexOf(uploadWarPath);
        return outputPath.substring(index, outputPath.length());
    }
}
