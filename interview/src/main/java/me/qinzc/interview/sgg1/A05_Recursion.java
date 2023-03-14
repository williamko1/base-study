package me.qinzc.interview.sgg1;

import org.junit.Test;

/**
 * desc : 递归调用和循环调用
 * 方法调用自身叫递归，利用变量的原值新值称为迭代
 * 递归
 *  优点：大问题转小问题，可以减少代码量，精简，可读性好
 *  缺点：递归浪费了空间，递归太深容易堆栈溢出
 * 迭代
 *  优点：运行效率好，循环只有时间增加，没有额外空间开销
 *  缺点：代码不如递归简洁，不如递归可读性好
 *
 *
 * @author Zane Qin
 * creatTime : 13:29 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A05_Recursion {

    @Test
    public void test() {
        System.out.println(f(40));
        System.out.println(loop(40));
    }

    public int f(int n) {
        if (n < 1) {
            throw new IllegalStateException("n 不能<1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n - 2) + f(n - 1);
    }

    public int loop(int n) {
        if (n < 1) {
            throw new IllegalStateException("n 不能<1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int one = 2;
        int two = 1;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = one + two;
            two = one;
            one = sum;
        }
        return sum;
    }
}
