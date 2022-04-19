package com.hjxlog.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author: Huang JX
 * @date: 2022/4/16
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.addFirst("first");
        linkedList.addLast("last");
        linkedList.add(1, "hello");
        System.out.println(linkedList);

    }

}
