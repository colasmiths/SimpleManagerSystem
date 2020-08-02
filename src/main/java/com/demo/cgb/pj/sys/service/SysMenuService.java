package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.entity.SysMenu;

import java.util.List;
import java.util.Map;

public interface SysMenuService {


    int updateObject(SysMenu entity);

    int saveObject(SysMenu entity);


    List<Map<String,Object>> findObjects();

    List<Node> findZTreeMenuNodes();
}
