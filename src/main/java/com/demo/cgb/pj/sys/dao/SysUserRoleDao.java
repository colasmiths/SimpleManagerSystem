package com.demo.cgb.pj.sys.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysUserRoleDao {

    @Select("select role_id from sys_user_roles where user_id=#{id}")
    List<Integer> findRoleIdsByUserId(Integer id);

    int insertObjects(@Param("userId")Integer userId,@Param("roleIds")Integer[] roleIds);







}
