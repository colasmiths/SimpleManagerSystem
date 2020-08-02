package com.demo.cgb.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysUserRoleDao {

    @Select("select role_id from sys_user_roles where user_id=#{id}")
    List<Integer> findRoleIdsByUserId(Integer id);

    int insertObjects(@Param("userId")Integer userId,@Param("roleIds")Integer[] roleIds);

    @Delete("delete from sys_user_roles where user_id=#{userId}")
    int deleteObjectsByUserId(Integer userId);







}
