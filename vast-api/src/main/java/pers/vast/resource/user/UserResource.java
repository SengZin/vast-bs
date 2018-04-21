package pers.vast.resource.user;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.vast.resource.BaseResource;
import pers.vast.service.user.UserService;
import pers.vast.service.user.dao.UserMapper;
import pers.vast.service.user.entity.UserPo;
import pers.vast.service.user.entity.UserVo;
import pers.vast.service.user.manager.UserManager;
import pers.vast.service.user.util.Users;

import java.util.Collections;
import java.util.Map;

/**
 * Created by sengzin on 2017/9/11.
 */
@RestController
@RequestMapping(value = "/user")//, headers = {"Access-Control-Allow-Origin:*"})//, consumes = "application/json", produces = "application/json")
public class UserResource extends BaseResource {

    @Autowired
    private UserService userService;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询
     */
    @RequestMapping(method = RequestMethod.GET)
    public UserVo get(@RequestParam Long id) {
        UserPo po = userMapper.selectById(id);
        return Users.poToVo(po);
    }

    /**
     * 注册
     */
    @RequestMapping(method = RequestMethod.POST)
    public UserVo register(@RequestBody UserPo po) {
        userMapper.insert(po);
        return Users.poToVo(userMapper.selectById(po.getId()));
    }

    /**
     * 注册校验
     */
    @RequestMapping(value = "/precondition", method = RequestMethod.POST)
    public Map<String, String> preconditionRegister(@RequestBody UserPo po) {
        if (userService.isConflict(po)) {
            response.setStatus(409);
            return ImmutableMap.of("message", "字段重复");
        }
        return Collections.EMPTY_MAP;
    }

}
