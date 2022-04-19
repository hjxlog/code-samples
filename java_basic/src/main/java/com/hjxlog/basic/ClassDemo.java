package com.hjxlog.basic;



/**
 * @Author: Huang JX
 * @Date: 2022/04/19
 * @Description:
 */
public class ClassDemo implements Animal {
    @Override
    public void sound() {

    }
//    @Override
//    protected void sound() {   //子类的权限不能比父类更低
//
//    }
}

interface Animal{
    void sound();
}