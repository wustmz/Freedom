package org.mz;

/**
 * @author steve.mei
 * @since 2022/3/1
 */
public class SynchronizedDemo {

    public static void main(String[] args) {

    }

    public void test() {
        synchronized (this) {
            System.out.println("synchronized代码块");
        }
    }
}
