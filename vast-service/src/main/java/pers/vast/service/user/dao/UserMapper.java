package pers.vast.service.user.dao;

import org.apache.ibatis.annotations.*;
import pers.vast.service.user.entity.UserPo;

/**
 * Created by sengzin on 2017/9/13.
 */
@Mapper
public interface UserMapper {

    UserPo selectByPo(UserPo po);

    @Select("select * from user where id = #{id}")
    UserPo selectById(Long id);

    @Select("SELECT * FROM user WHERE account = #{identity} OR email = #{identity} OR tel = #{identity}")
    UserPo selectByIdentity(String identity);

//    @Results(value = {
//            @Result(property = "roleId", column = "role_id", javaType = Integer.class, jdbcType = JdbcType.TINYINT),
//            @Result(property = "registerAt", column = "register_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP),
//            @Result(property = "updateAt", column = "update_at", javaType = Date.class, jdbcType = JdbcType.TIMESTAMP)
//    })

    @Select("SELECT * FROM user WHERE account = #{account} and password = #{password}")
    UserPo selectByAccount(@Param("account") String account, @Param("password") String password);

    @Insert("insert into user (account, password, name, email) values(#{account}, #{password}, #{name}" +
            ", #{email}) ")
    @Options(useGeneratedKeys = true)
    int insert(UserPo po);

}
