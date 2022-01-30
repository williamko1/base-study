package me.zaneqin.multi.lock;

/**
 * desc : 内存屏障
 *
 * @author Zane Qin
 * creatTime : 11:07 2022/1/30
 * modifier:
 * modifyTime:
 */
public class MemoryBarrierExample {


    private int a;

    private volatile int v1 = 1;
    private volatile int v2 = 2;

    public void readAndWrite(){
        int i = v1;//第⼀个volatile读
        // LoadLoad
        // LoadStore
        int j = v2;//第⼆个volatile读
        // LoadLoad
        // LoadStore
        a = i + j;//普通写
        // StoreStore
        v1 = i + 1;//第⼀个volatile写
        // StoreLoad
        // StoreStore
        v2 = j * 2;//第⼆个volatile写
        // StoreLoad
    }
}
