package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.vo.CheckBox;
import com.demo.cgb.pj.common.vo.PageObject;
import com.demo.cgb.pj.sys.entity.SysRole;
import com.demo.cgb.pj.sys.vo.SysRoleMenuVo;

import java.util.List;

public interface SysRoleService {

    List<CheckBox> findObjects();

    SysRoleMenuVo findObjectById(Integer id);

    int updateObject(SysRole entity,Integer[] menuIds);

    int saveObject(SysRole entity,Integer[] menuIds);

    int deleteObject(Integer id);

    PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);
}
