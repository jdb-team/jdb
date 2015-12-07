package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.*;
import com.ezb.jdb.service.IFriendService;
import com.ezb.jdb.service.IUserService;
import com.ezb.jdb.view.FriendView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 通讯录
 * author : liufeng
 * create time:2015/8/12 10:47
 */
@Controller
public class FriendController {

    @Resource
    private IFriendService friendServiceImpl;

    @Resource
    private IUserService userServiceImpl;

    /**
     * 好友分页列表
     *
     * @param pageResult
     * @param phone 当前用户的手机号
     * @param queryWords 查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/friend/queryfriend")
    public
    @ResponseBody
    String queryFriend(PageResult<Friend> pageResult, String phone, String queryWords) {
        PageResult<Friend> pageResult1 = friendServiceImpl.queryFriend(pageResult, phone, queryWords);
        return ResponseData.getResData(pageResult1);
    }

    /**
     * 确认好友关系
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "mobile/friend/confirefriend")
    public
    @ResponseBody
    String confireFriend(Integer id) {
        return friendServiceImpl.confireFriend(id);
    }

    /**
     * q确认好友关系
     *
     * @param phone1
     * @param phone2
     * @return
     */
    @RequestMapping(value = "mobile/friend/confirefriend2phone")
    public
    @ResponseBody
    String confireFriend2Phone(String phone1, String phone2) {
        return friendServiceImpl.confireFriend(phone1, phone2);
    }

    /**
     * 解除好友关系
     *
     * @param phone1
     * @param phone2
     * @return
     */
    @RequestMapping(value = "mobile/friend/release")
    public
    @ResponseBody
    String release(String phone1, String phone2) {
        return friendServiceImpl.releaseFriend(phone1, phone2);
    }

    /**
     * 查询未添加成好友关系的用户列表
     *
     * @param pageResult
     * @param phone      当前用户的手机号
     * @param alumnus    查询条件
     * @param orderby    排序规则 "username","location"
     * @return
     */
    @RequestMapping(value = "mobile/friend/queryunfriend")
    public
    @ResponseBody
    String queryUnFriend(PageResult<User> pageResult, String phone, Alumnus alumnus, String orderby) {
        PageResult<User> pageResult1 = userServiceImpl.queryUnFriendUser(pageResult, phone, alumnus, orderby);
        return ResponseData.getResData(pageResult1);
    }

    /**
     * 查询所有校友
     *
     * @param pageResult
     * @param phone      当前用户的手机号
     * @param alumnus    查询条件
     * @param orderby    排序规则 "username","location"
     * @return
     */
    @RequestMapping(value = "mobile/friend/queryallfriend")
    public
    @ResponseBody
    String queryAllFriend(PageResult<User> pageResult, String phone, Alumnus alumnus, String orderby) {
        User user = userServiceImpl.queryUserByPhone(phone);
        if(null == user){
            return ResponseState.INVALID_PHONE;
        }
        PageResult<User> pageResult1 = userServiceImpl.queryAllUser(pageResult, user, alumnus, orderby);
        return ResponseData.getResData(pageResult1);
    }

    /**
     * 查询附近校友
     *
     * @param pageResult
     * @param phone 当前用户
     * @return
     */
    @RequestMapping(value = "mobile/user/querynearusers")
    public
    @ResponseBody
    String queryNearUsers(PageResult<User> pageResult, String phone) {

        User user = userServiceImpl.queryUserByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        PageResult<User> pageResult1 = userServiceImpl.queryNearUsers(pageResult, phone);
        return ResponseData.getResData(pageResult1);
    }

    /**
     * 添加发送请求
     *
     * @param phone1
     * @param phone2
     * @return
     */
    @RequestMapping(value = "mobile/friend/addfriendapply")
    public
    @ResponseBody
    String addFriendApply(String phone1, String phone2 , String message){
        return friendServiceImpl.addFriendApply(phone1 , phone2 , message);
    }

    /**
     * 查询好友请求列表
     *
     * @param  phone
     * @return
     */
    @RequestMapping(value = "mobile/friend/queryfriendapply")
    public
    @ResponseBody
    String queryFriendApply(PageResult<FriendApply> pageResult, String phone){
        return friendServiceImpl.queryFriendApply(pageResult,phone);
    }
}
