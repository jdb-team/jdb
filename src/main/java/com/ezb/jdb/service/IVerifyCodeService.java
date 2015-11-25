package com.ezb.jdb.service;

import com.ezb.jdb.model.VerifyCode;

/**
 * author : liufeng
 * create time: 2015/8/6 10:37.
 */
public interface IVerifyCodeService {

    void save(VerifyCode verifyCode);

    String generateCode(String phone);

    int clearExpire();
}
