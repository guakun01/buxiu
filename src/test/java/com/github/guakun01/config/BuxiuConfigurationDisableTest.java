package com.github.guakun01.config;

import com.github.guakun01.buxiu.BuXiuApplication;
import com.github.guakun01.buxiu.actuator.BuxiuReadyHealthIndicator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = BuXiuApplication.class, properties = {
        "buxiu.ready=false",
})
public class BuxiuConfigurationDisableTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testPropertiesBeanUnavailable() {
        Assertions.assertEquals("false", applicationContext.getEnvironment().getProperty("buxiu.ready"));
        Assertions.assertFalse(applicationContext.containsBean("buxiu-com.github.guakun01.buxiu.BuXiuProperties"));
    }

    @Test
    void testIndicatorDown() {
        BuxiuReadyHealthIndicator indicator = applicationContext.getBean(BuxiuReadyHealthIndicator.class);
        Assertions.assertEquals(Status.DOWN, indicator.getHealth(false).getStatus());
    }
}
