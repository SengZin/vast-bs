package pers.vast.service.design.util;

import pers.vast.service.design.entity.AbilityPo;
import pers.vast.service.design.entity.AbilityVo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 单位能力工具类
 * Created by sengzin on 2018/4/22.
 */
public class Abilities {

    private Abilities() {}

    public static List<AbilityVo> poToVo(Collection<AbilityPo> pos) {
        if (pos == null) return null;
        return pos.stream().map(Abilities::poToVo).collect(Collectors.toList());
    }

    public static AbilityVo poToVo(AbilityPo po) {
        if (po == null) return null;
        return AbilityVo.builder()
                .id(po.getId())
                .name(po.getName())
                .type(po.getType())
                .tags(po.getTags()).build();
    }

}
