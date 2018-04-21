package pers.vast.service.project.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.vast.service.project.dao.ProjectMapper;
import pers.vast.service.project.entity.ProjectPo;

import java.util.Date;
import java.util.List;

/**
 * Created by sengzin on 2018/4/5.
 */
@Service
public class ProjectManager {

    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 创建项目
     */
    public ProjectPo create(ProjectPo po) {
        po.setCreateTime(new Date());
        if (po.getAnnouncement() == null) po.setAnnouncement("");
        if (po.getIntroduction() == null) po.setIntroduction("");
        if (po.getCoverUrl() == null) po.setCoverUrl("");
        if (projectMapper.insert(po) < 1) throw new RuntimeException("创建项目失败");
        po = projectMapper.selectById(po.getId());
        return po;
    }

    /**
     * 查询用户下的项目信息
     */
    public List<ProjectPo> listByCreatePerson(long userId) {
        return projectMapper.listByCreatePerson(userId);
    }

}
