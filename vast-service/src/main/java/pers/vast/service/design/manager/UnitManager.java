package pers.vast.service.design.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.design.dao.UnitMapper;
import pers.vast.service.design.entity.UnitPo;

import java.util.List;

/**
 * 单位 manager
 * Created by sengzin on 2018/5/5.
 */
@Service
public class UnitManager {
    @Autowired
    private UnitMapper mapper;

    public void update(UnitPo po, Long operator) {
        po.setUpdatePerson(operator);
        mapper.update(po);
    }

    public void delete(List<Long> ids, Long operator) {
        ids.forEach(id -> mapper.updateEnable(id, operator, "0"));
    }

    public UnitPo query(Long id) {
        return mapper.selectById(id);
    }

}
