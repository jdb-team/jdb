package com.ezb.jdb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.CirCmtDao;
import com.ezb.jdb.dao.CircleDao;
import com.ezb.jdb.dao.JoinUserCircleDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.model.Circle;
import com.ezb.jdb.model.JoinUserCircle;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.ICircleService;
import com.ezb.jdb.tool.JdbBeanUtil;
import com.ezb.jdb.tool.JdbFileUtil;
import com.ezb.jdb.tool.JdbPicUtil;
import com.ezb.jdb.view.CircleView;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/14 16:06
 */
@Service
public class CircleServiceImpl implements ICircleService {

    @Resource
    private CircleDao circleDao;

    @Resource
    private CirCmtDao cirCmtDao;

    @Resource
    private UserDao userDao;

    @Resource
    private JoinUserCircleDao joinUserCircleDao;

    @Value("${uploadWarPath}")
    private String uploadWarPath;

    public String queryCircles(PageResult<Circle> pageResult,
                               String phone,
                               String queryWords) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        pageResult = circleDao.queryCircles(pageResult, queryWords);
        for (Circle circle : pageResult.getResultList()) {
            circle.setCmtCount(cirCmtDao.queryCount(circle.getId()));
            circle.setCount(qCountCircleByid(circle.getId()));
        }
        return CircleView.convert2Json(pageResult.getResultList(), user);
    }

    public String queryMyCircles(PageResult<Circle> pageResult,
                                 String phone,
                                 String queryWords) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        pageResult = circleDao.queryMyCircles(pageResult, phone, queryWords);
        for (Circle circle : pageResult.getResultList()) {
            circle.setCmtCount(cirCmtDao.queryCount(circle.getId()));
            circle.setCount(qCountCircleByid(circle.getId()));
        }
        return CircleView.convert2Json(pageResult.getResultList(), user);
    }

    public String join(String phone, Integer id) {
        User user = userDao.queryByPhone(phone);
        if (null != user) {
            Circle circle = circleDao.get(Circle.class, id);
            if (null != circle) {
                JoinUserCircle joinUserCircle = joinUserCircleDao.getByUCId(user.getId(), circle.getId());
                if (joinUserCircle != null) {
                    return ResponseState.AREADY_JOIN_CIRCLE;
                }
                if (null == circle.getMembers()) {
                    circle.setMembers(new HashSet<JoinUserCircle>());
                }
                joinUserCircle = new JoinUserCircle();
                joinUserCircle.setCircle(circle);
                joinUserCircle.setUser(user);
                circle.getMembers().add(joinUserCircle);
                circleDao.update(circle);
                return ResponseState.SUCCESS;
            } else {
                return ResponseState.INVALID_ID;
            }
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public PageResult<Circle> query(PageResult<Circle> pageResult,
                                    Integer id,
                                    String title,
                                    String realName,
                                    String state,
                                    String startTime,
                                    String endTime) {

        return circleDao.query(pageResult, id, title, realName, state, startTime, endTime);
    }

    public String save(HttpServletRequest request, String phone, Circle circle) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }

        if (uploadPics(request, circle)) { //图片上传
            if (null != circle.getId()) {
                Circle oldCircle = circleDao.get(Circle.class, circle.getId());
                JdbBeanUtil.copyProperties(circle, oldCircle);
                circleDao.update(oldCircle);
            } else {
                circle.setCreateUser(user);
                circle.setCreateTime(new Date());
                circle.setState(1);
                circleDao.add(circle);
            }
            return ResponseState.SUCCESS;
        } else {
            return ResponseState.PIC_SAVE_ERR_JSON;
        }
    }

    public String offline(Integer id) {
        Circle circle = circleDao.get(Circle.class, id);
        if (null == circle) {
            return ResponseState.INVALID_ID;
        }
        if (circle.getState() == 1) {
            circle.setState(0);
        } else {
            circle.setState(1);
        }
        circleDao.update(circle);
        return ResponseState.SUCCESS;
    }

    public String viewCircle(String phone, Integer id) {
        Circle circle = circleDao.get(Circle.class, id);

        if (null == circle) {
            return ResponseState.INVALID_ID;
        }

        User user = userDao.queryByPhone(phone);

        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("circle", circle);

        if (circle.getMembers().contains(user)) {
            jsonObject.put("join", "1");
        } else {
            jsonObject.put("join", "0");
        }
        return ResponseData.getResData(jsonObject);
    }

    public Circle queryById(Integer id) {
        return circleDao.get(Circle.class, id);
    }

    public String saveNickName(String phone, Integer cid, String nickName) {

        User user = userDao.queryByPhone(phone);
        if(null == user){
            return ResponseState.INVALID_PHONE;
        }

        JoinUserCircle joinUserCircle = joinUserCircleDao.getByUCId(user.getId(),cid);

        if(null == joinUserCircle){
            return ResponseState.INVALID_ID;
        }

        joinUserCircle.setNickName(nickName);
        joinUserCircleDao.update(joinUserCircle);

        return ResponseState.SUCCESS;
    }

    public String delete(String phone, Integer id) {
        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        Circle circle = circleDao.get(Circle.class, id);
        if (null == circle) {
            return ResponseState.INVALID_ID;
        }
        if (user.getId() != circle.getCreateUser().getId()) {
            return ResponseState.AUTH_CIRCLE;
        }

        circleDao.deleteById(circle.getId());
        return ResponseState.SUCCESS;
    }

    public Integer qCountCircleByid(Integer refId) {
        return circleDao.qCountCircleByid(refId);
    }

    public String exit(String phone , Integer id){
        User user = userDao.queryByPhone(phone);
        if(user == null){
            return ResponseState.INVALID_PHONE;
        }
        Circle  circle = circleDao.get(Circle.class , id);
        if(circle == null){
            return ResponseState.INVALID_ID;
        }
        JoinUserCircle joinUserCircle = joinUserCircleDao.getByUCId(user.getId() , circle.getId());
        if(joinUserCircle == null){
            return ResponseState.NOT_JOIN_CIRCLE;
        }
        joinUserCircleDao.deleteById(user.getId() , circle.getId());
        return ResponseState.SUCCESS;

    }

    public String viewnickname(String phone, Integer cid) {
        User user = userDao.queryByPhone(phone);
        if(null == user){
            return ResponseState.INVALID_PHONE;
        }
        JoinUserCircle joinUserCircle = joinUserCircleDao.getByUCId(user.getId(),cid);
        if(null == joinUserCircle){
            return ResponseState.INVALID_ID;
        }
        return ResponseData.getResData(joinUserCircle.getNickName());
    }

    /**
     * 上传图标和不图片，设置circle属性
     *
     * @param request
     * @param circle
     */
    private boolean uploadPics(HttpServletRequest request, Circle circle) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {
            String prePath = request.getSession().getServletContext().getRealPath("/")
                    + File.separator + uploadWarPath;

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();

            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());

                if (file != null && !StringUtils.isEmpty(file.getOriginalFilename())) {
                    String rpath = JdbFileUtil.uploadFile(file, prePath);

                    if (StringUtils.equals(rpath, ResponseState.PIC_ERR)) {
                        return false;
                    }

                    String inputPath = request.getSession().getServletContext()
                            .getRealPath("/") + File.separator + uploadWarPath + rpath;

                    if (StringUtils.equals(file.getName(), "pic")) {
                        rpath = JdbPicUtil.resizecut(inputPath, uploadWarPath, JdbConstants.PIC_WIDTH, JdbConstants.PIC_HEIGHT);
                        circle.setPicPath(rpath);
                    }

                    if (StringUtils.equals(file.getName(), "icon")) {
                        rpath = JdbPicUtil.resizecut(inputPath, uploadWarPath, 220, 220);
                        circle.setIconPath(rpath);
                    }
                }
            }
        }
        return true;
    }

    public String batchJoin(String phones,int circleId){
        if (phones.contains(",")) {
            String[] phonrArray = phones.split(",");
            Circle circle = circleDao.get(Circle.class, circleId);
            if (null != circle) {
                for (int i = 0; i < phonrArray.length; i++) {
                    User user = userDao.queryByPhone(phonrArray[i]);
                    if (null != user) {
                        JoinUserCircle joinUserCircle = joinUserCircleDao.getByUCId(user.getId(), circle.getId());
                        if (joinUserCircle != null) {
                            return ResponseState.AREADY_JOIN_CIRCLE;
                        }
                        if (null == circle.getMembers()) {
                            circle.setMembers(new HashSet<JoinUserCircle>());
                        }
                        joinUserCircle = new JoinUserCircle();
                        joinUserCircle.setCircle(circle);
                        joinUserCircle.setUser(user);
                        circle.getMembers().add(joinUserCircle);
                        circleDao.update(circle);
                        } else {
                            return ResponseState.INVALID_PHONE;
                        }
                    }
                }
            return  ResponseState.SUCCESS;
        }else{
            return  join(phones,circleId);
        }
    }
}
