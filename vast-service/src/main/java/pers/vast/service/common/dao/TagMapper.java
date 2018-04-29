package pers.vast.service.common.dao;

import org.apache.ibatis.annotations.*;
import pers.vast.service.common.entity.TagPo;
import pers.vast.service.common.entity.TagRelPo;
import pers.vast.service.common.entity.TagVo;

import java.util.List;
import java.util.Map;

/**
 * 标签
 * Created by sengzin on 2018/4/22.
 */
@Mapper
public interface TagMapper {

    @Insert("INSERT INTO tag(name, owner_type, owner_id, scope, color, create_person, create_time) " +
            "VALUES( #{name}, #{ownerType}, #{ownerId}, #{scope}, #{color}, #{createPerson}, #{createTime})")
    @Options(useGeneratedKeys = true)
    int insert(TagPo po);

    @Update("UPDATE tag SET name = #{name}, color = #{color}, update_person = #{updatePerson} WHERE id = #{id}")
    int update(@Param("id") long id, @Param("name") String name, @Param("color") String color, @Param("updatePerson") long updatePerson);

    @Update("UPDATE tag SET enable = #{enable} WHERE id = #{id}")
    int updateEnable(@Param("id") long id, @Param("enable") String enable);

    TagPo select(Map param);

    List<TagPo> list(Map param);
    // 连表查询
    List<TagPo> listWithJoin(Map param);

    /** 标签关系 */

    TagRelPo selectRel(Map param);

    @Insert("INSERT INTO tag_rel(biz_id, tag_id, create_person, create_time) " +
            "VALUES(#{bizId}, #{tagId}, #{createPerson}, #{createTime})")
    int insertRel(TagRelPo po);

    @Update("UPDATE tag_rel SET enable = #{enable}, update_person = #{operator} WHERE biz_id = #{bizId} AND tag_id = #{tagId}")
    int updateRelEnable(@Param("bizId") String bizId, @Param("tagId") long tagId, @Param("enable") int enable, @Param("name") long operator);

    @Update("UPDATE tag_rel SET enable = #{enable} WHERE id = #{id}")
    int updateRelEnableById(@Param("id") long id, @Param("enable") String enable);

    @Update("UPDATE tag_rel SET enable = #{enable} WHERE tag_id = #{tagId}")
    int updateRelEnableByTagId(@Param("tagId") long tagId, @Param("enable") String enable);

    @Update("UPDATE tag_rel SET enable = #{enable} WHERE biz_id = #{bizId}")
    int updateRelEnableByBizId(@Param("bizId") String bizId, @Param("enable") String enable);

}
