package pers.vast.service.design;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.vast.service.design.dao.UnitMapper;
import pers.vast.service.design.entity.UnitPo;
import pers.vast.service.design.manager.UnitManager;

import java.util.List;


/**
 * 单位
 * Created by sengzin on 2018/4/17.
 */
@Service
public class UnitService {
    @Autowired
    private UnitManager manager;
    // todo 迁到 manager 中
    @Autowired
    private UnitMapper mapper;
    /**
     * 创建
     */
    @Transactional(rollbackFor = Throwable.class)
    public void create(UnitPo po, Long operator) {
        if (po == null) return;
        // 校验 abilities 数据准确性
        po.setCreatePerson(operator);
        mapper.insert(po);
    }

    /**
     * 更新
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(UnitPo po, Long operator) {
        manager.update(po, operator);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(List<Long> ids, Long operator) {
        manager.delete(ids, operator);
    }

    /**
     * 查询
     */
    public UnitPo query(Long id) {
        return manager.query(id);
    }

    /**
     * 列表
     */
    public List<UnitPo> list(Long projectId) {
        return mapper.listByProjectId(projectId);
    }

}
