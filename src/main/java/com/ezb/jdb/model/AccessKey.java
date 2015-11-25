package com.ezb.jdb.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 访问控制key
 * author : liufeng
 * create time:2015/9/21 13:42
 */
@Data
@Entity
@Table(name = "accesskey")
public class AccessKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "accesskey", length = 256)
    private String accessKey;

    @Column(length = 32)
    private String type;

    @Column(name = "c_time")
    private Date createTime;
}
