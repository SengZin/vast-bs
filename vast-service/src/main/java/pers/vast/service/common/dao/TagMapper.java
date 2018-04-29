package pers.vast.service.common.dao;

import org.apache.ibatis.annotations.*;
import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.entity.TagRelPo;
import pers.vast.service.common.entity.TagVo;

import java.util.List;

/**
 * 标签
 * Created by sengzin on 2018/4/22.
 */
@Mapper
public interface TagMapper {

    @Insert("INSERT INTO tag(scope, name, create_person, create_time) " +
            "VALUES(#{scope}, #{name}, #{createPerson}, #{createTime})")
    @Options(useGeneratedKeys = true)
    int insert(TagPo po);

    @Select("SELECT * FROM tag WHERE scope = #{scope} AND name = #{name} limit 1")
    TagPo select(@Param("scope") Integer scope, @Param("name") String name);

//    @Select("SELECT * FROM tag WHERE")
//    List<TagPo> list(String bizId);

    /** 标签关系 */

    @Insert("INSERT INTO tag_rel(biz_id, tag_id, create_person, create_time, update_person) " +
            "VALUES(#{bizId}, #{tagId}, #{createPerson}, #{createTime}, #{createPerson})")
    int insertRel(TagRelPo po);

    @Update("UPDATE tag_rel SET enable = #{enable}, update_person = #{operator} WHERE biz_id = #{bizId} AND tag_id = #{tagId}")
    int updateRelEnable(@Param("bizId") String bizId, @Param("tagId") long tagId, @Param("enable") int enable, @Param("name") long operator);

    @Select("SELECT t.name " +
            "FROM tag_rel tr " +
            "LEFT JOIN tag t ON t.id = tr.tag_id " +
            "WHERE tr.biz_id = #{bizId} AND tr.enable = 1 AND t.scope = #{scope}")
    List<String> listName(@Param("bizId") String bizId, @Param("scope") Integer scope);

}
