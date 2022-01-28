package me.qinzc.designp.singleton;

/**
 * desc : 枚举单例
 *
 * 由于单例模式的枚举实现代码比较简单，而且又可以利用枚举的特性来解决线程安全和单一实例的问题，
 * 还可以防止反射和反序列化对单例的破坏，因此在很多书和文章中都强烈推荐将该方法作为单例模式的最佳实现方法。
 *
 *  因为Java虚拟机会保证枚举对象的唯一性，因此每一个枚举类型和定义的枚举变量在JVM中都是唯一的。
 *  Java虚拟机会保证枚举类型不能被反射并且构造函数只被执行一次。
 * @author Zane Qin
 * creatTime : 14:21 2021/12/31
 * modifier:
 * modifyTime:
 */
public class EnumSingleton {
    public static void main(String args[]) {
        EnumSingleton s1 = SingletonEnum.SINGLETON.getInstance();
        EnumSingleton s2 = SingletonEnum.SINGLETON.getInstance();
        System.out.println(s1==s2);
    }
    private EnumSingleton() {
    }
    public static enum SingletonEnum {
        SINGLETON;
        private EnumSingleton instance = null;

        private SingletonEnum() {
            instance = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return instance;
        }
    }
}