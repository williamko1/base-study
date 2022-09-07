package me.qinzc.jdk8.lambda;

import org.junit.Test;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 09:37 2022/8/11
 * modifier:
 * modifyTime:
 */
public class StreamPractice {


    /**
     * 5.5.1
     */
    @Test
    public void practice() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transactions2011 = transactions.stream().filter(e -> e.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue).reversed()).collect(Collectors.toList());
        System.out.println(transactions2011);
        // (2) 交易员都在哪些不同的城市工作过？
        List<String> traderCities = transactions.stream().map(e -> e.getTrader().getCity()).distinct().collect(Collectors.toList());
        System.out.println(traderCities);
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> cambridgeTraders = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(cambridgeTraders);
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        String traderNames = transactions
                .stream()
                .map(e -> e.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(","));
        System.out.println(traderNames);
        // (5) 有没有交易员是在米兰工作的？
        boolean hasAlanTrader = transactions.stream().anyMatch(e -> "Milan".equals(e.getTrader().getCity()));
        System.out.println(hasAlanTrader);
        // (6) 打印生活在剑桥的交易员的所有交易额。
        transactions
                .stream()
                .filter(e -> "Cambridge".equals(e.getTrader().getCity()))
                .forEach(e -> System.out.println(e.getValue()));
        // (7) 所有交易中，最高的交易额是多少？
        int max = transactions.stream().max(Comparator.comparing(Transaction::getValue)).get().getValue();
        System.out.println(max);
        Integer reduceMax = transactions.stream().map(Transaction::getValue).reduce(0, Integer::max);
        System.out.println(reduceMax);
        // (8) 找到交易额最小的交易。
        int min = transactions.stream().min(Comparator.comparing(Transaction::getValue)).get().getValue();
        System.out.println(min);
    }


    public static class Trader {
        private final String name;
        private final String city;

        public Trader(String n, String c) {
            this.name = n;
            this.city = c;
        }

        public String getName() {
            return name;
        }

        public String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Trader.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("city='" + city + "'")
                    .toString();
        }
    }
    public static class Transaction{
        private final Trader trader;
        private final int year;
        private final int value;
        private Currency currency;

        public Currency getCurrency() {
            return currency;
        }

        public Transaction(Trader trader, int year, int value) {
            this.trader = trader;
            this.year = year;
            this.value = value;
        }

        public Transaction(Currency currency){
            this.trader = new Trader("init", "init");
            this.year = 2012;
            this.value = (int)(Math.random() *100);
            this.currency = currency;
        }

        public Trader getTrader() {
            return trader;
        }

        public int getYear() {
            return year;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Transaction.class.getSimpleName() + "[", "]")
                    .add("trader=" + trader)
                    .add("year=" + year)
                    .add("value=" + value)
                    .add("currency=" + currency.getCurrencyCode())
                    .toString();
        }
    }

    /**
     * 5.6 数值流
     */
    @Test
    public void numberStream() {
        List<Menu> menus = Arrays.asList(
                new Menu(200, "香蕉"),
                new Menu(150, "鸡蛋"),
                new Menu(633, "汉堡"),
                new Menu(300, "薯条"),
                new Menu(51, "苹果")
        );
        int calories = menus.stream().mapToInt(Menu::getCalories).sum();
        int maxCalories = menus.stream().mapToInt(Menu::getCalories).max().orElse(0);
        int minCalories = menus.stream().mapToInt(Menu::getCalories).min().orElse(0);
        System.out.println(calories);
        System.out.println(maxCalories);
        System.out.println(minCalories);
        // 原始类型流特化
        IntStream intStream = menus.stream().mapToInt(Menu::getCalories);
        // 转换为对象流
        Stream<Integer> boxed = intStream.boxed();
        // 数值范围
        System.out.println(IntStream.rangeClosed(1, 100).filter(e -> e % 2 == 0).count());


        // 应用 勾股数
        System.out.println("-------------勾股数------------------");
        int a1 = 5;
        // 转回对象
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a1 * a1 + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a1, b, (int) Math.sqrt(a1 * a1 + b * b)})
                .forEach(e -> System.out.println(Arrays.toString(e)));

        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a1 * a1 + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a1, b, (int) Math.sqrt(a1 * a1 + b * b)})
                .forEach(e -> System.out.println(Arrays.toString(e)));

        int[] origins = new int[]{5, 10, 25};

        for (int a : origins) {
        IntStream.rangeClosed(1, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .forEach(e -> System.out.println(Arrays.toString(e)));
        }

        Arrays.stream(origins)
                .forEach(a -> IntStream.rangeClosed(1, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .boxed()
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        .forEach(e -> System.out.println(Arrays.toString(e))));

        System.out.println("--------------自动生成1-100的a 生成勾股定理 ---------------------");
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b ->
                                        new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                ).forEach(e -> System.out.println(Arrays.toString(e)));
        System.out.println("--------------更优解 少算一遍开方----------------------------------");
        IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream
                                .rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0).map(t -> new int[]{(int)t[0],(int)t[1],(int)t[2]})
                ).forEach(e -> System.out.println(Arrays.toString(e)));
        System.out.println("---------------斐波那契数列--------------");
        // 无限流
        Stream.iterate(new int[]{0, 1},
                        t -> new int[]{t[1], t[0]+t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));

        int[] ints = Stream.iterate(new int[]{0, 1},
                        t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .mapToInt(t -> t[0])
                .toArray();
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 收集器
     *
     */
    @Test
    public void collector() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(Currency.getInstance("CNY")),
                new Transaction(Currency.getInstance("CNY")),
                new Transaction(Currency.getInstance("CNY")),
                new Transaction(Currency.getInstance("CNY")),
                new Transaction(Currency.getInstance("CNY")),
                new Transaction(Currency.getInstance("AUD")),
                new Transaction(Currency.getInstance("AUD")),
                new Transaction(Currency.getInstance("AUD")),
                new Transaction(Currency.getInstance("USD"))
        );
        Map<Currency, List<Transaction>> transactionsByCurrencies =
                transactions.stream().collect(Collectors.groupingBy(Transaction::getCurrency));
        transactionsByCurrencies.entrySet().forEach(System.out::println);


        List<Menu> menus = Arrays.asList(
                new Menu(200, "香蕉"),
                new Menu(150, "鸡蛋"),
                new Menu(633, "汉堡"),
                new Menu(300, "薯条"),
                new Menu(51, "苹果")
        );

        Optional<Menu> mostCalorieDish =
                menus.stream().collect(Collectors.reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));


        int totalCalories1 = menus.stream().collect(Collectors.reducing(0, Menu::getCalories, (i, j) -> i + j));
        int totalCalories3 = menus.stream().collect(Collectors.reducing(0, Menu::getCalories, Integer::sum));
        int totalCalories2 = menus.stream().map(Menu::getCalories).reduce(0, Integer::sum);
        int totalCalories4 = menus.stream().mapToInt(Menu::getCalories).sum();
        int totalCalories5 = menus.stream().flatMapToInt(e -> IntStream.of(e.getCalories())).sum();
        System.out.println(totalCalories1);
        System.out.println(totalCalories2);
        System.out.println(totalCalories3);
        System.out.println(totalCalories4);
        System.out.println(totalCalories5);

        // 多级分组
        Map<String, Map<String, List<Menu>>> collect = menus.stream()
                .collect(Collectors.groupingBy(Menu::getName,
                        Collectors.groupingBy(e -> {
                            if (e.getCalories() < 100) {
                                return "low";
                            } else if (e.getCalories() < 200) {
                                return "medium";
                            } else if (e.getCalories() >= 300) {
                                return "high";
                            }

                            return "breast";
                        })));
        System.out.println(collect);
        Map<String, Long> collect1 = menus.stream()
                .collect(
                        Collectors.groupingBy(e -> {
                            if (e.getCalories() < 100) {
                                return "low";
                            } else if (e.getCalories() < 200) {
                                return "medium";
                            } else if (e.getCalories() >= 300) {
                                return "high";
                            }

                            return "breast";
                        }, Collectors.counting()));
        System.out.println(collect1);
        // 1. 把收集器的结果转换为另一种类型
        // collectingAndThen()
        // 2. 与groupingBy联合使用的其他收集器的例子
        Map<String, Integer> totalCaloriesByType =
                menus.stream().collect(Collectors.groupingBy(Menu::getName,
                        Collectors.summingInt(Menu::getCalories)));
        System.out.println(totalCaloriesByType);

        // 分区 Collectors.partitioningBy(Predicate)

    }

    public static class Menu {
        private final int calories;
        private final String name;

        public Menu(int calories, String name) {
            this.calories = calories;
            this.name = name;
        }

        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Menu.class.getSimpleName() + "[", "]")
                    .add("calories=" + calories)
                    .add("name='" + name + "'")
                    .toString();
        }
    }
}
