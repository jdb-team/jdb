package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.ezb.jdb.tool.JdbGisUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 通讯录
 * author : liufeng
 * create time:2015/8/11 17:32
 */
@Data
@Entity
@Table(name = "friend")
@DynamicUpdate
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "friend_id")
    private User friend;

    @Column
    private Double distance;

    @Column(name = "a_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date applyDate;//申请时间

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date confireDate;//确认时间

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public Double getDistance() {

        return JdbGisUtil.getDistance(
                user.getAlumnus().getLat(),
                user.getAlumnus().getLng(),
                friend.getAlumnus().getLat(),
                friend.getAlumnus().getLng()
        );
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
