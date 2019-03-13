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
import org.springframework.stereotype.Repository;

@Repository
public interface GradeMapper {
    @Delete({
        "delete from grade",
        "where grade_id = #{gradeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer gradeId);

    @Insert({
        "insert into grade (grade_id, username, ",
        "name, title, group_name, ",
        "spend_time, score, ",
        "exam_id, create_time, ",
        "update_time)",
        "values (#{gradeId,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{groupName,jdbcType=VARCHAR}, ",
        "#{spendTime,jdbcType=VARCHAR}, #{score,jdbcType=INTEGER}, ",
        "#{examId,jdbcType=INTEGER}, now(), ",
        "now())"
    })
    int insert(Grade record);

    @InsertProvider(type=GradeSqlProvider.class, method="insertSelective")
    int insertSelective(Grade record);

    @Select({
        "select",
        "grade_id, username, name, title, group_name, spend_time, score, exam_id, create_time, ",
        "update_time",
        "from grade",
        "where grade_id = #{gradeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="grade_id", property="gradeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="spend_time", property="spendTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="score", property="score", jdbcType=JdbcType.INTEGER),
        @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Grade selectByPrimaryKey(Integer gradeId);

    @UpdateProvider(type=GradeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Grade record);

    @Update({
        "update grade",
        "set username = #{username,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "group_name = #{groupName,jdbcType=VARCHAR},",
          "spend_time = #{spendTime,jdbcType=VARCHAR},",
          "score = #{score,jdbcType=INTEGER},",
          "exam_id = #{examId,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where grade_id = #{gradeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Grade record);
}