package com.demo.cgb.pj.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageObject<T> implements Serializable {
    private static final long serialVersionUID = 2352271705547032307L;

    private Integer pageCurrent = 1;
    //页面大小
    private Integer pageSize = 3;
    //查询获得的总行数
    private Integer rowCount = 0;
    //计算获得的总页数
    private Integer pageCount = 0;
    //当前页的记录?
    private List<T> records;

}
