package com.testupstream.app.bundles;

import com.testupstream.app.AppConfig;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlet.ServletHolder;

public class ConfigurationBundle implements ConfiguredBundle<AppConfig> {

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        environment.getAdminContext().addServlet(new ServletHolder(new ConfigServlet(configuration)), "/config");
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {

    }

}
