package pers.vast.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.user.entity.UserPo;
import pers.vast.service.user.manager.UserManager;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by sengzin on 2018/4/1.
 */
@Service
public class UserService {

    @Autowired
    private UserManager userManager;

    /**
     * 是否冲突
     */
    public boolean isConflict(UserPo po) {
        return  userManager.query(po) != null;
    }

    /**
     * 注册
     */
    public void registe() {

    }

    /**
     * 查询
     */
    public void query() {

    }

    /**
     * 登陆
     * @return 返回 token
     */
    public Map.Entry<Long, String> login(String identity, String password) {
        UserPo po = userManager.queryByIdentity(identity);
        if (po == null) throw new RuntimeException("账号不存在");
        if (!Objects.equals(password, po.getPassword())) throw new RuntimeException("密码错误");
        return new AbstractMap.SimpleImmutableEntry(po.getId(), UUID.randomUUID().toString().replaceAll("-", ""));
    }

    /**
     * 登出/注销
     */
    public void logout() {

    }

}
