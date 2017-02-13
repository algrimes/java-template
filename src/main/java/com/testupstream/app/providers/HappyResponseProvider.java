package com.testupstream.app.providers;

public class HappyResponseProvider implements ResponseProvider {

    @Override
    public String get() {
        return "Hello, World!";
    }
}
