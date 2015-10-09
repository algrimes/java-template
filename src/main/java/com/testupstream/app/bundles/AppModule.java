package com.testupstream.app.bundles;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.testupstream.app.providers.HappyResponseProvider;
import com.testupstream.app.providers.ResponseProvider;
import org.glassfish.jersey.client.JerseyClient;

public class AppModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(JerseyClient.class).in(Scopes.SINGLETON);
        binder.bind(ResponseProvider.class).to(HappyResponseProvider.class);
        // your container config here
    }

}
