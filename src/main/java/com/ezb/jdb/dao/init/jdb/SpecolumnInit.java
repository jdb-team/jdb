package com.ezb.jdb.dao.init.jdb;

import com.ezb.jdb.dao.SpeColumnDao;
import com.ezb.jdb.model.SpeColumn;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: liuyan
 * @date: 2015年11月23日下午2:52:30
 * @Description: 栏目初始化
 */
@Component
public class SpecolumnInit {
    @Resource
    private SpeColumnDao speColumnDao;

    public void init() {

        SpeColumn speColumn1 = new SpeColumn();
        speColumn1.setColumnName("150周年校庆");
        speColumn1.setColumnDesc("这是150周年校庆专栏");
        speColumnDao.add(speColumn1);

        SpeColumn speColumn2 = new SpeColumn();
        speColumn2.setColumnName("专栏2");
        speColumn2.setColumnDesc("专栏2描述");
        speColumnDao.add(speColumn2);

        SpeColumn speColumn3 = new SpeColumn();
        speColumn3.setColumnName("专栏3");
        speColumn3.setColumnDesc("专栏3描述");
        speColumnDao.add(speColumn3);

        SpeColumn speColumn4 = new SpeColumn();
        speColumn4.setColumnName("专栏4");
        speColumn4.setColumnDesc("专栏4描述");
        speColumnDao.add(speColumn4);
    }
}
