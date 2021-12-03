package me.qinzc.jvm;

/**
 * desc : 标量替换
 * 在JIT阶段，如果经过逃逸分析，发现一个对象不会被外界访问的话，那么经过JIT优化，就会把这个对象
 * 拆解成若干个其中包含的若干个成员变量来代替。
 * @author : Zane Qin
 * creatTime : 17:21 2020/3/30
 * modifier:
 * modifyTime:
 */
public class ScalarReplace {

    //方法getAB使用类A里面的a，b
    private void getA() {
        A a = new A();
        a.a = 1;
        a.b = 2;
    }
    //JVM在编译的时候会直接编译成
//    private void getA() {
//        int a = 1 ;
//        int b = 2 ;
//    }
}

class A {
    public int a;
    public int b;
}
