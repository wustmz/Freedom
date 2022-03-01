package org.mz;

/**
 * @author steve.mei
 * @since 2022/3/1
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {

    }

    /**
     * 双重检查锁单例模式
     *
     * @return 单例
     */
    public static Singleton getInstance() {
        //假设有两个线程同时到达synchronized语句块，那么实例化代码只会由其中先抢到锁的线程执行一次
        //而后抢到锁的线程会在第二个if判断中发现singleton不为null，所以跳过创建实例的语句
        //再后面的其他线程再来调用getInstance方法时，只需判断第一次的if (instance == null)，
        //然后会跳过整个if块，直接return实例化后的对象
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
