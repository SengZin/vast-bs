package pers.vast.project.entity;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by sengzin on 2017/9/17.
 */
public class AbilityDefines {

    public static AbilityDefineVO poToVO(AbilityDefinePO po) {
        if (po == null) return null;
        AbilityDefineVO vo = new AbilityDefineVO();
        vo.setId(po.getId());
        vo.setProjectId(po.getProjectId());
        vo.setName(po.getName());
        vo.setMin(po.getMin());
        vo.setMax(po.getMax());
        vo.setStatus(po.getStatus());
        vo.setEnable(po.getEnable());
        return vo;
    }

    public static List<AbilityDefineVO> poToVO(Collection<AbilityDefinePO> pos) {
        if (CollectionUtils.isEmpty(pos)) return Lists.newArrayList();
        List<AbilityDefineVO> vos = Lists.newArrayListWithCapacity(pos.size());
        pos.forEach(ele -> {
            if (ele != null) vos.add(poToVO(ele));
        });
        return vos;
    }
}
