package me.zaneqin.multi.practice;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : c14:39 2022/3/21
 * modifier:
 * modifyTime:
 */
public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
