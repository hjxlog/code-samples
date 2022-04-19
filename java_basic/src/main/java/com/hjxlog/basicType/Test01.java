package com.hjxlog.basicType;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

/**
 * @Author: Huang JX
 * @Date: 2022/04/19
 * @Description:
 */
public class Test01 {

    public static void main(String[] args) {
//        int x = 1;
//        float y = 2; // float 不用f也可以不可以！！！
////        float ys=0.23; // float 不用f也可以不可以！！！  0.23 默认是double的，所以如果要定义float，需要指明f
//        double z = 0.2312421432; //double 不用d也可以
//        float v = x / y;
//        float s = 0.123f;
//        float dsf = 123;
//        System.out.println(v);
//
//        double v1 = x / z;
//        System.out.println(v1);

        System.out.println(0.3-0.1);

    }

}

//abstract final class A{ // abstract final 不能同时用
//
//}

abstract class A {

}

abstract class B extends A {
    public void fun() {
        System.out.println("fun()");
    }
}