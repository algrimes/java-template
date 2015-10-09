package com.testupstream.app.providers;

public class HappyResponseProvider implements ResponseProvider {

    @Override
    public String get() {
        return "<html><body><h1>Hello, World!</h1></body></html>";
    }
}
