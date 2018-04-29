package pers.vast.service.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        pos.stream().filter(po -> po.getTags() != null).forEach(po -> tagManager.tagging(TagScope.UNIT_ABILITY, po.getId().toString(), operator, po.getTags()));
    }

    /**
     * 更新
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(Collection<AbilityPo> pos, Long operator) {
        if (pos == null) return;
        pos.forEach(po -> po.setUpdatePerson(operator));
        manager.update(pos);
        // 打标签
        pos.stream().filter(po -> po.getTags() != null).forEach(po -> tagManager.tagging(TagScope.UNIT_ABILITY, po.getId().toString(), operator, po.getTags()));
    }

    /**
     * 查询列表
     */
    public List<AbilityPo> list(Long projectId) {
        List<AbilityPo> abilities = manager.listByProject(projectId);
        abilities.forEach(ability -> ability.setTags(tagManager.listName(TagScope.UNIT_ABILITY, ability.getId().toString())));
        return abilities;
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Collection<Long> ids, Long operator) {
        manager.delete(ids, operator);
    }

}
