package me.qinzc.interview.sgg1;

/**
 * desc : 自增
 *
 * 赋值= 最后计算
 * =右边的从左到右依次压入操作数栈
 * 实现先算哪个，看运算符优先级
 * 自增，自减操作都是直接修改变量的值，不经过操作数栈
 * 最后赋值之前，临时结果也是存储在操作数栈中
 *
 * 《JVM虚拟机规范》指令的部分
 * @author Zane Qin
 * creatTime : 10:08 2023/3/14
 * modifier:
 * modifyTime:
 */
public class A01_SelfAdd {

    public static void main(String[] args) {
        int i = 1;
        // 把i压入操作数栈 1
        // i变量自增1
        // 操作数栈赋值给i, i = 1
        i = i++;
        System.out.println(i);// 1
        // 把i压入操作数栈
        // i变量自增1 ,i=2
        // 把操作数栈赋值给j ,j=2
        int j = i++;
        System.out.println(i);// 2
        System.out.println(j);// 1
        // i压入操作数栈，i=2， i自增1后压入操作数栈，i=3，i压入操作数栈3，i自增 i=4
        int k = i + ++i * i++;// 2 + 3 * 3
        System.out.println("i:" + i);// 4
        System.out.println("j:" + j);// 1
        System.out.println("k:" + k);// 11

    }
}
