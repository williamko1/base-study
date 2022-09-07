package me.qinzc.jdk8.streams;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * desc :
 *
 * 创建流              |           中间管道(每个环节操作之后都会返回一个新的stream对象)  |       终止stream,返回结果或者执行操作（结束后的流没法再用）
 * stream()           |     filter()                                             |      count()
 * Stream<T>.of(..)   |     map()                                                |      max()
 * parallelStream()   |     flatMap()                                            |      findFirst()
 *                          limit(n)                                             |      findAny()
 *                          skip(n)                                              |      anyMatch()
 *                          concat()                                             |      allMatch()
 *                          distinct()                                           |      noneMatch()
 *                          sorted()                                             |      collect()
 *                          peek()                                               |      toArray()
 *                                                                               |      iterator()
 *                                                                               |      foreach()
 *
 *
 *
 * @author Zane Qin
 * creatTime : 09:31 2022/7/13
 * modifier:
 * modifyTime:
 */
public class StreamTest {

    public static void main(String[] args) {
        StreamTest streamTest = new StreamTest();
        streamTest.stringToIntMap();
        streamTest.stringToIntFlatmap();
        streamTest.testPeekAndForeach();
        streamTest.testGetTargetUsers();
        streamTest.testNumberCalculate();
        System.out.println((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS")).format(new Date()));
    }

    /**
     * 一一对应
     * stream.map
     */
    public void stringToIntMap() {
        List<String> ids = Arrays.asList("205", "105", "308", "469", "627", "193", "111");

        List<User> results = ids.parallelStream().map(id -> {
            User user = new User();
            user.setId(id);
            return user;
        }).collect(Collectors.toList());
        System.out.println(results);
    }

    /**
     * 一对多
     */
    public void stringToIntFlatmap(){
        List<String> sentences = Arrays.asList("hello world","Jia Gou Wu Dao");
        List<String> result = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println(result);
    }

    public void testPeekAndForeach() {
        List<String> sentences = Arrays.asList("hello world", "Jia Gou Wu Dao");
        // 演示点1： 仅peek操作，最终不会执行
        System.out.println("----before peek----");
        sentences.stream().peek(s -> System.out.println(s));
        System.out.println("----after peek----");
        // 演示点2： 仅foreach操作，最终会执行
        System.out.println("----before foreach----");
        sentences.stream().forEach(System.out::println);
        System.out.println("----after foreach----");
        // 演示点3：peek操作后面增加终止操作，peek会执行
        System.out.println("----before peek end----");
        sentences.stream().peek(s -> System.out.println(s)).count();
        System.out.println("----after peek end----");
    }

    /**
     *  filter、sorted、distinct、limit
     *  中间方法测试
     */
    public void testGetTargetUsers() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        List<User> results = ids.stream()
                .filter(s -> s.length() > 2)
                .distinct()
                .map(Integer::valueOf)
                .sorted(Comparator.comparingInt(o -> o))
                .map(id -> {
                    User user = new User();
                    user.setId(String.valueOf(id));
                    return user;
                }).collect(Collectors.toList());
        System.out.println(results);
    }

    /**
     * 统计
     */
    public void testNumberCalculate() {
        List<Integer> ids = Arrays.asList(10, 20, 30, 40, 50);
        // 计算平均值
        Double average = ids.stream().collect(Collectors.averagingInt(value -> value));
        System.out.println("平均值：" + average);
        // 数据统计信息
        IntSummaryStatistics summary = ids.stream().collect(Collectors.summarizingInt(value -> value));
        System.out.println("数据统计信息： " + summary);
    }

    /**
     * 字符串拼接
     */
    public void testCollectJoinStrings() {
        List<String> ids = Arrays.asList("205", "10", "308", "49", "627", "193", "111", "193");
        String joinResult = ids.stream().collect(Collectors.joining(","));
        System.out.println("拼接后：" + joinResult);
    }

        public static class User{

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                    .add("id='" + id + "'")
                    .toString();
        }
    }

    @Test
    public void map() {
        List<String> words = Arrays.asList("java8", "lambdas", "IN", "action");
        List<Integer> wordsLength = words.stream().map(s -> s.length()).collect(Collectors.toList());
        System.out.println(wordsLength);
        // 还是不行
        List<Stream<String>> collect = words.stream().map(word -> word.split("")).map(e -> Arrays.stream(e)).distinct().collect(Collectors.toList());
        for (Stream<String> stringStream : collect) {
            stringStream.forEach(System.out::println);
        }
        List<String> uniqueCharacters = words.stream().map(w -> w.split("")).flatMap(e -> Arrays.stream(e)).distinct().collect(Collectors.toList());
        System.out.println(uniqueCharacters);
        // 平方
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(2, 3);
        // 成对
        System.out.println(numbers.stream().map(e -> e * e).collect(Collectors.toList()));
        List<int[]> result = numbers.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        result.forEach(i -> System.out.println(Arrays.toString(i)));
        // 过滤被3整除的对
        System.out.println("\n过滤被3整除的对");
        List<int[]> result2 = numbers.stream().flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j})).collect(Collectors.toList());
        result2.forEach(i -> System.out.println(Arrays.toString(i)));

    }

    @Test
    public void reduce(){
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 2, 4, 6, 8);
        int sum = numbers.stream().reduce(0, Integer::sum);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(sum);
        System.out.println(product);
    }

}
