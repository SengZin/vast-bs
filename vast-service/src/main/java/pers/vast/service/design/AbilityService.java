package pers.vast.service.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.vast.service.common.entity.OwnerType;
import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.entity.TagScope;
import pers.vast.service.common.manager.TagManager;
import pers.vast.service.design.entity.AbilityPo;
import pers.vast.service.design.manager.AbilityManager;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 单位能力
 * Created by sengzin on 2018/4/22.
 */
@Service
public class AbilityService {
    @Autowired
    private AbilityManager manager;
    @Autowired
    private TagManager tagManager;

    /**
     * 创建能力
     */
    @Transactional(rollbackFor = Throwable.class)
    public void create(Collection<AbilityPo> pos, Long operator, Long projectId) {
        if (pos == null) return;
        Date createTime = new Date();
        pos.forEach(po -> {
            po.setProjectId(projectId);
            po.setCreatePerson(operator);
            po.setCreateTime(createTime);
        });
        pos = manager.create(pos);
        // 打标签
        pos.stream().filter(po -> po.getTagIds() != null)
                .forEach(po -> tagManager.retag(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY, operator, po.getTagIds(), po.getId().toString()));
    }

    /**
     * 更新
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(Collection<AbilityPo> pos, Long operator, Long projectId) {
        if (pos == null) return;
        pos.forEach(po -> po.setUpdatePerson(operator));
        manager.update(pos);
        // 打标签
        pos.stream().filter(po -> po.getTagIds() != null).forEach(po -> tagManager.retag(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY, operator, po.getTagIds(), po.getId().toString()));
    }

    /**
     * 查询列表
     */
    public List<AbilityPo> list(Long projectId) {
        List<AbilityPo> abilities = manager.listByProject(projectId);
        abilities.forEach(ability -> ability.setTags(tagManager.list(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY, ability.getId().toString())));
        return abilities;
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Collection<Long> ids, Long operator) {
        manager.delete(ids, operator);
    }

    /**
     * 创建项目下的能力标签
     */
    @Transactional(rollbackFor= Throwable.class)
    public void createTag(List<TagPo> tagPos, Long operator, Long projectId) {
        tagManager.create(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY, operator, tagPos);
    }

    /**
     * 更新能力标签
     */
    @Transactional(rollbackFor= Throwable.class)
    public void updateTag(List<TagPo> tagPos, Long operator, Long projectId) {
        tagManager.update(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY, operator, tagPos);
    }

    /**
     * 查询项目下的能力标签
     */
    public List<TagPo> listTag(Long projectId) {
        return tagManager.list(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY);
    }

    /**
     * 删除标签
     */
    public void deleteTag(List<Long> tagIds, Long operator, Long projectId) {
        tagManager.delete(OwnerType.PROJECT, projectId.toString(), TagScope.UNIT_ABILITY, operator, tagIds);
    }
}
