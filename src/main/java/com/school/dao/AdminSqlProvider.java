package com.school.dao;

import com.school.entity.Admin;
import org.apache.ibatis.jdbc.SQL;

public class AdminSqlProvider {

    public String insertSelective(Admin record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("admin");
        
        if (record.getAdminid() != null) {
            sql.VALUES("adminId", "#{adminid,jdbcType=INTEGER}");
        }
        
        if (record.getAdminusername() != null) {
            sql.VALUES("adminUserName", "#{adminusername,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminname() != null) {
            sql.VALUES("adminName", "#{adminname,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminpassword() != null) {
            sql.VALUES("adminPassword", "#{adminpassword,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminemail() != null) {
            sql.VALUES("adminEmail", "#{adminemail,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateupdate() != null) {
            sql.VALUES("createupdate", "#{createupdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.VALUES("updatetime", "#{updatetime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Admin record) {
        SQL sql = new SQL();
        sql.UPDATE("admin");
        
        if (record.getAdminusername() != null) {
            sql.SET("adminUserName = #{adminusername,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminname() != null) {
            sql.SET("adminName = #{adminname,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminpassword() != null) {
            sql.SET("adminPassword = #{adminpassword,jdbcType=VARCHAR}");
        }
        
        if (record.getAdminemail() != null) {
            sql.SET("adminEmail = #{adminemail,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateupdate() != null) {
            sql.SET("createupdate = #{createupdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdatetime() != null) {
            sql.SET("updatetime = #{updatetime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("adminId = #{adminid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}