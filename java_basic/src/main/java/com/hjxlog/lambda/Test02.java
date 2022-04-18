package com.hjxlog.lambda;

import java.util.function.IntPredicate;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description:
 */
public class Test02 {

    public static void main(String[] args) {

        /**
         * arr
         * 传入的是predicate
         * 走的是predicate.test(i)
         * 但是test()是被重写过的
         */
//        printNum(new IntPredicate() {
//            @Override
//            public boolean test(int value) {
//                return value % 2 == 0;
//            }
//        });

        /**
         * lambda表达式
         */
        printNum((int value) -> {
            return value % 2 == 0;
        });

    }

    public static void printNum(IntPredicate predicate) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        for (int i : arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }

}
