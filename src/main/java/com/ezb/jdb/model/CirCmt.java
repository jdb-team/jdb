package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 圈子评论
 * author : liufeng
 * create time: 2015/8/5 13:39.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "circmt")
public class CirCmt extends Fmodel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @Type(type="text")
    private String content;//评论内容

    @Column(name = "likecount")
    private Integer likeCount;//点赞次数

    @Column(name="c_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//评论时间

    @ManyToOne
    @JoinColumn(name = "comment_userid")
    private User commentUser;//评论人

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "circle_id")
    @JSONField(serialize = false)
    private Circle circle;//所属圈子

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private CirCmt parentCirCmt;//父评论

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id")
    @JSONField(serialize = false)
    private Set<CirCmt> childCirCmt;

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
