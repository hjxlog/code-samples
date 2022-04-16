package com.hjxlog.collection.list;

import java.util.ArrayList;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 * <p>
 * 说明：
 * 1. class ArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable
 * 实现RandomAccess，代表这个类具备随机访问功能
 *
 *
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add("aa");
        System.out.println(list);
    }

}
