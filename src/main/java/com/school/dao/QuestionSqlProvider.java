package com.school.dao;

import com.school.entity.Question;
import org.apache.ibatis.jdbc.SQL;

public class QuestionSqlProvider {

    public String insertSelective(Question record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("question");
        
        if (record.getQuestionid() != null) {
            sql.VALUES("questionId", "#{questionid,jdbcType=INTEGER}");
        }
        
        if (record.getQuestionname() != null) {
            sql.VALUES("questionName", "#{questionname,jdbcType=VARCHAR}");
        }
        
        if (record.getCratetype() != null) {
            sql.VALUES("crateType", "#{cratetype,jdbcType=INTEGER}");
        }
        
        if (record.getNeedtime() != null) {
            sql.VALUES("needtime", "#{needtime,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.VALUES("publisher", "#{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatetime() != null) {
            sql.VALUES("createtime", "#{createtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.VALUES("updatetime", "#{updatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getQuestioncontent() != null) {
            sql.VALUES("questionContent", "#{questioncontent,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Question record) {
        SQL sql = new SQL();
        sql.UPDATE("question");
        
        if (record.getQuestionname() != null) {
            sql.SET("questionName = #{questionname,jdbcType=VARCHAR}");
        }
        
        if (record.getCratetype() != null) {
            sql.SET("crateType = #{cratetype,jdbcType=INTEGER}");
        }
        
        if (record.getNeedtime() != null) {
            sql.SET("needtime = #{needtime,jdbcType=VARCHAR}");
        }
        
        if (record.getPublisher() != null) {
            sql.SET("publisher = #{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatetime() != null) {
            sql.SET("createtime = #{createtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.SET("updatetime = #{updatetime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getQuestioncontent() != null) {
            sql.SET("questionContent = #{questioncontent,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("questionId = #{questionid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}