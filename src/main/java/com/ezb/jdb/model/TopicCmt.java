package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * 话题评论
 * author : liufeng
 * create time: 2015/8/7 9:36.
 */
@Data
@Entity
@Table(name = "topiccmt")
@DynamicUpdate
public class TopicCmt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    @JSONField(serialize = false)
    private Topic topic;//所属话题

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_userid")
    private User commentUser;//评论人

    @Column
    @Type(type="text")
    private String content;//评论内容

    @Column(name = "likecount")
    private Integer likeCount;//点赞次数

    @Transient
    private Integer childCmtCount;

    @Column(name = "c_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//评论时间

    @Transient
    private String timePast;//

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private TopicCmt parentTopicCmt;//父评论

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
