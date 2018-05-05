package pers.vast.service.design.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * 单位
 * Created by sengzin on 2018/4/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UnitPo {
    private Long id;
    // 单位名称
    private String name;
    // 简介
    private String introduction;
    // 所属项目
    private Long projectId;
    // 能力列表
    private List<AbilityPo> abilities;

    private Integer enable;
    private Long createPerson;
    private Long updatePerson;
    private Date createTime;
    private Date updateTime;
}
