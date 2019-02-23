package com.school.dao;

import com.school.entity.Class;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ClassMapper {
    @Delete({
        "delete from class",
        "where classId = #{classid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer classid);

    @Insert({
        "insert into class (classId, className)",
        "values (#{classid,jdbcType=INTEGER}, #{classname,jdbcType=VARCHAR})"
    })
    int insert(Class record);

    @InsertProvider(type=ClassSqlProvider.class, method="insertSelective")
    int insertSelective(Class record);

    @Select({
        "select",
        "classId, className",
        "from class",
        "where classId = #{classid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="classId", property="classid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="className", property="classname", jdbcType=JdbcType.VARCHAR)
    })
    Class selectByPrimaryKey(Integer classid);

    @UpdateProvider(type=ClassSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Class record);

    @Update({
        "update class",
        "set className = #{classname,jdbcType=VARCHAR}",
        "where classId = #{classid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Class record);
}