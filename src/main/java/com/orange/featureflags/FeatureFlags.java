package com.orange.featureflags;

import org.togglz.core.Feature;
import org.togglz.core.annotation.DefaultActivationStrategy;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;

public enum FeatureFlags implements Feature {
    @Label("Allocation algorithm that gives Ahmad Ashraf all apartments")
    // If X-Features: ALTERNATIVE_ALLOCATION_ALGORITHM header is sent,  the feature is enabled
    @DefaultActivationStrategy(id = "header")
    // A feature must first be enabled, then the activation strategy can be used, otherwise even if header is present, no activation. Can be controlled from Togglz dashboard
    @EnabledByDefault
    ALTERNATIVE_ALLOCATION_ALGORITHM;
}
