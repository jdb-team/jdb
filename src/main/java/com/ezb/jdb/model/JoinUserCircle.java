package com.ezb.jdb.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 圈子昵称
 * author : liufeng
 * create time:2015/11/19 9:18
 */
@Data
@Entity
@Table(name = "join_user_circle")
@DynamicUpdate
public class JoinUserCircle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;          //对应用户

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "circle_id")
    private Circle circle;      //对应圈子

    @Column(name = "nickname", length = 32)
    private String nickName;    //昵称

    @Column(name = "msg_count")
    private  int msgCount;

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
