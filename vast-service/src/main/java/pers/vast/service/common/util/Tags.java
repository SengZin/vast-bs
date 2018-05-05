package pers.vast.service.common.util;

import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.entity.TagVo;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sengzin on 2018/4/29.
 */
public class Tags {
    private Tags() {}

    public static List<TagVo> poToVo(Collection<TagPo> pos) {
        if (pos == null) return null;
        return pos.stream().map(Tags::poToVo).collect(Collectors.toList());
    }

    public static TagVo poToVo(TagPo po) {
        if (po == null) return null;
        return TagVo.builder()
                .id(po.getId())
                .name(po.getName())
                .color(po.getColor()).build();
    }
}
