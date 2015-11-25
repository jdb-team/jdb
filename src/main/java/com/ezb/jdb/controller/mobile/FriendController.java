package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.Friend;
import com.ezb.jdb.model.User;
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
     * @param state      0 带通过的好友 1表示已通过的好友
     * @return
     */
    @RequestMapping(value = "mobile/friend/queryfriend")
    public
    @ResponseBody
    String queryFriend(PageResult<Friend> pageResult, String phone, String queryWords, Integer state) {
        List<Friend> list = friendServiceImpl.queryFriend(
                pageResult, phone, queryWords, state).getResultList();
        User user = userServiceImpl.queryUserByPhone(phone);
        return FriendView.convert2Json(user,list);
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
     * 解除好友关系
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
        List<User> list = userServiceImpl.queryUnFriendUser(pageResult, phone, alumnus, orderby).getResultList();
        User user = userServiceImpl.queryUserByPhone(phone);
        return FriendView.convertUList2Json(user, list);
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
        List<User> list = userServiceImpl.queryAllUser(pageResult, user, alumnus, orderby).getResultList();
        return FriendView.convertUList2Json(user, list);
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
        List<User> list = userServiceImpl.queryNearUsers(pageResult, phone).getResultList();
        return FriendView.convertUList2Json(user,list);
    }

    /**
     * 添加好友
     *
     * @param phone1
     * @param phone2
     * @return
     */
    @RequestMapping(value = "mobile/friend/addfriend")
    public
    @ResponseBody
    String addFriend(String phone1, String phone2) {
        return friendServiceImpl.addFriend(phone1, phone2);
    }

}
