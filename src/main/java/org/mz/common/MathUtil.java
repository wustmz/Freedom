package org.mz.common;

import java.text.NumberFormat;

public class MathUtil {
    public static String getRate(double xirr) {
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        return nt.format(xirr);
    }

    public static double negate(double amount) {
        return -amount;
    }

    public static String getDouble2(double d) {
        return String.format("%.2f", d);
    }
}
