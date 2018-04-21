package pers.vast.resource.user;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.vast.service.user.UserService;
import pers.vast.service.user.dao.UserMapper;
import pers.vast.service.user.entity.UserPo;

import java.util.Map;
import java.util.UUID;

/**
 * Created by sengzin on 2018/4/1.
 */
@RestController
@RequestMapping(value = "/user/log")
public class UserLogResource {

    @Autowired
    private UserService userService;

    /**
     * 注册
     */
    @RequestMapping(method = RequestMethod.POST)
    public Object post(@RequestBody UserPo po) {
        Map.Entry<Long, String> loginResult = userService.login(po.getIdentity(), po.getPassword());
        return ImmutableMap.of("userId", loginResult.getKey(), "token", loginResult.getValue());
    }

}
