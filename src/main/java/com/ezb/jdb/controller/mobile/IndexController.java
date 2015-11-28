package com.ezb.jdb.controller.mobile;

import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.Focus;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.ICircleService;
import com.ezb.jdb.service.IFocusService;
import com.ezb.jdb.service.IUserService;
import com.ezb.jdb.view.FriendView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页
 * author : liufeng
 * create time:2015/8/20 10:12
 */
@Controller
public class IndexController {

    @Resource
    private IFocusService focusServiceImpl;

    @Resource
    private IUserService userServiceImpl;

    @Resource
    private ICircleService circleServiceImpl;

    /**
     * 首页数据组装
     *
     * @param phone      当前登录用户的手机号
     * @param focusSize  焦点条数
     * @param circleSize 圈子条数
     * @param userSize   附近用户个数
     * @return
     */
    @RequestMapping(value = "mobile/discover/recommend")
    public
    @ResponseBody
    String indexData(String phone, Integer focusSize, Integer circleSize, Integer userSize) {

        User user = userServiceImpl.queryUserByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }

        if (null == focusSize) {
            focusSize = 6;
        }

        if (null == circleSize) {
            circleSize = 4;
        }

        if (null == userSize) {
            userSize = 5;
        }

        //焦点图(活动话题 6条)
        PageResult<Focus> focusPageResult = new PageResult<Focus>();
        focusPageResult.setCurPage(1);
        focusPageResult.setPageSize(focusSize);
        List<Focus> focusList = focusServiceImpl.getTopFocus(focusPageResult).getResultList();

        //圈子推荐(条)
        focusPageResult.setPageSize(circleSize);
        List<Focus> circleList = focusServiceImpl.getCircleFocus(focusPageResult).getResultList();
        for(Focus focus : circleList){
            focus.setCount(circleServiceImpl.qCountCircleByid(focus.getRefId()));
        }

        //附近校友
        PageResult<User> userPageResult = new PageResult<User>();
        userPageResult.setCurPage(1);
        userPageResult.setPageSize(userSize);
        List<User> userList = userServiceImpl.queryNearUsers(userPageResult, phone).getResultList();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("focusList", focusList);
        jsonObject.put("circleList", circleList);
        jsonObject.put("userList", FriendView.getUserJsonArray(user, userList));

        return ResponseData.getResData(jsonObject);
    }
}
