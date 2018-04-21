package pers.vast.project.dao.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import pers.vast.project.entity.CharacterPO;

import java.util.Date;
import java.util.List;

/**
 * Created by sengzin on 2017/9/17.
 */
@Mapper
public interface CharacterMapper {

    @Results(value = {
            @Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    @Select("select * from `character` where id = #{id}")
    CharacterPO selectById(int id);

    @Results(value = {
            @Result(property = "projectId", column = "project_id", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            @Result(property = "lastName", column = "last_name", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(property = "firstName", column = "first_name", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    @Select("select * from `character` where project_id = #{projectId}")
    List<CharacterPO> selectListByProjectId(int projectId);

    @Insert("insert into `character` (project_id, last_name, first_name, male, ability) " +
            "values (#{projectId}, #{lastName}, #{firstName}, #{male}, #{ability})")
    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(CharacterPO po);

}
