package pers.vast.service.project.util;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import pers.vast.service.project.entity.ProjectPo;
import pers.vast.service.project.entity.ProjectVo;

import java.util.Collection;
import java.util.List;

/**
 * Created by sengzin on 2017/9/17.
 */
public class Projects {

    public static ProjectVo poToVo(ProjectPo po) {
        if (po == null) return null;
//        Vo.setStatus(po.getStatus());
        return ProjectVo.builder()
                .id(po.getId())
                .name(po.getName())
                .introduction(po.getIntroduction())
                .announcement(po.getAnnouncement())
                .coverUrl(po.getCoverUrl())
                .createPerson(po.getCreatePerson())
                .createTime(po.getCreateTime())
                .updateTime(po.getUpdateTime()).build();
    }
//
//    public static List<ProjectVo> poToVo(Collection<ProjectPo> pos) {
//        if (CollectionUtils.isEmpty(pos)) return Lists.newArrayList();
//        List<ProjectVo> Vos = Lists.newArrayListWithCapacity(pos.size());
//        pos.forEach(ele -> {
//            if (ele != null) Vos.add(poToVo(ele));
//        });
//        return Vos;
//    }

}
