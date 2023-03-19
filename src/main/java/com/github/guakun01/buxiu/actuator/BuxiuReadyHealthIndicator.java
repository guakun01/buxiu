package com.github.guakun01.buxiu.actuator;

import com.github.guakun01.buxiu.BuXiuProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BuxiuReadyHealthIndicator extends AbstractHealthIndicator {

    private BuXiuProperties buXiuProperties;

    public BuxiuReadyHealthIndicator(ObjectProvider<BuXiuProperties> buXiuProperties) {
        this.buXiuProperties = buXiuProperties.getIfAvailable();
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (Objects.isNull(buXiuProperties) || !buXiuProperties.isReady()) {
            builder.down();
        } else {
            builder.up();
        }
    }
}
