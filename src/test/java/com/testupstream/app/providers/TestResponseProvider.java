package com.testupstream.app.providers;

public class TestResponseProvider implements ResponseProvider {
    @Override
    public String get() {
        return "<html><body><h1>Goodbye, World!</h1></body></html>";
    }
}
