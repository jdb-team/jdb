package com.ezb.jdb.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * 举报
 * author : liufeng
 * create time:2015/8/13 14:09
 */
@Data
@Entity
@Table(name = "inform")
@DynamicUpdate
public class Inform {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 8)
    private String type;

    @Column(name="asso_id",length = 32)
    private Integer assoId;//关联id

    @Column(name="asso_name",length = 64)
    private String assoName;//关联的某人 or 某活动名称

    @Column(length = 16)
    private String reason;//被举报的原因

    @Column(name="otherinfo")
    @Type(type="text")
    private String otherInfo;//其他说明

    @Column(length = 64)
    private String viewurl;//查看页面

    @Column
    private Integer state;//状态 (1已处理，0待处理)

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "create_userid")
    private User createUser;//举报人

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inform_userid")
    private User informUser;//被举报人

    @Column(name="c_time")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//举报时间

}
