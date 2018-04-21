package pers.vast.service.project.dao;

import org.apache.ibatis.annotations.*;
import pers.vast.service.project.entity.ProjectPo;

import java.util.List;

/**
 * Created by sengzin on 2017/9/17.
 */
@Mapper
public interface ProjectMapper {

    @Select("select * from project where id = #{id}")
    ProjectPo selectById(long id);

    @Select("select * from project WHERE create_person = #{createPerson}")
    List<ProjectPo> listByCreatePerson(long createPerson);

    @Insert("insert into project (name, introduction, announcement, cover_url, create_person, update_person, create_time) " +
            "values (#{name}, #{introduction}, #{announcement}, #{coverUrl}, #{createPerson}, #{createPerson}, #{createTime})")
    @Options(useGeneratedKeys = true)
    int insert(ProjectPo po);

}
