package pers.vast.project.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import pers.vast.project.entity.AbilityDefinePO;

import java.util.List;

/**
 * Created by sengzin on 2017/9/17.
 */
@Mapper
public interface AbilityDefineMapper {

    @Results(value = {
            @Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    @Select("select * from ability_define where id = #{id}")
    AbilityDefinePO selectById(int id);

    @Results(value = {
            @Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER)
    })
    @Select("select * from ability_define where project_id = #{projectId}")
    List<AbilityDefinePO> selectByProjectId(int projectId);

    @Insert("insert into ability_define (project_id, name, min, max) values (#{projectId}, #{name}, #{min}, #{max})")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AbilityDefinePO po);

}
