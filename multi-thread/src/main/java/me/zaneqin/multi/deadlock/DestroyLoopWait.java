package me.zaneqin.multi.deadlock;

/**
 * desc : 死锁  破坏环路等待
 * 将资源序号⼤⼩排序获取就会解决这个问题，将环路拆除
 *
 * @author Zane Qin
 * creatTime : 13:18 2022/2/7
 * modifier:
 * modifyTime:
 */
public class DestroyLoopWait {

    public static class Account {

        private int id;
        private int balance;

        // 转账
        void transfer(Account target, int amt){
            Account smaller = this;
            Account larger = target;

            // 排序
            if (this.id > target.id) {
                smaller = target;
                larger = this;
            }
            // 锁定序号小的账户
            synchronized (smaller) {
                // 锁定序号大的账户
                synchronized (larger) {
                    if (this.balance > amt) {
                        this.balance -= amt;
                        target.balance += amt;
                    }
                }
            }
        }


    }

}
