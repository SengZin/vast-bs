package pers.vast.service.design.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import pers.vast.core.dao.JsonListTypeHandler;
import pers.vast.service.design.entity.AbilityPo;
import pers.vast.service.design.entity.UnitPo;

import java.util.List;

/**
 * 单位
 * Created by sengzin on 2018/5/5.
 */
@Mapper
public interface UnitMapper {
    @Insert("insert into unit(name, introduction, project_id, abilities, create_person, update_person) " +
            "values(#{name}, #{introduction}, #{projectId}, #{abilities, typeHandler=pers.vast.core.dao.JsonTypeHandler}, " +
            "#{createPerson}, #{createPerson})")
    int insert(UnitPo po);

    @Update("update unit set name = #{name}, introduction = #{introduction}, project_id = #{projectId}, abilities = #{abilities, typeHandler=pers.vast.core.dao.JsonTypeHandler}, " +
            "update_person = #{updatePerson} where id = #{id}")
    int update(UnitPo po);

    @Update("update unit set enable = #{enable}, update_person = #{operator} where id = #{id}")
    int updateEnable(@Param("id") Long id, @Param("operator") Long operator, @Param("enable") String enable);

    @Select("select * from unit where id = #{id} and enable = 1")
    @Results({@Result(column = "abilities", property = "abilities", javaType = AbilityPo.class, typeHandler = JsonListTypeHandler.class)})
    UnitPo selectById(Long id);

    @Select("select * from unit where project_id = #{projectId} and enable = 1")
    @Results({@Result(column = "abilities", property = "abilities", javaType = AbilityPo.class, typeHandler = JsonListTypeHandler.class)})
    List<UnitPo> listByProjectId(Long projectId);

}
