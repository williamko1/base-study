package me.qinzc.interview.sgg1;

/**
 * desc :
 * 一. 类初始化过程
 *  1. 一个类需要创建实例需要先加载并初始化该类
 *      main方法所在的类需要先加载和初始化
 *  2. 一个子类需要初始化必须先初始化父类
 *  3. 一个类初始化是执行<clinit>()方法
 *      clinit()方法由静态变量显式赋值代码和静态代码块组成
 *      类变量显式赋值和静态代码块按顺序从上到下执行
 *      clinit()只执行一次
 *  4.
 * 二. 实例化过程
 *  1. <init>()方法
 *      init()方法可能重载多个，由几个构造器就有几个init()
 *      init()方法非静态实例变量显式赋值代码和非静态代码块，对应构造器代码组成
 *      从上到下顺序执行，构造器最后执行
 *      每new一次对象都是调用一次init()方法
 *      init()方法的首行是super()或super(实参)，即对应父类的init方法
 *  2.
 *
 * 三. 方法重写
 *  1. 非静态方法前面有一个默认的this， this 在构造器init()表示正在创建的对象，因为这里在创建son对象，所以test()执行的是子类的重写代码（多态）
 *  2. 哪些方法不可以不重写
 *      final方法
 *      静态方法
 *      private等子类中不可见方法
 *  3. 对象的多态性
 *      子类如果重写了父类的方法，通过子类对象调用的一定是子类重写过的代码
 *      非静态方法默认的调用对象都是this
 *      this对象在构造器或者说init方法中就是正在创建的对象
 *
 *  《JVM虚拟机规范》中关于clinit 和 init 方法的说明， invokespeical指令
 *
 * 子类的初始化 clinit
 * 1. j = method()
 * 2. 子类的静态代码块
 * 初始化父类 5， 1
 * 初始化子类 10，,6
 *
 * 子类实例的初始化
 * 1. super() 最前
 * 2. i = test() 按顺序
 * 3. 子类的非静态代码块
 * 4. 子类的无参构造器 最后
 * @author Zane Qin
 * creatTime : 10:47 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A03_Son extends A03_Father {
    private int i = test();

    private static int j = method();

    static {
        System.out.println(6);
    }

    A03_Son() {
//        super(); //写或不写都存在
        System.out.println(7);
    }

    {
        System.out.println(8);
    }

    public int test() {
        System.out.println(9);
        return 2;
    }

    public static int method() {
        System.out.println(10);
        return 2;
    }

    public static void main(String[] args) {
        // 5, 1,10, 6, 9, 3, 2, 9, 8, 7
        A03_Son s1 = new A03_Son();
        System.out.println("--------------------------");
        // 9, 3, 2, 9, 8, 7
        A03_Son s2 = new A03_Son();
    }
}
