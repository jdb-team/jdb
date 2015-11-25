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
 * 活动评论
 * author : liufeng
 * create time: 2015/8/5 13:39.
 */
@Data
@Entity
@Table(name = "atvcmt")
@DynamicUpdate
public class AtvCmt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    @JSONField(serialize = false)
    private Activity activity;//所属活动

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_userid")
    private User commentUser;//评论人

    @Column
    @Type(type="text")
    private String content;//评论内容

    @Column(name="c_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//评论时间

    @Column(name = "likecount")
    private Integer likeCount;//点赞次数

    @Transient
    private String timePast;//

    @Transient
    private Integer childCmtCount;//子评论条数

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private AtvCmt parentAtvCmt;//父评论

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
