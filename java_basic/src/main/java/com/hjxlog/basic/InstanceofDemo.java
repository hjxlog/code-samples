package com.hjxlog.basic;


interface MyInterface {
}
class FClass implements MyInterface {
}
class CClass extends FClass {
}

/**
 * instance是java的二元运算符，用来判断他左边的对象是否为右面类（接口，抽象类，父类）的实例
 */
public class InstanceofDemo {

    public static void main(String[] args) {
        MyInterface ab=new FClass();
        MyInterface ac=new CClass();
        FClass bc=new CClass();
        FClass bb=new FClass();
        CClass cc=new CClass();
        //对象实现一个接口，用这个对象和这个接口进行instanceof判断，都为true。
        System.out.println("ab instanceof A="+(ab instanceof MyInterface)); //true
        System.out.println("ac instanceof A="+(ac instanceof MyInterface)); //true
        System.out.println("bc instanceof A="+(bc instanceof MyInterface));//true
        System.out.println("bb instanceof A="+(bb instanceof MyInterface));//true
        System.out.println("cc instanceof A="+(cc instanceof MyInterface));//true
        //对象和父类进行instanceof判断，都为true
        System.out.println("ab instanceof B="+(ab instanceof FClass));
        System.out.println("ac instanceof B="+(ac instanceof FClass));
        System.out.println("bc instanceof B="+(bc instanceof FClass));
        System.out.println("bb instanceof B="+(bb instanceof FClass));
        System.out.println("cc instanceof B="+(cc instanceof FClass));
        //对象和他的子类进行instanceof判断为false
        System.out.println("ab instanceof C="+(ab instanceof CClass));
        System.out.println("ac instanceof C="+(ac instanceof CClass));
        System.out.println("bc instanceof C="+(bc instanceof CClass));
        System.out.println("bb instanceof C="+(bb instanceof CClass));
        System.out.println("cc instanceof C="+(cc instanceof CClass));
    }
}