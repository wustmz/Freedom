package org.mz;

/**
 * @author steve.mei
 * @since 2022/2/28
 */
public class RunnableThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "实现Runnable创建");
    }

    public static void main(String[] args) {
        RunnableThread runnable = new RunnableThread();
        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println(Thread.currentThread().getName() + "：main方法");
    }
}
