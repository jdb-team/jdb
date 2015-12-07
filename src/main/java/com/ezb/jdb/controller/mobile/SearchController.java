package com.ezb.jdb.controller.mobile;

import com.ezb.jdb.model.SearchPageResult;
import com.ezb.jdb.service.IGlobalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 全站搜索
 * author : liufeng
 * create time:2015/11/21 9:38
 */
@Controller
@Slf4j
public class SearchController {

    @Resource
    private IGlobalService globalServiceImpl;

    /**
     * 全站搜索列表
     *
     * @param phone            当前用户手机号
     * @param searchPageResult 参数封装
     * @param keyword          查询关键字
     * @return
     */
    @RequestMapping(value = "mobile/global/search")
    public
    @ResponseBody
    String search(
            String phone,
            SearchPageResult searchPageResult,
            String keyword) {

        return globalServiceImpl.search(
                phone,
                searchPageResult.getCirclePageResult(),
                searchPageResult.getActivityPageResult(),
                searchPageResult.getTopicPageResult(),
                searchPageResult.getFriendPageResult(),
                keyword
        );
    }
}
