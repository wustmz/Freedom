package org.mz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author steve.mei
 * @since 2022/3/1
 */
public class DebugCAS implements Runnable {

    int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
            System.out.println("线程" + Thread.currentThread().getName() + "执行成功");
        }
        return oldValue;
    }

    @Override
    public void run() {
        List<String>  list = new ArrayList<>();
        compareAndSwap(100, 150);
    }

    public static void main(String[] args) throws InterruptedException {
        DebugCAS r = new DebugCAS();
        r.value = 100;
        Thread t1 = new Thread(r, "Thread 1");
        Thread t2 = new Thread(r, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
