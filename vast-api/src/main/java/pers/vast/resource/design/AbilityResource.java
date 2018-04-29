package pers.vast.resource.design;

import com.google.common.base.Preconditions;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.vast.entity.CollectionVo;
import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.util.Tags;
import pers.vast.service.design.AbilityService;
import pers.vast.service.design.entity.AbilityPo;
import pers.vast.service.design.entity.AbilityTemplatePo;
import pers.vast.service.design.util.Abilities;

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
        service.update(co.getAbilities(), userId, co.getProjectId());
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CollectionVo list(@RequestParam("projectId") Long projectId) {
        if (projectId == null) throw new RuntimeException("项目 id 不可为空");
        List<AbilityPo> abilities = service.list(projectId);
        return CollectionVo.builder().rows(Abilities.poToVo(abilities)).total(abilities.size()).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("ids") String idsParam, @RequestParam("userId") Long userId, @Param("projectId") Long projectId) {
        if (userId == null) throw new RuntimeException("操作人不可为空");
        if (idsParam == null) throw new RuntimeException("ids 不可为空");
        Preconditions.checkNotNull(projectId, "项目 id 不可为空");
        service.delete(Stream.of(idsParam.split(",")).map(Long::parseLong).collect(Collectors.toList()), userId);
    }

    /** 单位能力标签 */

    /**
     * 新建标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.POST)
    public void createTag(@RequestBody AbilityPo co, @RequestParam("userId") Long userId) {
        if (userId == null) throw new RuntimeException("操作人不可为空");
        if (co == null) throw new RuntimeException("data 不可为空");
        if (co.getProjectId() == null) throw new RuntimeException("项目 id 不可为空");
        service.createTag(co.getTags(), userId, co.getProjectId());
    }

    /**
     * 编辑标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.PUT)
    public void updateTag(@RequestBody AbilityPo co, @RequestParam("userId") Long userId) {
        Preconditions.checkNotNull(userId, "操作人不可为空");
        Preconditions.checkNotNull(co, "data 不可为空");
        Preconditions.checkNotNull(co.getProjectId(), "项目 id 不可为空");
        service.updateTag(co.getTags(), userId, co.getProjectId());
    }

    /**
     * 查询标签列表
     */
    @RequestMapping(value = "/tag/list", method = RequestMethod.GET)
    public CollectionVo listTag(@RequestParam("projectId") Long projectId) {
        Preconditions.checkNotNull(projectId, "项目 id 不可为空");
        List<TagPo> tags = service.listTag(projectId);
        return CollectionVo.builder().rows(Tags.poToVo(tags)).total(tags.size()).build();
    }

    /**
     * 删除标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.DELETE)
    public void deleteTag(@RequestParam("ids") String idsParam, @RequestParam("userId") Long userId, @Param("projectId") Long projectId) {
        if (userId == null) throw new RuntimeException("操作人不可为空");
        if (idsParam == null) throw new RuntimeException("ids 不可为空");
        Preconditions.checkNotNull(projectId, "项目 id 不可为空");
        service.deleteTag(Stream.of(idsParam.split(",")).map(Long::parseLong).collect(Collectors.toList()), userId, projectId);
    }
}
