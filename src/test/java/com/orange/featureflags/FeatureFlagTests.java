package com.orange.featureflags;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class FeatureFlagTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void Request_with_features_request_header_uses_different_allocation_algorithm() throws Exception {
        var req = mockMvc.perform(
                get("/allocate")
                        .header("X-Features", FeatureFlags.ALTERNATIVE_ALLOCATION_ALGORITHM.name())
        ).andReturn();
        String responseBody = req.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).contains("alternative");
    }

    @Test
    void Request_without_features_request_header_uses_default_allocation_algorithm() throws Exception {
        var req = mockMvc.perform(
                get("/allocate")
        ).andReturn();
        String responseBody = req.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).contains("default");
    }

    @Test
    void Request_with_X_query_param_uses_alternative_allocation_algorithm() throws Exception {
        // Lab: implement query activation strategy for feature flag
        var req = mockMvc.perform(
                get("/allocate")
                        .queryParam("flagEnabled", "true")
        ).andReturn();
        String responseBody = req.getResponse().getContentAsString();
        Assertions.assertThat(responseBody).contains("alternative");
    }
}
