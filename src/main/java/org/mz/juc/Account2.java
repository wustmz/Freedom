package org.mz.juc;

/**
 * 不同的资源使用同一把锁保护
 */
public class Account2 {
    private Integer balance;

    void transfer(Account2 target, int amt) {
        synchronized (Account2.class) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }
}
