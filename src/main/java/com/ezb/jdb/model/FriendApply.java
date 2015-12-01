package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liuyan on 2015/12/1.
 */
@Data
@Entity
@Table(name = "friendapply")
@DynamicUpdate
public class FriendApply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;//好友申请ID

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender")
    private User sender;//请求者

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver")
    private User receiver;//被请求者

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;//申请时间

    @Column(name="message",length = 255)
    private String message;//请求消息

    @Column(name = "state")
    private Integer state;//是否确认好友关系
}
