package pers.vast.resource.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.vast.entity.CollectionVo;
import pers.vast.service.project.entity.ProjectPo;
import pers.vast.service.project.entity.ProjectVo;
import pers.vast.service.project.manager.ProjectManager;
import pers.vast.service.project.util.Projects;
import pers.vast.service.user.manager.UserManager;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by sengzin on 2018/4/5.
 */
@RestController
@RequestMapping(value = "/project")//, headers = {"Access-Control-Allow-Origin:*"})//, consumes = "application/json", produces = "application/json")
public class ProjectResource {
    @Autowired
    private ProjectManager projectManager;
    @Autowired
    private UserManager userManager;

    @RequestMapping(method = RequestMethod.POST)
    public ProjectPo create(@RequestBody ProjectPo po) {
        return projectManager.create(po);
    }

    /**
     * 查询项目列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CollectionVo list(@RequestParam Long userId) {
        Collection<ProjectPo> result = projectManager.listByCreatePerson(userId);
        String userName = userManager.queryNameById(userId);
        Collection<?> list = result.stream().map(po -> {
            ProjectVo vo = Projects.poToVo(po);
            vo.setCreatePersonName(userName);
            return vo;
        }).collect(Collectors.toList());
        return CollectionVo.builder().rows(list).total(list.size()).build();
    }

}
