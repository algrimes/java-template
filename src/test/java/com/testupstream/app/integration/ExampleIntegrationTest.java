package com.testupstream.app.integration;

import com.testupstream.app.integration.steps.IntegrationTests;
import com.testupstream.app.providers.ResponseProvider;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;


public class ExampleIntegrationTest extends IntegrationTests {

    @Test
    public void homepageShouldDisplayWhateverIChoose() throws Exception {
        validateInProcessMocking("messageOne");
        validateInProcessMocking("messageTwo");
    }

    private void validateInProcessMocking(String message) {
        given.theHomePageResponseIs(message);
        when.theHomepageIsVisited();
        assertThat(then.homepageText(), is(message));
    }

}