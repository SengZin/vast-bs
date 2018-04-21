package pers.vast.project.resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.vast.project.dao.mapper.AbilityDefineMapper;
import pers.vast.project.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sengzin on 2017/9/17.
 */
@RestController
@RequestMapping("/project/character/abilitydefine")
public class AbilityDefineResource {

    @Autowired
    private AbilityDefineMapper abilityDefineMapper;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getList(int projectId) {
        List<AbilityDefineVO> list = AbilityDefines.poToVO(abilityDefineMapper.selectByProjectId(projectId));
        Map<String, Object> ret = Maps.newHashMap();
        ret.put("rows", list == null ? Lists.newArrayList() : list);
        ret.put("total", list == null ? 0 : list.size());
        return ret;
    }

    @RequestMapping(method = RequestMethod.POST)
    public AbilityDefineVO post(@RequestBody AbilityDefinePO po) {
        abilityDefineMapper.insert(po);
        return AbilityDefines.poToVO(abilityDefineMapper.selectById(po.getId()));
    }

}
