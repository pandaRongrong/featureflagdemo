package com.czr.controller;

import dev.openfeature.sdk.BooleanHook;
import dev.openfeature.sdk.EvaluationContext;
import dev.openfeature.sdk.FlagEvaluationDetails;
import dev.openfeature.sdk.HookContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;

@Configuration
class MyHook implements BooleanHook {
    private static final Logger log = LoggerFactory.getLogger(MyHook.class);

    @Override
    public Optional<EvaluationContext> before(HookContext<Boolean> ctx, Map<String, Object> hints) {
        // code to run before flag evaluation
        log.info("before hook");
        return Optional.empty();
    }

    @Override
    public void after(HookContext<Boolean> ctx, FlagEvaluationDetails<Boolean> details, Map<String, Object> hints) {
        // code to run after successful flag evaluation
        log.info("after hook");
        return;
    }

    @Override
    public void error(HookContext<Boolean> ctx, Exception error, Map<String, Object> hints) {
        // code to run if there's an error during before hooks or during flag evaluation
        log.error("err hook happens", error);
    }

    @Override
    public void finallyAfter(HookContext<Boolean> ctx, FlagEvaluationDetails<Boolean> details, Map<String, Object> hints) {
        // code to run after all other stages, regardless of success/failure
        log.info("finallyAfter hook");
    }
}
