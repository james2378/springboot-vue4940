package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *美食好店：(GoodFoodShop)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "GoodFoodShop")
public class GoodFoodShop implements Serializable {

    //GoodFoodShop编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_food_shop_id")
    private Integer good_food_shop_id;
    // 店铺名称
    @Basic
    private String store_name;
    // 美食类型
    @Basic
    private String food_type;
    // 店铺门面
    @Basic
    private String shop_facade;
    // 店铺地址
    @Basic
    private String store_address;
    // 热推菜品
    @Basic
    private String hot_dishes;
    // 店铺详情
    @Basic
    private String shop_details;
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
