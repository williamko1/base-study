package me.qinzc.current;



/**
 * desc : java的双重检查
 * JSR133 的 Java 内存模型，如果将引用类型声明为 volatile，双重检查模式就可以工作了
 * 不然之后 除了 long 和 double 的基本类型适用
 *
 * @author : Zane Qin
 * creatTime : 17:53 2021/4/23
 * modifier:
 * modifyTime:
 */
public class DoubleCheck {

    private volatile Resource resource;

    public Resource getResource() {
        if (resource == null) {
            synchronized (this) {
                if (resource == null) {
                    resource = new Resource();
                }
            }
        }
        return resource;
    }

    static class Resource {

    }
}
