package com.hjxlog.test;

/**
 * @Author: Huang JX
 * @Date: 2022/04/19
 * @Description:
 */
public class Test02 {

    public static void main(String[] args) {
        int i = -5;
//        i =  ++(i++); 编译出错  //++（  ）  括号里面必须是一个变量，而 i ++  是一个字面量。
        // String 不是基本数据类型
        System.out.println(i);
    }

}
