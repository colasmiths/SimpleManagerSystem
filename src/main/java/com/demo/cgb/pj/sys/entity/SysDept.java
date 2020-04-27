package com.demo.cgb.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysDept implements Serializable {

    private static final long serialVersionUID = -5259265803332215029L;


    private Integer id;

    //部门名
    private String name;


    private Integer parentId;


    private Integer sort;


    private String note;


    private Date createdTime;


    private Date modifiedTime;


    private String createdUser;


    private String modifiedUser;
}
