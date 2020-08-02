package com.demo.cgb.pj.sys.controller;


import com.demo.cgb.pj.common.vo.JsonResult;
import com.demo.cgb.pj.sys.entity.SysRole;
import com.demo.cgb.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role/")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @RequestMapping("doRoleListUI")
    public String doRoleListUI(){
        return "sys/role_list";
    }

    @RequestMapping("doRoleEditUI")
    public String doRoleEditUI(){
        return "sys/role_edit";
    }

    @RequestMapping("doFindRoles")
    @ResponseBody
    public JsonResult doFindRoles(){
        return new JsonResult(sysRoleService.findObjects());
    }

    @ResponseBody
    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id){
        return new JsonResult(sysRoleService.findObjectById(id));
    }

    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysRole entity,Integer[]menuIds) {
        sysRoleService.updateObject(entity, menuIds);
        return new JsonResult("更新成功");
    }



    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysRole entity, Integer[]menuIds) {
        sysRoleService.saveObject(entity, menuIds);
        return new JsonResult("保存成功");
    }


    @RequestMapping("doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(Integer id) {
        sysRoleService.deleteObject(id);
        return new JsonResult("删除成功");
    }



    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(
            String name,Integer pageCurrent) {
        return new JsonResult(
                sysRoleService.findPageObjects(
                        name, pageCurrent));
    }



}
