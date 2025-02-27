package com.czr.controller;

import dev.openfeature.sdk.Client;
import dev.openfeature.sdk.Hook;
import dev.openfeature.sdk.OpenFeatureAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestHello {
    private final OpenFeatureAPI openFeatureAPI;

    @Autowired
    public RestHello(OpenFeatureAPI OFApi) {
        this.openFeatureAPI = OFApi;
    }

    @GetMapping("/hello2")
    public String getHello() {
        final Client client = openFeatureAPI.getClient();
        client.addHooks(new MyHook());
        // Evaluate welcome-message feature flag
        if (client.getBooleanValue("welcome-message", false)) {
            return "Hello, welcome to this OpenFeature-enabled website!";
        }

        return "Hello!";
    }
}
