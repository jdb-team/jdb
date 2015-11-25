package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 系统管理员
 * author : liufeng
 * create time:2015/8/21 14:02
 */
@Data
@Entity
@Table(name = "admin")
@DynamicUpdate
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer level;//管理员级别 0超级管理员

    @Column(length = 32)
    private String username;

    @Column(length = 32)
    private String password;

    @Column(name="realname",length = 16)
    private String realName;

    @Column(length = 16)
    private String phone;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="create_userid")
    @JSONField(serialize = false)
    private Set<Circle> circles;

    @Column(name="c_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public boolean equals(Object obj) {
        return (this == obj);
    }

    public int hashCode(){
        return super.hashCode();
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
