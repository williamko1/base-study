package me.qinzc.interview.sgg1;

/**
 * desc :
 *
 * 1. 方法参数的传递机制，实参给形参赋值
 *  形参是基本类型时，传的是具体值
 *  形参是对象时，传的是地址
 *
 * 2. String，包装类等对象的不可变性
 *
 *
 * @author Zane Qin
 * creatTime : 13:12 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A04_ParameterTransmit {

    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 2;
        int[] arr = {1, 2, 3, 4, 5};
        MyData my = new MyData();

        change(i, str, num, arr, my);
        System.out.println("i:" + i);// 1
        System.out.println("str:" + str);// hello
        System.out.println("num:" + num);// 2
        System.out.println("arr:" + arr[0]);// 2
        System.out.println("my:" + my.a);// 11
    }

    public static void change(int j, String s, Integer n, int[] a, MyData m) {
        j +=1;
        s += "word";
        n +=1;
        a[0] += 1;
        m.a += 1;
    }


}
class MyData {
    int a = 10;
}

