package com.ezb.jdb.controller.pc.admin;

import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.model.TopicType;
import com.ezb.jdb.service.ITopicTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 话题分类
 * author : liufeng
 * create time:2015/11/13 11:44
 */
@Controller
public class MTopicTypeController {

    @Resource
    private ITopicTypeService topicTypeServiceImpl;

    /**
     * 添加话题分类
     *
     * @param topicType
     * @return
     */
    @RequestMapping(value = "pc/admin/topictype/add")
    public
    @ResponseBody
    String add(TopicType topicType) {
        return topicTypeServiceImpl.add(topicType);
    }

    /**
     * 更新话题分类
     *
     * @param topicType
     * @return
     */
    @RequestMapping(value = "pc/admin/topictype/update")
    public
    @ResponseBody
    String update(TopicType topicType) {
        return topicTypeServiceImpl.update(topicType);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/topictype/delete")
    public
    @ResponseBody
    String delete(String id) {
        return topicTypeServiceImpl.delete(id);
    }

    /**
     * 查询
     *
     * @param pageResult 分页信息
     * @param typeName   分类名
     * @return
     */
    @RequestMapping(value = "pc/admin/topictype/query")
    public
    @ResponseBody
    String query(PageResult<TopicType> pageResult,
                 String typeName) {
        return topicTypeServiceImpl.query(pageResult, typeName);
    }

    /**
     * 查询某个话题分类
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "pc/admin/topictype/view")
    public
    @ResponseBody
    String view(Integer id) {
        return topicTypeServiceImpl.queryById(id);
    }
}
