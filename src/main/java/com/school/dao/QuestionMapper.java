package com.school.dao;

import com.school.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface QuestionMapper {
    @Delete({
        "delete from question",
        "where questionId = #{questionid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer questionid);

    @Insert({
        "insert into question (questionId, questionName, ",
        "crateType, needtime, ",
        "publisher, createtime, ",
        "updatetime, questionContent)",
        "values (#{questionid,jdbcType=INTEGER}, #{questionname,jdbcType=VARCHAR}, ",
        "#{cratetype,jdbcType=INTEGER}, #{needtime,jdbcType=VARCHAR}, ",
        "#{publisher,jdbcType=VARCHAR}, #{createtime,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP}, #{questioncontent,jdbcType=LONGVARCHAR})"
    })
    int insert(Question record);

    @InsertProvider(type=QuestionSqlProvider.class, method="insertSelective")
    int insertSelective(Question record);

    @Select({
        "select",
        "questionId, questionName, crateType, needtime, publisher, createtime, updatetime, ",
        "questionContent",
        "from question",
        "where questionId = #{questionid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="questionId", property="questionid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="questionName", property="questionname", jdbcType=JdbcType.VARCHAR),
        @Result(column="crateType", property="cratetype", jdbcType=JdbcType.INTEGER),
        @Result(column="needtime", property="needtime", jdbcType=JdbcType.VARCHAR),
        @Result(column="publisher", property="publisher", jdbcType=JdbcType.VARCHAR),
        @Result(column="createtime", property="createtime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="questionContent", property="questioncontent", jdbcType=JdbcType.LONGVARCHAR)
    })
    Question selectByPrimaryKey(Integer questionid);

    @UpdateProvider(type=QuestionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Question record);

    @Update({
        "update question",
        "set questionName = #{questionname,jdbcType=VARCHAR},",
          "crateType = #{cratetype,jdbcType=INTEGER},",
          "needtime = #{needtime,jdbcType=VARCHAR},",
          "publisher = #{publisher,jdbcType=VARCHAR},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP},",
          "questionContent = #{questioncontent,jdbcType=LONGVARCHAR}",
        "where questionId = #{questionid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Question record);

    @Update({
        "update question",
        "set questionName = #{questionname,jdbcType=VARCHAR},",
          "crateType = #{cratetype,jdbcType=INTEGER},",
          "needtime = #{needtime,jdbcType=VARCHAR},",
          "publisher = #{publisher,jdbcType=VARCHAR},",
          "createtime = #{createtime,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP}",
        "where questionId = #{questionid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Question record);
}