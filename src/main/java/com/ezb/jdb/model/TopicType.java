package com.ezb.jdb.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 话题类型
 * author : liufeng
 * create time:2015/11/11 10:18
 */
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "topictype")
@DynamicUpdate
public class TopicType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;             //id
    @Column(name = "typename", length = 32)
    private String typeName;        //类别名称
    @Column(name = "typedesc", length = 256)
    private String typeDesc;        //类别描述
}
