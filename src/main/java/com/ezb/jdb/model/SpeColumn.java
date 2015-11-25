package com.ezb.jdb.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 专栏
 * author : liufeng
 * create time:2015/11/11 10:37
 */
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "specolumn")
@DynamicUpdate
public class SpeColumn {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;             //id
    @Column(name = "columnname", length = 32)
    private String columnName;      //专栏名称
    @Column(name = "columndesc", length = 256)
    private String columnDesc;      //专栏描述
}
