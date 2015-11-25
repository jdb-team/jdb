package com.ezb.jdb.service;

import com.ezb.jdb.model.AtvCmt;

import java.util.List;

/**
 * 活动评论
 * author : liufeng
 * create time:2015/8/10 13:57
 */
public interface IAtvCmtService {

    List<AtvCmt> qAtvCmtByActivityId(Integer id);

    String addAtvCmt(String phone, AtvCmt atvCmt);

    String likeAtvCmt(String phone, Integer id);

}
