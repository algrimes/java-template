package com.testupstream.app.time;

import org.joda.time.DateTime;

public class TestDateTime implements DateTimeProvider {

    private static DateTime date;

    public void freezeTime(DateTime dateTime) {
        date = dateTime;
    }

    public DateTime now() {
        return date != null ? date : DateTime.now();
    }

}
