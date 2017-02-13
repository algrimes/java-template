package com.testupstream.app.integration.steps;

import com.google.inject.Injector;
import com.testupstream.app.integration.harness.IntegrationTestHarness;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.openqa.selenium.WebDriver;

import javax.ws.rs.client.Client;

public class IntegrationTests {

    private static final IntegrationTestHarness HARNESS = IntegrationTestHarness.build();

    @ClassRule
    public static final ResourceTestRule rule = HARNESS.getRule();

    protected final WebDriver driver = HARNESS.getDriver();
    protected final Injector injector = HARNESS.getInjector();
    protected final Client client = HARNESS.getRule().client();

    public final IntegrationTestSteps given;
    public final IntegrationTestSteps when;
    public final IntegrationTestSteps then;

    public IntegrationTests() {
        IntegrationTestSteps steps = new IntegrationTestSteps(HARNESS);
        given = steps;
        when  = steps;
        then  = steps;
    }


}
