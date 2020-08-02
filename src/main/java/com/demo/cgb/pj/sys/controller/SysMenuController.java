package com.demo.cgb.pj.sys.controller;


import com.demo.cgb.pj.common.vo.JsonResult;
import com.demo.cgb.pj.sys.entity.SysMenu;
import com.demo.cgb.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("doMenuListUI")
    public String doMenuListUI(){
        return "sys/menu_list";
    }

    @RequestMapping("doMenuEditUI")
    public String doMenuEditUI(){
        return "sys/menu_edit";
    }

    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysMenu entity) {
        sysMenuService.updateObject(entity);
        return new JsonResult("更新成功");
    }

    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysMenu entity) {
        sysMenuService.saveObject(entity);
        return new JsonResult("保存成功");

    }

    @RequestMapping("doFindZtreeMenuNodes")
    @ResponseBody
    public JsonResult doFindZtreeMenuNodes() {
        return new JsonResult(sysMenuService.findZTreeMenuNodes());
    }

    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects() {
        return new JsonResult(sysMenuService.findObjects());
    }



}
