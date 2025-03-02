package com.czr.controller;

import com.launchdarkly.sdk.EvaluationDetail;
import com.launchdarkly.sdk.LDContext;
import com.launchdarkly.sdk.server.LDClient;
import com.launchdarkly.sdk.server.LDConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("launchDarkly")
public class HelloLaunchDarkly {
    @GetMapping("/hello")
    public String hello(){
        // A "context" is a data object representing users, devices, organizations, and
        // other entities. You'll need this later, but you can ignore it for now.
        final LDContext context = LDContext.builder("example-context-key")
                .name("Sandy")
                .set("email", "182@vip.com")
                .build();

        LDConfig config = new LDConfig.Builder().build();

        // Set your LaunchDarkly SDK key.
        // This is inlined as example only for onboarding.
        // Never hardcode your SDK key in production.
        final LDClient client = new LDClient("sdk-b47e64f2-bedc-40b6-8aba-5018f07fed88", config);

        if (client.isInitialized()) {
            System.out.println("SDK successfully initialized!");
        }

        client.track("event-key-123abc", context);
        EvaluationDetail<Boolean> detail = client.boolVariationDetail("czrfeaturflag1", context, false);

        if (detail.getValue()) {

            // TODO: Put your feature here
            return "new feature value2";
        } else {

            // TODO: Put your fallback behavior here
            return "last feature value1";
        }
    }
}
