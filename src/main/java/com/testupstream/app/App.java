package com.testupstream.app;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.palominolabs.metrics.guice.MetricsInstrumentationModule;
import com.testupstream.app.bundles.AppModule;
import com.testupstream.app.bundles.ConfigurationBundle;
import com.testupstream.app.resources.HelloWorldResource;
import com.testupstream.app.routes.Urls;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

import java.util.Arrays;
import java.util.List;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());

        bootstrap.addBundle(new AssetsBundle(Urls.ASSETS_PATH));
        bootstrap.addBundle(new ConfigurationBundle());

        GuiceBundle<AppConfig> guiceBundle = GuiceBundle.<AppConfig>newBuilder()
                .addModule(new AppModule())
                .addModule(new MetricsInstrumentationModule(bootstrap.getMetricRegistry()))
                .setConfigClass(AppConfig.class)
                .build();

        bootstrap.addBundle(guiceBundle);

        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));

    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        for (Class resource : getResources()) {
            environment.jersey().register(resource);
        }
    }

    public List<Class> getResources() {
        return Arrays.<Class>asList(HelloWorldResource.class);
    }
}
