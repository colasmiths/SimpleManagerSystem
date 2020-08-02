package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.entity.SysDept;

import java.util.List;
import java.util.Map;


public interface SysDeptService {

    int saveObject(SysDept entity);

    int updateObject(SysDept entity);

    List<Node> findZTreeNodes();

    List<Map<String,Object>> findObjects();

    int deleteObject(Integer id);

}
