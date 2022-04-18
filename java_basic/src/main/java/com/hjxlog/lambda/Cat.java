package com.hjxlog.lambda;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description:
 */
public class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("print() ---> 猫吃草");
    }
}
