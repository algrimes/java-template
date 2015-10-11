package com.testupstream.app.time;

import org.joda.time.DateTime;

public interface DateTimeProvider {
    DateTime now();
}
