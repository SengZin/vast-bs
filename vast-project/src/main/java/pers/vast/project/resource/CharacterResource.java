package pers.vast.project.resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pers.vast.project.dao.mapper.CharacterMapper;
import pers.vast.project.entity.*;

import java.util.List;
import java.util.Map;

/**
 * Created by sengzin on 2017/9/17.
 */
@RestController
@RequestMapping("/project/character")
public class CharacterResource {

    @Autowired
    private CharacterMapper characterMapper;

    @RequestMapping(method = RequestMethod.GET)
    public CharacterVO get(int id) {
        return Characters.poToVO(characterMapper.selectById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public CharacterVO post(@RequestBody CharacterVO vo) {
        CharacterPO po =Characters.voToPO(vo);
        characterMapper.insert(po);
        return Characters.poToVO(characterMapper.selectById(po.getId()));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Object getList(int projectId) {
        List<CharacterVO> list =Characters.poToVO(characterMapper.selectListByProjectId(projectId));
        Map<String, Object> ret = Maps.newHashMap();
        ret.put("rows", list == null ? Lists.newArrayList() : list);
        ret.put("total", list == null ? 0 : list.size());
        return ret;
    }

}
