package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *美食达人：(GourmetMaster)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "GourmetMaster")
public class GourmetMaster implements Serializable {

    //GourmetMaster编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gourmet_master_id")
    private Integer gourmet_master_id;
    // 达人姓名
    @Basic
    private String name_of_talent;
    // 达人头像
    @Basic
    private String avatar_of_talent;
    // 达人地址
    @Basic
    private String talent_address;
    // 拿手菜系
    @Basic
    private String specialty_cuisine;
    // 达人简介
    @Basic
    private String introduction_to_talent;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
