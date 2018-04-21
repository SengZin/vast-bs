package pers.vast.service.user.util;

import pers.vast.service.user.entity.UserPo;
import pers.vast.service.user.entity.UserVo;

/**
 * Created by sengzin on 2017/9/14.
 */
public class Users {

    public static UserVo poToVo(UserPo po) {
        if (po == null) return null;
        UserVo vo = new UserVo();
        vo.setId(po.getId());
        vo.setAccount(po.getAccount());
        vo.setPassword(po.getPassword());
        vo.setName(po.getName());
        vo.setEmail(po.getEmail());
        vo.setRoleId(po.getRoleId());
        vo.setRoleName("超级管理员");
        vo.setRegisterAt(po.getRegisterAt());
        vo.setUpdateAt(po.getUpdateAt());
        return vo;
    }

}
