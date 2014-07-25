package com.testupstream.app.bundles;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.sun.jersey.api.client.Client;

public class AppModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Client.class).in(Scopes.SINGLETON);
        // your container config here
    }

}
