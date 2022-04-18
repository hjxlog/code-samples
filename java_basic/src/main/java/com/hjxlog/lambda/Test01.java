package com.hjxlog.lambda;

import java.util.function.IntBinaryOperator;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description: lambda简化
 * 如下面的例子：简化的原则，是一个接口，并且接口里面 只有 一个方法，就可以；
 * 要设置语言到level-8
 *
 * lambda一开始的作用，就是简化匿名内部类的
 */
public class Test01 {

    public static void main(String[] args) {
        /**
         * 简化之前
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello world");
//            }
//        }).start();
        /**
         * 简化之后
         */
//        new Thread(() -> {
//            System.out.println("hello world");
//        }).start();

        /**
         * 例子1
         * 1. 先采用匿名内部类的方法
         *
         * 过程：
         * 1. 调用calculateNum，需要的参数是IntBinaryOperator
         * 2. 传入的是匿名内部类，实际上调用的是重写的方法
         * 3. 传入的参数是20 20
         *
         * lambda就是优化某一些匿名内部类的
         */
//        int i = calculateNum(new IntBinaryOperator() {
//            @Override
//            public int applyAsInt(int left, int right) {
//                return left + right;
//            }
//        });
//        System.out.println(i);

        /**
         * 只关注参数和方法体，把参数和方法体拷贝下来，增加一个箭头
         */
//        int i = calculateNum((int left, int right) -> {
//            return left + right;
//        });
//        System.out.println(i);

        /**
         * 再省略
         */
        int i = calculateNum((left, right) -> left + right);
        System.out.println(i);

    }

    /**
     * @FunctionalInterface public interface IntBinaryOperator {
     * int applyAsInt(int left, int right);
     * }
     * <p>
     * 特征：
     * 1. 接口
     * 2. 只有一个方法
     */
    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;
        return operator.applyAsInt(a, b);
    }

}
