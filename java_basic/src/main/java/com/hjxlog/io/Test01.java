package com.hjxlog.io;

import java.io.PrintWriter;

/**
 * @Author: Huang JX
 * @Date: 2022/04/19
 * @Description:
 */
public class Test01 {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(
                new java.io.OutputStreamWriter(System.out), true);  // java.io.OutputStreamWriter 完整的路径，那就不需要导入
        out.println("Hello");
    }
}
