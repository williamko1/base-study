package me.zaneqin.multi.deadlock;

import java.util.ArrayList;
import java.util.List;

/**
 * desc : 死锁 破坏请求和保持条件
 * 每个柜员都可以取放账本，很容易出现互相等待的情况。要想破坏请求和保持条件，就要⼀
 * 次性拿到所有资源。
 *
 * 通过加入账本管理员 （单例）
 *
 * @author Zane Qin
 * creatTime : 13:07 2022/2/7
 * modifier:
 * modifyTime:
 */
public class DestroyRequest {

    public static class Account{
        /** 单例账本管理员 */
        private AccountBookManager accountBookManager;

        private int balance;

        public void transfer(Account target, int amt) {
            // 一次性申请转出账户和转入账户，直到成功
            while (accountBookManager.getAllRequiredAccountBook(this, target)) {
                try {
                    // 锁定转出账户
                    synchronized (this) {
                        // 锁定转入账号
                        synchronized (target) {
                            if (this.balance > amt) {
                                this.balance -= amt;
                                target.balance += amt;
                            }
                        }
                    }
                } finally {
                    accountBookManager.releaseObtainedAccountBook(this, target);
                }
            }
        }
    }

    public static class AccountBookManager{
        List<Object> accounts = new ArrayList<>();

        synchronized boolean getAllRequiredAccountBook(Object from, Object to) {
            if (accounts.contains(from) || accounts.contains(to)) {
                return false;
            }
            accounts.add(from);
            accounts.add(to);
            return true;
        }

        synchronized void releaseObtainedAccountBook(Object from, Object to) {
            accounts.remove(from);
            accounts.remove(to);
        }
    }
}
