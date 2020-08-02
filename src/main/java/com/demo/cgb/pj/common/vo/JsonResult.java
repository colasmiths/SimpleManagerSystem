package com.demo.cgb.pj.common.vo;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 为业务层返回的数据添加状态信息
 * 分别为正常数据和异常数据
 */
@Data
@NoArgsConstructor
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 677048178703375661L;
    //状态码
    private int state = 1;//1表示success,0表示error
    //状态信息
    private String message = "ok";
    //正确数据
    private Object data;

    public JsonResult(String message) {
        this.message = message;
    }

    public JsonResult(Object data) {
        this.data = data;
    }

    public JsonResult(Throwable t){
        this.state = 0;
        this.message = t.getMessage();
    }
}
