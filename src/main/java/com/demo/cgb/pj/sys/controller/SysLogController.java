package com.demo.cgb.pj.sys.controller;


import com.demo.cgb.pj.common.vo.JsonResult;
import com.demo.cgb.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/log/")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("doLogListUI")
    public String doLogListUI(){
        return "sys/log_list";
    }


    @ResponseBody
    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username,Integer pageCurrent){
        System.out.println("username="+username);
        return new JsonResult(sysLogService.findPageObjects(username, pageCurrent));

    }

    @ResponseBody
    @RequestMapping("doDeleteObjects")
    public JsonResult doDeleteObjects(Integer[] ids){
        int rows = sysLogService.deleteObjects(ids);
        return new JsonResult("成功删除，rows="+rows);
    }

}
