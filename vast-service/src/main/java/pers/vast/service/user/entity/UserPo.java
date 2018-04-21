package pers.vast.service.user.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by sengzin on 2017/9/11.
 */
@Getter
@Setter
@ToString
public class UserPo {

    private Long id;

    private String account;

    private String password;

    private String name;

    private String email;

    private String tel;

    private Integer roleId;

    private Date registerAt;

    private Date updateAt;

    /** 其他 */

    private String identity;

}
