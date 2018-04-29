package pers.vast.service.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标签使用域 code
 * Created by sengzin on 2018/4/29.
 */
@Getter
@AllArgsConstructor
public enum TagScope {
    UNIT_ABILITY(1, "单位能力")
    ;
    private int id;
    private String desc;

}
