package pers.vast.resource.design;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.vast.core.util.JsonUtils;
import pers.vast.entity.CollectionVo;
import pers.vast.service.design.UnitService;
import pers.vast.service.design.entity.UnitPo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 单位接口
 * Created by sengzin on 2018/5/2.
 */
@RestController
@RequestMapping(value = "/unit")
public class UnitResource {
    @Autowired
    private UnitService service;

    /**
     * 创建单位
     * 创建时刻携带能力值，一并保存
     */
    @RequestMapping(method = RequestMethod.POST)
    public void create(@RequestBody UnitPo po, @RequestParam("userId") Long userId) {
        Preconditions.checkNotNull(po, "data 不可为空");
        Preconditions.checkNotNull(po.getProjectId(), "projectId 不可为空");
        Preconditions.checkNotNull(userId, "userId 不可为空");
        service.create(po, userId);
    }

    /**
     * 更新单位
     */
    @RequestMapping(method = RequestMethod.PUT)
    public void update(@RequestBody UnitPo po, @RequestParam("userId") Long userId) {
        Preconditions.checkNotNull(po, "data 不可为空");
        Preconditions.checkNotNull(po.getProjectId(), "projectId 不可为空");
        Preconditions.checkNotNull(userId, "userId 不可为空");
        // 能力数值一次全更新，直接覆盖老数据
        service.update(po, userId);
    }

    /**
     * 查单个单位
     */
    @RequestMapping(method = RequestMethod.GET)
    public UnitPo get(@RequestParam("id") Long id) {
        Preconditions.checkNotNull(id, "id 不可为空");
        return service.query(id);
    }

    /**
     * 删除单位，先逻辑删除、再强删除
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("ids") String ids, @RequestParam(value = "userId") Long userId) {
        Preconditions.checkNotNull(ids, "ids 不可为空");
        Preconditions.checkNotNull(userId, "userId 不可为空");
        service.delete(Stream.of(ids.split(",")).map(Long::parseLong).collect(Collectors.toList()), userId);
    }

    /**
     * 单位列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CollectionVo list(@RequestParam("projectId") Long projectId) {
        Preconditions.checkNotNull(projectId, "projectId 不可为空");
        List<UnitPo> pos = service.list(projectId);
        return CollectionVo.builder().rows(pos).total(pos.size()).build();
    }
}
