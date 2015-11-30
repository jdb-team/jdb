package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 最新消息通知
 * author : liufeng
 * create time:2015/9/17 17:31
 */
@Data
@Entity
@Table(name = "msgnotify")
public class MsgNotify {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 255)
    private String msg;

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column
    private Integer type;//0发送对象为user 1发送对象为circle

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cur_userid")
    private User curUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ctc_userid")
    private User ctcUser;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ctc_circleid")
    private Circle ctcCircle;
}
