package org.mz;

import java.util.concurrent.locks.LockSupport;

public class StopThread implements Runnable {
    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count = " + count++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StopThread runnable = new StopThread();
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
        LockSupport.park();
    }
}