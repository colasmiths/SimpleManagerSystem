package com.demo.cgb.pj.sys.service;

import com.demo.cgb.pj.common.vo.PageObject;

public interface PageService<T> {
    //执行分页查询
    PageObject<T> findPageObjects(String username, Integer pageCurrent);

}
