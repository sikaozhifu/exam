package com.school.dao;

import com.school.entity.Questionmodel;
import org.apache.ibatis.jdbc.SQL;

public class QuestionmodelSqlProvider {

    public String insertSelective(Questionmodel record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("questionmodel");
        
        if (record.getMoldeid() != null) {
            sql.VALUES("moldeId", "#{moldeid,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.VALUES("answer", "#{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getAnalysis() != null) {
            sql.VALUES("analysis", "#{analysis,jdbcType=VARCHAR}");
        }
        
        if (record.getDifficulty() != null) {
            sql.VALUES("difficulty", "#{difficulty,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.VALUES("grade", "#{grade,jdbcType=INTEGER}");
        }
        
        if (record.getCreatetime() != null) {
            sql.VALUES("createtime", "#{createtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.VALUES("updatetime", "#{updatetime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Questionmodel record) {
        SQL sql = new SQL();
        sql.UPDATE("questionmodel");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=VARCHAR}");
        }
        
        if (record.getAnswer() != null) {
            sql.SET("answer = #{answer,jdbcType=VARCHAR}");
        }
        
        if (record.getAnalysis() != null) {
            sql.SET("analysis = #{analysis,jdbcType=VARCHAR}");
        }
        
        if (record.getDifficulty() != null) {
            sql.SET("difficulty = #{difficulty,jdbcType=INTEGER}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getGrade() != null) {
            sql.SET("grade = #{grade,jdbcType=INTEGER}");
        }
        
        if (record.getCreatetime() != null) {
            sql.SET("createtime = #{createtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.SET("updatetime = #{updatetime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("moldeId = #{moldeid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}