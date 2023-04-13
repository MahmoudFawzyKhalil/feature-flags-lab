package com.orange.featureflags;

import org.springframework.stereotype.Service;
import org.togglz.core.manager.FeatureManager;

@Service
public class AllocationService {

    // Togglz feature manager, autoconfigured, we ask it if a feature is on or not
    private final FeatureManager featureManager;

    public AllocationService(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    public String allocate() {
        boolean alternativeAllocationActive =
                featureManager.isActive(FeatureFlags.ALTERNATIVE_ALLOCATION_ALGORITHM);

        if (alternativeAllocationActive) {
            return alternative();
        } else {
            return defualt();
        }
    }

    private static String defualt() {
        return "Allocated with default algorithm";
    }

    private static String alternative() {
        return "Allocated with alternative algorithm \uD83D\uDE80";
    }
}
