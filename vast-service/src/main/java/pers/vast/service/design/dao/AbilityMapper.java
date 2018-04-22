package pers.vast.service.design.dao;

import org.apache.ibatis.annotations.*;
import pers.vast.service.design.entity.AbilityPo;

import java.util.List;
import java.util.Map;

/**
 * Created by sengzin on 2017/9/17.
 */
@Mapper
public interface AbilityMapper {

    @Select("select * from unit_ability where id = #{id}")
    AbilityPo selectById(long id);

    @Insert("insert into unit_ability (type, name, project_id, create_person, update_person, create_time) " +
            "values (#{type}, #{name}, #{projectId}, #{createPerson}, #{createPerson}, #{createTime})")
    @Options(useGeneratedKeys = true)
    int insert(AbilityPo po);

    @Update("UPDATE unit_ability SET type = #{type}, name = #{name}, update_person = #{updatePerson}" +
            "WHERE id = #{id}")
    int update(AbilityPo po);

    @Update("UPDATE SET enable = #{enable}, update_person = #{operator} WHERE id = #{id}")
    int updateEnable(@Param("id") long id, @Param("enable") int enable, @Param("operator") long operator);

    List<AbilityPo> list(Map param);

}
