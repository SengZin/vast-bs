package pers.vast.service.design.entity;

import lombok.*;

/**
 * 单位能力
 * Created by sengzin on 2018/4/19.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbilityPo {
    private Long id;
    // 能力名称
    private String name;
    // 能力类型
    private Integer type;
    // 能力模式/规则
    // todo 看看前端是怎么判断的、结合前后端选个适合的
    // 可参考 json schema
    private Object schema;
}
