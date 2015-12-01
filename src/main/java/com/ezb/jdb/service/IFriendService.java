package com.ezb.jdb.service;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Friend;

/**
 * 通讯录
 * author : liufeng
 * create time:2015/8/12 10:50
 */
public interface IFriendService {

    PageResult<Friend> queryFriend(PageResult<Friend> pageResult, String phone,
                                   String queryWords,Integer state);

    /**
     * 确认好友关系
     * @param id
     * @return
     */
    String confireFriend(Integer id);

    /**
     * 添加好友
     * @param phone1
     * @param phone2
     * @return
     */
    String addFriend(String phone1, String phone2);

    /**
     * 添加好友发送请求
     * @param phone1
     * @param phone2
     * @return
     */
    String addFriendApply(String phone1 , String phone2 , String message);

    String confireFriend(String phone1, String phone2);

    /**
     * 解除好友关系
     * @param phone1
     * @param phone2
     * @return
     */
    String releaseFriend(String phone1, String phone2);
}
