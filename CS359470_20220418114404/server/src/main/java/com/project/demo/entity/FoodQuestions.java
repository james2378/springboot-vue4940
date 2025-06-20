package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *美食提问：(FoodQuestions)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "FoodQuestions")
public class FoodQuestions implements Serializable {

    //FoodQuestions编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_questions_id")
    private Integer food_questions_id;
    // 问题标题
    @Basic
    private String question_title;
    // 美食类型
    @Basic
    private String food_type;
    // 美食封面
    @Basic
    private String gourmet_cover;
    // 问题内容
    @Basic
    private String question_content;
    // 点击数
    @Basic
    private Integer hits;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
