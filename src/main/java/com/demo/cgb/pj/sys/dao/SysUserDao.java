package com.demo.cgb.pj.sys.dao;


import com.demo.cgb.pj.sys.entity.SysUser;
import com.demo.cgb.pj.sys.vo.SysUserDeptVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SysUserDao {

    //根据用户名查询用户信息,此方法用于realm中
    @Select("select * from sys_users where username = #{username}")
    SysUser findUserByUserName(String Name);


    //更新传入的id所对应的用户的valid状态值、修改人名称和修改时间
    @Update("update sys_users set valid=#{valid},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int validById(@Param("id")Integer id,@Param("valid")Integer valid,@Param("modifiedUser")String modifiedUser);


    int insertObject(SysUser entity);



    SysUserDeptVo findObjectById(Integer id);

    int updateObject(SysUser entity);

    int getRowCount(@Param("username") String username); //需要通过username来查出记录行数，后面在通过行数来计算页面数

    List<SysUserDeptVo> findPageObjects(@Param("username")String username,@Param("startIndex")Integer satrtIndex,@Param("pageSize")Integer pageSize);


}
