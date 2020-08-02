package com.demo.cgb.pj.sys.service;


import com.demo.cgb.pj.common.vo.Node;
import com.demo.cgb.pj.sys.dao.SysMenuDao;
import com.demo.cgb.pj.sys.entity.SysMenu;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Resource
    private SysMenuDao sysMenuDao;

    @Override
    public int updateObject(SysMenu entity) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("菜单名不能为空");
        if (StringUtils.isEmpty(entity.getPermission()))
            throw new IllegalArgumentException("权限标识不能为空");
        int rows = sysMenuDao.updateObject(entity);
        return rows;


    }

    @Override
    public int saveObject(SysMenu entity) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("菜单名不能为空");
        if (StringUtils.isEmpty(entity.getPermission()))
            throw new IllegalArgumentException("权限标识不能为空");

        int rows = sysMenuDao.insertObject(entity);
        return rows;
    }

    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String,Object>> list = sysMenuDao.findObjects();
        return list;
    }

    @Override
    public List<Node> findZTreeMenuNodes() {
        return sysMenuDao.findZTreeMenuNodes();
    }
}
