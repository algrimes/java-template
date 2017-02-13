package com.testupstream.app.integration.steps;

import com.google.inject.Injector;
import com.testupstream.app.integration.harness.IntegrationTestHarness;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.openqa.selenium.WebDriver;

public class IntegrationTests {

    private static final IntegrationTestHarness integrationTestHarness = IntegrationTestHarness.build();

    @ClassRule
    public static final ResourceTestRule rule = integrationTestHarness.getRule();

    protected final WebDriver driver = integrationTestHarness.getDriver();
    protected final Injector injector = integrationTestHarness.getInjector();

    public final IntegrationTestSteps given;
    public final IntegrationTestSteps when;
    public final IntegrationTestSteps then;

    public IntegrationTests() {
        IntegrationTestSteps steps = new IntegrationTestSteps();
        given = steps;
        when  = steps;
        then  = steps;
    }


}
