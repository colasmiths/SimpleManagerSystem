package com.demo.cgb.pj.sys.service;


import com.demo.cgb.pj.common.annotation.RequiredLog;
import com.demo.cgb.pj.common.exceptions.ServiceException;
import com.demo.cgb.pj.common.util.PageUtil;
import com.demo.cgb.pj.common.vo.CheckBox;
import com.demo.cgb.pj.common.vo.PageObject;
import com.demo.cgb.pj.sys.dao.SysRoleDao;
import com.demo.cgb.pj.sys.dao.SysRoleMenuDao;
import com.demo.cgb.pj.sys.dao.SysUserRoleDao;
import com.demo.cgb.pj.sys.entity.SysRole;
import com.demo.cgb.pj.sys.vo.SysRoleMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    @Resource
    private SysRoleMenuDao sysRoleMenuDao;

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<CheckBox> findObjects() {
        return sysRoleDao.findObjects();
    }

    @Override
    public SysRoleMenuVo findObjectById(Integer id) {
        if(id==null||id<1)
            throw new IllegalArgumentException("id值无效");
        SysRoleMenuVo sysRoleMenuVo = sysRoleDao.findObjectById(id);
        if(sysRoleMenuVo==null)
            throw new ServiceException("没有找到对应结果");
        return sysRoleMenuVo;

    }

    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不能为空");
        if(menuIds==null||menuIds.length==0)
            throw new IllegalArgumentException("必须为角色分配权限");

        int rows = sysRoleDao.insertObject(entity);

        sysRoleMenuDao.insertObjects(entity.getId(),menuIds);

        return rows;
    }

    @Override
    public int saveObject(SysRole entity, Integer[] menuIds) {
        if(entity==null)
            throw new IllegalArgumentException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getName()))
            throw new IllegalArgumentException("角色名不能为空");
        if(menuIds==null||menuIds.length==0)
            throw new IllegalArgumentException("必须为角色分配权限");

        int rows = sysRoleDao.updateObject(entity);

        sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
        sysRoleMenuDao.insertObjects(entity.getId(),menuIds);

        return rows;
    }

    @Override
    public int deleteObject(Integer id) {
        if(id==null||id<1)
            throw new IllegalArgumentException("id值无效");

        sysRoleMenuDao.deleteObjectsByRoleId(id);

        sysUserRoleDao.deleteObjectsByUserId(id);

        int rows = sysRoleDao.deleteObject(id);

        if(rows==0)
            throw new ServiceException("记录可能不存在");
        return rows;

    }

    @RequiredLog("日志查询")
    @Override
    public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("页码值不正确");

        int rowCount = sysRoleDao.getRowCount(name);
        if(rowCount==0)
            throw new ServiceException("没有记录");

        int pageSize = PageUtil.getPageSize();

        int startIndex = PageUtil.getStartIndex(pageCurrent);

        List<SysRole> records = sysRoleDao.findPageObjects(name,startIndex,pageSize);

        return PageUtil.newPageObject(pageCurrent,rowCount,pageSize,records);
    }
}
