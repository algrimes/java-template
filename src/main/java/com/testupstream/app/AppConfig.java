package com.testupstream.app;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class AppConfig extends Configuration {

    @JsonProperty("my-variable")
    private String myVariable;

    @JsonProperty
    public String getMyVariable() {
        return myVariable;
    }

    @JsonProperty
    public void setMyVariable(String myVariable) {
        this.myVariable = myVariable;
    }
}
