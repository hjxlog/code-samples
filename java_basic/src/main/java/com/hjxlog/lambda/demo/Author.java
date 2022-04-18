package com.hjxlog.lambda.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Author {

    private Long id;
    private String name;
    private String introduction;
    private Integer age;
    private List<Book> bookList;

}
