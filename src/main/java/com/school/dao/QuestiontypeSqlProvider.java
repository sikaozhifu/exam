package com.school.dao;

import com.school.entity.Questiontype;
import org.apache.ibatis.jdbc.SQL;

public class QuestiontypeSqlProvider {

    public String insertSelective(Questiontype record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("questiontype");
        
        if (record.getTypeid() != null) {
            sql.VALUES("typeId", "#{typeid,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Questiontype record) {
        SQL sql = new SQL();
        sql.UPDATE("questiontype");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("typeId = #{typeid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}