package com.testupstream.app.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import com.testupstream.app.AppConfig;

public class ConfigHealthCheck extends HealthCheck {

    private AppConfig configuration;

    public ConfigHealthCheck(AppConfig configuration) {
        this.configuration = configuration;
    }

    @Override
    protected Result check() throws Exception {
        return null;
    }
}
