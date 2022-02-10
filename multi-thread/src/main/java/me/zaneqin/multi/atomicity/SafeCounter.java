package me.zaneqin.multi.atomicity;

/**
 * desc : 不在getCount上加synchronized关键字的话违背了 monitor rule
 * 从而导致脏读
 *
 * @author Zane Qin
 * creatTime : 10:38 2022/2/7
 * modifier:
 * modifyTime:
 */
public class SafeCounter {

    private int count;


    public synchronized int getCount() {
        return count;
    }

    public synchronized void setCount(int count) {
        this.count = count;
    }
}
