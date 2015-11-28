package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.FocusDao;
import com.ezb.jdb.model.Focus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author: liuyan
 * @date: 2015年11月24日下午1:56:15
 * @Description: 焦点初始化
 */
@Component
public class FocusInit {

    @Resource
    private FocusDao focusDao;

    public void init() {
        Random rd = new Random();
        int id;
        String[] picPath = {"uploadfiles/20150811/20150811111719207/pic1.jpg",
                "uploadfiles/20150811/20150811111719207/pic2.jpg",
                "uploadfiles/20150811/20150811111719207/pic3.jpg"};

        Focus focus1 = new Focus();
        focus1.setInfo("");
        focus1.setPicpath(picPath[rd.nextInt(3)]);
        focus1.setPosition(1);
        focus1.setRefId(id = (rd.nextInt(100) + 1));
        focus1.setTitle("");
        focus1.setType("专栏");
        focus1.setViewurl("admin/topic/view?id=" + String.valueOf(id));

        focusDao.add(focus1);

        Focus focus2 = new Focus();
        focus2.setInfo("");
        focus2.setTitle("");
        focus2.setPicpath(picPath[rd.nextInt(3)]);
        focus2.setPosition(2);
        focus2.setRefId(id = (rd.nextInt(100) + 1));
        focus2.setType("专栏");
        focus2.setViewurl("admin/topic/view?id=" + String.valueOf(id));
        focusDao.add(focus2);

        Focus focus3 = new Focus();
        focus3.setInfo("");
        focus3.setTitle("");
        focus3.setPicpath(picPath[rd.nextInt(3)]);
        focus3.setPosition(3);
        focus3.setRefId(id = (rd.nextInt(100) + 1));
        focus3.setType("活动");
        focus3.setViewurl("admin/activity/view?id=" + String.valueOf(id));
        focusDao.add(focus3);

        Focus focus4 = new Focus();
        focus4.setInfo("");
        focus4.setTitle("");
        focus4.setPicpath(picPath[rd.nextInt(3)]);
        focus4.setPosition(4);
        focus4.setRefId(id = (rd.nextInt(100) + 1));
        focus4.setType("活动");
        focus4.setViewurl("admin/activity/view?id=" + String.valueOf(id));
        focusDao.add(focus4);

        Focus focus5 = new Focus();
        focus5.setInfo("");
        focus5.setTitle("");
        focus5.setPicpath(picPath[rd.nextInt(3)]);
        focus5.setPosition(5);
        focus5.setRefId(id = (rd.nextInt(100) + 1));
        focus5.setType("专栏");
        focus5.setViewurl("admin/topic/view?id=" + String.valueOf(id));
        focusDao.add(focus5);

        Focus focus6 = new Focus();
        focus6.setInfo("");
        focus6.setTitle("");
        focus6.setPicpath(picPath[rd.nextInt(3)]);
        focus6.setPosition(6);
        focus6.setRefId(id = (rd.nextInt(100) + 1));
        focus6.setType("专栏");
        focus6.setViewurl("admin/topic/view?id=" + String.valueOf(id));
        focusDao.add(focus6);

        Focus focus7 = new Focus();
        focus7.setInfo("09级通讯录");
        focus7.setTitle("09级通讯录");
        focus7.setPicpath(picPath[rd.nextInt(3)]);
        focus7.setPosition(7);
        focus7.setRefId(id = (rd.nextInt(100) + 1));
        focus7.setType("圈子");
        focus7.setViewurl("admin/circle/view?id=" + String.valueOf(id));
        focusDao.add(focus7);

        Focus focus8 = new Focus();
        focus8.setInfo("09级通讯录");
        focus8.setTitle("09级通讯录");
        focus8.setPicpath(picPath[rd.nextInt(3)]);
        focus8.setPosition(8);
        focus8.setRefId(id = (rd.nextInt(100) + 1));
        focus8.setType("圈子");
        focus8.setViewurl("admin/circle/view?id=" + String.valueOf(id));
        focusDao.add(focus8);

        Focus focus9 = new Focus();
        focus9.setInfo("09级通讯录");
        focus9.setTitle("09级通讯录");
        focus9.setPicpath(picPath[rd.nextInt(3)]);
        focus9.setPosition(8);
        focus9.setRefId(id = (rd.nextInt(100) + 1));
        focus9.setType("圈子");
        focus9.setViewurl("admin/circle/view?id=" + String.valueOf(id));
        focusDao.add(focus9);

        Focus focus10 = new Focus();
        focus10.setInfo("09级通讯录");
        focus10.setTitle("09级通讯录");
        focus10.setPicpath(picPath[rd.nextInt(3)]);
        focus10.setPosition(8);
        focus10.setRefId(id = (rd.nextInt(100) + 1));
        focus10.setType("圈子");
        focus10.setViewurl("admin/circle/view?id=" + String.valueOf(id));
        focusDao.add(focus10);
    }
}
