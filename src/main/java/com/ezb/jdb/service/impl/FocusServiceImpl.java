package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.*;
import com.ezb.jdb.dao.*;
import com.ezb.jdb.model.*;
import com.ezb.jdb.service.IPicService;
import com.ezb.jdb.service.IFocusService;
import com.ezb.jdb.tool.JdbBeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.List;

/**
 * 焦点图
 * author : liufeng
 * create time:2015/8/20 10:17
 */
@Service
public class FocusServiceImpl implements IFocusService {

    @Resource
    private FocusDao focusDao;

    @Resource
    private TopicDao topicDao;


    @Resource
    private IPicService picServiceImpl;

    @Resource
    private CircleDao circleDao;

    @Resource
    private SpeColumnDao speColumnDao;

    @Resource
    private ActivityDao activityDao;

    public PageResult<Focus> getTopFocus(PageResult<Focus> pageResult) {

        pageResult = focusDao.getTopFocus(pageResult);
        List<Focus> resultList = pageResult.getResultList();

        for (int i = 0; i < resultList.size(); i++) {
            Focus focus = resultList.get(i);
            if (StringUtils.equals(focus.getType(), NavType.SPECOL.toString())) {
                Topic Topic = topicDao.get(Topic.class, focus.getRefId());
                if (null == Topic || 0 == Topic.getState()) {
                    resultList.remove(focus);
                    i--;
                    pageResult.setTotalCount(pageResult.getTotalCount() - 1);
                }
            }

            if (StringUtils.equals(focus.getType(), NavType.ACTIVITY.toString())) {
                Activity activity = activityDao.get(Activity.class, focus.getRefId());
                if (null == activity || 0 == activity.getState()) {
                    resultList.remove(focus);
                    i--;
                    pageResult.setTotalCount(pageResult.getTotalCount() - 1);
                }
            }
        }

        return pageResult;
    }

    public PageResult<Focus> getCircleFocus(PageResult<Focus> pageResult) {
        return focusDao.getCircleFocus(pageResult);
    }

    public PageResult<Focus> getMTopFocus(PageResult<Focus> pageResult) {
        return focusDao.getMTopFocus(pageResult);
    }

    public PageResult<Focus> getMCircleFocus(PageResult<Focus> pageResult) {
        return focusDao.getMCircleFocus(pageResult);
    }

    public String saveFocusDatas(List<Focus> focusList) {

        for (Focus focus : focusList) {

            if (null != focus.getType()) {
                if (StringUtils.equals(focus.getType(), NavType.CIRCLE.toString())) {
                    focus.setViewurl(JdbConstants.VIEWURL_CIRCLE + "?id=" + focus.getRefId());
                    if (null != focus.getRefId()) {
                        if (circleDao.get(Circle.class, focus.getRefId()) == null) {
                            return getUnexistRefIdErrStr(focus);
                        }
                    }
                }

                if (StringUtils.equals(focus.getType(), NavType.SPECOL.toString())) {
                    focus.setViewurl(JdbConstants.VIEWURL_TOPIC + "?id=" + focus.getRefId());
                    if (null != focus.getRefId()) {
                        if (speColumnDao.get(SpeColumn.class, focus.getRefId()) == null) {
                            return getUnexistRefIdErrStr(focus);
                        }
                    }
                }

                if (StringUtils.equals(focus.getType(), NavType.ACTIVITY.toString())) {
                    focus.setViewurl(JdbConstants.VIEWURL_ACTIVITY + "?id=" + focus.getRefId());
                    if (null != focus.getRefId()) {
                        if (activityDao.get(Activity.class, focus.getRefId()) == null) {
                            return getUnexistRefIdErrStr(focus);
                        }
                    }
                }

                if (StringUtils.equals(focus.getType(), NavType.OTHER.toString())) {
                    focus.setRefId(null);
                }
            }
        }

        for (Focus focus : focusList) {
            saveOne(focus, true);
        }

        return ResponseState.SUCCESS;
    }

    private void saveOne(Focus focus, boolean saveRefId) {
        if (null != focus.getId()) {
            Focus oldFocus = focusDao.get(Focus.class, focus.getId());
            if (null != oldFocus) {
                JdbBeanUtil.copyProperties(focus, oldFocus);
                if (saveRefId && null == focus.getRefId()) {
                    oldFocus.setRefId(null);
                }
                focusDao.update(oldFocus);
            }
        } else {
            focusDao.add(focus);
        }
    }

    private void saveOne(Focus focus) {
        saveOne(focus, false);
    }

    public String saveOne(HttpServletRequest request, Focus focus) {
        String rpath = picServiceImpl.uploadResizeCut(request, 1080, 450);
        if (StringUtils.equals(rpath, ResponseState.PIC_ERR)) {
            return ResponseState.PIC_SAVE_ERR_JSON;
        }
        focus.setPicpath(rpath);
        saveOne(focus);
        return ResponseState.SUCCESS;
    }

    public String view(Integer id) {
        Focus focus = focusDao.get(Focus.class, id);
        if (null == focus) {
            return ResponseState.INVALID_ID;
        }
        return ResponseData.getResData(focus);
    }

    private String getUnexistRefIdErrStr(Focus focus){
        Focus tFocus = focusDao.get(Focus.class,focus.getId());
        return ResponseState.getResState(
                ResponseState.UNEXIST_REFID[0],
                MessageFormat.format(
                        ResponseState.UNEXIST_REFID[1], tFocus.getPosition()
                )
        );
    }
}
