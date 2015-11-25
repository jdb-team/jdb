package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 活动
 * author : liufeng
 * create time: 2015/8/4 9:03.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "activity")
@DynamicUpdate
public class Activity extends Fmodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 64)
    private String title;//标题

    @Column(name = "picpath", length = 255)
    private String picPath;//图片路径

    @Column(name = "s_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;//举办时间

    @Column(name = "e_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间

    @Column(name = "close_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;//截止时间

    @Column(name = "personlimit")
    private Integer personLimit;//人数上限

    @Column(length = 16)
    private String city;//举办城市

    @Column(length = 128)
    private String address;//详细地址

    @Column(name = "activitydesc")
    @Type(type="text")
    private String activityDesc;//活动简介

    @Column(length = 256)
    private String topic;//核心话题

    @Column(name = "joinfee", length = 64)
    private String joinFee;//参与费用

    @Column(name = "otherinfo")
    @Type(type="text")
    private String otherInfo;//其他

    @Column(name = "contactman", length = 16)
    private String contactMan;//联系人

    @Column(name = "contactphone", length = 16)
    private String contactPhone;//联系电话

    @Column(name = "contactemail", length = 32)
    private String contactEmail;//联系邮箱

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//活动创建时间

    @Column
    private Integer pv;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "create_userid")
    private User createUser;//活动创建人

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "join_user_activity",
            joinColumns = {@JoinColumn(name = "activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> joinUsers;//参加活动的人

    @Column
    private Integer state;//状态 0下线 1正常
}
