package pers.vast.service.design.entity;

import lombok.*;

/**
 * 单位能力
 * Created by sengzin on 2018/4/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbilityVo {
    private Long id;
    // 能力名称
    private String name;
    // 能力类型 1：数值型
    private Integer type;
}
