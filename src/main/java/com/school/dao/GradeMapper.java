package com.school.dao;

import com.school.entity.Grade;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface GradeMapper {
    @Delete({
        "delete from grade",
        "where gradeid = #{gradeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer gradeid);

    @Insert({
        "insert into grade (gradeid, username, ",
        "name, title, className, ",
        "spendtime, score, ",
        "createtime, updatetime)",
        "values (#{gradeid,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{classname,jdbcType=VARCHAR}, ",
        "#{spendtime,jdbcType=VARCHAR}, #{score,jdbcType=INTEGER}, ",
        "#{createtime,jdbcType=TIMESTAMP}, #{updatetime,jdbcType=TIMESTAMP})"
    })
    int insert(Grade record);

    @InsertProvider(type=GradeSqlProvider.class, method="insertSelective")
    int insertSelective(Grade record);

    @Select({
        "select",
        "gradeid, username, name, title, className, spendtime, score, createtime, updatetime",
        "from grade",
        "where gradeid = #{gradeid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="gradeid", property="gradeid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="className", property="classname", jdbcType=JdbcType.VARCHAR),
        @Result(column="spendtime", property="spendtime", jdbcType=JdbcType.VARCHAR),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    Grade selectByPrimaryKey(Integer gradeid);

    @UpdateProvider(type=GradeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Grade record);

    @Update({
        "update grade",
        "set username = #{username,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "className = #{classname,jdbcType=VARCHAR},",
          "spendtime = #{spendtime,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP}",
        "where gradeid = #{gradeid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Grade record);
}