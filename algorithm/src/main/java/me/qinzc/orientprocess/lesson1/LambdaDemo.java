package me.qinzc.orientprocess.lesson1;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * desc : lambda表达式
 * SCFP == Supplier + Consumer + Function + Predicate
 * 四种模式，缺少Action模式
 *
 * @author : Zane Qin
 * creatTime : 13:39 2020/4/9
 * modifier:
 * modifyTime:
 */
public class LambdaDemo {
    // Supplier模式 没输入有输出
    private static void showSupplier() {
        // 普通写法
        String string = "helloworld";
        //
        Supplier<String> stringSupplier = () -> "helloworld";
        Supplier<String> stringSupplier1 = () -> new Integer(2).toString();
    }

    // Consumer模式 有输入没输出
    private static void showConsumer() {
        // 传统写法 匿名内部类写法
        PropertyChangeListener listener1 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                println(evt);
            }
        };
        // lambda基本款
        PropertyChangeListener listener2 = evt -> System.out.println(evt);
        // 方法引用
        PropertyChangeListener listener3 = LambdaDemo::println;
    }

    // Function模式 有输入有输出
    private static void showFunction(){
        Function<String,Integer> function = LambdaDemo::compareTo;
    }

    private static Integer compareTo(String s) {
        return s.compareTo("hello");
    }

    // Action模式 没输入没输出
    private static void showAction(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        Runnable runnable1 = () -> {

        };
        Runnable runnable2 = LambdaDemo::showConsumer;
    }

    public static void main(String[] args) {
        Action action = () -> {

        };
        // 传统写法 匿名内部类写法
        PropertyChangeListener listener1 = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                println(evt);
            }
        };
        // lambda基本款
        PropertyChangeListener listener2 = evt -> System.out.println(evt);
        // 方法引用
        PropertyChangeListener listener3 = LambdaDemo::println;
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    @FunctionalInterface
    public interface Action {

        void execute();

        default void doExecute() {
            execute();
        }

        ;

    }

    // 特点1 流程编排清晰
    // 特点2 函数类型编程
    // 特点3 改善代码臃肿
    // 特定4 兼容接口升级ø
    private static void stream() {
        Stream.of(1, 2, 3, 4, 5)
                .map(String::valueOf);
    }
}
