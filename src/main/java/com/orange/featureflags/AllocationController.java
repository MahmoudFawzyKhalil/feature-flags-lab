package com.orange.featureflags;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @GetMapping("/allocate") // For easy browser access
    public String allocate() {
        return allocationService.allocate();
    }
}
