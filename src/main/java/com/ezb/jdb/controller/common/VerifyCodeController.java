package com.ezb.jdb.controller.common;

import com.ezb.jdb.common.JdbConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码
 * author : liufeng
 * create time:2015/8/21 10:33
 */
@Controller
public class VerifyCodeController {

    private int width = 100;
    private int height = 20;
    private int codeCount = 4;
    private int x = 0;
    private int fontHeight;
    private int codeY;
    char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 初始化验证图片属性
     */
    public void initxuan() throws ServletException {

        String strWidth = "80";
        String strHeight = "30";
        String strCodeCount = "4";
        try {
            if (strWidth != null && strWidth.length() != 0) {
                width = Integer.parseInt(strWidth);
            }
            if (strHeight != null && strHeight.length() != 0) {
                height = Integer.parseInt(strHeight);
            }
            if (strCodeCount != null && strCodeCount.length() != 0) {
                codeCount = Integer.parseInt(strCodeCount);
            }
        } catch (NumberFormatException e) {
        }
        x = width / (codeCount + 1);
        fontHeight = height - 4;
        codeY = height - 4;
    }

    @RequestMapping(value = "login/verifycode", method = RequestMethod.GET)
    public void verifycode(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {

        initxuan();
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width + 1, height - 1);
        g.setColor(Color.BLACK);

        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width - 10);
            int y = random.nextInt(height - 10);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            randomCode.append(strRand);
        }

        HttpSession session = req.getSession();
        session.setAttribute(JdbConstants.VERIFYCODE_KEY, randomCode.toString());

        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");

        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    /**
     * 验证码校验
     *
     * @return
     */
    @RequestMapping(value = "login/checkverifycode")
    public
    @ResponseBody
    String checkVerifycode(HttpServletRequest request,
                           @RequestParam(required = true) String verifycode) {
        Object vobj = request.getSession().getAttribute(JdbConstants.VERIFYCODE_KEY);
        String verifyCodeServer = "";
        if (null != vobj) {
            verifyCodeServer = (String) vobj;
            if (StringUtils.equalsIgnoreCase(verifycode, verifyCodeServer)) {
                return "true";
            }
        }
        return "false";
    }
}

