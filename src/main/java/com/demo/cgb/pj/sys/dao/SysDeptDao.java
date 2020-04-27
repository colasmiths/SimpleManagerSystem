package com.demo.cgb.pj.sys.dao;


import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDeptDao {
    SysDept findById(Integer id);

    int updateObject(SysDept entity);

    int insertObject(SysDept entity);

    List<Node> findZTreeNodes();

    List<Map<String,Object>> findObjects();


    int getChildCount(Integer id);


    int deleteObject(Integer id);


}
