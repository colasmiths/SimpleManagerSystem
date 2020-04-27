package com.demo.cgb.pj.sys.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.xml.soap.Node;
import java.util.List;
import java.util.Map;

@Mapper
public interface SysMenuDao {


    @Select("select sm_s.*,sm_p.name parentName from sys_menus sm_s left join sys_menus sm_p on sm_s.parentId=sm_p.id ")
    List<Map<String,Object>> findObjects();




}
