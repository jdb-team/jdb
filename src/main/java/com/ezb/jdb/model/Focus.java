package com.ezb.jdb.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 专栏
 * author : liufeng
 * create time:2015/8/20 9:40
 */
@Data
@Entity
@Table(name = "focus")
@DynamicUpdate
public class Focus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 64)
    private String title;

    @Column
    @Type(type="text")
    private String info;

    @Column(length = 64)
    private String picpath;//图片路径

    @Column(length = 16)
    private String type;//活动还是话题

    @Column(length = 32)
    private Integer refId;//绑定id

    @Column(name = "viewurl", length = 32)
    private String viewurl;

    @Column
    private Integer position;//位置 第几位焦点图

}
