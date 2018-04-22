package pers.vast.service.design.entity;

import lombok.*;

import java.util.Date;

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
    // 能力类型
    private Integer type;
    // 能力名称
    private String name;
    // 所属项目 id
    private Long projectId;

    private Long createPerson;
    private Long updatePerson;
    private Date createTime;
    private Date updateTime;
    // 能力模式/规则
    // todo 看看前端是怎么判断的、结合前后端选个适合的
    // 可参考 json schema
    // private Object schema;
}
