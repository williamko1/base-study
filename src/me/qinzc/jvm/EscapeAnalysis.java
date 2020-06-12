package me.qinzc.jvm;

/**
 * desc : 逃逸分析实例
 * JVM判断新创建的对象是否逃逸的依据有：
 *  一、对象被赋值给堆中对象的字段和类的静态变量。
 *  二、对象被传进了不确定的代码中去运行。如果满足了以上情况的任意一种，那这个对象JVM就会判定为逃逸。
 *  对于第一种情况，因为对象被放进堆中，则其它线程就可以对其进行访问，所以对象的使用情况，编译器就无法再进行追踪。
 *  第二种情况相当于JVM在解析普通的字节码的时候，如果没有发生JIT即时编译，编译器是不能事先完整知道这段代码会对对象做什么操作。
 *  保守一点，这个时候也只能把对象是当作是逃逸来处理。
 * @author : Zane Qin
 * creatTime : 17:11 2020/3/30
 * modifier:
 * modifyTime:
 */
public class EscapeAnalysis {

    // 全局变量 (产生逃逸）
    public static Object object;

    public Object instanceObject;

    // 全局变量赋值逃逸
    // 静态变量, 外部线程可见, 发生逃逸
    public void globalVariableEscape(){
        object = new Object();
    }

    //赋值给堆中实例字段,外部线程可见,发生逃逸
    public void instanceEscape(){
        instanceObject = new Object();
    }

    // 方法返回值逃逸
    //
    public Object methodEscape(){
        return object;
    }

    public void noEscape() {
        synchronized (new Object()) {
            //仅创建线程可见,对象无逃逸
        }
        Object noEscape = new Object();  //仅创建线程可见,对象无逃逸
    }




}
