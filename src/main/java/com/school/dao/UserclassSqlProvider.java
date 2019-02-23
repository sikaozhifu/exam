package com.school.dao;

import com.school.entity.Userclass;
import org.apache.ibatis.jdbc.SQL;

public class UserclassSqlProvider {

    public String insertSelective(Userclass record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("userclass");
        
        if (record.getUserClassId() != null) {
            sql.VALUES("user_class_id", "#{userClassId,jdbcType=INTEGER}");
        }
        
        if (record.getUsername() != null) {
            sql.VALUES("username", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getClassId() != null) {
            sql.VALUES("class_id", "#{classId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Userclass record) {
        SQL sql = new SQL();
        sql.UPDATE("userclass");
        
        if (record.getUsername() != null) {
            sql.SET("username = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getClassId() != null) {
            sql.SET("class_id = #{classId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("user_class_id = #{userClassId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}