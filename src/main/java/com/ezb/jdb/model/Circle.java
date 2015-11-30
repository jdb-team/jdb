package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 圈子
 * author : liufeng
 * create time:2015/8/14 14:50
 */
@Data
@Entity
@Table(name = "circle")
@DynamicUpdate
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 32)
    private String eid;//在环信服务器上的id 如果为空，说明还未在环信上初始化

    @Column(length = 32)
    private String title;

    @Column(name = "iconpath", length = 255)
    private String iconPath;//圈子图标

    @Column(name = "picpath", length = 255)
    private String picPath;//图片路径

    @Column(length = 255)
    private String introduce;

    @Transient
    private Integer cmtCount;//评论条数

    @Transient
    private Integer count;

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//圈子创建时间

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "create_userid")
    private User createUser;//圈子创建人

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "circle_id")
    @JSONField(serialize = false)
    private Set<JoinUserCircle> members;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "circle_id")
    @JSONField(serialize = false)
    private Set<CirCmt> comments;

    @Column
    private Integer state;//状态 0下线 1正常

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
