package com.school.dao;

import com.school.entity.Userclass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserclassMapper {
    @Delete({
        "delete from userclass",
        "where user_class_id = #{userClassId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userClassId);

    @Insert({
        "insert into userclass (user_class_id, username, ",
        "class_id)",
        "values (#{userClassId,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{classId,jdbcType=INTEGER})"
    })
    int insert(Userclass record);

    @InsertProvider(type=UserclassSqlProvider.class, method="insertSelective")
    int insertSelective(Userclass record);

    @Select({
        "select",
        "user_class_id, username, class_id",
        "from userclass",
        "where user_class_id = #{userClassId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="user_class_id", property="userClassId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="class_id", property="classId", jdbcType=JdbcType.INTEGER)
    })
    Userclass selectByPrimaryKey(Integer userClassId);

    @UpdateProvider(type=UserclassSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Userclass record);

    @Update({
        "update userclass",
        "set username = #{username,jdbcType=VARCHAR},",
          "class_id = #{classId,jdbcType=INTEGER}",
        "where user_class_id = #{userClassId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Userclass record);
}