package com.a21vianet.quincysx.library.net.http;

import java.util.concurrent.TimeUnit;

/**
 * Created by q7728 on 2017/5/25 0025.
 */

public class TimeKey {
    private long mValue = 10;
    private TimeUnit mTimeUnit = TimeUnit.SECONDS;

    public TimeKey(long value, TimeUnit timeUnit) {
        mValue = value;
        mTimeUnit = timeUnit;
    }

    public TimeKey() {
    }

    public long getValue() {
        return mValue;
    }

    public void setValue(long value) {
        mValue = value;
    }

    public TimeUnit getTimeUnit() {
        return mTimeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        mTimeUnit = timeUnit;
    }
}
