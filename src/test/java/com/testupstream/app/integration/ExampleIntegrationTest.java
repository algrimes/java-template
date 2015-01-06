package com.testupstream.app.integration;

import com.testupstream.app.integration.harness.IntegrationTestRunner;
import com.testupstream.app.integration.steps.IntegrationTestStepWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

import static com.testupstream.app.integration.harness.IntegrationTestHarness.getHarness;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(IntegrationTestRunner.class)
public class ExampleIntegrationTest extends IntegrationTestStepWrapper {

    private static final String JERSEY_HOST = "http://localhost:9998";

    @Test
    public void homepageShouldHaveHelloWorld() throws Exception {
        getHarness().getDriver().get(JERSEY_HOST + "/");
        assertThat(getHarness().getDriver().findElement(By.tagName("h1")).getText(), is("Hello, World!"));
    }

}
