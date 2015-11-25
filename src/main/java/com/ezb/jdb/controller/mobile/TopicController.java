package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.Topic;
import com.ezb.jdb.model.TopicCmt;
import com.ezb.jdb.model.TopicType;
import com.ezb.jdb.service.ITopicCmtService;
import com.ezb.jdb.service.ITopicService;
import com.ezb.jdb.service.ITopicTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 话题
 * author : liufeng
 * create time: 2015/8/7 10:26.
 */
@Controller
public class TopicController {

    @Resource
    private ITopicService topicServiceImpl;

    @Resource
    private ITopicCmtService topicCmtServiceImpl;

    @Resource
    private ITopicTypeService topicTypeServiceImpl;

    /**
     * 话题分页列表
     *
     * @param pageResult
     * @param phone       phone
     * @param speColumnId 专栏id
     * @param typeId      话题类别Id
     * @param labelId     1 热门 2 最新 3 好友
     * @param isShield    false 不过滤屏蔽的好友 true 过滤掉屏蔽的好友话题
     * @param isOneUser   false 查询全部用户话题 true 查询指定phone用户的话题
     * @return
     */
    @RequestMapping(value = "mobile/topic/query")
    public
    @ResponseBody
    String query(PageResult<Topic> pageResult,
                 String phone,
                 String speColumnId,
                 String typeId,
                 String labelId,
                 Boolean isShield,
                 Boolean isOneUser) {

        return topicServiceImpl.queryTopic(pageResult, phone, speColumnId, typeId, labelId, isShield, isOneUser);
    }

    /**
     * 屏蔽指定用户话题
     *
     * @param phone  当前用户手机号
     * @param tphone 被屏蔽用户手机号
     * @return
     */
    @RequestMapping(value = "mobile/topic/shield")
    public
    @ResponseBody
    String shield(String phone, String tphone) {
        return topicServiceImpl.shield(phone, tphone);
    }

    /**
     * 话题详情页
     *
     * @param phone 当前用户手机号
     * @param id    话题 id
     * @return
     */
    @RequestMapping(value = "mobile/topic/view")
    public
    @ResponseBody
    String view(String phone, Integer id) {
        return topicServiceImpl.view(phone, id);
    }

    /**
     * 话题点赞
     *
     * @param phone 当前用户手机号
     * @param id    话题id
     * @return
     */
    @RequestMapping(value = "mobile/topic/lv")
    public
    @ResponseBody
    String lv(String phone, Integer id) {
        return topicServiceImpl.lv(phone, id);
    }

    /**
     * 新增分享
     *
     * @param phone 当前用户手机号
     * @param id    当前话题 id
     * @return
     */
    @RequestMapping(value = "mobile/topic/sv")
    public
    @ResponseBody
    String sv(String phone, Integer id) {
        return topicServiceImpl.sv(phone, id);
    }

    /**
     * 话题创建页
     *
     * @param topic 话题
     * @param phone 当前用户手机号
     * @return
     */
    @RequestMapping(value = "mobile/topic/createtopic")
    public
    @ResponseBody
    String createTopic(HttpServletRequest request, Topic topic, String phone) {
        return topicServiceImpl.addTopic(request, topic, phone);
    }

    /**
     * 创建评论
     *
     * @param phone    当前用户手机号
     * @param topicCmt 话题评论
     * @return
     */
    @RequestMapping(value = "mobile/topic/createtopiccmt")
    public
    @ResponseBody
    String createTopicCmt(String phone, TopicCmt topicCmt) {
        return topicCmtServiceImpl.addTopicCmt(phone, topicCmt);
    }

    /**
     * 评论点赞
     *
     * @param phone 手机号
     * @param id    对应评论id
     * @return
     */
    @RequestMapping(value = "mobile/topic/liketopiccmt")
    public
    @ResponseBody
    String likeTopicCmt(String phone, Integer id) {
        return topicCmtServiceImpl.likeTopicCmt(phone, id);
    }

    /**
     * 查询
     *
     * @param pageResult 分页信息
     * @param typeName   分类名称
     * @return
     */
    @RequestMapping(value = "mobile/topic/querytopictype")
    public
    @ResponseBody
    String queryTopicType(PageResult<TopicType> pageResult,
                          String typeName) {
        return topicTypeServiceImpl.query(pageResult, typeName);
    }
}
