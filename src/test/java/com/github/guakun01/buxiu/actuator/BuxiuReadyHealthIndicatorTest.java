package com.github.guakun01.buxiu.actuator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthContributorRegistry;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BuxiuReadyHealthIndicatorTest {

    @Autowired
    private HealthContributorRegistry registry;

    @Test
    void testRegistryContainsBuxiuReady() {
        Assertions.assertNotNull(registry.getContributor("buxiuReady"));
    }
}
