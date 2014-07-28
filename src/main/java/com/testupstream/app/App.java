package com.testupstream.app;

import com.testupstream.app.bundles.AppGuiceBundle;
import com.testupstream.app.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
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
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AppGuiceBundle());
        bootstrap.addBundle(new AssetsBundle("/assets"));
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
