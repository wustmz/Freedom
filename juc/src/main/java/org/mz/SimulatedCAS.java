package org.mz;

/**
 * @author steve.mei
 * @since 2022/3/1
 */
public class SimulatedCAS {

    int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
