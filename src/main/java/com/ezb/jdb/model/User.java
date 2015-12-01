package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 * author : liufeng
 * create time: 2015/8/1 15:39.
 */
@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
@DynamicUpdate
public class User extends Fmodel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 32)
    private String username;//默认手机号

    @Column(length = 32)
    private String password;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Alumnus alumnus;

    @Column
    private Integer state;//状态 0停用 1正常

    @Column(name="c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//注册日期

    @Transient
    private Integer cCount;//加入圈子个数

    @Transient
    private Integer tCount;//发布话题个数
}
