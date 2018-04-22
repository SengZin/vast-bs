package pers.vast.resource.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.vast.entity.CollectionVo;
import pers.vast.service.design.AbilityService;
import pers.vast.service.design.entity.AbilityPo;
import pers.vast.service.design.entity.AbilityTemplatePo;
import pers.vast.service.design.util.Abilities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 单位能力
 * Created by sengzin on 2018/4/17.
 */
@RestController
@RequestMapping(value = "/unit/ability")
public class AbilityResource {

    @Autowired
    private AbilityService service;

    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody AbilityTemplatePo co, @RequestParam("userId") Long userId) {
        if (userId == null) throw new RuntimeException("操作人不可为空");
        if (co == null) throw new RuntimeException("data 不可为空");
        if (co.getProjectId() == null) throw new RuntimeException("项目 id 不可为空");
        service.create(co.getAbilities(), userId, co.getProjectId());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody AbilityTemplatePo co, @RequestParam("userId") Long userId) {
        if (userId == null) throw new RuntimeException("操作人不可为空");
        if (co == null) throw new RuntimeException("data 不可为空");
        service.update(co.getAbilities(), userId);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CollectionVo list(@RequestParam("projectId") Long projectId) {
        if (projectId == null) throw new RuntimeException("项目 id 不可为空");
        List<AbilityPo> abilities = service.list(projectId);
        return CollectionVo.builder().rows(Abilities.poToVo(abilities)).total(abilities.size()).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("ids") String idsParam, @RequestParam("userId") Long userId) {
        if (userId == null) throw new RuntimeException("操作人不可为空");
        if (idsParam == null) throw new RuntimeException("ids 不可为空");
        service.delete(Stream.of(idsParam.split(",")).map(Long::parseLong).collect(Collectors.toList()), userId);
    }

}
