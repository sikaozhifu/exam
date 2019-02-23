package com.school.dao;

import com.school.entity.Admin;
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
public interface AdminMapper {
    @Delete({
        "delete from admin",
        "where adminId = #{adminid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer adminid);

    @Insert({
        "insert into admin (adminId, adminUserName, ",
        "adminName, adminPassword, ",
        "adminEmail, createupdate, ",
        "updatetime)",
        "values (#{adminid,jdbcType=INTEGER}, #{adminusername,jdbcType=VARCHAR}, ",
        "#{adminname,jdbcType=VARCHAR}, #{adminpassword,jdbcType=VARCHAR}, ",
        "#{adminemail,jdbcType=VARCHAR}, #{createupdate,jdbcType=TIMESTAMP}, ",
        "#{updatetime,jdbcType=TIMESTAMP})"
    })
    int insert(Admin record);

    @InsertProvider(type=AdminSqlProvider.class, method="insertSelective")
    int insertSelective(Admin record);

    @Select({
        "select",
        "adminId, adminUserName, adminName, adminPassword, adminEmail, createupdate, ",
        "updatetime",
        "from admin",
        "where adminId = #{adminid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="adminId", property="adminid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="adminUserName", property="adminusername", jdbcType=JdbcType.VARCHAR),
        @Result(column="adminName", property="adminname", jdbcType=JdbcType.VARCHAR),
        @Result(column="adminPassword", property="adminpassword", jdbcType=JdbcType.VARCHAR),
        @Result(column="adminEmail", property="adminemail", jdbcType=JdbcType.VARCHAR),
        @Result(column="createupdate", property="createupdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin selectByPrimaryKey(Integer adminid);

    @UpdateProvider(type=AdminSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Admin record);

    @Update({
        "update admin",
        "set adminUserName = #{adminusername,jdbcType=VARCHAR},",
          "adminName = #{adminname,jdbcType=VARCHAR},",
          "adminPassword = #{adminpassword,jdbcType=VARCHAR},",
          "adminEmail = #{adminemail,jdbcType=VARCHAR},",
          "createupdate = #{createupdate,jdbcType=TIMESTAMP},",
          "updatetime = #{updatetime,jdbcType=TIMESTAMP}",
        "where adminId = #{adminid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Admin record);

    @Select({
            "select",
            "adminId, adminUserName, adminName, adminPassword, adminEmail, createupdate, ",
            "updatetime",
            "from admin",
            "where adminUserName = #{adminUserName,jdbcType=VARCHAR}",
            "and adminPassword = #{adminPassword,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="adminId", property="adminid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="adminUserName", property="adminusername", jdbcType=JdbcType.VARCHAR),
            @Result(column="adminName", property="adminname", jdbcType=JdbcType.VARCHAR),
            @Result(column="adminPassword", property="adminpassword", jdbcType=JdbcType.VARCHAR),
            @Result(column="adminEmail", property="adminemail", jdbcType=JdbcType.VARCHAR),
            @Result(column="createupdate", property="createupdate", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="updatetime", property="updatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin login(String adminUserName,String adminPassword);
}