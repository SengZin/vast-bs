package pers.vast.service.design.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 能力类型
 * Created by sengzin on 2018/4/20.
 */
@Getter
@AllArgsConstructor
public enum AbilityType {
    CONST(1, "常量型"),
    // 如：行动力，会随时间恢复
    VARIABLE(2, "变量型")
    ;
    private Integer id;

    private String desc;
}
