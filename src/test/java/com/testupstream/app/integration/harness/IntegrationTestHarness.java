package com.testupstream.app.integration.harness;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.testupstream.app.App;
import com.testupstream.app.bundles.AppModule;
import com.thoughtworks.inproctester.jerseytester.webdriver.JerseyClientHtmlunitDriver;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.mockito.Mockito.mock;

public class IntegrationTestHarness {

    private static IntegrationTestHarness harness;
    private static WebDriver driver;

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

    public WebDriver getDriver() {
        if (driver == null) {
            driver = new JerseyClientHtmlunitDriver(getJersey().client());
            ((HtmlUnitDriver) driver).setJavascriptEnabled(true);
        }
        return driver;
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
                .addResource(injector.getInstance(StaticContentResource.class))
                .addResource(new TestMessageBodyWriter());
        for (Class resource : new App().getResources()) {
            builder.addResource(injector.getInstance(resource));
        }
        return builder.build();
    }
}
