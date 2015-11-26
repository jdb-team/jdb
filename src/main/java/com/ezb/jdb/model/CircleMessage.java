package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * 圈子群聊消息
 * author : liufeng
 * create time:2015/11/26 9:57
 */
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "circlemessage")
@DynamicUpdate
public class CircleMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @Type(type="text")
    private String content;

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//消息发送时间

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "sender_userid")
    private User sender;//发送者

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "circle_id")
    private Circle circle;//发送者
}
