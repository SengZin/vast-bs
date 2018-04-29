package pers.vast.service.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标签拥有着类型
 * Created by sengzin on 2018/4/29.
 */
@Getter
@AllArgsConstructor
public enum OwnerType {
    PROJECT(1, "项目")
    ;
    private Integer id;
    private String desc;
}
