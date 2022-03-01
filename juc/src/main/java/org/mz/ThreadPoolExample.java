package org.mz;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author steve.mei
 * @since 2022/2/28
 */
public class ThreadPoolExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardPolicy());
    }
}
