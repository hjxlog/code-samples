package com.hjxlog.emuns;

/**
 * @Author: Huang JX
 * @Date: 2022/04/19
 * @Description:
 */
public class Test01 {

//    public int x;
//    public static void main(String []args)
//    {
//        System. out. println("Value is" + x); //非静态变量不能够被静态方法引用
//    }

    public static void main(String[] args) {
        System.out.println(AccountType.FIXED);
    }

}

enum AccountType {
    SAVING, FIXED, CURRENT,AA;

    // 枚举类有三个实例，故调用三次构造方法，打印三次It is a account type
    // 对每个都进行了一次初始化
    private AccountType() {
        System.out.println("It is a account type");
    }
}