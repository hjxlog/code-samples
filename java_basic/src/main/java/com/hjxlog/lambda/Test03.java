package com.hjxlog.lambda;

import java.util.function.Function;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description:
 */
public class Test03 {

    public static void main(String[] args) {
//        Integer result = typeConver(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                return Integer.valueOf(s);
//            }
//        });
//        System.out.println(result);

        /**
         * lambda
         */
        Integer result = typeConver((String s) -> {
            return Integer.valueOf(s);
        });
        System.out.println(result);


    }

    /**
     * 方法泛型
     * typeConver 只有一个抽象方法
     * T 是泛型的参数类型
     * R 是泛型的结果类型
     */
    public static <R> R typeConver(Function<String, R> function) {
        String str = "1235";
        R result = function.apply(str);
        return result;
    }

}
