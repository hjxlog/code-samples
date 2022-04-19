package com.hjxlog.test;

/**
 * @Author: Huang JX
 * @Date: 2022/04/19
 * @Description:
 */
public class Test01 {

    public static void main(String[] args) {

        double a = 0.9239;
        double b = 0.9239d;
        float c = 0.9239f;
//        float d = 0.9239;
        System.out.println(0.3-0.1);  // 不是0.2！！   无限循环小数
        long ss = 1245L;
//        byte xx = (byte) 128;   byte 等于或者超过128会报错
        byte xx = 12;

    }

//    public void loop() {
//        int x= 10;
//        while ( x )  {  // 只能接收boolean值，不能用int > 0啥的来代替
//            System.out.print("x minus one is " + (x - 1));
//            x -= 1;
//        }
//    }

}
