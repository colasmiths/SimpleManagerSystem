package com.demo.cgb.pj.common.util;

import com.demo.cgb.pj.common.vo.PageObject;

import java.util.List;

public class PageUtil {
    private static int pageSize = 3;

    public static int getPageSize(){
        return pageSize;
    }
    //此方法暂时没有理解什么意思
    public static int getStartIndex(Integer pageCurrent){
        return (pageCurrent-1)*pageSize;
    }

    public static <T>PageObject<T> newPageObject(Integer pageCurrent, int rowCount, int pageSize, List<T> records){
        PageObject<T> po = new PageObject<>();
        po.setRowCount(rowCount);   //设置总行数
        po.setPageCount((rowCount-1)/pageSize+1);   //设置总页数
        po.setRecords(records);
        po.setPageCurrent(pageCurrent);
        po.setPageSize(pageSize);

        return po;

    }
}
