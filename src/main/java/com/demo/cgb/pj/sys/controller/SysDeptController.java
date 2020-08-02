package com.demo.cgb.pj.sys.controller;


import com.demo.cgb.pj.common.vo.JsonResult;
import com.demo.cgb.pj.sys.entity.SysDept;
import com.demo.cgb.pj.sys.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept/")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @RequestMapping("doDeptListUI")
    public String doDeptListUI(){
        return "sys/dept_list";
    }

    @RequestMapping("doDeptEditUI")
    public String doDeptEditUI(){
        return "sys/dept_edit";
    }

    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysDept entity){
        sysDeptService.updateObject(entity);
        return new JsonResult("更新成功");
    }

    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysDept entity){
        sysDeptService.saveObject(entity);
        return new JsonResult("保存成功");
    }

    @RequestMapping("doFindZTreeNodes")
    @ResponseBody
    public JsonResult doFindZTreeNodes(){
        return new JsonResult(sysDeptService.findZTreeNodes());
    }


    @RequestMapping("doDeleteObject")
    @ResponseBody
    public JsonResult doDeleteObject(Integer id){
        sysDeptService.deleteObject(id);
        return new JsonResult("删除成功");
    }


    @RequestMapping("doFindObjects")
    @ResponseBody
    public JsonResult doFindObjects(){
        return new JsonResult(sysDeptService.findObjects());
    }






}
