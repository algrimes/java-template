package com.testupstream.app.integration.harness;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.testupstream.app.App;
import com.testupstream.app.bundles.AppModule;
import com.testupstream.app.providers.ResponseProvider;
import com.testupstream.app.providers.TestResponseProvider;
import com.testupstream.app.time.DateTimeProvider;
import com.testupstream.app.time.TestDateTime;
import com.thoughtworks.inproctester.jerseytester.webdriver.JerseyClientHtmlunitDriver;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class IntegrationTestHarness {

    private static IntegrationTestHarness harness;
    private static WebDriver driver;

    //    setup mocks/stubs here

    private final Injector injector = Guice.createInjector(Stage.PRODUCTION, Modules.override(new AppModule()).with(binder -> {
        binder.bind(ResponseProvider.class).to(TestResponseProvider.class);
        binder.bind(DateTimeProvider.class).to(TestDateTime.class);

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
