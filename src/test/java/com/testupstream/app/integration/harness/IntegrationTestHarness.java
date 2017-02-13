package com.testupstream.app.integration.harness;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;
import com.testupstream.app.App;
import com.testupstream.app.bundles.AppModule;
import com.testupstream.app.providers.ResponseProvider;
import com.testupstream.app.time.DateTimeProvider;
import com.testupstream.app.time.TestDateTime;
import com.thoughtworks.inproctester.jerseytester.webdriver.JerseyClientHtmlunitDriver;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.mockito.Mockito.mock;

public class IntegrationTestHarness {

    private WebDriver driver = null;
    private ResourceTestRule rule;
    private Injector injector;

    private IntegrationTestHarness(final ResourceTestRule rule, final Injector injector) {
        this.rule = rule;
        this.injector = injector;
    }

    public Injector getInjector() {
        return this.injector;
    }


    public WebDriver getDriver() {
        if (driver == null) {
            driver = new JerseyClientHtmlunitDriver(rule.client());
        }
        return this.driver;
    }

    public ResourceTestRule getRule() {
        return rule;
    }

    public static IntegrationTestHarness build() {

        Injector injector = Guice.createInjector(Stage.PRODUCTION, Modules.override(new AppModule()).with(binder -> {
            binder.bind(ResponseProvider.class).toInstance(mock(ResponseProvider.class));
            binder.bind(DateTimeProvider.class).to(TestDateTime.class);

        }));

        ResourceTestRule.Builder builder = new ResourceTestRule.Builder();

        for (Class resource : new App().getResources()) {
            builder.addResource(injector.getInstance(resource));
        }

        JerseyGuiceUtils.reset(); //Because of https://github.com/HubSpot/dropwizard-guice/issues/95

        ResourceTestRule rule = builder.build();

        return new IntegrationTestHarness(rule, injector);
    }
}
