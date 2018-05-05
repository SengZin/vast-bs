package pers.vast.service.design.entity;

import lombok.*;

import java.util.Collection;
import java.util.Date;

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
public class UnitVo {
    private Long id;
    // 单位名称
    private String name;
    // 简介
    private String introduction;

    private Long createPerson;
    private Date createTime;
    // 能力模板
    // private AbilityTemplateVo abilityTemplate;
    // 能力列表
    private Collection<AbilityVo> abilities;
    // 特性列表
    // private Collection<FeatureVo> features;
}
