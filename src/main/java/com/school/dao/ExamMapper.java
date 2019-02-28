package com.school.dao;

import com.school.entity.Exam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ExamMapper {
    @Delete({
        "delete from exam",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer examId);

    @Insert({
        "insert into exam (exam_id, exam_name, ",
        "exam_type, need_time, ",
        "publisher, create_time, ",
        "update_time, exam_content)",
        "values (#{examId,jdbcType=INTEGER}, #{examName,jdbcType=VARCHAR}, ",
        "#{examType,jdbcType=INTEGER}, #{needTime,jdbcType=VARCHAR}, ",
        "#{publisher,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "now(), now())"
    })
    int insert(Exam record);

    @InsertProvider(type=ExamSqlProvider.class, method="insertSelective")
    int insertSelective(Exam record);

    @Select({
        "select",
        "exam_id, exam_name, exam_type, need_time, publisher, create_time, update_time, ",
        "exam_content",
        "from exam",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="exam_id", property="examId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="exam_name", property="examName", jdbcType=JdbcType.VARCHAR),
        @Result(column="exam_type", property="examType", jdbcType=JdbcType.INTEGER),
        @Result(column="need_time", property="needTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="publisher", property="publisher", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="exam_content", property="examContent", jdbcType=JdbcType.LONGVARCHAR)
    })
    Exam selectByPrimaryKey(Integer examId);

    @UpdateProvider(type=ExamSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Exam record);

    @Update({
        "update exam",
        "set exam_name = #{examName,jdbcType=VARCHAR},",
          "exam_type = #{examType,jdbcType=INTEGER},",
          "need_time = #{needTime,jdbcType=VARCHAR},",
          "publisher = #{publisher,jdbcType=VARCHAR},",
          "update_time = now(),",
          "exam_content = #{examContent,jdbcType=LONGVARCHAR}",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Exam record);

    @Update({
        "update exam",
        "set exam_name = #{examName,jdbcType=VARCHAR},",
          "exam_type = #{examType,jdbcType=INTEGER},",
          "need_time = #{needTime,jdbcType=VARCHAR},",
          "publisher = #{publisher,jdbcType=VARCHAR},",
          "update_time = now()",
        "where exam_id = #{examId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Exam record);
}