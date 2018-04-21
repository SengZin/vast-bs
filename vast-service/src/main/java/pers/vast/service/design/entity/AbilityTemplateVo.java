package pers.vast.service.design.entity;

import lombok.*;

import java.util.Collection;

/**
 * 能力模板
 * Created by sengzin on 2018/4/17.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbilityTemplateVo {
    private Long id;
    // 模板名称
    private String name;
    // 模板能力列表
    private Collection<AbilityVo> abilities;

}
