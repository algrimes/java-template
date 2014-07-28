package com.testupstream.app.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.palominolabs.metrics.guice.InstrumentationModule;

public class DWInstrumentationModule extends InstrumentationModule {

    private final MetricRegistry metricRegistry;
    private final HealthCheckRegistry healthCheckRegistry;

    public DWInstrumentationModule(MetricRegistry metricRegistry, HealthCheckRegistry healthCheckRegistry) {
        this.metricRegistry = metricRegistry;
        this.healthCheckRegistry = healthCheckRegistry;
    }

    @Override
    protected HealthCheckRegistry createHealthCheckRegistry() {
        return this.healthCheckRegistry;
    }

    @Override
    protected void bindJmxReporter() {
//        don't bother
    }

    @Override
    protected MetricRegistry createMetricRegistry() {
        return this.metricRegistry;
    }
}
