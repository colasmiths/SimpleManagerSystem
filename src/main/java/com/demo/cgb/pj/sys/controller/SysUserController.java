package com.demo.cgb.pj.sys.controller;

import com.demo.cgb.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class SysUserController {
    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("doUserListUI")
    public String doUserListUI(){
        return "sys/user_list";
    }
}
