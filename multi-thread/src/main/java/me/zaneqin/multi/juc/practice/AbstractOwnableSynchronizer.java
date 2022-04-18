package me.zaneqin.multi.juc.practice;

import java.io.Serializable;

/**
 * desc :
 *
 * @author Zane Qin
 * creatTime : 09:46 2022/2/24
 * modifier:
 * modifyTime:
 */
public class AbstractOwnableSynchronizer implements Serializable {


    public AbstractOwnableSynchronizer() {
    }

    /** 同步独占模式下的拥有者 */
    private transient Thread exclusiveOwnerThread;

    protected final Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    protected final void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
    }
}
