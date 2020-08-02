package com.demo.cgb.pj.common.vo;

import lombok.Data;

import java.io.Serializable;
//这个类有意思，需留意
@Data
public class Node implements Serializable {

    private static final long serialVersionUID = -8035928111049328300L;


    private Integer id;


    private String  name;


    private Integer parentId;
}
