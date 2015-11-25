package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.User;
import com.ezb.jdb.tool.JdbMd5Util;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月23日下午1:43:15
 * @Description: 初始化用户
 */
@Component
public class UserInit {
    @Resource
    private UserDao userDao;

    public void init() {
        User userSpecial = new User();
        userSpecial.setCreateTime(new Date());

        int passWordSpecial = 100000;
        passWordSpecial += 1;
        userSpecial.setPassword(JdbMd5Util.md5(String.valueOf(passWordSpecial)));
        userSpecial.setState(1);

        long usernameSpecial = 13327689963l;
        usernameSpecial += 1;
        userSpecial.setUsername(String.valueOf(usernameSpecial));

        //设置校友初值
        Alumnus alumusspecial = new Alumnus();
        alumusspecial.setCompany("company" + String.valueOf(1));
        alumusspecial.setDepartment("department" + String.valueOf(1));
        alumusspecial.setEmail("email" + String.valueOf(1) + "@qq.com");
        alumusspecial.setEnRealName("enrealname" + String.valueOf(1));
        alumusspecial.setGrade("grade" + String.valueOf(1));
        Random rdSpecial = new Random();
        int pathIdSpecial = rdSpecial.nextInt(3);

        String[] headPicPathSpecial = {"uploadfiles/20150811/20150811111719207/pic1.jpg",
                "uploadfiles/20150811/20150811111719207/pic2.jpg",
                "uploadfiles/20150811/20150811111719207/pic3.jpg"};

        alumusspecial.setHeadPicPath(headPicPathSpecial[pathIdSpecial]);
        double latSpecial = rdSpecial.nextDouble() * 180 - 90;
        DecimalFormat dfSpecial = new DecimalFormat("#.##");
        alumusspecial.setLat(Double.valueOf(dfSpecial.format(latSpecial)));

        double lngSpecial = rdSpecial.nextDouble() * 360 - 180;
        alumusspecial.setLng(Double.valueOf(dfSpecial.format(lngSpecial)));
        alumusspecial.setPhone(String.valueOf(usernameSpecial));
        alumusspecial.setRealName("realname" + String.valueOf(1));
        alumusspecial.setSchool("school" + String.valueOf(1));

        int sexSpecial = rdSpecial.nextInt(2);
        alumusspecial.setSex(sexSpecial);
        alumusspecial.setTitle("title" + String.valueOf(1));
        alumusspecial.setWeixin("weixin" + String.valueOf(1));
        alumusspecial.setUser(userSpecial);
        userSpecial.setAlumnus(alumusspecial);

        userDao.add(userSpecial);

        for (int i = 1; i < 101; i++) {
            //设置用户初值
            User user = new User();
            user.setCreateTime(new Date());

            int passWord = 100000;
            passWord += i;
            user.setPassword(JdbMd5Util.md5(String.valueOf(passWord)));
            user.setState(1);

            long username = 10000000000l;
            username += i;
            user.setUsername(String.valueOf(username));

            //设置校友初值
            Alumnus alumus = new Alumnus();
            alumus.setCompany("company" + String.valueOf(i));
            alumus.setDepartment("department" + String.valueOf(i));
            alumus.setEmail("email" + String.valueOf(i) + "@qq.com");
            alumus.setEnRealName("enrealname" + String.valueOf(i));
            alumus.setGrade("grade" + String.valueOf(i));

            Random rd = new Random();
            int pathId = rd.nextInt(3);
            String[] headPicPath = {"uploadfiles/20150811/20150811111719207/pic1.jpg",
                    "uploadfiles/20150811/20150811111719207/pic2.jpg",
                    "uploadfiles/20150811/20150811111719207/pic3.jpg"};

            alumus.setHeadPicPath(headPicPath[pathId]);

            double lat = rd.nextDouble() * 180 - 90;
            DecimalFormat df = new DecimalFormat("#.##");
            alumus.setLat(Double.valueOf(df.format(lat)));

            double lng = rd.nextDouble() * 360 - 180;
            alumus.setLng(Double.valueOf(df.format(lng)));
            alumus.setPhone(String.valueOf(username));
            alumus.setRealName("realname" + String.valueOf(i));
            alumus.setSchool("school" + String.valueOf(i));

            int sex = rd.nextInt(2);
            alumus.setSex(sex);
            alumus.setTitle("title" + String.valueOf(i));
            alumus.setWeixin("weixin" + String.valueOf(i));
            alumus.setUser(user);
            user.setAlumnus(alumus);

            userDao.add(user);
        }
    }
}
