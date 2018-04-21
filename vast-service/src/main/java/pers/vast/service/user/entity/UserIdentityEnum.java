package pers.vast.service.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by sengzin on 2018/4/5.
 */
@Getter
@AllArgsConstructor
public enum UserIdentityEnum {

    ACCOUNT("account", "账号"),
    EMAIL("email", "邮箱"),
    TEL("tel", "电话号")
    ;
    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 内容
     */
    private String comment;

}
