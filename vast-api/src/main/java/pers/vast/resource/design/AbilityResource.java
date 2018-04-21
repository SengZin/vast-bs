package pers.vast.resource.design;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.vast.service.design.entity.AbilityVo;

/**
 * 单位能力
 * Created by sengzin on 2018/4/17.
 */
@RestController
@RequestMapping(value = "/unit/ability")
public class AbilityResource {

    @RequestMapping(method = RequestMethod.POST)
    public AbilityVo create() {
        return new AbilityVo();
    }

}
