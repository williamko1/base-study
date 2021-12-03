package me.qinzc.current;

/**
 * desc : Initialization on Demand Holder（IODH）
 * 改进double check
 * @author : Zane Qin
 * creatTime : 17:58 2021/4/23
 * modifier:
 * modifyTime:
 */
public class Singleton {
    static class SingletonHolder {
        public static Singleton instance = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonHolder.instance;
    }
}