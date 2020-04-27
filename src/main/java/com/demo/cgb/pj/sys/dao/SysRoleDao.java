package com.demo.cgb.pj.sys.dao;

import org.apache.ibatis.annotations.Delete;

public interface SysRoleDao {

    @Delete("delete from sys_roles where id=#{id}")
    int deleteObject(Integer id);


}
