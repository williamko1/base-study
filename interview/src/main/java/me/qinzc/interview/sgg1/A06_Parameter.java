package me.qinzc.interview.sgg1;

/**
 * desc : 局部变量和全局变量
 *
 * 就近原则
 *
 * 变量的分类
 *  成员变量：类，实例
 *  局部变量
 * 非静态代码块的执行：每次创建实例的时候都会执行
 * 方法的调用规则：调用一次执行一次
 *
 * 存储的位置
 *  局部变量：栈
 *  实例变量：堆
 *  类变量：方法区
 *
 * @author Zane Qin
 * creatTime : 14:50 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A06_Parameter {

    static int s;
    int i;
    int j;
    {
        int i = 1;
        i++;
        j++;
        s++;
    }

    public void test(int j) {
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        // i = 0, j = 1, s = 1
        A06_Parameter obj1 = new A06_Parameter();
        // i = 0, j = 1, s = 2
        A06_Parameter obj2 = new A06_Parameter();
        // i = 1, j = 1, s = 3
        obj1.test(10);
        // i = 2, j = 1, s = 4
        obj1.test(20);
        // i = 1, j = 1, s = 5
        obj2.test(30);
        System.out.println("obj1.i=" + obj1.i + ",j=" + obj1.j + ",s=" + obj1.s);
        System.out.println("obj2.i=" + obj2.i + ",j=" + obj2.j + ",s=" + obj2.s);
    }
}
