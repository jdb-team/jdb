package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.ezb.jdb.tool.JdbDateUtil;
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
 * 话题
 * author : liufeng
 * create time: 2015/8/7 9:34.
 */
@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "topic")
@DynamicUpdate
public class Topic extends Fmodel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @Type(type="text")
    private String content;

    @Column(name="picpath",length = 255)
    private String picPath;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private TopicType topicType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specolumn_id")
    private SpeColumn speColumn;

    @Column
    private Integer pv;//阅读次数

    @Column
    private Integer lv;//点赞次数

    @Column
    private Integer sv;//分享次数

    @Column(name = "c_time")
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createTime;

    @Transient
    private String interTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "create_userid")
    private User createUser;//话题创建人

    @Column
    private Integer state;//状态 0下线 1正常

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "like_user_topic",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> likedUser;//点赞的人

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "share_user_topic",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> sharedUser;//分享的人

    public String getTimePast(){
        return JdbDateUtil.interTime(createTime);
    }
}
