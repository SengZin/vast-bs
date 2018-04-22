package pers.vast.service.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.vast.service.design.entity.AbilityPo;
import pers.vast.service.design.manager.AbilityManager;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by sengzin on 2018/4/22.
 */
@Service
public class AbilityService {

    @Autowired
    private AbilityManager manager;

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
        manager.create(pos);
    }

    /**
     * 更新
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(Collection<AbilityPo> pos, Long operator) {
        if (pos == null) return;
        pos.forEach(po -> po.setUpdatePerson(operator));
        manager.update(pos);
    }

    /**
     * 查询列表
     */
    public List<AbilityPo> list(Long projectId) {
        return manager.listByProject(projectId);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Collection<Long> ids, Long operator) {
        manager.delete(ids, operator);
    }

}
