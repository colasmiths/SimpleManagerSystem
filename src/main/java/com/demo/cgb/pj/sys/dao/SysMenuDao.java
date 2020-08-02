package com.demo.cgb.pj.sys.dao;


import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {


    /**查询所有菜单信息
     * 1)一行菜单信息映射为一个map对象(key为记录中的字段名)
     * 2)多行记录会对应多个map,然后将map存在list集合*/
    @Select("select sm_c.*,sm_p.name parentName from sys_menus sm_c left join sys_menus sm_p on sm_c.parentId=sm_p.id ")
    List<Map<String,Object>> findObjects();

    /**查询所有菜单信息
     * 1)一行菜单信息映射为一个Node对象(属性名建议和字段名相同)
     * 2)多行记录会对应多个node,然后将node存在list集合*/
    @Select("select id,name,parentId from sys_menus")
    List<Node> findZTreeMenuNodes();


    List<String> findPermissions(Integer[] menuIds);

    @Update("update sys_menus set name=#{name},type=#{type},sort=#{sort},url=#{url},parentId=#{parentId},permission=#{permission},modifiedUser=#{modifiedUser},modifiedTime=now() where id=#{id}")
    int updateObject(SysMenu entity);


    @Insert("insert into sys_menus (name,url,type,sort,note,parentId,permission,createdTime,modifiedTime,createdUser,modifiedUser) values (#{name},#{url},#{type},#{sort},#{note},#{parentId},#{permission},now(),now(),#{createdUser},#{modifiedUser})")
    int insertObject(SysMenu entity);






}
