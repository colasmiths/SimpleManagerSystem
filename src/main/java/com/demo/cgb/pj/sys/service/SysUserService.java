package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.vo.PageObject;
import com.demo.cgb.pj.sys.entity.SysUser;
import com.demo.cgb.pj.sys.vo.SysUserDeptVo;

import java.util.Map;

public interface SysUserService extends PageService<SysUserDeptVo> {

    int updateObject(SysUser entity,Integer[] roleIds);

    Map<String,Object> findObjectById(Integer userId);

    int saveObject(SysUser entity,Integer[] roleIds);

    int validById(Integer id,Integer valid,String modifiedUser);




}
