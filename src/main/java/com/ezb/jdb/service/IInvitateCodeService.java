package com.ezb.jdb.service;

/**
 * author : liufeng
 * create time: 2015/8/6 11:15.
 */
public interface IInvitateCodeService {

    String generateCode(String name);

    int clearExpire();
}
