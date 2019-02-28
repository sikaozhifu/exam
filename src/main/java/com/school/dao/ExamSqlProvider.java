package com.school.dao;

import com.school.entity.Exam;
import org.apache.ibatis.jdbc.SQL;

public class ExamSqlProvider {

    public String insertSelective(Exam record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("exam");
        
        if (record.getExamId() != null) {
            sql.VALUES("exam_id", "#{examId,jdbcType=INTEGER}");
        }
        
        if (record.getExamName() != null) {
            sql.VALUES("exam_name", "#{examName,jdbcType=VARCHAR}");
        }
        
        if (record.getExamType() != null) {
            sql.VALUES("exam_type", "#{examType,jdbcType=INTEGER}");
        }
        
        if (record.getNeedTime() != null) {
            sql.VALUES("need_time", "#{needTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.VALUES("publisher", "#{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExamContent() != null) {
            sql.VALUES("exam_content", "#{examContent,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Exam record) {
        SQL sql = new SQL();
        sql.UPDATE("exam");
        
        if (record.getExamName() != null) {
            sql.SET("exam_name = #{examName,jdbcType=VARCHAR}");
        }
        
        if (record.getExamType() != null) {
            sql.SET("exam_type = #{examType,jdbcType=INTEGER}");
        }
        
        if (record.getNeedTime() != null) {
            sql.SET("need_time = #{needTime,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.SET("publisher = #{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getExamContent() != null) {
            sql.SET("exam_content = #{examContent,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("exam_id = #{examId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}