package com.hjxlog.lambda.demo;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: Huang JX
 * @Date: 2022/04/18
 * @Description: 总结：
 * 1. map mapToInt 可以避免装箱拆箱
 */
public class StreamDemo {

    public static void main(String[] args) {


        test22();
//        test21();
//        test20();
//        test19();
//        test18();
//        test17();
//        test16();
//        test15();
//        test14();
//        test13();
//        test12();
//        test11();
//        test10();
//        test9();
//        test8();
//        test7();
//        test6();
//        test5();
//        test4();
//        test3();
//        test2();
//        test1_2();
//        test1_1();
//        test1();
    }

    private static void test22() {
        // 使用并行流
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = stream
                .parallel()   // .parallel()调用这个方法，就可以并行处理，数据量大的情况下，可以使用
                .peek(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer num) {
                        System.out.println(num+"====>"+Thread.currentThread().getName());
                    }
                })   // 这个可以用来调试用
                .filter(num -> num > 5)
                .reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result + element;
                    }
                }).get();
        System.out.println(sum);

    }

    private static void test21() {
        //使用reduce求作者年龄最大值 使用一个参数的重载
        List<Author> authors = getAuthors();
        Optional<Integer> optionalInteger = authors.stream()
                .map(author -> author.getAge())
                .reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result < element ? element : result;
                    }
                });
        optionalInteger.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    private static void test20() {
        //使用reduce求作者年龄最大值
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result < element ? element : result;
                    }
                });
        System.out.println(reduce);
    }

    private static void test19() {
        //使用reduce求所有作者的年龄和
        List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(new Function<Author, Integer>() {
                    @Override
                    public Integer apply(Author author) {
                        return author.getAge();
                    }
                })
                .reduce(0, new BinaryOperator<Integer>() {  // identity就是初始值，new BinaryOperator
                    @Override
                    public Integer apply(Integer result, Integer element) { // 得到的结果，加element max min就是封装了reduce
                        return result + element;
                    }
                });
        System.out.println(reduce);

    }

    private static void test18() {
        // findFirst 获取一个年龄最小的作家，并输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> author = authors.stream()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o1.getAge() - o2.getAge();
                    }
                })
                .findFirst();
        System.out.println(author.get());
    }

    private static void test17() {
        // findAny 获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> author = authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 18;
                    }
                }).findAny();
        //  // any是一个对象，任意匹配到的一个对象
        author.ifPresent(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author.getName());
            }
        });
    }

    private static void test16() {
        //noneMatch 全都不符合
        List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .noneMatch(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 60;
                    }
                });
        System.out.println(b);

    }

    private static void test15() {
        // allMatch 判断所有作家都是成年人
        List<Author> authors = getAuthors();
        System.out.println(authors.stream().allMatch(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 18;
            }
        }));
    }

    private static void test14() {
        // anyMatch 判断是否有年龄在29以上的作家
        List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .anyMatch(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 29;
                    }
                });
        System.out.println(b);

    }

    private static void test13() {
        // 获取一个map集合，key为作者名，value为List<Book>
        List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                .distinct()
                .collect(Collectors.toMap(new Function<Author, String>() {
                                              @Override
                                              public String apply(Author author) {
                                                  return null;
                                              }
                                          }
                        , new Function<Author, List<Book>>() {
                            @Override
                            public List<Book> apply(Author author) {
                                return author.getBookList();
                            }
                        }));
        System.out.println(collect);
    }

    private static void test12() {
        // 获取所有书名的set集合
        List<Author> authors = getAuthors();
        Set<Object> set = authors.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBookList().stream();
                    }
                })
                .map(new Function<Book, Object>() {
                    @Override
                    public Object apply(Book book) {
                        return book.getName();
                    }
                })
                .collect(Collectors.toSet());
        System.out.println(set);
    }

    private static void test11() {
        // // 获取所有作者名字合集list
        List<Author> authors = getAuthors();
        List<String> list = authors.stream()
                .map(author -> author.getName())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);

    }

    private static void test10() {
        // 获取所有作者 书籍名字合集list, set, map
        List<Author> authors = getAuthors();
//        authors.stream()
//                .distinct()
//                .map(new Function<Author, String>() {  // new Function<Author, String>  传入Author，返回的是String
//                    @Override
//                    public String apply(Author author) {
//                        return author.getName();
//                    }
//                })
        List<Object> collect = authors.stream()
                .flatMap(new Function<Author, Stream<?>>() {
                    @Override
                    public Stream<?> apply(Author author) {
                        return author.getBookList().stream();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(collect);

    }

    private static void test9() {
        //分别获取这些作家的所出书籍的最高分和最低分并打印
        List<Author> authors = getAuthors();
        OptionalDouble max = authors.stream()
                .distinct()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBookList().stream();
                    }
                })
                .mapToDouble(new ToDoubleFunction<Book>() {
                    @Override
                    public double applyAsDouble(Book value) {
                        return value.getScore();
                    }
                })
                .max();

        double asDouble = max.getAsDouble();
        System.out.println(asDouble);
    }

    private static void test8() {
        // 打印现有数据的所有分类，去重，不能出现格式 哲学,爱情 这种拼接的
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(new Function<Author, Stream<Book>>() {
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBookList().stream();
                    }
                })
                .flatMap(new Function<Book, Stream<String>>() {  // 有种一对多的感觉
                    @Override
                    public Stream<String> apply(Book book) {
                        return Arrays.stream(book.getCategory().split(",").clone());
                    }
                })
                .distinct().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    private static void test7() {
        //打印所有书籍的名字，去重
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(new Function<Author, Stream<Book>>() {  // 这里的泛型要明确好
                    @Override
                    public Stream<Book> apply(Author author) {
                        return author.getBookList().stream();
                    }
                })
                .distinct()
                .forEach(new Consumer<Book>() {
                    @Override
                    public void accept(Book book) {
                        System.out.println(book.getName());
                    }
                });
    }

    private static void test6() {
        //年龄最大的作家外，不能重复，年龄降序
        List<Author> authors = getAuthors();
        authors.stream()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .distinct()
                .skip(1).forEach(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author);
            }
        });
    }

    private static void test5() {
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() < 18;
                    }
                })
                .map(new Function<Author, Object>() {
                    @Override
                    public Object apply(Author author) {
                        return author.getName();
                    }
                })
                .distinct()
                .forEach(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) {
                        System.out.println(o);
                    }
                });
    }

    private static void test4() {
        Integer[] arr = {1, 2, 3, 4};
//        Arrays.stream(arr);
        Stream<Integer> arr1 = Stream.of(arr); // 利用Stream的静态方法
        arr1.forEach(System.out::println);
        Stream<Integer> arr2 = Arrays.stream(arr);
    }

    private static void test3() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("波吉", 15);
        map.put("卡克", 14);
        map.put("波斯", 13);
        map.entrySet()
                .forEach(new Consumer<Map.Entry<String, Integer>>() {
                    @Override
                    public void accept(Map.Entry<String, Integer> entry) {
                        System.out.println(entry.getKey());
                        System.out.println(entry.getValue());
                    }
                });
    }

    private static void test2() {
        // 年龄降序，不能重复，打印年龄最大的两个作家名字
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .limit(2)
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });

    }

    private static void test1_2() {
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复元素
        List<Author> authors = getAuthors();
        authors.stream()
                .mapToInt(new ToIntFunction<Author>() {
                    @Override
                    public int applyAsInt(Author value) {  // 转换为int，避免装箱拆箱
                        return value.getAge();
                    }
                })
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    private static void test1_1() {
        // 对流中的元素按照年龄进行降序排序，并且要求不能有重复元素
        List<Author> authors = getAuthors();
        authors.stream()
                .map(new Function<Author, Integer>() { // 先获取年龄出来，再处理
                    @Override
                    public Integer apply(Author author) {
                        return author.getAge();
                    }
                })
                .distinct()
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {  // 这里有
                        return o1 - o2;
                    }
                })
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                });

    }

    private static void test1() {
// 对流中的元素按照年龄进行降序排序，并且要求不能有重复元素
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o1.getAge() - o2.getAge();
                    }
                }).forEach(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println(author);
            }
        });
    }


    // 初始化一些数据
    private static List<Author> getAuthors() {

        Author author1 = new Author(1L, "布赖恩·费瑟斯通豪", "my introduction 1", 18, null);
        Author author2 = new Author(2L, "阿尔贝·加缪", "my introduction 2", 19, null);
        Author author3 = new Author(3L, "安托万", "my introduction 3", 14, null);
        Author author4 = new Author(4L, "乔治·奥威尔", "my introduction 4", 29, null);
        Author author5 = new Author(5L, "鲁迅", "my introduction 5", 12, null);
        Author author6 = new Author(5L, "鲁迅", "my introduction 5", 12, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        // 上面是作者和书
        books1.add(new Book(1L, "类别,分类啊", "娱乐至死", 45D, "这是简介哦"));
        books1.add(new Book(2L, "职业规划,社会学", "论人类不平等的起源和基础", 84D, "这是简介哦"));
        books1.add(new Book(3L, "喜剧", "远见", 83D, "这是简介哦"));

        books2.add(new Book(5L, "高中文教,语言文字", "看不见的城市", 65D, "这是简介哦"));
        books2.add(new Book(6L, "自然科学,高效", "追风筝的人", 89D, "这是简介哦"));

        books3.add(new Book(7L, "社会科学", "动物庄园", 45D, "这是简介哦"));
        books3.add(new Book(8L, "历史地理,高效", "彷徨", 44D, "这是简介哦"));
        books3.add(new Book(9L, "喜剧", "朝花夕拾", 81D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books3);
        author4.setBookList(books3);
        author5.setBookList(books2);
        author6.setBookList(books2);

        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author5, author6));
    }

}
