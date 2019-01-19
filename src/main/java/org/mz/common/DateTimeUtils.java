package org.mz.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static final String SHORT_FORMAT = "yyyy-MM-dd";

    public static String getSimpleTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(SHORT_FORMAT);
        return sdf.format(new Date(timestamp));
    }
}
