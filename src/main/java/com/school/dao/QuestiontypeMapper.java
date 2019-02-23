package com.school.dao;

import com.school.entity.Questiontype;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface QuestiontypeMapper {
    @Delete({
        "delete from questiontype",
        "where typeId = #{typeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer typeid);

    @Insert({
        "insert into questiontype (typeId, type)",
        "values (#{typeid,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR})"
    })
    int insert(Questiontype record);

    @InsertProvider(type=QuestiontypeSqlProvider.class, method="insertSelective")
    int insertSelective(Questiontype record);

    @Select({
        "select",
        "typeId, type",
        "from questiontype",
        "where typeId = #{typeid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="typeId", property="typeid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    Questiontype selectByPrimaryKey(Integer typeid);

    @UpdateProvider(type=QuestiontypeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Questiontype record);

    @Update({
        "update questiontype",
        "set type = #{type,jdbcType=VARCHAR}",
        "where typeId = #{typeid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Questiontype record);
}