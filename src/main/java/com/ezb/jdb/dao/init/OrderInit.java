package com.ezb.jdb.dao.init;

import com.ezb.jdb.common.JdbConstants;
import com.ezb.jdb.dao.init.easemob.*;
import com.ezb.jdb.dao.init.jdb.*;
import com.ezb.jdb.easemob.comm.Constants;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 按依赖关系初始化数据
 * author : liufeng
 * create time:2015/11/25 9:25
 */
@Component
@Slf4j
public class OrderInit {

    @Resource
    private UserInit userInit;

    @Resource
    private AdminInit adminInit;

    @Resource
    private SpecolumnInit specolumnInit;

    @Resource
    private TopicTypeInit topicTypeInit;

    @Resource
    private TopicInit topicInit;

    @Resource
    private TopicCmtInit topicCmtInit;

    @Resource
    private ActivityInit activityInit;

    @Resource
    private AtvCmtInit atvCmtInit;

    @Resource
    private FriendInit friendInit;

    @Resource
    private CircleInit circleInit;

    @Resource
    private CirCmtInit cirCmtInit;

    @Resource
    private MessageInit messageInit;

    @Resource
    private FocusInit focusInit;

    @Resource
    private InformInit informInit;

    @Resource
    private CircleMessageInit circleMessageInit;

    @Resource
    private EuserInit euserInit;

    @Resource
    private EfriendInit efriendInit;

    @Resource
    private EcircleInit ecircleInit;

    @Resource
    private EmessageInit emessageInit;

    @Resource
    private EcircleMessageInit ecircleMessageInit;

    @Resource
    private JoinUserCircleInit joinUserCircleInit;

    /**
     * 交大邦祥光数据初始化
     */
    private void initJdb(){
        log.info("=============== start initJdb ==============");

        userInit.init();
        log.info("end init user");

        adminInit.init();
        log.info("end init admin");

        specolumnInit.init();
        log.info("end init specolumn");

        topicTypeInit.init();
        log.info("end init topicType");

        topicInit.init();
        log.info("end init topic");

        topicCmtInit.init();
        log.info("end init topicCmt");

        activityInit.init();
        log.info("end init activity");

        atvCmtInit.init();
        log.info("end init atvcmt");

        friendInit.init();
        log.info("end init friend");

        circleInit.init();
        log.info("end init circle");

        cirCmtInit.init();
        log.info("end init circmt");

        messageInit.init();
        log.info("end init message");

        focusInit.init();
        log.info("end init focus");

        informInit.init();
        log.info("end init inform");

        joinUserCircleInit.init();
        log.info("end init joinusercircle");

        circleMessageInit.init();
        log.info("end int circlemessage");

        log.info("=============== end initJdb ==============");
    }

    /**
     * 环信相关数据初始化
     */
    private void initEaseMob(){
        log.info("=============== start initEaseMob ==============");

        euserInit.init();
        log.info("end init user");

        efriendInit.init();
        log.info("end init friend");

        ecircleInit.init();
        log.info("end init circle");

        emessageInit.init();
        log.info("end init message");

        ecircleMessageInit.init();
        log.info("end init circlemessage");

        log.info("=============== end initEaseMob ==============");
    }

    /**
     * 初始化应用
     */
    public void orderInit(){

        if(JdbConstants.JDB_INIT_DATA){
            initJdb();
        }

        if(Constants.INIT_DATA){
            initEaseMob();
        }
    }
}
