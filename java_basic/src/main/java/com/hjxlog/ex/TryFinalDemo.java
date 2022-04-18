package com.hjxlog.ex;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description:
 */
public class TryFinalDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(fun());
    }

    /**
     * ====finally====
     * Exception in thread "main" java.lang.ArithmeticException: / by zero
     * <p>
     * 先走finally再抛出异常，finally有处理的话，就会处理，如果没有的话，异常就会往上面抛出
     */
    private static String fun() throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception ex) {
            throw ex;
        } finally {
            System.out.println("====finally====");
        }
        return "aa";
    }

}
