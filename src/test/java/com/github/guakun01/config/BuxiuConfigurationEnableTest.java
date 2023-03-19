package com.github.guakun01.config;

import com.github.guakun01.buxiu.BuXiuApplication;
import com.github.guakun01.buxiu.BuXiuProperties;
import com.github.guakun01.buxiu.actuator.BuxiuReadyHealthIndicator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = BuXiuApplication.class, properties = {
        "buxiu.ready=true",
        "buxiu.open-hours=08:00-22:00"
})
public class BuxiuConfigurationEnableTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testPropertiesBeanAvailable() {
        Assertions.assertNotNull(applicationContext.getBean(BuXiuProperties.class));
        Assertions.assertTrue(applicationContext.containsBean("buxiu-com.github.guakun01.buxiu.BuXiuProperties"));
    }

    @Test
    void testPropertyValues() {
        BuXiuProperties properties = applicationContext.getBean(BuXiuProperties.class);
        Assertions.assertTrue(properties.isReady());
        Assertions.assertEquals("08:00-22:00", properties.getOpenHours());
    }

    @Test
    void testIndicatorUp() {
        BuxiuReadyHealthIndicator indicator = applicationContext.getBean(BuxiuReadyHealthIndicator.class);
        Assertions.assertEquals(Status.UP, indicator.getHealth(false).getStatus());
    }
}
