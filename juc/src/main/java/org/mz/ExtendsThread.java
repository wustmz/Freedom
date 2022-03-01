package org.mz;

/**
 * @author steve.mei
 * @since 2022/2/28
 **/
public class ExtendsThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "继承Thread类创建线程");
    }

    public static void main(String[] args) {
        ExtendsThread thread = new ExtendsThread();
        thread.start();
        System.out.println(Thread.currentThread().getName() + "：main方法");
    }
}
