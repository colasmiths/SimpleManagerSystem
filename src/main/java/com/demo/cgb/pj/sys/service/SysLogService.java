package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.vo.PageObject;
import com.demo.cgb.pj.sys.entity.SysLog;

public interface SysLogService {

    PageObject<SysLog> findPageObjects(String username,Integer pageCurrent);

    int deleteObjects(Integer[] ids);
}
