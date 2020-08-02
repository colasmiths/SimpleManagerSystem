package com.demo.cgb.pj.sys.dao;


import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;

@Mapper
public interface SysDeptDao {

    @Select("select * from sys_depts where id=#{id}")
    SysDept findById(Integer id);


    @Update("update sys_dept set name=#{name},sort=#{sort},note=#{note},parentId=#{parentId},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int updateObject(SysDept entity);

    @Insert("insert into sys_depts(id,name,parentId,sort,note,createdTime,createdUser,modifiedTime,modifiedUser)values(#{id},#{name},#{parentId},#{sort},#{note},now(),#{createdUser},now(),#{modifiedUser})")
    int insertObject(SysDept entity);

    List<Node> findZTreeNodes();

    List<Map<String,Object>> findObjects();


    @Select("select count(*) from sys_depts where parentId=#{id}")
    int getChildCount(Integer id);


    @Delete("delete from sys_depts where id=#{id}")
    int deleteObject(Integer id);


}
