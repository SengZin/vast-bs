package pers.vast.service.project.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 项目
 * Created by sengzin on 2017/9/17.
 */
@Getter
@Setter
@ToString
public class ProjectPo {
    private Long id;
    // 项目名
    private String name;
    // 介绍
    private String introduction;
    // 通知
    private String announcement;
    // 封面图
    private String coverUrl;

    private Byte status;
    /** 通用属性 */
    private Long createPerson;
    private Long updatePerson;
    private Date createTime;
    private Date updateTime;
}
