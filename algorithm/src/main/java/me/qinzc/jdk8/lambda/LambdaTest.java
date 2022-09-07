package me.qinzc.jdk8.lambda;

import org.junit.Before;
import org.junit.Test;
import sun.awt.image.IntegerComponentRaster;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * desc :
 * 布尔表达式            (List<String> list) -> list.isEmpty()                Predicate<List<String>>
 * 创建对象             () -> new Apple(10)                                  Supplier<Apple>
 * 消费一个对象           (Apple a) ->  System.out.println(a.getWeight())     Consumer<Apple>
 * 从一个对象中选择/提取  (String s) -> s.length()                               Function<String, Integer>或ToIntFunction<String>
 * 合并两个值            (int a, int b) -> a * b                               IntBinaryOperator
 * 比较两个对象           (Apple a1, Apple a2) ->                             Comparator<Apple>或
 *                      a1.getWeight().compareTo(a2.getWeight())            BiFunction<Apple, Apple, Integer>或
 *                                                                          ToIntBiFunction<Apple, Apple>
 *
 * @author Zane Qin
 * creatTime : 13:12 2022/7/14
 * modifier:
 * modifyTime:
 */
public class LambdaTest {
    List<Apple> apples = new ArrayList<>();
    @Before
    public void init() {
        apples.add(new Apple("red", 300));
        apples.add(new Apple("green", 240));
        apples.add(new Apple("red", 80));
        apples.add(new Apple("green", 100));
    }

    @Test
    public void basicLambda() {
        // jdk8 之前的写法
        Comparator<Apple> byWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        };
        // lambda 基础写法
        Comparator<Apple> byWeightLambda = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        // lambda 方法引用
        Comparator<Apple> byWeightLambdaMethod = Comparator.comparing(Apple::getWeight);

        // 具有一个string类型的参数并返回一个int。 lambda没有return语句，因为已经隐含了return
        // 从一个对象抽取
        Consumer<String> consumer = (String s) -> s.length();
        // 有一个Apple类型的参数并且返回一个boolean
        Function<Apple, Boolean> function = (Apple a) -> a.getWeight() > 150;
        // 2个int入参没有返回值
        IntBinaryOperator binaryOperator = (int x, int y) -> {
            System.out.println("result:");
            System.out.println(x + y);
            return 0;
        };
        // 没有参数返回一个int
        IntSupplier supplier = () -> 42;
        // 2个Apple类型，返回一个int 比较种类
        Comparator<Apple> comparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        // 创建实例
        Supplier<Apple> apple = () -> new Apple();
        Supplier<Apple> apple1 = Apple::new;
        // 组合2个值
        IntBinaryOperator c = (int a, int b) -> a * b;
    }

    @Test
    public void whereToUse() {
        List<Apple> greenApples = apples.stream().filter(a -> "green".equals(a.getColor())).collect(Collectors.toList());
        System.out.println(greenApples);
        // T -> R
        Function<String, Boolean> function = s -> false;
        // (int,int) -> int
        IntBinaryOperator intBinaryOperator = (int i, int i2) -> i + i2;
        // T -> void
        Consumer<String> consumer = s -> System.out.println(s);
        // () -> T
        Supplier<String> supplier = () -> "supplier";
        Callable<String> callable = () -> "callable";
        // (T,U) -> R
        BiFunction<Integer, String, Boolean> biFunction = (i1, s2) -> (i1 + s2).length() > 10;
    }

    /**
     * 方法引用
     */
    @Test
    public void methodReference() {
        apples.sort(Comparator.comparing(Apple::getWeight));

        // 以下等效
//        (Apple a) -> a.getWeight() = Apple::getWeight
//        () -> Thread.currentThread().getId() = Thread.currentThread()::getId
//        (str, i) -> str.substring(i) = String::substring
//        (String s) -> System.out.println(s) = System.out::println
        // 静态方法引用
//        Integer::parseInt;
        // 指向任意实例方法的方法引用
//        String::length;
        // 指向现有对象的实例方法的方法引用
//        LambdaTest lambdaTest = new LambdaTest();
//        lambdaTest::basicLambda;
    }

    /**
     * 构造函数引用
     */
    @Test
    public void constructorReference(){
        Supplier<Apple> c1 = Apple::new;
        Apple a1 = c1.get();
        BiFunction<String, Integer, Apple> c2 = Apple::new;
        Apple a2 = c2.apply("green", 24);
        List<Integer> weights = Arrays.asList(3, 7, 4, 190);
        List<String> colors = Arrays.asList("green", "yellow", "green", "red");
        List<Apple> apples = map(weights, colors, c2);
        System.out.println(apples);
    }

    /**
     * 符合表达式
     */
    @Test
    public void complexLambda() {
        // 复合比较器
        // 逆序
        apples.sort(Comparator.comparing(Apple::getWeight).reversed());
        // 比较器链
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                // 如果一样重时，按照颜色排序
                .thenComparing(Apple::getColor));
        // 谓词复合
        // 从左向右确定优先级 因此 a.or(b).and(c) 可以看做 (a || b) && c
        Predicate<Apple> redApple = a -> "red".equals(a.getColor());
        // 否定复合谓词
        Predicate<Apple> notRedApple = redApple.negate();
        // and 组合
        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
        // and 组合 or 组合
        Predicate<Apple> redAndHeavyAppleOrGreen = redAndHeavyApple.or(a -> "green".equals(a.getColor()));
        List<Apple> collect = apples.stream().filter(redAndHeavyAppleOrGreen).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(apples);
        // 函数复合
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        // then先 计算fx 在计算gx = g(f(x))
        Function<Integer, Integer> h = f.andThen(g);
        // compose 先计算gx 在计算fx  = f(g(x))
        Function<Integer, Integer> j = f.compose(g);
        //
        Consumer<Function<Integer, Integer>> c = (i) -> {
            System.out.println(i.apply(1));
            System.out.println(i.apply(2));
            System.out.println(i.apply(3));
            System.out.println(i.apply(4));
            System.out.println(i.apply(5));
        };
        c.accept(h);
        c.accept(j);
    }

    @Test
    public void complexLambdaDemo() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline = addHeader
                .andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        System.out.println(transformationPipeline.apply("im the best"));
    }

    public static class Letter{
        public static String addHeader(String text) {
            return "From Raoul, Mario and Alan:" + text;
        }
        public static String addFooter(String text) {
            return text + " Kind regards";
        }

        public static String checkSpelling(String text) {
            return text.replace("labda", "lambda");
        }
    }

    public static List<Apple> map(List<Integer> weights, List<String> colors, BiFunction<String, Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++) {
            result.add(function.apply(colors.get(i), weights.get(i)));
        }
        return result;
    }


    public static class Apple {
        private String color;
        private Integer weight;

        public Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        public Apple() {
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Apple.class.getSimpleName() + "[", "]")
                    .add("color='" + color + "'")
                    .add("weight=" + weight)
                    .toString();
        }
    }

}
