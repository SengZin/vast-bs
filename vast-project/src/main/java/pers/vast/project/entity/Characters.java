package pers.vast.project.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by sengzin on 2017/9/17.
 */
@Slf4j
public class Characters {

    public static final ObjectMapper _OM = new ObjectMapper();

    // to vo

    public static CharacterVO poToVO(CharacterPO po) {
        if (po == null) return null;
        CharacterVO vo = new CharacterVO();
        vo.setId(po.getId());
        vo.setProjectId(po.getProjectId());
        vo.setFirstName(po.getFirstName());
        vo.setLastName(po.getLastName());
        vo.setMale(po.getMale());
        try {
            vo.setAbility(_OM.readTree(po.getAbility()));
        } catch (IOException e) {
            log.error("Parse json string failed, ", e);
        }
        return vo;
    }

    public static List<CharacterVO> poToVO(Collection<CharacterPO> pos) {
        if (CollectionUtils.isEmpty(pos)) return Lists.newArrayList();
        List<CharacterVO> vos = Lists.newArrayListWithCapacity(pos.size());
        pos.forEach(ele -> {
            if (ele != null) vos.add(poToVO(ele));
        });
        return vos;
    }

    // to po

    public static CharacterPO voToPO(CharacterVO vo) {
        if (vo == null) return null;
        CharacterPO po = new CharacterPO();
        po.setId(vo.getId());
        po.setProjectId(vo.getProjectId());
        po.setFirstName(vo.getFirstName());
        po.setLastName(vo.getLastName());
        po.setMale(vo.getMale());
        po.setAbility(
                vo.getAbility() == null ? "" : vo.getAbility().toString()
        );
        return po;
    }
}
