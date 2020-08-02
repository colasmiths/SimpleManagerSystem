package com.demo.cgb.pj.sys.dao;


import com.demo.cgb.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysLogDao {

    @Insert("insert into sys_logs (username,operation,method,params,time,ip,createdTime) values (#{username},#{operation},#{method},#{params},#{time},#{ip},#{createdTime}")
    int insertObject(SysLog entity);


    int getRowCount(String username);



    List<SysLog> findPageObjects(@Param("username")String username,@Param("startIndex")Integer startIndex,@Param("pageSize")Integer pageSize);


    int deleteObjects(@Param("id")Integer[] ids);

    @Delete("delete from sys_logs where id=#{id}")
    int deleteObject(Integer id);

}
