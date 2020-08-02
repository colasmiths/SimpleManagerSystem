package com.demo.cgb.pj.sys.dao;

import com.demo.cgb.pj.common.vo.CheckBox;
import com.demo.cgb.pj.sys.entity.SysMenu;
import com.demo.cgb.pj.sys.entity.SysRole;
import com.demo.cgb.pj.sys.vo.SysRoleMenuVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface SysRoleDao {

    @Delete("delete from sys_roles where id=#{id}")
    int deleteObject(Integer id);


    //查询所有角色信息
    @Select("select id,name from sys_roles")
    List<CheckBox> findObjects();


    @Update("update sys_roles set name=#{name},note=#{note},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int updateObject(SysRole entity);


    @Insert("insert into sys_roles(name,note,createdTime,modifiedTime,createdUser,modifiedUser values (#{name},#{note},now(),now(),#{createdUser},#{modifiedUser}")
    int insertObject(SysRole entity);


    //基于角色id获取角色对应的菜单id
    SysRoleMenuVo findObjectById(Integer id);


    int getRowCount(String name);

    List<SysRole> findPageObjects(@Param("name")String name,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);







}

