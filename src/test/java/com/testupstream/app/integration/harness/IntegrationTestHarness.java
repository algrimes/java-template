package com.testupstream.app.integration.harness;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.testupstream.app.App;
import com.testupstream.app.bundles.AppModule;
import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.mock;

public class IntegrationTestHarness {

    private static IntegrationTestHarness harness;

    //    setup mocks/stubs here

    private final Injector injector = Guice.createInjector(Stage.PRODUCTION, Modules.override(new AppModule()).with(new Module() {
        @Override
        public void configure(Binder binder) {
            // bind interfaces to mocks/stubs here
        }
    }));

    private final ResourceTestRule jersey = setUpRule();

    private IntegrationTestHarness() {}


    public static IntegrationTestHarness getHarness() {
        if(harness == null) {
            harness = new IntegrationTestHarness();
        }
        return harness;
    }

    public ResourceTestRule getJersey() {
        return jersey;
    }

    public Injector getInjector() {
        return injector;
    }

    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    private ResourceTestRule setUpRule() {
        ResourceTestRule.Builder builder = new ResourceTestRule.Builder()
                .addResource(new TestMessageBodyWriter());
        for (Class resource : new App().getResources()) {
            builder.addResource(injector.getInstance(resource));
        }
        return builder.build();
    }
}
