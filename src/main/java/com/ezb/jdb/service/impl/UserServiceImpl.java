package com.ezb.jdb.service.impl;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.common.PageResult;
import com.ezb.jdb.common.ResponseData;
import com.ezb.jdb.common.ResponseState;
import com.ezb.jdb.dao.AccessKeyDao;
import com.ezb.jdb.dao.InvitateCodeDao;
import com.ezb.jdb.dao.UserDao;
import com.ezb.jdb.dao.VerifyCodeDao;
import com.ezb.jdb.model.Alumnus;
import com.ezb.jdb.model.User;
import com.ezb.jdb.service.IUserService;
import com.ezb.jdb.service.IPicService;
import com.ezb.jdb.tool.JdbBeanUtil;
import com.ezb.jdb.tool.JdbMd5Util;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * author : liufeng
 * create time: 2015/8/4 15:44.
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Resource
    private VerifyCodeDao verifyCodeDao;

    @Resource
    private AccessKeyDao accessKeyDao;

    @Resource
    private InvitateCodeDao invitateCodeDao;

    @Resource
    private IPicService picServiceImpl;

    public String register(User user, String invitateCode, String verifyCode) {
        if (invitateCodeDao.qcByCode(invitateCode) >= 1) {
            if (verifyCodeDao.qcByPhoneAndCode(user.getUsername(), verifyCode) >= 1) {
                if (isValid(user.getUsername())) {
                    return ResponseState.REGED_PHONE;
                }

                Alumnus alumnus = null;
                if (null != user.getAlumnus()) {
                    alumnus = user.getAlumnus();
                } else {
                    alumnus = new Alumnus();
                    user.setAlumnus(alumnus);
                }

                alumnus.setPhone(user.getUsername());
                alumnus.setUser(user);

                user.setState(1);
                user.setCreateTime(new Date());
                user.setPassword(JdbMd5Util.md5(user.getPassword()));
                userDao.add(user);
                invitateCodeDao.deleteByCode(invitateCode);
                return ResponseState.SUCCESS;
            } else {
                return ResponseState.VERIFYCODE_ERR;
            }
        } else {
            return ResponseState.INVITATE_ERR;
        }
    }

    public String login(HttpServletRequest request, HttpServletResponse response, User user) {

        //手机号为空
        if (null == user || StringUtils.isEmpty(user.getUsername())) {
            return ResponseState.PHONE_EMPTY;
        }

        //密码为空
        if (StringUtils.isEmpty(user.getPassword())) {
            return ResponseState.PASS_EMPTY;
        }

        //手机号不存在
        if (!isValid(user.getUsername())) {
            return ResponseState.INVALID_PHONE;
        }

        user.setPassword(JdbMd5Util.md5(user.getPassword()));
        User rUser = userDao.login(user);

        //密码错误
        if (null == rUser) {
            return ResponseState.LOGIN_PASS_ERR;
        }

        //用户被停用
        if (rUser.getState() == 0) {
            return ResponseState.USER_DISABLE;
        }

        //完善经纬度信息
        if (null != user.getAlumnus()) {
            if (null != user.getAlumnus().getLat()) {
                rUser.getAlumnus().setLat(user.getAlumnus().getLat());
            }
            if (null != user.getAlumnus().getLng()) {
                rUser.getAlumnus().setLng(user.getAlumnus().getLng());
            }
            userDao.update(rUser);
        }

        String token = generateToken(request, user);
        response.addHeader(JdbConstants.TOKEN_HEADER, token);
        return ResponseData.getResData(rUser);
    }

    private String generateToken(HttpServletRequest request, User user) {
        //md5 加密
        String token = JdbMd5Util.md5(System.currentTimeMillis()
                + user.getUsername() + request.getSession().getId());
        accessKeyDao.add(token, JdbConstants.ACCKEY_TOKEN);
        return token;
    }


    /**
     * 上传头像
     *
     * @param request
     * @param phone
     * @return
     */
    public String uploadHeadPic(HttpServletRequest request, String phone) {

        User user = userDao.queryByPhone(phone);
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }

        String rpath = picServiceImpl.uploadResizeCut(request, 220, 220);
        if (StringUtils.equals(rpath, ResponseState.PIC_ERR)) {
            return ResponseState.PIC_SAVE_ERR_JSON;
        }
        user.getAlumnus().setHeadPicPath(rpath);
        userDao.update(user);
        return ResponseData.getResData(user);
    }

    /**
     * 信息完善
     *
     * @param alumnus
     * @return
     */
    public String perfInfo(HttpServletRequest request, Alumnus alumnus) {

        if(null == alumnus || StringUtils.isEmpty(alumnus.getPhone())){
            return ResponseState.INVALID_PHONE;
        }
        User user = userDao.queryByPhone(alumnus.getPhone());
        if (null == user) {
            return ResponseState.INVALID_PHONE;
        }
        String rpath = picServiceImpl.uploadResizeCut(request, 220, 220);
        if (StringUtils.equals(rpath, ResponseState.PIC_ERR)) {
            return ResponseState.PIC_SAVE_ERR_JSON;
        }
        if (!StringUtils.isEmpty(rpath)) {
            alumnus.setHeadPicPath(rpath);
        }
        Alumnus rAlumnus = user.getAlumnus();
        JdbBeanUtil.copyProperties(alumnus, rAlumnus);
        userDao.update(user);
        return ResponseData.getResData(user);
    }

    public boolean isValid(String phone) {
        return userDao.qcByPhone(phone) >= 1;
    }

    public User queryUserByPhone(String phone) {
        return userDao.queryByPhone(phone);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public PageResult<User> queryUnFriendUser(PageResult<User> pageResult, String phone, Alumnus alumnus, String orderby) {
        return userDao.queryUnFriendUser(pageResult, phone, alumnus, orderby);
    }

    public String resetPwd(String phone, String password, String verifyCode) {
        if (isValid(phone)) {
            if (verifyCodeDao.qcByPhoneAndCode(phone, verifyCode) >= 1) {
                User user = queryUserByPhone(phone);
                user.setPassword(JdbMd5Util.md5(password));
                update(user);
                return ResponseState.SUCCESS;
            } else {
                return ResponseState.VERIFYCODE_ERR;
            }
        } else {
            return ResponseState.INVALID_PHONE;
        }
    }

    public String resetPhone(String phone, String newPhone, String verifyCode) {
        if (verifyCodeDao.qcByPhoneAndCode(newPhone, verifyCode) >= 1) {
            User user = queryUserByPhone(phone);
            if (null != user) {
                user.setUsername(newPhone);
                user.getAlumnus().setPhone(newPhone);
                userDao.update(user);
                return ResponseState.SUCCESS;
            } else {
                return ResponseState.INVALID_PHONE;
            }
        } else {
            return ResponseState.VERIFYCODE_ERR;
        }
    }

    public PageResult<User> queryNearUsers(PageResult<User> pageResult, String phone) {
        return userDao.queryNearUsers(pageResult, phone);
    }

    public PageResult<User> queryAllUser(PageResult<User> pageResult, User user, Alumnus alumnus, String orderby) {
        return userDao.queryAllUser(pageResult, user, alumnus, orderby);
    }

    public PageResult<User> query(PageResult<User> pageResult, String username, String state, Alumnus alumnus, String startTime, String endTime) {
        return userDao.query(pageResult, username, state, alumnus, startTime, endTime);
    }

    public String state(Integer id) {
        User user = userDao.get(User.class, id);
        if (null == user) {
            return ResponseState.INVALID_ID;
        }
        if (1 == user.getState()) {
            user.setState(0);
        } else {
            user.setState(1);
        }
        userDao.update(user);
        return ResponseState.SUCCESS;
    }
}
