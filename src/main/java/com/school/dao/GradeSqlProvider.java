package com.school.dao;

import com.school.entity.Grade;
import org.apache.ibatis.jdbc.SQL;

public class GradeSqlProvider {

    public String insertSelective(Grade record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("grade");
        
        if (record.getGradeid() != null) {
            sql.VALUES("gradeid", "#{gradeid,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getClassname() != null) {
            sql.VALUES("className", "#{classname,jdbcType=VARCHAR}");
        }
        
        if (record.getSpendtime() != null) {
            sql.VALUES("spendtime", "#{spendtime,jdbcType=VARCHAR}");
        }
        
        if (record.getScore() != null) {
            sql.VALUES("score", "#{score,jdbcType=INTEGER}");
        }
        
        if (record.getCreatetime() != null) {
            sql.VALUES("createtime", "#{createtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.VALUES("updatetime", "#{updatetime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Grade record) {
        SQL sql = new SQL();
        sql.UPDATE("grade");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getClassname() != null) {
            sql.SET("className = #{classname,jdbcType=VARCHAR}");
        }
        
        if (record.getSpendtime() != null) {
            sql.SET("spendtime = #{spendtime,jdbcType=VARCHAR}");
        }
        
        if (record.getScore() != null) {
            sql.SET("score = #{score,jdbcType=INTEGER}");
        }
        
        if (record.getCreatetime() != null) {
            sql.SET("createtime = #{createtime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.SET("updatetime = #{updatetime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("gradeid = #{gradeid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}