package com.demo.cgb.pj.sys.controller;

import com.demo.cgb.pj.common.vo.JsonResult;
import com.demo.cgb.pj.sys.entity.SysUser;
import com.demo.cgb.pj.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
@RequestMapping("/user/")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("doUserListUI")
    public String doUserListUI(){
        return "sys/user_list";
    }

    @RequestMapping("doUserEditUI")
    public String doUserEditUI(){
        return "sys/user_edit";
    }

    //
    @RequestMapping("doLogin")
    @ResponseBody
    public JsonResult doLogin(String username, String password){
        //1.封装用户数据，获取一个token令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //2.获取一个subject对象
        Subject subject = SecurityUtils.getSubject();
        //3.执行登录认证
        subject.login(token);
        return new JsonResult("login成功");

    }

    @RequestMapping("doSaveObject")
    @ResponseBody
    public JsonResult doSaveObject(SysUser entity,Integer[] roleIds){
        sysUserService.saveObject(entity,roleIds);
        return new JsonResult("insert成功");
    }

    @ResponseBody
    @RequestMapping("doValidById")
    public JsonResult doValidById(Integer id,Integer valid){
        sysUserService.validById(id,valid,"admin");
        return new JsonResult("update成功");
    }

    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(String username,Integer pageCurrent){
        return new JsonResult(sysUserService.findPageObjects(username,pageCurrent));
    }


    @RequestMapping("doFindObjectById")
    @ResponseBody
    public JsonResult doFindObjectById(Integer id){
        Map<String,Object> map = sysUserService.findObjectById(id);
        return new JsonResult(map);
    }


    @RequestMapping("doUpdateObject")
    @ResponseBody
    public JsonResult doUpdateObject(SysUser entity,Integer[] roleIds){
        sysUserService.updateObject(entity,roleIds);
        return new JsonResult("update成功");
    }





}
