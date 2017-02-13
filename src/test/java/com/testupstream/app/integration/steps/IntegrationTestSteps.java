package com.testupstream.app.integration.steps;

import com.google.inject.Injector;
import com.testupstream.app.integration.harness.IntegrationTestHarness;
import com.testupstream.app.providers.ResponseProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.ws.rs.client.Client;

import static org.mockito.Mockito.when;

public class IntegrationTestSteps {

    private final String baseUri = "http://localhost:9998";
    private final IntegrationTestHarness harness;
    private final WebDriver driver;
    private final Client client;
    private final Injector injector;

    public IntegrationTestSteps(IntegrationTestHarness harness) {
        this.harness = harness;
        this.driver =  harness.getDriver();
        this.client = harness.getRule().client();
        this.injector = harness.getInjector();
    }

    public void theHomepageIsVisited() {
        driver.get(baseUri);
    }

    public String homepageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public void theHomePageResponseIs(String message) {
        when(injector.getInstance(ResponseProvider.class).get()).thenReturn(message);

    }
}
