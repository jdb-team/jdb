package com.ezb.jdb.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 下线通知
 * author : liufeng
 * create time:2015/11/12 13:31
 */
@Data
@Entity
@Table(name = "offlinenotice")
public class OfflineNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;         //主键
    @Column(name = "c1", length = 32)
    private String c1;          //模板填充位置1
    @Column(name = "c2", length = 32)
    private String c2;          //模板填充位置2
    @Column(name = "c3", length = 32)
    private String c3;          //模板填充位置3
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id")
    private Admin admin;        //下线操作人
    @Column(name = "c_time")
    private Date createTime;   //下线时间
}
