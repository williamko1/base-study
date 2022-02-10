package me.zaneqin.multi.happensbefore;

/**
 * desc :
 *
 * volatile 域的写, happens-before 于任意后续对这个 volatile 域的读
 * 第二个操作是写，不能重排序
 * volatile域的读，happens-before于后续任意操作
 *
 *
 * 1. 在每个 volatile 写操作的前⾯插⼊⼀个 StoreStore 屏障
 * 2. 在每个 volatile 写操作的后⾯插⼊⼀个 StoreLoad 屏障
 * 3. 在每个 volatile 读操作的后⾯插⼊⼀个 LoadLoad 屏障
 * 4. 在每个 volatile 读操作的后⾯插⼊⼀个 LoadStore 屏障
 *
 * @author Zane Qin
 * creatTime : 10:43 2022/2/7
 * modifier:
 * modifyTime:
 */
public class VolatileBarrierExample {
    private int a;
    private volatile int v1 = 1;
    private volatile int v2 = 2;
    void readAndWrite(){
        int i = v1; //第⼀个volatile读
        // loadload 禁止前面的读和后面的读重排序
        // loadstore 禁止前面的读和后面的写重排序 可省略
        int j = v2; //第⼆个volatile读
        // loadload 可省略 后面没有读
        // loadstore 禁止前面的读和后面的普通写重排序
        a = i + j; //普通写
        // storestore 禁止上面的普通写和下面的volatile写重排序
        v1 = i + 1; //第⼀个volatile写
        // storeload 禁止上面的volatile写和下面的volatile读重排序，可省略，后面没有读
        // storestore
        v2 = j * 2; //第⼆个volatile写
        // storeload
    }
}
