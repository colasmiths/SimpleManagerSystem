package com.demo.cgb.pj.common.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Node implements Serializable {

    private static final long serialVersionUID = -8035928111049328300L;

    //SysDept的id
    private Integer id;

    //SysDept的name
    private String  name;

    //SysDept的parentId
    private Integer parentId;
}
