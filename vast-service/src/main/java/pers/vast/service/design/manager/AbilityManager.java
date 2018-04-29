package pers.vast.service.design.manager;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.design.dao.AbilityMapper;
import pers.vast.service.design.entity.AbilityPo;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by sengzin on 2018/4/22.
 */
@Service
public class AbilityManager {

    @Autowired
    private AbilityMapper mapper;

    /**
     * 创建能力
     */
    public Collection<AbilityPo> create(Collection<AbilityPo> pos) {
        if (pos == null) return null;
        pos.forEach(mapper::insert);
        return pos;
    }

    /**
     * 更新
     */
    public void update(Collection<AbilityPo> pos) {
        if (pos == null) return;
        pos.forEach(mapper::update);
    }

    /**
     * 查项目下的能力定义
     * @param projectId 项目 id
     */
    public List<AbilityPo> listByProject(long projectId) {
        return mapper.list(ImmutableMap.of("projectId", projectId, "enable", 1));
    }

    /**
     * 删除（置为无效）
     */
    public void delete(Collection<Long> ids, long operator) {
        if (ids == null) return;
        ids.forEach(id -> mapper.updateEnable(id, 0, operator));
    }

}
