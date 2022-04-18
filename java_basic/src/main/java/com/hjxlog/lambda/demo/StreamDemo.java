package com.hjxlog.lambda.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description:
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Author> authors = getAuthors();
        System.out.println(authors);

    }

    // 初始化一些数据
    private static List<Author> getAuthors() {

        Author author1 = new Author(1L, "aa", "my introduction 1", 18, null);
        Author author2 = new Author(2L, "bb", "my introduction 2", 19, null);
        Author author3 = new Author(3L, "cc", "my introduction 3", 14, null);
        Author author4 = new Author(4L, "dd", "my introduction 4", 29, null);
        Author author5 = new Author(5L, "ee", "my introduction 5", 12, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        // 上面是作者和书
        books1.add(new Book(1L, "类别,分类啊", "书名1", 45D, "这是简介哦"));
        books1.add(new Book(2L, "高效", "书名2", 84D, "这是简介哦"));
        books1.add(new Book(3L, "喜剧", "书名3", 83D, "这是简介哦"));

        books2.add(new Book(5L, "天啊", "书名4", 65D, "这是简介哦"));
        books2.add(new Book(6L, "高效", "书名5", 89D, "这是简介哦"));

        books3.add(new Book(7L, "久啊", "书名6", 45D, "这是简介哦"));
        books3.add(new Book(8L, "高效", "书名7", 44D, "这是简介哦"));
        books3.add(new Book(9L, "喜剧", "书名8", 81D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books3);
        author4.setBookList(books3);
        author5.setBookList(books2);

        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author5));
    }

}
