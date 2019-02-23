package com.school.dao;

import com.school.entity.Questionmodel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface QuestionmodelMapper {
    @Delete({
        "delete from questionmodel",
        "where moldeId = #{moldeid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer moldeid);

    @Insert({
        "insert into questionmodel (moldeId, title, ",
        "type, content, answer, ",
        "analysis, difficulty, ",
        "author, grade, createtime, ",
        "updatetime)",
        "values (#{moldeid,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, #{answer,jdbcType=VARCHAR}, ",
        "#{analysis,jdbcType=VARCHAR}, #{difficulty,jdbcType=INTEGER}, ",
        "#{author,jdbcType=VARCHAR}, #{grade,jdbcType=INTEGER}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP})"
    })
    int insert(Questionmodel record);

    @InsertProvider(type=QuestionmodelSqlProvider.class, method="insertSelective")
    int insertSelective(Questionmodel record);

    @Select({
        "select",
        "moldeId, title, type, content, answer, analysis, difficulty, author, grade, ",
        "createtime, updatetime",
        "from questionmodel",
        "where moldeId = #{moldeid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="moldeId", property="moldeid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="answer", property="answer", jdbcType=JdbcType.VARCHAR),
        @Result(column="analysis", property="analysis", jdbcType=JdbcType.VARCHAR),
        @Result(column="difficulty", property="difficulty", jdbcType=JdbcType.INTEGER),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="grade", property="grade", jdbcType=JdbcType.INTEGER),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    Questionmodel selectByPrimaryKey(Integer moldeid);

    @UpdateProvider(type=QuestionmodelSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Questionmodel record);

    @Update({
        "update questionmodel",
        "set title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "answer = #{answer,jdbcType=VARCHAR},",
          "analysis = #{analysis,jdbcType=VARCHAR},",
          "difficulty = #{difficulty,jdbcType=INTEGER},",
          "author = #{author,jdbcType=VARCHAR},",
          "grade = #{grade,jdbcType=INTEGER},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP}",
        "where moldeId = #{moldeid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Questionmodel record);
}