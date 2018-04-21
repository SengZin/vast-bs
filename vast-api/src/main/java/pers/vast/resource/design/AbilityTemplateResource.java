package pers.vast.resource.design;

import org.springframework.web.bind.annotation.*;
import pers.vast.service.design.entity.AbilityTemplatePo;

/**
 * 能力值模板
 * Created by sengzin on 2018/4/19.
 */
@RestController
@RequestMapping(value = "/unit/ability/template")
public class AbilityTemplateResource {

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody AbilityTemplatePo abtTmpPo) {
        System.out.println(abtTmpPo);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody AbilityTemplatePo abtTmpPo) {
        System.out.println(abtTmpPo);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam Long id) {
        // todo 校验请求合理性
    }

}
