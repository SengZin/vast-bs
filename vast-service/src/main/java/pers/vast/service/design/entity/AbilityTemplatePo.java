package pers.vast.service.design.entity;

import lombok.*;

import java.util.List;

/**
 * 单位能力模板
 * Created by sengzin on 2018/4/19.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbilityTemplatePo {
    private Long id;
    // 模板名称
    private String name;
    // 所属项目 id
    private Long projectId;

    /** 他属性 */

    // 能力项
    private List<AbilityPo> abilities;

}
