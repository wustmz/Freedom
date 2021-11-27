package org.mz;

/**
 * @Author steve.mei
 * @Version VolatileExample,  2020/10/9 11:05 下午
 **/
public class VolatileExample {
    int x = 0;

    /**
     * 1.5版本之后对volatile进行了加强
     */
    volatile boolean v = false;

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v) {
            System.out.println(x);
        }
    }

    /**
     * happens-before规则：前面一个操作的结果对后续的操作是可见的
     * 正式说法：happens-before约束了编译器的优化行为，虽然允许编译器优化，但是要求编译器优化后一定遵守happens-before规则
     * 1.程序的顺序性规则：按照程序顺序，前面的操作happens-before于后续的任意操作。
     *
     * @param args
     */
    public static void main(String[] args) {
        VolatileExample volatileExample = new VolatileExample();
        //创建线程1，写操作
        Thread thread1 = new Thread(volatileExample::writer);
        //创建线程2，读操作
        Thread thread2 = new Thread(volatileExample::reader);
        thread1.start();
        thread2.start();
    }
}
