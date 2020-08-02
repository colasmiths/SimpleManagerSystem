package com.demo.cgb.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysRoleMenuDao {

    List<Integer> findMenuIdsByRoleIds(@Param("roleIds")Integer[] roleIds);

    @Select("select menu_id from sys_role_menus where role_id=#{roleId}")
    int findMenuIdByRoleId(int roleId);

    int insertObjects(@Param("roleId")int roleId,@Param("menuIds")Integer[] menuIds);

    @Delete("delete from sys_role_menus where role_id=#{roleId}")
    int deleteObjectsByRoleId(Integer roleId);







}
