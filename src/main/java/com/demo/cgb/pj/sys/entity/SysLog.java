package com.demo.cgb.pj.sys.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class SysLog implements Serializable {

    private static final long serialVersionUID = 8924387722922123121L;


    private Integer id;


    /**用户名*/
    private String username;


    /**用户操作*/
    private String operation;


    /**请求方法*/
    private String method;


    /**请求参数*/
    private String params;


    /**执行时长(毫秒)*/
    private Long time;


    /**IP地址*/
    private String ip;


    /**创建时间*/
    private Date createdTime;
}
