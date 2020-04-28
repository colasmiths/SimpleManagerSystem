package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.annotation.RequiredLog;
import com.demo.cgb.pj.common.exceptions.ServiceException;
import com.demo.cgb.pj.common.util.PageUtil;
import com.demo.cgb.pj.common.vo.PageObject;
import com.demo.cgb.pj.sys.dao.SysUserDao;
import com.demo.cgb.pj.sys.dao.SysUserRoleDao;
import com.demo.cgb.pj.sys.entity.SysUser;
import com.demo.cgb.pj.sys.vo.SysUserDeptVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    private SysUserRoleDao sysUserRoleDao;


    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
        return 0;
    }

    @Override
    @Transactional(readOnly = true)//表示大家可以并发访问数据
    //添加一个新用户
    public Map<String, Object> findObjectById(Integer userId) {
        //1.验证userId的合法性
        if(userId==null||userId<=0)
            throw new ServiceException("此用户不存在");
        //2.查询user
        SysUserDeptVo sysUserDeptVo = sysUserDao.findObjectById(userId);
        if(sysUserDeptVo==null)
            throw new ServiceException("此用户不存在");
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);

        Map<String,Object> map = new HashMap<>();
        map.put("user",sysUserDeptVo);
        map.put("roleIds",roleIds);
        return map;
    }


    @Override
    //添加新用户，并对提交的用户信息进行校验
    public int saveObject(SysUser entity, Integer[] roleIds) {
        //1.参数校验
        if(entity==null)
            throw new ServiceException("保存对象不能为空");
        if(StringUtils.isEmpty(entity.getUsername()))
            throw new ServiceException("用户名不能为空");
        if(StringUtils.isEmpty(entity.getPassword()))
            throw new ServiceException("密码不能为空");
        if(roleIds==null||roleIds.length==0)
            throw new ServiceException("至少要为用户分配角色");
        //2.对用户的密码进行加密
        String salt = UUID.randomUUID().toString();
        SimpleHash sh = new SimpleHash("MD5", entity.getPassword(), salt, 1);
        entity.setSalt(salt);
        entity.setPassword(sh.toHex());
        //3.将对象写入到数据库中
        int rows = sysUserDao.insertObject(entity);
        int count = sysUserRoleDao.insertObjects(entity.getId(),roleIds);

        return rows;



    }

    @Override
    @RequiresPermissions("sys:user:valid")//此注解暂未知其含义
    /**
     * 当添加@RequiresPermissions注解时，
     * 此方法需要进行权限控制(授权以后才可以访问)
     */
    public int validById(Integer id, Integer valid, String modifiedUser) {
        if(id==null||id<1)
            throw new ServiceException("id值不正确");
        if(valid!=1||valid!=0)
            throw new ServiceException("用户状态值不正确");

        int rows = sysUserDao.validById(id,valid,modifiedUser);
        if(rows==0)
            throw new ServiceException("此用户可能已经不存在");
        return rows;

    }

    @Override
    @Transactional(readOnly = false)
    @RequiredLog("query user")//此注解暂时未知其含义
    public PageObject<SysUserDeptVo> findPageObjects(String username, Integer pageCurrent) {
        if(pageCurrent==null||pageCurrent<1)
            throw new IllegalArgumentException("页码不正确");

        int rowCount=sysUserDao.getRowCount(username);
        if(rowCount==0)
            throw new ServiceException("用户不存在");

        int pageSize = PageUtil.getPageSize();

        int startIndex = PageUtil.getStartIndex(pageCurrent);

        List<SysUserDeptVo> records = sysUserDao.findPageObjects(username,startIndex,pageSize);


        return PageUtil.newPageObject(pageCurrent,rowCount,pageSize,records);
    }
}
