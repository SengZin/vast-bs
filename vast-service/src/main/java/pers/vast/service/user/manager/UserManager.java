package pers.vast.service.user.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.user.dao.UserMapper;
import pers.vast.service.user.entity.UserPo;

/**
 * Created by sengzin on 2018/4/5.
 */
@Service
public class UserManager {

    @Autowired
    private UserMapper userMapper;

    /**
     * 通用查询
     */
    public UserPo query(UserPo queryObj) {
        return userMapper.selectByPo(queryObj);
    }

    public String queryNameById(Long id) {
        UserPo po = userMapper.selectById(id);
        return po != null ? po.getName() : "";
    }

    /**
     * 用账号唯一性查询
     */
    public UserPo queryByIdentity(String identity) {
        return userMapper.selectByIdentity(identity);
    }

}
