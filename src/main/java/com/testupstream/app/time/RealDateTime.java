package com.testupstream.app.time;

import org.joda.time.DateTime;

public class RealDateTime implements DateTimeProvider {
    @Override
    public DateTime now() {
        return DateTime.now();
    }
}
