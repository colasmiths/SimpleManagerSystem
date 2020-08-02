package com.demo.cgb.pj.test;

import org.openjdk.jol.info.ClassLayout;

public class Test {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        o.hashCode();
    }

}
