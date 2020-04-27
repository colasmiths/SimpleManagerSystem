package com.demo.cgb.pj.sys.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLogDao {

    @Delete("delete from sys_logs where id=#{id}")
    int deleteObject(Integer id);

}
