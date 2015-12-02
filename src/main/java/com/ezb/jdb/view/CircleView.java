package com.ezb.jdb.view;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.User;

/**
 * 圈子视图包装
 * author : liufeng
 * create time:2015/9/11 11:20
 */
public class CircleView {

    public static String convert2Json(PageResult<Circle> pageResult, User user) {
        for (Circle circle : pageResult.getResultList()) {
            if (null == circle.getMembers()
                    || null != circle.getMembers()
                    && !circle.getMembers().contains(user)) {
                circle.setJoin(0);
            } else {
                circle.setJoin(1);
            }
        }
        return ResponseData.getResData(pageResult);
    }
}
