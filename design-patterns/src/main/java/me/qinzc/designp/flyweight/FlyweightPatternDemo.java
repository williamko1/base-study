package me.qinzc.designp.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 13:53 2021/12/6
 * modifier:
 * modifyTime:
 */
public class FlyweightPatternDemo {

    public static void main(String[] args) {
        Flyweight flyweight = FlyweightFactory.get("对象1");
        flyweight.execute();

        Flyweight flyweight2 = FlyweightFactory.get("对象1");
        flyweight.execute();

        System.out.println(flyweight == flyweight2);
        // 享元
        // 同一个数据，整个系统里就存储一份数据，缓存起来
        // 整个系统对这个数据，全部享受他一个对象实例即可
        // 直接基于内存，缓存一个数据

    }

    public interface Flyweight{
        String getName();
        void execute();
        void setName(String name);
    }

    public static class ConcreteFlyweight implements Flyweight {
        private String name;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void execute() {
            System.out.println("执行功能逻辑");
        }
    }

    public static class FlyweightFactory{
        private static Map<String, Flyweight> cachePool = new HashMap<>();

        public static void put(Flyweight flyweight) {
            cachePool.put(flyweight.getName(), flyweight);
        }

        public static Flyweight get(String name) {
            Flyweight flyweight = cachePool.get(name);
            if (flyweight == null) {
                flyweight = new ConcreteFlyweight();
                flyweight.setName(name);
                cachePool.put(name, flyweight);
            }
            return flyweight;
        }
    }

}
