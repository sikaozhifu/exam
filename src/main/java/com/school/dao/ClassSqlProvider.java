package com.school.dao;

import com.school.entity.Class;
import org.apache.ibatis.jdbc.SQL;

public class ClassSqlProvider {

    public String insertSelective(Class record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("class");
        
        if (record.getClassid() != null) {
            sql.VALUES("classId", "#{classid,jdbcType=INTEGER}");
        }
        
        if (record.getClassname() != null) {
            sql.VALUES("className", "#{classname,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Class record) {
        SQL sql = new SQL();
        sql.UPDATE("class");
        
        if (record.getClassname() != null) {
            sql.SET("className = #{classname,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("classId = #{classid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}