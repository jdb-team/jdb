package com.ezb.jdb.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 用户屏蔽关系
 * author : liufeng
 * create time:2015/11/21 13:46
 */
@Data
@Entity
@Table(name = "shielduser")
@DynamicUpdate
public class ShieldUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cur_user_id")
    private User curUser;           //发起屏蔽的用户

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shield_user_id")
    private User shieldUser;        //被屏蔽的用户

    @Column(name = "type", length = 32)
    private String type;            //类型
}
