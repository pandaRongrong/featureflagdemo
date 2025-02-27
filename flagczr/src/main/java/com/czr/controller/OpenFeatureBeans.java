package com.czr.controller;

import com.networknt.schema.FalseValidator;
import dev.openfeature.contrib.providers.flagd.Config;
import dev.openfeature.contrib.providers.flagd.FlagdOptions;
import dev.openfeature.contrib.providers.flagd.FlagdProvider;
import dev.openfeature.sdk.OpenFeatureAPI;
import dev.openfeature.sdk.exceptions.OpenFeatureError;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFeatureBeans {
    @Bean
    public OpenFeatureAPI openFeatureAPI() {
        final OpenFeatureAPI openFeatureAPI = OpenFeatureAPI.getInstance();

        // Use flagd as the OpenFeature provider and use default configurations
        try {
            FlagdProvider flagdProvider = new FlagdProvider(
                    FlagdOptions.builder()
                            .resolverType(Config.Resolver.FILE)
                            .offlineFlagSourcePath("flagczr/src/main/resources/flags.json")
                            .build());
            openFeatureAPI.setProviderAndWait(flagdProvider);


        } catch (OpenFeatureError e) {
            throw new RuntimeException("Failed to set OpenFeature provider", e);
        }
        return openFeatureAPI;
    }
}
